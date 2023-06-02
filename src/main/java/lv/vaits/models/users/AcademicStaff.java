package lv.vaits.models.users;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

}
