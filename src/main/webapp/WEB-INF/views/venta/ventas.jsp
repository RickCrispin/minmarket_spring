<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ventas - MiniMarket</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>
    <c:set var="activePage" value="ventas" />
    <c:set var="headerTitle" value="Ventas" />
    <jsp:include page="/WEB-INF/views/components/navbara.jsp" />
    <main>
        <div class="container sales">
            <div class="products-col">
                <h2>Productos</h2>
                <div class="grid">
                    <c:choose>
                        <c:when test="${not empty productos}">
                            <c:forEach var="producto" items="${productos}">
                                <div class="card">
                                    <h3>${producto.nombre}</h3>
                                    <p>S/${producto.precio}</p>
                                    <p style="font-size: 0.9em; color: #666;">Stock: ${producto.stock}</p>
                                    <button data-producto-id="${producto.id}">Agregar</button>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <p>No hay productos activos para mostrar.</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="sidebar">
                <h3>Carrito</h3>
                <p>Leche - S/2.50</p>
                <hr>
                <p><strong>Total: S/2.50</strong></p>
                <button class="btn-primary">Pagar</button>
            </div>
        </div>
    </main>
</body>
</html>