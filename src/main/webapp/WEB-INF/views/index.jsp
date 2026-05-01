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
            <form>
                <input type="text" placeholder="Usuario" required>
                <input type="password" placeholder="Contraseña" required>
                <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/ventas'">Entrar</button>
            </form>
        </div>
    </main>
</body>
</html>
