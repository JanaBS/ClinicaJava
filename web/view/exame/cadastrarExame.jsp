<%-- 
    Document   : cadastrarExame.jsp
    Created on : 03/12/2022, 21:32:07
    Author     : janaina.borges
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="aplicacao.TipoExame"%>
<%@page import="model.ExameDAO"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="bootstrap/bootstrap.min.css" rel="stylesheet">
    <title>Cadastrar exame</title>
</head>
<body>
   <jsp:include page="../../menuPacienteMedico.jsp"/>

    <div class="container-sm mt-4">
        <% String idconsulta = (String) request.getParameter("idconsulta"); %>
        <%  String descricao = (String) request.getParameter("descricao"); %>
        <div class="container position-absolute top-50 start-50 translate-middle p-4 rounded">
            <form action="MedicoController?btEnviar=IncluirExame&descricao=<%=descricao%>" method="POST">
                <input type="hidden" name="idconsulta" value="<%=idconsulta%>" class="form-control">
                <div class="row justify-content-center">
                        <div class="col-sm-6">
                            <label for="ControlSelect2"  class="form-label">Nome do Exame</label>
                            <%
                                        ExameDAO exameDAO = new ExameDAO();
                                        ArrayList<TipoExame> listaExame = exameDAO.ListaTipoExame();
                                        out.println("<select class='form-control' name='idexame'>");
                                        if (!listaExame.isEmpty()) {
                                            for (TipoExame tipoExame : listaExame) {

                                                out.println("<option value='"+tipoExame.getId()+"'>" + tipoExame.getDescricao() + "</option>");
                                            }
                                            out.println("</select>"); }                      %>
                        </div>
                         
                </div>
                <div class="row justify-content-center">
                    <div class="col-6 mt-5">
                        <button type="submit" class="btn btn-light mt-3 text-danger">Solicitar Exame</button>
                      </div>
                </div>
           </form> 
        </div>
    </div>
    <script src="bootstrap\bootstrap.bundle.min.js" ></script>
</body>
</html>
