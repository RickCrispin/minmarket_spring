<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contacto - MiniMarket</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>
    <c:set var="activePage" value="contacto" />
    <c:set var="headerTitle" value="Contacto" />
    <jsp:include page="/WEB-INF/views/components/navbara.jsp" />
    <main>
        <div class="container">
            <div class="contact-section">
                <div class="info">
                    <h2>Información</h2>
                    <p><strong>Ubicación:</strong> Calle Principal 123</p>
                    <p><strong>Teléfono:</strong> +51 904426338</p>
                    <p><strong>Email:</strong> eltilininsano@minimarket.com</p>
                </div>

                <div class="form">
                    <h2>Enviar Mensaje</h2>
                    <form>
                        <input type="text" placeholder="Nombre" required>
                        <input type="email" placeholder="Email" required>
                        <textarea placeholder="Mensaje" rows="4" required></textarea>
                        <button type="submit">Enviar</button>
                    </form>
                </div>
            </div>
        </div>
    </main>
</body>
</html>
