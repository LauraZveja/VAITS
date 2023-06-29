package lv.vaits.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "message_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Message {

	@Setter(value = AccessLevel.NONE)
	@Column(name = "Idm")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idm;

	@Column(name = "Source")
	@NotEmpty(message = "The message requiers a sender")
	private String source;

	@Column(name = "Text")
	@NotEmpty(message = "The message requires text")
	private String text;

	@ManyToOne
	@JoinColumn(name = "Idt")
	private Thesis thesis;

	public Message(String source, String text, Thesis thesis) {

		this.source = source;
		this.text = text;
		this.thesis = thesis;
	}

}