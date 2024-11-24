package com.example.rest.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
public class ReporteEstadoCuenta {

    private List<CuentaReporte> cuentasReportes;


    public void setCuentasReportes(List<CuentaReporte> cuentasReportes) {
        this.cuentasReportes = cuentasReportes;
    }

    public ReporteEstadoCuenta() {
        this.cuentasReportes = new ArrayList<>();
    }

    public void addCuentaReporte(CuentaReporte cuentaReporte) {
        this.cuentasReportes.add(cuentaReporte);
    }
}
