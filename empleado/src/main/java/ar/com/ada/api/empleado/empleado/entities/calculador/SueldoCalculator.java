package ar.com.ada.api.empleado.empleado.entities.calculador;

import java.math.BigDecimal;

import ar.com.ada.api.empleado.empleado.entities.Empleado;

public interface SueldoCalculator {
    
    BigDecimal calcularSueldo(Empleado empleado);
}