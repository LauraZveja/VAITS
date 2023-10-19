package lv.vaits.user.confs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.http.HttpMethod;

import jakarta.servlet.DispatcherType;
import lv.vaits.user.services.users.impl.MyUserDetailsManagerImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


	@Bean
	public MyUserDetailsManagerImpl userDetailsManager() {
		MyUserDetailsManagerImpl manager = new MyUserDetailsManagerImpl();
		return manager;
	}

	@Bean
	PasswordEncoder passwordEncoderSimple2() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {

		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);

		authenticationManagerBuilder.userDetailsService(userDetailsManager()).passwordEncoder(passwordEncoderSimple2());
		return authenticationManagerBuilder.build();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests()
		.requestMatchers("/academicStaff/create").hasAnyAuthority("ADMIN")
		.requestMatchers("/academicStaff/showAll").permitAll()
		.requestMatchers("/academicStaff/update/**").hasAnyAuthority("ADMIN")
		.requestMatchers("/academicStaff/error").permitAll()
		.requestMatchers("/academicStaff/delete/**").hasAnyAuthority("ADMIN")
		.requestMatchers("/student/addNew").hasAnyAuthority("ADMIN")
		.requestMatchers("/student/showAll").permitAll()
		.requestMatchers("/student/showAll/**").hasAnyAuthority("ADMIN")
		.requestMatchers("/student/update/**").hasAnyAuthority("ADMIN")
		.requestMatchers("/student/error").permitAll()
		.requestMatchers("/student/remove/**").hasAnyAuthority("ADMIN")
		.requestMatchers("/student/showAllDebtCourses/**").hasAnyAuthority("ADMIN")
		.requestMatchers("/student/showAllDebtCourses/add/**").hasAnyAuthority("ADMIN")
		.requestMatchers("/student/showAllDebtCourses/remove/**").hasAnyAuthority("ADMIN")
		.requestMatchers("/student/thesis/**").permitAll()
		.requestMatchers("/student/thesis/addNew/**").hasAnyAuthority("ADMIN")
		.requestMatchers("/thesis/addNew").hasAnyAuthority("ADMIN")
		.requestMatchers("/thesis/showAll").permitAll()
		.requestMatchers("/thesis/showAll/**").permitAll()
		.requestMatchers("/thesis/update/**").hasAnyAuthority("ADMIN")
		.requestMatchers("/thesis/error").permitAll()
		.requestMatchers("/thesis/remove/**").hasAnyAuthority("ADMIN")
		.requestMatchers("/thesis/changeSupervisor/**").hasAnyAuthority("ADMIN")
		.requestMatchers("/thesis/addReviewerByThesisId/**").hasAnyAuthority("ADMIN")
		.requestMatchers("/thesis/updateStatus/**").hasAnyAuthority("ADMIN")
		.requestMatchers("/thesis/export").permitAll()
		.requestMatchers("/course/addNew").hasAnyAuthority("ADMIN")
		.requestMatchers("/course/showAll").permitAll()
		.requestMatchers("/course/showAll/**").permitAll()
		.requestMatchers("/course/update/**").hasAnyAuthority("ADMIN")
		.requestMatchers("/course/remove/**").hasAnyAuthority("ADMIN")
		.requestMatchers("/course/showAllDebtors/**").hasAnyAuthority("ADMIN")
		.requestMatchers("/course/showAllDebtors/add/**").hasAnyAuthority("ADMIN")
		.requestMatchers("/course/error").permitAll()
		.requestMatchers("/course/showAllDebtors/remove/**").hasAnyAuthority("ADMIN")
		.requestMatchers("/comment/addNew").permitAll()
		.requestMatchers("/comment/showAll").permitAll()
		.requestMatchers("/comment/update/**").hasAnyAuthority("ADMIN")
		.requestMatchers("/comment/error").permitAll()
		.requestMatchers("/testing").permitAll()
		.requestMatchers("/comment/showAll/**").hasAnyAuthority("ADMIN")
		.requestMatchers("/comment/remove/**").hasAnyAuthority("ADMIN")
		.requestMatchers("/thesis/showAllComments/**").hasAnyAuthority("ADMIN")
		.requestMatchers("/notifications/showAll").hasAnyAuthority("ADMIN")
		.requestMatchers("/api/v1/kafka/publish").hasAnyAuthority("ADMIN")
		.requestMatchers("/student/import").hasAnyAuthority("ADMIN")
		.requestMatchers("/student/export").hasAnyAuthority("ADMIN")
		.requestMatchers("/student/import/**").hasAnyAuthority("ADMIN")
		.requestMatchers("/student/export/**").hasAnyAuthority("ADMIN")
		.requestMatchers(HttpMethod.GET,"/thesis/export/**").hasAnyAuthority("ADMIN")
		.requestMatchers(HttpMethod.POST,"/thesis/export/**").hasAnyAuthority("ADMIN")
		.requestMatchers("/thesis/removeReviewerByThesisId/**").hasAnyAuthority("ADMIN")
		.requestMatchers("/comment/dto/**").permitAll()
		.requestMatchers(HttpMethod.POST,"/comment/dto/**").hasAnyAuthority("ADMIN")
		.requestMatchers(HttpMethod.GET,"/comment/dto/**").hasAnyAuthority("ADMIN")
		.requestMatchers(HttpMethod.GET,"/otherApps/**").permitAll()
		.requestMatchers(HttpMethod.POST,"/otherApps/**").permitAll()
		.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll().and().formLogin()
		.permitAll().and().logout().permitAll().and().exceptionHandling().accessDeniedPage("/my-access-denied").and().csrf().disable();

		return http.build();
	}

}
