<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sistema de Gestión - Editar compra</title>
    <link href="./Bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<%
    HttpSession sesion = request.getSession();
    Usuario usuario = (Usuario) sesion.getAttribute("usuarioLogueado");

    if (usuario == null){
        response.sendRedirect("index.jsp");
    }

    // Obtener los datos de la compra a editar
    int id = Integer.parseInt(request.getParameter("id"));
    String nombre = request.getParameter("nombre");
    String descripcion = request.getParameter("descripcion");
    int cantidad = Integer.parseInt(request.getParameter("cantidad"));
    double costo = Double.parseDouble(request.getParameter("costo"));
    double precio = Double.parseDouble(request.getParameter("precio"));
%>

<div class="container mt-4">
    <form action="ControladorCompra" method="POST">
        <div class="card border-info mb-4" style="width: 18rem;">
            <div class="card-header">
                ACTUALIZAR COMPRA
            </div>

            <div class="card-body text-info">
                <div class="form-group">
                    <label>ID</label>
                    <input type="text" value="<%= id %>" name="txtId" readonly class="form-control">
                </div>
                <div class="form-group">
                    <label>NOMBRE</label>
                    <input type="text" value="<%= nombre %>" name="txtNombre" class="form-control">
                </div>
                <div class="form-group">
                    <label>DESCRIPCION</label>
                    <input type="text" value="<%= descripcion %>" name="txtDescripcion" class="form-control">
                </div>
                <div class="form-group">
                    <label>CANTIDAD</label>
                    <input type="text" value="<%= cantidad %>" name="txtCantidad" class="form-control">
                </div>
                <div class="form-group">
                    <label>COSTO</label>
                    <input type="text" value="<%= costo %>" name="txtCosto" class="form-control">
                </div>
                <div class="form-group">
                    <label>PRECIO</label>
                    <input type="text" value="<%= precio %>" name="txtPrecio" class="form-control">
                </div>
            </div>
            <div class="card-footer">
                <input type="submit" value="Actualizar" name="accion" class="btn btn-outline-success">
                <a href="ControladorCompra?accion=listar" class="btn-link ms-2">Volver</a>
            </div>
        </div>
    </form>
</div>

<script src="./Bootstrap/js/bootstrap.bundle.js"></script>
</body>
</html>
