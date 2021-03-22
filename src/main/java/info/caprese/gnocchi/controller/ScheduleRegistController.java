package info.caprese.gnocchi.controller;

import info.caprese.gnocchi.entity.Schedule;
import info.caprese.gnocchi.entity.ScheduleStatus;
import info.caprese.gnocchi.entity.ScheduleTemplate;
import info.caprese.gnocchi.repository.ScheduleRepository;
import info.caprese.gnocchi.repository.ScheduleTemplateRepository;
import info.caprese.gnocchi.service.ScheduleRegistService;
import info.caprese.gnocchi.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Slf4j
public class ScheduleRegistController {
    @Autowired
    ScheduleRegistService service;

//    @Scheduled(fixedDelay = 5000)
    public void checkTweetSchedule() {
        MDC.put("user_id", "regist-batch");
        log.info("スケジュール登録処理 - 開始");
        service.execute();
        log.info("スケジュール登録処理 - 終了");
        MDC.clear();;
    }

}
