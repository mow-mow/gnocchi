package info.caprese.gnocchi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "GOURMET")
public class Gourmet implements Serializable {
	@Id
	private String gourmetId;
	private String botId;
	private String menu;
	private String shopName;
	private String comment;
	private int tweetCount;
	private String userId;
	private LocalDateTime updateDate;
	private LocalDateTime insertDate;
	private boolean invalidFlag;
	private boolean literatureDisplayFlag=true;
	@Version
	private int version;
	private boolean deleteFlag;
}