package info.caprese.gnocchi.service;

import info.caprese.gnocchi.entity.*;
import info.caprese.gnocchi.repository.*;
import info.caprese.gnocchi.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class TweetLogic {

    @Value("${spring.social.twitter.consumer-id}")
    private String consumerId;
    @Value("${spring.social.twitter.consumer-secret}")
    private String consumerSecret;
    @Autowired
    BotRepository botRepository;
    @Autowired
    TweetJournalRepository tweetJournalRepository;

    public boolean tweet(String botId, int scheduleId, String message) {

        Optional<Bot> bot = botRepository.findById(botId);
        if(!bot.isPresent()) {
            log.error("BOT存在チェック - [NG] : " + botId);
        }
        log.info("BOT存在チェック - [OK]" + botId);

        TweetJournal tweetJournal = generateTweetJournal(botId, scheduleId, message);
        tweetJournalRepository.save(tweetJournal);

        Twitter twitter = generateTwitter(bot.get().getAccessToken(), bot.get().getSecret());
        Status status;
        try {
            status = twitter.updateStatus(message);
        } catch (TwitterException e) {
            log.error("ツイート - [NG]", e);
            return false;
        }

        updateTweetJournal(tweetJournal, status);
        tweetJournalRepository.save(tweetJournal);

        log.info("ツイート - [OK]");
        log.info("message : " + status.getText());
        return true;
    }

    public TweetJournal generateTweetJournal(String botId, int scheduleId, String message) {
        LocalDateTime sysdate = LocalDateTime.now();
        TweetJournal tweetJournal = new TweetJournal();
        tweetJournal.setTargetDate(DateUtil.format(sysdate, "yyyyMM"));
        tweetJournal.setBotId(botId);
        tweetJournal.setScheduleId(scheduleId);
        tweetJournal.setMessage(message);
        tweetJournal.setUpdateDate(sysdate);
        tweetJournal.setInsertDate(sysdate);
        tweetJournal.setDeleteFlag(false);
        return tweetJournal;
    }

    public void updateTweetJournal(TweetJournal tweetJournal, Status status) {
        LocalDateTime sysdate = LocalDateTime.now();
        tweetJournal.setStatus("OK");
        tweetJournal.setUpdateDate(sysdate);
    }

    private Twitter generateTwitter(String accessToken, String secret) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerId)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(secret);
        TwitterFactory tf = new TwitterFactory(cb.build());
        return tf.getInstance();
    }
}





