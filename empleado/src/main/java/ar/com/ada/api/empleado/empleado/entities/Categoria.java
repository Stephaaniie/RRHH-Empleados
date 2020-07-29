package ar.com.ada.api.empleado.empleado.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import ar.com.ada.api.empleado.empleado.entities.calculador.SueldoAdministrativoCalculartor;
import ar.com.ada.api.empleado.empleado.entities.calculador.SueldoAuxiliarCalculator;
import ar.com.ada.api.empleado.empleado.entities.calculador.SueldoCalculator;
import ar.com.ada.api.empleado.empleado.entities.calculador.SueldoVendedorCalculator;

@Entity
@Table(name = "categoria")
public class Categoria {
    
    private static final String AUXILIAR = "auxiliar";

	private static final String ADMINISTRATIVO = "administrativo";

	private static final String VENDEDOR = "vendedor";

	@Id
    @Column(name = "categoria_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoriaId;

    @Column(name = "sueldo_base")
    private BigDecimal sueldo;

    @Column(name = "descripcion")
	private String descripcion;
	
	private String nombre;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnore
	private List<Empleado> empleados = new ArrayList<>();
	
	@JsonIgnore
    @Transient
    private SueldoCalculator sueldoStrategy;
	
	public Categoria(int categoriaId2, String descripcion2, BigDecimal sueldo2) {
		this.categoriaId = categoriaId2;

		this.descripcion = descripcion2;

		this.sueldo = sueldo2;
	}

	public int getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(int categoriaId) {
		this.categoriaId = categoriaId;
	}

	public BigDecimal getSueldo() {
		return sueldo;
	}

	public void setSueldo(BigDecimal sueldo) {
		this.sueldo = sueldo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public BigDecimal calcularSueldo(Empleado empleado) {
        return this.getSueldoStrategy().calcularSueldo(empleado);
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
        this.nombre = nombre;
        switch (this.nombre) {
            case VENDEDOR:
                this.setSueldoStrategy(new SueldoVendedorCalculator());
                break;
            case ADMINISTRATIVO:
                this.setSueldoStrategy(new SueldoAdministrativoCalculartor());
                break;
            case AUXILIAR:
                this.setSueldoStrategy(new SueldoAuxiliarCalculator());
                break;
            default:
                this.setSueldoStrategy(new SueldoAdministrativoCalculartor());
                break;
        }
    }

	public SueldoCalculator getSueldoStrategy() {
		return sueldoStrategy;
	}

	public void setSueldoStrategy(SueldoCalculator sueldoStrategy) {
		this.sueldoStrategy = sueldoStrategy;
	}  
	
}