package com.mentoria.lojavirtual.LojaVirtualJdev.security;

import java.io.IOException;
import java.util.Date;

import javax.mail.MethodNotSupportedException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.mentoria.lojavirtual.LojaVirtualJdev.ApplicationContextLoad;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.Usuario;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.UsuarioRepository;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

/*Criar a autenticação e retornar também a autenticação JWT*/

@Service
public class JWTTokenAutenticacaoService {

	/* Token de validade de 2 dias */
	private static final long EXPIRATION_TIME = 172800000; /*172800000*/

	/* Chave de senha para juntar com o JWT */
	private static final String SECRET = "SenhaExtremamenteSecreta";

	private static final String TOKEN_PREFIX = "Bearer";

	private static final String HEADER_STRING = "Authorization";

	/* Gera o token e dá a resposta para o cliente com o JWT */
	public void addAuthentication(HttpServletResponse response, String  username) throws Exception {

		/* Montagem do token */

		String JWT = Jwts.builder() /* Chama o gerador de token */
				.setSubject(username) /* Adiciona o user */
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) /* tempo de expiração */
				.signWith(SignatureAlgorithm.HS512, SECRET).compact(); /* criptografa e compacta */
		/* Ex.: Bearer dfdsfmopmFSDEF.SFERWFOfsdsf.DRTHGHTMO */
		String token = TOKEN_PREFIX + " " + JWT;

		/*
		 * Dá a resposta para a tela e para o cliente, outra API, navegador, aplicativo,
		 * javascript, outra chamada java
		 */
		response.addHeader(HEADER_STRING, token);

		liberacaoCors(response);
		
		Usuario usuario = ApplicationContextLoad.
				getApplicationContext().
				getBean(UsuarioRepository.class).findUserByLogin(username);
		
		System.out.println(usuario.getEmpresa().getId_pessoa());
		
		/*Usado para ver no Postman para teste*/
		response.getWriter().write("{\"Authorization\": \""+ token + "\","
				                + "\"username\":\""+ username +"\","
				                + "\"id\":\""+ usuario.getId_usuario() +"\","
				                + "\"empresa\":\""+usuario.getEmpresa().getId_pessoa() + "\"}");

		/* Usado para ver no Postmam para teste  */
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");

	}
	
	
	/*Retorna o usuário validado com token ou caso não seja validado retorna null*/
	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String token = request.getHeader(HEADER_STRING);
		try {
		
		if(token != null) {  /*Se o token existir*/
			
			String tokenLimpo = token.replace(TOKEN_PREFIX, "").trim();
			
			/*Faz a validação do token do usuário na requisição e obtém o USER*/
			String user = Jwts.parser().
					setSigningKey(SECRET)
					.parseClaimsJws(tokenLimpo)
					.getBody().getSubject();/*ADMIN ou Pedro*/
			if(user != null) { /*Se user foi encontrado*/
				
				Usuario usuario = ApplicationContextLoad.
						getApplicationContext().
						getBean(UsuarioRepository.class).findUserByLogin(user);
				if(usuario != null) {
					return new UsernamePasswordAuthenticationToken(
							usuario.getLogin(),
							usuario.getSenha(),
							usuario.getAuthorities());
				}
				
			}
			
		}
		}catch(SignatureException e) {
			response.getWriter().write("Token está inválido!");
			
		}catch(ExpiredJwtException e) {
			response.getWriter().write("Token está expirado, efetue o login novamente!");
			
		}catch(MalformedJwtException e) {
			response.getWriter().write("Token JWT mal formatado ou faltando partes!");
		
		}
		finally {
		liberacaoCors(response);
		}
		return null;
	}
	
	
	
	
	
	
	/*Fazendo liberação contra erro de CORS no navegador*/
	private void liberacaoCors(HttpServletResponse response) {
		
		if(response.getHeader("Access-Control-Allow-Origin") == null) {/*se não tem o parâmetro adiciona e libera*/
			response.addHeader("Access-Control-Allow-Origin", "*");/*asterisco libera*/
			
		}
		
		if(response.getHeader("Access-Control-Allow-Headers") == null) {/*se não tem o parâmetro adiciona e libera*/
			response.addHeader("Access-Control-Allow-Headers", "*");/*asterisco libera*/
			
		}
		
		if(response.getHeader("Access-Control-Request-Headers") == null) {/*se não tem o parâmetro adiciona e libera*/
			response.addHeader("Access-Control-Request-Headers", "*");/*asterisco libera*/
			
		}
		
		if(response.getHeader("Access-Control-Allow-Methods") == null) {/*se não tem o parâmetro adiciona e libera*/
			response.addHeader("Access-Control-Allow-Methods", "*");/*asterisco libera*/
			
		}
	}
		
	
	
	
	
	
	
	
	
	
	
	
	

}
