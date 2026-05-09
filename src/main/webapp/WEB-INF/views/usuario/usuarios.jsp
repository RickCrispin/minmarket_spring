<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Usuarios - MiniMarket</title>
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
    <c:set var="activePage" value="gestor-usuarios" />
    <c:set var="headerTitle" value="Usuarios" />
    <jsp:include page="/WEB-INF/views/components/navbara.jsp" />
    <main>
        <div class="container">
            <h2>Gestión de Usuarios</h2>
            <!-- Control CSS-only para mostrar/ocultar formulario de nuevo usuario -->
            <input type="checkbox" id="toggleUsuario" class="toggle-input" />
            <label for="toggleUsuario" class="toggle-label open">Añadir nuevo usuario</label>
            <label for="toggleUsuario" class="toggle-label close" style="display:none;">Cerrar</label>
            <div class="form-container">
                <form class="form-section" id="usuarioForm" action="${pageContext.request.contextPath}/usuario" method="post">
                    <input type="text" name="nombres" placeholder="Nombres" required>
                    <input type="text" name="apellidos" placeholder="Apellidos" required>
                    <input type="email" name="correo" placeholder="Correo" required>
                    <input type="password" name="password" placeholder="Password" required>
                    <input type="text" name="telefono" placeholder="Teléfono">
                    <input type="text" name="direccion" placeholder="Dirección">
                    <select name="estado" required>
                        <option value="Activo">Activo</option>
                        <option value="Inactivo">Inactivo</option>
                    </select>
                    <button type="submit">Agregar</button>
                </form>
            </div>

            <h3>Usuarios</h3>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Nombres</th>
                    <th>Correo</th>
                    <th>Teléfono</th>
                    <th>Dirección</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
                <c:choose>
                    <c:when test="${not empty usuarios}">
                        <c:forEach var="usuario" items="${usuarios}">
                            <tr>
                                <td>${usuario.id}</td>
                                <td>${usuario.nombres} ${usuario.apellidos}</td>
                                <td>${usuario.correo}</td>
                                <td>${usuario.telefono}</td>
                                <td>${usuario.direccion}</td>
                                <td>
                                    <span class="estado-badge ${usuario.estado == 'Activo' ? 'estado-activo' : 'estado-inactivo'}">
                                        ${usuario.estado}
                                    </span>
                                </td>
                                <td>
                                    <form action="${pageContext.request.contextPath}/usuario/edit/${usuario.id}" method="get" style="display:inline;">
                                        <button type="submit">Editar</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="7">No hay usuarios para mostrar.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </table>
        </div>
    </main>
</body>
</html>
