package com.example.demo.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.ProductoVendido;
import com.example.demo.model.VentaMensual;
import com.example.demo.repositories.EstadisticaDAO;
import com.example.demo.services.EstadisticaService;

@Service
public class EstadisticaServiceImpl implements EstadisticaService {

    private final EstadisticaDAO estadisticaDAO;

    public EstadisticaServiceImpl(EstadisticaDAO estadisticaDAO) {
        this.estadisticaDAO = estadisticaDAO;
    }

    @Override
    public List<ProductoVendido> getTopProductosVendidos(int limit) {
        return estadisticaDAO.getTopProductosVendidos(limit);
    }

    @Override
    public List<VentaMensual> getVentasMensuales(int monthsBack) {
        return estadisticaDAO.getVentasMensuales(monthsBack);
    }
}
