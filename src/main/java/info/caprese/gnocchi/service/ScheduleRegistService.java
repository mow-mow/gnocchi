package info.caprese.gnocchi.service;

import info.caprese.gnocchi.entity.Schedule;
import info.caprese.gnocchi.entity.ScheduleStatus;
import info.caprese.gnocchi.entity.ScheduleTemplate;
import info.caprese.gnocchi.repository.ScheduleRepository;
import info.caprese.gnocchi.repository.ScheduleTemplateRepository;
import info.caprese.gnocchi.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ScheduleRegistService {
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    ScheduleTemplateRepository scheduleTemplateRepository;

    public void execute() {

        List<ScheduleTemplate> result = scheduleTemplateRepository.findByInvalidFlagAndDeleteFlag(false,false);

        LocalDateTime sysdate = LocalDateTime.now();
        for (ScheduleTemplate template : result) {
            Schedule schedule = new Schedule();

            schedule.setTargetDate(DateUtil.format(sysdate, "yyyyMM"));
            schedule.setBotId(template.getBotId());
            schedule.setScheduleDate(LocalDateTime.of(sysdate.getYear(), sysdate.getMonth(), sysdate.getDayOfMonth(), template.getHour(), template.getMinutes(), 0));

            schedule.setMessageTemplate(template.getMessageTemplate());
            schedule.setStatus(ScheduleStatus.UNPROCESSING);
            schedule.setUpdateDate(sysdate);
            schedule.setInsertDate(sysdate);
            schedule.setInvalidFlag(false);
            schedule.setDeleteFlag(false);

            scheduleRepository.save(schedule);

        }


    }

}
