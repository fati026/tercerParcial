package modelo;

public class Compra {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer cantidad;
    private Double costo;
    private Double precio;
    private Integer idUsuario;
    private String nombreUsuario;
    private String accion;

    public Compra() {
    }

    public Compra(String nombre, String descripcion, Integer cantidad, Double costo, Double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.costo = costo;
        this.precio = precio;
    }
    
    public Compra(Integer id, String nombre, String descripcion, Integer cantidad, Double costo, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.costo = costo;
        this.precio = precio;
    }

    public Compra(String nombre, String descripcion, Integer cantidad, Double costo, Double precio, Integer idUsuario, String nombreUsuario, String accion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.costo = costo;
        this.precio = precio;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.accion = accion;
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
}
