package jdev.mentoria.lojavirtual.security;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTTokenAutenticacaoService {
	
	/*Token de validade de 11 dias*/
	private static final long EXPIRATION_TIME = 959990000;
	
	/*Chave de senha para juntar com o JWT*/
	private static final String SECRET = "ssdfsdfojffpojffpoow¨*t45#%";

	private static final String TOKEN_PREFIX = "Bearer";
	
	private static final String HEADER_STRING = "Authorization";
	
	/*Gera o token e dá a resposta para o cliente com JWT*/
	public void addAuthentication(HttpServletResponse response, String username) throws Exception{
		
		/*Montagem do Token*/
		String JWT = Jwts.builder() /*Chama o gerador de token*/
				.setSubject(username) /*Adiciona o user*/
		        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		
		/*Ex Bearer 49439rfrrh9hfhf.f3f09j39fj349fj49.fwhef9fh9ewh9*/
		String token = TOKEN_PREFIX + " " + JWT;
		
		/*Dá a resposta pra tela e para o cliente, outra API, navegador, aplicativo, javascript, outra chamada Java*/
		response.addHeader(HEADER_STRING, token);
		
		/*Usado para ver no Postman para teste*/
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
	}
}
