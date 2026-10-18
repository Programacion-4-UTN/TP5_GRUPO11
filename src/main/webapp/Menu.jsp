<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<aside class="menu">
    <h2>Menú</h2>
    <nav>

        <a href="${pageContext.request.contextPath}/altaCliente.jsp"
           class="${param.activo == 'alta' ? 'activo' : ''}">Alta de Cliente</a>
        <a href="${pageContext.request.contextPath}/ListarClientes.jsp"
           class="${param.activo == 'listado' ? 'activo' : ''}">Listado de Clientes</a>         
  
        <a href="${pageContext.request.contextPath}/ServletLogin?accion=logout" class="salir">Cerrar sesión</a>

    </nav>
</aside>