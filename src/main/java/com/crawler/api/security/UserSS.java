package com.crawler.api.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.crawler.api.enums.Perfil;

public class UserSS implements UserDetails {

	private static final long serialVersionUID = 4912631962753028965L;

	private Integer id;
	private String email;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserSS(Integer id, String email, String senha, Set<Perfil> perfis) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override //conta não expirada
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override //conta não está bloqueada
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override //as credencias não estão expirada
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override //está ativo 
	public boolean isEnabled() {
		return true;
	}

	public Integer getId() {
		return id;
	}

	public boolean hasRole(Perfil perfil) {
		return this.getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescricao()));
	}	
}
