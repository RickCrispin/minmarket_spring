<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header class="header">
    <h1>MiniMarket - <c:out value="${headerTitle}" default="Inicio"/></h1>
    <nav>
        <a href="${pageContext.request.contextPath}/principal" class="${activePage == 'principal' ? 'active' : ''}">Inicio</a>
        <a href="${pageContext.request.contextPath}/ventas" class="${activePage == 'ventas' ? 'active' : ''}">Ventas</a>
        <details class="nav-dropdown ${(activePage == 'gestor' || activePage == 'gestor-inventario' || activePage == 'gestor-usuarios' || activePage == 'gestor-categorias' || activePage == 'gestor-historial') ? 'active' : ''}" ${(activePage == 'gestor' || activePage == 'gestor-inventario' || activePage == 'gestor-usuarios' || activePage == 'gestor-categorias' || activePage == 'gestor-historial') ? 'open' : ''}>
            <summary>Gestor</summary>
            <div class="nav-dropdown-menu">
                <a href="${pageContext.request.contextPath}/producto" class="${activePage == 'gestor-inventario' ? 'active' : ''}">Inventario</a>
                <a href="${pageContext.request.contextPath}/usuario" class="${activePage == 'gestor-usuarios' ? 'active' : ''}">Usuarios</a>
                <a href="${pageContext.request.contextPath}/categoria" class="${activePage == 'gestor-categorias' ? 'active' : ''}">Categorías</a>
                <a href="${pageContext.request.contextPath}/ventas/historial" class="${activePage == 'gestor-historial' ? 'active' : ''}">Historial de ventas</a>
            </div>
        </details>
        <a href="${pageContext.request.contextPath}/publicidad" class="${activePage == 'publicidad' ? 'active' : ''}">Publicidad</a>
        <a href="${pageContext.request.contextPath}/contacto" class="${activePage == 'contacto' ? 'active' : ''}">Contacto</a>
        <a href="${pageContext.request.contextPath}/logout" class="logout">Salir</a>
    </nav>
</header>