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
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserPrincipalDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/activity/**").hasAuthority("View_Activity")
                .antMatchers("/activity/addActivity/**").hasAuthority("Add_Activity")
                .antMatchers("/activity/saveActivity/**").hasAuthority("Add_Activity")
                .antMatchers("/activity/edit/**").hasAuthority("Edit_Activity")
                .antMatchers("/activity/updateActivity/**").hasAuthority("Edit_Activity")
                .antMatchers("/activity/deleteActivity/**").hasAuthority("Delete_Activity")
                .antMatchers("/activity/exportExcel/**").hasAuthority("View_Activity")
                .antMatchers("/admin/**").hasAuthority("View_Admin")
                .antMatchers("/admin/user/edit/**").hasAuthority("Edit_Admin")
                .antMatchers("/admin/user/updateUser/**").hasAuthority("Edit_Admin")
                .antMatchers("/admin/user/deleteUser/**").hasAuthority("Delete_Admin")
                .antMatchers("/admin/user/authority/**").hasAuthority("Delete_Admin")
                .antMatchers("/company/**").hasAuthority("View_Company")
                .antMatchers("/company/addCompany/**").hasAuthority("Add_Company")
                .antMatchers("/company/saveCompany/**").hasAuthority("Add_Company")
                .antMatchers("/company/editCompany/**").hasAuthority("Edit_Company")
                .antMatchers("/company/updateCompany/**").hasAuthority("Edit_Company")
                .antMatchers("/company/deleteCompany/**").hasAuthority("Delete_Company")
                .antMatchers("/company/company_info/**").hasAuthority("Delete_Company")
                .antMatchers("/contact/**").hasAuthority("View_Contact")
                .antMatchers("/contact/addContact/**").hasAuthority("Add_Contact")
                .antMatchers("/contact/saveContact/**").hasAuthority("Add_Contact")
                .antMatchers("/contact/editContact/**").hasAuthority("Edit_Contact")
                .antMatchers("/contact/updateContact/**").hasAuthority("Edit_Contact")
                .antMatchers("/contact/exportExcel/**").hasAuthority("View_Contact")
                .antMatchers("/profile/**").authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/signin")
                .failureHandler(authenticationFailureHandler())
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
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new AuthenticationFailure();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
