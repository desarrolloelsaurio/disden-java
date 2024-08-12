import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class Habilitador {
    public static void main(String[] args) throws IOException {
        Properties conf_app = Configuracion.lee(".//app.properties");
        File fichero = new File(conf_app.getProperty("haycorrida"));
        if (!fichero.exists()) {
            fichero.createNewFile();
        }
    }
}
