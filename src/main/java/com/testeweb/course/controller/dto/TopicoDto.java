package com.testeweb.course.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.testeweb.course.model.Topico;

public class TopicoDto {
	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;
	
	
	public TopicoDto(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
	}
	public Long getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	//codigo fica mais otimizado -> utilizando springData para conversao para dto
	public static Page<TopicoDto> converter(Page<Topico> topicos) {
		//realizar uma convecao de topicos para topicos dto
		return topicos.map(TopicoDto::new);
	}
	
	
}
