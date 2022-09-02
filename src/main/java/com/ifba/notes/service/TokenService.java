package com.ifba.notes.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.ifba.notes.model.entity.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	@Value("${notes.jwt.secret}")
	private String secret;
	
	@Value("${notes.jwt.expiration}")
	private String expiration;
	
	public String gerarToken(Authentication authentication) {
		Usuario usuario = (Usuario)authentication.getPrincipal();
		Date hoje = new Date();
		Date expiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder()
				.setIssuer("NotesAPI")
				.setSubject(usuario.getId().toString())
				.setIssuedAt(hoje)
				.setExpiration(expiracao)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean validarToken(String token) {
		try {			
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Integer getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return Integer.parseInt(claims.getSubject());
	}
}