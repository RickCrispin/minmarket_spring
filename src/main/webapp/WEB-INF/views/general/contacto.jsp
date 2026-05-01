<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contacto - MiniMarket</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>
    <header class="header">
        <h1>MiniMarket - Contacto</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/ventas">Ventas</a>
            <a href="${pageContext.request.contextPath}/gestion">Gestión</a>
            <a href="${pageContext.request.contextPath}/publicidad">Publicidad</a>
            <a href="${pageContext.request.contextPath}/contacto" class="active">Contacto</a>
            <a href="${pageContext.request.contextPath}/" class="logout">Salir</a>
        </nav>
    </header>
    <main>
        <div class="container">
            <div class="contact-section">
                <div class="info">
                    <h2>Información</h2>
                    <p><strong>Ubicación:</strong> Calle Principal 123</p>
                    <p><strong>Teléfono:</strong> +1 (555) 123-4567</p>
                    <p><strong>Email:</strong> info@minimarket.com</p>
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
