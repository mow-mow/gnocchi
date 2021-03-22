package info.caprese.gnocchi.service;

import info.caprese.gnocchi.entity.*;
import info.caprese.gnocchi.repository.*;
import info.caprese.gnocchi.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TweetService {

    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    GourmetRepository gourmetRepository;
    @Autowired
    TweetMessageLogic tweetMessageLogic;
    @Autowired
    TweetLogic tweetLogic;
    @Autowired
    TweetDrawLogic tweetDrawLogic;

    public void tweet(Schedule schedule) {

        log.info("ツイートサービス処理 - [開始]");

        // 対象グルメ選出
        Gourmet gourmet = tweetDrawLogic.drawGourmet();
        // ツイートメッセージ生成
        String message = tweetMessageLogic.renderTemplate(schedule.getMessageTemplate(), gourmet);
        // ツイート処理
        boolean tweetResult = tweetLogic.tweet(schedule.getBotId(), schedule.getScheduleId(), message);

        updateStatus(schedule, gourmet, tweetResult);

        log.info("ツイートサービス処理 - [完了]");
    }

    @Transactional
    private void updateStatus(Schedule schedule, Gourmet gourmet, boolean tweetResult) {
        LocalDateTime sysdate = LocalDateTime.now();

        schedule.setGourmetId(gourmet.getGourmetId());
        schedule.setStatus(tweetResult ? ScheduleStatus.COMPLETE : ScheduleStatus.ERROR);
        schedule.setUpdateDate(sysdate);
        scheduleRepository.save(schedule);

        gourmet.setTweetCount(gourmet.getTweetCount() + 1);
        gourmet.setUpdateDate(sysdate);
        gourmetRepository.save(gourmet);
    }

}





