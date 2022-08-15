/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.esiarox.Controller;

import com.portfolio.esiarox.Entity.Persona;
import com.portfolio.esiarox.Interface.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Esiarox
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PersonaController {
    @Autowired IPersonaService ipersonaService;
    
    @GetMapping("/personas/buscar")
    public List<Persona> buscarPersonas(){
        return ipersonaService.buscarPersonas();
    }
    
    @GetMapping("/personas/buscar/perfil")
    public Persona cargarPerfil(){
        return ipersonaService.buscarPersona(1);
    }
    
    @PostMapping("/personas/crear")
    public String crearPersona(@RequestBody Persona persona){
        ipersonaService.guardarPersona(persona);
        return "Se ha agregado correctamente";
    }
    
    @DeleteMapping("/personas/borrar/{id}")
    public String borrarPersona(@PathVariable Integer id){
        ipersonaService.borrarPersona(id);
        return "Se ha eliminado correctamente";
    }
    
    @PutMapping("/personas/editar/{id}")
    public Persona editarPersona(@PathVariable Integer id,@RequestParam("nombre") String nuevoNombre,
                                                          @RequestParam("apellido") String nuevoApellido,
                                                          @RequestParam("imagen") String nuevaImagen){
        Persona persona = ipersonaService.buscarPersona(id);
        persona.setNombre(nuevoNombre);
        persona.setApellido(nuevoApellido);
        persona.setImagen(nuevaImagen);
        
        ipersonaService.guardarPersona(persona);
        return persona;
    }
}
