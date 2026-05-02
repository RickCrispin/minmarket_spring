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
        <section class="hero-panel">
            <div class="hero-copy">
                <span class="eyebrow">MiniMarket</span>
                <h2>Panel principal para operar el negocio desde un solo lugar.</h2>
                <p>
                    Accede rápido a ventas, gestión de productos, categorías y contenidos públicos
                    para mantener el flujo de trabajo ordenado.
                </p>
                <div class="hero-actions">
                    <a class="btn-primary" href="${pageContext.request.contextPath}/ventas">Ir a ventas</a>
                    <a class="secondary-link" href="${pageContext.request.contextPath}/gestion">Gestionar inventario</a>
                </div>
            </div>
            <aside class="hero-card">
                <h3>Accesos rápidos</h3>
                <p>Atajos preparados para las tareas que más se usan durante el día.</p>
                <ul>
                    <li>Revisar productos con stock disponible</li>
                    <li>Administrar categorías y catálogo</li>
                    <li>Consultar publicidad y contacto</li>
                </ul>
            </aside>
        </section>

        <section class="stats-grid">
            <article class="stat-card">
                <strong>Ventas</strong>
                <p>Ingresa al flujo de cobro y armando de carrito.</p>
            </article>
            <article class="stat-card">
                <strong>Gestión</strong>
                <p>Administra productos, precios, stock y categorías.</p>
            </article>
            <article class="stat-card">
                <strong>Contenido</strong>
                <p>Mantén visibles la publicidad y los datos de contacto.</p>
            </article>
        </section>

        <section class="feature-grid">
            <article class="feature-card">
                <h3>Catálogo</h3>
                <p>Revisa y organiza la información del inventario sin salir del panel.</p>
                <a href="${pageContext.request.contextPath}/gestion">Abrir gestión</a>
            </article>
            <article class="feature-card">
                <h3>Promociones</h3>
                <p>Publica la información que acompaña la experiencia de compra.</p>
                <a href="${pageContext.request.contextPath}/publicidad">Ver publicidad</a>
            </article>
            <article class="feature-card">
                <h3>Atención</h3>
                <p>Consulta la página de contacto para mantener visibles los canales de comunicación.</p>
                <a href="${pageContext.request.contextPath}/contacto">Ir a contacto</a>
            </article>
        </section>
    </main>
</body>
</html>