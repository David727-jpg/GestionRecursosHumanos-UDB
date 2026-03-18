<%-- Document : cargos_rescate Created on : 13 mar 2026, 16:50:30 Author : josed --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestión de Cargos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
              rel="stylesheet">
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <link href="css/estilos.css" rel="stylesheet">
    </head>

    <body>
        <nav class="navbar navbar-expand-lg navbar-custom">
            <div class="container">
                <a class="navbar-brand" href="index.html">
                    <i class="fas fa-users"></i> GestionRRHH - UDB
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarNav">
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

        <main class="container mt-5">
            <div class="card">
                <div class="card-header bg-primary text-white">
                    <h3 class="text-center"><i class="fas fa-briefcase"></i> Listado de Cargos</h3>
                </div>
                <div class="card-body">
                    <!-- Mensajes -->
                    <c:if test="${not empty mensaje}">
                        <div class="alert alert-success alert-dismissible fade show">
                            <span class="d-flex gap-2 align-items-center">
                                <i class="fas fa-check-circle"></i> ${mensaje}
                            </span>
                            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                        </div>
                    </c:if>

                    <c:if test="${not empty error}">
                        <div class="alert alert-danger alert-dismissible fade show">
                            <i class="fas fa-exclamation-circle"></i> ${error}
                            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                        </div>
                    </c:if>

                    <div class="mb-3 d-flex justify-content-start">
                        <a href="nuevoCargo_rescate.jsp" class="btn btn-success d-flex gap-2 align-items-center">
                            <i class="fas fa-plus"></i> Nuevo Cargo
                        </a>
                    </div>

                    <div class="table-responsive">
                        <table class="table table-bordered table-striped table-hover">
                            <thead class="table-dark">
                                <tr>
                                    <th class="text-center">ID</th>
                                    <th>Nombre del Cargo</th>
                                    <th>Descripción</th>
                                    <th class="text-center">Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${listaCargos}" var="c">
                                    <tr>
                                        <td class="text-center">${c.idCargo}</td>
                                        <td>${c.cargo}</td>
                                        <td>${c.descripcionCargo}</td>
                                        <td class="text-center">
                                            <a href="CargoServletRescate?accion=editar&id=${c.idCargo}" class="btn btn-sm btn-warning" title="Editar">
                                                <i class="fas fa-edit text-white"></i>
                                            </a>
                                            <button type="button" class="btn btn-danger btn-sm" 
                                                    data-bs-toggle="modal" 
                                                    data-bs-target="#modalEliminar"
                                                    data-id="${c.idCargo}"
                                                    data-nombre="${c.cargo}">
                                                <i class="fas fa-trash"></i>
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>

                                <c:if test="${empty listaCargos}">
                                    <tr>
                                        <td colspan="4" class="text-center">No hay cargos registrados.</td>
                                    </tr>
                                </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <!-- Modal Confirmar Eliminación -->
    <div class="modal fade" id="modalEliminar" tabindex="-1" aria-labelledby="modalEliminarLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header bg-danger text-white">
                    <h5 class="modal-title" id="modalEliminarLabel">
                        <i class="fas fa-exclamation-triangle"></i> Confirmar Eliminación
                    </h5>
                    <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"
                            aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p>¿Estás seguro de que deseas eliminar el cargo:</p>
                    <p class="fw-bold text-center" id="nombreCargoEliminar"></p>
                    <p class="text-danger"><i class="fas fa-info-circle"></i> Esta acción no se puede
                        deshacer.</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                        <i class="fas fa-times"></i> Cancelar
                    </button>
                    <a href="#" id="btnConfirmarEliminar" class="btn btn-danger">
                        <i class="fas fa-trash"></i> Eliminar
                    </a>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        // Capturar datos del cargo al abrir el modal
        const modalEliminar = document.getElementById('modalEliminar');
        modalEliminar.addEventListener('show.bs.modal', function (event) {
            const button = event.relatedTarget;
            const idCargo = button.getAttribute('data-id');
            const nombreCargo = button.getAttribute('data-nombre');

            // Actualizar el contenido del modal
            document.getElementById('nombreCargoEliminar').textContent = nombreCargo;

            // Configurar el enlace de confirmación
            const btnConfirmar = document.getElementById('btnConfirmarEliminar');
            btnConfirmar.href = 'CargoServletRescate?accion=eliminar&id=' + idCargo;
        });
    </script>
</body>

</html>