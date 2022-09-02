package com.ifba.notes.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotaDto {
	private Integer id;
	private Integer idCategoria;
	private String titulo;
	private String conteudo;
	private Boolean concluida;
}
