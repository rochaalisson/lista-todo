package com.ifba.notes.model.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Getter;

@Getter
public class LoginForm {
	private String email;
	private String senha;
	
	public UsernamePasswordAuthenticationToken toAuthenticationToken() {
		return new UsernamePasswordAuthenticationToken(email, senha);
	}
}
