import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @web http://www.jc-mouse.net
 * @author Mouse
 */
public class Conector {
 /* DATOS PARA LA CONEXION */
//  private String bd = "CALAMANTE_DISDEN_SH\\AXSQLEXPRESS_1:49258";//BASE DE DATOS
  private String login = "user=sa;"; //USUARIO
  private String password = "password=Axoft1988;"; //CONTRASENA
  private String url;
  private Connection conn = null;

  public Connection getConnection()
  {
      return this.conn;
  }

  /* Constructor de clase: Se conecta a la base de datos
  */
   public Conector(String empresa, String servidor, String puerto){
      try{
         //obtenemos el driver de para mysql
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
         //obtenemos la conexión
         url = "jdbc:sqlserver://"+servidor+"\\AXSQLEXPRESS:"+puerto+";databaseName="+empresa+";"+
                 ";encrypt=true;trustServerCertificate=true;";
         System.out.println(url+login+password);
         conn = DriverManager.getConnection(url+login+password);
         
         if (conn!=null){
            System.out.println("OK base de datos "+empresa+" listo");
         }
         
                 
      }catch(SQLException e){
     	 System.out.println("Error sql"); 
         System.out.println(e);
    	 System.exit(0);
      }catch(ClassNotFoundException e){
    	 System.out.println("Error clase"); 
         System.out.println(e);
    	 System.exit(0);
         
      }
    }

 
}
