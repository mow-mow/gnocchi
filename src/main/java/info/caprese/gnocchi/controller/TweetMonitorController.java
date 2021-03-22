package info.caprese.gnocchi.controller;

import info.caprese.gnocchi.entity.*;
import info.caprese.gnocchi.repository.*;
import info.caprese.gnocchi.service.TweetMonitorService;
import info.caprese.gnocchi.service.TweetService;
import info.caprese.gnocchi.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
public class TweetMonitorController {

    @Autowired
    TweetMonitorService tweetMonitorService;

    @Scheduled(fixedDelay = 5000)
    public void checkTweetSchedule() {
        MDC.put("user_id", "monitor-batch");
        log.info("ツイート監視処理 - 開始");
        tweetMonitorService.checkTweetSchedule();
        log.info("ツイート監視処理 - 終了");
        MDC.clear();;
    }


}





