<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listado de Clientes</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/inicio.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/menu.css">
    <!-- Datatables CSS -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css">
</head>
<body>
    <jsp:include page="Menu.jsp">
        <jsp:param name="activo" value="listado"/>
    </jsp:include>

    <main class="contenido">
        <h2>Listado de Clientes</h2>
        <p>Usuario: <strong>${sessionScope.usuarioLogueado}</strong></p>

        <table id="tablaClientes" class="display">
            <thead>
                <tr>
                    <th>DNI</th>
                    <th>CUIL</th>
                    <th>Nombre y Apellido</th>
                    <th>Sexo</th>
                    <th>Fecha de Nacimiento</th>
                    <th>Dirección</th>
                    <th>Nacionalidad</th>
                    <th>Localidad</th>
                    <th>Provincia</th>
                </tr>
            </thead>
            <tbody>
                <!-- atributo listaClientes mandar desde el Servlet -->
                <c:forEach var="c" items="${listaClientes}">
                    <tr>
                        <td>${c.dni}</td>
                        <td>${c.cuil}</td>
                        <td>${c.nombre} ${c.apellido}</td>
                        <td>${c.sexo}</td>
                        <td>${c.fechaNacimiento}</td>
                        <td>${c.direccion}</td>
                        <td>${c.nacionalidad}</td>
                        <td>${c.localidad}</td>
                        <td>${c.provincia}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </main>

    <!-- jQuery y DataTables JS -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#tablaClientes').DataTable({
                "language": {
                    "url": "//cdn.datatables.net/plug-ins/1.13.6/i18n/es-ES.json"
                }
            });
        });
    </script>
</body>
</html>