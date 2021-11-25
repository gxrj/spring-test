package com.teste.app.configs;

import java.util.ArrayList;
import java.util.Collection;

import com.teste.app.entities.Usuario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserSecurityConfigMapping implements UserDetails {
    
    private final Usuario user;

    public UserSecurityConfigMapping( Usuario user ){ this.user = user; }

    @Override
    public String getUsername(){ return this.user.getLogin(); }

    @Override
    public String getPassword(){ return this.user.getSenha(); }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){ return new ArrayList<GrantedAuthority>( ); }

    @Override
    public boolean isEnabled(){ return true; }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

}
