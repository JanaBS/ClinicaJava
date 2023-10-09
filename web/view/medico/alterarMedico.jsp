<%-- 
    Document   : alterarMedico
    Created on : 09/12/2022, 23:39:13
    Author     : janaina.borges
--%>

<%@page import="model.MedicoDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="aplicacao.Especialidade"%>
<%@page import="aplicacao.Medico"%>
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
                  <div class="container-sm">
        <div class="container position-absolute top-50 start-50 translate-middle  p-4 rounded">
            <div class="row justify-content-start">
                <div class="col-12">
                    <div class="h5 text-left text-dark mb-3">Alterar médico</div>
                </div>
            </div>
            <%
                                Medico medico = new Medico();
                                medico = (Medico) request.getAttribute("medico");
            %>
            <form action="AdministradorController?btEnviar=AlterarMedico" method="POST">
                <input type="hidden" name="id" value="<%=medico.getId()%>" class="form-control">
                <div class="row">
                        <div class="col-sm-4">
                            <label for="ControlInput1" class="form-label">Nome</label>
                            <input type="Nome" class="form-control col-sm-12" id="ControlInput1" name="nome" value="<%=medico.getNome()%>">
                        </div>
                        <div class="col-sm-2">
                            <label for="ControlInput2" class="form-label">CRM</label>
                            <div class="">
                                <input type="CRM" class="form-control col-sm-12" id="ControlInput2" name="crm" value="<%=medico.getCrm()%>">
                            </div>
                        </div>
                        <div class="col-sm-2">
                            <label for="ControlInput3" class="form-label">UF CRM</label>
                            <div class="">
                                <input type="UF-CRM" class="form-control col-sm-12" id="ControlInput3" name="estadocrm" value="<%=medico.getEstadoCrm()%>">
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <label for="ControlInput4" class="form-label">CPF</label>
                            <div class="">
                                <input type="CPF" class="form-control col-sm-12" id="ControlInput4" name="cpf" value="<%=medico.getCpf()%>">
                            </div>
                        </div>
                </div>
                <div class="row">   
                    <div class="col-sm-4">
                                <label for="ControlSelect2"  class="form-label mt-2">Especialidade</label>
                                <%
                                            MedicoDAO medicoDAO = new MedicoDAO();
                                            PacienteDAO pacienteDAO = new PacienteDAO();
                                            ArrayList<Especialidade>  listaEespecialidades =  pacienteDAO.ListaDeEspecialidades();
                                            out.println("<select class='form-control' name='idespecialidade'");
                                            for (Especialidade especialidade : listaEespecialidades) {
                                                out.println("<option value='"+especialidade.getId()+"'>"+ especialidade.getDescricao() + "</option>");
                                            }
                                            out.println("</select>");                        %>
                    </div>
                    <div class="form-group col-sm-2">
                        <label for="ControlSelect2"  class="form-label mt-2">Cadastro ativo</label>
                        <select class="form-control" id="ControlSelect2" name="autorizado">
                            <option value="S">Sim</option>
                            <option value="N">Não</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6">
                        <label for="inputPassword1" class="col-sm-2 col-form-label mt-1">Senha</label>
                        <div>
                        <input type="password" class="form-control" id="inputPassword1" name="senha">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <label for="inputPassword2" class="col-sm-12 col-form-label mt-1">Confirme a senha</label>
                        <div>
                        <input type="password" class="form-control" id="inputPassword2">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-auto">
                        <button type="submit" class="btn btn-light text-danger mt-3">Alterar</button>
                      </div>
                </div>
            </form>
        </div>
    </div>
    <script src="bootstrap\bootstrap.bundle.min.js" ></script> 
