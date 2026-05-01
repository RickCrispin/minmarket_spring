<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión - MiniMarket</title>
    <link rel="stylesheet" href="../css/styles.css">
</head>

<body>
    <header class="header">
        <h1>MiniMarket - Gestión</h1>
        <nav>
            <a href="main.html">Ventas</a>
            <a href="gestion.html" class="active">Gestión</a>
            <a href="publicidad.html">Publicidad</a>
            <a href="contacto.html">Contacto</a>
            <a href="../index.html" class="logout">Salir</a>
        </nav>
    </header>
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
