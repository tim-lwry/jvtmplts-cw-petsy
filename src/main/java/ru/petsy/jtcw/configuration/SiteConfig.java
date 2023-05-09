package ru.petsy.jtcw.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import ru.petsy.jtcw.entities.Adopter;
import ru.petsy.jtcw.repositories.UserRepository;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.List;

@EnableWebSecurity
@Configuration
public class SiteConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login", "/logout", "/registration", "/icons/**", "/images/**", "/", "/home", "/index").permitAll()
                .anyRequest().authenticated()

                .and().formLogin()
                .loginPage("/login").permitAll()
                .loginProcessingUrl("/perform-login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/index", true)
                .failureUrl("/login?error")

                .and()
                .logout()
                .deleteCookies("JSESSIONID")

                .and().userDetailsService(userDetailsService());
    }

    @Override
    protected UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        List<Adopter> customers = userRepository.findAll();
        for (Adopter customer : customers) {
            userDetailsManager.createUser(new User(customer.getUsername(), customer.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER"))));
        }
        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
