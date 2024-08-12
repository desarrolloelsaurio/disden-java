
import java.io.*;

    public class FicheroSalida
    {
        public static FileWriter fichero = null;
        public static int cambios = 0;

        public static void open(String nombre) throws IOException {
            fichero = new FileWriter(nombre);
        }

        public static void write(String texto) throws IOException {
            fichero.write(texto+"\n");
            cambios++;
        }

        public static boolean noHayCambios(){
            return 0 == cambios;
        }
        public static void close() throws IOException {
            fichero.close();
        }
    }
    