<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Producto - MiniMarket</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>
    <c:set var="activePage" value="gestion" />
    <c:set var="headerTitle" value="Editar Producto" />
    <jsp:include page="/WEB-INF/views/components/navbara.jsp" />
    <main>
        <div class="container">
            <h2>Editar Producto</h2>
            <form class="form-section" action="${pageContext.request.contextPath}/producto/edit/${producto.id}" method="post">
                <input type="text" name="nombre" value="${producto.nombre}" placeholder="Nombre producto" required>
                <input type="text" name="descripcion" value="${producto.descripcion}" placeholder="Descripción">
                <input type="number" name="precio" value="${producto.precio}" placeholder="Precio" step="0.01" required>
                <select name="idCategoria" required>
                    <option value="">Seleccionar categoría...</option>
                    <c:forEach var="categoria" items="${categorias}">
                        <option value="${categoria.id}" 
                            <c:if test="${categoria.id == producto.idCategoria}">selected</c:if>>
                            ${categoria.nombre}
                        </option>
                    </c:forEach>
                </select>
                <select name="estado" required>
                    <option value="">Seleccionar estado...</option>
                    <option value="Activo" <c:if test="${producto.estado == 'Activo'}">selected</c:if>>Activo</option>
                    <option value="Inactivo" <c:if test="${producto.estado == 'Inactivo'}">selected</c:if>>Inactivo</option>
                </select>
                <button type="submit">Actualizar</button>
                <a href="${pageContext.request.contextPath}/producto" style="color: rgb(255, 255, 255); text-decoration: none;">
                    <button type="button">Cancelar</button>
                </a>
            </form>
        </div>
    </main>
</body>
</html>
