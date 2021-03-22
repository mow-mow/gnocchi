package info.caprese.gnocchi.repository;

import info.caprese.gnocchi.entity.Schedule;
import info.caprese.gnocchi.entity.ScheduleStatus;
import info.caprese.gnocchi.entity.TweetJournal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TweetJournalRepository extends JpaRepository<TweetJournal, Integer> {
}