<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Añadir Categoría - MiniMarket</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <c:set var="activePage" value="gestor-categorias" />
    <c:set var="headerTitle" value="Añadir Categoría" />
    <jsp:include page="/WEB-INF/views/components/navbara.jsp" />
    <main>
        <div class="container manager-page">
            <section class="manager-hero">
                <span class="eyebrow">Gestor de categorías</span>
                <h2>Añadir Categoría</h2>
            </section>

            <section class="manager-form-card">
            <form class="form-section" action="${pageContext.request.contextPath}/categoria" method="post">
                <input type="text" name="nombre" placeholder="Nombre categoría..." required>
                <input type="text" name="descripcion" placeholder="Descripción...">
                <select name="estado" required>
                    <option value="Activo" selected>Activo</option>
                    <option value="Inactivo">Inactivo</option>
                </select>
                <button type="submit">Agregar</button>
                <a href="${pageContext.request.contextPath}/categoria" style="color: rgb(255, 255, 255); text-decoration: none;">
                    <button type="button">Cancelar</button>
                </a>
            </form>
            </section>
        </div>
    </main>
</body>
</html>
