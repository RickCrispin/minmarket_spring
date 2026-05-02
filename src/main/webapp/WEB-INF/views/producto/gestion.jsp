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
            <h2>Gestión de Inventario</h2>
            <!-- Control CSS-only para mostrar/ocultar formulario de nuevo producto -->
            <input type="checkbox" id="toggleProducto" class="toggle-input" />
            <label for="toggleProducto" class="toggle-label open">Añadir nuevo producto</label>
            <label for="toggleProducto" class="toggle-label close" style="display:none;">Cerrar</label>
            <div class="form-container">
                <form class="form-section" id="productoForm" action="${pageContext.request.contextPath}/producto" method="post">
                    <input type="text" name="nombre" placeholder="Nombre producto" required>
                    <input type="text" name="descripcion" placeholder="Descripción">
                    <input type="number" name="precio" placeholder="Precio" step="0.01" required>
                    <input type="number" name="stock" placeholder="Stock" required>
                    <select name="idCategoria" required>
                        <option value="">Seleccionar categoría...</option>
                        <c:forEach var="categoria" items="${categorias}">
                            <option value="${categoria.id}">${categoria.nombre}</option>
                        </c:forEach>
                    </select>
                    <button type="submit">Agregar</button>
                </form>
            </div>

            <h3>Inventario</h3>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Producto</th>
                    <th>Descripción</th>
                    <th>Precio</th>
                    <th>Stock</th>
                    <th>Categoría</th>
                    <th>Acciones</th>
                </tr>
                <c:choose>
                    <c:when test="${not empty productos}">
                        <c:forEach var="producto" items="${productos}">
                            <tr>
                                <td>${producto.id}</td>
                                <td>${producto.nombre}</td>
                                <td>${producto.descripcion}</td>
                                <td>S/${producto.precio}</td>
                                <td>${producto.stock}</td>
                                <td>${producto.categoria.nombre}</td>
                                <td>
                                    <form action="${pageContext.request.contextPath}/producto/edit/${producto.id}" method="get" style="display:inline;">
                                        <button type="submit" style="color: rgb(255, 255, 255);">Editar</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="7">No hay productos para mostrar.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </table>
        </div>
    </main>
</body>
</html>
