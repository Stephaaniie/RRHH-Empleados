package ar.com.ada.api.empleado.empleado.controllers;

import java.math.BigDecimal;
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
import ar.com.ada.api.empleado.empleado.models.request.EstadoNuevo;
import ar.com.ada.api.empleado.empleado.models.request.InfoBasicaEmpleadoRequest;
import ar.com.ada.api.empleado.empleado.models.request.SueldoNuevo;
import ar.com.ada.api.empleado.empleado.services.implementations.EmpleadoService;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {   

    @Autowired
    EmpleadoService empleadoService;

    @PutMapping("/{id}")
    public Empleado modificarEmpleado(@PathVariable int id, @RequestBody Empleado empleado) {
      empleado.setEmpleadoId(id);
      return empleadoService.save(empleado);
    }

    @PutMapping("/{id}/sueldos")
    public Empleado modificarSueldo(@PathVariable int id, @RequestBody SueldoNuevo sueldoNuevo) {
      Empleado empleado = empleadoService.findById(id);
      empleado.setSueldo(sueldoNuevo.sueldo);
      return empleadoService.save(empleado);
    }

  @PutMapping("/{id}/estados")
  public Empleado modificarEstado(@PathVariable int id, @RequestBody EstadoNuevo estado) {
    Empleado empleado = empleadoService.findById(id);
    empleado.setEstadoId(estado.estado);
    return empleadoService.save(empleado);
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable("id") int id) {
    empleadoService.deleteById(id);
    return "OK";
  }

  @PostMapping()
  public ResponseEntity<Empleado> save(@RequestBody InfoBasicaEmpleadoRequest info) {
    return new ResponseEntity<>(empleadoService.save(new Empleado(info.categoriaId,info.edad,info.nombre,info.sueldo)), HttpStatus.CREATED);
  }
    
  @GetMapping()
  public List<Empleado> findAll() {
		return empleadoService.findAll();
  }
    
  @GetMapping("/{nombre}")
  public List<Empleado> findByNombre(@PathVariable String nombre) {
		return empleadoService.findByNombre(nombre);
  }

  @GetMapping("/{dni}")
  public Empleado findByDni(@PathVariable int dni) {
		return empleadoService.findByDni(dni);
  }

  @GetMapping("/{categoria}")
  public List<Empleado> findByCategoria(@PathVariable Categoria categoria) {
		return empleadoService.findByCategoria(categoria);
  }

  @GetMapping("/{edad}")
  public List<Empleado> findByEdad(@PathVariable  int edad) {
		return empleadoService.findByEdad(edad);
  }

  @GetMapping("/{estado}")
  public List<Empleado> findByEstado(@PathVariable int estado) {
		return empleadoService.findByEstadoId(estado);
  }

  @GetMapping("/{sueldo}")
  public List<Empleado> findBySueldo(@PathVariable BigDecimal sueldo) {
		return empleadoService.findBySueldo(sueldo);
  }

  @GetMapping("/{id}")
  public Empleado findById(@PathVariable int id) {
		return empleadoService.findById(id);
  }
}