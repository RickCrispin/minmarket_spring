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

        int stockDisponible = producto.getStock() != null ? producto.getStock() : 0;
        if (stockDisponible <= 0) {
            throw new IllegalArgumentException("Ya no existe stock del producto");
        }

        // Validación: no agregar más de stock disponible considerando los detalles ya agregados a la venta
        List<DetalleVenta> detallesExistentes = detalleVentaDAO.getDetallesByVentaId(idVenta);
        int cantidadExistente = detallesExistentes.stream()
                .filter(d -> d.getIdProducto() != null && d.getIdProducto().equals(idProducto))
                .mapToInt(d -> d.getCantidad() != null ? d.getCantidad() : 0)
                .sum();

        if (cantidadExistente + cantidad > stockDisponible) {
            throw new IllegalArgumentException("Cantidad solicitada excede el stock disponible");
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
        ventaDAO.updateTotal(idVenta, total);

        // Actualizar stock de cada producto en la venta
        List<DetalleVenta> detalles = detalleVentaDAO.getDetallesByVentaId(idVenta);
        for (DetalleVenta detalle : detalles) {
            Integer idProducto = detalle.getIdProducto();
            Integer cantidad = detalle.getCantidad() != null ? detalle.getCantidad() : 0;
            Producto producto = productoService.getProductoById(idProducto);
            if (producto == null) {
                throw new IllegalStateException("Producto en detalle no existe: " + idProducto);
            }

            int stockActual = producto.getStock() != null ? producto.getStock() : 0;
            int nuevoStock = stockActual - cantidad;
            if (nuevoStock < 0) {
                throw new IllegalStateException("Stock insuficiente para el producto id=" + idProducto);
            }

            producto.setStock(nuevoStock);
            if (nuevoStock == 0) {
                // marcar como inactivo
                producto.setEstado("Inactivo");
                productoService.updateProducto(producto);
            } else {
                productoService.updateProducto(producto);
            }
        }

        ventaDAO.updateEstado(idVenta, "Concretado");
    }
}
