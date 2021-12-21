package com.testeweb.course.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.testeweb.course.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Service
public class TokenService {
	@Value("${forum.jwt.expiration}")
	private String expiration;
	
	@Value("${forum.jwt.secret}")
	private String secret;
	public String gerarToken(Authentication authentication) {
		

	
			Usuario logado = (Usuario) authentication.getPrincipal();
			Date hoje = new Date();
			Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
			
			return Jwts.builder()
					.setIssuer("API do Fórum da Alura")
					.setSubject(logado.getId().toString())
					.setIssuedAt(hoje)
					.setExpiration(dataExpiracao)
					.signWith(SignatureAlgorithm.HS256, secret)
					.compact();
		}
	public boolean isTokenValido(String token) {
	/*Aqui temos aquele método para gerar o token. Preciso ter esse método para fazer a validação,
	 * para validar se o token que está chegando está ok ou não. Para fazer isso, vamos usar de novo o tal de jwts
	 * Na sequência, temos que chamar primeiro setSigningKey. Tenho que passar aquele secret da nossa aplicação, 
	 * que é a chave que ele usa para criptografar e descriptografar. Tem um método chamado parseClaimsJws.
	 * Esse é o método que vamos chamar passando como parâmetro o token.
	 * Esse método devolve o Jws claims, que é um objeto onde consigo recuperar o token e as informações que setei dentro do token.
	 * Mas quando eu fizer essa chamada, se o token estiver válido, ele devolve o objeto. Se estiver inválido ou nulo,
	 * ele joga uma exception
	 * */
		try {
		Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
		return true;
		}catch(Exception e) {
			return false;
		}
	}
	public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
		
	}

		
	}

