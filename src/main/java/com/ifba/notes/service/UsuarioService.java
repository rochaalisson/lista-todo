package com.ifba.notes.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ifba.notes.model.dto.ConsultaUsuarioDto;
import com.ifba.notes.model.dto.CriaUsuarioDto;
import com.ifba.notes.model.entity.Perfil;
import com.ifba.notes.model.entity.Usuario;
import com.ifba.notes.repository.PerfilRepository;
import com.ifba.notes.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsuarioService {
	private final UsuarioRepository usuarioRepository;
	private final PerfilRepository perfilRepository;
	private final ModelMapper mapper;

	public ConsultaUsuarioDto criar(CriaUsuarioDto dto) {
		Usuario usuario = mapper.map(dto, Usuario.class);
		
		Perfil perfilPadrao = perfilRepository.findBySigla("USU");
		usuario.adicionarPerfil(perfilPadrao);
		
		usuario = usuarioRepository.save(usuario);
		
		return mapper.map(usuario, ConsultaUsuarioDto.class);
	}
}
