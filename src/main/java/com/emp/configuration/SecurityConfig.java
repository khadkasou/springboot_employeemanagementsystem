package com.emp.configuration;

import com.emp.user.service.CustomUserDetailsService;
import com.emp.utils.JwtRequestFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        prePostEnabled = true

)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private JwtRequestFilter requestFilter;
    private CustomUserDetailsService customUserDetailsService;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//      auth.userDetailsService(customUserDetailsService);
//      auth.authenticationProvider(empAuthProvider).authenticationProvider(userAuthProvider);
    }

    /**
     * Authenticting username and password
     */
    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().disable()
                .authorizeRequests()
                .and()
                .authorizeRequests()
                .antMatchers("/login**","/v1/**","/v2/**").permitAll()
                .and()
                .formLogin().loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/login")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .and()
                .rememberMe()
                .and()
                .csrf().disable();


        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/")
                .hasAuthority("ADMIN")
                .anyRequest().authenticated();

        http.addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class);

    }
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }


}
