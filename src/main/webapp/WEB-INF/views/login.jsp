<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - MiniMarket</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="login-page">
    <main>
        <div class="login-box">
            <h1>MiniMarket</h1>
            <p>Sistema de Ventas</p>
            <form action="${pageContext.request.contextPath}/login" method="post">
                <input type="text" 
                    placeholder="Usuario"
                    name="username"
                    required>
                <input type="password" 
                    placeholder="Contraseña" 
                    name="password"
                    required>
                <button type="submit">Entrar</button>
            </form>
            <c:if test="${not empty error}">
                <p class="error">${error}</p>
            </c:if>
        </div>
    </main>
</body>
</html>
