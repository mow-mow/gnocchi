package info.caprese.gnocchi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SCHEDULE")
public class Schedule implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer scheduleId;

	private String targetDate;

	private String botId;

	private LocalDateTime scheduleDate;

	private String messageTemplate;
	private int tweetJournalId;
	private String gourmetId;
	private ScheduleStatus status;

	private LocalDateTime updateDate;
	private LocalDateTime insertDate;
	@Version
	private int version;
	private boolean invalidFlag;
	private boolean deleteFlag;
}