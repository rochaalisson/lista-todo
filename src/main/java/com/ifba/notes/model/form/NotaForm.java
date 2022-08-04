package com.ifba.notes.model.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;

@Getter
public class NotaForm {
	@NotNull
	private String titulo;
	private String conteudo;
	@NotNull @Positive
	private int idCategoria;
}
