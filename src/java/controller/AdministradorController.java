/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import aplicacao.Administrador;
import aplicacao.Especialidade;
import aplicacao.Medico;
import aplicacao.Paciente;
import aplicacao.TipoPlano;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AdministradorDAO;
import model.MedicoDAO;
import model.PacienteDAO;

/**
 *
 * @author janaina.borges
 */
@WebServlet(name = "AdministradorController", urlPatterns = {"/AdministradorController"})
public class AdministradorController extends HttpServlet {

  @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        AdministradorDAO administradorDAO = new AdministradorDAO();
        Paciente paciente = new Paciente();
        Administrador administrador = new Administrador();
        Especialidade especialidade = new Especialidade();
        TipoPlano tipoPlano = new TipoPlano();
        PacienteDAO pacienteDAO = new PacienteDAO();
        MedicoDAO medicoDAO = new MedicoDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "ListarPaciente":
                try {
                    ArrayList<Paciente> listaPacientes = pacienteDAO.ListaDePacientes();
                    request.setAttribute("msgOperacaoRealizada", "");
                    request.setAttribute("listaPacientes", listaPacientes);
                    rd = request.getRequestDispatcher("/view/paciente/listaPacientes.jsp");
                    rd.forward(request, response);

                } catch (IOException | ServletException ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha na query listar pacientes");
                }
                break;
            case "ListarMedico":
                try {
                    
                    ArrayList<Medico> listaMedicos = administradorDAO.ListaDeMedicos();
                    request.setAttribute("msgOperacaoRealizada", "");
                    request.setAttribute("listaMedicos", listaMedicos);
                    rd = request.getRequestDispatcher("/view/medico/listaMedicos.jsp");
                    rd.forward(request, response);

                } catch (IOException | ServletException ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha na query listar pacientes");
                }
                break;
            
