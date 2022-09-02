package com.ifba.notes.model.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Usuario implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	public String nome;
	@Column(unique = true)
	public String email;
	public String senha;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Perfil> perfis;
	
	public void adicionarPerfil(Perfil perfil) {
		if (perfis == null)
			perfis = new ArrayList<Perfil>();
		perfis.add(perfil);
	}
	
	public void setSenha(String senha) {
		this.senha = new BCryptPasswordEncoder().encode(senha);
	}
	
	@Override
	public boolean equals(Object object) {
		if (this == object)
	        return true;
	    if (object == null)
	        return false;
	    if (getClass() != object.getClass())
	        return false;
	    if (this.getUsername() == ((Usuario)object).getUsername())
	    	return true;
	    return false;
	}
	
	public Usuario(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
	
	@Override
	public Collection<Perfil> getAuthorities() {
		return perfis;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}	
}
