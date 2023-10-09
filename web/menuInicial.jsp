<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="bootstrap/bootstrap.min.css" rel="stylesheet">
    <title>Página Inicial</title>
</head>
<body>
    <nav class="navbar navbar-danger bg-danger navbar-expand-lg">
        <div class="container-fluid">
            <a class="navbar-brand text-light" href="index.jsp">Clínica Médica</a>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                  <li class="nav-item">
                    <a class="nav-link text-light" href="#">Especialidades</a>
                  </li>
                </ul>
                <ul class="navbar-nav mb-2 mb-lg-0">
                  <li class="nav-item justify-content-end">
                      <a class="btn btn-outline-light me-3" href="AutenticaController?acao=Registrar">Registrar</a>
                  </li>
                  <li class="nav-item justify-content-end me-3">
                      <a class="btn btn-outline-light" href="AutenticaController?acao=Login">Login</a>
                  </li>
                </ul>
            </div>
         </div>
      </nav>
      <script src="bootstrap\bootstrap.bundle.min.js" ></script>    
</body>
</html>
