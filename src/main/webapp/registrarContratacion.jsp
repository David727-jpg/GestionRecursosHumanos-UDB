<%-- Document : registrarContratacion Created on : Sistema Author : Sistema --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Contrataciones" scope="request" />
<jsp:include page="includes/header.jsp" />

<body class="d-flex flex-column min-vh-100">

    <jsp:include page="includes/navbar.jsp">
        <jsp:param name="activePage" value="contrataciones" />
    </jsp:include>

    <main class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-10">
                <div class="card">
                    <div class="card-header bg-primary text-white">
                        <h4 class="text-center">
                            <i class="fas fa-file-contract"></i>
                            ${empty contratacion.idContratacion ? 'Nueva' : 'Actualizar'} Contratación
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

                        <form action="ContratacionServlet" method="post">
                            <input type="hidden" name="action" value="guardar">
                            <input type="hidden" name="idContratacion" value="${contratacion.idContratacion}">

                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="idEmpleado" class="form-label">Empleado <span
                                            class="text-danger">*</span></label>
                                    <select class="form-select" id="idEmpleado" name="idEmpleado" required>
                                        <option value="">-- Seleccione un empleado --</option>
                                        <c:forEach items="${empleados}" var="emp">
                                            <option value="${emp.idEmpleado}" 
                                                    ${(not empty idEmpleado && idEmpleado eq emp.idEmpleado) 
                                                      || (not empty param.idEmpleado && param.idEmpleado eq emp.idEmpleado) 
                                                      ? 'selected' : ''}>
                                                ${emp.nombreEmpleado} - ${emp.duiEmpleado.substring(0, 8)}-${emp.duiEmpleado.substring(8)}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="col-md-6 mb-3">
                                    <label for="idDepartamento" class="form-label">Departamento <span
                                            class="text-danger">*</span></label>
                                    <select class="form-select" id="idDepartamento" name="idDepartamento" required>
                                        <option value="">-- Seleccione un departamento --</option>
                                        <c:forEach items="${departamentos}" var="dept">
                                            <option value="${dept.idDepartamento}" 
                                                    ${(not empty idDepartamento && idDepartamento eq dept.idDepartamento) 
                                                      || (not empty param.idDepartamento && param.idDepartamento eq dept.idDepartamento) 
                                                      ? 'selected' : ''}>
                                                ${dept.nombreDepartamento}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="idCargo" class="form-label">Cargo <span
                                            class="text-danger">*</span></label>
                                    <select class="form-select" id="idCargo" name="idCargo" required>
                                        <option value="">-- Seleccione un cargo --</option>
                                        <c:forEach items="${cargos}" var="cargo">
                                            <option value="${cargo.idCargo}" 
                                                    ${(not empty idCargo && idCargo eq cargo.idCargo) 
                                                      || (not empty param.idCargo && param.idCargo eq cargo.idCargo) 
                                                      ? 'selected' : ''}>
                                                ${cargo.cargo}${cargo.jefatura ? ' (Jefatura)' : ''}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="col-md-6 mb-3">
                                    <label for="idTipoContratacion" class="form-label">Tipo de Contratación <span
                                            class="text-danger">*</span></label>
                                    <select class="form-select" id="idTipoContratacion" name="idTipoContratacion" required>
                                        <option value="">-- Seleccione un tipo --</option>
                                        <c:forEach items="${tiposContratacion}" var="tipo">
                                            <option value="${tipo.idTipoContratacion}" 
                                                    ${(not empty idTipoContratacion && idTipoContratacion eq tipo.idTipoContratacion) 
                                                      || (not empty param.idTipoContratacion && param.idTipoContratacion eq tipo.idTipoContratacion) 
                                                      ? 'selected' : ''}>
                                                ${tipo.tipoContratacion}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4 mb-3">
                                    <label for="fechaContratacion" class="form-label">Fecha de Contratación <span
                                            class="text-danger">*</span></label>
                                    <input type="date" class="form-control" id="fechaContratacion"
                                           name="fechaContratacion" required
                                           value="${not empty fechaContratacion ? fechaContratacion : param.fechaContratacion}">
                                    <small class="text-muted">Fecha de inicio del contrato</small>
                                </div>

                                <div class="col-md-4 mb-3">
                                    <label for="salario" class="form-label">Salario <span
                                            class="text-danger">*</span></label>
                                    <div class="input-group">
                                        <span class="input-group-text">$</span>
                                        <input type="number" class="form-control" id="salario" name="salario"
                                               required min="0" step="0.01" placeholder="0.00"
                                               value="${not empty salario ? salario : param.salario}">
                                    </div>
                                    <small class="text-muted">Salario mensual</small>
                                </div>

                                <div class="col-md-4 mb-3">
                                    <label for="estado" class="form-label">Estado <span
                                            class="text-danger">*</span></label>
                                    <select class="form-select" id="estado" name="estado" required>
                                        <option value="1" ${(empty estado || estado eq '1') ? 'selected' : ''}>
                                            Activo
                                        </option>
                                        <option value="0" ${estado eq '0' ? 'selected' : ''}>
                                            Inactivo
                                        </option>
                                    </select>
                                    <small class="text-muted">Estado del contrato</small>
                                </div>
                            </div>

                            <div class="d-flex align-items-center justify-content-center gap-3 mt-4">
                                <button type="submit" class="btn btn-primary d-flex gap-2 align-items-center">
                                    <i class="fas fa-save"></i> 
                                    ${empty contratacion.idContratacion ? 'Guardar' : 'Actualizar'}
                                </button>
                                <a href="ContratacionServlet" class="btn btn-secondary d-flex gap-2 align-items-center">
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
        document.querySelector('form').addEventListener('submit', function(e) {
            const salario = parseFloat(document.getElementById('salario').value);
            
            if (salario <= 0) {
                e.preventDefault();
                alert('El salario debe ser mayor a 0');
                return false;
            }
            
            if (salario > 999999999.99) {
                e.preventDefault();
                alert('El salario ingresado es demasiado alto');
                return false;
            }
            
            const campos = ['idEmpleado', 'idDepartamento', 'idCargo', 'idTipoContratacion'];
            for (let campo of campos) {
                const valor = document.getElementById(campo).value;
                if (!valor || valor === '') {
                    e.preventDefault();
                    alert('Por favor complete todos los campos obligatorios');
                    return false;
                }
            }
        });
        
        document.getElementById('fechaContratacion').addEventListener('change', function() {
            const fechaSeleccionada = new Date(this.value);
            const fechaActual = new Date();
            fechaActual.setHours(0, 0, 0, 0);
            
            if (fechaSeleccionada > fechaActual) {
                alert('La fecha de contratación no puede ser futura');
                this.value = '';
            }
        });
    </script>
</body>

</html>
