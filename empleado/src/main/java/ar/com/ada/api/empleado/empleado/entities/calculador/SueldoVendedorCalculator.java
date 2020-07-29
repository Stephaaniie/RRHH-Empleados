package ar.com.ada.api.empleado.empleado.entities.calculador;

import java.math.BigDecimal;
import java.math.RoundingMode;

import ar.com.ada.api.empleado.empleado.entities.Empleado;

public class SueldoVendedorCalculator implements SueldoCalculator {

    private final BigDecimal PORCENTAJE = new BigDecimal(0.10);

    @Override
    public BigDecimal calcularSueldo(Empleado empleado) {
        return empleado.getCategoria().getSueldo().add(empleado.getVentasActuales().multiply(PORCENTAJE)).setScale(2, RoundingMode.HALF_EVEN);
    }
}