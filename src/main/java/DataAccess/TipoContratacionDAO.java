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
import com.udb.modelo.TipoContratacion;
/**
 *
 * @author josed
 */
public class TipoContratacionDAO {
 
 Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    // ================== AGREGAR ==================
    public boolean agregar(TipoContratacion tc) {
        String sql = "INSERT INTO tipo_contratacion (tipo_contratacion) VALUES (?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, tc.getTipoContratacion());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al agregar tipo de contratación: " + e.getMessage());
            return false;
        }
    }
    
    // ================== LISTAR ==================
    public List<TipoContratacion> listar() {
        List<TipoContratacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM tipo_contratacion";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                TipoContratacion tc = new TipoContratacion();
                tc.setIdTipoContratacion(rs.getInt("id_tipo_contratacion"));
                tc.setTipoContratacion(rs.getString("tipo_contratacion"));
                lista.add(tc);
            }
        } catch (Exception e) {
            System.out.println("Error al listar tipos de contratación: " + e.getMessage());
        }
        return lista;
    }
    
    // ================== ACTUALIZAR ==================
    public boolean actualizar(TipoContratacion tc) {
        String sql = "UPDATE tipo_contratacion SET tipo_contratacion = ? WHERE id_tipo_contratacion = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, tc.getTipoContratacion());
            ps.setInt(2, tc.getIdTipoContratacion());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar tipo de contratación: " + e.getMessage());
            return false;
        }
    }
    
    // ================== ELIMINAR ==================
    public boolean eliminar(int id) {
        String sql = "DELETE FROM tipo_contratacion WHERE id_tipo_contratacion = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar tipo de contratación: " + e.getMessage());
            return false;
        }
    }
}   
    

