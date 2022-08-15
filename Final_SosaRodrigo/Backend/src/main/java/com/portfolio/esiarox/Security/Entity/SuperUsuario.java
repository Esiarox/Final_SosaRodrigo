/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.esiarox.Security.Entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
/**
 *
 * @author Esiarox
 */
public class SuperUsuario implements UserDetails{
    private String nombre;
    private String usuario;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public SuperUsuario(String nombre, String usuario, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }
    
    public static SuperUsuario build(Usuario usuario){
        List<GrantedAuthority> authorities = usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().name())).collect(Collectors.toList());
        return new SuperUsuario(usuario.getNombre(), usuario.getUsuario(), usuario.getEmail(),usuario.getPassword(),authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getEmail() {
        return email;
    }
    
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return usuario;
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
