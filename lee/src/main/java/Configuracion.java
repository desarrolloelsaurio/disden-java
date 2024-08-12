import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuracion {
    public static Properties lee(String archivo) throws IOException {
        Properties config = new Properties();
        InputStream configInput = new FileInputStream(archivo);
        config.load(configInput);
        return config;
    }
}
