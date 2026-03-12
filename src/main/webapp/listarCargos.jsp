<%-- 
    Document   : listarCargos
    Created on : 12 mar 2026, 3:40:45 a. m.
    Author     : moica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Establecer parámetros para la plantilla --%>
<c:set var="titulo" value="Listado de Cargos" scope="request"/>
<c:set var="contenido" value="contenidoCargos.jsp" scope="request"/>

<%-- Incluir la plantilla base --%>
<jsp:include page="base.jsp">
    <jsp:param name="titulo" value="Listado de Cargos"/>
    <jsp:param name="contenido" value="contenidoCargos.jsp"/>
</jsp:include>