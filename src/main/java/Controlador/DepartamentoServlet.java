/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import DataAccess.DepartamentoDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author josed
 */
@WebServlet(name = "DepartamentoServlet", urlPatterns = {"/DepartamentoServlet"})
public class DepartamentoServlet extends HttpServlet {

   DepartamentoDAO dao = new DepartamentoDAO();
 
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String accion = request.getParameter("accion");
       
       if (accion == null || accion.equalsIgnoreCase("listar")){
           request.setAttribute("listaDepartamentos", dao.listar());
           request.getRequestDispatcher("departamentos.jsp").forward(request, response);
           
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
