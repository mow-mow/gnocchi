package info.caprese.gnocchi.repository;

import info.caprese.gnocchi.entity.Schedule;
import info.caprese.gnocchi.entity.ScheduleStatus;
import info.caprese.gnocchi.entity.ScheduleTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleTemplateRepository extends JpaRepository<ScheduleTemplate, Integer> {

	List<ScheduleTemplate> findByBotIdAndInvalidFlagAndDeleteFlag(String botId, boolean invalidFlag, boolean deleteFlag);

	List<ScheduleTemplate> findByInvalidFlagAndDeleteFlag(boolean invalidFlag, boolean deleteFlag);
}