package com.testeweb.course.config.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.testeweb.course.model.Usuario;
import com.testeweb.course.repository.UsuarioRepository;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {
	/* obs:essas requisicoes sao realizada antes de passar para o meu controller
	 * temos que colocar nossa lógica para pegar o token do cabeçalho,
	 *  verificar se está ok, autenticar no Spring
	 * 
	 * */
	
	private TokenService tokenService;
	
	private UsuarioRepository usuarioRepository;
	
	public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override 
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = recuperarToken(request);
		//proximo passo e validar o token,verificar se ele esta correto
		boolean valido = tokenService.isTokenValido(token);
		//chamada do metodo de autentincacao do usuario
		if(valido) {
			autenticarCliente(token);
		}
		filterChain.doFilter(request, response);//essa linha significa ja executei as tarefa pedentes e seguir o fluxo da execucao dos demias componentes
		
	}
	private void autenticarCliente(String token) {
		Long idUsuario = tokenService.getIdUsuario(token);
		Usuario usuario = usuarioRepository.findById(idUsuario).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null,usuario.getAuthorities());
		//metodo proprio do spring que considera que ja esta autenticado
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
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
