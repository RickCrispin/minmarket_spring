<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Boleta - Venta #${venta.id} - MiniMarket</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        body{ background:#f7fafc; margin:0; }
        .receipt { max-width:900px; margin:24px auto; background:#fff; padding:20px; border-radius:8px; box-shadow:0 6px 18px rgba(0,0,0,0.06);} 
        .receipt header{ display:flex; justify-content:space-between; align-items:center; margin-bottom:18px; }
        .receipt h2{ margin:0; }
        .receipt .meta{ text-align:right; color:#555; }
        .receipt table{ width:100%; border-collapse:collapse; margin-top:12px; }
        .receipt th, .receipt td{ padding:12px 8px; border-bottom:1px solid #eee; text-align:left; }
        .receipt tfoot td{ font-weight:700; font-size:1.05rem; }
        .receipt .actions{ margin-top:18px; display:flex; gap:8px; }
        .btn-print{ background:#2b6cb0; color:#fff; padding:10px 14px; border-radius:6px; border:0; cursor:pointer; }
        .btn-back{ background:#edf2f7; color:#1a202c; padding:10px 14px; border-radius:6px; border:0; cursor:pointer; }

        @media print {
            body { background:#fff; }
            .receipt {
                max-width:100%;
                margin:0;
                border-radius:0;
                box-shadow:none;
                padding:0;
            }
            .actions { display:none; }
        }
    </style>
</head>
<body>
    <main>
        <section class="receipt">
                <header>
                    <div>
                        <h2>Boleta de Venta</h2>
                        <div>MiniMarket</div>
                    </div>
                    <div class="meta">
                        <div>Boleta: <strong>#${venta.id}</strong></div>
                        <div>Fecha: ${venta.fechaVenta}</div>
                        <div>Vendedor: ${venta.usuario.nombres}</div>
                        <div>Estado: ${venta.estado}</div>
                    </div>
                </header>

                <table>
                    <thead>
                        <tr>
                            <th>Producto</th>
                            <th>Precio unitario</th>
                            <th>Cantidad</th>
                            <th>Subtotal</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="d" items="${venta.detalles}">
                            <tr>
                                <td>${d.producto.nombre}</td>
                                <td>S/${d.precioUnitario}</td>
                                <td>${d.cantidad}</td>
                                <td>S/${d.subTotal}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="3" style="text-align:right">Total</td>
                            <td>S/${venta.total}</td>
                        </tr>
                    </tfoot>
                </table>

                <div class="actions">
                    <button class="btn-print" onclick="window.print()">Imprimir</button>
                    <a href="${pageContext.request.contextPath}/ventas"><button class="btn-back">Volver a ventas</button></a>
                </div>
            </section>
    </main>
</body>
</html>
