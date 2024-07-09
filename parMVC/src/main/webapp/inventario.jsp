<%@page import="modelo.Usuario"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Sistema de Gestión, inventario</title>
        <link href="./Bootstrap/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        
        <% 
            HttpSession sesion = request.getSession();
            Usuario usuario = (Usuario) sesion.getAttribute("usuarioLogueado");
            
            if (usuario == null){
                response.sendRedirect("index.jsp");
            }
            else if (usuario.getAdministrador() == 0){
                response.sendRedirect("listadoProductos.jsp");
            }
        %>
        
        <div class="container mt-4">
            <h1 class="h3">Inventario de productos</h1>
            <div class="d-flex">
                <a class="btn btn-outline-primary" href="InventarioServlet?accion=listar">Listar</a>
                <a class="btn btn-outline-primary ms-2" href="Controlador?accion=listar">Volver</a>
                <a class="btn btn-outline-primary ms-2" href="cerrarSesion">SALIR</a>
            </div>
            <hr>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Nombre Producto</th>
                        <th scope="col">Descripción</th>
                        <th scope="col">Cantidad</th>
                        <th scope="col">Costo</th>
                        <th scope="col">Precio</th>
                    </tr>
                </thead>
                <tbody class="table-group-divider">
                    <c:forEach var="registro" items="${registrosInventario}">
                        <tr>
                            <th scope="row">${registro.id}</th>
                            <td>${registro.nombreProducto}</td>
                            <td>${registro.descripcionProducto}</td>
                            <td>${registro.cantidadProducto}</td>
                            <td>${registro.costoProducto}</td>
                            <td>${registro.precioProducto}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <script src="./Bootstrap/js/bootstrap.bundle.js"></script>
    </body>
</html>
