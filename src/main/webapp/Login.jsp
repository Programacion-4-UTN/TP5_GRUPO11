<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Iniciar sesión</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
    <main class="login-card">
        <h1>Iniciar sesión</h1>

        <form action="${pageContext.request.contextPath}/ServletLogin" method="post">
            <label for="usuario">Usuario</label>
            <input type="text" id="usuario" name="usuario" autocomplete="username" autofocus required>

            <label for="contrasena">Contraseña</label>
            <input type="password" id="contrasena" name="contrasena" autocomplete="current-password" required>

            <p class="mensaje-error" role="alert" ${empty requestScope.error ? 'hidden' : ''}>${requestScope.error}</p>

            <button type="submit">Ingresar</button>
        </form>
    </main>
</body>
</html>