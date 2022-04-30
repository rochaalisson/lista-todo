package com.ifba.notes.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ifba.notes.model.dto.NotaDto;
import com.ifba.notes.model.entity.Nota;
import com.ifba.notes.repository.NotaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NotaService {	
	private final NotaRepository notaRepository;
	
	private final ModelMapper mapper;
	
	public NotaDto criar(NotaDto dto) {
		Nota nota = mapper.map(dto, Nota.class);
		
		nota = notaRepository.save(nota);
		
		return mapper.map(nota, NotaDto.class);
	}
	
	public List<NotaDto> listar() {
		List<Nota> notas = notaRepository.findAll();
		
		return notas.stream()
				.map(nota -> mapper.map(nota, NotaDto.class))
				.collect(Collectors.toList());
	}
	
	public NotaDto consultar(Integer id) {
		Nota nota = notaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		return mapper.map(nota, NotaDto.class);
	}
	
	@Transactional
	public NotaDto atualizar(Integer id, NotaDto dto) {
		Nota nota = notaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		
		nota.atualizarDados(dto);
		
		return mapper.map(nota, NotaDto.class);
	}
	
	@Transactional
	public void deletar(Integer id) {
		notaRepository.deleteById(id);
	}
}
