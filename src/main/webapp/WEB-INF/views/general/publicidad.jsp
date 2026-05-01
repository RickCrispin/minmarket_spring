<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Publicidad - MiniMarket</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <header class="header">
        <h1>MiniMarket - Publicidad</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/ventas">Ventas</a>
            <a href="${pageContext.request.contextPath}/gestion">Gestión</a>
            <a href="${pageContext.request.contextPath}/publicidad" class="active">Publicidad</a>
            <a href="${pageContext.request.contextPath}/contacto">Contacto</a>
            <a href="${pageContext.request.contextPath}/" class="logout">Salir</a>
        </nav>
    </header>

    <main>
        <div class="container">
            <img src="${pageContext.request.contextPath}/img/Banner.avif" alt="Banner de promociones" style="width: 100%; border-radius: 4px;">
        </div>
    </main>
</body>
</html>
