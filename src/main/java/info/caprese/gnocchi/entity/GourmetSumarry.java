package info.caprese.gnocchi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class GourmetSumarry {
	@Id
	private String gourmetId;
	private String menu;
	private String shopName;
	private String comment;
	private int tweetCount;
	private String displayName;
	private LocalDateTime updateDate;
	private LocalDateTime insertDate;
}