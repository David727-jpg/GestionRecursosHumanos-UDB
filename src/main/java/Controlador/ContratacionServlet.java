package Controlador;

import DataAccess.ContratacionDAO;
import DataAccess.DepartamentoDAO;
import DataAccess.EmpleadoDAO;
import DataAccess.CargoDAO;
import DataAccess.TipoContratacionDAO;
import com.udb.modelo.Contratacion;
import com.udb.modelo.Departamento;
import com.udb.modelo.Empleado;
import com.udb.modelo.Cargo;
import com.udb.modelo.TipoContratacion;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.ArrayList;

@WebServlet(name = "ContratacionServlet", urlPatterns = { "/ContratacionServlet" })
public class ContratacionServlet extends HttpServlet {

    ContratacionDAO contratacionDAO = new ContratacionDAO();
    DepartamentoDAO departamentoDAO = new DepartamentoDAO();
    EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    CargoDAO cargoDAO = new CargoDAO();
    TipoContratacionDAO tipoContratacionDAO = new TipoContratacionDAO();

    private ArrayList<String> validarDatos(String idDepartamento, String idEmpleado, String idCargo,
            String idTipoContratacion, String fechaStr, String salarioStr, String estado) {
        ArrayList<String> errores = new ArrayList<>();

        if (idDepartamento == null || idDepartamento.trim().isEmpty()) {
            errores.add("Debe seleccionar un departamento");
        } else {
            try {
                Integer.parseInt(idDepartamento);
            } catch (NumberFormatException e) {
                errores.add("El departamento seleccionado no es válido");
            }
        }

        if (idEmpleado == null || idEmpleado.trim().isEmpty()) {
            errores.add("Debe seleccionar un empleado");
        } else {
            try {
                Integer.parseInt(idEmpleado);
            } catch (NumberFormatException e) {
                errores.add("El empleado seleccionado no es válido");
            }
        }

        if (idCargo == null || idCargo.trim().isEmpty()) {
            errores.add("Debe seleccionar un cargo");
        } else {
            try {
                Integer.parseInt(idCargo);
            } catch (NumberFormatException e) {
                errores.add("El cargo seleccionado no es válido");
            }
        }

        if (idTipoContratacion == null || idTipoContratacion.trim().isEmpty()) {
            errores.add("Debe seleccionar un tipo de contratación");
        } else {
            try {
                Integer.parseInt(idTipoContratacion);
            } catch (NumberFormatException e) {
                errores.add("El tipo de contratación seleccionado no es válido");
            }
        }

        if (fechaStr == null || fechaStr.trim().isEmpty()) {
            errores.add("La fecha de contratación es obligatoria");
        } else {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.setLenient(false);
                Date fechaContratacion = sdf.parse(fechaStr);

                Date fechaActual = new Date();
                if (fechaContratacion.after(fechaActual)) {
                    errores.add("La fecha de contratación no puede ser futura");
                }
            } catch (ParseException e) {
                errores.add("La fecha debe tener formato YYYY-MM-DD");
            }
        }

        if (salarioStr == null || salarioStr.trim().isEmpty()) {
            errores.add("El salario es obligatorio");
        } else {
            try {
                double salario = Double.parseDouble(salarioStr);
                if (salario <= 0) {
                    errores.add("El salario debe ser mayor a 0");
                } else if (salario > 999999999.99) {
                    errores.add("El salario es demasiado alto");
                }
            } catch (NumberFormatException e) {
                errores.add("El salario debe ser un número válido");
            }
        }

        if (estado == null || estado.trim().isEmpty()) {
            errores.add("Debe seleccionar el estado");
        }

