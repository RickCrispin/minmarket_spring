<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="es_PE"/>
<jsp:include page="/WEB-INF/views/components/navbara.jsp" />

<div class="container">
    <section class="hero">
        <h2>Estadísticas</h2>
        <p>Resumen de los productos más vendidos y las ventas mensuales.</p>
    </section>

    <section class="stats-grid">
        <div class="card">
            <h3>Productos más vendidos</h3>
            <table class="table">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Producto</th>
                        <th>Cantidad</th>
                        <th>Ingresos</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="p" items="${topProductos}" varStatus="st">
                        <tr>
                            <td>${st.index + 1}</td>
                            <td>${p.nombre}</td>
                            <td>${p.totalCantidad}</td>
                            <td>
                                S/. <fmt:formatNumber value="${p.totalIngresos}" type="number" minFractionDigits="2" maxFractionDigits="2"/>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty topProductos}">
                        <tr><td colspan="4">No hay datos.</td></tr>
                    </c:if>
                </tbody>
            </table>
        </div>

        <div class="card">
            <h3>Ventas mensuales (últimos meses)</h3>
            <div class="bar-chart">
                <c:forEach var="v" items="${ventasMensuales}">
                    <div class="bar">
                        <div class="bar-head">
                            <div class="bar-label">${v.mes}/${v.ano}</div>
                            <div class="bar-value">
                                S/. <fmt:formatNumber value="${v.total}" type="number" minFractionDigits="2" maxFractionDigits="2"/>
                            </div>
                        </div>
                        <div class="bar-track">
                            <div class="bar-fill" style="width: ${ (v.total / maxVentaTotal) * 100 }%;"></div>
                        </div>
                    </div>
                </c:forEach>
                <c:if test="${empty ventasMensuales}">
                    <div class="history-empty">No hay datos.</div>
                </c:if>
            </div>
        </div>
    </section>
</div>
