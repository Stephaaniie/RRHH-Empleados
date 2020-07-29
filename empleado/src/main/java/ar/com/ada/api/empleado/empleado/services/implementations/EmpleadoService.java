package ar.com.ada.api.empleado.empleado.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.com.ada.api.empleado.empleado.entities.Empleado;
import ar.com.ada.api.empleado.empleado.excepciones.ResourceNotFoundException;
import ar.com.ada.api.empleado.empleado.repos.EmpleadoRepository;
import ar.com.ada.api.empleado.empleado.services.IEmpleadoService;

@Service
public class EmpleadoService implements IEmpleadoService{

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository eRepository){
        this.empleadoRepository = eRepository;
    }

	@Override
	public List<Empleado> findAll() {
		return empleadoRepository.findAll();
	}

	@Override
	public Empleado save(Empleado empleado) {
		return empleadoRepository.save(empleado);
	}

	@Override
	public void delete(Empleado empleado) {
		this.deleteById(empleado.getEstadoId());
	}

	@Override
	public void deleteById(int id) {
		if (!empleadoRepository.existsById(id)){
            throw new ResourceNotFoundException("model with id " + id + " not found");
        }
        empleadoRepository.deleteById(id);
	}


	@Override
	public Long count() {
		return empleadoRepository.count();
    }

	@Override
	public Empleado findById(int id) throws ResourceNotFoundException {
        return  empleadoRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("model with id " + id + " not found"));
	}
    
}