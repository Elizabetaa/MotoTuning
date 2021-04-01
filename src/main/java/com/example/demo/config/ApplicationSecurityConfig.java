package com.example.demo.config;

import com.example.demo.service.impl.MotoTuningUserService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final MotoTuningUserService motoTuningUserService;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, MotoTuningUserService motoTuningUserService) {
        this.passwordEncoder = passwordEncoder;
        this.motoTuningUserService = motoTuningUserService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests().
                        antMatchers("/", "/users/signIn", "/users/register", "/blog/all", "/blog/categories/**", "/blog/details/{id}",
                        "/tuning/**", "/css/style.css", "/images/**", "/home/**").permitAll().
                         antMatchers("/admin/**").hasRole("ADMIN").
                        antMatchers("/**").authenticated().

                and().
                        formLogin().
                        loginPage("/users/signIn").
                        usernameParameter("email").
                        passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                        defaultSuccessUrl("/home").
        failureForwardUrl("/users/signIn-error")
                .and()
                .logout()
                .logoutUrl("/signOut")
                .logoutSuccessUrl("/users/signIn")
                .deleteCookies("JSESSIONID");
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                userDetailsService(motoTuningUserService).
                passwordEncoder(passwordEncoder);
    }
}
