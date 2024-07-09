package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Inventario;
import modeloDAO.InventarioDAO;

public class ControladorInventario extends HttpServlet {

    InventarioDAO dao;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        List<Inventario> inventario = new ArrayList<>();
        
        switch (accion) {
            case "listar":
                dao = new InventarioDAO();
                inventario = dao.listarInventario();
                Collections.reverse(inventario);
                request.setAttribute("inventario", inventario);
                request.getRequestDispatcher("inventario.jsp").forward(request, response);
                break;
            // Puedes agregar más casos para otras acciones como agregar, modificar, eliminar, etc.
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

    @Override
    public String getServletInfo() {
        return "Controlador para la gestión del inventario";
    }
}
