package com.testeweb.course.config.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroDeValidacaoHandler {
	//variavel que auxiliar pegar mensagem de erro
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroDeFormularioDto> handle(MethodArgumentNotValidException exception) {
		//variavel que contem meus campos de erro pensonalizado
		List<ErroDeFormularioDto>dto = new ArrayList<>();
		//variavel que contem o erro padrao do spring, logica 
	List<FieldError> fieldErros = exception.getBindingResult().getFieldErrors();
	//logicas vou percorrer meus campos add minha classe dto
	fieldErros.forEach(e->{
		//string que descobrir de qual idioma esta vindo erro , para ela mostra o idioma correto ao cliente
		String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
		//instanciando os objetos com new
		ErroDeFormularioDto erro = new ErroDeFormularioDto(e.getField(), mensagem);
		dto.add(erro);
	});
		return dto;
	}
	
}
