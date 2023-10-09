<%-- 
    Document   : login.jsp
    Created on : 15/11/2022, 15:11:12
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
    <title>Página de Login</title>
    </head>
    <body>
        <%
                    String msgError = (String) request.getAttribute("msgError");
                    if ((msgError != null) && (!msgError.isEmpty())) {%>
                        <div class="alert alert-danger" role="alert">
                           <%= msgError %>
                        </div>
        <% }%>
        <form action="AutenticaController?acao=logar" method="POST">
            <div class="container-sm"><div class="container position-absolute top-50 start-50 translate-middle">
                <div class="row">
                  <div class="col">
                  </div>

                  <div class="col-4 p-4 rounded text-center">
                    <div class="h1 text-center text-dark">Clinica médica</div>
                    <label for="cpf" class="form-label mb-3">CPF</label>
                    <div class="col-8 offset-2">
                        <input type="text" name="cpf" class="form-control col-sm-6" placeholder="00000000000">
                    </div>
                    <label for="password" class="col-sm-2 col-form-label">Senha</label>
                    <div class="col-8 offset-2">
                    <input type="password" class="form-control" name="senha" id="password">
                    </div>
                    <div class="col-8 offset-2">
                        <label for="perfil" class="col-sm-2 col-form-label">Perfil</label>
                        <select name="perfil" class="form-control form-control-sm">
                            <option>paciente</option>
                            <option>medico</option>
                            <option>administrador</option>
                         </select>
                    </div>
                    
                    <div class="col-auto">
                      <button type="submit" class="btn btn-light mt-3 text-danger">Acessar</button>
                    </div>
                  </div>

                  <div class="col">
                  </div>
                </div>
            </div>
       </form>
            <script src="../bootstrap\bootstrap.bundle.min.js" ></script> 
        </body>
</html>
