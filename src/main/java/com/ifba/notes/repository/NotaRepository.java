package com.ifba.notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifba.notes.model.entity.Nota;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Integer>{

}
