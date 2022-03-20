package pl.infoshare.workandfun.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/img/**", "/js/**", "/sign-in", "/api/sign-in").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/sign-in")
                .failureUrl("/sign-in?error")
                .loginProcessingUrl("/api/sign-in")
                .defaultSuccessUrl("/", true)
                .and()
                .logout()
                .logoutUrl("/api/sign-out")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);
    }
}