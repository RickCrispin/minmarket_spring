<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Principal - MiniMarket</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <c:set var="activePage" value="principal" />
    <c:set var="headerTitle" value="Principal" />
    <jsp:include page="/WEB-INF/views/components/navbara.jsp" />

    <main class="container principal-page">
        <section class="hero-panel principal-hero">
            <div class="hero-copy">
                <span class="eyebrow">MiniMarket</span>
                <h2>Centro de operaciones para vender, administrar y analizar.</h2>
                <p>
                    Mantén el ritmo del negocio con accesos directos a ventas, historial, estadísticas
                    y gestión de catálogo, usuarios y categorías.
                </p>
                <div class="hero-actions">
                    <a class="btn-primary" href="${pageContext.request.contextPath}/ventas">Ir a ventas</a>
                    <a class="btn-ghost" href="${pageContext.request.contextPath}/ventas/historial">Historial</a>
                    <a class="btn-ghost" href="${pageContext.request.contextPath}/estadisticas">Estadísticas</a>
                </div>
                <div class="hero-tags">
                    <span>Ventas</span>
                    <span>Historial</span>
                    <span>Estadísticas</span>
                    <span>Inventario</span>
                </div>
            </div>
            <aside class="hero-card quick-card">
                <h3>Accesos clave</h3>
                <p>Atajos listos para las tareas frecuentes del día.</p>
                <div class="quick-grid">
                    <a class="quick-link" href="${pageContext.request.contextPath}/producto">
                        <span>Productos</span>
                        <small>Inventario activo</small>
                    </a>
                    <a class="quick-link" href="${pageContext.request.contextPath}/categoria">
                        <span>Categorías</span>
                        <small>Organiza tu catálogo</small>
                    </a>
                    <a class="quick-link" href="${pageContext.request.contextPath}/usuario">
                        <span>Usuarios</span>
                        <small>Gestión de cuentas</small>
                    </a>
                    <a class="quick-link" href="${pageContext.request.contextPath}/publicidad">
                        <span>Publicidad</span>
                        <small>Contenido visible</small>
                    </a>
                </div>
            </aside>
        </section>

        <section class="principal-section">
            <div class="section-title">
                <h3>Operacion diaria</h3>
                <p>Ingresa directo a lo que necesitas para cobrar y revisar ventas.</p>
            </div>
            <div class="action-grid">
                <a class="action-card" href="${pageContext.request.contextPath}/ventas">
                    <h4>Ventas</h4>
                    <p>Abre el punto de venta y genera boletas.</p>
                    <span>Entrar</span>
                </a>
                <a class="action-card" href="${pageContext.request.contextPath}/ventas/historial">
                    <h4>Historial</h4>
                    <p>Revisa ventas pendientes y concretadas.</p>
                    <span>Ver historial</span>
                </a>
                <a class="action-card" href="${pageContext.request.contextPath}/estadisticas">
                    <h4>Estadísticas</h4>
                    <p>Productos mas vendidos y ventas mensuales.</p>
                    <span>Analizar</span>
                </a>
            </div>
        </section>

        <section class="principal-section">
            <div class="section-title">
                <h3>Gestion y contenidos</h3>
                <p>Administra catalogo, usuarios y contenido publico.</p>
            </div>
            <div class="action-grid">
                <a class="action-card" href="${pageContext.request.contextPath}/producto">
                    <h4>Productos</h4>
                    <p>Altas, ediciones y control del inventario.</p>
                    <span>Abrir</span>
                </a>
                <a class="action-card" href="${pageContext.request.contextPath}/categoria">
                    <h4>Categorías</h4>
                    <p>Ordena y activa tus categorías.</p>
                    <span>Gestionar</span>
                </a>
                <a class="action-card" href="${pageContext.request.contextPath}/usuario">
                    <h4>Usuarios</h4>
                    <p>Administra cuentas y accesos.</p>
                    <span>Ver usuarios</span>
                </a>
                <a class="action-card" href="${pageContext.request.contextPath}/publicidad">
                    <h4>Publicidad</h4>
                    <p>Actualiza la informacion comercial.</p>
                    <span>Editar</span>
                </a>
                <a class="action-card" href="${pageContext.request.contextPath}/contacto">
                    <h4>Contacto</h4>
                    <p>Mantén visibles los canales de soporte.</p>
                    <span>Ir</span>
                </a>
            </div>
        </section>
    </main>
</body>
</html>