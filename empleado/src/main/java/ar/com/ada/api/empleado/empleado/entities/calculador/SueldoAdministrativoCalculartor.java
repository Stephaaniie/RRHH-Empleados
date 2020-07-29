package ar.com.ada.api.empleado.empleado.entities.calculador;

import java.math.BigDecimal;

import ar.com.ada.api.empleado.empleado.entities.Empleado;

public class SueldoAdministrativoCalculartor implements SueldoCalculator {

    @Override
    public BigDecimal calcularSueldo(Empleado empleado) {

        BigDecimal sueldoActual = empleado.getSueldo();
        if (sueldoActual.compareTo(empleado.getCategoria().getSueldo())  < 0 )
            return empleado.getCategoria().getSueldo();

        return sueldoActual;
    }

}