package modeloDAO;

import java.util.List;
import modelo.Inventario;

public interface InterfazInventarioDAO {
    
    public List<Inventario> listarInventario();
    
    public void agregarProducto(Inventario producto);

}
