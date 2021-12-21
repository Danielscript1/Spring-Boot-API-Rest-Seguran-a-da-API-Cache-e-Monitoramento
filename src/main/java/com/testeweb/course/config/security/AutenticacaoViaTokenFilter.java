package com.testeweb.course.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {
	/* obs:essas requisicoes sao realizada antes de passar para o meu controller
	 * temos que colocar nossa lógica para pegar o token do cabeçalho,
	 *  verificar se está ok, autenticar no Spring
	 * 
	 * */
	@Override 
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = recuperarToken(request);
		//proximo passo e validar o token,verificar se ele esta correto
		
		filterChain.doFilter(request, response);//essa linha significa ja executei as tarefa pedentes e seguir o fluxo da execucao dos demias componentes
		
	}
	//logica para recupração do token
	/*
	 *  A autenticação é feita para cada requisição. 
	 *  Toda requisição que chegar para a nossa API, nós pegamos o token, 
	 *  autenticamos o usuário, se estiver ok o token, executamos a requisição,
	 *  devolvemos a resposta, acabou o assunto. Na próxima requisição a API nem lembra mais quem é esse cliente.
	 *  Tenho que pegar o token do cabeçalho, autenticar de novo, rodar o request e devolver a resposta.
	 *  Por isso é por requisição.
	 * 
	 * */
	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token == null || token.isEmpty() || !token.startsWith("Bearer")) {
			return null;
		}
		return token.substring(7, token.length());
	}

}
