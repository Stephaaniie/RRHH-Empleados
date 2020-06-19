package ar.com.ada.api.empleado.empleado.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.empleado.empleado.entities.Empleado;
import ar.com.ada.api.empleado.empleado.repos.EmpleadoRepository;

@Service
public class EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    public void crearEmpleado(Empleado empleado) {

        empleadoRepository.save(empleado);
    }

    public List<Empleado> listaEmpleados() {

        return empleadoRepository.findAll();
    }
    
    public Empleado getEmpleadoPorId(int id) {
        Empleado empleado = null;

        Optional<Empleado> eOptional = empleadoRepository.findById(id);

        if(eOptional.isPresent()){
            empleado = eOptional.get();
        }
        return empleado;
    }
}