<%-- Document : nuevoCargo_rescate Created on : 13 mar 2026, 17:03:34 Author : josed --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Cargos" scope="request" />
<jsp:include page="includes/header.jsp" />

<body>
    <jsp:include page="includes/navbar.jsp">
        <jsp:param name="activePage" value="cargos" />
    </jsp:include>

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