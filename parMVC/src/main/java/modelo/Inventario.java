package modelo;

public class Inventario {
    
    private Integer id;
    private String nombreProducto;
    private String descripcionProducto;
    private Integer cantidadProducto;
    private Double costoProducto;
    private Double precioProducto;

    public Inventario() {
    }

    public Inventario(String nombreProducto, String descripcionProducto, Integer cantidadProducto, Double costoProducto, Double precioProducto) {
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.cantidadProducto = cantidadProducto;
        this.costoProducto = costoProducto;
        this.precioProducto = precioProducto;
    }
    
    public Inventario(Integer id, String nombreProducto, String descripcionProducto, Integer cantidadProducto, Double costoProducto, Double precioProducto) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.cantidadProducto = cantidadProducto;
        this.costoProducto = costoProducto;
        this.precioProducto = precioProducto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public Integer getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public Double getCostoProducto() {
        return costoProducto;
    }

    public void setCostoProducto(Double costoProducto) {
        this.costoProducto = costoProducto;
    }

    public Double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(Double precioProducto) {
        this.precioProducto = precioProducto;
    }

}
