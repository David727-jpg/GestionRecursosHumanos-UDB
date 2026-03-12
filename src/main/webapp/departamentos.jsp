<%-- 
    Document   : departamentos
    Created on : 11 mar 2026, 00:09:26
    Author     : josed
--%>

<%@page import="java.util.List"%>
<%@page import="com.udb.modelo.Departamento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestión de Departamentos</title>
        <style>
            
            body { font-family: Arial, sans-serif; padding: 20px; }
            table { border-collapse: collapse; width: 60%; margin-top: 20px; }
            th, td { border: 1px solid black; padding: 8px; text-align: left; }
            th { background-color: #f2f2f2; }
        </style>
    </head>
    <body>
        <h1>   Lista de Departamentos</h1>
        
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre del Departamento</th>
                    <th>Descripción</th>
                </tr>
            </thead>
            <tbody>
                <%
                    // Aquí recibimos la "bandeja" que nos trajo el mesero (Servlet)
                    List<Departamento> lista = (List<Departamento>) request.getAttribute("listaDepartamentos");
                    
                    if (lista != null && !lista.isEmpty()) {
                        // Recorremos la lista y creamos una fila <tr> por cada departamento
                        for (Departamento depto : lista) {
                %>
                        <tr>
                            <td><%= depto.getIdDepartamento() %></td>
                            <td><%= depto.getNombreDepartamento() %></td>
                            <td><%= depto.getDescripcionDepartamento() %></td>
                        </tr>
                <%
                        }
                    } else {
                %>
                        <tr>
                            <td colspan="3">No hay departamentos registrados aún.</td>
                        </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </body>
</html>