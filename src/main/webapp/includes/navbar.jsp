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
                <li class="nav-item">
                    <a class="nav-link <%= paginaActiva.equals("tiposContratacion") ? "active" : ""%>" href="TipoContratacionServlet">
                        <i class="fas fa-list-alt me-1"></i>Tipos Contratación
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- MODAL DE ADVERTENCIA -->
<div class="modal fade" id="modalAdvertencia" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header bg-purple text-white" style="background-color:  #6f42c1;">
                <h5 class="modal-title">
                    <i class="fas fa-exclamation-triangle me-2"></i>¿Salir sin guardar?
                </h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                Tienes cambios sin guardar. Si salés ahora, se perderán todos los datos ingresados.
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" data-bs-dismiss="modal">
                    <i class="fas fa-arrow-left me-1"></i>Quedarse aquí
                </button>
                <a href="#" id="btnConfirmarNavegacion" class="btn btn-danger">
                    <i class="fas fa-sign-out-alt me-1"></i>Salir sin guardar
                </a>
            </div>
        </div>
    </div>
</div>
                       
<script>
window.addEventListener('load', function () {
    setTimeout(function() {
        const form = document.querySelector('form');
        const modalEl = document.getElementById('modalAdvertencia');

        if (!form || !modalEl || typeof bootstrap === 'undefined') return;

        let formularioModificado = false;
        let destinoNavegacion = '';

        form.addEventListener('input', () => formularioModificado = true);
        form.addEventListener('change', () => formularioModificado = true);
        form.addEventListener('submit', () => formularioModificado = false);

        document.querySelectorAll('.navbar a, .nav-link').forEach(link => {
            link.addEventListener('click', function (e) {
                const href = this.getAttribute('href');
                if (!href || href === '#') return;
                if (formularioModificado) {
                    e.preventDefault();
                    destinoNavegacion = href;
                    bootstrap.Modal.getOrCreateInstance(modalEl).show();
                }
            });
        });

        document.getElementById('btnConfirmarNavegacion').addEventListener('click', function () {
            formularioModificado = false;
            bootstrap.Modal.getInstance(modalEl).hide();
            window.location.href = destinoNavegacion;
        });
    }, 300);
});
</script>