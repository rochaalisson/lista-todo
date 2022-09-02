package com.ifba.notes.config.security;

import java.io.IOException;

import javax.persistence.EntityNotFoundException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ifba.notes.model.entity.Usuario;
import com.ifba.notes.repository.UsuarioRepository;
import com.ifba.notes.service.TokenService;


public class AutenticacaoTokenFilter extends OncePerRequestFilter {
	private TokenService tokenService;
	private UsuarioRepository usuarioRepository;

	public AutenticacaoTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = recuperarToken(request);

		if (tokenService.validarToken(token))
			autenticarCliente(token);

		filterChain.doFilter(request, response);
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");

		if (token == null || token.isEmpty() || !token.startsWith("Bearer")) {
			return null;
		}
		return token.substring(7);
	}

	private void autenticarCliente(String token) {
		Integer idUsuario = tokenService.getIdUsuario(token);
		Usuario usuario = usuarioRepository.findById(idUsuario)
				.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null,
				usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
