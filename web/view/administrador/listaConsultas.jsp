<%-- 
    Document   : listaConsultas
    Created on : 11/12/2022, 10:24:31
    Author     : janaina.borges
--%>

<%@page import="model.PacienteDAO"%>
<%@page import="aplicacao.Medico"%>
<%@page import="aplicacao.Especialidade"%>
<%@page import="model.MedicoDAO"%>
<%@page import="aplicacao.Consulta"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../bootstrap/bootstrap.min.css" rel="stylesheet">
        <title>Clinica médica - Consultas</title>
    </head>
    <body>
        <jsp:include page="../../menuPacienteMedico.jsp"/>
        <div class="container mt-4">
            <%  String msgOperacaoRealizada = (String) request.getAttribute("msgOperacaoRealizada");
            if ((msgOperacaoRealizada != null) && (!msgOperacaoRealizada.isEmpty())) {%>

            <div class="alert alert-success alert-dismissible fade show" role="alert">
                <strong><%= msgOperacaoRealizada%></strong>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>

            <% }%>

            <a href="AdministradorController?acao=ListarMedico" class="mb-2 btn btn-light text-danger" METHOD="GET">Voltar</a>
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Médico / Especialidade</th>
                            <th scope="col">Data</th>
                            <th scope="col">Realizada</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<Consulta> listaConsultas = (ArrayList<Consulta>) request.getAttribute("listaConsultas");
                            if (!listaConsultas.isEmpty()) {
                                for (Consulta consulta : listaConsultas) {
                                    MedicoDAO medicoDAO = new MedicoDAO();
                                    PacienteDAO pacienteDAO = new PacienteDAO();
                                    Medico medico = medicoDAO.getMedico(consulta.getIdMedico());
                                    Especialidade especialidade = (Especialidade) pacienteDAO.getEspecialidade(medico.getIdEspecialidade());
                                    out.println("<tr>");
                                    out.println("<th>" + consulta.getId() + "</th>");
                                    out.println("<td>" + medico.getNome() + " - " + especialidade.getDescricao() + "</td>");
                                    out.println("<td>" + consulta.getData() + "</td>");
                                    out.println("<td>" + consulta.getRealizada() + "</td>");
                        %>
                    
                        <%   out.println("</tr>");
                            }
                        %>
                    </tbody>
                </table>
             </div>
               <% } else {
                                out.println("<h1>");
                                out.println("No momento não há consultas agendadas");
                                out.println("</h1>");
                                }
               %>
           
        </div>
    </body>
</html>

