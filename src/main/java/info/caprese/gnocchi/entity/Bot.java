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
@Table(name = "BOT")
public class Bot implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private String botId;
	private String name;
	private String twitterUserId;
	private String accessToken;
	private String secret;
}