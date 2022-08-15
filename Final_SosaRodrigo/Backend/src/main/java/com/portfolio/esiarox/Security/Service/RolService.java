/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.esiarox.Security.Service;

import com.portfolio.esiarox.Security.Entity.Rol;
import com.portfolio.esiarox.Security.Enums.RolNombre;
import com.portfolio.esiarox.Security.Repository.IRolRepository;
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
public class RolService {
    @Autowired
    IRolRepository iRolRpository;
    
    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return iRolRpository.findByRolNombre(rolNombre);
    }
    
    public void save(Rol rol){
        iRolRpository.save(rol);
    }
}
