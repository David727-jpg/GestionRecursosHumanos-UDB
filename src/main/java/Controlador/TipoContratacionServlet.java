/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import DataAccess.TipoContratacionDAO;
import com.udb.modelo.TipoContratacion;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "TipoContratacionServlet", urlPatterns = {"/TipoContratacionServlet"})
public class TipoContratacionServlet extends HttpServlet {

    TipoContratacionDAO tipoContratacionDAO = new TipoContratacionDAO();

    private ArrayList<String> validarDatos(String tipoContratacion) {
        ArrayList<String> errores = new ArrayList<>();

        if (tipoContratacion == null || tipoContratacion.trim().isEmpty()) {
            errores.add("El tipo de contratación es obligatorio");
        } else if (tipoContratacion.trim().length() < 3) {
            errores.add("El tipo de contratación debe tener al menos 3 caracteres");
        } else if (tipoContratacion.trim().length() > 100) {
            errores.add("El tipo de contratación no puede exceder 100 caracteres");
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
            listarTiposContratacion(request, response);
        } else if (action.equals("nuevo")) {
            mostrarFormularioRegistro(request, response);
        } else if (action.equals("guardar")) {
            guardarTipoContratacion(request, response);
        } else if (action.equals("editar")) {
            mostrarFormularioEdicion(request, response);
        } else if (action.equals("eliminar")) {
            eliminarTipoContratacion(request, response);
        } else {
            listarTiposContratacion(request, response);
        }
    }

    private void listarTiposContratacion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<TipoContratacion> lista = tipoContratacionDAO.listar();
        request.setAttribute("tiposContratacion", lista);
        request.getRequestDispatcher("/listarTipoContratacion.jsp").forward(request, response);
    }

    private void mostrarFormularioRegistro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/registrarTipoContratacion.jsp").forward(request, response);
    }

    private void guardarTipoContratacion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String tipoContratacion = request.getParameter("tipoContratacion");
            String idParam = request.getParameter("idTipoContratacion");

            ArrayList<String> errores = validarDatos(tipoContratacion);

            if (!errores.isEmpty()) {
                request.setAttribute("errores", errores);
                request.setAttribute("tipoContratacion", tipoContratacion);
                if (idParam != null && !idParam.trim().isEmpty()) {
                    request.setAttribute("idTipoContratacion", idParam);
                }
                mostrarFormularioRegistro(request, response);
                return;
            }

            TipoContratacion tc = new TipoContratacion();
            tc.setTipoContratacion(tipoContratacion.trim());

            boolean resultado;

            if (idParam != null && !idParam.trim().isEmpty()) {
                tc.setIdTipoContratacion(Integer.parseInt(idParam));
                resultado = tipoContratacionDAO.actualizar(tc);
                if (resultado) {
                    request.setAttribute("mensaje", "Tipo de contratación actualizado exitosamente");
                } else {
                    String errorMsg = tipoContratacionDAO.getUltimoError();
                    request.setAttribute("error", errorMsg.isEmpty() ? "No se pudo actualizar el tipo de contratación" : errorMsg);
                }
            } else {
                resultado = tipoContratacionDAO.agregar(tc);
                if (resultado) {
                    request.setAttribute("mensaje", "Tipo de contratación registrado exitosamente");
                } else {
                    String errorMsg = tipoContratacionDAO.getUltimoError();
                    request.setAttribute("error", errorMsg.isEmpty() ? "No se pudo guardar el tipo de contratación" : errorMsg);
                }
            }

            if (!resultado) {
                request.setAttribute("tipoContratacion", tipoContratacion);
                if (idParam != null && !idParam.trim().isEmpty()) {
                    request.setAttribute("idTipoContratacion", idParam);
                }
                mostrarFormularioRegistro(request, response);
                return;
            }

            listarTiposContratacion(request, response);
        } catch (Exception e) {
            System.out.println("Error en guardarTipoContratacion: " + e.getMessage());
            request.setAttribute("error", "Error inesperado: " + e.getMessage());
            mostrarFormularioRegistro(request, response);
        }
    }

    private void mostrarFormularioEdicion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            TipoContratacion tc = tipoContratacionDAO.obtenerPorId(id);

            if (tc != null) {
                request.setAttribute("tipoContratacion", tc.getTipoContratacion());
                request.setAttribute("idTipoContratacion", tc.getIdTipoContratacion());
                request.getRequestDispatcher("/registrarTipoContratacion.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "No se encontró el tipo de contratación");
                listarTiposContratacion(request, response);
            }
        } catch (Exception e) {
            System.out.println("Error en mostrarFormularioEdicion: " + e.getMessage());
            request.setAttribute("error", "Error al cargar el tipo de contratación");
            listarTiposContratacion(request, response);
        }
    }

    private void eliminarTipoContratacion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            boolean resultado = tipoContratacionDAO.eliminar(id);

            if (resultado) {
                request.setAttribute("mensaje", "Tipo de contratación eliminado exitosamente");
            } else {
                request.setAttribute("error", "No se pudo eliminar el tipo de contratación");
            }
        } catch (Exception e) {
            System.out.println("Error en eliminarTipoContratacion: " + e.getMessage());
            request.setAttribute("error", "Error al eliminar el tipo de contratación");
        }

        listarTiposContratacion(request, response);
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
        return "Servlet para gestionar Tipos de Contratación";
    }
}
