package ar.com.ada.api.empleado.empleado.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.empleado.empleado.entities.Categoria;
import ar.com.ada.api.empleado.empleado.entities.Empleado;
import ar.com.ada.api.empleado.empleado.models.request.InfoBasicaCategoriaRequest;
import ar.com.ada.api.empleado.empleado.services.implementations.CategoriaService;

@RestController
@RequestMapping("api/categorias")
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;

    @PostMapping()
    public ResponseEntity<Categoria> save(@RequestBody InfoBasicaCategoriaRequest info){
        return new ResponseEntity<>(categoriaService.save(new Categoria(info.categoriaId,info.descripcion,info.sueldo)), HttpStatus.CREATED);
    }

    @GetMapping()
    public List<Categoria> listarCategorias() {
        return categoriaService.findAll();
    }

    @GetMapping("/{id}")
    public Categoria listarEmpleadoPorNombre(@PathVariable int id) {
        return categoriaService.findById(id);
    }
    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        categoriaService.deleteById(id);
        return "OK";
    }

    @PutMapping("/{id}")
    public Categoria modificarEmpleado(@PathVariable int id, @RequestBody Categoria categoria) {
      categoria.setCategoriaId(id);
      return categoriaService.save(categoria);
    }

    @GetMapping("/sueldos-nuevos")
    public List<Empleado> getSueldosNuevos() {
        return categoriaService.calcularProximosSueldos();
    }

    @GetMapping("/sueldos-actuales")
    public List<Empleado> getSueldosActuales() {
        return categoriaService.obtenerSueldosActuales();
    }

    @GetMapping("/vacias")
    public List<Categoria> getCategoriasSinEmpleados() {
        return categoriaService.obtenerCategoriasSinEmpleados();
    }
}