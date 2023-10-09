<%@page import="aplicacao.TipoPlano"%>
<%@page import="aplicacao.Especialidade"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.PacienteDAO"%>
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
    <jsp:include page="menuInicial.jsp"/>
    <div class="container">

            <div class="container mt-5">
                <div class="row mb-4 justify-content-around">
                    <div class="col-10">
                        <h3>Página Inicial</h3>
                    </div>
                    
                </div>
                
                <div class="row justify-content-around">
                    <div class="col-4">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th scope="col">Nossas especialidades</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    
                                    PacienteDAO pacienteDAO = new PacienteDAO();
                                    //Especialidade especialidade = new Especialidade();
                                    ArrayList<Especialidade> listaEspecialidade = pacienteDAO.ListaDeEspecialidades();
                                        for (Especialidade especialidade : listaEspecialidade) {
                                            out.println("<tr>");
                                            out.println("<td>" + especialidade.getDescricao() + "</td>");
                                            out.println("</tr>");
                                        }
                                %>
                            </tbody>
                        </table>
                     </div>
                            
                    <div class="col-4">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th scope="col">Convenios aceitos</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                                                        //Especialidade especialidade = new Especialidade();
                                    ArrayList<TipoPlano> listaPlanos = pacienteDAO.ListaDePlanos();
                                        for (TipoPlano plano : listaPlanos) {
                                            out.println("<tr>");
                                            out.println("<td>" + plano.getDescricao() + "</td>");
                                            out.println("</tr>");
                                        }
                                %>
                            </tbody>
                        </table>
                     </div>
                </div>
                </div>
            </div>
        </div>
      <script src="bootstrap\bootstrap.bundle.min.js" ></script>    
</body>
</html>