package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Compra;
import modelo.Usuario;
import modeloDAO.CompraDAO;

public class ControladorCompra extends HttpServlet {

    CompraDAO daoCompra;

    private List<Compra> FiltrarPorNombre(List<Compra> compras, String nombreAFiltrar) {
        List<Compra> comprasFiltradas = new ArrayList<>();

        for (Compra compra : compras) {
            if (compra.getNombre().toLowerCase().contains(nombreAFiltrar.toLowerCase())) {
                comprasFiltradas.add(compra);
            }
        }

        return comprasFiltradas;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        List<Compra> compras = new ArrayList<>();
        HttpSession session = request.getSession();
        String nombreUsuario = (String) session.getAttribute("nombreUsuario");
        Integer idUsuario = (Integer) session.getAttribute("idUsuario");

        switch (accion) {
            case "listar":
                daoCompra = new CompraDAO();
                compras = daoCompra.getCompras();
                Collections.reverse(compras);
                String nombreAFiltrar = request.getParameter("txtNombreProducto");
                if (nombreAFiltrar == null || nombreAFiltrar.isEmpty()) {
                    request.setAttribute("Compras", compras);
                } else {
                    compras = FiltrarPorNombre(compras, nombreAFiltrar);
                    request.setAttribute("Compras", compras);
                }
                request.getRequestDispatcher("listadoCompra.jsp").forward(request, response);
                break;
            case "Agregar":
                try {
                    int resultado;
                    String nombre = request.getParameter("txtNombre");
                    String descripcion = request.getParameter("txtDescripcion");
                    Integer cantidad = Integer.valueOf(request.getParameter("txtCantidad"));
                    Double costo = Double.valueOf(request.getParameter("txtCosto"));
                    Double precio = Double.valueOf(request.getParameter("txtPrecio"));
                    Compra compra = new Compra(nombre, descripcion, cantidad, costo, precio);
                    resultado = daoCompra.add(compra);
                    if (resultado != 0) {
                        Compra registro = new Compra(nombre, descripcion, cantidad, costo, precio, idUsuario, nombreUsuario, "Agregó");
                        CompraDAO daoCompra = new CompraDAO();
                        daoCompra.add(registro);
                        request.setAttribute("config", "alert alert-success");
                        request.setAttribute("mensaje", "LA COMPRA SE HA AGREGADO CON ÉXITO");
                        request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                    } else {
                        request.setAttribute("config", "alert alert-danger");
                        request.setAttribute("mensaje", "NO SE HA PODIDO GUARDAR LA COMPRA");
                        request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                    }
                } catch (Exception e) {
                    request.setAttribute("config", "alert alert-danger");
                    request.setAttribute("mensaje", "Ha ocurrido un error al agregar la compra: " + e.getMessage());
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                }
                break;
            case "Editar":
                int id = Integer.valueOf(request.getParameter("id"));
                Compra c = daoCompra.getId(id);
                request.setAttribute("compra", c);
                request.getRequestDispatcher("editarCompra.jsp").forward(request, response);
                break;
            case "Actualizar":
                try {
                    int idCompra = Integer.valueOf(request.getParameter("txtId"));
                    Compra compraAEditar = daoCompra.getId(idCompra);
                    String nombre1 = request.getParameter("txtNombre");
                    String descripcion1 = request.getParameter("txtDescripcion");
                    Integer cantidad1 = Integer.valueOf(request.getParameter("txtCantidad"));
                    Double costo1 = Double.valueOf(request.getParameter("txtCosto"));
                    Double precio1 = Double.valueOf(request.getParameter("txtPrecio"));
                    Compra compraEditada = new Compra(idCompra, nombre1, descripcion1, cantidad1, costo1, precio1);
                    int respuesta = daoCompra.update(compraEditada);
                    if (respuesta != 0) {
                        Compra registroAnterior = new Compra(compraAEditar.getNombre(), compraAEditar.getDescripcion(), compraAEditar.getCantidad(), compraAEditar.getCosto(), compraAEditar.getPrecio(), idUsuario, nombreUsuario, "Estado anterior");
                        CompraDAO daoCompra = new CompraDAO();
                        daoCompra.add(registroAnterior);
                        Compra registro = new Compra(nombre1, descripcion1, cantidad1, costo1, precio1, idUsuario, nombreUsuario, "Actualizó");
                        daoCompra.add(registro);
                        request.setAttribute("config", "alert alert-success");
                        request.setAttribute("mensaje", "LA COMPRA SE HA ACTUALIZADO CON ÉXITO");
                        request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                    } else {
                        request.setAttribute("config", "alert alert-danger");
                        request.setAttribute("mensaje", "NO SE HA PODIDO ACTUALIZAR LA COMPRA");
                        request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                    }
                } catch (Exception e) {
                    request.setAttribute("config", "alert alert-danger");
                    request.setAttribute("mensaje", "Ha ocurrido un error al actualizar la compra: " + e.getMessage());
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                }
                break;
            case "Delete":
                try {
                    int idComp = Integer.valueOf(request.getParameter("id"));
                    Compra compraABorrar = daoCompra.getId(idComp);
                    int res = daoCompra.delete(idComp);
                    if (res != 0) {
                        Compra registro = new Compra(compraABorrar.getNombre(), compraABorrar.getDescripcion(), compraABorrar.getCantidad(), compraABorrar.getCosto(), compraABorrar.getPrecio(), idUsuario, nombreUsuario, "Borró");
                        CompraDAO daoCompra = new CompraDAO();
                        daoCompra.add(registro);
                        request.setAttribute("config", "alert alert-warning");
                        request.setAttribute("mensaje", "LA COMPRA SE HA ELIMINADO CON ÉXITO");
                        request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                    } else {
                        request.setAttribute("config", "alert alert-danger");
                        request.setAttribute("mensaje", "NO SE HA PODIDO ELIMINAR LA COMPRA");
                        request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                    }
                } catch (Exception e) {
                    request.setAttribute("config", "alert alert-danger");
                    request.setAttribute("mensaje", "Ha ocurrido un error al eliminar la compra: " + e.getMessage());
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                }
                break;
            default:
                request.setAttribute("config", "alert alert-danger");
                request.setAttribute("mensaje", "Acción no válida");
                request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    public String getServletInfo() {
        return "Short description";
    }
}