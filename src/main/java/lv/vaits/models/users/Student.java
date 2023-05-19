package lv.vaits.models.users;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "student_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student extends Person{
	
	private String matriculaNo;
	
	

}
