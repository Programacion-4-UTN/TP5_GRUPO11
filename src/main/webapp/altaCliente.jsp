
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Alta de Cliente</title>
    <!-- estilos  -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/inicio.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/menu.css">
</head>
<body>
    <!--  Menú Lateral  -->
    <jsp:include page="Menu.jsp">
        <jsp:param name="activo" value="alta"/>
    </jsp:include>

    <!-- Área de contenido -->
    <main class="contenido">
        <h2>Formulario de Cliente</h2>
        <p>Usuario: <strong>${sessionScope.usuarioLogueado}</strong></p>

        <p style="color:red;">${requestScope.error}</p>
        <p style="color:green;">${requestScope.exito}</p>

        <form action="${pageContext.request.contextPath}/ServletCliente" method="POST">
            <input type="hidden" name="accion" value="alta">
            
            <label>DNI *</label>
            <input type="text" name="dni" pattern="\d{6,8}" title="Debe contener entre 6 y 8 números" required>

            <label>CUIL *</label>
            <input type="text" name="cuil" pattern="\d{11}" title="11 números" required>

            <label>Nombre *</label>
            <input type="text" name="nombre" pattern="[A-Za-zÁÉÍÓÚáéíóúÑñ\s]+" required>

            <label>Apellido *</label>
            <input type="text" name="apellido" pattern="[A-Za-zÁÉÍÓÚáéíóúÑñ\s]+" required>

            <label>Sexo *</label>
            <select name="sexo" required>
                <option value="">-- Seleccione --</option>
                <option value="M">M</option>
                <option value="F">F</option>
                <option value="Otro">Otro</option>
            </select>

            <label>Fecha de nacimiento *</label>
            <input type="date" name="fechaNacimiento" id="fechaNac" required>

            <label>Dirección *</label>
            <input type="text" name="direccion" required>

            <label>Nacionalidad *</label>
            <input type="text" name="nacionalidad" pattern="[A-Za-z\s]+" required>

            <label>Localidad *</label>
            <input type="text" name="localidad" pattern="[A-Za-z\s]+" required>

            <label>Provincia *</label>
            <input type="text" name="provincia" pattern="[A-Za-z\s]+" required>

            <label>Correo electrónico *</label>
            <input type="email" name="correo" required>

            <label>Teléfono *</label>
            <input type="text" name="telefono" pattern="\d{10}" title="10 números" required>

            <button type="submit">Guardar cliente</button>
        </form>
    </main>

    <script>
        // Restricción para no elegir fechas futuras
        document.getElementById("fechaNac").setAttribute('max', new Date().toISOString().split('T')[0]);
    </script>
</body>
</html>