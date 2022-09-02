package com.ifba.notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifba.notes.model.entity.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Integer> {
	Perfil findBySigla(String sigla);
}
