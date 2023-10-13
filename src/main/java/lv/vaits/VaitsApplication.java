package lv.vaits;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lv.vaits.models.Comment;
import lv.vaits.models.Course;
import lv.vaits.models.ApplicationType;
import lv.vaits.models.CalendarSchedule;
import lv.vaits.models.Faculty;
import lv.vaits.models.LevelOfStudy;
import lv.vaits.models.Message;
import lv.vaits.models.Thesis;
import lv.vaits.models.ThesisApplications;
import lv.vaits.models.OtherApplications;
import lv.vaits.models.StudentStudyProgram;
import lv.vaits.models.users.AcademicStaff;
import lv.vaits.models.users.Degree;
import lv.vaits.models.users.Student;
import lv.vaits.models.users.User;
import lv.vaits.models.StudyProgram;
import lv.vaits.repos.ICalendarSchedule;
import lv.vaits.repos.ICommentRepo;
import lv.vaits.repos.ICourseRepo;
import lv.vaits.repos.IMessageRepo;
import lv.vaits.repos.IOtherApplications;
import lv.vaits.repos.IStudentStudyProgramRepo;
import lv.vaits.repos.IStudyProgramRepo;
import lv.vaits.repos.IThesisApplications;
import lv.vaits.repos.IThesisRepo;
import lv.vaits.repos.users.IAcademicStaffRepo;
import lv.vaits.repos.users.IPersonRepo;
import lv.vaits.repos.users.IStudentRepo;
import lv.vaits.repos.users.IUserRepo;

