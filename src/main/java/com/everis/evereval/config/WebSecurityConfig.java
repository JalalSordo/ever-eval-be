package com.everis.evereval.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService myUserDetailsService;

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// We don't need CSRF for this example
		httpSecurity.csrf().disable()
				// dont authenticate this particular request
				.authorizeRequests()
				.antMatchers("/authenticate", "/initdb", "/findStaff/**", "/getCandidateByToken/**",
						"/postQuizAnswers/**", "/getCandidatewithoutquiz/**", "/getAnswerNoScore/**",
						"/getQuizByDoneAndEval/**", "/getAllQUESTION/**", "/findAllC/**", "/findQbylevel&tech/**",
						"/getCandidateByToken/**", "/postQuizAnswers/**")
				.permitAll().and().authorizeRequests()
				.antMatchers("/getAllCandidateWithEvaluatedQuiz/**", "/sendEmail/**", "/getAllMails/**",
						"/addCandidate/**", "/updateCandidate/**", "/deleteCandidateById/**", "/getCandidateByMail/**",
						"/addQuizToC/**", "/getCandidateByQuizId/**")
				.hasAnyRole("ADMIN", "HR").and().

				authorizeRequests()
				.antMatchers("/deleteQuestionById/**", "/editQuestion/**", "/findbycontent/**", "/getAllQuizes/**",
						"/addQuiz/**", "/postEvaluatorReview/**", "/addScoreToCandidate/**", "/getCandidateByQuizId/**")
				.hasAnyRole("ADMIN", "EVALUATOR").and().authorizeRequests()
				.antMatchers("/addStaff/**", "/getStaffByMail/**", "/getStaffByMail/**", "/findAllStaff/**",
						"/deleteStaffById/**", "/editStaff/**")
				.hasRole("ADMIN").and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
}
