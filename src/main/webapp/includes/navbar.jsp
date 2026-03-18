<%-- navbar.jsp - Template de navegación reutilizable --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Obtener el parámetro que indica la página activa
    String paginaActiva = request.getParameter("activePage");
    if (paginaActiva == null)
        paginaActiva = "";
%>

<nav class="navbar navbar-expand-lg navbar-custom">
    <div class="container">
        <a class="navbar-brand" href="index.html">
            <i class="fas fa-users me-2"></i>GestiónRRHH
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link <%= paginaActiva.equals("inicio") ? "active" : ""%>" href="index.html">
                        <i class="fas fa-home me-1"></i>Inicio
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%= paginaActiva.equals("cargos") ? "active" : ""%>" href="CargoServletRescate">
                        <i class="fas fa-briefcase me-1"></i>Cargos
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%= paginaActiva.equals("empleados") ? "active" : ""%>" href="EmpleadoServlet">
                        <i class="fas fa-user-tie me-1"></i>Empleados
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%= paginaActiva.equals("departamentos") ? "active" : ""%>" href="DepartamentoServlet">
                        <i class="fas fa-building me-1"></i>Departamentos
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%= paginaActiva.equals("contrataciones") ? "active" : ""%>" href="ContratacionServlet">
                        <i class="fas fa-file-contract me-1"></i>Contrataciones
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
