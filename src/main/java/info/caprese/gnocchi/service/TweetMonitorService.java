package info.caprese.gnocchi.service;

import info.caprese.gnocchi.entity.Schedule;
import info.caprese.gnocchi.entity.ScheduleStatus;
import info.caprese.gnocchi.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class TweetMonitorService {

    @Autowired
    TweetService tweetService;
    @Autowired
    ScheduleRepository scheduleRepository;

    public void checkTweetSchedule() {
        List<Schedule> result = scheduleRepository.findByTargetDateAndScheduleDateLessThanEqualAndStatus("202103", LocalDateTime.now(), ScheduleStatus.UNPROCESSING);

        for (Schedule schedule : result) {
            log.info("ツイート対象あり:" + schedule.getScheduleDate());
            schedule.setStatus(ScheduleStatus.PROCESSING);
            schedule.setUpdateDate(LocalDateTime.now());
            scheduleRepository.save(schedule);
            log.info("スケジュール更新処理 - [OK]");

            tweet(schedule);
        }
    }

    @Async
    private void tweet(Schedule schedule) {
        MDC.put("user_id", "monitor-batch");
        tweetService.tweet(schedule);
        MDC.clear();;
    }
}





