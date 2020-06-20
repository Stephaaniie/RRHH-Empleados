package ar.com.ada.api.empleado.empleado.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.empleado.empleado.entities.Empleado;
import ar.com.ada.api.empleado.empleado.models.request.InfoBasicaEmpleadoRequest;
import ar.com.ada.api.empleado.empleado.repos.EmpleadoRepository;

@Service
public class EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Autowired
    CategoriaService categoriaService;

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

	public List<Empleado> getEmpleadosPorNombre(String nombre) {
        List<Empleado> empleados = new ArrayList<>();

        Optional<List<Empleado>> eOptional = empleadoRepository.findByNombreEmpleado(nombre);

        if (eOptional.isPresent()) {
            empleados = eOptional.get();
        }
		return empleados;
	}

	public boolean operacionCheck(Object object) {
		return object == null;
	}

	public Empleado crearEmpleado(InfoBasicaEmpleadoRequest info, Empleado empleado) {
        empleado.setNombre(info.nombre);

        empleado.setEdad(info.edad);

        empleado.setSueldo(info.sueldo);

        empleado.setCategoria(categoriaService.busCategoriaPorId(info.categoriaId));
        
        empleado.setFechaAlta(new Date());
        
        empleado.setEstadoId(1);

        empleadoRepository.save(empleado);

		return empleado;
	}

	public boolean actualizarEmpleado(Empleado empleadoOrg, Empleado empleadoNuevo) {
        empleadoOrg.setNombre(empleadoNuevo.getNombre());

        empleadoOrg.setEdad(empleadoNuevo.getEdad());

        empleadoOrg.getCategoria().setCategoriaId(empleadoNuevo.getCategoria().getCategoriaId());

        empleadoOrg.setFechaAlta(empleadoNuevo.getFechaAlta()); 
        
        empleadoOrg.setFechaBaja(empleadoNuevo.getFechaBaja());

        empleadoRepository.save(empleadoOrg);
        return true;
	}

	public boolean actualizarSueldo(Empleado empleadoOrg, Empleado empleado) {
        empleadoOrg.setSueldo(empleado.getSueldo());

        empleadoRepository.save(empleadoOrg);

        return true;
	}

	public boolean actualizarEstado(Empleado empleadoOrg, int estado) {
        empleadoOrg.setEmpleadoId(estado);

        empleadoOrg.setFechaBaja(new Date());

        empleadoRepository.save(empleadoOrg);

        return true;
	}

	public boolean eliminanarEmpleado(Empleado empleado) {

        return actualizarEstado(empleado, 0);
	}

	public Empleado getEmpleadoEliminadoConId(int id) {
		return getEmpleadoPorId(id);
    }
    
    public List<Empleado> buscarEmpleadosPorCategoriaId(int categoriaId) {

        return empleadoRepository.findAllByCategoriaId(categoriaId);
    }
}