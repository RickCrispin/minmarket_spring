<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Publicidad - MiniMarket</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <c:set var="activePage" value="publicidad" />
    <c:set var="headerTitle" value="Publicidad" />
    <jsp:include page="/WEB-INF/views/components/navbara.jsp" />

    <main>
        <div class="container">
            <img src="${pageContext.request.contextPath}/img/Banner.avif" alt="Banner de promociones" style="width: 100%; border-radius: 4px;">
        </div>
    </main>
</body>
</html>
