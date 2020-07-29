package ar.com.ada.api.empleado.empleado.repos;


import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.empleado.empleado.entities.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository <Categoria, Integer>{

    Categoria findByCategoriaId(int categoriaId);

    Categoria findBySueldo(BigDecimal sueldo);
}