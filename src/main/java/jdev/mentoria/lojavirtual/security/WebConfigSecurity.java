package jdev.mentoria.lojavirtual.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jdev.mentoria.lojavirtual.service.ImplementaçãoUserDetailsService;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ImplementaçãoUserDetailsService implementaçãoUserDetailsService;
	
	/*Irá consultar o user no banco com Spring Security*/
	@Override
	protected void configure(AuthenticationManagerBuilder auth)throws Exception {
		
		auth.userDetailsService(implementaçãoUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void configure(HttpSecurity  web) throws Exception {
		getHttp()
		.httpBasic()
        .and()
        .authorizeRequests()
		.antMatchers(HttpMethod.GET, "/salvarAcesso", "/deleteAcesso").permitAll()
		.antMatchers(HttpMethod.POST, "/salvarAcesso", "deleteAcesso").permitAll()
		.anyRequest().authenticated()
        .and()
        .csrf().disable();
		/*Ignorando URL no momento para não autenticar*/
	}
	
	

}
