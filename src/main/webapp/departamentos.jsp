<%-- 
    Document   : departamentos
    Created on : 11 mar 2026, 00:09:26
    Author     : josed
--%>

<%@page import="java.util.List"%>
<%@page import="com.udb.modelo.Departamento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Departamentos" scope="request" />
<jsp:include page="includes/header.jsp" />

<body class="bg-light d-flex flex-column min-vh-100">

    <jsp:include page="includes/navbar.jsp">
        <jsp:param name="activePage" value="departamentos" />
    </jsp:include>

    <!-- Contenido principal -->
    <main class="container mt-5">
        <div class="card">
            <div class="card-header bg-primary text-white">
                <h3 class="text-center"><i class="fas fa-building"></i> Lista de Departamentos</h3>
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
                    <a href="registrarDepartamento.jsp" class="btn btn-success d-flex gap-2 align-items-center">
                        <i class="fas fa-plus"></i> Nuevo Departamento
                    </a>
                </div>



                <div class="table-responsive">
                    <table class="table table-bordered table-striped table-hover" id="tablaDepartamentos">
                        <thead class="table-dark">
                            <tr>
                                <th class="text-center">ID</th>
                                <th>Nombre</th>
                                <th>Descripción</th>
                                <th class="text-center">Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="lista" value="${requestScope.listaDepartamentos}" />
                            <c:choose>
                                <c:when test="${not empty lista}">
                                    <c:forEach var="depto" items="${lista}">
                                        <tr>
                                            <td class="text-center">${depto.idDepartamento}</td>
                                            <td>${depto.nombreDepartamento}</td>
                                            <td>${depto.descripcionDepartamento}</td>
                                            <td class="text-center">
                                                <a href="DepartamentoServlet?action=editar&id=${depto.idDepartamento}" 
                                                   class="btn btn-sm btn-warning" title="Editar">
                                                    <i class="fas fa-edit text-white"></i>
                                                </a>
                                                <button type="button" class="btn btn-sm btn-danger"
                                                        title="Eliminar" data-bs-toggle="modal"
                                                        data-bs-target="#modalEliminar" 
                                                        data-id="${depto.idDepartamento}"
                                                        data-nombre="${depto.nombreDepartamento}">
                                                    <i class="fas fa-trash"></i>
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td colspan="4" class="text-center py-5">
                                            <i class="bi bi-folder-x display-1 text-muted"></i>
                                            <p class="lead text-muted mt-3">No hay departamentos registrados aún.</p>
                                            <a href="registrarDepartamento.jsp" class="btn btn-primary">
                                                <i class="bi bi-plus-circle"></i> Crear primer departamento
                                            </a>
                                        </td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
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
                    <p>¿Estás seguro de que deseas eliminar el departamento:</p>
                    <p class="fw-bold text-center" id="nombreDepartamentoEliminar"></p>
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

    <!-- Footer -->
    <jsp:include page="includes/footer.jsp" />

    <!-- Scripts -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    
    <!-- Script para el modal de eliminación -->
    <script>
        // Capturar datos del departamento al abrir el modal
        const modalEliminar = document.getElementById('modalEliminar');
        modalEliminar.addEventListener('show.bs.modal', function (event) {
            const button = event.relatedTarget;
            const idDepartamento = button.getAttribute('data-id');
            const nombreDepartamento = button.getAttribute('data-nombre');

            // Actualizar el contenido del modal
            document.getElementById('nombreDepartamentoEliminar').textContent = nombreDepartamento;

            // Configurar el enlace de confirmación
            const btnConfirmar = document.getElementById('btnConfirmarEliminar');
            btnConfirmar.href = 'DepartamentoServlet?action=eliminar&id=' + idDepartamento;
        });
    </script>
</body>

</html>