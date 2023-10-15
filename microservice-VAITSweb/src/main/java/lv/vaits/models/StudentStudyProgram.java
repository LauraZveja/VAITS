package lv.vaits.models;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lv.vaits.models.users.Student;

@Table(name = "student_study_program_list_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class StudentStudyProgram {

	@Column(name = "Idssp")
	@Id
	@Setter(value = AccessLevel.NONE)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idssp;

	@ManyToOne
	@JoinColumn(name = "Ids")
	@NotNull
	private Student student;

	@ManyToOne
	@JoinColumn(name = "Idsp")
	@NotNull
	private StudyProgram studyProgram;

	@Column(name = "startDate")
	@NotNull
	private LocalDate startDate;

	@Column(name = "endDate")
	@NotNull
	private LocalDate endDate;

	public StudentStudyProgram(@NotNull Student student, @NotNull StudyProgram studyProgram,
			@NotNull LocalDate startDate, @NotNull LocalDate endDate) {
		this.student = student;
		this.studyProgram = studyProgram;
		this.startDate = startDate;
		this.endDate = endDate;
	}

}
