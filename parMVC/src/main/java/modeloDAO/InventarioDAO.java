package modeloDAO;

import config.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Inventario;

public class InventarioDAO implements InterfazInventarioDAO {

    @Override
    public List<Inventario> listarInventario() {
        List<Inventario> inventario = new ArrayList<>();
        String sql = "SELECT * FROM inventario;";
        try {
            PreparedStatement ps = Conexion.Conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Inventario item = new Inventario();
                item.setId(rs.getInt("id"));
                item.setNombreProducto(rs.getString("nombreProducto"));
                item.setDescripcionProducto(rs.getString("descripcionProducto"));
                item.setCantidadProducto(rs.getInt("cantidadProducto"));
                item.setCostoProducto(rs.getDouble("costoProducto"));
                item.setPrecioProducto(rs.getDouble("precioProducto"));
                inventario.add(item);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }
        
        Conexion.cerrarConexion();
        
        return inventario;
    }

    @Override
    public void agregarProducto(Inventario producto) {
        int resultado = 0;
        String sql = "INSERT INTO inventario(nombreProducto, descripcionProducto, cantidadProducto, costoProducto, precioProducto) VALUES (?, ?, ?, ?, ?);";
        try {
            PreparedStatement ps = Conexion.Conectar().prepareStatement(sql);
            ps.setString(1, producto.getNombreProducto());
            ps.setString(2, producto.getDescripcionProducto());
            ps.setInt(3, producto.getCantidadProducto());
            ps.setDouble(4, producto.getCostoProducto());
            ps.setDouble(5, producto.getPrecioProducto());
            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al agregar el producto en la base de datos: " + e);
        }
        
        Conexion.cerrarConexion();
    }
}
