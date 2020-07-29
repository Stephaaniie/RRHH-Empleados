package ar.com.ada.api.empleado.empleado.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import ar.com.ada.api.empleado.empleado.entities.Categoria;
import ar.com.ada.api.empleado.empleado.models.request.InfoBasicaCategoriaRequest;
import ar.com.ada.api.empleado.empleado.services.implementations.CategoriaService;

@RestController
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;

    @PostMapping("/categorias")
    public ResponseEntity<?> crearCategoria(@RequestBody InfoBasicaCategoriaRequest info){
        return null;
    }


    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> listarCategorias() {

        return null;
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<Categoria> listarEmpleadoPorNombre(@PathVariable int id) {
        return null;
    }

}