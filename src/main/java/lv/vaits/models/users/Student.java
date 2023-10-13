package lv.vaits.models.users;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.vaits.models.Course;
import lv.vaits.models.StudentStudyProgram;
import lv.vaits.models.Thesis;

@Table(name = "student_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name = "Idp", column = @Column(name = "Ids"))
public class Student extends Person {

	@Column(name = "MatriculaNo")
	@NotNull
	@Size(min = 8, max = 20)
	@Pattern(regexp = "[0-9]{8,20}")
	private String matriculaNo;

	@Column(name = "FinancialDebt")
	private boolean financialDebt;

	@ManyToMany
	@JoinTable(name = "student_debt_course_table", joinColumns = @JoinColumn(name = "Ids"), inverseJoinColumns = @JoinColumn(name = "Idc"))
	private Collection<Course> debtCourse = new ArrayList<>();

	@OneToMany(mappedBy = "student")
	private Collection<Thesis> thesis;

	@OneToMany(mappedBy = "student")
	private Collection<StudentStudyProgram> studentStudyProgram;

	public Student(
			@NotNull @Size(min = 3, max = 15) @NotNull @Pattern(regexp = "[A-ZĒŪĪĻĶŠĀŽČŅ]{1}[a-zēūīļķšāžčņ\\ ]+", message = "NAME MUST START WITH A CAPITAL LETTER!") String name,
			@Pattern(regexp = "[A-ZĒŪĪĻĶŠĀŽČŅ]{1}[a-zēūīļķšāžčņ\\ ]+", message = "SURNAME MUST START WITH A CAPITAL LETTER!") @NotNull String surname,
			@Pattern(regexp = "[0-9]{6}-[0-9]{5}", message = "Neatbilstošs personas kods") @NotNull @Size(min = 12, max = 12) String personcode,
			User user, @NotNull @Size(min = 8, max = 20) @Pattern(regexp = "[0-9]{8,20}") String matriculaNo,
			boolean financialDebt) {
		super(name, surname, personcode, user);
		this.matriculaNo = matriculaNo;
		this.financialDebt = financialDebt;
	}

	public void addDebtCourse(Course course) {
		if (!debtCourse.contains(course)) {
			debtCourse.add(course);
		}
	}

}
