<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header class="header">
    <h1>MiniMarket - <c:out value="${headerTitle}" default="Inicio"/></h1>
    <nav>
        <a href="${pageContext.request.contextPath}/principal" class="${activePage == 'principal' ? 'active' : ''}">Inicio</a>
        <a href="${pageContext.request.contextPath}/ventas" class="${activePage == 'ventas' ? 'active' : ''}">Ventas</a>
        <a href="${pageContext.request.contextPath}/gestion" class="${activePage == 'gestion' ? 'active' : ''}">Inventario</a>
        <a href="${pageContext.request.contextPath}/categoria" class="${activePage == 'categoria' ? 'active' : ''}">Categorías</a>
        <a href="${pageContext.request.contextPath}/publicidad" class="${activePage == 'publicidad' ? 'active' : ''}">Publicidad</a>
        <a href="${pageContext.request.contextPath}/contacto" class="${activePage == 'contacto' ? 'active' : ''}">Contacto</a>
        <a href="${pageContext.request.contextPath}/logout" class="logout">Salir</a>
    </nav>
</header>