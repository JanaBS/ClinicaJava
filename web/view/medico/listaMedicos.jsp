<%-- 
    Document   : listaMedicos
    Created on : 07/12/2022, 22:47:05
    Author     : janaina.borges
--%>
<%@page import="model.AdministradorDAO"%>
<%@page import="aplicacao.Especialidade"%>
<%@page import="java.util.ArrayList"%>
<%@page import="aplicacao.Medico"%>
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
                <a href="AdministradorController?acao=IncluirMedico" class="mb-2 btn btn-light text-danger">Incluir</a>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Nome</th>
                                <th scope="col">CRM</th>
                                <th scope="col">UF CRM</th>
                                <th scope="col">CPF</th>
                                <th scope="col">Autorizado</th>
                                <th scope="col">Especialidade</th>
                                <th scope="col">Açoes</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                
                                ArrayList<Medico> listaMedicos = (ArrayList<Medico>) request.getAttribute("listaMedicos");
                                PacienteDAO pacienteDAO = new PacienteDAO();
                                for (Medico medico : listaMedicos) {
                                    
                                    Especialidade especialidade = (Especialidade) pacienteDAO.getEspecialidade(medico.getIdEspecialidade());
                                    out.println("<tr>");
                                    out.println("<th>" + medico.getId() + "</th>");
                                    out.println("<td>" + medico.getNome() + "</td>");
                                    out.println("<td>" + medico.getCrm() + "</td>");
                                    out.println("<td>" + medico.getEstadoCrm() + "</td>");
                                    out.println("<td>" + medico.getCpf() + "</td>");
                                    out.println("<td>" + medico.getAutorizado() + "</td>");
                                    out.println("<td>" + especialidade.getDescricao() + "</td>");
                            %>
                        <td><a href="AdministradorController?acao=ListarConsultas&id=<%=medico.getId()%>" class="btn btn-light text-danger">Visualizar consultas</a>
                            <a href="AdministradorController?acao=AlterarMedico&id=<%=medico.getId()%>" class="btn btn-warning">Alterar</a>
                            <a href="AdministradorController?acao=ExcluirMedico&id=<%=medico.getId()%>" class="btn btn-danger">Excluir</a></td>
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
