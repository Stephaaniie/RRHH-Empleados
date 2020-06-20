package ar.com.ada.api.empleado.empleado.models.request;

import java.math.BigDecimal;

public class InfoBasicaEmpleadoRequest {
    public String nombre;

    public int edad;

    public BigDecimal sueldo;
    
    public int categoriaId;
}