<%-- 
    Document   : registrarTipoContratacion
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
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header bg-primary text-white">
                        <h4 class="text-center">
                            <i class="fas fa-file-contract"></i>
                            ${empty idTipoContratacion ? 'Registrar' : 'Actualizar'} Tipo de Contratación
                        </h4>
                    </div>

                    <div class="card-body">

                        <c:if test="${not empty error}">
                            <div class="alert alert-danger">
                                <i class="fas fa-exclamation-circle"></i> ${error}
                            </div>
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

                        <form action="TipoContratacionServlet" method="post">
                            <input type="hidden" name="action" value="guardar">
                            <input type="hidden" name="idTipoContratacion" value="${idTipoContratacion}">

                            <div class="mb-3">
                                <label for="tipoContratacion" class="form-label">
                                    Tipo de Contratación <span class="text-danger">*</span>
                                </label>
                                <input type="text" 
                                       class="form-control" 
                                       id="tipoContratacion" 
                                       name="tipoContratacion" 
                                       required 
                                       maxlength="100"
                                       placeholder="Ej: Tiempo completo, Medio tiempo, Por proyecto"
                                       value="${not empty tipoContratacion ? tipoContratacion : param.tipoContratacion}">
                                <small class="form-text text-muted">
                                    Ingrese el nombre del tipo de contratación (mínimo 3 caracteres)
                                </small>
                            </div>

                            <div class="d-flex align-items-center justify-content-center gap-3 mt-4">
                                <button type="submit" class="btn btn-primary d-flex gap-2 align-items-center">
                                    <i class="fas fa-save"></i> 
                                    ${empty idTipoContratacion ? 'Guardar' : 'Actualizar'}
                                </button>
                                <a href="TipoContratacionServlet" class="btn btn-secondary d-flex gap-2 align-items-center">
                                    <i class="fas fa-arrow-left"></i> Cancelar
                                </a>
                            </div>
                        </form>
                    </div>
                </div>

            </div>
        </div>
    </main>

    <!-- Footer -->
    <jsp:include page="includes/footer.jsp" />

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        // Auto-focus en el campo de texto al cargar la página
        document.getElementById('tipoContratacion').focus();
    </script>
</body>
</html>
