package ar.com.ada.api.empleado.empleado.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.com.ada.api.empleado.empleado.controllers.CategoriaController;
import ar.com.ada.api.empleado.empleado.entities.Categoria;
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
}