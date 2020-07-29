package ar.com.ada.api.empleado.empleado.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.com.ada.api.empleado.empleado.entities.Empleado;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Empleado save(Empleado entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Empleado entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long count() {
		return empleadoRepository.count();
    }
    
}