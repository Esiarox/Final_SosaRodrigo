/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.esiarox.Interface;

import com.portfolio.esiarox.Entity.Persona;
import java.util.List;

/**
 *
 * @author Esiarox
 */
public interface IPersonaService {
    
    public List<Persona> buscarPersonas();
    
    public void guardarPersona(Persona persona);
    
    public void borrarPersona(Integer id);
    
    public Persona buscarPersona(Integer id);
}
