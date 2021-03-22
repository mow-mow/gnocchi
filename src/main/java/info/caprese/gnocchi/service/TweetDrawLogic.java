package info.caprese.gnocchi.service;

import info.caprese.gnocchi.entity.Gourmet;
import info.caprese.gnocchi.entity.Schedule;
import info.caprese.gnocchi.entity.ScheduleStatus;
import info.caprese.gnocchi.entity.TweetJournal;
import info.caprese.gnocchi.repository.GourmetRepository;
import info.caprese.gnocchi.repository.ScheduleRepository;
import info.caprese.gnocchi.repository.TweetJournalRepository;
import info.caprese.gnocchi.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class TweetDrawLogic {

    @Autowired
    GourmetRepository gourmetRepository;

    public Gourmet drawGourmet() {
        log.info("ツイート抽選処理 - [開始]");
        Gourmet gourmet = gourmetRepository.findAll().get(0);
        log.info("ツイート抽選処理 - [終了]");
        return gourmet;
    }

}





