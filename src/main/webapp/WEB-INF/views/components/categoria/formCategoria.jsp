<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión - MiniMarket</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>
    <c:set var="activePage" value="gestor-categorias" />
    <c:set var="headerTitle" value="Gestión" />
    <jsp:include page="/WEB-INF/views/components/navbara.jsp" />
    <main>
        <div class="container manager-page">
            <section class="manager-hero">
                <span class="eyebrow">Gestor de categorías</span>
                <h2>Editar Categoría</h2>
            </section>

            <section class="manager-form-card">
            <form class="form-section" action="${pageContext.request.contextPath}/categoria/edit/${categoria.id}" method="post">
                <input type="text" name="nombre" value="${categoria.nombre}" required>
                <input type="text" name="descripcion" value="${categoria.descripcion}">
                <select name="estado" required>
                    <option value="">Seleccionar estado...</option>
                    <option value="Activo" ${categoria.estado == 'Activo' ? 'selected' : ''}>Activo</option>
                    <option value="Inactivo" ${categoria.estado == 'Inactivo' ? 'selected' : ''}>Inactivo</option>
                </select>
                <div class="manager-form-actions">
                    <button type="submit">Actualizar</button>
                    <a href="${pageContext.request.contextPath}/categoria"><button type="button">Cancelar</button></a>
                </div>
            </form>
            </section>
        </div>
    </main>
</body>
</html>