        return errores;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        if (action == null || action.equals("listar")) {
            listarContrataciones(request, response);
        } else if (action.equals("nuevo")) {
            mostrarFormularioRegistro(request, response);
        } else if (action.equals("guardar")) {
            guardarContratacion(request, response);
        } else if (action.equals("editar")) {
            mostrarFormularioEdicion(request, response);
        } else if (action.equals("eliminar")) {
            eliminarContratacion(request, response);
        } else {
            listarContrataciones(request, response);
        }
    }

    private void listarContrataciones(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Contratacion> lista = contratacionDAO.listar();
        request.setAttribute("contrataciones", lista);
        request.getRequestDispatcher("/listarContrataciones.jsp").forward(request, response);
    }

    private void mostrarFormularioRegistro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Cargar listas para los selectores
        List<Departamento> departamentos = departamentoDAO.listar();
        List<Empleado> empleados = empleadoDAO.listar(true);
        List<Cargo> cargos = cargoDAO.listar();
        List<TipoContratacion> tiposContratacion = tipoContratacionDAO.listar();

        request.setAttribute("departamentos", departamentos);
        request.setAttribute("empleados", empleados);
        request.setAttribute("cargos", cargos);
        request.setAttribute("tiposContratacion", tiposContratacion);

        request.getRequestDispatcher("/registrarContratacion.jsp").forward(request, response);
    }

    private void guardarContratacion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String idDepartamento = request.getParameter("idDepartamento");
            String idEmpleado = request.getParameter("idEmpleado");
            String idCargo = request.getParameter("idCargo");
            String idTipoContratacion = request.getParameter("idTipoContratacion");
            String fechaStr = request.getParameter("fechaContratacion");
            String salarioStr = request.getParameter("salario");
            String estado = request.getParameter("estado");

            ArrayList<String> errores = validarDatos(idDepartamento, idEmpleado, idCargo,
                    idTipoContratacion, fechaStr, salarioStr, estado);

            if (!errores.isEmpty()) {
                request.setAttribute("errores", errores);
                request.setAttribute("idDepartamento", idDepartamento);
                request.setAttribute("idEmpleado", idEmpleado);
                request.setAttribute("idCargo", idCargo);
                request.setAttribute("idTipoContratacion", idTipoContratacion);
                request.setAttribute("fechaContratacion", fechaStr);
                request.setAttribute("salario", salarioStr);
                request.setAttribute("estado", estado);
                mostrarFormularioRegistro(request, response);
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaContratacion = sdf.parse(fechaStr);

            Contratacion cont = new Contratacion();
            cont.setIdDepartamento(Integer.parseInt(idDepartamento));
            cont.setIdEmpleado(Integer.parseInt(idEmpleado));
            cont.setIdCargo(Integer.parseInt(idCargo));
            cont.setIdTipoContratacion(Integer.parseInt(idTipoContratacion));
            cont.setFechaDeContratacion(fechaContratacion);
            cont.setSalario(Double.parseDouble(salarioStr));
            cont.setEstado(estado.equals("1"));

            String idParam = request.getParameter("idContratacion");
            boolean resultado;

            if (idParam != null && !idParam.trim().isEmpty()) {
                cont.setIdContratacion(Integer.parseInt(idParam));
                resultado = contratacionDAO.actualizar(cont);
            } else {
                resultado = contratacionDAO.agregar(cont);
            }

            if (resultado) {
                request.setAttribute("mensaje", "Contratación guardada exitosamente");
            } else {
                String errorMsg = contratacionDAO.getUltimoError();
                request.setAttribute("errores", errores);
                request.setAttribute("idDepartamento", idDepartamento);
                request.setAttribute("idEmpleado", idEmpleado);
                request.setAttribute("idCargo", idCargo);
                request.setAttribute("idTipoContratacion", idTipoContratacion);
                request.setAttribute("fechaContratacion", fechaStr);
                request.setAttribute("salario", salarioStr);
                request.setAttribute("estado", estado);
                request.setAttribute("error", errorMsg.isEmpty() ? "No se pudo guardar la contratación" : errorMsg);
                mostrarFormularioRegistro(request, response);
                return;
            }

            listarContrataciones(request, response);
        } catch (Exception e) {
            System.out.println("Error en guardarContratacion: " + e.getMessage());
            request.setAttribute("error", "Error al guardar: " + e.getMessage());
            mostrarFormularioRegistro(request, response);
        }
    }

    private void mostrarFormularioEdicion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id");

            if (idParam == null || idParam.trim().isEmpty()) {
                request.setAttribute("error", "ID no válido");
                listarContrataciones(request, response);
                return;
            }

            int id = Integer.parseInt(idParam);
            Contratacion contratacionEncontrada = contratacionDAO.obtenerPorId(id);

            if (contratacionEncontrada != null) {
                // Cargar listas para los selectores
                List<Departamento> departamentos = departamentoDAO.listar();
                List<Empleado> empleados = empleadoDAO.listar();
                List<Cargo> cargos = cargoDAO.listar();
                List<TipoContratacion> tiposContratacion = tipoContratacionDAO.listar();

                request.setAttribute("departamentos", departamentos);
                request.setAttribute("empleados", empleados);
                request.setAttribute("cargos", cargos);
                request.setAttribute("tiposContratacion", tiposContratacion);

                request.setAttribute("contratacion", contratacionEncontrada);
                request.setAttribute("idDepartamento", String.valueOf(contratacionEncontrada.getIdDepartamento()));
                request.setAttribute("idEmpleado", String.valueOf(contratacionEncontrada.getIdEmpleado()));
                request.setAttribute("idCargo", String.valueOf(contratacionEncontrada.getIdCargo()));
                request.setAttribute("idTipoContratacion", String.valueOf(contratacionEncontrada.getIdTipoContratacion()));
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (contratacionEncontrada.getFechaDeContratacion() != null) {
                    request.setAttribute("fechaContratacion",
                            sdf.format(contratacionEncontrada.getFechaDeContratacion()));
                }
                
                request.setAttribute("salario", String.valueOf(contratacionEncontrada.getSalario()));
                request.setAttribute("estado", contratacionEncontrada.isEstado() ? "1" : "0");

                request.getRequestDispatcher("/registrarContratacion.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Contratación no encontrada");
                listarContrataciones(request, response);
            }

        } catch (NumberFormatException e) {
            request.setAttribute("error", "ID no válido");
            listarContrataciones(request, response);
        }
    }

    private void eliminarContratacion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String idParam = request.getParameter("id");

            if (idParam == null || idParam.trim().isEmpty()) {
                request.setAttribute("error", "ID no válido");
                listarContrataciones(request, response);
                return;
            }

            int id = Integer.parseInt(idParam);
            boolean resultado = contratacionDAO.eliminar(id);

            if (resultado) {
                request.setAttribute("mensaje", "Contratación eliminada exitosamente");
            } else {
                request.setAttribute("error", "No se pudo eliminar la contratación");
            }

            listarContrataciones(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("error", "ID no válido");
            listarContrataciones(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para gestionar Contrataciones";
    }
}
