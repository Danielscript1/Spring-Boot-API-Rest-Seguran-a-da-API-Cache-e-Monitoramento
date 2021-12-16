package com.testeweb.course.controller.dto;

import com.testeweb.course.model.Resposta;

public class RespostaDto {
	private Long id;
	private String mensagem;
	private String nomeAutor;
	public RespostaDto(Resposta resposta) {
		this.id = resposta.getId();
		this.mensagem = resposta.getMensagem();
		this.nomeAutor = resposta.getAutor().getNome();
	}
	public Long getId() {
		return id;
	}
	public String getMensagem() {
		return mensagem;
	}
	public String getNomeAutor() {
		return nomeAutor;
	}
	
	
}
