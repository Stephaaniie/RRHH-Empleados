package ar.com.ada.api.empleado.empleado.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.empleado.empleado.entities.Categoria;
import ar.com.ada.api.empleado.empleado.entities.Empleado;
import ar.com.ada.api.empleado.empleado.repos.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;

	public List<Empleado> buscarTodosPorCategoria(int categoriaId) {

		List<Empleado> empleados = new ArrayList<>();

		Optional<Categoria> eOptional = categoriaRepository.findById(categoriaId);

		if(eOptional.isPresent()){
			empleados = (eOptional.get()).getEmpleados();
		}
		return empleados;
	}

	public void crearCategoria(Categoria categoria) {

		categoriaRepository.save(categoria);
	}

	public List<Categoria> listarCategorias() {

		return categoriaRepository.findAll();
	}
    
}