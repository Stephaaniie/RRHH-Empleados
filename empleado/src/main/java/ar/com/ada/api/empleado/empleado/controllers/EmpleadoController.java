package ar.com.ada.api.empleado.empleado.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.empleado.empleado.entities.Empleado;
import ar.com.ada.api.empleado.empleado.models.GenericResponse;
import ar.com.ada.api.empleado.empleado.models.request.EstadoNuevo;
import ar.com.ada.api.empleado.empleado.models.request.InfoBasicaEmpleadoRequest;
import ar.com.ada.api.empleado.empleado.models.request.SueldoNuevo;
import ar.com.ada.api.empleado.empleado.services.CategoriaService;
import ar.com.ada.api.empleado.empleado.services.EmpleadoService;

@RestController
public class EmpleadoController {   

    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    CategoriaService categoriaService;

    @PutMapping("/empleados/{id}")
    public ResponseEntity<?> modificarEmpleado(@PathVariable int id, @RequestBody Empleado empleado) {
        GenericResponse response = new GenericResponse();

        if (!empleadoService.operacionCheck(empleadoService.getEmpleadoPorId(id))) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (empleadoService.actualizarEmpleado(empleadoService.getEmpleadoPorId(id),empleado)) {
            response.isOk    = true;
            response.id      = empleado.getEmpleadoId();
            response.mensaje = "Empleado actualizado con exito";
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response);
    }

    @PutMapping("/empleados/{id}/sueldos")
    public ResponseEntity<?> modificarSueldo(@PathVariable int id, @RequestBody SueldoNuevo sueldoNuevo) {
        GenericResponse response = new GenericResponse();
        Empleado empleado = empleadoService.getEmpleadoPorId(id);
        if (empleado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (empleadoService.actualizarSueldo(empleado,sueldoNuevo.sueldo)) {
            response.isOk    = true;
            response.id      = empleado.getEmpleadoId();
            response.mensaje = "Empleado actualizado con exito";
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response);
    }

    @PutMapping("/empleados/{id}/estados")
    public ResponseEntity<?> modificarEstado(@PathVariable int id, @RequestBody EstadoNuevo estado) {
        GenericResponse response = new GenericResponse();
        Empleado empleado = empleadoService.getEmpleadoPorId(id);
        if (empleado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (empleadoService.actualizarEstado(empleado,estado.estado)) {
            response.isOk    = true;
            response.id      = empleado.getEmpleadoId();
            response.mensaje = "Empleado actualizado con exito";
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response);
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<?> eliminanarEmpleado(@PathVariable int id) {
        GenericResponse response = new GenericResponse();

        Empleado empleado = empleadoService.getEmpleadoPorId(id);
        if (empleado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (empleadoService.eliminanarEmpleado(empleado)) {
            response.isOk    = true;
            response.id      = empleado.getEmpleadoId();
            response.mensaje = "Empleado eliminado con exito";
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/empleados")
    public ResponseEntity<?> crearEmpleado(@RequestBody InfoBasicaEmpleadoRequest info) {
        GenericResponse response = new GenericResponse();
        
        Empleado empleado = empleadoService.crearEmpleado(info.sueldo,info.nombre,info.edad,info.categoriaId, new Empleado());
        if (empleado != null) {
            response.isOk= true;
            response.id = empleado.getEmpleadoId();
            response.mensaje = "Empleado generado con correctamente";
            return ResponseEntity.ok(response);
        }
        return  ResponseEntity.badRequest().body(response);
    }
    
    @GetMapping("/empleados")
    public ResponseEntity<?> listarEmpleado() {
        List<Empleado> empleados = empleadoService.listaEmpleados();
        if (empleados == null) {
            return ResponseEntity.ok(empleados);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
/*
    @GetMapping("/empleados/{nombre}")
    public ResponseEntity<?> listarEmpleadoPorNombre(@PathVariable String nombre) {
        List<Empleado> empleados = empleadoService.getEmpleadosPorNombre(nombre);
        if (empleados.isEmpty()) {
            return ResponseEntity.ok(empleados);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> getEmpleadoPorId(@PathVariable int id) {
        Empleado empleado = empleadoService.getEmpleadoPorId(id);
        if (empleado != null) {
            return ResponseEntity.ok(empleado);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}