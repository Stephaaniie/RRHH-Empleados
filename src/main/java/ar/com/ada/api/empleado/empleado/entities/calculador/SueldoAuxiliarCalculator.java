package ar.com.ada.api.empleado.empleado.entities.calculador;

import java.math.BigDecimal;

import ar.com.ada.api.empleado.empleado.entities.Empleado;

public class SueldoAuxiliarCalculator implements SueldoCalculator {

    @Override
    public BigDecimal calcularSueldo(Empleado empleado) {
        return empleado.getCategoria().getSueldo();
    }
}