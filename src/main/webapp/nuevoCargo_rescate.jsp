<%-- 
    Document   : nuevoCargo_rescate
    Created on : 13 mar 2026, 17:03:34
    Author     : josed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nuevo Cargo</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class="card shadow">
                        <div class="card-header bg-success text-white">
                            <h3 class="mb-0"> Registrar Nuevo Cargo</h3>
                        </div>
                        <div class="card-body">
                            
                            <form action="CargoServletRescate" method="POST">
                                <input type="hidden" name="accion" value="guardar">
                                
                                <div class="mb-3">
                                    <label class="form-label fw-bold">Nombre del Cargo:</label>
                                    <input type="text" class="form-control" name="txtNombre" required placeholder="Ej: Gerente de Ventas">
                                </div>
                                
                                <div class="mb-3">
                                    <label class="form-label fw-bold">Descripción:</label>
                                    <textarea class="form-control" name="txtDescripcion" rows="3" required placeholder="Describe las funciones principales..."></textarea>
                                </div>
                                
                                <div class="d-grid gap-2 d-md-flex justify-content-md-end mt-4">
                                    <a href="CargoServletRescate?accion=listar" class="btn btn-secondary">Cancelar</a>
                                    <button type="submit" class="btn btn-success fw-bold">Guardar Cargo</button>
                                </div>
                            </form>
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
