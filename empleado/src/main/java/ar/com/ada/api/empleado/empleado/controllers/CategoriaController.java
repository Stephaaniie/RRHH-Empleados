package ar.com.ada.api.empleado.empleado.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import ar.com.ada.api.empleado.empleado.entities.Categoria;
import ar.com.ada.api.empleado.empleado.models.GenericResponse;
import ar.com.ada.api.empleado.empleado.models.request.InfoBasicaCategoriaRequest;
import ar.com.ada.api.empleado.empleado.services.CategoriaService;

@RestController
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;

    @PostMapping("/categorias")
    public ResponseEntity<?> crearCategoria(@RequestBody InfoBasicaCategoriaRequest info){
        GenericResponse response = new GenericResponse();

        Categoria categoria = categoriaService.crearCategoria(info, new Categoria());

        if (categoriaService.operacionCheck(categoria)) {
            response.isOk= true;
            response.id = categoria.getCategoriaId();
            response.mensaje = "La categoria se genero correctamente";
            return ResponseEntity.ok(response);
        }
        return  ResponseEntity.badRequest().body(response);
    }


    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> listarCategorias() {
        if (categoriaService.operacionCheck(categoriaService.listarCategorias())) {
            return ResponseEntity.ok(categoriaService.listarCategorias());
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<Categoria> listarEmpleadoPorNombre(@PathVariable int id) {
       if (categoriaService.operacionCheck(categoriaService.buscarTodosPorCategoria(id))) {
            return ResponseEntity.ok(categoriaService.busCategoriaPorId(id));
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}