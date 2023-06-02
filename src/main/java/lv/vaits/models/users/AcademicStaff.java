package lv.vaits.models.users;

import java.util.Collection;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.vaits.models.Course;

@Table(name = "academic_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name = "Idp", column = @Column(name = "Ida"))
public class AcademicStaff extends Person{
	
	@Column(name = "Degree")
	private Degree degree;

}