            case "ListarAdministrador":
                try {
                    ArrayList<Administrador> listaAdministradores = administradorDAO.ListaDeAdministradores();
                    request.setAttribute("msgOperacaoRealizada", "");
                    request.setAttribute("listaAdministradores", listaAdministradores);
                    rd = request.getRequestDispatcher("/view/administrador/listaAdministrador.jsp");
                    rd.forward(request, response);

                } catch (IOException | ServletException ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha na query listar administradores");
                }
                break;
            case "ListarEspecialidade":
                try {
                    ArrayList<Especialidade> listaEspecialidades = pacienteDAO.ListaDeEspecialidades();
                    request.setAttribute("msgOperacaoRealizada", "");
                    request.setAttribute("listaEspecialidades", listaEspecialidades);
                    rd = request.getRequestDispatcher("/view/especialidade/listaEspecialidade.jsp");
                    rd.forward(request, response);

                } catch (IOException | ServletException ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha na query listar administradores");
                }
                break;
            case "ListarPlano":
                try {
                    ArrayList<TipoPlano> listaPlanos = pacienteDAO.ListaDePlanos();
                    request.setAttribute("msgOperacaoRealizada", "");
                    request.setAttribute("listaPlanos", listaPlanos);
                    rd = request.getRequestDispatcher("/view/plano/listaPlano.jsp");
                    rd.forward(request, response);

                } catch (IOException | ServletException ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha na query listar administradores");
                }
                break;
            case "IncluirPaciente":
            try {
                rd = request.getRequestDispatcher("/view/paciente/cadastrarPaciente.jsp");
                rd.forward(request, response);

            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query listar administradores");
            }
            break;
            case "AlterarPaciente":
                try {
                    
                    int id = Integer.parseInt(request.getParameter("id"));
                    paciente = administradorDAO.getPaciente(id);
                    request.setAttribute("msgOperacaoRealizada", "");
                    request.setAttribute("paciente", paciente);
                    rd = request.getRequestDispatcher("/view/paciente/alterarPaciente.jsp");
                    rd.forward(request, response);

                } catch (IOException | ServletException ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha na query listar pacientes");
                } catch (Exception ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;
            case "IncluirMedico":
            try {
                rd = request.getRequestDispatcher("/view/medico/cadastrarMedico.jsp");
                rd.forward(request, response);

            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query listar administradores");
            }
            break;
            case "AlterarMedico":
                try {
                    
                    int id = Integer.parseInt(request.getParameter("id"));
                    Medico medico = administradorDAO.getMedico(id);
                    request.setAttribute("msgOperacaoRealizada", "");
                    request.setAttribute("medico", medico);
                    rd = request.getRequestDispatcher("/view/medico/alterarMedico.jsp");
                    rd.forward(request, response);

                } catch (IOException | ServletException ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha na query listar pacientes");
                } catch (Exception ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;
            case "ExcluirMedico":
            {
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    administradorDAO.excluirMedico(id);
                } catch (Exception ex) {
                    Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
            ArrayList<Medico> listaMedicos = administradorDAO.ListaDeMedicos();
            request.setAttribute("msgOperacaoRealizada", "");
            request.setAttribute("listaMedicos", listaMedicos);
            rd = request.getRequestDispatcher("/view/medico/listaMedicos.jsp");
            rd.forward(request, response);
            break;

            }
            case "IncluirAdministrador":
            try {
                rd = request.getRequestDispatcher("/view/administrador/cadastrarAdministrador.jsp");
                rd.forward(request, response);

            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query listar administradores");
            }
            break;
        
         case "AlterarAdministrador":
            try {

                int id = Integer.parseInt(request.getParameter("id"));
                administrador = administradorDAO.getAdministrador(id);
                request.setAttribute("msgOperacaoRealizada", "");
                request.setAttribute("administrador", administrador);
                rd = request.getRequestDispatcher("/view/administrador/alterarAdministrador.jsp");
                rd.forward(request, response);

            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query listar pacientes");
            } catch (Exception ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;
  
        case "ExcluirAdministrador":
            {
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    administradorDAO.excluirAdministrador(id);
                } catch (Exception ex) {
                    Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
            ArrayList<Administrador> listaAdministradores = administradorDAO.ListaDeAdministradores();
            request.setAttribute("msgOperacaoRealizada", "");
            request.setAttribute("listaAdministradores", listaAdministradores);
            rd = request.getRequestDispatcher("/view/administrador/listaAdministrador.jsp");
            rd.forward(request, response);
            break;

            }
        case "IncluirEspecialidade":
            try {
                rd = request.getRequestDispatcher("/view/especialidade/cadastrarEspecialidade.jsp");
                rd.forward(request, response);

            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query listar administradores");
            }
            break;
            
        case "AlterarEspecialidade":
            try {

                int id = Integer.parseInt(request.getParameter("id"));
                especialidade = administradorDAO.getEspecialidade(id);
                request.setAttribute("msgOperacaoRealizada", "");
                request.setAttribute("especialidade", especialidade);
                rd = request.getRequestDispatcher("/view/especialidade/alterarEspecialidade.jsp");
                rd.forward(request, response);

            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query listar pacientes");
            } catch (Exception ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;
            
        case "ExcluirEspecialidade":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    administradorDAO.excluirEspecialidade(id);
                } catch (Exception ex) {
                    Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
            ArrayList<Especialidade> listaEspecialidades = pacienteDAO.ListaDeEspecialidades();
            request.setAttribute("msgOperacaoRealizada", "");
            request.setAttribute("listaEspecialidades", listaEspecialidades);
            rd = request.getRequestDispatcher("/view/especialidade/listaEspecialidade.jsp");
            rd.forward(request, response);
            break;
        
        case "IncluirPlano":
            try {
                rd = request.getRequestDispatcher("/view/plano/cadastrarPlano.jsp");
                rd.forward(request, response);

            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query listar administradores");
            }
            break;
            
        case "AlterarPlano":
            try {

                int id = Integer.parseInt(request.getParameter("id"));
                tipoPlano = administradorDAO.getPlano(id);
                request.setAttribute("msgOperacaoRealizada", "");
                request.setAttribute("tipoPlano", tipoPlano);
                rd = request.getRequestDispatcher("/view/plano/alterarPlano.jsp");
                rd.forward(request, response);

            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query listar pacientes");
            } catch (Exception ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;
            
        case "ExcluirPlano":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    administradorDAO.excluirPlano(id);
                } catch (Exception ex) {
                    Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
            ArrayList<TipoPlano> listaPlanos = pacienteDAO.ListaDePlanos();
            request.setAttribute("msgOperacaoRealizada", "");
            request.setAttribute("listaPlanos", listaPlanos);
            rd = request.getRequestDispatcher("/view/plano/listaPlano.jsp");
            rd.forward(request, response);
            break;
            
        case "ListarConsultas":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    ArrayList<aplicacao.Consulta> listaConsultas = medicoDAO.ListaDeConsultas(id);
                    request.setAttribute("msgOperacaoRealizada", "");
                    request.setAttribute("listaConsultas", listaConsultas);
                    rd = request.getRequestDispatcher("/view/administrador/listaConsultas.jsp");
                    rd.forward(request, response);

                } catch (IOException | ServletException ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha na query listar consultas");
                }
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = 0;
        
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        
        String estadocrm = request.getParameter("estadocrm");
        String senha = request.getParameter("senha");
        String autorizado = request.getParameter("autorizado");
        String descricao = request.getParameter("descricao");
        String btEnviar = request.getParameter("btEnviar");
        PacienteDAO pacienteDAO = new PacienteDAO();
        AdministradorDAO administradorDAO = new AdministradorDAO();
        
        RequestDispatcher rd;
        switch (btEnviar) {
            case "IncluirPaciente":

                if (nome.isEmpty() || cpf.isEmpty() || senha.isEmpty() || autorizado.isEmpty()) {
                    request.setAttribute("acao", "IncluirPaciente");
                    //request.setAttribute("msgError", "É necessário preencher todos os campos");
                    id = Integer.parseInt(request.getParameter("id"));
                    int idplano = Integer.parseInt(request.getParameter("idplano"));
                    Paciente paciente = new Paciente(id, nome, cpf, senha, autorizado, idplano);
                    request.setAttribute("paciente", paciente);
                } else {
                try {

                    id = Integer.parseInt(request.getParameter("id"));
                    int idplano = Integer.parseInt(request.getParameter("idplano"));
                    Paciente paciente = new Paciente(id, nome, cpf, senha, autorizado, idplano);
                    pacienteDAO.Inserir(paciente);


                } catch (Exception ex) {
                    Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
            ArrayList<Paciente> listaPacientes = pacienteDAO.ListaDePacientes();
            request.setAttribute("msgOperacaoRealizada", "");
            request.setAttribute("listaPacientes", listaPacientes);
            rd = request.getRequestDispatcher("/view/paciente/listaPacientes.jsp");
            rd.forward(request, response);
              break;  
            }
            case "AlterarPaciente":   
                try {
                    
                    id = Integer.parseInt(request.getParameter("id"));
                    int idplano = Integer.parseInt(request.getParameter("idplano"));
                    Paciente paciente = new Paciente(id, nome, cpf, senha, autorizado, idplano);
                    administradorDAO.alterarPaciente(paciente);  
                } catch (Exception ex) {
                    Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
                }            
                request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                ArrayList<Paciente> listaPacientes = pacienteDAO.ListaDePacientes();
                request.setAttribute("msgOperacaoRealizada", "");
                request.setAttribute("listaPacientes", listaPacientes);
                rd = request.getRequestDispatcher("/view/paciente/listaPacientes.jsp");
                rd.forward(request, response);
            break;
            case "ExcluirPaciente":
                try {
                    id = Integer.parseInt(request.getParameter("id"));
                    administradorDAO.excluirPaciente(id);
                } catch (Exception ex) {
                    Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                listaPacientes = pacienteDAO.ListaDePacientes();
                request.setAttribute("msgOperacaoRealizada", "");
                request.setAttribute("listaPacientes", listaPacientes);
                rd = request.getRequestDispatcher("/view/paciente/listaPacientes.jsp");
                rd.forward(request, response);
                break;

            case "CadastrarMedico":
                if (nome.isEmpty() || cpf.isEmpty() || senha.isEmpty() || autorizado.isEmpty()) {
                    request.setAttribute("acao", "CadastrarMedico");
                    id = Integer.parseInt(request.getParameter("id"));
                    int crm = Integer.parseInt(request.getParameter("crm"));
                    int idespecialidade = Integer.parseInt(request.getParameter("idespecialidade"));
                    Medico medico = new Medico(id, nome, crm, estadocrm, cpf, senha, autorizado, idespecialidade);
                    request.setAttribute("medico", medico);
                } else {
                try {
                    int crm = Integer.parseInt(request.getParameter("crm"));
                    int idespecialidade = Integer.parseInt(request.getParameter("idespecialidade"));
                    Medico medico = new Medico(id, nome, crm, estadocrm, cpf, senha, autorizado, idespecialidade);
                    administradorDAO.cadastrarMedico(medico);


                } catch (Exception ex) {
                    Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ArrayList<Medico> listaMedicos = administradorDAO.ListaDeMedicos();
                request.setAttribute("msgOperacaoRealizada", "");
                request.setAttribute("listaMedicos", listaMedicos);
                rd = request.getRequestDispatcher("/view/medico/listaMedicos.jsp");
                rd.forward(request, response);
                break;  
                }
            case "AlterarMedico":
            
                try {
                    int crm = Integer.parseInt(request.getParameter("crm"));
                    id = Integer.parseInt(request.getParameter("id"));
                    int idespecialidade = Integer.parseInt(request.getParameter("idespecialidade"));
                    Medico medico = new Medico(id, nome, crm, estadocrm, cpf, senha, autorizado, idespecialidade);
                    administradorDAO.alterarMedico(medico);  
                } catch (Exception ex) {
                    Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
                ArrayList<Medico> listaMedicos = administradorDAO.ListaDeMedicos();
                request.setAttribute("msgOperacaoRealizada", "");
                request.setAttribute("listaMedicos", listaMedicos);
                rd = request.getRequestDispatcher("/view/medico/listaMedicos.jsp");
                rd.forward(request, response);
                break;            
            case "CadastrarAdministrador":
                if (nome.isEmpty() || cpf.isEmpty() || senha.isEmpty()) {
                    request.setAttribute("acao", "CadastrarAdministrador");
                    request.setAttribute("msgError", "É necessário preencher todos os campos");
                    id = Integer.parseInt(request.getParameter("id"));
                    Administrador administrador = new Administrador(id, nome, cpf, senha);
                    request.setAttribute("administrador", administrador);
                } else {
                try {
                    Administrador administrador = new Administrador(nome, cpf, senha);
                    administradorDAO.cadastrarAdministrador(administrador);


                } catch (Exception ex) {
                    Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ArrayList<Administrador> listaAdministradores = administradorDAO.ListaDeAdministradores();
                request.setAttribute("msgOperacaoRealizada", "");
                request.setAttribute("listaAdministradores", listaAdministradores);
                rd = request.getRequestDispatcher("/view/administrador/listaAdministrador.jsp");
                rd.forward(request, response);  
                break;  
                }
            case "AlterarAdministrador":
                if (nome.isEmpty() || cpf.isEmpty() || senha.isEmpty()) {
                    request.setAttribute("acao", "AlterarAdministrador");
                    request.setAttribute("msgError", "É necessário preencher todos os campos");
                    id = Integer.parseInt(request.getParameter("id"));
                    Administrador administrador = new Administrador(id, nome, cpf, senha);
                    request.setAttribute("administrador", administrador);
                } else {
                try {
                    id = Integer.parseInt(request.getParameter("id"));
                    Administrador administrador = new Administrador(id, nome, cpf, senha);
                    administradorDAO.alterarAdministrador(administrador);

                } catch (Exception ex) {
                    Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ArrayList<Administrador> listaAdministradores = administradorDAO.ListaDeAdministradores();
                request.setAttribute("msgOperacaoRealizada", "");
                request.setAttribute("listaAdministradores", listaAdministradores);
                rd = request.getRequestDispatcher("/view/administrador/listaAdministrador.jsp");
                rd.forward(request, response);
                break;  
                }
            case "CadastrarEspecialidade":
                if (descricao.isEmpty()) {
                    request.setAttribute("acao", "CadastrarEspecialidade");
                    request.setAttribute("msgError", "É necessário preencher todos os campos");
                    Especialidade especialidade = new Especialidade(descricao);
                    request.setAttribute("especialidade", especialidade);
                } else {
                try {
                    Especialidade especialidade = new Especialidade(descricao);
                    administradorDAO.cadastrarEspecialidade(especialidade);

                } catch (Exception ex) {
                    Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ArrayList<Especialidade> listaEspecialidades = pacienteDAO.ListaDeEspecialidades();
                request.setAttribute("msgOperacaoRealizada", "");
                request.setAttribute("listaEspecialidades", listaEspecialidades);
                rd = request.getRequestDispatcher("/view/especialidade/listaEspecialidade.jsp");
                rd.forward(request, response);
                break;  
                }
            case "AlterarEspecialidade":
                if (descricao.isEmpty()) {
                    request.setAttribute("acao", "CadastrarEspecialidade");
                    request.setAttribute("msgError", "É necessário preencher todos os campos");
                    id = Integer.parseInt(request.getParameter("id"));
                    Especialidade especialidade = new Especialidade(id, descricao);
                    request.setAttribute("especialidade", especialidade);
                } else {
                try {
                    id = Integer.parseInt(request.getParameter("id"));
                    Especialidade especialidade = new Especialidade(id, descricao);
                    administradorDAO.alterarEspecialidade(especialidade);

                } catch (Exception ex) {
                    Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ArrayList<Especialidade> listaEspecialidades = pacienteDAO.ListaDeEspecialidades();
                request.setAttribute("msgOperacaoRealizada", "");
                request.setAttribute("listaEspecialidades", listaEspecialidades);
                rd = request.getRequestDispatcher("/view/especialidade/listaEspecialidade.jsp");
                rd.forward(request, response);
                break;
                }
            case "CadastrarPlano":
                if (descricao.isEmpty()) {
                    request.setAttribute("acao", "CadastrarEspecialidade");
                    request.setAttribute("msgError", "É necessário preencher todos os campos");
                    TipoPlano tipoPlano = new TipoPlano(descricao);
                    request.setAttribute("tipoPlano", tipoPlano);
                } else {
                try {
                    TipoPlano tipoPlano = new TipoPlano(descricao);
                    administradorDAO.cadastrarPlano(tipoPlano);

                } catch (Exception ex) {
                    Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ArrayList<TipoPlano> listaPlanos = pacienteDAO.ListaDePlanos();
                request.setAttribute("msgOperacaoRealizada", "");
                request.setAttribute("listaPlanos", listaPlanos);
                rd = request.getRequestDispatcher("/view/plano/listaPlano.jsp");
                rd.forward(request, response);
                break;  
                }
            case "AlterarPlano":
                if (descricao.isEmpty()) {
                    request.setAttribute("acao", "CadastrarPlano");
                    request.setAttribute("msgError", "É necessário preencher todos os campos");
                    id = Integer.parseInt(request.getParameter("id"));
                    TipoPlano tipoPlano = new TipoPlano(id, descricao);
                    request.setAttribute("tipoPlano", tipoPlano);
                } else {
                try {
                    id = Integer.parseInt(request.getParameter("id"));
                    TipoPlano tipoPlano = new TipoPlano(id, descricao);
                    administradorDAO.alterarPlano(tipoPlano);

                } catch (Exception ex) {
                    Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ArrayList<TipoPlano> listaPlanos = pacienteDAO.ListaDePlanos();
                request.setAttribute("msgOperacaoRealizada", "");
                request.setAttribute("listaPlanos", listaPlanos);
                rd = request.getRequestDispatcher("/view/plano/listaPlano.jsp");
                rd.forward(request, response);
                break;  
                }
            }  
        }   
    }
