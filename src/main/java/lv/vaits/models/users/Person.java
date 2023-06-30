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
import jakarta.validation.constraints.NotBlank;
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

	@Setter(value = AccessLevel.NONE)
	@Column(name = "Idp")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idp;

	@NotNull
	@Column(name = "Name")
	@NotBlank
	@Pattern(regexp = "[A-ZĒŪĪĻĶŠĀŽČŅ]{1}[a-zēūīļķšāžčņ\\ ]+", message = "NAME MUST START WITH A CAPITAL LETTER!")
	@Size(min = 3, max = 30)
	private String name;

	@Column(name = "Surname")
	@NotBlank
	@Pattern(regexp = "[A-ZĒŪĪĻĶŠĀŽČŅ]{1}[a-zēūīļķšāžčņ\\ ]+", message = "SURNAME MUST START WITH A CAPITAL LETTER!")
	@Size(min = 3, max = 30)
	private String surname;

	@Pattern(regexp = "[0-9]{6}-[0-9]{5}", message = "Neatbilstošs personas kods")
	@NotNull
	@Size(min = 12, max = 12)
	@Column(name = "Personcode")
	private String personcode;

	@OneToOne
	@JoinColumn(name = "Idu")
	private User user;

	public Person(
			@NotNull @Size(min = 3, max = 15) @NotNull @Pattern(regexp = "[A-ZĒŪĪĻĶŠĀŽČŅ]{1}[a-zēūīļķšāžčņ\\ ]+", message = "NAME MUST START WITH A CAPITAL LETTER!") String name,
			@Pattern(regexp = "[A-ZĒŪĪĻĶŠĀŽČŅ]{1}[a-zēūīļķšāžčņ\\ ]+", message = "SURNAME MUST START WITH A CAPITAL LETTER!") @NotNull String surname,
			@Pattern(regexp = "[0-9]{6}-[0-9]{5}", message = "Neatbilstošs personas kods") @NotNull @Size(min = 12, max = 12) String personcode,
			User user) {

		this.name = name;
		this.surname = surname;
		this.personcode = personcode;
		this.user = user;
	}

}
