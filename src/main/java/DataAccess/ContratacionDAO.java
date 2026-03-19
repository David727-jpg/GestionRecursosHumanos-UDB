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
import com.udb.modelo.Contratacion;
import com.udb.modelo.Departamento;
import com.udb.modelo.Empleado;
import com.udb.modelo.Cargo;
import com.udb.modelo.TipoContratacion;
/**
 *
 * @author josed
 */
public class ContratacionDAO {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    private String ultimoError = "";

    public String getUltimoError() {
        return ultimoError;
    }
    
    public boolean agregar(Contratacion cont) {
        String sql = "INSERT INTO contratacion (id_departamento, id_empleado, id_cargo, id_tipo_contratacion, fecha_contratacion, salario, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, cont.getIdDepartamento());
            ps.setInt(2, cont.getIdEmpleado());
            ps.setInt(3, cont.getIdCargo());
            ps.setInt(4, cont.getIdTipoContratacion());
            ps.setDate(5, new java.sql.Date(cont.getFechaDeContratacion().getTime()));
            ps.setDouble(6, cont.getSalario());
            ps.setBoolean(7, cont.isEstado());
            
            ps.executeUpdate();
            ultimoError = "";
            return true;
        } catch (SQLIntegrityConstraintViolationException e) {
            if (e.getMessage().contains("fk_contratacion_empleado")) {
                ultimoError = "El empleado seleccionado no existe";
            } else if (e.getMessage().contains("fk_contratacion_depto")) {
                ultimoError = "El departamento seleccionado no existe";
            } else if (e.getMessage().contains("fk_contratacion_cargo")) {
                ultimoError = "El cargo seleccionado no existe";
            } else if (e.getMessage().contains("fk_contratacion_tipo")) {
                ultimoError = "El tipo de contratación seleccionado no existe";
            } else {
                ultimoError = "Error de integridad de datos: " + e.getMessage();
            }
            System.out.println("Error de integridad al agregar contratación: " + e.getMessage());
            return false;
        } catch (SQLException e) {
            ultimoError = "Error de base de datos: " + e.getMessage();
            System.out.println("Error SQL al agregar contratación: " + e.getMessage());
            return false;
        } catch (Exception e) {
            ultimoError = "Error inesperado: " + e.getMessage();
            System.out.println("Error al agregar contratación: " + e.getMessage());
            return false;
        } finally {
            cn.desconectar();
        }
    }
    
    public List<Contratacion> listar() {
        List<Contratacion> lista = new ArrayList<>();
        String sql = "SELECT c.*, " +
                     "d.nombre_departamento, " +
                     "e.nombre_empleado, e.dui_empleado, " +
                     "ca.nombre_cargo, " +
                     "tc.tipo_contratacion " +
                     "FROM contratacion c " +
                     "INNER JOIN departamento d ON c.id_departamento = d.id_departamento " +
                     "INNER JOIN empleado e ON c.id_empleado = e.id_empleado " +
                     "INNER JOIN cargo ca ON c.id_cargo = ca.id_cargo " +
                     "INNER JOIN tipo_contratacion tc ON c.id_tipo_contratacion = tc.id_tipo_contratacion " +
                     "ORDER BY c.id_contratacion DESC";
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
                
                Departamento dept = new Departamento();
                dept.setIdDepartamento(rs.getInt("id_departamento"));
                dept.setNombreDepartamento(rs.getString("nombre_departamento"));
                cont.setDepartamento(dept);
                
                Empleado emp = new Empleado();
                emp.setIdEmpleado(rs.getInt("id_empleado"));
                emp.setNombreEmpleado(rs.getString("nombre_empleado"));
                emp.setDuiEmpleado(rs.getString("dui_empleado"));
                cont.setEmpleado(emp);
                
                Cargo cargo = new Cargo();
                cargo.setIdCargo(rs.getInt("id_cargo"));
                cargo.setCargo(rs.getString("nombre_cargo"));
                cont.setCargo(cargo);
                
                TipoContratacion tc = new TipoContratacion();
                tc.setIdTipoContratacion(rs.getInt("id_tipo_contratacion"));
                tc.setTipoContratacion(rs.getString("tipo_contratacion"));
                cont.setTipoContratacion(tc);
                
                lista.add(cont);
            }
        } catch (Exception e) {
            System.out.println("Error al listar contrataciones: " + e.getMessage());
        } finally {
            cn.desconectar();
        }
        return lista;
    }
    
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
            ps.setBoolean(7, cont.isEstado());
            ps.setInt(8, cont.getIdContratacion());
            
            int filasAfectadas = ps.executeUpdate();
            ultimoError = "";
            return filasAfectadas > 0;
        } catch (SQLIntegrityConstraintViolationException e) {
            if (e.getMessage().contains("fk_contratacion_empleado")) {
                ultimoError = "El empleado seleccionado no existe";
            } else if (e.getMessage().contains("fk_contratacion_depto")) {
                ultimoError = "El departamento seleccionado no existe";
            } else if (e.getMessage().contains("fk_contratacion_cargo")) {
                ultimoError = "El cargo seleccionado no existe";
            } else if (e.getMessage().contains("fk_contratacion_tipo")) {
                ultimoError = "El tipo de contratación seleccionado no existe";
            } else {
                ultimoError = "Error de integridad de datos: " + e.getMessage();
            }
            System.out.println("Error de integridad al actualizar contratación: " + e.getMessage());
            return false;
        } catch (SQLException e) {
            ultimoError = "Error de base de datos: " + e.getMessage();
            System.out.println("Error SQL al actualizar contratación: " + e.getMessage());
            return false;
        } catch (Exception e) {
            ultimoError = "Error inesperado: " + e.getMessage();
            System.out.println("Error al actualizar contratación: " + e.getMessage());
            return false;
        } finally {
            cn.desconectar();
        }
    }
    
    public boolean eliminar(int id) {
        String sql = "DELETE FROM contratacion WHERE id_contratacion = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (Exception e) {
            System.out.println("Error al eliminar contratación: " + e.getMessage());
            return false;
        } finally {
            cn.desconectar();
        }
    }
    
    public Contratacion obtenerPorId(int id) {
        Contratacion cont = null;
        String sql = "SELECT * FROM contratacion WHERE id_contratacion = ?";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                cont = new Contratacion();
                cont.setIdContratacion(rs.getInt("id_contratacion"));
                cont.setIdDepartamento(rs.getInt("id_departamento"));
                cont.setIdEmpleado(rs.getInt("id_empleado"));
                cont.setIdCargo(rs.getInt("id_cargo"));
                cont.setIdTipoContratacion(rs.getInt("id_tipo_contratacion"));
                cont.setFechaDeContratacion(rs.getDate("fecha_contratacion"));
                cont.setSalario(rs.getDouble("salario"));
                cont.setEstado(rs.getBoolean("estado"));
            }
        } catch (Exception e) {
            System.out.println("Error al obtener contratación: " + e.getMessage());
        } finally {
            cn.desconectar();
        }
        return cont;
    }
      
}
