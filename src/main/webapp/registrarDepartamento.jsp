<%-- 
    Document   : registrarDepartamento
    Created on : 18 mar 2026
    Author     : moica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Departamentos" scope="request" />
<jsp:include page="includes/header.jsp" />

<body>
    <jsp:include page="includes/navbar.jsp">
        <jsp:param name="activePage" value="departamentos" />
    </jsp:include>

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header bg-primary text-white">
                        <h4 class="text-center">
                            <i class="fas fa-building"></i>
                            ${empty departamento.idDepartamento ? 'Guardar' : 'Actualizar'} Departamento
                        </h4>
                    </div>

                    <div class="card-body">

                        <c:if test="${not empty error}">
                            <div class="alert alert-danger">${error}</div>
                        </c:if>

                        <c:if test="${not empty errores}">
                            <div class="alert alert-danger">
                                <strong>Por favor corrija los siguientes errores:</strong>
                                <ul class="mb-0 mt-2">
                                    <c:forEach items="${errores}" var="err">
                                        <li>${err}</li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:if>

                        <form action="DepartamentoServlet" method="post">
                            <input type="hidden" name="action" value="guardar">
                            <input type="hidden" name="idDepartamento" value="${departamento.idDepartamento}">

                            <div class="mb-3">
                                <label for="nombreDepartamento" class="form-label">Nombre del Departamento <span
                                        class="text-danger">*</span></label>
                                <input type="text" class="form-control" id="nombreDepartamento" 
                                       name="nombreDepartamento" required maxlength="100"
                                       placeholder="Ej: Recursos Humanos"
                                       value="${not empty nombreDepartamento ? nombreDepartamento : (not empty departamento.nombreDepartamento ? departamento.nombreDepartamento : param.nombreDepartamento)}">
                            </div>

                            <div class="mb-3">
                                <label for="descripcionDepartamento" class="form-label">Descripción</label>
                                <textarea class="form-control" id="descripcionDepartamento" 
                                          name="descripcionDepartamento" rows="4" maxlength="500"
                                          placeholder="Ingrese una descripción del departamento...">${not empty descripcionDepartamento ? descripcionDepartamento : (not empty departamento.descripcionDepartamento ? departamento.descripcionDepartamento : param.descripcionDepartamento)}</textarea>
                                <small class="text-muted">Opcional - Máximo 500 caracteres</small>
                            </div>

                            <div class="d-flex align-items-center justify-content-center gap-3">
                                <button type="submit" class="btn btn-primary d-flex gap-2 align-items-center">
                                    <i class="fas fa-save"></i> 
                                    ${empty departamento.idDepartamento ? 'Guardar' : 'Actualizar'}
                                </button>
                                <a href="DepartamentoServlet" class="btn btn-secondary d-flex gap-2 align-items-center">
                                    <i class="fas fa-arrow-left"></i> Cancelar
                                </a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
