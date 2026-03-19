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
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 *
 * @author josed
 */
public class EmpleadoDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    private String ultimoError = "";

    public String getUltimoError() {
        return ultimoError;
    }

    public boolean agregar(Empleado emp) {
        String sql = "INSERT INTO empleado (dui_empleado, nombre_empleado, usuario_empleado, telefono_empleado, correo_empleado, fecha_nacimiento_empleado) VALUES (?,?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, emp.getDuiEmpleado());
            ps.setString(2, emp.getNombreEmpleado());
            ps.setString(3, emp.getUsuarioEmpleado());
            ps.setString(4, emp.getTelefonoEmpleado());
            ps.setString(5, emp.getCorreoEmpleado());
            ps.setDate(6, new java.sql.Date(emp.getFechadenacimientoEmpleado().getTime()));

            ps.executeUpdate();
            ultimoError = "";
            return true;

        } catch (SQLIntegrityConstraintViolationException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                if (e.getMessage().contains("numeroDui")) {
                    ultimoError = "El DUI ya está registrado en el sistema";
                } else if (e.getMessage().contains("usuario")) {
                    ultimoError = "El usuario ya está en uso";
                } else {
                    ultimoError = "Ya existe un registro con estos datos";
                }
            } else {
                ultimoError = "Error de integridad de datos: " + e.getMessage();
            }
            System.out.println("Error de integridad al agregar empleado: " + e.getMessage());
            return false;
        } catch (SQLException e) {
            // Otros errores SQL
            ultimoError = "Error de base de datos: " + e.getMessage();
            System.out.println("Error SQL al agregar empleado: " + e.getMessage());
            return false;
        } catch (Exception e) {
            // Errores generales
            ultimoError = "Error inesperado: " + e.getMessage();
            System.out.println("Error al agregar empleado: " + e.getMessage());
            return false;
        } finally {
            cn.desconectar();
        }
    }

    public List<Empleado> listar() {
        return listar(false);
    }

    public List<Empleado> listar(boolean soloSinContratacion) {
        List<Empleado> lista = new ArrayList<>();
        String sql;

        if (soloSinContratacion) {
            sql = "SELECT e.id_empleado, e.dui_empleado, e.nombre_empleado " +
                  "FROM empleado e " +
                  "LEFT JOIN contratacion c ON e.id_empleado = c.id_empleado " +
                  "WHERE c.id_contratacion IS NULL";
        } else {
            sql = "SELECT * FROM empleado";
        }

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Empleado emp = new Empleado();

                emp.setIdEmpleado(rs.getInt("id_empleado"));
                emp.setDuiEmpleado(rs.getString("dui_empleado"));
                emp.setNombreEmpleado(rs.getString("nombre_empleado"));

                if (!soloSinContratacion) {
                    emp.setUsuarioEmpleado(rs.getString("usuario_empleado"));
                    emp.setTelefonoEmpleado(rs.getString("telefono_empleado"));
                    emp.setCorreoEmpleado(rs.getString("correo_empleado"));
                    if (rs.getDate("fecha_nacimiento_empleado") != null) {
                        emp.setFechadenacimientoEmpleado(
                                new java.util.Date(rs.getDate("fecha_nacimiento_empleado").getTime()));
                    }
                }

                lista.add(emp);
            }
        } catch (Exception e) {
            System.out.println("Error al listar empleados: " + e.getMessage());
        } finally {
            cn.desconectar();
        }
        return lista;
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM empleado WHERE id_empleado = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar empleado: " + e.getMessage());
            return false;
        } finally {
            cn.desconectar();
        }
    }

    public boolean actualizar(Empleado emp) {
        String sql = "UPDATE empleado SET dui_empleado=?, nombre_empleado=?, usuario_empleado=?, "
                + "telefono_empleado=?, correo_empleado=?, fecha_nacimiento_empleado=? "
                + "WHERE id_empleado=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, emp.getDuiEmpleado());
            ps.setString(2, emp.getNombreEmpleado());
            ps.setString(3, emp.getUsuarioEmpleado());
            ps.setString(4, emp.getTelefonoEmpleado());
            ps.setString(5, emp.getCorreoEmpleado());
            ps.setDate(6, new java.sql.Date(emp.getFechadenacimientoEmpleado().getTime()));
            ps.setInt(7, emp.getIdEmpleado());

            int filasAfectadas = ps.executeUpdate();
            ultimoError = "";
            return filasAfectadas > 0;
        } catch (SQLIntegrityConstraintViolationException e) {
            // Error de integridad: clave duplicada, violación de FK, etc.
            if (e.getMessage().contains("Duplicate entry")) {
                if (e.getMessage().contains("numeroDui")) {
                    ultimoError = "El DUI ya está registrado en el sistema";
                } else if (e.getMessage().contains("usuario")) {
                    ultimoError = "El usuario ya está en uso";
                } else {
                    ultimoError = "Ya existe un registro con estos datos";
                }
            } else {
                ultimoError = "Error de integridad de datos: " + e.getMessage();
            }
            System.out.println("Error de integridad al actualizar empleado: " + e.getMessage());
            return false;
        } catch (SQLException e) {
            // Otros errores SQL
            ultimoError = "Error de base de datos: " + e.getMessage();
            System.out.println("Error SQL al actualizar empleado: " + e.getMessage());
            return false;
        } catch (Exception e) {
            // Errores generales
            ultimoError = "Error inesperado: " + e.getMessage();
            System.out.println("Error al actualizar empleado: " + e.getMessage());
            return false;
        } finally {
            cn.desconectar();
        }
    }

    public Empleado obtenerPoId(int id) {
        Empleado emp = null;
        String sql = "SELECT * FROM empleado WHERE id_empleado = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                emp = new Empleado();
                emp.setIdEmpleado(rs.getInt("id_empleado"));
                emp.setNombreEmpleado(rs.getString("nombre_empleado"));
                emp.setDuiEmpleado(rs.getString("dui_empleado"));
                emp.setUsuarioEmpleado(rs.getString("usuario_empleado"));
                emp.setTelefonoEmpleado(rs.getString("telefono_empleado"));
                emp.setCorreoEmpleado(rs.getString("correo_empleado"));
                if (rs.getDate("fecha_nacimiento_empleado") != null) {
                    emp.setFechadenacimientoEmpleado(
                            new java.util.Date(rs.getDate("fecha_nacimiento_empleado").getTime()));
                }
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar empleado: " + e.getMessage());
        } finally {
            cn.desconectar();
        }
        return emp;
    }
}
