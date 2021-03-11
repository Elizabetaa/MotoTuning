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
                // allow access to static resources to anyone
                        requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
                // allow access to index, user login and registration to anyone
                        antMatchers("/", "/users/signIn", "/users/register").permitAll().
                // protect all other pages
                        and().
                // configure login with HTML form
                        formLogin().
                // our login page will be served by the controller with mapping /users/login
                        loginPage("/users/signIn").
                // the name of the user name input field in OUR login form is username (optional)
                        usernameParameter("email").
                // the name of the user password input field in OUR login form is password (optional)
                        passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                // on login success redirect here
                        defaultSuccessUrl("/home").
//                // on login failure redirect here
                        failureForwardUrl("/users/signIn-error")
//                       failureUrl("/users/signIn-error")
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
