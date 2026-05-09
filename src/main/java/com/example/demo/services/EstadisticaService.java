package com.example.demo.services;

import java.util.List;
import com.example.demo.model.ProductoVendido;
import com.example.demo.model.VentaMensual;

public interface EstadisticaService {
    List<ProductoVendido> getTopProductosVendidos(int limit);
    List<VentaMensual> getVentasMensuales(int monthsBack);
}
