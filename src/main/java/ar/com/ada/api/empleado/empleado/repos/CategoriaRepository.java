package ar.com.ada.api.empleado.empleado.repos;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.empleado.empleado.entities.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository <Categoria, Integer>{

    Categoria findByCategoriaId(int categoriaId);

    @Query("select c from Categoria c where c.descripcion = ?1")
	List<Categoria> findByDescripcionCategoria(String descripcion);

    Categoria findBySueldo(BigDecimal sueldo);

}