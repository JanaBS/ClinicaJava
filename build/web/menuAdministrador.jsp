<%-- 
    Document   : menuAdministrador.jsp
    Created on : 17/11/2022, 17:57:04
    Author     : janaina.borges
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="view/bootstrap/bootstrap.min.css" rel="stylesheet">
    <title>Página Inicial</title>
</head>
<body>
    <nav class="navbar navbar-danger bg-danger navbar-expand-lg">
        <div class="container-fluid">
            <a class="navbar-brand text-light" href="index.html">Clínica Médica</a>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                  <li class="nav-item">
                    <a class="nav-link text-light" href="AdministradorController?acao=ListarPaciente">Pacientes</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link text-light" href="AdministradorController?acao=ListarMedico">Médicos</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link text-light" href="AdministradorController?acao=ListarAdministrador">Administradores</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link text-light" href="AdministradorController?acao=ListarEspecialidade">Especialidades</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link text-light" href="AdministradorController?acao=ListarPlano">Planos de Saúde</a>
                  </li>

                </ul>
                <ul class="navbar-nav mb-2 mb-lg-0">
                  <li class="nav-item justify-content-end me-3">
                    <a class="btn btn-outline-light" href="AutenticaController?acao=Logout" role="button">Logout</a>
                  </li>
                </ul>
            </div>
         </div>
      </nav>
      <script src="bootstrap\bootstrap.bundle.min.js" ></script>    
</body>
</html>