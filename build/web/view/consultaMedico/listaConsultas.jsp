<%-- 
    Document   : consultaMedico
    Created on : 17/11/2022, 18:02:43
    Author     : janaina.borges
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="aplicacao.Consulta"%>
<%@page import="model.PacienteDAO"%>
<%@page import="aplicacao.Usuario"%>
<%@page import="aplicacao.Paciente"%>
<%@page import="model.MedicoDAO"%>
<%@page import="aplicacao.Especialidade"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <link href="../bootstrap/bootstrap.min.css" rel="stylesheet">
        <title>Pacientes</title>
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

            <div class="table-responsive mt-5">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Paciente</th>
                            <th scope="col">Data</th>
                            <th scope="col">Descrição</th>
                            <th scope="col">Realizada</th>
                            <th scope="col">Açoes</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<Consulta> listaConsultas = (ArrayList<Consulta>) request.getAttribute("listaConsultas");
                            if (!listaConsultas.isEmpty()) {
                                for (Consulta consulta : listaConsultas) {
                                    PacienteDAO pacienteDAO = new PacienteDAO();
                                    Paciente paciente = pacienteDAO.getPaciente(consulta.getIdPaciente());
                                    //Especialidade especialidade = (Especialidade) pacienteDAO.getEspecialidade(medico.getIdEspecialidade());
                                    out.println("<tr>");
                                    out.println("<th>" + consulta.getId() + "</th>");
                                    out.println("<td>" + paciente.getNome() + "</td>");
                                    out.println("<td>" + consulta.getData() + "</td>");
                                    out.println("<td>" + consulta.getDescricao() + "</td>");
                                    out.println("<td>" + consulta.getRealizada() + "</td>");
                        %>
                        
                    <td><a href="MedicoController?acao=RealizarConsulta&idconsulta=<%=consulta.getId()%>&descricao=<%=consulta.getDescricao()%>" class="btn btn-light text-danger" METHOD="GET">Realizar/ Editar Consulta</a></td>

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
        <script src="../bootstrap\bootstrap.bundle.min.js" ></script> 
    </body>
</html>
