package lv.vaits.models;

import java.time.LocalDateTime;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.vaits.models.users.AcademicStaff;

@Table(name = "comment_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {
	
	@Setter(value = AccessLevel.NONE)
	@Column(name = "Idco")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idco;
	
	@Column(name = "Description")
	private String description;
	
	@Column(name = "commentDate")
	private LocalDateTime commentDate;
	
	@ManyToOne
	@JoinColumn(name="Ida")
	private AcademicStaff staff;
	
	@ManyToOne
	@JoinColumn(name = "Idt")
	private Thesis thesis;

	public Comment(String description, AcademicStaff staff, Thesis thesis) {
		
		this.description = description;
		this.staff = staff;
		this.thesis = thesis;
		this.commentDate = LocalDateTime.now();
	}
	
	

}
