<%-- 
    Document   : listaPacientes
    Created on : 15/11/2022, 10:34:59
    Author     : janaina.borges
--%>
<%@page import="model.AdministradorDAO"%>
<%@page import="aplicacao.TipoPlano"%>
<%@page import="java.util.ArrayList"%>
<%@page import="aplicacao.Paciente"%>
<%@page import="model.PacienteDAO"%>
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
        <jsp:include page="../../menuAdministrador.jsp"/>
        <%  String msgOperacaoRealizada = (String) request.getAttribute("msgOperacaoRealizada");
                    if ((msgOperacaoRealizada != null) && (!msgOperacaoRealizada.isEmpty())) {%>

                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <strong><%= msgOperacaoRealizada%></strong>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>

                <% }%>
                <div class="container mt-5">
                <a href="AdministradorController?acao=IncluirPaciente" class="mb-2 btn btn-light text-danger">Incluir</a>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Nome</th>
                                <th scope="col">CPF</th>
                                <th scope="col">Autorizado</th>
                                <th scope="col">Plano</th>
                                <th scope="col">Açoes</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                
                                ArrayList<Paciente> listaPacientes = (ArrayList<Paciente>) request.getAttribute("listaPacientes");
                                PacienteDAO pacienteDAO = new PacienteDAO();
                                for (Paciente paciente : listaPacientes) {
                                    
                                    TipoPlano tipoPlano = (TipoPlano) pacienteDAO.getPlano(paciente.getIdTipoPlano());
                                    out.println("<tr>");
                                    out.println("<th>" + paciente.getId() + "</th>");
                                    out.println("<td>" + paciente.getNome() + "</td>");
                                    out.println("<td>" + paciente.getCpf() + "</td>");
                                    out.println("<td>" + paciente.getAutorizado() + "</td>");
                                    out.println("<td>" + tipoPlano.getDescricao() + "</td>");
                            %>
                        <td><a href="AdministradorController?acao=AlterarPaciente&id=<%=paciente.getId()%>" class="btn btn-warning">Alterar</a>
                    <td><form action="AdministradorController?btEnviar=ExcluirPaciente&id=<%=paciente.getId()%>" method="POST"><button class="btn btn-danger" type="submit">Excluir</button></form></td>
                            <%   out.println("</tr>");
                                }
                            %>

                        </tbody>
                    </table>
                </div>
         </div>                   
        <script src="../bootstrap\bootstrap.bundle.min.js" ></script> 
    </body>
</html>
