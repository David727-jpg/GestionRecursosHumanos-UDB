<%-- 
    Document   : editarCargos_rescate
    Created on : 13 mar 2026, 17:04:23
    Author     : josed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Cargo</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class="card shadow">
                        <div class="card-header bg-warning text-dark">
                            <h3 class="mb-0">️ Editar Cargo</h3>
                        </div>
                        <div class="card-body">
                            
                            <form action="CargoServletRescate" method="POST">
                                <input type="hidden" name="accion" value="actualizar">
                                
                                <% 
                                    
                                    com.udb.modelo.Cargo cargo = (com.udb.modelo.Cargo) request.getAttribute("cargo"); 
                                %>
                                
                                <input type="hidden" name="txtId" value="<%= cargo.getIdCargo() %>">
                                
                                <div class="mb-3">
                                    <label class="form-label fw-bold">Nombre del Cargo:</label>
                                    <input type="text" class="form-control" name="txtNombre" required value="<%= cargo.getCargo() %>">
                                </div>
                                
                                <div class="mb-3">
                                    <label class="form-label fw-bold">Descripción:</label>
                                    <textarea class="form-control" name="txtDescripcion" rows="3" required><%= cargo.getDescripcionCargo() %></textarea>
                                </div>
                                
                                <div class="d-grid gap-2 d-md-flex justify-content-md-end mt-4">
                                    <a href="CargoServletRescate?accion=listar" class="btn btn-secondary">Cancelar</a>
                                    <button type="submit" class="btn btn-warning fw-bold">Actualizar Datos</button>
                                </div>
                            </form>
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>