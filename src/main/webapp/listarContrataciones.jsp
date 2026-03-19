<%-- Document : listarContrataciones Author : Sistema --%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="pageTitle" value="Contrataciones" scope="request" />
<jsp:include page="includes/header.jsp" />

<body class="d-flex flex-column min-vh-100">

    <jsp:include page="includes/navbar.jsp">
        <jsp:param name="activePage" value="contrataciones" />
    </jsp:include>

    <main class="container mt-5">
        <div class="card">
            <div class="card-header bg-primary text-white">
                <h3 class="text-center"><i class="fas fa-file-contract"></i> Lista de Contrataciones</h3>
            </div>
            <div class="card-body">

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
                    <a href="ContratacionServlet?action=nuevo"
                       class="btn btn-success d-flex gap-2 align-items-center">
                        <i class="fas fa-plus"></i> Nueva Contratación
                    </a>
                </div>

                <div class="table-responsive">
                    <table class="table table-bordered table-striped table-hover">
                        <thead class="table-dark">
                            <tr>
                                <th class="text-center">ID</th>
                                <th>Empleado</th>
                                <th>Departamento</th>
                                <th>Cargo</th>
                                <th>Tipo Contratación</th>
                                <th class="text-center">Fecha</th>
                                <th class="text-end">Salario</th>
                                <th class="text-center">Estado</th>
                                <th class="text-center">Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${contrataciones}" var="cont">
                                <tr>
                                    <td class="text-center">${cont.idContratacion}</td>
                                    <td>${cont.empleado.nombreEmpleado}</td>
                                    <td>${cont.departamento.nombreDepartamento}</td>
                                    <td>${cont.cargo.cargo}</td>
                                    <td>${cont.tipoContratacion.tipoContratacion}</td>
                                    <td class="text-center">
                                        <fmt:formatDate value="${cont.fechaDeContratacion}"
                                                        pattern="dd/MM/yyyy" />
                                    </td>
                                    <td class="text-center">
                                        $<fmt:formatNumber value="${cont.salario}" 
                                                          type="number" 
                                                          minFractionDigits="2" 
                                                          maxFractionDigits="2" />
                                    </td>
                                    <td class="text-center">
                                        <c:choose>
                                            <c:when test="${cont.estado}">
                                                <span class="badge bg-success">Activo</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge bg-danger">Inactivo</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="text-center">
                                        <a href="ContratacionServlet?action=editar&id=${cont.idContratacion}"
                                           class="btn btn-sm btn-warning" title="Editar">
                                            <i class="fas fa-edit text-white"></i>
                                        </a>
                                        <button type="button" class="btn btn-sm btn-danger"
                                                title="Eliminar" data-bs-toggle="modal"
                                                data-bs-target="#modalEliminar" data-id="${cont.idContratacion}"
                                                data-nombre="${cont.empleado.nombreEmpleado}">
                                            <i class="fas fa-trash"></i>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>

                            <c:if test="${empty contrataciones}">
                                <tr>
                                    <td colspan="9" class="text-center">No hay contrataciones registradas
                                    </td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </main>

    <!-- Modal Eliminar -->
    <div class="modal fade" id="modalEliminar" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header bg-danger text-white">
                    <h5 class="modal-title">
                        <i class="fas fa-exclamation-triangle"></i> Confirmar Eliminación
                    </h5>
                    <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <p>¿Está seguro que desea eliminar la contratación del empleado <strong id="nombreEliminar"></strong>?</p>
                    <p class="text-danger mb-0">
                        <i class="fas fa-exclamation-circle"></i> Esta acción no se puede deshacer.
                    </p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <a href="#" id="btnConfirmarEliminar" class="btn btn-danger">
                        <i class="fas fa-trash"></i> Eliminar
                    </a>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <jsp:include page="includes/footer.jsp" />

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    
    <script>
        // Script para el modal de eliminación
        const modalEliminar = document.getElementById('modalEliminar');
        if (modalEliminar) {
            modalEliminar.addEventListener('show.bs.modal', function (event) {
                const button = event.relatedTarget;
                const id = button.getAttribute('data-id');
                const nombre = button.getAttribute('data-nombre');
                
                document.getElementById('nombreEliminar').textContent = nombre;
                document.getElementById('btnConfirmarEliminar').href = 'ContratacionServlet?action=eliminar&id=' + id;
            });
        }
    </script>
</body>

</html>
