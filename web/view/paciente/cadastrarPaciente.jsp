<%-- 
    Document   : cadastrarPaciente
    Created on : 08/12/2022, 19:50:00
    Author     : janaina.borges
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="aplicacao.TipoPlano"%>
<%@page import="aplicacao.Paciente"%>
<%@page import="model.PacienteDAO"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="view/bootstrap/bootstrap.min.css"  rel="stylesheet"> 
    <title>Registrar</title>
</head>
<body>
                    <div class="col-sm-4 offset-3">
                    <%
                        Paciente paciente = new Paciente();
                        
                    %>
                    
    <div class="container-sm">
        <div class="container position-absolute top-50 start-50 translate-middle p-4 rounded">
            <div class="row justify-content-start">
                <div class="col-12">
                    <div class="h5 text-left text-dark mb-3">Registro de paciente</div>
                </div>
            </div>
            <form action="AdministradorController?btEnviar=IncluirPaciente" method="POST">
                <input type="hidden" name="id" value="<%=paciente.getId()%>" class="form-control">
                <div class="row">
                        <div class="col-sm-6">
                            <label for="Nome" class="form-label">Nome</label>
                            <input type="text" class="form-control col-sm-12" id="Nome" name="nome">
                        </div>
                        <div class="col-sm-6">
                            <label for="cpf" class="form-label">CPF</label>
                            <input type="text" class="form-control col-sm-12" id="cpf" name="cpf">
                        </div>
                </div>
                <div class="row">   
                    <div class="form-group col-sm-6">
                        <label for="tipoplano"  class="form-label mt-2">Tipo do plano de saúde</label>
                        <%
                                    PacienteDAO pacienteDAO = new PacienteDAO();
                                    ArrayList<TipoPlano> listaPlano = pacienteDAO.ListaDePlanos();
                                    out.println("<select class='form-control' name='idplano'>");
                                    for (TipoPlano plano : listaPlano) {
                                        out.println("<option value='" + plano.getId() + "'>" + plano.getDescricao() + "</option>");
                                    }
                                    out.println("</select>");
                        %>
                    </div>
                    <div class="col-sm-6">
                        <label for="senha" class="col-sm-2 col-form-label mt-1">Autorizado</label>
                        <div>
                            <select type="password" class="form-control" id="autorizado" name="autorizado">
                                <option value="">Selecione</option>
                                <option value="S">S</option>
                                <option value="N">N</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6">
                        <label for="senha" class="col-sm-2 col-form-label mt-1">Senha</label>
                        <div>
                            <input type="password" class="form-control" id="senha" name="senha">
                        </div>
                    </div>
                    
                    <div class="col-sm-6">
                        <label for="confirmasenha" class="col-sm-12 col-form-label mt-1">Confirme a senha</label>
                        <div>
                            <input type="password" class="form-control" id="confirmasenha" name="confirmasenha">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-auto">
                        <button type="submit" class="btn btn-light mt-3 text-danger">Registrar</button>
                      </div>
                </div>
            </form>
        </div>
    </div>
    <script src="bootstrap\bootstrap.bundle.min.js" ></script>  
</body>
</html>

