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
@Table(name = "schedule_template")
public class ScheduleTemplate implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int scheduleTemplateId;

	private String botId;
	private String name;
	private int hour;
	private int minutes;
	private String messageTemplate;
	private String messageTemplateJs;
	private LocalDateTime updateDate;
	private LocalDateTime insertDate;
	private boolean invalidFlag;
	private boolean deleteFlag;
}