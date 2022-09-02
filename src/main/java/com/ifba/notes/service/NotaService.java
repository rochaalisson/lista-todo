package com.ifba.notes.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ifba.notes.model.dto.NotaDto;
import com.ifba.notes.model.entity.Nota;
import com.ifba.notes.model.entity.Usuario;
import com.ifba.notes.repository.NotaRepository;
import com.ifba.notes.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NotaService {	
	private final NotaRepository notaRepository;
	private final UsuarioRepository usuarioRepository;
	private final ModelMapper mapper;
	
	public NotaDto criar(NotaDto dto, String emailUsuario) {
		Nota nota = mapper.map(dto, Nota.class);
		
		Usuario usuario = usuarioRepository.findByEmail(emailUsuario).orElseThrow();
		nota.setUsuario(usuario);
		
		nota = notaRepository.save(nota);
		return mapper.map(nota, NotaDto.class);
	}
	
	public List<NotaDto> listar(String emailUsuario) {
		Usuario usuario = usuarioRepository.findByEmail(emailUsuario).orElseThrow();
		
		List<Nota> notas = notaRepository.findAllByUsuario(usuario);
		
		return notas.stream()
				.map(nota -> mapper.map(nota, NotaDto.class))
				.collect(Collectors.toList());
	}
	
	public NotaDto consultar(Integer id, String emailUsuario) throws Exception {
		Usuario usuario = usuarioRepository.findByEmail(emailUsuario).orElseThrow();
		
		Nota nota = notaRepository.findById(id).orElseThrow();
		
		if (!usuario.equals(nota.getUsuario()))
			throw new Exception();
		
		return mapper.map(nota, NotaDto.class);
	}
	
	@Transactional
	public NotaDto atualizar(Integer id, NotaDto dto, String emailUsuario) throws Exception {
		Usuario usuario = usuarioRepository.findByEmail(emailUsuario).orElseThrow();
		
		Nota nota = notaRepository.findById(id).orElseThrow();
		

		if (!usuario.equals(nota.getUsuario()))
			throw new Exception();
		
		nota.atualizarDados(dto);
		
		return mapper.map(nota, NotaDto.class);
	}
	
	@Transactional
	public NotaDto atualizarStatus(Integer id, boolean estaConcluido, String emailUsuario) throws Exception {
		Usuario usuario = usuarioRepository.findByEmail(emailUsuario).orElseThrow();
		
		Nota nota = notaRepository.findById(id).orElseThrow();

		if (!usuario.equals(nota.getUsuario()))
			throw new Exception();
		
		nota.atualizarStatus(estaConcluido);
		
		return mapper.map(nota, NotaDto.class);
	}
	
	@Transactional
	public void deletar(Integer id, String emailUsuario) throws Exception {
		Usuario usuario = usuarioRepository.findByEmail(emailUsuario).orElseThrow();
		Nota nota = notaRepository.findById(id).orElseThrow();

		if (!usuario.equals(nota.getUsuario()))
			throw new Exception();
		
		notaRepository.deleteById(id);
	}
}
