package ar.com.ada.api.empleado.empleado.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "empleado")
public class Empleado {
   
    @Id
    @Column(name = "empleado_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empleadoId;
	
    private String nombre;

    @NaturalId
    private int dni;

    @NaturalId
    private int edad;

    @Column(name = "sueldo")
    private BigDecimal sueldo;

    @Column(name = "fecha_alta")
    private Date fechaAlta;
    
    @Column(name = "fecha_baja")
    private Date fechaBaja;

    @Column(name = "estado_id")
    private int estadoId;
    
    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "categoria_id")
	private Categoria categoria;

	public Empleado(int categoriaId, int edad, String nombre, BigDecimal sueldo2) {
		this.categoria.setCategoriaId(categoriaId);

		this.edad = edad;

		this.nombre = nombre;

		this.sueldo = sueldo2;
	}

	public int getEmpleadoId() {
		return empleadoId;
	}

	public void setEmpleadoId(int empleadoId) {
		this.empleadoId = empleadoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public BigDecimal getSueldo() {
		return sueldo;
	}

	public void setSueldo(BigDecimal sueldo) {
		this.sueldo = sueldo;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public int getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(int estadoId) {
		this.estadoId = estadoId;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
		this.categoria.getEmpleados().add(this);
	}

	public enum EmpleadoValidationType {
        EMPLEADO_OK, 
        EMPLEADO_DUPLICADO, 
        EMPLEADO_INVALIDO, 
        SUELDO_NULO, 
        EDAD_INVALIDA, 
        NOMBRE_INVALIDO,
        EMPLEADO_DATOS_INVALIDOS
    }

	@JsonIgnore
    public BigDecimal getVentasActuales() {
		Random randomGenerator = new Random();
		
        double venta = randomGenerator.nextDouble() * 10000 + 1;

		venta = ((long) (venta * 100)) / 100d;

        return new BigDecimal(venta);
    }
}