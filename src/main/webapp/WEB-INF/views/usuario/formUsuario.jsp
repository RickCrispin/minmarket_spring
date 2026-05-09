<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Usuario - MiniMarket</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>
    <c:set var="activePage" value="gestor-usuarios" />
    <c:set var="headerTitle" value="Editar Usuario" />
    <jsp:include page="/WEB-INF/views/components/navbara.jsp" />
    <main>
        <div class="container manager-page">
            <section class="manager-hero">
                <span class="eyebrow">Gestor de usuarios</span>
                <h2>Editar Usuario</h2>
            </section>

            <section class="manager-form-card">
                <form class="form-section" action="${pageContext.request.contextPath}/usuario/edit/${usuario.id}" method="post">
                    <input type="text" name="nombres" value="${usuario.nombres}" placeholder="Nombres" required>
                    <input type="text" name="apellidos" value="${usuario.apellidos}" placeholder="Apellidos" required>
                    <input type="email" name="correo" value="${usuario.correo}" placeholder="Correo" required>
                    <input type="hidden" name="password" value="${usuario.password}" placeholder="Password" required>
                    <input type="text" name="telefono" value="${usuario.telefono}" placeholder="Teléfono">
                    <input type="text" name="direccion" value="${usuario.direccion}" placeholder="Dirección">
                    <select name="estado" required>
                        <option value="Activo" <c:if test="${usuario.estado == 'Activo'}">selected</c:if>>Activo</option>
                        <option value="Inactivo" <c:if test="${usuario.estado == 'Inactivo'}">selected</c:if>>Inactivo</option>
                    </select>
                    <div class="manager-form-actions">
                        <button type="submit">Actualizar</button>
                        <a href="${pageContext.request.contextPath}/usuario"><button type="button">Cancelar</button></a>
                    </div>
                </form>
            </section>
        </div>
    </main>
</body>
</html>
