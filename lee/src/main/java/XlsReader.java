import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class XlsReader {

    ArrayList<Producto> losProductos = new ArrayList<>();;

    public ArrayList<Producto> leePlanilla(String nombre) {
        try {

            String sFichero = nombre;
            File fichero = new File(sFichero);
            if (!fichero.exists()) {
                CentralLogger.logger.error("El fichero {} NO existe", sFichero);
                FicheroSalida.write("No existe el fichero " + sFichero);
            }
            try (FileInputStream fis = new FileInputStream(new File(sFichero));
                 Workbook workbook = new HSSFWorkbook(fis)) {

                // Obtén la primera hoja del libro
                Sheet sheet = workbook.getSheetAt(0);

                int nro_fila = 0;
                // Itera sobre las filas
                for (Row row : sheet) {
                    // Itera sobre las celdas
                    nro_fila++;
                    if (nro_fila > 2) {
                        getProducto(row);
                    }
                }
            }

            CentralLogger.logger.info("leida la planilla");

        } catch (
                IOException e) {
            CentralLogger.logger.error(e);
            try {
                FicheroSalida.write("Error de lectura planilla");
            } catch (IOException ex) {
                CentralLogger.logger.error(e);
            }

        }

        return losProductos;
    }

    private void getProducto(Row row) {
        String cod="";
        int lista=0;
        String moneda="";
        double  precio=0.0d;
        Producto p = null;
        for (Cell cell : row) {
            // Obtengo cual columna es
            CellReference referenciaCelda = new CellReference(cell);
            String columna = referenciaCelda.getCellRefParts()[2];

            if (columna.equals("A"))
                cod = cell.getStringCellValue();
            if (columna.equals("F"))
                lista = (int) cell.getNumericCellValue();
            if (columna.equals("H"))
                moneda = cell.getStringCellValue();
            if (columna.equals("J"))
                precio = cell.getNumericCellValue();
            }
        if (!cod.equals("")){
            p = new Producto(cod,lista,moneda,precio);
            losProductos.add(p);
            CentralLogger.logger.info(p);
        }
    }
}
