package ar.com.ada.api.empleado.empleado.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.empleado.empleado.entities.Empleado;
import ar.com.ada.api.empleado.empleado.models.request.EstadoNuevo;
import ar.com.ada.api.empleado.empleado.models.request.InfoBasicaEmpleadoRequest;
import ar.com.ada.api.empleado.empleado.models.request.SueldoNuevo;
import ar.com.ada.api.empleado.empleado.services.implementations.CategoriaService;
import ar.com.ada.api.empleado.empleado.services.implementations.EmpleadoService;

@RestController
public class EmpleadoController {   

    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    CategoriaService categoriaService;

    @PutMapping("/empleados/{id}")
    public ResponseEntity<?> modificarEmpleado(@PathVariable int id, @RequestBody Empleado empleado) {
		return null;
    }

    @PutMapping("/empleados/{id}/sueldos")
    public ResponseEntity<?> modificarSueldo(@PathVariable int id, @RequestBody SueldoNuevo sueldoNuevo) {
		return null;
    }

    @PutMapping("/empleados/{id}/estados")
    public ResponseEntity<?> modificarEstado(@PathVariable int id, @RequestBody EstadoNuevo estado) {
		return null;
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<?> eliminanarEmpleado(@PathVariable int id) {

        return null;
    }

    @PostMapping("/empleados")
    public ResponseEntity<?> crearEmpleado(@RequestBody InfoBasicaEmpleadoRequest info) {

        return null;
    }
    
    @GetMapping("/empleados")
    public ResponseEntity<?> listarEmpleado() {
		return null;
    }
    
    @GetMapping("/empleados/{nombre}")
    public ResponseEntity<?> listarEmpleadoPorNombre(@PathVariable String nombre) {
		return null;
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> getEmpleadoPorId(@PathVariable int id) {
		return null;
    }
}