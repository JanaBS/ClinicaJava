<%-- 
    Document   : realizarConsulta
    Created on : 17/11/2022, 18:05:25
    Author     : janaina.borges
--%>

<%@page import="model.ExameDAO"%>
<%@page import="aplicacao.TipoExame"%>
<%@page import="aplicacao.Exames"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <link href="../bootstrap/bootstrap.min.css" rel="stylesheet">
        <title>Realizar Consulta</title>
    </head>
    <body>
        <jsp:include page="../../menuPacienteMedico.jsp"/>
        <div class="container">
             <%  String descricao = (String) request.getParameter("descricao"); 
                 String id = (String) request.getParameter("idconsulta");%>
            <div class="container  p-4 rounded">
                <form action="MedicoController?btEnviar=CadastrarConsulta&idconsulta=<%=id%>" method="POST">
                    <div class="row justify-content-start">
                        <div class="col-12">
                            <div class="h5 text-left text-dark mb-3">Marcação de consulta</div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-12">
                            <label for="ControlSelect1"  class="form-label">Descrição</label>
                            <textarea type="textarea" class="form-control" id="ControlSelect1" name="descricao"><%=descricao%></textarea>                        
                        </div>  
                    </div>

                    <div class="row">
                        <div class="col-4">
                            <div class="table-responsive mt-5">
                                <table class="table table-sm">
                                    <thead>
                                        <tr>
                                            <th scope="col">Descrição</th>
                                            <th scope="col">excluir</th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <%  
                                            
                                            ExameDAO exameDAO = new ExameDAO();
                                            ArrayList<Exames> listaExames = (ArrayList<Exames>) exameDAO.ListaExames(Integer.parseInt(id));
                                            if (!listaExames.isEmpty()) {
                                                for (Exames exame : listaExames) {

                                                    TipoExame tipoExame = exameDAO.getTipoExame(exame.getIdTipoExame());
                                                    //Especialidade especialidade = (Especialidade) pacienteDAO.getEspecialidade(medico.getIdEspecialidade());
                                                    out.println("<tr scope='row'>");
                                                    out.println("<th>" + tipoExame.getDescricao() + "</th>");
                                        %>
                                        <td><form action="MedicoController?btEnviar=ExcluirExame&idexame=<%=exame.getId()%>&idconsulta=<%=id%>&descricao=<%=descricao%>" method="POST"><button class="btn btn-danger" type="submit">X</button></form></td>
                                        <%   out.println("</tr>");
                                        } }
                                    %>
                                    </tbody>

                                </table>

                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-auto">
                            <a href="MedicoController?acao=Listar" type="button" class="btn btn-light text-danger mt-3">Voltar</a>
                         </div>
                        <div class="col-auto">
                            <button type="submit" class="btn btn-light text-danger mt-3">Realizar consulta</button>
                         </div>
                        <div class="col-auto">
                            <a href="MedicoController?acao=cadastrarExame&idconsulta=<%=id%>&descricao=<%=descricao%>" type="button" type="button" class="btn btn-light text-danger mt-3">Solicitar Exames</a>
                         </div>
                    </div> 
                </form>      
            </div>    
        </div>
    </body>
</html>


            
           
            
        
    