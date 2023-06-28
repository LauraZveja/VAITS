package lv.vaits.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "calendar_schedule")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class CalendarSchedule {

	@Column(name = "Idcs")
	@Id
	@Setter(value = AccessLevel.NONE)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idcs;
	
	@Column(name = "study_year")
	@NotNull
	@Min(value = 2023, message = "STUDY YEAR CANNOT BE LESS THAN 2023")
	@Max(value = 2099, message = "STUDY YEAR CANNOT BE GREATER THAN 2099")
	private int studyYear;
	
	@Column(name = "Activity")
	@NotNull
	@Pattern(regexp = "^[A-ZĀČĒĢĪĶĻŅŠŪŽ].*", message = "Activity must start with a capital letter")
	private String activity;
	
	@Column(name = "deadline")
	@NotNull
	private LocalDate deadline;
	
	@ManyToOne
	@JoinColumn(name = "Idsp")
	private StudyProgram studyProgram;

	public CalendarSchedule(
			@NotNull @Min(value = 2023, message = "STUDY YEAR CANNOT BE LESS THAN 2023") @Max(value = 2099, message = "STUDY YEAR CANNOT BE GREATER THAN 2099") int studyYear,
			@NotNull @Pattern(regexp = "^[A-ZĀČĒĢĪĶĻŅŠŪŽ].*", message = "Activity must start with a capital letter") String activity,
			@NotNull LocalDate deadline, StudyProgram studyProgram) {
		this.studyYear = studyYear;
		this.activity = activity;
		this.deadline = deadline;
		this.studyProgram = studyProgram;
	}
	
	
	
	
}
