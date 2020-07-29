package ar.com.ada.api.empleado.empleado.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ar.com.ada.api.empleado.empleado.entities.Categoria;
import ar.com.ada.api.empleado.empleado.entities.Empleado;
import ar.com.ada.api.empleado.empleado.excepciones.ResourceNotFoundException;
import ar.com.ada.api.empleado.empleado.repos.CategoriaRepository;
import ar.com.ada.api.empleado.empleado.services.ICategoriaService;

@Service
public class CategoriaService implements ICategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository cRepository){
        this.categoriaRepository = cRepository;
    }

	@Override
	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}

	@Override
	public Categoria save(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	@Override
	public void delete(Categoria categoria) {
		this.deleteById(categoria.getCategoriaId());
	}

	@Override
	public void deleteById(int id) {
		if (!categoriaRepository.existsById(id)){
            throw new ResourceNotFoundException("model with id " + id + " not found");
        }
        categoriaRepository.deleteById(id);
	}


	@Override
	public Long count() {
		return categoriaRepository.count();
    }

	@Override
	public Categoria findById(int id) throws ResourceNotFoundException {
		return  categoriaRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("model with id " + id + " not found"));
	}

	public List<Empleado> calcularProximosSueldos() {
        List<Empleado> empleados = new ArrayList<>();

        this.findAll().stream().forEach(categoria -> {

            categoria.getEmpleados().stream().forEach(empleado -> {

                empleado.setSueldo(categoria.calcularSueldo(empleado));
                empleados.add(empleado);
            });

        });
        return empleados;
	}

	public List<Empleado> obtenerSueldosActuales() {
        List<Empleado> empleados = new ArrayList<>();

        this.findAll().stream().forEach(cat -> empleados.addAll(cat.getEmpleados()));

        return empleados;
    }

	public List<Categoria> obtenerCategoriasSinEmpleados() {
        return this.findAll().stream().filter(cat -> cat.getEmpleados().size() == 0).collect(Collectors.toList());
	}
	
	public List<Object> obtenerNombresCategorias() {
        return this.findAll().stream().map(categoria -> categoria.getNombre()).collect(Collectors.toList());
    }
}