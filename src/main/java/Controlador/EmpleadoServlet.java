/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador; // CORREGIDO: Mayúscula

import DataAccess.EmpleadoDAO;
// import DataAccess.DepartamentoDAO;
import com.udb.modelo.Empleado;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Pattern;

@WebServlet(name = "EmpleadoServlet", urlPatterns = { "/EmpleadoServlet" })
public class EmpleadoServlet extends HttpServlet {

    EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    private ArrayList<String> validarDatos(String dui, String nombre, String usuario,
            String telefono, String correo, String fechaStr) {
        ArrayList<String> errores = new ArrayList<>();

        Pattern DUI_PATTERN = Pattern.compile("^\\d{8}-\\d{1}$");
        Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
        Pattern TELEFONO_PATTERN = Pattern.compile("^\\d{4}-\\d{4}$");
        Pattern NOMBRE_PATTERN = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{2,100}$");
        Pattern USUARIO_PATTERN = Pattern.compile("^[a-zA-Z0-9_]{4,20}$");

        if (dui == null || dui.trim().isEmpty()) {
            errores.add("El DUI es obligatorio");
        } else if (!DUI_PATTERN.matcher(dui.trim()).matches()) {
            errores.add("El DUI debe tener el formato XXXXXXXX-X");
        }

        if (nombre == null || nombre.trim().isEmpty()) {
            errores.add("El nombre es obligatorio");
        } else if (!NOMBRE_PATTERN.matcher(nombre.trim()).matches()) {
            errores.add("El nombre debe contener solo letras y espacios (2-100 caracteres)");
        }

        if (usuario == null || usuario.trim().isEmpty()) {
            errores.add("El usuario es obligatorio");
        } else if (!USUARIO_PATTERN.matcher(usuario.trim()).matches()) {
            errores.add("El usuario debe ser alfanumérico de 4-20 caracteres");
        }

        if (telefono != null && !telefono.trim().isEmpty()) {
            if (!TELEFONO_PATTERN.matcher(telefono.trim()).matches()) {
                errores.add("El teléfono debe tener el formato XXXX-XXXX");
            }
        }

        if (correo == null || correo.trim().isEmpty()) {
            errores.add("El correo es obligatorio");
        } else if (!EMAIL_PATTERN.matcher(correo.trim()).matches()) {
            errores.add("El correo no tiene un formato válido");
        }

        if (fechaStr == null || fechaStr.trim().isEmpty()) {
            errores.add("La fecha de nacimiento es obligatoria");
        } else {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.setLenient(false);
                Date fechaNacimiento = sdf.parse(fechaStr);

                Calendar fechaNac = Calendar.getInstance();
                fechaNac.setTime(fechaNacimiento);
                Calendar fechaActual = Calendar.getInstance();
                int edad = fechaActual.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);

                if (fechaActual.get(Calendar.DAY_OF_YEAR) < fechaNac.get(Calendar.DAY_OF_YEAR)) {
                    edad--;
                }

                if (edad < 18) {
                    errores.add("El empleado debe ser mayor de 18 años");
                } else if (edad > 100) {
                    errores.add("La fecha de nacimiento no es válida");
                }
            } catch (ParseException e) {
                errores.add("La fecha debe tener formato YYYY-MM-DD");
            }
        }

        return errores;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Configurar codificación UTF-8 para recibir correctamente caracteres con tildes
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        if (action == null || action.equals("listar")) {
            listarEmpleados(request, response);
        } else if (action.equals("nuevo")) {
            mostrarFormularioRegistro(request, response);
        } else if (action.equals("guardar")) {
            guardarEmpleado(request, response);
        } else if (action.equals("editar")) {
            mostrarFormularioEdicion(request, response);
        } else if (action.equals("eliminar")) {
            eliminarEmpleado(request, response);
        } else {
            listarEmpleados(request, response);
        }
    }

    private void listarEmpleados(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Empleado> lista = empleadoDAO.listar();
        request.setAttribute("empleados", lista);
        request.getRequestDispatcher("/listarEmpleados.jsp").forward(request, response);
    }

    private void mostrarFormularioRegistro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/registrarEmpleado.jsp").forward(request, response);
    }

    private void guardarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String dui = request.getParameter("dui");
            String nombre = request.getParameter("nombre");
            String usuario = request.getParameter("usuario");
            String telefono = request.getParameter("telefono");
            String correo = request.getParameter("correo");
            String fechaStr = request.getParameter("fechaNacimiento");
            // String idDepartamentoStr = request.getParameter("idDepartamento");

            ArrayList<String> errores = validarDatos(dui, nombre, usuario, telefono, correo, fechaStr);

            if (!errores.isEmpty()) {
                request.setAttribute("errores", errores);
                request.setAttribute("dui", dui);
                request.setAttribute("nombre", nombre);
                request.setAttribute("usuario", usuario);
                request.setAttribute("telefono", telefono);
                request.setAttribute("correo", correo);
                request.setAttribute("fechaNacimiento", fechaStr);
                mostrarFormularioRegistro(request, response);
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaNacimiento = sdf.parse(fechaStr);

            Empleado emp = new Empleado();
            emp.setDuiEmpleado(dui.replace("-", ""));
            emp.setNombreEmpleado(nombre);
            emp.setUsuarioEmpleado(usuario);
            emp.setTelefonoEmpleado(telefono);
            emp.setCorreoEmpleado(correo);
            emp.setFechadenacimientoEmpleado(fechaNacimiento);

            String idParam = request.getParameter("idEmpleado");
            boolean resultado;

            if (idParam != null && !idParam.trim().isEmpty()) {
                emp.setIdEmpleado(Integer.parseInt(idParam));
                resultado = empleadoDAO.actualizar(emp);
            } else {
                resultado = empleadoDAO.agregar(emp);
            }

            if (resultado) {
                request.setAttribute("mensaje", "Empleado guardado exitosamente");
            } else {
                String errorMsg = empleadoDAO.getUltimoError();
                request.setAttribute("errores", errores);
                request.setAttribute("dui", dui);
                request.setAttribute("nombre", nombre);
                request.setAttribute("usuario", usuario);
                request.setAttribute("telefono", telefono);
                request.setAttribute("correo", correo);
                request.setAttribute("fechaNacimiento", fechaStr);
                request.setAttribute("error", errorMsg.isEmpty() ? "No se pudo guardar el empleado" : errorMsg);
                mostrarFormularioRegistro(request, response);
            }

            listarEmpleados(request, response);
        } catch (Exception e) {
            System.out.println("Error en guardarEmpleado: " + e.getMessage());
            request.setAttribute("error", "Error al guardar: " + e.getMessage());
            mostrarFormularioRegistro(request, response);
        }
    }

    private void mostrarFormularioEdicion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id");

            if (idParam == null || idParam.trim().isEmpty()) {
                request.setAttribute("error", "ID no válido");
                listarEmpleados(request, response);
                return;
            }

            int id = Integer.parseInt(idParam);
            Empleado empleadoEncontrado = empleadoDAO.obtenerPoId(id);

            if (empleadoEncontrado != null) {
                request.setAttribute("empleado", empleadoEncontrado);

                String dui = empleadoEncontrado.getDuiEmpleado();
                if (dui != null && dui.length() == 9) {
                    dui = dui.substring(0, 8) + "-" + dui.substring(8);
                }
                request.setAttribute("dui", dui);
                request.setAttribute("nombre", empleadoEncontrado.getNombreEmpleado());
                request.setAttribute("usuario", empleadoEncontrado.getUsuarioEmpleado());
                request.setAttribute("telefono", empleadoEncontrado.getTelefonoEmpleado());
                request.setAttribute("correo", empleadoEncontrado.getCorreoEmpleado());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (empleadoEncontrado.getFechadenacimientoEmpleado() != null) {
                    request.setAttribute("fechaNacimiento",
                            sdf.format(empleadoEncontrado.getFechadenacimientoEmpleado()));
                }
                request.getRequestDispatcher("/registrarEmpleado.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Empleado no encontrado");
                listarEmpleados(request, response);
            }

        } catch (NumberFormatException e) {
            request.setAttribute("error", "ID no válido");
            listarEmpleados(request, response);
        }
    }

    private void eliminarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String idParam = request.getParameter("id");

            if (idParam == null || idParam.trim().isEmpty()) {
                request.setAttribute("error", "ID no válido");
                listarEmpleados(request, response);
                return;
            }

            int id = Integer.parseInt(idParam);
            boolean resultado = empleadoDAO.eliminar(id);

            if (resultado) {
                request.setAttribute("mensaje", "Empleado eliminado exitosamente");
            } else {
                request.setAttribute("error", "No se pudo eliminar el empleado. Puede tener contrataciones asociadas.");
            }

            listarEmpleados(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("error", "ID no válido");
            listarEmpleados(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para gestionar Empleados";
    }
}
