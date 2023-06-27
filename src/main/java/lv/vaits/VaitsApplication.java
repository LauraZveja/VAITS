package lv.vaits;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lv.vaits.models.Comment;
import lv.vaits.models.Course;
import lv.vaits.models.Thesis;
import lv.vaits.models.users.AcademicStaff;
import lv.vaits.models.users.Degree;
import lv.vaits.models.users.Student;
import lv.vaits.models.users.User;
import lv.vaits.repos.ICommentRepo;
import lv.vaits.repos.ICourseRepo;
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

	@Bean
	public CommandLineRunner testModelLayer(IUserRepo userRepo, IPersonRepo personRepo, IStudentRepo studentRepo,
			IAcademicStaffRepo staffRepo, ICourseRepo courseRepo, IThesisRepo thesisRepo, ICommentRepo commentRepo) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				User us1 = new User("123", "s22zvejlaur@venta.lv"); // pasniedzejs
				User us2 = new User("123", "s23zvejlaur@venta.lv"); // pasniedzejs
				User us3 = new User("123", "s24zvejlaur@venta.lv"); // students
				User us4 = new User("123", "s25zvejlaur@venta.lv"); // students
				User us5 = new User("123", "s25zvejlaur@venta.lv"); // students
				userRepo.save(us1);
				userRepo.save(us2);
				userRepo.save(us3);
				userRepo.save(us4);
				userRepo.save(us5);

				Course c1 = new Course("Javaa", 4);
				Course c2 = new Course("Datastr", 4);
				courseRepo.save(c1);
				courseRepo.save(c2);

				AcademicStaff ac1 = new AcademicStaff("Karina", "Skirmante", "121212-121212", us1, Degree.MG);
				AcademicStaff ac2 = new AcademicStaff("Karlis", "Immers", "131212-131212", us2, Degree.MG);

				staffRepo.save(ac1);
				staffRepo.save(ac2);

				Student s1 = new Student("Janis", "Berzins", "212121-212121", us3, "12345678", false);
				Student s2 = new Student("Baiba", "Kalnina", "222121-222121", us4, "12245678", true);

				s2.addDebtCourse(c1);
				s2.addDebtCourse(c2);
				studentRepo.save(s1);
				studentRepo.save(s2);
				c1.addStudent(s2);
				c2.addStudent(s2);
				courseRepo.save(c1);
				courseRepo.save(c2);

				Thesis th1 = new Thesis("Sistēmas izstrāde", "Development of System", "Development", "1...2.3..4", s1,
						ac1);
				Thesis th2 = new Thesis("Programmas izstrāde", "Development of Program", "Development", "1...2.3..4",
						s2, ac2);

				th1.addReviewer(ac1);
				th2.addReviewer(ac2);
				thesisRepo.save(th1);
				thesisRepo.save(th2);
				ac1.addThesisForReviews(th2);
				ac2.addThesisForReviews(th1);
				personRepo.save(ac1);
				personRepo.save(ac2);

				Comment com1 = new Comment("Neprecīzs nosaukums", ac2, th1);
				Comment com2 = new Comment("Nepareizs mērķis", ac1, th2);

				commentRepo.save(com1);
				commentRepo.save(com2);

			}
		};
	}
}
