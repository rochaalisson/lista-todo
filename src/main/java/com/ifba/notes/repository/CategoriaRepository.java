package com.ifba.notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifba.notes.model.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

}
