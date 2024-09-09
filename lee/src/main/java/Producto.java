import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter

@ToString

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


    public Producto(String codigo, int lista, String moneda, Double precio) {
        this.codigo = codigo;
        this.lista = lista;
        this.moneda = moneda;
        this.precio = precio;
    }

}
