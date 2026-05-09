<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Historial de Ventas - MiniMarket</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <c:set var="activePage" value="gestor-historial" />
    <c:set var="headerTitle" value="Historial de Ventas" />
    <jsp:include page="/WEB-INF/views/components/navbara.jsp" />
    <main>
        <div class="container history-page">
            <div class="history-hero">
                <div>
                    <span class="eyebrow">Ventas registradas</span>
                    <h2>Historial de Ventas</h2>
                    <p>Revisa cada venta con sus productos, total y estado. Las ventas pendientes permiten continuar, finalizar o cancelar desde la misma tarjeta.</p>
                </div>
            </div>

            <c:if test="${not empty detalleError}">
                <div class="alert-error">${detalleError}</div>
            </c:if>

            <c:choose>
                <c:when test="${not empty ventas}">
                    <section class="history-grid">
                        <c:forEach var="venta" items="${ventas}">
                            <article class="history-card">
                                <div class="history-card-header">
                                    <div>
                                        <span class="history-label">Venta #${venta.id}</span>
                                        <h3>S/${venta.total}</h3>
                                    </div>
                                    <span class="sale-badge ${venta.estado == 'Pendiente' ? 'sale-pending' : 'sale-concreted'}">
                                        ${venta.estado}
                                    </span>
                                </div>

                                <div class="history-meta">
                                    <div>
                                        <span>Usuario</span>
                                        <strong>${venta.idUsuario}</strong>
                                    </div>
                                    <div>
                                        <span>Fecha</span>
                                        <strong>${venta.fechaVenta}</strong>
                                    </div>
                                </div>

                                <details class="history-details" open>
                                    <summary>Productos incluidos</summary>
                                    <c:choose>
                                        <c:when test="${not empty venta.detalles}">
                                            <ul class="history-items">
                                                <c:forEach var="d" items="${venta.detalles}">
                                                    <li>
                                                        <div>
                                                            <strong>${d.producto.nombre}</strong>
                                                            <span>Cantidad: ${d.cantidad}</span>
                                                        </div>
                                                        <span>S/${d.subTotal}</span>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </c:when>
                                        <c:otherwise>
                                            <p class="history-empty">No hay detalles registrados.</p>
                                        </c:otherwise>
                                    </c:choose>
                                </details>

                                <div class="history-actions">
                                    <c:choose>
                                        <c:when test="${venta.estado == 'Pendiente'}">
                                            <form action="${pageContext.request.contextPath}/ventas/continuar" method="post">
                                                <input type="hidden" name="idVenta" value="${venta.id}" />
                                                <button type="submit" class="btn-primary history-btn history-btn-primary">Continuar</button>
                                            </form>
                                            <form action="${pageContext.request.contextPath}/ventas/confirmar/${venta.id}" method="post">
                                                <button type="submit" class="btn-primary history-btn history-btn-success">Finalizar</button>
                                            </form>
                                            <form action="${pageContext.request.contextPath}/ventas/cancelar/${venta.id}" method="post">
                                                <button type="submit" class="btn-primary history-btn history-btn-danger">Cancelar</button>
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="history-note">Venta cerrada. Solo disponible para consulta.</span>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </article>
                        </c:forEach>
                    </section>
                </c:when>
                <c:otherwise>
                    <div class="history-empty-state">
                        <h3>No hay ventas registradas</h3>
                        <p>Cuando se cree una venta, aparecerá aquí con su detalle y sus acciones disponibles.</p>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </main>
</body>
</html>