public class Producto {
    String codigo;
    String oferta;
    String descripcion;
    String adicional;
    String sinonimo;
    int lista;
    String descripcion_lista;
    String moneda;
    String unidad;
    double precio;
    String bonificacion;


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getOferta() {
        return oferta;
    }

    public void setOferta(String oferta) {
        this.oferta = oferta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAdicional() {
        return adicional;
    }

    public void setAdicional(String adicional) {
        this.adicional = adicional;
    }

    public String getSinonimo() {
        return sinonimo;
    }

    public void setSinonimo(String sinonimo) {
        this.sinonimo = sinonimo;
    }

    public int getLista() {
        return lista;
    }

    public void setLista(int lista) {
        this.lista = lista;
    }

    public String getDescripcion_lista() {
        return descripcion_lista;
    }

    public void setDescripcion_lista(String descripcion_lista) {
        this.descripcion_lista = descripcion_lista;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getBonificacion() {
        return bonificacion;
    }

    public void setBonificacion(String bonificacion) {
        this.bonificacion = bonificacion;
    }

    public Producto(String codigo, int lista, String moneda, Double precio) {
        this.codigo = codigo;
        this.lista = lista;
        this.moneda = moneda;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo='" + codigo + '\'' +
                ", lista=" + lista +
                ", moneda='" + moneda + '\'' +
                ", precio=" + precio +
                '}';
    }
}
