package ar.com.ada.api.empleado.empleado.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.empleado.empleado.entities.Categoria;
import ar.com.ada.api.empleado.empleado.entities.Empleado;
import ar.com.ada.api.empleado.empleado.models.request.InfoBasicaCategoriaRequest;
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

	public List<Categoria> listarCategorias() {

		return categoriaRepository.findAll();
	}

	public List<Categoria> buscarCategoriasPorDescripcion(String descripcion) {

        return categoriaRepository.findByDescripcionCategoria(descripcion);
    }
	
	public Categoria busCategoriaPorId(int categoriaId) {
        Categoria categoria = null;

        Optional<Categoria> cOptional = categoriaRepository.findById(categoriaId);

        if (cOptional.isPresent()) {
            categoria = cOptional.get();
        }
        return categoria;
    }

	public boolean operacionCheck(Object objeto) {
		return objeto == null;
	}

	public Categoria crearCategoria(InfoBasicaCategoriaRequest info, Categoria categoria) {

		if (existsById(busCategoriaPorId(categoria.getCategoriaId()))) {
			return null;
		}
		categoria.setDescripcion(info.descripcion);
		categoria.setCategoriaId(info.categoriaId);
		categoria.setSueldo(info.sueldo);
		categoriaRepository.save(categoria);
		return categoria;
	}

	private boolean existsById(Categoria categoria) {
		return categoria != null;
	}
}