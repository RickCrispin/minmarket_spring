<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Añadir Producto - MiniMarket</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <c:set var="activePage" value="gestor-inventario" />
    <c:set var="headerTitle" value="Añadir Producto" />
    <jsp:include page="/WEB-INF/views/components/navbara.jsp" />
    <main>
        <div class="container manager-page">
            <section class="manager-hero">
                <span class="eyebrow">Gestor de inventario</span>
                <h2>Añadir Producto</h2>
            </section>

            <section class="manager-form-card">
            <form class="form-section" action="${pageContext.request.contextPath}/producto" method="post">
                <input type="text" name="nombre" placeholder="Nombre producto" required>
                <input type="text" name="descripcion" placeholder="Descripción">
                <input type="number" name="precio" placeholder="Precio" step="0.01" required>
                <select name="idCategoria" required>
                    <option value="">Seleccionar categoría...</option>
                    <c:forEach var="categoria" items="${categorias}">
                        <option value="${categoria.id}">${categoria.nombre}</option>
                    </c:forEach>
                </select>
                <button type="submit">Agregar</button>
                <a href="${pageContext.request.contextPath}/producto" style="color: rgb(255, 255, 255); text-decoration: none;">
                    <button type="button">Cancelar</button>
                </a>
            </form>
            </section>
        </div>
    </main>
</body>
</html>
