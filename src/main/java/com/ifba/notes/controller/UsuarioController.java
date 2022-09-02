package com.ifba.notes.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifba.notes.model.dto.ConsultaUsuarioDto;
import com.ifba.notes.model.dto.CriaUsuarioDto;
import com.ifba.notes.model.form.UsuarioForm;
import com.ifba.notes.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	private final UsuarioService usuarioService;
	private final ModelMapper mapper;
	
	@PostMapping
	public ResponseEntity<ConsultaUsuarioDto> criar(@RequestBody UsuarioForm form) {
		CriaUsuarioDto usuarioNovo = mapper.map(form, CriaUsuarioDto.class);
		ConsultaUsuarioDto usuarioCriado = usuarioService.criar(usuarioNovo);
		
		return ResponseEntity.ok(usuarioCriado);
	}
}
