package jdev.mentoria.lojavirtual.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {
	
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
