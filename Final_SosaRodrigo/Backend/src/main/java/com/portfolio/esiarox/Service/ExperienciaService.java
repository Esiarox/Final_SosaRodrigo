/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.esiarox.Service;

import com.portfolio.esiarox.Entity.Experiencia;
import com.portfolio.esiarox.Repository.IExperienciaRepo;
import java.util.List;
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
public class ExperienciaService {
    @Autowired
    IExperienciaRepo iExperienciaRepo;
    
    public List<Experiencia> listaExp(){
        return iExperienciaRepo.findAll();
    }
    
    public Optional<Experiencia> getExp(int id){
        return iExperienciaRepo.findById(id);
    }
    
    public Optional<Experiencia> getByNombreExp(String nombreExp){
        return iExperienciaRepo.findByNombreExp(nombreExp);
    }
    
    public void save(Experiencia experiencia){
        iExperienciaRepo.save(experiencia);
    }
    
    public void delete(int id){
        iExperienciaRepo.deleteById(id);
    }
    
    public boolean existsById(int id){
        return iExperienciaRepo.existsById(id);
    }
    
    public boolean existsByNombreExp(String nombreExp){
        return iExperienciaRepo.existsByNombreExp(nombreExp);
    }
}
