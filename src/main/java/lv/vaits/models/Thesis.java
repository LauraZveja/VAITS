package lv.vaits.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

	// TODO servisā vai konstruktorā pie jauna objekta izveidas jāuzliek
	// LocalDateTime.now()
	@Column(name = "SubmitDateTime")
	private LocalDateTime submitDateTime;

	@Column(name = "statusFromSupervisor")
	private boolean statusFromSupervisor;

	// TODO servisā vai konstruktorā uzlikt submit pēc noklusējuma
	@Column(name = "accStatus")
	@Enumerated(EnumType.STRING)
	private AcceptanceStatus accStatus;

	@Column(name = "accDateTime")
	private LocalDateTime accDateTime;

	@ManyToOne
	@JoinColumn(name = "Ids")
	private Student student;

	@ManyToOne
	@JoinColumn(name = "Ida")
	private AcademicStaff supervisor;

	// TODO izveidot saiti, ja nepieciešams, ar konsultantu, vērtētāju utt/
	@ManyToMany
	@JoinTable(name = "thesis_reviewers", joinColumns = @JoinColumn(name = "Idt"), inverseJoinColumns = @JoinColumn(name = "Ida"))
	private Collection<AcademicStaff> reviewers = new ArrayList<>();

	public void addReviewer(AcademicStaff reviewer) {
		if (!reviewers.contains(reviewer)) {
			reviewers.add(reviewer);
		}
	}

	@OneToMany(mappedBy = "thesis")
	private Collection<Comment> comments;

	@OneToMany(mappedBy = "thesis")
	private Collection<OtherApplications> otherApplications;

	@OneToMany(mappedBy = "thesis")
	private Collection<ThesisApplications> thesisApplications;
	
	private boolean isDeleted;

	@OneToMany(mappedBy = "thesis")
	private Collection<Message> messages;

	public Thesis(String titleLv, String titleEn, String aim, String tasks, Student student, AcademicStaff supervisor) {

		this.titleLv = titleLv;
		this.titleEn = titleEn;
		this.aim = aim;
		this.tasks = tasks;
		this.student = student;
		this.supervisor = supervisor;
		this.submitDateTime = LocalDateTime.now();
		this.accDateTime = LocalDateTime.now();
		this.accStatus = AcceptanceStatus.SUBMITTED;
		this.otherApplications = new ArrayList<>();
		this.comments = new ArrayList<>();
		this.reviewers = new ArrayList<>();
		this.thesisApplications = new ArrayList<>();
		this.messages = new ArrayList<>();
		this.isDeleted = false;

	}

	public void addOtherApplicationToThesis(OtherApplications inputOtherApplication) {
		if (!otherApplications.contains(inputOtherApplication)) {
			otherApplications.add(inputOtherApplication);
		}
	}

	public void addCommentToThesis(Comment inputComment) {
		if (!comments.contains(inputComment)) {
			comments.add(inputComment);
		}
	}

	public void addThesisApplicationToThesis(ThesisApplications inputThesisApplications) {
		if (!thesisApplications.contains(inputThesisApplications)) {
			thesisApplications.add(inputThesisApplications);
		}
	}

	public void addMessageToThesis(Message inputMessage) {
		if (!messages.contains(inputMessage)) {
			messages.add(inputMessage);
		}
	}

}
