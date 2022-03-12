package pl.infoshare.workandfun.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

    private static final String HOME_PAGE = "/";
    private static final String ANNOUNCEMENT_DETAILS = "/announcement/details/{id}";
    private static final String ALL_ANNOUNCEMENT = "/announcement/all";
    private static final String ADD_NEW_ANNOUNCEMENT = "/announcement/add-new";
    private static final String EDIT_ANNOUNCEMENT = "/announcement/edit";
    private static final String SEARCH_ANNOUNCEMENT = "/announcement/search";


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**", "/img/**", "/js/**",
                        HOME_PAGE,
                        ANNOUNCEMENT_DETAILS,
                        ALL_ANNOUNCEMENT,
                        SEARCH_ANNOUNCEMENT).permitAll()
                .antMatchers(ADD_NEW_ANNOUNCEMENT, EDIT_ANNOUNCEMENT).hasRole("ADMIN")
                .antMatchers(ADD_NEW_ANNOUNCEMENT, EDIT_ANNOUNCEMENT).hasRole("USER")
                .anyRequest().authenticated();
        //todo waiting for login and logout endpoints
    }
}
