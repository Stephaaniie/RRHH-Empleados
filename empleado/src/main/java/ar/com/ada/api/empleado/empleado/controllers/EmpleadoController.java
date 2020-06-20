package ar.com.ada.api.empleado.empleado.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.empleado.empleado.entities.Empleado;
import ar.com.ada.api.empleado.empleado.models.GenericResponse;
import ar.com.ada.api.empleado.empleado.models.request.InfoBasicaEmpleadoRequest;
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
    public ResponseEntity<?> modificarSueldo(@PathVariable int id, @RequestBody Empleado empleado) {
        GenericResponse response = new GenericResponse();

        if (!empleadoService.operacionCheck(empleadoService.getEmpleadoPorId(id))) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (empleadoService.actualizarSueldo(empleadoService.getEmpleadoPorId(id),empleado)) {
            response.isOk    = true;
            response.id      = empleado.getEmpleadoId();
            response.mensaje = "Empleado actualizado con exito";
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response);
    }

    @PutMapping("/empleados/{id}/estados")
    public ResponseEntity<?> modificarEstado(@PathVariable int id, @RequestBody int estado) {
        GenericResponse response = new GenericResponse();

        if (!empleadoService.operacionCheck(empleadoService.getEmpleadoPorId(id))) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (empleadoService.actualizarEstado(empleadoService.getEmpleadoPorId(id),estado)) {
            response.isOk    = true;
            response.id      = empleadoService.getEmpleadoPorId(id).getEmpleadoId();
            response.mensaje = "Empleado actualizado con exito";
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response);
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<?> eliminanarEmpleado(@PathVariable int id) {
        GenericResponse response = new GenericResponse();

        if (!empleadoService.operacionCheck(empleadoService.getEmpleadoPorId(id))) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (empleadoService.eliminanarEmpleado(empleadoService.getEmpleadoPorId(id))) {
            response.isOk    = true;
            response.id      = empleadoService.getEmpleadoEliminadoConId(id).getEmpleadoId();
            response.mensaje = "Empleado eliminado con exito";
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/empleados")
    public ResponseEntity<?> crearEmpleado(@RequestBody InfoBasicaEmpleadoRequest info){
        GenericResponse response = new GenericResponse();
        
        Empleado empleado = empleadoService.crearEmpleado(info, new Empleado());
        if (empleadoService.operacionCheck(empleado)) {
            response.isOk= true;
            response.id = empleado.getEmpleadoId();
            response.mensaje = "Empleado generado con correctamente";
            return ResponseEntity.ok(response);
        }
        return  ResponseEntity.badRequest().body(response);
    }
    
    @GetMapping("/empleados")
    public ResponseEntity<?> listarEmpleado() {
        if (empleadoService.operacionCheck(empleadoService.listaEmpleados())) {
            return ResponseEntity.ok(empleadoService.listaEmpleados());
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/empleados/{nombre}")
    public ResponseEntity<?> listarEmpleadoPorNombre(@PathVariable String nombre) {
       if (empleadoService.operacionCheck(empleadoService.getEmpleadosPorNombre(nombre))) {
            return ResponseEntity.ok(empleadoService.getEmpleadosPorNombre(nombre));
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> getEmpleadoPorId(@PathVariable int id) {
        if (empleadoService.operacionCheck(empleadoService.getEmpleadoPorId(id))) {
            return ResponseEntity.ok(empleadoService.getEmpleadoPorId(id));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}