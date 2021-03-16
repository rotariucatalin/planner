package com.example.Planner.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserPrincipalDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/activity/addActivity/**").hasAuthority("Add_Activity")
                .antMatchers("/activity/edit/**").hasAuthority("Edit_Activity")
                .antMatchers("/activity/updateActivity/**").hasAuthority("Edit_Activity")
                .antMatchers("/activity/deleteActivity/**").hasAuthority("Delete_Activity")
                .antMatchers("/activity/**").hasAuthority("View_Activity")
                .antMatchers("/admin/**").hasAuthority("View_Admin")
                .antMatchers("/admin/user/edit/**").hasAuthority("Edit_Admin")
                .antMatchers("/admin/user/updateUser/**").hasAuthority("Edit_Admin")
                .antMatchers("/admin/user/deleteUser/**").hasAuthority("Delete_Admin")
                .antMatchers("/company/**").hasAuthority("View_Company")
                .antMatchers("/contact/**").hasAuthority("View_Contact")
                .antMatchers("/profile/**").authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/signin")
                .loginPage("/login").permitAll()
                .usernameParameter("txtUsername")
                .passwordParameter("txtPassword")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                .and()
                .rememberMe().tokenValiditySeconds(2592000).key("mySecret!").rememberMeParameter("checkRememberMe").userDetailsService(userDetailsService);
    }

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);

        return daoAuthenticationProvider;

    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
