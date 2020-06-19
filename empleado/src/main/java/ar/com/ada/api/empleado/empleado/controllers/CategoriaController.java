package ar.com.ada.api.empleado.empleado.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import ar.com.ada.api.empleado.empleado.entities.Categoria;
import ar.com.ada.api.empleado.empleado.services.CategoriaService;

@RestController
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @PostMapping("/categorias")
    public ResponseEntity<?> crearCategoria(@RequestBody Categoria categoria){
        
        categoriaService.crearCategoria(categoria);

		return ResponseEntity.ok(categoria);
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> getCategoria(){
         
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }
}