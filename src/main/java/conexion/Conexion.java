/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author josed
 */
public class Conexion {
    
 // 1.  variables con los datos de XAMPP
    private final String URL = "jdbc:mysql://localhost:3306/db_recursos_humanos";
    private final String USER = "root";
    private final String PASS = ""; 
    
    private Connection con;

    // 2.  método para conectar
    public Connection getConnection() {
        try {
            // Cargar el driver 
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Intentar establecer la conexión
            con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("¡Conexión exitosa a la base de datos!");
            
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Falta el Driver de MySQL. " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error de conexión con la base de datos. " + e.getMessage());
        }
        return con;
    }
    public static void main(String[] args) {
        Conexion prueba = new Conexion();
        prueba.getConnection();
    }
}  
    
    
    
    

