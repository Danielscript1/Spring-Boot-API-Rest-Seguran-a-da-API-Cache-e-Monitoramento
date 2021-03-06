package com.testeweb.course.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testeweb.course.model.Usuario;



public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByEmail(String email);

}
