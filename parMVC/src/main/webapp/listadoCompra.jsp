<%@page import="modelo.Usuario"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sistema de Gestión - Listado de Compras</title>
    <link href="./Bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    
<% 
    HttpSession sesion = request.getSession();
    Usuario usuario = (Usuario) sesion.getAttribute("usuarioLogueado");
    
    if (usuario == null){
        response.sendRedirect("index.jsp");
    }
%>

<div class="container mt-4">
    <h1 class="h3">Sistema de Gestión</h1>
    <div class="d-flex justify-content-between">
        <form class="d-flex" action="ControladorCompra" method="POST">
            <button type="submit" class="btn btn-outline-primary" value="listar" name="accion">Listar</button>
            <a class="btn btn-outline-primary ms-2" href="ControladorCompra?accion=nuevo">Agregar Compra</a>
            <a class="btn btn-outline-primary ms-2" href="cerrarSesion">Salir</a>
        </form>
        <% if (usuario != null){
            if (usuario.getAdministrador() == 1){
        %>
        <div>
        </div>
        <% } } %>
    </div>
    <hr>
    <table class="table">
        <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Nombre</th>
                <th scope="col">Descripción</th>
                <th scope="col">Cantidad</th>
                <th scope="col">Costo</th>
                <th scope="col">Precio</th>
                <th scope="col">Acciones</th>
            </tr>
        </thead>
        <tbody class="table-group-divider">
            <c:forEach var="compra" items="${compras}">
                <tr>
                    <th scope="row">${compra.id}</th>
                    <td>${compra.nombre}</td>
                    <td>${compra.descripcion}</td>
                    <td>${compra.cantidad}</td>
                    <td>${compra.costo}</td>
                    <td>${compra.precio}</td>
                    <td>
                        <a href="ControladorCompra?accion=editar&id=${compra.id}" class="btn btn-outline-warning">Editar</a>
                        <a href="ControladorCompra?accion=eliminar&id=${compra.id}" class="btn btn-outline-danger">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<script src="./Bootstrap/js/bootstrap.bundle.js"></script>
</body>
</html>
