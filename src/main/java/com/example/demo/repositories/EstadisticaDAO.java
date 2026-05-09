package com.example.demo.repositories;

import java.util.List;
import com.example.demo.model.ProductoVendido;
import com.example.demo.model.VentaMensual;

public interface EstadisticaDAO {
    List<ProductoVendido> getTopProductosVendidos(int limit);
    List<VentaMensual> getVentasMensuales(int monthsBack); // last N months
}
