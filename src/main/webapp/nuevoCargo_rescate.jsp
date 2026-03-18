<%-- Document : nuevoCargo_rescate Created on : 13 mar 2026, 17:03:34 Author : josed --%>

    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Nuevo Cargo</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
            <link href="css/estilos.css" rel="stylesheet">
        </head>
        <nav class="navbar navbar-expand-lg navbar-custom">
            <div class="container">
                <a class="navbar-brand" href="index.html">
                    <i class="fas fa-users"></i> GestionRRHH - UDB
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="index.html">
                                <i class="fas fa-home me-1"></i>Inicio
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="CargoServletRescate">
                                <i class="fas fa-briefcase me-1"></i>Cargos
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="EmpleadoServlet">
                                <i class="fas fa-user-tie me-1"></i>Empleados
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="DepartamentoServlet">
                                <i class="fas fa-building me-1"></i>Departamentos
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="ContratacionServlet">
                                <i class="fas fa-file-contract me-1"></i>Contrataciones
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

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
                                        <input type="text" class="form-control" name="txtNombre" required
                                            placeholder="Ej: Gerente de Ventas">
                                    </div>

                                    <div class="mb-3">
                                        <label class="form-label fw-bold">Descripción:</label>
                                        <textarea class="form-control" name="txtDescripcion" rows="3" required
                                            placeholder="Describe las funciones principales..."></textarea>
                                    </div>

                                    <div class="d-flex align-items-center justify-content-center gap-3">
                                        <button type="submit" class="btn btn-success fw-bold d-flex gap-2 align-items-center"><i class="fas fa-save"></i> Guardar Cargo</button>
                                        <a href="CargoServletRescate?accion=listar"
                                            class="btn btn-secondary d-flex gap-2 align-items-center"><i class="fas fa-arrow-left"></i> Cancelar</a>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </body>

        </html>