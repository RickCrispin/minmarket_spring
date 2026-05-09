package com.example.demo.services.impl;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.model.DetalleVenta;
import com.example.demo.model.Producto;
import com.example.demo.model.Venta;
import com.example.demo.repositories.DetalleVentaDAO;
import com.example.demo.repositories.VentaDAO;
import com.example.demo.services.ProductoService;
import com.example.demo.services.VentaService;

@Service
public class VentaServiceImpl implements VentaService {
    private final VentaDAO ventaDAO;
    private final DetalleVentaDAO detalleVentaDAO;
    private final ProductoService productoService;

    public VentaServiceImpl(VentaDAO ventaDAO, DetalleVentaDAO detalleVentaDAO, ProductoService productoService) {
        this.ventaDAO = ventaDAO;
        this.detalleVentaDAO = detalleVentaDAO;
        this.productoService = productoService;
    }

    @Override
    public Integer iniciarVenta(Integer idUsuario) {
        return ventaDAO.createVenta(idUsuario);
    }

    @Override
    public void agregarDetalle(Integer idVenta, Integer idProducto, Integer cantidad) {
        if (cantidad == null || cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }

        Producto producto = productoService.getProductoById(idProducto);
        if (producto == null) {
            throw new IllegalArgumentException("El producto no existe");
        }

        DetalleVenta detalleVenta = new DetalleVenta();
        detalleVenta.setIdVenta(idVenta);
        detalleVenta.setIdProducto(idProducto);
        detalleVenta.setCantidad(cantidad);
        detalleVenta.setPrecioUnitario(producto.getPrecio());
        // El subtotal es columna generada en la base (cantidad * precio_unitario),
        // pero establecemos también en el objeto por consistencia
        detalleVenta.setSubTotal(producto.getPrecio() * cantidad);
        detalleVentaDAO.addDetalleVenta(detalleVenta);
    }

    @Override
    public Venta getVentaConDetalles(Integer idVenta) {
        Venta venta = ventaDAO.getVentaById(idVenta);
        if (venta == null) {
            return null;
        }
        venta.setDetalles(detalleVentaDAO.getDetallesByVentaId(idVenta));
        return venta;
    }

    @Override
    public Double calcularTotal(Integer idVenta) {
        List<DetalleVenta> detalles = detalleVentaDAO.getDetallesByVentaId(idVenta);
        double total = detalles.stream()
                .mapToDouble(detalle -> detalle.getSubTotal() != null
                        ? detalle.getSubTotal()
                        : (detalle.getPrecioUnitario() * detalle.getCantidad()))
                .sum();
        ventaDAO.updateTotal(idVenta, total);
        return total;
    }

    @Override
    @Transactional
    public void confirmarVenta(Integer idVenta) {
        Double total = calcularTotal(idVenta);
        // No permitir confirmar ventas sin detalles o con total 0
        List<DetalleVenta> detalles = detalleVentaDAO.getDetallesByVentaId(idVenta);
        if (detalles == null || detalles.isEmpty() || total == null || total <= 0.0) {
            throw new IllegalStateException("No se puede confirmar una venta sin productos o con total 0");
        }

        ventaDAO.updateTotal(idVenta, total);
        ventaDAO.updateEstado(idVenta, "Concretado");
    }

    @Override
    public void cancelarVenta(Integer idVenta) {
        Venta venta = ventaDAO.getVentaById(idVenta);
        if (venta == null) {
            throw new IllegalArgumentException("La venta no existe");
        }
        if ("Concretado".equalsIgnoreCase(venta.getEstado())) {
            throw new IllegalStateException("No se puede cancelar una venta ya concretada");
        }

        // Eliminamos la venta; las filas de detalle se eliminan por la FK con cascade
        ventaDAO.deleteVenta(idVenta);
    }

    @Override
    public java.util.List<Venta> getAllVentas() {
        return ventaDAO.getAllVentas();
    }
}
