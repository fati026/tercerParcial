package modeloDAO;

import config.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Compra;

public class CompraDAO implements InterfazCompraDAO {

    @Override
    public List<Compra> getCompras() {
        List<Compra> compras = new ArrayList<>();
        String sql = "SELECT * FROM compra;";
        try {
            PreparedStatement ps = Conexion.Conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Compra compra = new Compra();
                compra.setId(rs.getInt("id"));
                compra.setNombre(rs.getString("nombre"));
                compra.setDescripcion(rs.getString("descripcion"));
                compra.setCantidad(rs.getInt("cantidad"));
                compra.setCosto(rs.getDouble("costo"));
                compra.setPrecio(rs.getDouble("precio"));
                compras.add(compra);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }
        
        Conexion.cerrarConexion();
        
        return compras;
    }

    @Override
    public Compra getId(int id) {
        String sql = "SELECT * FROM compra WHERE id = ?;";
        Compra compra = new Compra();
        try {
            PreparedStatement ps = Conexion.Conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                compra.setId(rs.getInt(1));
                compra.setNombre(rs.getString(2));
                compra.setDescripcion(rs.getString(3));
                compra.setCantidad(rs.getInt(4));
                compra.setCosto(rs.getDouble(5));
                compra.setPrecio(rs.getDouble(6));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }
        
        Conexion.cerrarConexion();
        
        return compra;
    }

    @Override
    public int add(Compra compra) {
        int resultado = 0;
        String sql = "INSERT INTO compra(nombre, descripcion, cantidad, costo, precio) VALUES (?, ?, ?, ?, ?);";
        try {
            PreparedStatement ps = Conexion.Conectar().prepareStatement(sql);
            ps.setString(1, compra.getNombre());
            ps.setString(2, compra.getDescripcion());
            ps.setInt(3, compra.getCantidad());
            ps.setDouble(4, compra.getCosto());
            ps.setDouble(5, compra.getPrecio());
            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al agregar en la base de datos" + e);
        }
        
        Conexion.cerrarConexion();
        
        return resultado;
    }

    @Override
    public int update(Compra compra) {
        int resultado = 0;
        String sql = "UPDATE compra SET nombre = ?, descripcion = ?, cantidad = ?, costo = ?, precio = ? WHERE id = ?;";
        try {
            PreparedStatement ps = Conexion.Conectar().prepareStatement(sql);
            ps.setString(1, compra.getNombre());
            ps.setString(2, compra.getDescripcion());
            ps.setInt(3, compra.getCantidad());
            ps.setDouble(4, compra.getCosto());
            ps.setDouble(5, compra.getPrecio());
            ps.setInt(6, compra.getId());
            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar en la base de datos" + e);
        }
        
        Conexion.cerrarConexion();
        
        return resultado;
    }

    @Override
    public int delete(int id) {
        int resultado = 0;
        String sql = "DELETE FROM compra WHERE id = "+id;
        try {
            PreparedStatement ps = Conexion.Conectar().prepareStatement(sql);
            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Ha ocurrido un error durante el borrado" + e);
        }
        
        Conexion.cerrarConexion();
        
        return resultado;
    }
}
