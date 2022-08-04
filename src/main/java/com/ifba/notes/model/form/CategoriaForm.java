package com.ifba.notes.model.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;

@Getter
public class CategoriaForm {
	@NotNull
	private String nome;
}
