package lv.vaits.user.dto;

import java.util.Collection;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lv.vaits.user.models.users.Authorities;

@Getter
@Setter
@AllArgsConstructor
public class StudentDTO {

	@Setter(value = AccessLevel.NONE)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idu;

	@NotNull
	private String password;

	@NotNull
	@Email
	private String email;

	@NotNull
	private String username;

	private Collection<Authorities> myAuthorities;

	@NotNull
	@NotBlank
	@Pattern(regexp = "[A-ZĒŪĪĻĶŠĀŽČŅ]{1}[a-zēūīļķšāžčņ\\ ]+", message = "NAME MUST START WITH A CAPITAL LETTER!")
	@Size(min = 3, max = 30)
	private String name;

	@NotBlank
	@Pattern(regexp = "[A-ZĒŪĪĻĶŠĀŽČŅ]{1}[a-zēūīļķšāžčņ\\ ]+", message = "SURNAME MUST START WITH A CAPITAL LETTER!")
	@Size(min = 3, max = 30)
	private String surname;

	@NotNull
	@Pattern(regexp = "[0-9]{6}-[0-9]{5}", message = "Neatbilstošs personas kods")
	@Size(min = 12, max = 12)
	private String personcode;

	@NotNull
	@Pattern(regexp = "[0-9]{8,20}")
	@Size(min = 8, max = 20)
	private String matriculaNo;

	private boolean financialDebt;

}
