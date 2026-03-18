<%-- 
    Document   : cargos_rescate
    Created on : 13 mar 2026, 16:50:30
    Author     : josed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.udb.modelo.Cargo"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestión de Cargos (Rescate)</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
        <link href="css/estilos.css" rel="stylesheet">
    </head>
    <body class="bg-light">
        <div class="container mt-5">
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h2><i class="bi bi-briefcase me-2"></i>Listado de Cargos</h2>
                <a href="nuevoCargo_rescate.jsp" class="btn btn-success fw-bold">
                    <i class="bi bi-plus-circle me-1"></i>Nuevo Cargo
                </a>
            </div>
            
            <div class="card shadow">
                <div class="card-body">
                    <table class="table table-hover table-striped">
                        <thead class="table-dark">
                            <tr>
                                <th>ID</th>
                                <th>Nombre del Cargo</th>
                                <th>Descripción</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<Cargo> lista = (List<Cargo>) request.getAttribute("listaCargos");
                                if(lista != null) {
                                    for(Cargo c : lista) {
                            %>
                            <tr>
                                <td><%= c.getIdCargo() %></td>
                                <td><%= c.getCargo() %></td>
                                <td><%= c.getDescripcionCargo() %></td>
                                <td>
                                    <a href="CargoServletRescate?accion=editar&id=<%= c.getIdCargo() %>" class="btn btn-warning btn-sm">
                                        <i class="bi bi-pencil"></i> Editar
                                    </a>
                                    <a href="CargoServletRescate?accion=eliminar&id=<%= c.getIdCargo() %>" class="btn btn-danger btn-sm" onclick="return confirm('¿Seguro que deseas eliminar este cargo?');">
                                        <i class="bi bi-trash"></i> Eliminar
                                    </a>
                                </td>
                            </tr>
                            <%
                                    }
                                } else {
                            %>
                            <tr>
                                <td colspan="4" class="text-center">No hay cargos registrados.</td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>