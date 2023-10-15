package lv.vaits.models;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lv.vaits.models.users.Student;

@Table(name = "course_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Course {

	@Setter(value = AccessLevel.NONE)
	@Column(name = "Idc")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idc;

	@Column(name = "Title")
	@Size(min = 3, max = 25)
	@NotNull
	@Pattern(regexp = "[A-ZĒŪĪĻĶŠĀŽČŅ]{1}[a-zēūīļķšāžčņ\\ ]+", message = "Pirmajam burtam jābūt lielajam")
	private String title;

	@Column(name = "CreditPoints")
	@NotNull
	@Min(value = 1)
	@Max(value = 20)
	private int creditPoints;

	@ManyToMany(mappedBy = "debtCourse")
	private Collection<Student> debtStudents = new ArrayList<>();

	public Course(
			@Size(min = 3, max = 25) @NotNull @Pattern(regexp = "[A-ZĒŪĪĻĶŠĀŽČŅ]{1}[a-zēūīļķšāžčņ\\ ]+", message = "Pirmajam burtam jābūt lielajam") String title,
			@NotNull @Min(1) @Max(20) int creditPoints) {
		super();
		this.title = title;
		this.creditPoints = creditPoints;
		this.debtStudents = new ArrayList<>();
	}

	public void addStudent(Student student) {
		if (!debtStudents.contains(student)) {
			debtStudents.add(student);
		}
	}

}
