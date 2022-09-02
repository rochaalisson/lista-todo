package com.ifba.notes.model.form;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;

@Getter
public class CategoriaForm {
	@NotEmpty
	private String nome;
}
