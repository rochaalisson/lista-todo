package com.ifba.notes.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.ifba.notes.model.dto.NotaDto;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Nota {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String titulo;
	private String conteudo;
	private Boolean concluida;
	private LocalDateTime horaConclusao;
	
	@ManyToOne
	private Categoria categoria;
	
	@ManyToOne
	private Usuario usuario;

	public void atualizarDados(NotaDto dto) {
		this.titulo = dto.getTitulo();
		this.conteudo = dto.getConteudo();
	}

	public void atualizarStatus(boolean estaConcluido) {
		this.concluida = estaConcluido;
		this.horaConclusao = estaConcluido ? LocalDateTime.now() : null;
	}
}
