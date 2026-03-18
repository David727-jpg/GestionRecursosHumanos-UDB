<%-- Document : listarEmpleados Author : Moises --%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="includes/header.jsp" />

<body>
    <jsp:include page="includes/navbar.jsp">
        <jsp:param name="activePage" value="empleados" />
    </jsp:include>

    <div class="container mt-5">
        <div class="card">
            <div class="card-header bg-primary text-white">
                <h3 class="text-center"><i class="fas fa-user-tie"></i> Lista de Empleados</h3>
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

                <!-- Botón Nuevo -->
                <div class="mb-3 d-flex justify-content-start">
                    <a href="EmpleadoServlet?action=nuevo"
                       class="btn btn-success d-flex gap-2 align-items-center">
                        <i class="fas fa-plus"></i> Nuevo Empleado
                    </a>
                </div>

                <!-- Tabla -->
                <div class="table-responsive">
                    <table class="table table-bordered table-striped table-hover">
                        <thead class="table-dark">
                            <tr>
                                <th class="text-center">ID</th>
                                <th class="text-center">DUI</th>
                                <th>Nombre</th>
                                <th class="text-center">Usuario</th>
                                <th class="text-center">Teléfono</th>
                                <th>Correo</th>
                                <th class="text-center">Fecha Nac.</th>
                                <th class="text-center">Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${empleados}" var="emp">
                                <tr>
                                    <td class="text-center">${emp.idEmpleado}</td>
                                    <td class="text-center">${emp.duiEmpleado.substring(0,
                                                              8)}-${emp.duiEmpleado.substring(8)}</td>
                                    <td>${emp.nombreEmpleado}</td>
                                    <td class="text-center">${emp.usuarioEmpleado}</td>
                                    <td class="text-center">${emp.telefonoEmpleado}</td>
                                    <td>${emp.correoEmpleado}</td>
                                    <td class="text-center">
                                        <fmt:formatDate value="${emp.fechadenacimientoEmpleado}"
                                                        pattern="dd/MM/yyyy" />
                                    </td>
                                    <td class="text-center">
                                        <a href="EmpleadoServlet?action=editar&id=${emp.idEmpleado}"
                                           class="btn btn-sm btn-warning" title="Editar">
                                            <i class="fas fa-edit text-white"></i>
                                        </a>
                                        <button type="button" class="btn btn-sm btn-danger"
                                                title="Eliminar" data-bs-toggle="modal"
                                                data-bs-target="#modalEliminar" data-id="${emp.idEmpleado}"
                                                data-nombre="${emp.nombreEmpleado}">
                                            <i class="fas fa-trash"></i>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>

                            <c:if test="${empty empleados}">
                                <tr>
                                    <td colspan="8" class="text-center">No hay empleados registrados
                                    </td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal Confirmar Eliminación -->
    <div class="modal fade" id="modalEliminar" tabindex="-1" aria-labelledby="modalEliminarLabel"
         aria-hidden="true">
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
                    <p>¿Estás seguro de que deseas eliminar al empleado:</p>
                    <p class="fw-bold text-center" id="nombreEmpleadoEliminar"></p>
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

    <!-- Scripts -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        // Capturar datos del empleado al abrir el modal
        const modalEliminar = document.getElementById('modalEliminar');
        modalEliminar.addEventListener('show.bs.modal', function (event) {
            const button = event.relatedTarget;
            const idEmpleado = button.getAttribute('data-id');
            const nombreEmpleado = button.getAttribute('data-nombre');

            // Actualizar el contenido del modal
            document.getElementById('nombreEmpleadoEliminar').textContent = nombreEmpleado;

            // Configurar el enlace de confirmación
            const btnConfirmar = document.getElementById('btnConfirmarEliminar');
            btnConfirmar.href = 'EmpleadoServlet?action=eliminar&id=' + idEmpleado;
        });
    </script>
</body>

</html>