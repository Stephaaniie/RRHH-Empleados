package ar.com.ada.api.empleado.empleado.repos;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.empleado.empleado.entities.Categoria;
import ar.com.ada.api.empleado.empleado.entities.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository <Empleado,Integer> {

    Empleado findByEmpleadoId(int empleadoId);

    @Query("select e from Empleado e where e.categoria.categoriaId = :categoriaId")
    List<Empleado> findAllByCategoriaId(@Param("categoriaId") int categoriaId);
  
    @Query("select e from Empleado e where e.nombre = ?1")
    Optional<List<Empleado>> findByNombreEmpleado(String nombre);

    List<Empleado> findByNombre (String nombre);

    Empleado findByDni (int id);

    List<Empleado> findByCategoria(Categoria categoria);

    List<Empleado> findByEstadoId(int estado);
   
    List<Empleado> findBySueldo(BigDecimal sueldo);

    List<Empleado> findByEdad(int edad);
}