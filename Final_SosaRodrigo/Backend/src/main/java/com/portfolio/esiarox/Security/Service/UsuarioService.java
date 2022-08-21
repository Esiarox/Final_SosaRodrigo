/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.esiarox.Security.Service;

import com.portfolio.esiarox.Security.Entity.Usuario;
import com.portfolio.esiarox.Security.Repository.IUsuarioRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Esiarox
 */
@Service
@Transactional
public class UsuarioService {
    @Autowired
    IUsuarioRepository iUsuarioRepository;
    
    public Optional<Usuario> getByUsuario(String usuario){
        return iUsuarioRepository.findByUsuario(usuario);
    }
    
    public boolean existByUsuario(String usuario){
        return iUsuarioRepository.existByUsuario(usuario);
    }
    
    public boolean existByEmail(String email){
        return iUsuarioRepository.existByEmail(email);
    }
    
    public void save(Usuario usuario){
        iUsuarioRepository.save(usuario);
    }
}
