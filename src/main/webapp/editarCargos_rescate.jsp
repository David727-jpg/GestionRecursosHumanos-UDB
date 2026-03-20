<%-- Document : editarCargos_rescate Created on : 13 mar 2026, 17:04:23 Author : josed --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Cargos" scope="request" />
<jsp:include page="includes/header.jsp" />

<body class="d-flex flex-column min-vh-100">

    <jsp:include page="includes/navbar.jsp">
        <jsp:param name="activePage" value="cargos" />
    </jsp:include>

    <main class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card shadow">
                    <div class="card-header bg-primary text-white">
                        <h3 class="mb-0">️ Editar Cargo</h3>
                    </div>
                    <div class="card-body">

                        <form action="CargoServletRescate" method="POST">
                            <input type="hidden" name="accion" value="actualizar">

                            <% com.udb.modelo.Cargo cargo = (com.udb.modelo.Cargo) request.getAttribute("cargo");%>

                            <input type="hidden" name="txtId" value="<%= cargo.getIdCargo()%>">

                            <div class="mb-3">
                                <label class="form-label fw-bold">Nombre del Cargo:</label>
                                <input type="text" class="form-control" name="txtNombre" required
                                       value="<%= cargo.getCargo()%>">
                            </div>

                            <div class="mb-3">
                                <label class="form-label fw-bold">Descripción:</label>
                                <textarea class="form-control" name="txtDescripcion" rows="3"
                                          required><%= cargo.getDescripcionCargo()%></textarea>
                            </div>

                            <div class="d-flex align-items-center justify-content-center gap-3">
                                <button type="submit" class="btn btn-primary fw-bold text-white d-flex gap-2 align-items-center"><i class="fas fa-save"></i> Actualizar
                                    Datos</button>
                                <a href="CargoServletRescate?accion=listar"
                                   class="btn btn-secondary d-flex gap-2 align-items-center"><i class="fas fa-arrow-left"></i> Cancelar</a>
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
</body>

</html>