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
            <h2>Gestión de Inventario</h2>
            <form class="form-section">
                <input type="text" placeholder="Nombre producto" required>
                <input type="number" placeholder="Precio" step="0.01" required>
                <input type="number" placeholder="Stock" required>
                <button type="submit">Agregar</button>
            </form>

            <h3>Inventario</h3>
            <table>
                <tr>
                    <th>Producto</th>
                    <th>Precio</th>
                    <th>Stock</th>
                    <th>Acción</th>
                </tr>
                <tr>
                    <td>Leche</td>
                    <td>S/2.50</td>
                    <td>45</td>
                    <td><button>Editar</button></td>
                </tr>
            </table>
        </div>
    </main>
</body>
</html>
