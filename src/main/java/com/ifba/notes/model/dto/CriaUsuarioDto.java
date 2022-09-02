package com.ifba.notes.model.dto;

import lombok.Data;

@Data
public class CriaUsuarioDto {
	private Integer id;
	private String nome;
	private String email;
	private String senha;
}
