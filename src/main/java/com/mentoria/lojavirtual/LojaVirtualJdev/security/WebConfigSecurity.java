package com.mentoria.lojavirtual.LojaVirtualJdev.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.mentoria.lojavirtual.LojaVirtualJdev.service.ImplementacaoUserDetailsService;

import jakarta.servlet.http.HttpSessionListener;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebConfigSecurity extends WebSecurityConfigurerAdapter implements HttpSessionListener{

	@Autowired
	private ImplementacaoUserDetailsService implementacaoUserDetailsService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		.disable().authorizeRequests().antMatchers("/").permitAll()
		.antMatchers("/index","/pagamento/**","/resources/**","/static/**","/templates/**","classpath:/static/**","classpath:/resources/**","classpath:/templates/**").permitAll()
		.antMatchers(HttpMethod.POST, "/requisicaojunoboleto/**", "/notificacaoapiv2","/pagamento/**","/resources/**","/static/**","/templates/**","classpath:/static/**","classpath:/resources/**","classpath:/templates/**","/recuperarSenha","/criaAcesso").permitAll()
		.antMatchers(HttpMethod.GET, "/requisicaojunoboleto/**", "/notificacaoapiv2","/pagamento/**","/resources/**","/static/**","/templates/**","classpath:/static/**","classpath:/resources/**","classpath:/templates/**","/recuperarSenha","/criaAcesso").permitAll()
		.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
		
		/* redireciona ou da um retorno para index quando desloga*/
		.anyRequest().authenticated().and().logout().logoutSuccessUrl("/index")
		
		/* Mapeia URL de logout e invalida o usuário */
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				
				/* Filtra requisições de login para autenticação */
				.and().addFilterAfter(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
				
				/* Filtra demais requisições para verificar a presença do TOKEN JWT no HEADER HTTP */
				.addFilterBefore(new JwtApiAutenticacaoFilter(), UsernamePasswordAuthenticationFilter.class);		

		
	}
	
	
	/*Irá consultar o user no banco com Spring Security*/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(implementacaoUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
		
	}
	


	/*Ignora alguas URL livre de autenticação*/
	@Override
	public void configure(WebSecurity web) throws Exception {
		//web.ignoring().antMatchers(HttpMethod.GET, "/salvarAcesso", "/deleteAcesso")
		//.antMatchers(HttpMethod.POST, "/salvarAcesso", "/deleteAcesso");
		/*Ingnorando URL no momento para nao autenticar*/
	}

	

	

	
}
	
	

