import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

public class Prueba {
    public static void main(String[] args) throws IOException {

        Properties conf_app = Configuracion.lee(".//app.properties");
        File fichero = new File(conf_app.getProperty("haycorrida"));
        if (fichero.exists()) {

            FicheroSalida.open(conf_app.getProperty("salida"));

            XlsReader xls = new XlsReader();
            ArrayList<Producto> losProductos = xls.leePlanilla(conf_app.getProperty("archivo"));

            ActualizadorTabla actualizador = new ActualizadorTabla();
            actualizador.actualizarTabla(losProductos, "GVA17");
            actualizador.cerrarConexion();

            if (FicheroSalida.noHayCambios()){
                FicheroSalida.write("TODO OKEY!!");
            }

            FicheroSalida.close();
        }
        else {
            CentralLogger.logger.info("NO CORRE");
        }
    }

}
