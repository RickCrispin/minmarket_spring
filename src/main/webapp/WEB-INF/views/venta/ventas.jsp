<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ventas - MiniMarket</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        .sale-status{ margin: 0 0 16px; padding: 12px 14px; border-radius: 8px; background: #eef2ff; }
        .sale-badge{ display:inline-block; padding:4px 10px; border-radius:999px; color:#fff; font-weight:600; font-size:0.85rem; }
        .sale-pending{ background:#d69e2e; }
        .sale-concreted{ background:#2f855a; }
        .alert-error{ margin: 12px 0; padding: 10px 12px; border-radius: 8px; background: #fff5f5; color: #9b2c2c; border: 1px solid #feb2b2; }
        .empty-state{ padding: 14px; border: 1px dashed #cbd5e1; border-radius: 8px; background: #fff; }
        .sale-actions{ display:flex; gap:10px; flex-wrap:wrap; margin-top:12px; }
        .sale-actions form{ display:inline; }
        .detail-list{ margin-top:12px; padding-left: 18px; }
        .detail-list li{ display:flex; align-items:center; justify-content:space-between; gap:12px; margin-bottom:10px; }
        .detail-actions form{ display:inline; }
        .detail-actions button{ border:0; border-radius:999px; padding:8px 12px; cursor:pointer; background:linear-gradient(90deg,#c53030,#e53e3e); color:#fff; font-weight:600; }
        .product-form{ margin-top: 12px; display:flex; gap:8px; align-items:center; flex-wrap:wrap; }
        .product-form input[type="number"]{ width: 90px; }
        .product-meta{ font-size: 0.9em; color: #666; }
    </style>
</head>

<body>
    <c:set var="activePage" value="ventas" />
    <c:set var="headerTitle" value="Ventas" />
    <jsp:include page="/WEB-INF/views/components/navbara.jsp" />
    <main>
        <div class="container sales">
            <div class="products-col">
                <h2>Productos</h2>
                <c:if test="${not empty detalleError}">
                    <div class="alert-error">${detalleError}</div>
                </c:if>
                <div class="grid">
                    <c:choose>
                        <c:when test="${not empty productos}">
                            <c:forEach var="producto" items="${productos}">
                                <div class="card">
                                    <h3>${producto.nombre}</h3>
                                    <p>S/${producto.precio}</p>
                                    <p class="product-meta">Categoría: ${producto.categoria.nombre}</p>
                                    <c:choose>
                                        <c:when test="${not empty ventaActiva}">
                                            <form class="product-form" action="${pageContext.request.contextPath}/ventas/detalle" method="post">
                                                <input type="hidden" name="idProducto" value="${producto.id}">
                                                <input type="number" name="cantidad" min="1" value="1" required>
                                                <button type="submit">Agregar</button>
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <button type="button" disabled>Inicia una venta para agregar</button>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <p>No hay productos activos para mostrar.</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="sidebar">
                <h3>Venta</h3>
                <c:choose>
                    <c:when test="${not empty ventaActiva}">
                        <div class="sale-status">
                            <div><strong>Venta #${ventaActiva.id}</strong></div>
                            <div>Estado: <span class="sale-badge ${ventaActiva.estado == 'Concretado' ? 'sale-concreted' : 'sale-pending'}">${ventaActiva.estado}</span></div>
                            <div>Fecha: ${ventaActiva.fechaVenta}</div>
                        </div>

                        <h4>Detalles</h4>
                        <c:choose>
                            <c:when test="${not empty detallesVenta}">
                                <ul class="detail-list">
                                    <c:forEach var="detalle" items="${detallesVenta}">
                                        <li>
                                            <div>
                                                <strong>${detalle.producto.nombre}</strong>
                                                <div class="product-meta">Cantidad: ${detalle.cantidad} | S/${detalle.subTotal}</div>
                                            </div>
                                            <div class="detail-actions">
                                                <form action="${pageContext.request.contextPath}/ventas/detalle/eliminar" method="post">
                                                    <input type="hidden" name="idDetalle" value="${detalle.id}">
                                                    <button type="submit">Quitar</button>
                                                </form>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </c:when>
                            <c:otherwise>
                                <div class="empty-state">Todavía no hay detalles registrados.</div>
                            </c:otherwise>
                        </c:choose>

                        <hr>
                        <p><strong>Total: S/${totalVenta}</strong></p>
                        <div class="sale-actions">
                            <form action="${pageContext.request.contextPath}/ventas/confirmar" method="post">
                                <button type="submit" class="btn-primary">Confirmar venta</button>
                            </form>
                            <form action="${pageContext.request.contextPath}/ventas/cancelar" method="post">
                                <button type="submit" class="btn-primary" style="background:linear-gradient(90deg,#c53030,#e53e3e); box-shadow:0 8px 20px rgba(229,62,62,0.12);">Cancelar venta</button>
                            </form>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="empty-state">
                            <p>No hay una venta activa.</p>
                            <form action="${pageContext.request.contextPath}/ventas/iniciar" method="post">
                                <button type="submit" class="btn-primary">Iniciar venta</button>
                            </form>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </main>
</body>
</html>