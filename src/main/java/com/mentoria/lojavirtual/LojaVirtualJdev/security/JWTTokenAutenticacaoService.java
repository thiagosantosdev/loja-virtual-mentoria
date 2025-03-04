package com.mentoria.lojavirtual.LojaVirtualJdev.security;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletResponse;

/*Criar a autenticação e retornar também a autenticação JWT*/
@Service
@Component
public class JWTTokenAutenticacaoService {

	/* Token de validade de 2 dias */
	private static final long EXPIRATION_TIME = 172800000;

	/* Chave de senha para juntar com o JWT */
	private static final String SECRET = "SenhaExtremamenteSecreta";

	private static final String TOKEN_PREFIX = "Bearer";

	private static final String HEADER_STRING = "Authorization";

	/* Gera o token e dá a resposta para o cliente com o JWT */
	public void addAuthentication(HttpServletResponse response, String username) throws Exception {

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

		//liberacaoCors(response);

		/* Usado para ver no Postmam para teste */
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");

	}

}
