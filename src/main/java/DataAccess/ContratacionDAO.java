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
import com.udb.modelo.Contratacion;
/**
 *
 * @author josed
 */
public class ContratacionDAO {
    
Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    // ================== AGREGAR ==================
    public boolean agregar(Contratacion cont) {
        // Son 7 datos a insertar, por lo tanto 7 signos de interrogación
        String sql = "INSERT INTO contratacion (id_departamento, id_empleado, id_cargo, id_tipo_contratacion, fecha_contratacion, salario, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            // Los 4 IDs foráneos
            ps.setInt(1, cont.getIdDepartamento());
            ps.setInt(2, cont.getIdEmpleado());
            ps.setInt(3, cont.getIdCargo());
            ps.setInt(4, cont.getIdTipoContratacion());
            
            // El truco de la fecha que dominaste en Empleado
            ps.setDate(5, new java.sql.Date(cont.getFechaDeContratacion().getTime()));
            
            // El salario (Double) y el estado (Boolean)
            ps.setDouble(6, cont.getSalario());
            ps.setBoolean(7, cont.isEstado()); // O cont.isEstado() si NetBeans lo nombró así
            
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al agregar contratación: " + e.getMessage());
            return false;
        }
    }
    
    // ================== LISTAR ==================
    public List<Contratacion> listar() {
        List<Contratacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM contratacion";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Contratacion cont = new Contratacion();
                
                cont.setIdContratacion(rs.getInt("id_contratacion"));
                cont.setIdDepartamento(rs.getInt("id_departamento"));
                cont.setIdEmpleado(rs.getInt("id_empleado"));
                cont.setIdCargo(rs.getInt("id_cargo"));
                cont.setIdTipoContratacion(rs.getInt("id_tipo_contratacion"));
                
                cont.setFechaDeContratacion(rs.getDate("fecha_contratacion"));
                cont.setSalario(rs.getDouble("salario"));
                cont.setEstado(rs.getBoolean("estado"));
                
                lista.add(cont);
            }
        } catch (Exception e) {
            System.out.println("Error al listar contrataciones: " + e.getMessage());
        }
        return lista;
    }
    
    // ================== ACTUALIZAR ==================
    public boolean actualizar(Contratacion cont) {
        String sql = "UPDATE contratacion SET id_departamento = ?, id_empleado = ?, id_cargo = ?, id_tipo_contratacion = ?, fecha_contratacion = ?, salario = ?, estado = ? WHERE id_contratacion = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, cont.getIdDepartamento());
            ps.setInt(2, cont.getIdEmpleado());
            ps.setInt(3, cont.getIdCargo());
            ps.setInt(4, cont.getIdTipoContratacion());
            ps.setDate(5, new java.sql.Date(cont.getFechaDeContratacion().getTime()));
            ps.setDouble(6, cont.getSalario());
            ps.setBoolean(7, cont.isEstado()); // O cont.isEstado()
            
            // El ID de la contratación que vamos a modificar (El WHERE)
            ps.setInt(8, cont.getIdContratacion());
            
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar contratación: " + e.getMessage());
            return false;
        }
    }
    
    // ================== ELIMINAR ==================
    public boolean eliminar(int id) {
        String sql = "DELETE FROM contratacion WHERE id_contratacion = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar contratación: " + e.getMessage());
            return false;
        }
    }
    
      
}
