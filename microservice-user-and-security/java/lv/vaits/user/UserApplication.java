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


}