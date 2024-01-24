package lv.vaits.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

	public StudentDTO(@NotNull String password, @NotNull @Email String email, @NotNull String username,
			@NotNull @NotBlank @Pattern(regexp = "[A-ZĒŪĪĻĶŠĀŽČŅ]{1}[a-zēūīļķšāžčņ\\ ]+", message = "NAME MUST START WITH A CAPITAL LETTER!") @Size(min = 3, max = 30) String name,
			@NotBlank @Pattern(regexp = "[A-ZĒŪĪĻĶŠĀŽČŅ]{1}[a-zēūīļķšāžčņ\\ ]+", message = "SURNAME MUST START WITH A CAPITAL LETTER!") @Size(min = 3, max = 30) String surname,
			@NotNull @Pattern(regexp = "[0-9]{6}-[0-9]{5}", message = "Neatbilstošs personas kods") @Size(min = 12, max = 12) String personcode,
			@NotNull @Pattern(regexp = "[0-9]{8,20}") @Size(min = 8, max = 20) String matriculaNo) {
		super();
		this.password = password;
		this.email = email;
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.personcode = personcode;
		this.matriculaNo = matriculaNo;
	}

	@NotNull
	private String password;

	@NotNull
	@Email
	private String email;

	@NotNull
	private String username;

	@NotNull
	private String authority = "USER";

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

	private boolean financialDebt = false;

}
