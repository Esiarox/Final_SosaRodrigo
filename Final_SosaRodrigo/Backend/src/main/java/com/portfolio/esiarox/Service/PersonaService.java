/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.esiarox.Service;

import com.portfolio.esiarox.Entity.Persona;
import com.portfolio.esiarox.Interface.IPersonaService;
import com.portfolio.esiarox.Repository.IPersonaRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Esiarox
 */
@Service
public class PersonaService implements IPersonaService{
    @Autowired IPersonaRepo ipersonaRepo;
    @Override
    public List<Persona> buscarPersonas() {
        List<Persona> personas = ipersonaRepo.findAll();
        return personas;
    }

    @Override
    public void guardarPersona(Persona persona) {
        ipersonaRepo.save(persona);
    }

    @Override
    public void borrarPersona(Integer id) {
        ipersonaRepo.deleteById(id);
    }

    @Override
    public Persona buscarPersona(Integer id) {
        Persona persona = ipersonaRepo.findById(id).orElse(null);
        return persona;
    }
    
}
