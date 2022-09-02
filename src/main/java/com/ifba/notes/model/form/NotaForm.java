package com.ifba.notes.model.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;

@Getter
public class NotaForm {
	@NotEmpty
	private String titulo;
	private String conteudo;
	@Positive
	private Integer idCategoria;
}
