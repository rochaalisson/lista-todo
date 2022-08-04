package com.ifba.notes.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ifba.notes.model.dto.CategoriaDto;

import lombok.Getter;

@Entity
@Getter
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	public void atualizarDados(CategoriaDto dto) {
		this.nome = dto.getNome();
	}
}
