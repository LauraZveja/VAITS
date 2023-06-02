package lv.vaits.models.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "person_table")
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public class Person {
	
	//TODO uzlikt Data JPA anotācijas
	//TODO uzlikt atbilstošās validāciju anotācijas
	//TODO izveidot Student, Course, Grade
	@Setter(value = AccessLevel.NONE)
	@Column(name = "Idp")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idp;
	
	@NotNull
	@Column(name = "Name")
	@Size(min = 3, max =15)
	@NotNull
	//@Pattern(regexp = "[A-ZĒŪĻĶ]{1}[a-zēūļķ]+", message = "Pirmajam burtam jābūt lielajam")
	private String name;
	
	@Column(name = "Surname")
	//@Pattern(regexp = "[A-ZĒŪĻĶ]{1}[a-zēūļķ]+", message = "Pirmajam burtam jābūt lielajam")
	@NotNull
	private String surname;
	
	//@Pattern(regexp = "[0-9] {6} - [0-9] {5}", message = "Neatbilstošs personas kods")
	@NotNull
	//@Size(min = 12, max = 12)
	//TODO apdomāt un pievienot risinājumu ārzemju studentiem un jaunajiem LV personas kodiem
	@Column(name = "Personcode")
	private String personcode;
	
	//TODO uztaisīt one to one saiti
	
	@OneToOne
	@JoinColumn(name = "Idu")
	private User user;

	public Person(
			@NotNull @Size(min = 3, max = 15) @NotNull @Pattern(regexp = "[A-ZĒŪĻĶ]{1}[a-zēūļķ]+", message = "Pirmajam burtam jābūt lielajam") String name,
			@Pattern(regexp = "[A-ZĒŪĻĶ]{1}[a-zēūļķ]+", message = "Pirmajam burtam jābūt lielajam") @NotNull String surname,
			@Pattern(regexp = "[0-9] {6} - [0-9] {5}", message = "Neatbilstošs personas kods") @NotNull @Size(min = 12, max = 12) String personcode,
			User user) {
		
		this.name = name;
		this.surname = surname;
		this.personcode = personcode;
		this.user = user;
	}
	
	

}
