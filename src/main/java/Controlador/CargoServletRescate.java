/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import DataAccess.CargoDAO;


@WebServlet(name = "CargoServletRescate", urlPatterns = {"/CargoServletRescate"})
public class CargoServletRescate extends HttpServlet {

   
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        if (accion == null) {
            accion = "listar";
        }
        
        
        DataAccess.CargoDAO dao = new DataAccess.CargoDAO();
        
        switch (accion) {
            case "guardar":
                String nombre = request.getParameter("txtNombre");
                String descripcion = request.getParameter("txtDescripcion");
                com.udb.modelo.Cargo nuevoCargo = new com.udb.modelo.Cargo();
                nuevoCargo.setCargo(nombre);
                nuevoCargo.setDescripcionCargo(descripcion);
                dao.agregar(nuevoCargo);
                request.getRequestDispatcher("CargoServletRescate?accion=listar").forward(request, response);
                break;
                
            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                dao.eliminar(idEliminar);
                request.getRequestDispatcher("CargoServletRescate?accion=listar").forward(request, response);
                break;
                
            case "editar":
                int idEditar = Integer.parseInt(request.getParameter("id"));
                com.udb.modelo.Cargo cargo = dao.buscarPorId(idEditar);
                request.setAttribute("cargo", cargo);
                request.getRequestDispatcher("editarCargos_rescate.jsp").forward(request, response);
                break;
                
            case "actualizar":
                int idActualizar = Integer.parseInt(request.getParameter("txtId"));
                String nombreMod = request.getParameter("txtNombre");
                String descMod = request.getParameter("txtDescripcion");
                
                com.udb.modelo.Cargo cargoModificado = new com.udb.modelo.Cargo();
                cargoModificado.setIdCargo(idActualizar);
                cargoModificado.setCargo(nombreMod);
                cargoModificado.setDescripcionCargo(descMod);
                
                dao.actualizar(cargoModificado);
                request.getRequestDispatcher("CargoServletRescate?accion=listar").forward(request, response);
                break;
                
            case "listar":
            default:
                request.setAttribute("listaCargos", dao.listar());
                request.getRequestDispatcher("cargos_rescate.jsp").forward(request, response);
                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
