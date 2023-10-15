package lv.vaits.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "other_applications_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class OtherApplications {
	
	@Column(name = "Idoa")
	@Id
	@Setter(value = AccessLevel.NONE)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idoa;
	
	@Column(name = "ApplicationType")
	@Enumerated(EnumType.STRING)
	@NotNull(message = "APPLICATION REQUIRES A TYPE!")
	private ApplicationType applicationType;
	
	@Column(name = "ApplicationText")
	@NotNull
	@Pattern(regexp = "^[A-ZĀČĒĢĪĶĻŅŠŪŽ].*", message = "APPLICATION TEXT MUST START WITH CAPITAL LETTER.")
	private String activity;
	
	@ManyToOne
	@JoinColumn(name = "Idt")
	private Thesis thesis;

	public OtherApplications(@NotNull(message = "APPLICATION REQUIRES A TYPE!") ApplicationType applicationType,
			@NotNull @Pattern(regexp = "^[A-ZĀČĒĢĪĶĻŅŠŪŽ].*", message = "APPLICATION TEXT MUST START WITH CAPITAL LETTER.") String activity,
			Thesis thesis) {
		this.applicationType = applicationType;
		this.activity = activity;
		this.thesis = thesis;
	}
	
	

}
