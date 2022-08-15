/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.esiarox.Security.Service;

import com.portfolio.esiarox.Security.Entity.SuperUsuario;
import com.portfolio.esiarox.Security.Entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Esiarox
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        Usuario user = usuarioService.getByUsuario(usuario).get();
        return SuperUsuario.build(user);       
    }
    
    
}
