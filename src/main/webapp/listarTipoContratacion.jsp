<%-- 
    Document   : listarTipoContratacion
    Author     : Generated
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageTitle" value="Tipos de Contratación" scope="request" />
<jsp:include page="includes/header.jsp" />

<body class="d-flex flex-column min-vh-100">

    <jsp:include page="includes/navbar.jsp">
        <jsp:param name="activePage" value="tiposContratacion" />
    </jsp:include>

    <main class="container mt-5">
        <div class="card">
            <div class="card-header bg-primary text-white">
                <h3 class="text-center"><i class="fas fa-file-contract"></i> Lista de Tipos de Contratación</h3>
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
                    <a href="TipoContratacionServlet?action=nuevo"
                       class="btn btn-success d-flex gap-2 align-items-center">
                        <i class="fas fa-plus"></i> Nuevo Tipo de Contratación
                    </a>
                </div>

                <!-- Tabla -->
                <div class="table-responsive">
                    <table class="table table-bordered table-striped table-hover">
                        <thead class="table-dark">
                            <tr>
                                <th class="text-center" style="width: 10%;">ID</th>
                                <th>Tipo de Contratación</th>
                                <th class="text-center" style="width: 20%;">Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${tiposContratacion}" var="tc">
                                <tr>
                                    <td class="text-center">${tc.idTipoContratacion}</td>
                                    <td>${tc.tipoContratacion}</td>
                                    <td class="text-center">
                                        <a href="TipoContratacionServlet?action=editar&id=${tc.idTipoContratacion}"
                                           class="btn btn-sm btn-warning" title="Editar">
                                            <i class="fas fa-edit text-white"></i>
                                        </a>
                                        <button type="button" class="btn btn-sm btn-danger"
                                                title="Eliminar" data-bs-toggle="modal"
                                                data-bs-target="#modalEliminar" data-id="${tc.idTipoContratacion}"
                                                data-nombre="${tc.tipoContratacion}">
                                            <i class="fas fa-trash"></i>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>

                            <c:if test="${empty tiposContratacion}">
                                <tr>
                                    <td colspan="3" class="text-center">No hay tipos de contratación registrados</td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </main>

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
                    <p>¿Estás seguro de que deseas eliminar el tipo de contratación:</p>
                    <p class="fw-bold text-center" id="nombreTipoEliminar"></p>
                    <p class="text-danger"><i class="fas fa-info-circle"></i> Esta acción no se puede deshacer.</p>
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

    <!-- Footer -->
    <jsp:include page="includes/footer.jsp" />

    <!-- Scripts -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        // Capturar datos del tipo de contratación al abrir el modal
        const modalEliminar = document.getElementById('modalEliminar');
        modalEliminar.addEventListener('show.bs.modal', function (event) {
            const button = event.relatedTarget;
            const idTipo = button.getAttribute('data-id');
            const nombreTipo = button.getAttribute('data-nombre');

            // Actualizar el contenido del modal
            document.getElementById('nombreTipoEliminar').textContent = nombreTipo;

            // Configurar el enlace de confirmación
            const btnConfirmar = document.getElementById('btnConfirmarEliminar');
            btnConfirmar.href = 'TipoContratacionServlet?action=eliminar&id=' + idTipo;
        });
    </script>
</body>
</html>
