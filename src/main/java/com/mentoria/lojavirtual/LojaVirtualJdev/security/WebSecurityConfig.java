package com.mentoria.lojavirtual.LojaVirtualJdev.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
		
		http
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/public/**").permitAll() // permite acesso público a URLs que começam com /public/
            .anyRequest().authenticated() // exige autenticação para qualquer outra URL
            
        )
        .formLogin((form) -> form
            .loginPage("/login") // define a página de login personalizada
            .permitAll() // permite acesso público à página de login
        )
        .logout((logout) -> logout
            .permitAll() // permite logout público
        );
    return http.build();
		
	}
	
	/* Ignora algumas URL livre de autenticação */
	public void configure(WebSecurity web) throws Exception {
		((Object) web.ignoring()).
		.requestMatchers(HttpMethod.GET, "/requisicaojunoboleto/**", "/notificacaoapiv2","/pagamento/**","/resources/**",
		    		"/static/**","/templates/**","classpath:/static/**","classpath:/resources/**",
		    		"classpath:/templates/**","/webjars/**","/WEB-INF/classes/static/**","/recuperarSenha","/criaAcesso")
		   .antMatchers(HttpMethod.POST,"/requisicaojunoboleto/**", "/notificacaoapiv2",
				   "/pagamento/**","/resources/**","/static/**","/templates/**",
				   "classpath:/static/**","classpath:/resources/**","classpath:/templates/**",
				   "/webjars/**","/WEB-INF/classes/static/**","/recuperarSenha","/criaAcesso");
		/* Ingnorando URL no momento para nao autenticar */
	}
}
	
//.antMatchers(HttpMethod.GET, "/parking-spot/**").permitAll()

	
	
	
	

