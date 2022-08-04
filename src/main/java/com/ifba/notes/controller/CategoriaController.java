package com.ifba.notes.controller;

import java.net.URI;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ifba.notes.model.dto.CategoriaDto;
import com.ifba.notes.model.form.CategoriaForm;
import com.ifba.notes.service.CategoriaService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	private final ModelMapper mapper;
	private final CategoriaService categoriaService;
	
	@PostMapping
	public ResponseEntity<CategoriaDto> criar(@RequestBody CategoriaForm form, UriComponentsBuilder uriBuilder) {
		CategoriaDto categoria = mapper.map(form, CategoriaDto.class);
		categoria = categoriaService.criar(categoria);
		
		URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).body(categoria);
	}
	
	@GetMapping
	public ResponseEntity<List<CategoriaDto>> listar() {
		return ResponseEntity.ok(categoriaService.listar());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDto> consultar(@PathVariable Integer id) {
		return ResponseEntity.ok(categoriaService.consultar(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoriaDto> atualizar(@PathVariable Integer id, @RequestBody CategoriaForm form) {
		CategoriaDto categoria = mapper.map(form, CategoriaDto.class);
		categoria = categoriaService.atualizar(id, categoria);
		
		return ResponseEntity.ok(categoria);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Integer id) {
		categoriaService.deletar(id);
		
		return ResponseEntity.noContent().build();
	}
}