@SpringBootApplication
public class VaitsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaitsApplication.class, args);
	}

	//@Bean
	public CommandLineRunner testModelLayer(IUserRepo userRepo, IPersonRepo personRepo, IStudentRepo studentRepo,
			IAcademicStaffRepo staffRepo, ICourseRepo courseRepo, IThesisRepo thesisRepo, ICommentRepo commentRepo,
			IStudyProgramRepo studyRepo, ICalendarSchedule calendarRepo, IOtherApplications otherApplicationRepo,
			IStudentStudyProgramRepo sspRepo, IThesisApplications thesisApplicationsRepo, IMessageRepo messageRepo) {
		return new CommandLineRunner() {


			@Override
			public void run(String... args) throws Exception {
				User us1 = new User("123", "s22zvejlaur@venta.lv"); // pasniedzejs
				User us2 = new User("123", "s23zvejlaur@venta.lv"); // pasniedzejs
				User us3 = new User("123", "s24zvejlaur@venta.lv"); // students
				User us4 = new User("123", "s25zvejlaur@venta.lv"); // students
				User us5 = new User("555", "venta@venta.lv"); // students
				userRepo.save(us1);
				userRepo.save(us2);
				userRepo.save(us3);
				userRepo.save(us4);
				userRepo.save(us5);

				Course c1 = new Course("Javaa", 4);
				Course c2 = new Course("Datastr", 4);
				courseRepo.save(c1);
				courseRepo.save(c2);

				AcademicStaff ac1 = new AcademicStaff("Karina", "Skirmante", "270792-11111", us1, Degree.MG);
				AcademicStaff ac2 = new AcademicStaff("Karlis", "Immers", "270792-11112", us2, Degree.MG);

				staffRepo.save(ac1);
				staffRepo.save(ac2);

				Student s1 = new Student("Janis", "Berzins", "270792-11113", us3, "12345678", false);
				Student s2 = new Student("Baiba", "Kalnina", "270792-11114", us4, "12245679", true);

				s2.addDebtCourse(c1);
				s1.addDebtCourse(c2);
				studentRepo.save(s1);
				studentRepo.save(s2);
				c1.addStudent(s2);
				c2.addStudent(s2);
				courseRepo.save(c1);
				courseRepo.save(c2);

				Thesis th1 = new Thesis("Sistēmas izstrāde", "Development of System", "Development", "1...2.3..4", s1,
						ac1);
				Thesis th2 = new Thesis("Programmas izstrāde", "Development of Program", "Non Development",
						"1...2.3..5", s1, ac2);

				th1.addReviewer(ac2);
				th2.addReviewer(ac1);
				thesisRepo.save(th1);
				thesisRepo.save(th2);
				ac1.addThesisForReviews(th1);
				ac2.addThesisForReviews(th2);
				personRepo.save(ac1);
				personRepo.save(ac2);

				Comment com1 = new Comment("Neprecīzs nosaukums", ac2, th1);
				Comment com2 = new Comment("Nepareizs mērķis", ac1, th2);

				commentRepo.save(com1);
				commentRepo.save(com2);

				StudyProgram studyProgram1 = new StudyProgram("Programmēšanas speciālists", Faculty.ITF,
						LevelOfStudy.FIRST_LEVEL);
				StudyProgram studyProgram2 = new StudyProgram("Datorzinātnes", Faculty.ITF, LevelOfStudy.BACHELOR);

				studyRepo.save(studyProgram1);
				studyRepo.save(studyProgram2);

				CalendarSchedule calendarSchedule1 = new CalendarSchedule(2023, "Aktivitāte", LocalDate.of(2023, 7, 31),
						studyProgram1);
				CalendarSchedule calendarSchedule2 = new CalendarSchedule(2023, "Aktivitātes",
						LocalDate.of(2023, 8, 10), studyProgram2);

				calendarRepo.save(calendarSchedule1);
				calendarRepo.save(calendarSchedule2);

				studyProgram1.addCalendarSchedule(calendarSchedule1);
				studyProgram2.addCalendarSchedule(calendarSchedule2);

				studyRepo.save(studyProgram1);
				studyRepo.save(studyProgram2);

				OtherApplications changeTopicApplication = new OtherApplications(ApplicationType.CHANGE_THESIS_TOPIC,
						"Application for changing the topic of thesis.", th1);
				OtherApplications submissionExtensionApplication = new OtherApplications(
						ApplicationType.SUBMISSION_EXTENSION, "Application for requesting extension of submission.",
						th2);

				otherApplicationRepo.save(changeTopicApplication);
				otherApplicationRepo.save(submissionExtensionApplication);

				th1.addOtherApplicationToThesis(changeTopicApplication);
				th2.addOtherApplicationToThesis(submissionExtensionApplication);

				thesisRepo.save(th1);
				thesisRepo.save(th2);
				
				Message m1 = new Message("Karina Šķirmante", "Lūdzu atsūtiet darba mērķi", th1);
				Message m2 = new Message("Janis Berzins", "...", th2);
				messageRepo.save(m1);
				messageRepo.save(m2);


				StudentStudyProgram listItem1 = new StudentStudyProgram(s1, studyProgram1, LocalDate.of(2021, 1, 31),
						LocalDate.of(2022, 8, 31));
				StudentStudyProgram listItem2 = new StudentStudyProgram(s2, studyProgram2, LocalDate.of(2022, 1, 31),
						LocalDate.of(2023, 8, 31));
				StudentStudyProgram listItem3 = new StudentStudyProgram(s1, studyProgram2, LocalDate.of(2020, 1, 8),
						LocalDate.of(2020, 11, 30));

				sspRepo.save(listItem1);
				sspRepo.save(listItem2);
				sspRepo.save(listItem3);

				ThesisApplications application1 = new ThesisApplications("Sistēmasizstrāde", "Developmentofsystem",
						"Development................................", "A1...2.3..4.......................",
						LocalDateTime.now(), th1);
				ThesisApplications application2 = new ThesisApplications("Sistēmasizstrāde", "Developmentofsystem",
						"Development...................................", "A1...2.3..4............................",
						LocalDateTime.now(), th1);

				thesisApplicationsRepo.save(application1);
				thesisApplicationsRepo.save(application2);

			}
		};
	}
}