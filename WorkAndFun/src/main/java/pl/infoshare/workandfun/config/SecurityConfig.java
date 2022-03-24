package pl.infoshare.workandfun.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Order(1)
    @Configuration
    public static class MSConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/", "/css/**", "/img/**", "/js/**", "/sign-in", "/api/sign-in", "/sign-up").permitAll()
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

    @Order(2)
    @Configuration
    public static class PGConfig extends WebSecurityConfigurerAdapter {
        private static final String HAS_WAF_SERVICE_IPS = "hasIpAddress('localhost:8081')";
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, "/v1/**").access(HAS_WAF_SERVICE_IPS);
        }
    }
}