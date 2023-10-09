<%-- 
    Document   : marcacaoConsulta
    Created on : 19/11/2022, 22:04:20
    Author     : janaina.borges
--%>
<%@page import="aplicacao.Especialidade"%>
<%@page import="aplicacao.Consulta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.PacienteDAO"%>
<%@page import="aplicacao.Medico"%>
<%@page import="model.MedicoDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <link href="../bootstrap/bootstrap.min.css" rel="stylesheet">
    <title>Alteração de consulta</title>
</head>
<body>
    <jsp:include page="../../menuPacienteMedico.jsp"/>
    <div class="container-sm">
        <div class="container position-absolute top-50 start-50 translate-middle p-4 rounded">
            <div class="row justify-content-center">
                <div class="col-8">
                    <div class="h5 text-left text-dark mb-3">Alteração de consulta</div>
                </div>
            </div>
            <% String id = (String) request.getAttribute("idconsulta"); 
            %>
            <form action="PacienteController?btEnviar=AlterarConsulta&id=<%=id%>" method="POST">
                <div class="row justify-content-center">
                        <div class="col-sm-4">
                            <label for="ControlSelect2"  class="form-label">Médico</label>
                            <%
                                        MedicoDAO medicoDAO = new MedicoDAO();
                                        PacienteDAO pacienteDAO = new PacienteDAO();
                                        ArrayList<Medico> listaMedico = medicoDAO.listaMedicos();
                                        out.println("<select class='form-control' name='idmedico'");
                                        for (Medico medico : listaMedico) {
                                            Especialidade especialidade = (Especialidade) pacienteDAO.getEspecialidade(medico.getIdEspecialidade());
                                            out.println("<option value='" + medico.getId() + "'>" + medico.getNome() + " - " + especialidade.getDescricao() + "</option>");
                                        }
                                        out.println("</select>");                        %>
                        </div>
                        <div class="col-sm-4">
                            <label for="ControlDate1"  class="form-label">Data</label>
                            <input type="date" class="form-control" id="ControlDate1" name="data">
                        </div>  
                </div>
                <div class="row justify-content-center">
                    <div class="col-8 mt-3">
                        <button type="submit" class="btn btn-light mt-3 text-danger">Alterar consulta</button>
                      </div>
                </div>
           </form>  
        </div>
    </div>

    <script src="bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>