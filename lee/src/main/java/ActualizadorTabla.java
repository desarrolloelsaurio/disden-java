import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class ActualizadorTabla {

    private Connection connection;

    public ActualizadorTabla() {
        try {
            Properties config = Configuracion.lee(".//config.properties");
            connection = new Conector(config.getProperty("empresa"), config.getProperty("servidor"), config.getProperty("puerto")).getConnection();
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
                if (cuantos != 1) {
                    FicheroSalida.write(cuantos + " - " + dato);
                }
                CentralLogger.logger.info("{}-{}", cuantos, dato);
            }

            //   Para las listas 4 y 1
            String sql_actualiza_4_1 = "UPDATE gva17 SET precio = g2.precio FROM gva17 " +
                    "JOIN gva17 AS g2 ON gva17.cod_articu = g2.cod_articu  " +
                    "WHERE gva17.precio = 0 AND gva17.nro_de_lis = 4 AND g2.nro_de_lis = 1;";

            // Para las listas 3 y 2
            String sql_actualiza_3_2 = "UPDATE gva17 SET precio = g2.precio FROM gva17 " +
                    "JOIN gva17 AS g2 ON gva17.cod_articu = g2.cod_articu " +
                    "WHERE gva17.precio = 0 AND gva17.nro_de_lis = 3 AND g2.nro_de_lis = 2;";

            Statement stmt = connection.createStatement();
            int i1 = stmt.executeUpdate(sql_actualiza_4_1);
            int i2 = stmt.executeUpdate(sql_actualiza_3_2);

            CentralLogger.logger.info("{}-{}", i1, sql_actualiza_4_1);
            CentralLogger.logger.info("{}-{}", i2, sql_actualiza_3_2);

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
