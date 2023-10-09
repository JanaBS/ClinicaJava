<%-- 
    Document   : cadastrarAdministrador
    Created on : 10/12/2022, 08:21:40
    Author     : janaina.borges
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="view/bootstrap/bootstrap.min.css"  rel="stylesheet"> 
    <title>Cadastrar administrador</title>
</head>
<body>
    <jsp:include page="../../menuAdministrador.jsp"/>

    <div class="container-sm">
        <div class="container position-absolute top-50 start-50 translate-middle p-4 rounded">
            <form action="AdministradorController?btEnviar=CadastrarAdministrador" method="POST">
                <div class="row justify-content-start">
                    <div class="col-12">
                        <div class="h5 text-left text-dark mb-3">Cadastrar administrador</div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6">
                        <label for="ControlInput1" class="form-label">Nome</label>
                        <input type="Nome" class="form-control col-sm-12" id="ControlInput1" name="nome">
                    </div>
                    <div class="col-sm-6">
                        <label for="ControlInput2" class="form-label">CPF</label>
                        <input type="CPF" class="form-control col-sm-12" id="ControlInput2" name="cpf">
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6">
                        <label for="inputPassword1" class="col-sm-2 col-form-label mt-1">Senha</label>
                        <input type="password" class="form-control" id="inputPassword1" name="senha">
                    </div>
                    <div class="col-sm-6">
                        <label for="inputPassword2" class="col-sm-12 col-form-label mt-1">Confirme a senha</label>
                        <input type="password" class="form-control" id="inputPassword2">
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
    <script src="bootstrap\bootstrap.bundle.min.js" ></script>   
</body>
</html>