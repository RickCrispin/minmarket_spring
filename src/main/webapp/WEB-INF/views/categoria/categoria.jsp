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
        .estado-badge{ display:inline-block; padding:4px 10px; border-radius:999px; font-size:0.85rem; font-weight:600; color:#fff; }
        .estado-activo{ background:#2f855a; }
        .estado-inactivo{ background:#c53030; }
    </style>
</head>

<body>
    <c:set var="activePage" value="gestor-categorias" />
    <c:set var="headerTitle" value="Gestión" />
    <jsp:include page="/WEB-INF/views/components/navbara.jsp" />
    <main>
        <div class="container manager-page">
            <section class="manager-hero">
                <span class="eyebrow">Gestor de categorías</span>
                <h2>Categorías</h2>
                <div class="manager-toolbar">
                    <a href="${pageContext.request.contextPath}/categoria/add"><button type="button">Añadir nueva categoría</button></a>
                </div>
            </section>

            <section class="manager-table-card">
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
                        <td>
                            <span class="estado-badge ${categoria.estado == 'Activo' ? 'estado-activo' : 'estado-inactivo'}">
                                ${categoria.estado}
                            </span>
                        </td>
                        <td>

                            <!-- EDITAR -->
                            <form action="${pageContext.request.contextPath}/categoria/edit/${categoria.id}" method="get" style="display:inline;">
                                <button type="submit" style="color: rgb(255, 255, 255);">Editar</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            </section>
        </div>
    </main>
</body>
</html>
