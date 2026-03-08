/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;
import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.udb.modelo.Empleado;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author josed
 */
public class EmpleadoDAO {
   
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean agregar(Empleado emp){
        
        String sql = "INSERT INTO Empleado ( nombre_empleado, dui_empleado, usuario_empleado, telefono_empleado, correo_empleado, fecha_nacimiento_empleado) VALUES (?,?,?,?,?,?)";
        
        try {
            
            con = cn.getConnection(); 
            ps = con.prepareStatement(sql); 
            //agregar Empleado
            ps.setString(1, emp.getNombreEmpleado());
            ps.setString(2, emp.getDuiEmpleado());
            ps.setString(3, emp.getUsuarioEmpleado());
            ps.setString(4, emp.getTelefonoEmpleado());
            ps.setString(5, emp.getCorreoEmpleado());
            ps.setDate(6, new java.sql.Date(emp.getFechadenacimientoEmpleado().getTime()));
            
            ps.executeUpdate();
            return true;
            
        } catch(Exception e) {
            System.out.println("Error al agregar al empleado");
            return false;
            
        }
    }
        
        public List<Empleado> listar() {
          List<Empleado> lista = new ArrayList<>();
          String sql = "SELECT * FROM Empleado";
          
         try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Empleado emp = new Empleado();
                
                emp.setIdEmpleado(rs.getInt("id_empleado"));
                emp.setNombreEmpleado(rs.getString("nombre_empleado"));
                emp.setDuiEmpleado(rs.getString("dui_empleado"));
                emp.setUsuarioEmpleado(rs.getString("usuario_empleado"));
                emp.setTelefonoEmpleado(rs.getString("telefono_empleado"));
                emp.setCorreoEmpleado(rs.getString("correo_empleado"));
                emp.setFechadenacimientoEmpleado(rs.getDate("fecha_nacimiento_empleado")); 
                
                lista.add(emp); 
            }
        } catch (Exception e) {
            System.out.println("Error al listar empleados: " + e.getMessage());
        }
        
        
        return lista;
    }
        
    public boolean eliminar(int id) {
        
        String sql = "DELETE FROM Empleado WHERE id_empleado = ?";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            
            ps.setInt(1, id);
            
            ps.executeUpdate(); 
            return true;
            
        } catch (Exception e) {
            System.out.println("Error al eliminar empleado: " + e.getMessage());
            return false;
        }
    }
        
        public boolean actualizar(Empleado emp) {
       
        String sql = "UPDATE Empleado SET nombre_empleado = ?, dui_empleado = ?, usuario_empleado = ?, telefono_empleado = ?, correo_empleado = ?, fecha_nacimiento_empleado = ? WHERE id_empleado = ?";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
          
            ps.setString(1, emp.getNombreEmpleado());
            ps.setString(2, emp.getDuiEmpleado());
            ps.setString(3, emp.getUsuarioEmpleado());
            ps.setString(4, emp.getTelefonoEmpleado());
            ps.setString(5, emp.getCorreoEmpleado());
            
           
            ps.setDate(6, new java.sql.Date(emp.getFechadenacimientoEmpleado().getTime()));
            
            
            ps.setInt(7, emp.getIdEmpleado());
            
            ps.executeUpdate(); 
            return true;
            
        } catch (Exception e) {
            System.out.println("Error al actualizar empleado: " + e.getMessage());
            return false;
        }
        
        
          
    }
     
        
               
}

                   
             
    
        
           

    
    
    
    
    
    

