<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión - MiniMarket</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        .toggle-input{ display:none; }
        .toggle-label{ display:inline-block; cursor:pointer; padding:8px 12px; background:#2b6cb0; color:#fff; border-radius:4px; margin-bottom:8px; }
        .toggle-label.close{ background:#a0aec0; }
        .form-container{ display:none; margin-top:10px; }
        .toggle-input:checked + .toggle-label.open + .toggle-label.close + .form-container{ display:block; }
        .toggle-input:checked + .toggle-label.open{ display:none; }
        .toggle-input:checked + .toggle-label.open + .toggle-label.close{ display:inline-block; }
    </style>
</head>

<body>
    <c:set var="activePage" value="gestion" />
    <c:set var="headerTitle" value="Gestión" />
    <jsp:include page="/WEB-INF/views/components/navbara.jsp" />
    <main>
        <div class="container">
            <h2>Gestión de Categorías</h2>
            <!-- Control CSS-only para mostrar/ocultar formulario de nueva categoría -->
            <input type="checkbox" id="toggleCategoria" class="toggle-input" />
            <label for="toggleCategoria" class="toggle-label open">Añadir nueva categoría</label>
            <label for="toggleCategoria" class="toggle-label close" style="display:none;">Cerrar</label>
            <div class="form-container">
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
            </div>

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
