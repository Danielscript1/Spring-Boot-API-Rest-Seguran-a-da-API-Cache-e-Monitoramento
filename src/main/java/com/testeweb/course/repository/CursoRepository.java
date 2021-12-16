package com.testeweb.course.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testeweb.course.model.Curso;
import com.testeweb.course.model.Topico;

public interface CursoRepository extends JpaRepository<Curso,Long>{
	Curso findByNome(String nome);
}
