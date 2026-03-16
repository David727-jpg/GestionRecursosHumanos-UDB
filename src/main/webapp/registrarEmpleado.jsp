<%-- Document : registrarEmpleado Created on : 12 mar 2026, 9:03:18 p. m. Author : moica --%>

    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Registrar Empleado</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
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
                                    <a class="nav-link" href="CargoServlet">
                                        <i class="fas fa-briefcase me-1"></i>Cargos
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link active" href="EmpleadoServlet">
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
                <div class="container mt-5">
                    <div class="row justify-content-center">
                        <div class="col-md-8">
                            <div class="card">
                                <div class="card-header bg-primary text-white">
                                    <h4 class="text-center"> ${empty empleado.idEmpleado
                                        ? 'Guardar' : 'Actualizar'} Empleado</h4>
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

                                    <form action="EmpleadoServlet" method="post">
                                        <input type="hidden" name="action" value="guardar">
                                        <input type="hidden" name="idEmpleado" value="${empleado.idEmpleado}">

                                        <div class="row">
                                            <div class="col-md-6 mb-3">
                                                <label for="dui" class="form-label">DUI <span
                                                        class="text-danger">*</span></label>
                                                <input type="text" class="form-control" id="dui" name="dui" required
                                                    maxlength="10" placeholder="00000000-0" pattern="\d{8}-\d{1}"
                                                    title="Formato: 12345678-9" inputmode="numeric"
                                                    value="${not empty dui ? dui : param.dui}">
                                            </div>

                                            <div class="col-md-6 mb-3">
                                                <label for="nombre" class="form-label">Nombre Completo <span
                                                        class="text-danger">*</span></label>
                                                <input type="text" class="form-control" id="nombre" name="nombre"
                                                    required maxlength="100"
                                                    value="${not empty nombre ? nombre : param.nombre}">
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6 mb-3">
                                                <label for="usuario" class="form-label">Usuario</label>
                                                <input type="text" class="form-control" id="usuario" name="usuario"
                                                    maxlength="50"
                                                    value="${not empty usuario ? usuario : param.usuario}">
                                            </div>

                                            <div class="col-md-6 mb-3">
                                                <label for="telefono" class="form-label">Teléfono</label>
                                                <input type="text" class="form-control" id="telefono" name="telefono"
                                                    maxlength="9" placeholder="0000-0000" pattern="\d{4}-\d{4}"
                                                    title="Formato: 1234-5678" inputmode="numeric"
                                                    value="${not empty telefono ? telefono : param.telefono}">
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6 mb-3">
                                                <label for="correo" class="form-label">Correo Electrónico</label>
                                                <input type="email" class="form-control" id="correo" name="correo"
                                                    maxlength="100" value="${not empty correo ? correo : param.correo}">
                                            </div>

                                            <div class="col-md-6 mb-3">
                                                <label for="fechaNacimiento" class="form-label">Fecha de
                                                    Nacimiento</label>
                                                <input type="date" class="form-control" id="fechaNacimiento"
                                                    name="fechaNacimiento"
                                                    value="${not empty fechaNacimiento ? fechaNacimiento : param.fechaNacimiento}">
                                            </div>
                                        </div>

                                        <div class="d-flex align-items-center justify-content-center gap-3">
                                            <button type="submit" class="btn btn-primary d-flex gap-2 align-items-center">
                                                <i class="fas fa-save"></i> ${empty empleado.idEmpleado ? 'Guardar' : 'Actualizar'}
                                            </button>
                                            <a href="EmpleadoServlet" class="btn btn-secondary d-flex gap-2 align-items-center">
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
                <script src="js/funciones.js"></script>
            </body>

            </html>