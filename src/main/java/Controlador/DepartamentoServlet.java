/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import DataAccess.DepartamentoDAO;
import com.udb.modelo.Departamento;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author josed
 */
@WebServlet(name = "DepartamentoServlet", urlPatterns = {"/DepartamentoServlet"})
public class DepartamentoServlet extends HttpServlet {

   DepartamentoDAO dao = new DepartamentoDAO();
 
    /**
     * Valida los datos del departamento antes de guardar
     */
    private ArrayList<String> validarDatos(String nombre, String descripcion) {
        ArrayList<String> errores = new ArrayList<>();
        
        Pattern NOMBRE_PATTERN = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{2,100}$");
        
        if (nombre == null || nombre.trim().isEmpty()) {
            errores.add("El nombre del departamento es obligatorio");
        } else if (nombre.trim().length() < 2) {
            errores.add("El nombre debe tener al menos 2 caracteres");
        } else if (nombre.trim().length() > 100) {
            errores.add("El nombre no puede exceder los 100 caracteres");
        } else if (!NOMBRE_PATTERN.matcher(nombre.trim()).matches()) {
            errores.add("El nombre debe contener solo letras y espacios");
        }
        
        if (descripcion != null && descripcion.trim().length() > 500) {
            errores.add("La descripción no puede exceder los 500 caracteres");
        }
        
        return errores;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
       // Configurar codificación UTF-8
       request.setCharacterEncoding("UTF-8");
       response.setCharacterEncoding("UTF-8");
       
       String action = request.getParameter("action");
       
       if (action == null || action.equals("listar")) {
           listarDepartamentos(request, response);
       } else if (action.equals("nuevo")) {
           mostrarFormularioRegistro(request, response);
       } else if (action.equals("guardar")) {
           guardarDepartamento(request, response);
       } else if (action.equals("editar")) {
           mostrarFormularioEdicion(request, response);
       } else if (action.equals("eliminar")) {
           eliminarDepartamento(request, response);
       } else {
           listarDepartamentos(request, response);
       }
    }
    
    private void listarDepartamentos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Departamento> lista = dao.listar();
        request.setAttribute("listaDepartamentos", lista);
        request.getRequestDispatcher("/departamentos.jsp").forward(request, response);
    }
    
    private void mostrarFormularioRegistro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/registrarDepartamento.jsp").forward(request, response);
    }
    
    private void guardarDepartamento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            String nombre = request.getParameter("nombreDepartamento");
            String descripcion = request.getParameter("descripcionDepartamento");
            String idParam = request.getParameter("idDepartamento");
            
            // Validar datos
            ArrayList<String> errores = validarDatos(nombre, descripcion);
            
            if (!errores.isEmpty()) {
                request.setAttribute("errores", errores);
                request.setAttribute("nombreDepartamento", nombre);
                request.setAttribute("descripcionDepartamento", descripcion);
                if (idParam != null && !idParam.trim().isEmpty()) {
                    Departamento dep = new Departamento();
                    dep.setIdDepartamento(Integer.parseInt(idParam));
                    dep.setNombreDepartamento(nombre);
                    dep.setDescripcionDepartamento(descripcion);
                    request.setAttribute("departamento", dep);
                }
                mostrarFormularioRegistro(request, response);
                return;
            }
            
            // Crear objeto departamento
            Departamento dep = new Departamento();
            dep.setNombreDepartamento(nombre.trim());
            dep.setDescripcionDepartamento(descripcion != null ? descripcion.trim() : "");
            
            boolean resultado;
            
            // Verificar si es actualización o inserción
            if (idParam != null && !idParam.trim().isEmpty()) {
                dep.setIdDepartamento(Integer.parseInt(idParam));
                resultado = dao.actualizar(dep);
            } else {
                resultado = dao.agregar(dep);
            }
            
            if (resultado) {
                String mensaje = (idParam != null && !idParam.trim().isEmpty()) 
                    ? "Departamento actualizado exitosamente" 
                    : "Departamento guardado exitosamente";
                request.setAttribute("mensaje", mensaje);
                listarDepartamentos(request, response);
            } else {
                request.setAttribute("error", "No se pudo guardar el departamento");
                request.setAttribute("nombreDepartamento", nombre);
                request.setAttribute("descripcionDepartamento", descripcion);
                if (idParam != null && !idParam.trim().isEmpty()) {
                    Departamento dep2 = new Departamento();
                    dep2.setIdDepartamento(Integer.parseInt(idParam));
                    dep2.setNombreDepartamento(nombre);
                    dep2.setDescripcionDepartamento(descripcion);
                    request.setAttribute("departamento", dep2);
                }
                mostrarFormularioRegistro(request, response);
            }
            
        } catch (NumberFormatException e) {
            request.setAttribute("error", "ID no válido");
            mostrarFormularioRegistro(request, response);
        } catch (Exception e) {
            System.out.println("Error en guardarDepartamento: " + e.getMessage());
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
                listarDepartamentos(request, response);
                return;
            }
            
            int id = Integer.parseInt(idParam);
            Departamento departamento = dao.obtenerPorId(id);
            
            if (departamento != null) {
                request.setAttribute("departamento", departamento);
                request.getRequestDispatcher("/registrarDepartamento.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Departamento no encontrado");
                listarDepartamentos(request, response);
            }
            
        } catch (NumberFormatException e) {
            request.setAttribute("error", "ID no válido");
            listarDepartamentos(request, response);
        }
    }
    
    private void eliminarDepartamento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id");
            
            if (idParam == null || idParam.trim().isEmpty()) {
                request.setAttribute("error", "ID no válido");
                listarDepartamentos(request, response);
                return;
            }
            
            int id = Integer.parseInt(idParam);
            boolean resultado = dao.eliminar(id);
            
            if (resultado) {
                request.setAttribute("mensaje", "Departamento eliminado exitosamente");
            } else {
                request.setAttribute("error", "No se pudo eliminar el departamento");
            }
            
            listarDepartamentos(request, response);
            
        } catch (NumberFormatException e) {
            request.setAttribute("error", "ID no válido");
            listarDepartamentos(request, response);
        } catch (Exception e) {
            System.out.println("Error en eliminarDepartamento: " + e.getMessage());
            request.setAttribute("error", "Error al eliminar: " + e.getMessage());
            listarDepartamentos(request, response);
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

    
  
}
