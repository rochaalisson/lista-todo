package com.ifba.notes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifba.notes.model.dto.TokenDto;
import com.ifba.notes.model.form.LoginForm;
import com.ifba.notes.service.TokenService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController {
	private final AuthenticationManager authManager;
	private final TokenService tokenService;

	@PostMapping
	public ResponseEntity<TokenDto> login(@RequestBody LoginForm form) {
		UsernamePasswordAuthenticationToken credentials = form.toAuthenticationToken();
		try {
			Authentication authentication = authManager.authenticate(credentials);
			
			TokenDto token = new TokenDto(tokenService.gerarToken(authentication), "Bearer");
			return ResponseEntity.ok(token);
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
