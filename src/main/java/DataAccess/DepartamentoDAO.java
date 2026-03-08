/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;
import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.udb.modelo.Departamento;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author josed
 */
public class DepartamentoDAO {
    
   Conexion cn = new Conexion();
   Connection con;
   PreparedStatement ps;
   ResultSet rs;
   
   public boolean agregar(Departamento dep) {
       
        String sql = "INSERT INTO departamento (nombre_departamento, descripcion_departamento) VALUES (?,?)";
        
        try {
            con = cn.getConnection(); 
            ps = con.prepareStatement(sql); 
            
            
          ps.setString(1,dep.getNombreDepartamento());
          ps.setString(2,dep.getDescripcionDepartamento());
          
          
            
            ps.executeUpdate(); 
            return true; 
            
        } catch (Exception e) {
            System.out.println("Error al guardar: " + e.getMessage());
            return false;
        }
   
}
 
   public List<Departamento> listar() {
        List<Departamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM departamento";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(); 
            
            
            
            while (rs.next()) {
                Departamento dep = new Departamento();
                
                dep.setIdDepartamento(rs.getInt("id_departamento"));
                dep.setNombreDepartamento(rs.getString("nombre_departamento"));
                dep.setDescripcionDepartamento(rs.getString("descripcion_departamento"));
                
     
                lista.add(dep); 
            }
        } catch (Exception e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
        return lista;
   }
        
        public boolean eliminar(int id) {
         String sql = "DELETE FROM departamento WHERE id_departamento = ?";
         
         try{
             con = cn.getConnection();
             ps = con.prepareStatement(sql);
             
             ps.setInt(1, id);
             
             ps.executeUpdate();
             return true;
             
         }catch(Exception e){
             System.out.println("Error al eliminar: " + e.getMessage());
             return false;
             
         }
        }
         
       public boolean actualizar(Departamento dep) {
        
        String sql = "UPDATE departamento SET nombre_departamento = ?, descripcion_departamento = ? WHERE id_departamento = ?";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            
            ps.setString(1, dep.getNombreDepartamento());
            ps.setString(2, dep.getDescripcionDepartamento());
            
            
            ps.setInt(3, dep.getIdDepartamento());
            
            ps.executeUpdate(); 
            return true;
            
        } catch (Exception e) {
            System.out.println("Error al actualizar: " + e.getMessage());
            return false;
        }
    }  
         
      public static void main(String[] args) {
        DepartamentoDAO dao = new DepartamentoDAO();
        
       
        Departamento deptoModificado = new Departamento();
        deptoModificado.setIdDepartamento(2); // OJO: Pon aquí el ID que sobrevivió en tu base de datos
        deptoModificado.setNombreDepartamento("Soporte Técnico");
        deptoModificado.setDescripcionDepartamento("Los que arreglan las impresoras.");
        
        
        System.out.println("Actualizando el departamento... ⏳");
        dao.actualizar(deptoModificado);
        
        
        System.out.println("\n--- DEPARTAMENTOS ACTUALIZADOS ---");
        List<Departamento> listaDePrueba = dao.listar();
        for (Departamento dep : listaDePrueba) {
            System.out.println("ID: " + dep.getIdDepartamento() + " | Nombre: " + dep.getNombreDepartamento()+ " | Desc: " + dep.getDescripcionDepartamento());
        }
    }   
          
    }
 
  
   
        
   
   
     

       
      

