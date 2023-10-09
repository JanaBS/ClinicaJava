package controller;

import aplicacao.Usuario;
import aplicacao.Consulta;
import aplicacao.Paciente;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AdministradorDAO;
import model.UsuarioDAO;
import model.PacienteDAO;
import model.MedicoDAO;


@WebServlet(name = "AutenticaController", urlPatterns = {"/AutenticaController"})
public class AutenticaController extends HttpServlet {

    private Object medicoDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       String acao = request.getParameter("acao");
       RequestDispatcher rd ;
        switch (acao) {
            case "Login":  // chama form de login
                rd = request.getRequestDispatcher("/view/autenticacao/login.jsp");
                rd.forward(request, response);
                break;
            case "Registrar": 
                rd = request.getRequestDispatcher("/view/paciente/registrar.jsp");
                rd.forward(request, response);
                
                break;
            case "Logout":
                HttpSession session = request.getSession();
                session.invalidate();
                rd = request.getRequestDispatcher("/view/autenticacao/login.jsp");
                rd.forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        // pegando os parâmetros do request
        String cpf_user = request.getParameter("cpf");
        String senha_user = request.getParameter("senha");
        String perfil_user = request.getParameter("perfil");
        PacienteDAO pacienteDAO = new PacienteDAO();
        MedicoDAO medicoDAO = new MedicoDAO();
        AdministradorDAO administradorDAO = new AdministradorDAO();
        if (cpf_user.isEmpty() || senha_user.isEmpty()) {
            // dados não foram preenchidos retorna ao formulário
            request.setAttribute("msgError", "Usuário e/ou senha incorreto");
            rd = request.getRequestDispatcher("/view/autenticacao/login.jsp");
            rd.forward(request, response);


        } else {
            Usuario usuarioObtido;
                Usuario usuario = new Usuario(cpf_user, senha_user);
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                try {
                   usuarioObtido = usuarioDAO.Logar(usuario, perfil_user);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha no Login");
                }
               
            if (usuarioObtido.getId() != 0) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuarioObtido);
                switch (perfil_user) {
                    case "paciente":  // chama form de login
                        
                        ArrayList<Consulta> listaConsultas = pacienteDAO.ListaDeConsultas(usuarioObtido.getId());
                        request.setAttribute("msgOperacaoRealizada", "");
                        request.setAttribute("listaConsultas", listaConsultas);
                        rd = request.getRequestDispatcher("/view/consultaPaciente/listaConsultas.jsp");
                        rd.forward(request, response);
                    case "medico": 
                        listaConsultas = medicoDAO.ListaDeConsultas(usuarioObtido.getId());
                        request.setAttribute("msgOperacaoRealizada", "");
                        request.setAttribute("listaConsultas", listaConsultas);
                        rd = request.getRequestDispatcher("/view/consultaMedico/listaConsultas.jsp");
                        rd.forward(request, response);
                        break;
                    case "administrador":
                        ArrayList<Paciente> listaPacientes = administradorDAO.ListaDePacientes();
                        request.setAttribute("msgOperacaoRealizada", "");
                        request.setAttribute("listaPacientes", listaPacientes);
                        rd = request.getRequestDispatcher("/view/paciente/listaPacientes.jsp");
                        rd.forward(request, response);
                        break;
                        }
            } else {
                request.setAttribute("msgError", "Usuário e/ou senha incorreto ou usuário não autorizado");
                rd = request.getRequestDispatcher("/view/autenticacao/login.jsp");
                rd.forward(request, response);

            }
        }
    }

}
