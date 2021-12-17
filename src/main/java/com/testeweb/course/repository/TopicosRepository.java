package com.testeweb.course.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.testeweb.course.model.Topico;

public interface TopicosRepository extends JpaRepository<Topico,Long>{
	Page<Topico> findByCursoNome(String nomeCurso,Pageable paginacao);

	
}
