import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class ActualizadorTabla {

    private Connection connection;

    public ActualizadorTabla() {
        try {
            Properties config = Configuracion.lee(".//config.properties");
            connection = new Conector(config.getProperty("empresa"),config.getProperty("servidor"),config.getProperty("puerto")).getConnection();
        } catch (FileNotFoundException e) {
            CentralLogger.logger.error(e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            CentralLogger.logger.error(e);
            throw new RuntimeException(e);
        }
    }

    public void actualizarTabla(ArrayList<Producto> datos, String tabla) {
        String sql = "UPDATE " + tabla + " SET PRECIO = ?, FECHA_MODI = GETDATE() WHERE COD_ARTICU = ? AND NRO_DE_LIS=?";

/*
*
UPDATE dbo.GVA17
SET FILLER = @filler,
	COD_ARTICU = @cod_articu,
	NRO_DE_LIS = @nro_de_lis,
	PRECIO = @precio,
	FECHA_MODI = @fecha_modi,
	BASE = @base,
	FECHA_PRECIOS_PEDIDOS = @fecha_precios_pedidos,
	ID_STA11 = @id_sta11,
	ID_GVA10 = @id_gva10,
	ROW_VERSION = @row_version,
	ID_GVA17 = @id_gva17,
	ID_MAESTRO_GVA17 = @id_maestro_gva17
*
* */
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            for (Producto dato : datos) {
                CentralLogger.logger.info(dato);
            }
            for (Producto dato : datos) {
                pstmt.setDouble(1, dato.getPrecio()); // Establece el valor para la columna
                pstmt.setString(2, dato.getCodigo()); // Define la condición para la actualización
                pstmt.setInt(3, dato.getLista()); // Establece el valor para la columna
                int cuantos = pstmt.executeUpdate(); // Ejecuta la actualización
                if (cuantos!=1){
                    FicheroSalida.write(cuantos+" - "+dato);
                }
                CentralLogger.logger.info("{}-{}",cuantos,dato);
            }
        } catch (SQLException e) {
            CentralLogger.logger.error(e);
        } catch (IOException e) {
            CentralLogger.logger.error(e);
        }
    }

    public void cerrarConexion() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            CentralLogger.logger.error(e);
        }
    }
}
