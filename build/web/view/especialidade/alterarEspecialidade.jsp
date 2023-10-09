<%-- 
    Document   : alterarEspecialidade
    Created on : 11/12/2022, 09:00:23
    Author     : janaina.borges
--%>

<%@page import="aplicacao.Especialidade"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastrar Especialidade</title>
    </head>
    <body>
        <jsp:include page="../../menuAdministrador.jsp"/>
            <div class="container-sm">
            <div class="container position-absolute top-50 start-50 translate-middle p-4 rounded">
                <%
                                Especialidade especialidade = new Especialidade();
                                especialidade = (Especialidade) request.getAttribute("especialidade");
                %>
                <form action="AdministradorController?btEnviar=AlterarEspecialidade" method="POST">
                    <div class="row justify-content-start">
                        <div class="col-12">
                            <div class="h5 text-left text-dark mb-3">Alterar especialidade</div>
                        </div>
                    </div>
                    <input type="hidden" name="id" value="<%=especialidade.getId()%>" class="form-control">
                    <div class="row">
                        <div class="col-sm-12">
                            <label for="ControlInput1" class="form-label">Descrição</label>
                            <input type="Nome" class="form-control col-sm-12" id="ControlInput1" value="<%=especialidade.getDescricao()%>" name="descricao"></input>
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
    </body>
</html>

