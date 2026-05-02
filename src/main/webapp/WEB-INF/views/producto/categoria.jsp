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
    <c:set var="activePage" value="gestion" />
    <c:set var="headerTitle" value="Gestión" />
    <jsp:include page="/WEB-INF/views/components/navbara.jsp" />
    <main>
        <div class="container">
            <h2>Gestión de Categorías</h2>
            <!-- Formulario para agregar nueva categoría -->
            <form class="form-section" action="${pageContext.request.contextPath}/categoria" method="post">
                <input type="text" name="nombre" placeholder="Nombre categoría..." required>
                <input type="text" name="descripcion" placeholder="Descripción...">
                <select name="estado" required>
                    <option value="">Seleccionar estado...</option>
                    <option value="Activo">Activo</option>
                    <option value="Inactivo">Inactivo</option>
                </select>
                <button type="submit">Agregar</button>
            </form>

            <h3>Categorías</h3>
            <!-- Tabla de categorías -->
            <table>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Descripción</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
                <c:forEach var="categoria" items="${categorias}">
                    <tr>
                        <td>${categoria.id}</td>
                        <td>${categoria.nombre}</td>
                        <td>${categoria.descripcion}</td>
                        <td>${categoria.estado}</td>
                        <td>

                            <!-- EDITAR -->
                            <form action="${pageContext.request.contextPath}/categoria/edit/${categoria.id}" method="get" style="display:inline;">
                                <button type="submit" style="color: rgb(255, 255, 255);">Editar</button>
                            </form>

                            <!-- PENDIENTE -->
                            <!-- DESACTIVAR -->
                            <!-- <form action="${pageContext.request.contextPath}/categoria/${categoria.id}" method="post" style="display:inline;">
                                <input type="hidden" name="_method" value="delete">
                                <button type="submit" style="color: rgb(216, 255, 255);">Desactivar</button>
                            </form> -->
                        </th>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </main>
</body>
</html>
