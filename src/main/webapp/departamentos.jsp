<%-- 
    Document   : departamentos
    Created on : 11 mar 2026, 00:09:26
    Author     : josed
--%>

<%@page import="java.util.List"%>
<%@page import="com.udb.modelo.Departamento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Gestión de Departamentos</title>
        
        <!-- Bootstrap 5 CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Bootstrap Icons (opcional pero muy útil) -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    </head>
    <body class="bg-light d-flex flex-column min-vh-100">

        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary shadow-sm">
            <div class="container">
                <a class="navbar-brand" href="#">
                    <i class="bi bi-building"></i> RRHH - Gestión de Departamentos
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item">
                            <a class="nav-link active" href="#"><i class="bi bi-house-door"></i> Inicio</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#"><i class="bi bi-people"></i> Empleados</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#"><i class="bi bi-gear"></i> Configuración</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Contenido principal -->
        <div class="container my-4 flex-grow-1">
            <!-- Cabecera con título y botón de nuevo -->
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h1 class="h3 text-primary">
                    <i class="bi bi-grid-3x3-gap-fill"></i> Lista de Departamentos
                </h1>
                <a href="nuevoDepartamento.jsp" class="btn btn-success">
                    <i class="bi bi-plus-circle"></i> Nuevo Departamento
                </a>
            </div>

            <!-- Card con buscador y tabla -->
            <div class="card shadow border-0">
                <div class="card-header bg-white py-3">
                    <div class="row g-3 align-items-center">
                        <div class="col-md-6">
                            <form class="d-flex" onsubmit="event.preventDefault();">
                                <input class="form-control me-2" type="search" id="buscador" placeholder="Buscar por nombre o descripción..." aria-label="Buscar">
                                <button class="btn btn-outline-primary" type="submit">
                                    <i class="bi bi-search"></i>
                                </button>
                            </form>
                        </div>
                        <div class="col-md-6 text-md-end">
                            <span class="text-muted">
                                <i class="bi bi-info-circle"></i> Total: 
                                <strong>${listaDepartamentos != null ? listaDepartamentos.size() : 0}</strong> departamentos
                            </span>
                        </div>
                    </div>
                </div>
                <div class="card-body p-0">
                    <div class="table-responsive">
                        <table class="table table-hover align-middle mb-0" id="tablaDepartamentos">
                            <thead class="bg-light">
                                <tr>
                                    <th>ID</th>
                                    <th>Nombre</th>
                                    <th>Descripción</th>
                                    <th class="text-center">Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    List<Departamento> lista = (List<Departamento>) request.getAttribute("listaDepartamentos");
                                    if (lista != null && !lista.isEmpty()) {
                                        for (Departamento depto : lista) {
                                %>
                                <tr>
                                    <td><span class="badge bg-secondary"><%= depto.getIdDepartamento() %></span></td>
                                    <td class="fw-bold"><%= depto.getNombreDepartamento() %></td>
                                    <td><%= depto.getDescripcionDepartamento() %></td>
                                    <td class="text-center">
                                        <a href="#" class="btn btn-sm btn-outline-primary" title="Editar">
                                            <i class="bi bi-pencil"></i>
                                        </a>
                                        <a href="#" class="btn btn-sm btn-outline-danger" title="Eliminar" onclick="return confirm('¿Eliminar este departamento?')">
                                            <i class="bi bi-trash"></i>
                                        </a>
                                    </td>
                                </tr>
                                <%
                                        }
                                    } else {
                                %>
                                <tr>
                                    <td colspan="4" class="text-center py-5">
                                        <i class="bi bi-folder-x display-1 text-muted"></i>
                                        <p class="lead text-muted mt-3">No hay departamentos registrados aún.</p>
                                        <a href="nuevoDepartamento.jsp" class="btn btn-primary">
                                            <i class="bi bi-plus-circle"></i> Crear primer departamento
                                        </a>
                                    </td>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
                <!-- Paginación simple (placeholder) -->
                <div class="card-footer bg-white">
                    <nav aria-label="...">
                        <ul class="pagination justify-content-end mb-0">
                            <li class="page-item disabled">
                                <span class="page-link">Anterior</span>
                            </li>
                            <li class="page-item active"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item">
                                <a class="page-link" href="#">Siguiente</a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <footer class="bg-white text-center text-muted py-3 mt-auto border-top">
            <div class="container">
                <small>&copy; 2025 RRHH Universidad. Todos los derechos reservados.</small>
            </div>
        </footer>

        <!-- Bootstrap JS (necesario para el navbar toggler y otros componentes interactivos) -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        
        <!-- Script para filtro en tiempo real -->
        <script>
            document.getElementById('buscador').addEventListener('keyup', function() {
                let input = this.value.toLowerCase();
                let filas = document.querySelectorAll('#tablaDepartamentos tbody tr');
                filas.forEach(fila => {
                    // Si la fila es la de "no hay datos" (con colspan) la ignoramos
                    if (fila.children.length === 1 && fila.children[0].colSpan === 4) return;
                    
                    let texto = fila.innerText.toLowerCase();
                    fila.style.display = texto.includes(input) ? '' : 'none';
                });
            });
        </script>
    </body>
</html>