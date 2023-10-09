/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import aplicacao.Consulta;
import aplicacao.Paciente;
import aplicacao.TipoExame;
import aplicacao.Exames;
import aplicacao.TipoPlano;
import aplicacao.Usuario;
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
import javax.servlet.http.HttpSession;
import model.ExameDAO;
import model.MedicoDAO;
import model.PacienteDAO;

/**
 *
 * @author janaina.borges
 */
@WebServlet(name = "MedicoController", urlPatterns = {"/MedicoController"})
public class MedicoController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");        
        String descricao = request.getParameter("descricao");
        String data = request.getParameter("data");
        String realizada = request.getParameter("realizada");
        String idMedico = request.getParameter("idmedico");
        String idPaciente = request.getParameter("idpaciente");
        String idconsulta = request.getParameter("id");
        Paciente paciente = new Paciente();
        MedicoDAO medicoDAO = new MedicoDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "RealizarConsulta":
                request.setAttribute("idconsulta", idconsulta);
                request.setAttribute("descricao", descricao);
                rd = request.getRequestDispatcher("/view/consultaMedico/realizarConsulta.jsp");
                rd.forward(request, response);
                break;
            case "Listar":
                try {
                    
                    HttpSession session = request.getSession();
                    Usuario usuario = (Usuario)session.getAttribute("usuario");
                    ArrayList<aplicacao.Consulta> listaConsultas = medicoDAO.ListaDeConsultas(usuario.getId());
                    request.setAttribute("msgOperacaoRealizada", "");
                    request.setAttribute("listaConsultas", listaConsultas);
                    rd = request.getRequestDispatcher("/view/consultaMedico/listaConsultas.jsp");
                    rd.forward(request, response);

                } catch (IOException | ServletException ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha na query listar consultas");
                }
                break;
            case "Alterar":


                request.setAttribute("idconsulta", idconsulta);
                rd = request.getRequestDispatcher("/view/consultaPaciente/alteracaoConsulta.jsp");
                rd.forward(request, response);  
                break;
            case "cadastrarExame":
                request.setAttribute("descricao", descricao);
                rd = request.getRequestDispatcher("/view/exame/cadastrarExame.jsp");
                rd.forward(request, response);
                break;
                

        }

        }
    
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

          String descricao = request.getParameter("descricao");
        //String cpf_paciente = request.getParameter("cpf");
//        String senha_paciente = request.getParameter("senha");
//        String autorizado_paciente = "S";
//        String descricao_plano_paciente = request.getParameter("descricao");
          String btEnviar = request.getParameter("btEnviar"); 
//        String data_consulta = request.getParameter("data");
          int idExame = 0;
          int idConsulta;
          
          idConsulta = Integer.parseInt(request.getParameter("idconsulta"));
          ArrayList<aplicacao.Exames> listaExames;
          RequestDispatcher rd;
//        TipoPlano plano = new TipoPlano();
          ExameDAO exameDAO = new ExameDAO();
          MedicoDAO medicoDAO = new MedicoDAO();
          
//        HttpSession session = request.getSession();
//        Usuario usuario = (Usuario)session.getAttribute("usuario");
            
            switch (btEnviar) {
                case "IncluirExame":
                idExame = Integer.parseInt(request.getParameter("idexame"));    
                    if (idExame == 0 || idConsulta == 0) {
                        request.setAttribute("acao", "IncluirPaciente");
                        //request.setAttribute("msgError", "É necessário preencher todos os campos");
                        request.setAttribute("exame", exame);
                    } else {
                    try {
                        Exames exame = new Exames(idExame, idConsulta);
                        exameDAO.Inserir(exame); 

                    } catch (Exception ex) {
                        Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  request.setAttribute("descricao", descricao);
                  request.setAttribute("msgOperacaoRealizada", "Cadastro realizado com sucesso");
                  listaExames = exameDAO.ListaExames(idConsulta); 
                  request.setAttribute("listaExames", listaExames);
                  request.setAttribute("idconsulta", idConsulta);
                  rd = request.getRequestDispatcher("/view/consultaMedico/realizarConsulta.jsp");
                rd.forward(request, response);
                  break;  
                }
                case "CadastrarConsulta":
       
                    
                    try {
                        Consulta consulta = medicoDAO.getConsulta(idConsulta);
                        consulta.setDescricao(descricao);
                        consulta.setRealizada("S");
                        medicoDAO.cadastrarConsulta(consulta);
                    } catch (Exception ex) {
                        Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                    HttpSession session = request.getSession();
                    Usuario usuario = (Usuario)session.getAttribute("usuario");
                    ArrayList<Consulta> listaConsultas = medicoDAO.ListaDeConsultas(usuario.getId());
                    request.setAttribute("listaConsultas", listaConsultas);
                    rd = request.getRequestDispatcher("/view/consultaMedico/listaConsultas.jsp");
                    rd.forward(request, response);
                    break;        
                  
                case "ExcluirExame":
                    idExame = Integer.parseInt(request.getParameter("idexame"));
                    try {

                        exameDAO.Excluir(idExame);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        throw new RuntimeException("Falha no cadastro de paciente");
                    }
                    request.setAttribute("descricao", descricao);
                    request.setAttribute("idconsulta", idConsulta);
                    rd = request.getRequestDispatcher("/view/consultaMedico/realizarConsulta.jsp");
                    rd.forward(request, response);
                    break;
                case "ExcluirConsulta":
                    id = Integer.parseInt(request.getParameter("id"));
                    {
                        try {
                            pacienteDAO.Excluir(id);
                        } catch (Exception ex) {
                            Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                    listaConsultas = pacienteDAO.ListaDeConsultas(usuario.getId());
                    request.setAttribute("listaConsultas", listaConsultas);
                    rd = request.getRequestDispatcher("/view/consultaPaciente/listaConsultas.jsp");
                    rd.forward(request, response);  
                    break;
                case "AlterarConsulta":

                    id = Integer.parseInt(request.getParameter("id"));
                    idmedico = Integer.parseInt(request.getParameter("idmedico"));
                    {
                        try {
                            pacienteDAO.Alterar(id, data_consulta, idmedico);  
                        } catch (Exception ex) {
                            Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                    listaConsultas = pacienteDAO.ListaDeConsultas(usuario.getId());
                    request.setAttribute("listaConsultas", listaConsultas);
                    request.setAttribute("btEnviar", clean);
                    rd = request.getRequestDispatcher("/view/consultaPaciente/listaConsultas.jsp");
                    rd.forward(request, response);  
                    break;
                    
            }

            

        } 
                
                

    }

 


