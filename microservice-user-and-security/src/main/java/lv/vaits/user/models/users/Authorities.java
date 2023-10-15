package lv.vaits.user.models.users;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "authority_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Authorities {

	@Id
	@GeneratedValue
	@Column(name = "AuthorityId")
	@Getter
	private int AuthorityId;

	@NotNull
	@Column(name = "Title")
	@Getter
	@Setter
	@Pattern(regexp = "[A-Z]{3,8}")
	private String title;

	@ManyToMany
	@JoinTable(name = "user_authorities_table", joinColumns = @JoinColumn(name = "AuthorityId"), inverseJoinColumns = @JoinColumn(name = "Idu"))

	private Collection<User> myUsers = new ArrayList<>();

	public Authorities(String title) {
		setTitle(title);
	}

	public void addUser(User user) {
		if (!myUsers.contains(user)) {
			myUsers.add(user);
		}
	}

	public void removeUser(User user) {
		if (myUsers.contains(user)) {
			myUsers.remove(user);
		}
	}

}
