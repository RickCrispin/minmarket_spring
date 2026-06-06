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
            <c:choose>
                <c:when test="${empty ventasMensuales}">
                    <div class="history-empty-state">No hay datos.</div>
                </c:when>
                <c:otherwise>
                    <div class="time-series-chart">
                        <svg class="time-series-svg" viewBox="0 0 720 300" preserveAspectRatio="none" role="img" aria-label="Serie temporal de ventas mensuales">
                            <defs>
                                <linearGradient id="ventasAreaGradient" x1="0%" y1="0%" x2="0%" y2="100%">
                                    <stop offset="0%" stop-color="#667eea" stop-opacity="0.28"/>
                                    <stop offset="100%" stop-color="#667eea" stop-opacity="0.02"/>
                                </linearGradient>
                            </defs>
                            <line class="time-series-axis" x1="56" y1="24" x2="56" y2="252"/>
                            <line class="time-series-axis" x1="56" y1="252" x2="696" y2="252"/>
                            <text class="time-series-axis-title time-series-axis-title-y" x="22" y="150" transform="rotate(-90 22 150)">${ventasSerieYAxisLabel}</text>
                            <text class="time-series-axis-title time-series-axis-title-x" x="376" y="288">${ventasSerieXAxisLabel}</text>
                            <c:forEach var="tick" items="${ventasSerieYTicks}">
                                <line class="time-series-grid-line" x1="56" y1="${tick.y}" x2="696" y2="${tick.y}"/>
                                <text class="time-series-axis-tick time-series-axis-tick-left" x="18" y="${tick.y + 4}">${tick.label}</text>
                            </c:forEach>
                            <path class="time-series-area" d="${ventasSerieAreaPath}"/>
                            <path class="time-series-line" d="${ventasSeriePath}"/>
                            <c:forEach var="point" items="${ventasSeriePoints}">
                                <g>
                                    <circle class="time-series-dot" cx="${point.x}" cy="${point.y}" r="5"/>
                                    <text class="time-series-point-label" x="${point.x}" y="272">${point.label}</text>
                                </g>
                            </c:forEach>
                        </svg>
                        <div class="time-series-legend">
                            <c:forEach var="point" items="${ventasSeriePoints}">
                                <div class="time-series-item">
                                    <span class="time-series-item-label">${point.label}</span>
                                    <strong>
                                        S/. <fmt:formatNumber value="${point.total}" type="number" minFractionDigits="2" maxFractionDigits="2"/>
                                    </strong>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </section>

    <section class="charts-grid">
        <div class="card">
            <h3>Promedio diario (últimos meses)</h3>
            <c:choose>
                <c:when test="${empty promedioDiarioSeriePath}">
                    <div class="history-empty-state">No hay datos.</div>
                </c:when>
                <c:otherwise>
                    <div class="time-series-chart">
                        <svg class="time-series-svg" viewBox="0 0 720 300" preserveAspectRatio="none">
                            <defs>
                                <linearGradient id="promDiaArea" x1="0%" y1="0%" x2="0%" y2="100%">
                                    <stop offset="0%" stop-color="#10b981" stop-opacity="0.22"/>
                                    <stop offset="100%" stop-color="#10b981" stop-opacity="0.02"/>
                                </linearGradient>
                            </defs>
                            <line class="time-series-axis" x1="56" y1="24" x2="56" y2="252"/>
                            <line class="time-series-axis" x1="56" y1="252" x2="696" y2="252"/>
                            <c:forEach var="tick" items="${ventasSerieYTicks}">
                                <line class="time-series-grid-line" x1="56" y1="${tick.y}" x2="696" y2="${tick.y}"/>
                                <text class="time-series-axis-tick time-series-axis-tick-left" x="18" y="${tick.y + 4}">${tick.label}</text>
                            </c:forEach>
                            <path class="time-series-area" d="${promedioDiarioSerieAreaPath}"/>
                            <path class="time-series-line" d="${promedioDiarioSeriePath}"/>
                            <c:forEach var="point" items="${promedioDiarioSeriePoints}">
                                <g>
                                    <circle class="time-series-dot" cx="${point.x}" cy="${point.y}" r="5"/>
                                    <text class="time-series-point-label" x="${point.x}" y="272">${point.label}</text>
                                </g>
                            </c:forEach>
                        </svg>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="card">
            <h3>Ticket promedio mensual</h3>
            <c:choose>
                <c:when test="${empty ticketPromedioSeriePath}">
                    <div class="history-empty-state">No hay datos.</div>
                </c:when>
                <c:otherwise>
                    <div class="time-series-chart">
                        <svg class="time-series-svg" viewBox="0 0 720 300" preserveAspectRatio="none">
                            <defs>
                                <linearGradient id="ticketArea" x1="0%" y1="0%" x2="0%" y2="100%">
                                    <stop offset="0%" stop-color="#f59e0b" stop-opacity="0.22"/>
                                    <stop offset="100%" stop-color="#f59e0b" stop-opacity="0.02"/>
                                </linearGradient>
                            </defs>
                            <line class="time-series-axis" x1="56" y1="24" x2="56" y2="252"/>
                            <line class="time-series-axis" x1="56" y1="252" x2="696" y2="252"/>
                            <c:forEach var="tick" items="${ventasSerieYTicks}">
                                <line class="time-series-grid-line" x1="56" y1="${tick.y}" x2="696" y2="${tick.y}"/>
                                <text class="time-series-axis-tick time-series-axis-tick-left" x="18" y="${tick.y + 4}">${tick.label}</text>
                            </c:forEach>
                            <path class="time-series-area" d="${ticketPromedioSerieAreaPath}"/>
                            <path class="time-series-line" d="${ticketPromedioSeriePath}"/>
                            <c:forEach var="point" items="${ticketPromedioSeriePoints}">
                                <g>
                                    <circle class="time-series-dot" cx="${point.x}" cy="${point.y}" r="5"/>
                                    <text class="time-series-point-label" x="${point.x}" y="272">${point.label}</text>
                                </g>
                            </c:forEach>
                        </svg>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="card">
            <h3>Ingresos mensuales - categoría principal</h3>
            <c:choose>
                <c:when test="${empty categoriaSeriePath}">
                    <div class="history-empty-state">No hay datos.</div>
                </c:when>
                <c:otherwise>
                    <div class="time-series-chart">
                        <svg class="time-series-svg" viewBox="0 0 720 300" preserveAspectRatio="none">
                            <defs>
                                <linearGradient id="catArea" x1="0%" y1="0%" x2="0%" y2="100%">
                                    <stop offset="0%" stop-color="#6366f1" stop-opacity="0.22"/>
                                    <stop offset="100%" stop-color="#6366f1" stop-opacity="0.02"/>
                                </linearGradient>
                            </defs>
                            <line class="time-series-axis" x1="56" y1="24" x2="56" y2="252"/>
                            <line class="time-series-axis" x1="56" y1="252" x2="696" y2="252"/>
                            <c:forEach var="tick" items="${ventasSerieYTicks}">
                                <line class="time-series-grid-line" x1="56" y1="${tick.y}" x2="696" y2="${tick.y}"/>
                                <text class="time-series-axis-tick time-series-axis-tick-left" x="18" y="${tick.y + 4}">${tick.label}</text>
                            </c:forEach>
                            <path class="time-series-area" d="${categoriaSerieAreaPath}"/>
                            <path class="time-series-line" d="${categoriaSeriePath}"/>
                            <c:forEach var="point" items="${categoriaSeriePoints}">
                                <g>
                                    <circle class="time-series-dot" cx="${point.x}" cy="${point.y}" r="5"/>
                                    <text class="time-series-point-label" x="${point.x}" y="272">${point.label}</text>
                                </g>
                            </c:forEach>
                        </svg>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </section>
</div>
