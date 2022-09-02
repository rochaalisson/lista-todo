package com.ifba.notes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifba.notes.model.entity.Nota;
import com.ifba.notes.model.entity.Usuario;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Integer> {
	List<Nota> findAllByUsuario(Usuario usuario);
}
