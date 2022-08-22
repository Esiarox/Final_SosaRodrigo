/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.esiarox.Controller;

import com.portfolio.esiarox.Dto.ExperienciaDto;
import com.portfolio.esiarox.Entity.Experiencia;
import com.portfolio.esiarox.Security.Controller.MensajeController;
import com.portfolio.esiarox.Service.ExperienciaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Esiarox
 */
@RestController
@RequestMapping("/exp")
@CrossOrigin(origins = "http://localhost:4200")
public class ExperienciaController {
    @Autowired
    ExperienciaService experienciaService;
    
    @GetMapping("/buscarExp")
    public ResponseEntity<List<Experiencia>> listaExp(){
        List<Experiencia> listaExp = experienciaService.listaExp();
        return new ResponseEntity(listaExp, HttpStatus.OK);
    }
    
    @GetMapping("/exp/{id}")
    public ResponseEntity<Experiencia> getExpById(@PathVariable("id") int id){
        if(!experienciaService.existsById(id))
            return new ResponseEntity(new MensajeController("no existe"), HttpStatus.NOT_FOUND);
        Experiencia experiencia = experienciaService.getExp(id).get();

        return new ResponseEntity(experiencia, HttpStatus.OK);
    }
    
    @PostMapping("/crearExp")
    public ResponseEntity<?> crearExp(@RequestBody ExperienciaDto experienciaDto){
        if(StringUtils.isBlank(experienciaDto.getNombreExp()))
            return new ResponseEntity(new MensajeController("El nombre de la experiencia laboral es obligatorio"), HttpStatus.BAD_REQUEST);
        if(experienciaService.existsByNombreExp(experienciaDto.getNombreExp()))
            return new ResponseEntity(new MensajeController("Esta experiencia laboral ya existe"), HttpStatus.BAD_REQUEST);
        Experiencia experiencia = new Experiencia(experienciaDto.getNombreExp(),experienciaDto.getDescripcion());
        experienciaService.save(experiencia);
        
        return new ResponseEntity(new MensajeController("La experiencia laboral fue agregada correctamente"), HttpStatus.OK);
    }
    
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarExp(@PathVariable("id") int id,@RequestBody ExperienciaDto experienciaDto){
        if(!experienciaService.existsById(id))
            return new ResponseEntity(new MensajeController("La experiencia laboral no existe"), HttpStatus.BAD_REQUEST);
        if(!experienciaService.existsByNombreExp(experienciaDto.getNombreExp()) && 
                experienciaService.getByNombreExp(experienciaDto.getNombreExp()).get().getId() != id)
            return new ResponseEntity(new MensajeController("La experiencia laboral no existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(experienciaDto.getNombreExp()))
            return new ResponseEntity(new MensajeController("El nombre de la experiencia laboral es obligatorio"), HttpStatus.BAD_REQUEST);
        
        Experiencia experiencia = experienciaService.getExp(id).get();
        experiencia.setNombreExp(experienciaDto.getNombreExp());
        experiencia.setDescripcion(experienciaDto.getDescripcion());
        experienciaService.save(experiencia);
        
        return new ResponseEntity(new MensajeController("La experiencia laboral fue actualizada correctamente"), HttpStatus.OK);
    }
    
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrarExp(@PathVariable("id") int id){
        if(!experienciaService.existsById(id))
            return new ResponseEntity(new MensajeController("La experiencia laboral no existe"), HttpStatus.BAD_REQUEST);    
        experienciaService.delete(id);
        
        return new ResponseEntity(new MensajeController("La experiencia laboral fue eliminada"), HttpStatus.OK);
    }
}
