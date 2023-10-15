package lv.vaits.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "thesis_applications_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class ThesisApplications {

	@Column(name = "Idtca")
	@Id
	@Setter(value = AccessLevel.NONE)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idtca;

	@Column(name = "TitleLv")
	@NotNull
	@NotBlank
	@Pattern(regexp = "^[A-ZĀČĒĢĪĶĻŅŠŪŽ].*", message = "TITLE START WITH CAPITAL LETTER!")
	@Size(min = 3, max = 30)
	private String titleLv;

	@Column(name = "TitleEn")
	@NotNull
	@NotBlank
	@Pattern(regexp = "^[A-ZĀČĒĢĪĶĻŅŠŪŽ].*", message = "TITLE START WITH CAPITAL LETTER!")
	@Size(min = 3, max = 30)
	private String titleEn;

	@Column(name = "Aim")
	@NotNull
	@NotBlank
	@Pattern(regexp = "^[A-ZĀČĒĢĪĶĻŅŠŪŽ].*", message = "AIM START WITH CAPITAL LETTER!")
	@Size(min = 20, max = 100, message = "SIZE IS DEFINED AS BETWEEN 20 AND 200 SYMBOLS!")
	private String aim;

	@Column(name = "Tasks")
	@NotNull
	@NotBlank
	@Pattern(regexp = "^[A-ZĀČĒĢĪĶĻŅŠŪŽ].*", message = "TASKS START WITH CAPITAL LETTER!")
	@Size(min = 20, max = 200, message = "SIZE IS DEFINED AS BETWEEN 20 AND 200 SYMBOLS!")
	private String tasks;

	@Column(name = "SubmitDateTime")
	@NotNull
	private LocalDateTime submitDateTime;

	@ManyToOne
	@JoinColumn(name = "Idt")
	private Thesis thesis;

	public ThesisApplications(
			@NotNull @NotBlank @Pattern(regexp = "^[A-ZĀČĒĢĪĶĻŅŠŪŽ].*", message = "TITLE START WITH CAPITAL LETTER!") @Size(min = 3, max = 30) String titleLv,
			@NotNull @NotBlank @Pattern(regexp = "^[A-ZĀČĒĢĪĶĻŅŠŪŽ].*", message = "TITLE START WITH CAPITAL LETTER!") @Size(min = 3, max = 30) String titleEn,
			@NotNull @NotBlank @Pattern(regexp = "^[A-ZĀČĒĢĪĶĻŅŠŪŽ].*", message = "AIM START WITH CAPITAL LETTER!") @Size(min = 20, max = 100, message = "SIZE IS DEFINED AS BETWEEN 20 AND 200 SYMBOLS!") String aim,
			@NotNull @NotBlank @Pattern(regexp = "^[A-ZĀČĒĢĪĶĻŅŠŪŽ].*", message = "TASKS START WITH CAPITAL LETTER!") @Size(min = 20, max = 200, message = "SIZE IS DEFINED AS BETWEEN 20 AND 200 SYMBOLS!") String tasks,
			@NotNull LocalDateTime submitDateTime, Thesis thesis) {
		this.titleLv = titleLv;
		this.titleEn = titleEn;
		this.aim = aim;
		this.tasks = tasks;
		this.submitDateTime = submitDateTime;
		this.thesis = thesis;
	}

}
