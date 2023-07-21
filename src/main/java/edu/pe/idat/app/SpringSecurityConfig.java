package edu.pe.idat.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.pe.idat.app.models.service.JpaUserDetailsService;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/css/**", "/js/**","/logo/**", "/").permitAll()
				.antMatchers("/venta/detalle/**").hasAnyRole("MODERATOR")
				.antMatchers("/venta/formulario/**").hasAnyRole("MODERATOR")
				.antMatchers("/venta/eliminar/**").hasAnyRole("ADMIN")
				.antMatchers("/empleado/formulario/**").hasAnyRole("ADMIN")
				.antMatchers("/empleado/editar/**").hasAnyRole("ADMIN")
				.antMatchers("/empleado/eliminar/**").hasAnyRole("ADMIN")
				.antMatchers("/empleado/detalle/**").hasAnyRole("MODERATOR")
				.antMatchers("/cliente/formulario/**").hasAnyRole("MODERATOR")
				.antMatchers("/cliente/editar/**").hasAnyRole("ADMIN")
				.antMatchers("/cliente/eliminar/**").hasAnyRole("ADMIN")
				.antMatchers("/cliente/detalle/**").hasAnyRole("MODERATOR")
				.antMatchers("/producto/formulario/**").hasAnyRole("ADMIN")
				.antMatchers("/producto/editar/**").hasAnyRole("ADMIN")
				.antMatchers("/producto/eliminar/**").hasAnyRole("ADMIN")
				.antMatchers("/producto/detalle/**").hasAnyRole("MODERATOR")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
}
