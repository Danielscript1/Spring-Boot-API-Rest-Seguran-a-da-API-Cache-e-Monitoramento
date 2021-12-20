package com.testeweb.course.controller;

import javax.naming.AuthenticationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testeweb.course.controller.form.LoginForm;
import com.testeweb.course.config.security.TokenService;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
	@Autowired
	private TokenService tokenService;
	//injetando depedencia do metodo AuthenticationManager
	@Autowired
	private AuthenticationManager authManager;
	
	
	@PostMapping //metodo de autenticacao pelo tokken
	public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm form){
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
	
		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication);
			return ResponseEntity.ok().build();
		}catch (org.springframework.security.core.AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
		}
		
	
	}
	
	

