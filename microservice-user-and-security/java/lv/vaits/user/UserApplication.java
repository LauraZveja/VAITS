package lv.vaits.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import lv.vaits.user.confs.SecurityConfig;
import lv.vaits.user.models.users.Authorities;
import lv.vaits.user.models.users.User;
import lv.vaits.user.repos.users.IAuthorityRepository;
import lv.vaits.user.repos.users.IUserRepository;

@SpringBootApplication(scanBasePackages = { "lv.vaits.user", "lv.vaits", "lv.vaits.main" })
@Import({ SecurityConfig.class })
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoderSimple() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	//@Bean
	public CommandLineRunner testDB(final IUserRepo userRepo, final IAuthorityRepo authorityRepo) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				User us1 = new User(passwordEncoderSimple().encode("123"), "s22zvejlaur@venta.lv", "laura");
				User us2 = new User(passwordEncoderSimple().encode("123"), "s22zvejlaur@venta.lv", "laura1");
				User us3 = new User(passwordEncoderSimple().encode("123"), "s22zvejlaur@venta.lv", "laura2");
				User us4 = new User(passwordEncoderSimple().encode("123"), "s22zvejlaur@venta.lv", "laura3");
				User us5 = new User(passwordEncoderSimple().encode("123"), "s22zvejlaur@venta.lv", "laura4");

				userRepo.save(us1);
				userRepo.save(us2);
				userRepo.save(us3);
				userRepo.save(us4);
				userRepo.save(us5);

				Authorities auth1 = new Authorities("ADMIN");
				Authorities auth2 = new Authorities("USER");

				auth1.addUser(us1);
				auth2.addUser(us1);
				auth2.addUser(us2);
				auth2.addUser(us3);
				auth2.addUser(us4);
				auth2.addUser(us5);

				authorityRepo.save(auth1);
				authorityRepo.save(auth2);

				us1.addAuthority(auth1);
				us1.addAuthority(auth2);
				us2.addAuthority(auth2);
				us3.addAuthority(auth2);
				us4.addAuthority(auth2);
				us5.addAuthority(auth2);

				userRepo.save(us1);
				userRepo.save(us2);
				userRepo.save(us3);
				userRepo.save(us4);
				userRepo.save(us5);

			}
		};
	}
}