package ar.com.ada.api.empleado.empleado.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.com.ada.api.empleado.empleado.controllers.CategoriaController;
import ar.com.ada.api.empleado.empleado.entities.Categoria;
import ar.com.ada.api.empleado.empleado.repos.CategoriaRepository;
import ar.com.ada.api.empleado.empleado.services.ICategoriaService;

@Service
public class CategoriaService implements ICategoriaService {

    private final CategoriaRepository CategoriaRepository;

    public CategoriaService(CategoriaRepository cRepository){
        this.CategoriaRepository = cRepository;
    }

	@Override
	public List<Categoria> findAll() {
		return CategoriaRepository.findAll();
	}

	@Override
	public Categoria save(Categoria entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Categoria entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
    }
}