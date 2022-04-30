package com.ifba.notes.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.ifba.notes.model.dto.NotaDto;

import lombok.Getter;

@Entity
@Getter
public class Nota {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String titulo;
	private String conteudo;
	
	@ManyToOne
	private Categoria categoria;

	public void atualizarDados(NotaDto dto) {
		this.titulo = dto.getTitulo();
		this.conteudo = dto.getConteudo();
	}
}
