package com.ifba.notes.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ifba.notes.model.dto.CategoriaDto;
import com.ifba.notes.model.entity.Categoria;
import com.ifba.notes.repository.CategoriaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoriaService {	
	private final CategoriaRepository categoriaRepository;
	
	private final ModelMapper mapper;
	
	public CategoriaDto criar(CategoriaDto dto) {
		Categoria categoria = mapper.map(dto, Categoria.class);
		
		categoria = categoriaRepository.save(categoria);
		return mapper.map(categoria, CategoriaDto.class);
	}
	
	public List<CategoriaDto> listar() {
		List<Categoria> categorias = categoriaRepository.findAll();
		
		return categorias.stream()
				.map(categoria -> mapper.map(categoria, CategoriaDto.class))
				.collect(Collectors.toList());
	}
	
	public CategoriaDto consultar(Integer id) {
		Categoria categoria = categoriaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		return mapper.map(categoria, CategoriaDto.class);
	}
	
	@Transactional
	public CategoriaDto atualizar(Integer id, CategoriaDto dto) {
		Categoria categoria = categoriaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		
		categoria.atualizarDados(dto);
		
		return mapper.map(categoria, CategoriaDto.class);
	}
	
	@Transactional
	public void deletar(Integer id) {
		categoriaRepository.deleteById(id);
	}
}
