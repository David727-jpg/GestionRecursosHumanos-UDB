/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import conexion.Conexion;
import com.udb.modelo.Cargo;
/**
 *
 * @author josed
 */
public class CargoDAO {
    
Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    // ================== AGREGAR ==================
    public boolean agregar(Cargo cargo) {
        String sql = "INSERT INTO cargo (nombre_cargo, descripcion_cargo, jefatura) VALUES (?, ?, ?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cargo.getCargo());
            ps.setString(2, cargo.getDescripcionCargo());
            
            // Usamos setBoolean para el estado de jefatura
            ps.setBoolean(3, cargo.isJefatura()); 
            
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al agregar cargo: " + e.getMessage());
            return false;
        }
    }
    
    // ================== LISTAR ==================
    public List<Cargo> listar() {
        List<Cargo> lista = new ArrayList<>();
        String sql = "SELECT * FROM cargo";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Cargo cargo = new Cargo();
                cargo.setIdCargo(rs.getInt("id_cargo"));
                cargo.setCargo(rs.getString("nombre_cargo"));
                cargo.setDescripcionCargo(rs.getString("descripcion_cargo"));
                
                // Usamos getBoolean para leer el tinyint(1) de MySQL y pasarlo a Java
                cargo.setJefatura(rs.getBoolean("jefatura")); 
                
                lista.add(cargo);
            }
        } catch (Exception e) {
            System.out.println("Error al listar cargos: " + e.getMessage());
        }
        return lista;
    }
    
    // ================== ACTUALIZAR ==================
    public boolean actualizar(Cargo cargo) {
        String sql = "UPDATE cargo SET nombre_cargo = ?, descripcion_cargo = ?, jefatura = ? WHERE id_cargo = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cargo.getCargo());
            ps.setString(2, cargo.getDescripcionCargo());
            
            // Usamos setBoolean
            ps.setBoolean(3, cargo.isJefatura()); 
            
            ps.setInt(4, cargo.getIdCargo());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar cargo: " + e.getMessage());
            return false;
        }
    }
    
    // ================== ELIMINAR ==================
    public boolean eliminar(int id) {
        String sql = "DELETE FROM cargo WHERE id_cargo = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar cargo: " + e.getMessage());
            return false;
        }
    }
    public com.udb.modelo.Cargo buscarPorId(int id) {
        com.udb.modelo.Cargo cargo = new com.udb.modelo.Cargo();
        
       
        String sql = "SELECT * FROM cargo WHERE id_cargo = ?"; 
        
        try {
            con = cn.getConnection(); 
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                
                cargo.setIdCargo(rs.getInt("id_cargo"));
                cargo.setCargo(rs.getString("nombre_cargo")); 
                cargo.setDescripcionCargo(rs.getString("descripcion_cargo"));
                cargo.setJefatura(rs.getBoolean("jefatura"));
                
            }
        } catch (Exception e) {
            System.out.println("Error al buscar cargo por ID: " + e.getMessage());
        }
        
        return cargo;
    }
    
    
}
