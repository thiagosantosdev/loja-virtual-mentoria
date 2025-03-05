package com.mentoria.lojavirtual.LojaVirtualJdev.security;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.Usuario;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	// Configurando o gerenciador de autenticação
		protected JWTLoginFilter(String url, AuthenticationManager authenticationManager) {
			
			// Obriga a autenticar a url
			super(new AntPathRequestMatcher(url));
			
			// Gerenciador de autenticação
			setAuthenticationManager(authenticationManager);
		}

		/* Retorna o usuário ao processar a autenticação */
		@Override
		public Authentication attemptAuthentication(javax.servlet.http.HttpServletRequest request, 
				javax.servlet.http.HttpServletResponse response)
				throws AuthenticationException, IOException, javax.servlet.ServletException {

			/* Está pegando o token para validar */
			Usuario user = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
			
			/* Retorna o usuário login, senha e acesso */		
			return getAuthenticationManager()
					.authenticate(new UsernamePasswordAuthenticationToken(
						user.getLogin(), user.getSenha()));
		}
		/*	
		public Authentication attemptAuthentication(javax.servlet.http.HttpServletRequest request,
				javax.servlet.http.HttpServletResponse response)
				throws AuthenticationException, IOException, javax.servlet.ServletException {
			// TODO Auto-generated method stub
			return null;
		}
		
		*/
		
		protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
				Authentication authResult){
			try {
				new JWTTokenAutenticacaoService().addAuthentication(response, authResult.getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException failed) throws IOException {
			if (failed instanceof BadCredentialsException) {
				response.getWriter().write("Usuário e/ou senha não encontrado(s)!");
			} else {
				response.getWriter().write("Falha ao logar: " + failed.getMessage());
			}
		}

		@Override
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
				throws IOException, ServletException {
			// TODO Auto-generated method stub
			
		}

	

		
	

	


	

		
	
}
