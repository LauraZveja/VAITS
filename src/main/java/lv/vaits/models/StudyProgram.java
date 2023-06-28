package lv.vaits.models;

import java.util.Collection;

import jakarta.persistence.*;
import java.util.ArrayList;
import jakarta.validation.constraints.*;
import lombok.*;

@Table(name = "study_program_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class StudyProgram {

	@Column(name = "Idsp")
	@Id
	@Setter(value = AccessLevel.NONE)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idsp;

	@Column(name = "CourseTitleLv")
	@Size(min = 3, max = 30)
	@NotNull
	@NotBlank
	private String courseTitleLv;

	@Column(name = "Faculty")
	@Enumerated(EnumType.STRING)
	@NotNull(message = "STUDY PROGRAM REQUIRES A FACULTY!")
	private Faculty faculty;

	@Column(name = "LevelOfStudy")
	@Enumerated(EnumType.STRING)
	@NotNull(message = "STUDY PROGRAM REQUIRES A LEVEL OF STUDY!")
	private LevelOfStudy levelOfStudy;

	@OneToMany(mappedBy = "studyProgram")
	private Collection<StudentStudyProgram> studentStudyProgram;

	@OneToMany(mappedBy = "studyProgram")
	private Collection<CalendarSchedule> calendarSchedule;

	public StudyProgram(@Size(min = 3, max = 30) @NotNull @NotBlank String courseTitleLv,
			@NotNull(message = "STUDY PROGRAM REQUIRES A FACULTY!") Faculty faculty,
			@NotNull(message = "STUDY PROGRAM REQUIRES A LEVEL OF STUDY!") LevelOfStudy levelOfStudy) {
		this.courseTitleLv = courseTitleLv;
		this.faculty = faculty;
		this.levelOfStudy = levelOfStudy;
		this.calendarSchedule = new ArrayList<>();
	}
	
	public void addCalendarSchedule(CalendarSchedule schedule) {
		if(!calendarSchedule.contains(schedule)) {
			calendarSchedule.add(schedule);
		}
	}

}
