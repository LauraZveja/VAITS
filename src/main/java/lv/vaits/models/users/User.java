package lv.vaits.models.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "user_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
//pielikt toString, ja nepieciešams
public class User {
	
	@Setter(value = AccessLevel.NONE)
	@Column(name = "Idu")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idu;
	
	@Column(name = "Password")
	@NotNull
	//TODO Papildināt ar validāciju, kad ir zināms passwordEncoder
	private String password;//TODO kad pievienos Spring Security, tad jāuzliek passwordEncoder
	
	@Column(name = "Email")
	@NotNull
	@Email
	private String email;

	public User(@NotNull String password, @NotNull @Email String email) {
		
		this.password = password;
		this.email = email;
	}
	
	//TODO uztaisīt one to one saiti 
	@OneToOne(mappedBy = "user")
	private Person person;

	
	

}
