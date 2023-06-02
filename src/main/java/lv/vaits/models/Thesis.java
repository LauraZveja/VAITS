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
import lombok.ToString;
import lv.vaits.models.users.AcademicStaff;
import lv.vaits.models.users.Student;

@Table(name = "thesis_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
//@ToString
public class Thesis {

	@Setter(value = AccessLevel.NONE)
	@Column(name = "Idt")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idt;

	// Pievienot nepieciešamās validācijas.
	@Column(name = "TitleLv")
	private String titleLv;

	// Pievienot nepieciešamās validācijas.
	@Column(name = "TitleEn")
	private String titleEn;

	// Pievienot nepieciešamās validācijas.
	@Column(name = "Aim")
	private String aim;

	// Pievienot nepieciešamās validācijas.
	@Column(name = "Tasks")
	private String tasks;

	//TODO servisā vai konstruktorā pie jauna objekta izveidas jāuzliek LocalDateTime.now()
	@Column(name = "SubmitDateTime")
	private LocalDateTime submitDateTime;
	
	@Column(name = "statusFromSupervisor")
	private boolean statusFromSupervisor;
	
	//TODO servisā vai konstruktorā uzlikt submit pēc noklusējuma
	@Column(name = "accStatus")
	private AcceptanceStatus accStatus;
	
	@Column(name = "accDateTime")
	private LocalDateTime accDateTime;
	
	@ManyToOne
	@JoinColumn(name = "Ids")
	private Student student;
	
	@ManyToOne
	@JoinColumn(name = "Ida")
	private AcademicStaff supervisor;
	
	//TODO izveidot saiti, ja nepieciešams, ar konsultantu, vērtētāju utt/
	

}