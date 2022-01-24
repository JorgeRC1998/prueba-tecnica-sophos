package com.sophos.backendcanvas.Config;
 
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
 
/**
 * Clase para especificar cuales request pueden continuar a la ejecución de los servicios como tal
 * 
 * @author JorgeRojas
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
 
   // @Override
   // public void configure(final HttpSecurity http) throws Exception {
   //    http.csrf().disable();
   //    http.authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated();
   // }

   @Override
   protected void configure(HttpSecurity httpSecurity) throws Exception {
       httpSecurity.authorizeRequests().antMatchers("/").permitAll().and()
               .authorizeRequests().antMatchers("/console/**",
                                                "/v2/api-docs/**",
                                                "/v3/api-docs/**",
                                                "/swagger-ui/**",
                                                "/swagger-resources/**").permitAll();
       httpSecurity.csrf().disable();
       httpSecurity.headers().frameOptions().disable();
   }
 
}