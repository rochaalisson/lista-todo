package com.ifba.notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifba.notes.model.entity.Nota;

public interface NotaRepository extends JpaRepository<Nota, Integer>{

}
