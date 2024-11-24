package com.example.rest.service;

import com.example.rest.model.Cuenta;
import com.example.rest.model.Movimiento;
import com.example.rest.model.ReporteEstadoCuenta;
import com.example.rest.repository.CuentaRepository;
import com.example.rest.repository.MovimientoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ReporteServiceTest {

    @Mock
    private CuentaRepository cuentaRepository;

    @Mock
    private MovimientoRepository movimientoRepository;

    @InjectMocks
    private ReporteService reporteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generarReporte_withValidData_returnsReporteEstadoCuenta() {
        Long clienteId = 1L;
        LocalDate fechaInicio = LocalDate.of(2023, 1, 1);
        LocalDate fechaFin = LocalDate.of(2023, 12, 31);

        Cuenta cuenta = new Cuenta();
        cuenta.setId(1L);
        List<Cuenta> cuentas = Collections.singletonList(cuenta);

        Movimiento movimiento = new Movimiento();
        movimiento.setId(1L);
        List<Movimiento> movimientos = Collections.singletonList(movimiento);

        when(cuentaRepository.findAll()).thenReturn(cuentas);
        when(movimientoRepository.findAllByCuentaIdAndFechaBetween(1L, fechaInicio, fechaFin)).thenReturn(movimientos);

        ReporteEstadoCuenta reporte = reporteService.generarReporte(clienteId, fechaInicio, fechaFin);

        assertEquals(1, reporte.getCuentasReportes().size());
        assertEquals(1, reporte.getCuentasReportes().get(0).getMovimientos().size());
    }

    @Test
    void generarReporte_withNoCuentas_returnsEmptyReporteEstadoCuenta() {
        Long clienteId = 1L;
        LocalDate fechaInicio = LocalDate.of(2023, 1, 1);
        LocalDate fechaFin = LocalDate.of(2023, 12, 31);

        when(cuentaRepository.findAll()).thenReturn(Collections.emptyList());

        ReporteEstadoCuenta reporte = reporteService.generarReporte(clienteId, fechaInicio, fechaFin);

        assertEquals(0, reporte.getCuentasReportes().size());
    }

    @Test
    void generarReporte_withNoMovimientos_returnsReporteEstadoCuentaWithEmptyMovimientos() {
        Long clienteId = 1L;
        LocalDate fechaInicio = LocalDate.of(2023, 1, 1);
        LocalDate fechaFin = LocalDate.of(2023, 12, 31);

        Cuenta cuenta = new Cuenta();
        cuenta.setId(1L);
        List<Cuenta> cuentas = Collections.singletonList(cuenta);

        when(cuentaRepository.findAll()).thenReturn(cuentas);
        when(movimientoRepository.findAllByCuentaIdAndFechaBetween(1L, fechaInicio, fechaFin)).thenReturn(Collections.emptyList());

        ReporteEstadoCuenta reporte = reporteService.generarReporte(clienteId, fechaInicio, fechaFin);

        assertEquals(1, reporte.getCuentasReportes().size());
        assertEquals(0, reporte.getCuentasReportes().get(0).getMovimientos().size());
    }
}