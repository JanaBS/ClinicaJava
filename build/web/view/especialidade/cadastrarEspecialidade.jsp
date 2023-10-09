<%-- 
    Document   : cadastrarEspecialidade
    Created on : 11/12/2022, 08:33:37
    Author     : janaina.borges
--%>

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
                <form action="AdministradorController?btEnviar=CadastrarEspecialidade" method="POST">
                    <div class="row justify-content-start">
                        <div class="col-12">
                            <div class="h5 text-left text-dark mb-3">Cadastrar especialidade</div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <label for="ControlInput1" class="form-label">Descrição</label>
                            <input type="Nome" class="form-control col-sm-12" id="ControlInput1" name="descricao"></input>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-auto">
                            <button type="submit" class="btn btn-light text-danger mt-3">Cadastrar</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
