<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ventas - MiniMarket</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>
    <header class="header">
        <h1>MiniMarket - Ventas</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/ventas" class="active">Ventas</a>
            <a href="${pageContext.request.contextPath}/gestion">Gestión</a>
            <a href="${pageContext.request.contextPath}/publicidad">Publicidad</a>
            <a href="${pageContext.request.contextPath}/contacto">Contacto</a>
            <a href="${pageContext.request.contextPath}/" class="logout">Salir</a>
        </nav>
    </header>
    <main>
        <div class="container sales">
            <div class="products-col">
                <h2>Productos</h2>
                <div class="grid">
                    <div class="card">
                        <h3>Leche</h3>
                        <p>S/2.50</p>
                        <button>Agregar</button>
                    </div>
                    <div class="card">
                        <h3>Pan</h3>
                        <p>S/1.50</p>
                        <button>Agregar</button>
                    </div>
                    <div class="card">
                        <h3>Arroz</h3>
                        <p>S/2.00</p>
                        <button>Agregar</button>
                    </div>
                    <div class="card">
                        <h3>Café</h3>
                        <p>S/4.50</p>
                        <button>Agregar</button>
                    </div>
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
