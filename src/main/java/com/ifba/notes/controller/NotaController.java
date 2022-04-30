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

import com.ifba.notes.model.dto.NotaDto;
import com.ifba.notes.model.form.NotaForm;
import com.ifba.notes.service.NotaService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notas")
public class NotaController {
	private final ModelMapper mapper;
	private final NotaService notaService;
	
	@PostMapping
	public ResponseEntity<NotaDto> criar(@RequestBody NotaForm form, UriComponentsBuilder uriBuilder) {
		NotaDto nota = mapper.map(form, NotaDto.class);
		nota = notaService.criar(nota);
		
		URI uri = uriBuilder.path("/notas/{id}").buildAndExpand(nota.getId()).toUri();
		return ResponseEntity.created(uri).body(nota);
	}
	
	@GetMapping
	public ResponseEntity<List<NotaDto>> listar() {
		return ResponseEntity.ok(notaService.listar());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<NotaDto> consultar(@PathVariable Integer id) {
		return ResponseEntity.ok(notaService.consultar(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<NotaDto> atualizar(@PathVariable Integer id, @RequestBody NotaForm form) {
		NotaDto nota = mapper.map(form, NotaDto.class);
		nota = notaService.atualizar(id, nota);
		
		return ResponseEntity.ok(nota);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Integer id) {
		notaService.deletar(id);
		
		return ResponseEntity.noContent().build();
	}
}
