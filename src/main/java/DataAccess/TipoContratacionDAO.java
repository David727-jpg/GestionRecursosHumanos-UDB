/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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
    
    private String ultimoError = "";

    public String getUltimoError() {
        return ultimoError;
    }
    
    // ================== AGREGAR ==================
    public boolean agregar(TipoContratacion tc) {
        String sql = "INSERT INTO tipo_contratacion (tipo_contratacion) VALUES (?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, tc.getTipoContratacion());
            ps.executeUpdate();
            ultimoError = "";
            return true;
        } catch (SQLIntegrityConstraintViolationException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                ultimoError = "Ya existe un tipo de contratación con este nombre";
            } else {
                ultimoError = "Error de integridad de datos: " + e.getMessage();
            }
            System.out.println("Error de integridad al agregar tipo de contratación: " + e.getMessage());
            return false;
        } catch (SQLException e) {
            ultimoError = "Error de base de datos: " + e.getMessage();
            System.out.println("Error SQL al agregar tipo de contratación: " + e.getMessage());
            return false;
        } catch (Exception e) {
            ultimoError = "Error inesperado: " + e.getMessage();
            System.out.println("Error al agregar tipo de contratación: " + e.getMessage());
            return false;
        } finally {
            cn.desconectar();
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
        } finally {
            cn.desconectar();
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
            int filasAfectadas = ps.executeUpdate();
            ultimoError = "";
            return filasAfectadas > 0;
        } catch (SQLIntegrityConstraintViolationException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                ultimoError = "Ya existe un tipo de contratación con este nombre";
            } else {
                ultimoError = "Error de integridad de datos: " + e.getMessage();
            }
            System.out.println("Error de integridad al actualizar tipo de contratación: " + e.getMessage());
            return false;
        } catch (SQLException e) {
            ultimoError = "Error de base de datos: " + e.getMessage();
            System.out.println("Error SQL al actualizar tipo de contratación: " + e.getMessage());
            return false;
        } catch (Exception e) {
            ultimoError = "Error inesperado: " + e.getMessage();
            System.out.println("Error al actualizar tipo de contratación: " + e.getMessage());
            return false;
        } finally {
            cn.desconectar();
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
        } finally {
            cn.desconectar();
        }
    }
    
    // ================== OBTENER POR ID ==================
    public TipoContratacion obtenerPorId(int id) {
        TipoContratacion tc = null;
        String sql = "SELECT * FROM tipo_contratacion WHERE id_tipo_contratacion = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                tc = new TipoContratacion();
                tc.setIdTipoContratacion(rs.getInt("id_tipo_contratacion"));
                tc.setTipoContratacion(rs.getString("tipo_contratacion"));
            }
        } catch (Exception e) {
            System.out.println("Error al obtener tipo de contratación por ID: " + e.getMessage());
        } finally {
            cn.desconectar();
        }
        return tc;
    }
}   
    

