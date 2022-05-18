package com.everis.evereval.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    Environment env;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    //needed for creating the token with username and password
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //needed for creating the token with username and password
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable()
                .cors().configurationSource(corsConfigurationSource()).and()
                .authorizeRequests()
                .antMatchers(
                        "/authenticate",
                        "/initdb",
                        "/findStaff/**",
                        "/getCandidateByToken/**",
                        "/postQuizAnswers/**",
                        "/getCandidatewithoutquiz/**",
                        "/getAnswerNoScore/**",
                        "/getQuizByDoneAndEval/**",
                        "/getAllQUESTION/**",
                        "/findAllC/**",
                        "/findQbylevel&tech/**",
                        "/getCandidateByToken/**",
                        "/postQuizAnswers/**")
                .permitAll()
                .and().authorizeRequests()
                .antMatchers(
                        "/getAllCandidateWithEvaluatedQuiz/**",
                        "/sendEmail/**",
                        "/getAllMails/**",
                        "/addCandidate/**",
                        "/updateCandidate/**",
                        "/deleteCandidateById/**", "/getCandidateByMail/**",
                        "/addQuizToC/**",
                        "/getCandidateByQuizId/**")
                .hasAnyRole("ADMIN", "HR")
                .and().authorizeRequests()
                .antMatchers("/deleteQuestionById/**",
                        "/editQuestion/**",
                        "/findbycontent/**",
                        "/getAllQuizes/**",
                        "/addQuiz/**",
                        "/postEvaluatorReview/**",
                        "/addScoreToCandidate/**",
                        "/getCandidateByQuizId/**")
                .hasAnyRole("ADMIN", "EVALUATOR")
                .and().authorizeRequests()
                .antMatchers("/addStaff/**",
                        "/getStaffByMail/**",
                        "/getStaffByMail/**",
                        "/findAllStaff/**",
                        "/deleteStaffById/**",
                        "/editStaff/**")
                .hasRole("ADMIN").
                and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList(env.getProperty("cors.allowed.origins")));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowCredentials(false);
        //the below three lines will add the relevant CORS response headers
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
