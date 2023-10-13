package lv.vaits.models.users;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.vaits.models.Comment;
import lv.vaits.models.Course;
import lv.vaits.models.Thesis;

@Table(name = "academic_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name = "Idp", column = @Column(name = "Ida"))
public class AcademicStaff extends Person{
	
	@Column(name = "Degree")
	@Enumerated(EnumType.STRING)
	private Degree degree;
	
	@OneToMany(mappedBy = "supervisor")
	private Collection<Thesis> thesis;
	
	@ManyToMany(mappedBy = "reviewers")
	private Collection<Thesis> thesisForReviews = new ArrayList<>();
	
	public void addThesisForReviews(Thesis inputThesis) {
		if(!thesisForReviews.contains(inputThesis)) {
			thesisForReviews.add(inputThesis);
		}
	}

	public AcademicStaff(
			@NotNull @Size(min = 3, max = 15) @NotNull @Pattern(regexp = "[A-ZĒŪĪĻĶŠĀŽČŅ]{1}[a-zēūīļķšāžčņ\\ ]+", message = "NAME MUST START WITH A CAPITAL LETTER!") String name,
			@Pattern(regexp = "[A-ZĒŪĪĻĶŠĀŽČŅ]{1}[a-zēūīļķšāžčņ\\ ]+", message = "SURNAME MUST START WITH A CAPITAL LETTER!") @NotNull String surname,
			@Pattern(regexp = "[0-9]{6}-[0-9]{5}", message = "Neatbilstošs personas kods") @NotNull @Size(min = 12, max = 12) String personcode,
			User user, Degree degree) {
		super(name, surname, personcode, user);
		this.degree = degree;
	}
	
	@OneToMany(mappedBy = "staff")
	private Collection<Comment> comments;
	

}
