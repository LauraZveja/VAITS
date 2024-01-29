package lv.vaits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Import;

import lv.vaits.user.confs.SecurityConfig;


@SpringBootApplication(scanBasePackages = { "lv.vaits.user", "lv.vaits", "lv.vaits.main" })
@Import({ SecurityConfig.class })
public class VaitsWeb {

	public static void main(String[] args) {
		SpringApplication.run(VaitsWeb.class, args);
	}

}