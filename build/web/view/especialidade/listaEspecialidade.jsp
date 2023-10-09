<%-- 
    Document   : listaEspecialidade
    Created on : 08/12/2022, 13:39:13
    Author     : janaina.borges
--%>

<%@page import="aplicacao.Especialidade"%>
<%@page import="aplicacao.Administrador"%>
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
        <jsp:include page="../../menuAdministrador.jsp"/>
        <div class="container mt-4">
            <%  String msgOperacaoRealizada = (String) request.getAttribute("msgOperacaoRealizada");
            if ((msgOperacaoRealizada != null) && (!msgOperacaoRealizada.isEmpty())) {%>

            <div class="alert alert-success alert-dismissible fade show" role="alert">
                <strong><%= msgOperacaoRealizada%></strong>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>

            <% }%>

            <a href="AdministradorController?acao=IncluirEspecialidade" class="mb-2 btn btn-light text-danger" METHOD="GET">Incluir</a>
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Descrição</th>
                            <th scope="col">Açoes</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<Especialidade> listaEspecialidades = (ArrayList<Especialidade>) request.getAttribute("listaEspecialidades");
                            if (!listaEspecialidades.isEmpty()) {
                                for (Especialidade especialidade : listaEspecialidades) {
                                   
                                    out.println("<tr>");
                                    out.println("<th>" + especialidade.getId() + "</th>");
                                    out.println("<td>" + especialidade.getDescricao() + "</td>");

                        %>
                    <td><a href="AdministradorController?acao=AlterarEspecialidade&id=<%=especialidade.getId()%>" class="btn btn-warning">Alterar</a>
                    <a href="AdministradorController?acao=ExcluirEspecialidade&id=<%=especialidade.getId()%>" class="btn btn-danger">Excluir</a></td>
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

