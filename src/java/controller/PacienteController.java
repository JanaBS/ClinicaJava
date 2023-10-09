/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.PacienteDAO;
import aplicacao.Paciente;
import aplicacao.Usuario;
import aplicacao.TipoPlano;
import aplicacao.Consulta;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author janaina.borges
 */
@WebServlet(name = "PacienteController", urlPatterns = {"/PacienteController"})
public class PacienteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");        
        String descricao = request.getParameter("descricao");
        String data = request.getParameter("data");
        String realizada = request.getParameter("realizada");
        String idMedico = request.getParameter("idmedico");
        String idPaciente = request.getParameter("idpaciente");
        Paciente paciente = new Paciente();
        PacienteDAO pacienteDAO = new PacienteDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "Cadastrar":
                rd = request.getRequestDispatcher("/view/consultaPaciente/marcacaoConsulta.jsp");
                rd.forward(request, response);
            case "Listar":
                try {
                    
                    HttpSession session = request.getSession();
                    Usuario usuario = (Usuario)session.getAttribute("usuario");
                    ArrayList<aplicacao.Consulta> listaConsultas = pacienteDAO.ListaDeConsultas(usuario.getId());
                    request.setAttribute("msgOperacaoRealizada", "");
                    request.setAttribute("listaConsultas", listaConsultas);
                    rd = request.getRequestDispatcher("/view/consultaPaciente/listaConsultas.jsp");
                    rd.forward(request, response);

                } catch (IOException | ServletException ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha na query listar consultas");
                }
                break;
            case "Alterar":

                String idconsulta = request.getParameter("id");
                request.setAttribute("idconsulta", idconsulta);
                rd = request.getRequestDispatcher("/view/consultaPaciente/alteracaoConsulta.jsp");
                rd.forward(request, response);      

        }
        request.setAttribute("paciente", paciente);
        request.setAttribute("msgError", "");
        request.setAttribute("acao", acao);

        rd = request.getRequestDispatcher("/view/paciente/listaPacientes.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //String idmedico = request.getParameter("idmedico");
        String nome_paciente = request.getParameter("nome");
        String cpf_paciente = request.getParameter("cpf");
        String senha_paciente = request.getParameter("senha");
        String autorizado_paciente = "S";
        String id_plano_paciente = request.getParameter("descricao");
        String btEnviar = request.getParameter("btEnviar"); 
        String data_consulta = request.getParameter("data");
        int id;
        int idmedico = 0;
        int idplano = 0;
        RequestDispatcher rd;
        TipoPlano plano = new TipoPlano();
        PacienteDAO pacienteDAO = new PacienteDAO(); 
        //Paciente paciente = new Paciente();
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario)session.getAttribute("usuario");
        ArrayList<aplicacao.Consulta> listaConsultas;
       
        
        String clean = "";
            
            switch (btEnviar) {
                case "IncluirPaciente":
                    if (nome_paciente.isEmpty() || cpf_paciente.isEmpty() || senha_paciente.isEmpty()) {
                        request.setAttribute("acao", "IncluirPaciente");
                        //request.setAttribute("msgError", "É necessário preencher todos os campos");
                        request.setAttribute("paciente", paciente);
                    } else {
                    try {
                        idplano = Integer.parseInt(request.getParameter("idplano"));
                        Paciente paciente = new Paciente(nome_paciente, cpf_paciente, senha_paciente, autorizado_paciente, idplano);
                        pacienteDAO.Inserir(paciente);
                        

                    } catch (Exception ex) {
                        Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  request.setAttribute("msgOperacaoRealizada", "Cadastro realizado com sucesso");
                  rd = request.getRequestDispatcher("index.jsp");
                  rd.forward(request, response);
                  break;  
                }
                case "CadastrarConsulta":
                    idmedico = Integer.parseInt(request.getParameter("idmedico"));
                    
                    if (data_consulta.isEmpty() || idmedico == 0) {
                        request.setAttribute("msgError", "É necessário preencher todos os campos");
                    } else{
                        Consulta consulta = new Consulta(data_consulta, idmedico, usuario.getId());
                        
                        try {
                            int contagem = pacienteDAO.validaConsulta(idmedico, data_consulta);
                            if (contagem < 2) {
                                pacienteDAO.cadastrarConsulta(consulta);
                                listaConsultas = pacienteDAO.ListaDeConsultas(usuario.getId());
                                request.setAttribute("listaConsultas", listaConsultas);
                                rd = request.getRequestDispatcher("/view/consultaPaciente/listaConsultas.jsp");
                                rd.forward(request, response);
                        break; 
                                
                            }
                            else{
                                request.setAttribute("msgError", "Médico não disponível nesta data");
                                rd = request.getRequestDispatcher("/view/consultaPaciente/marcacaoConsulta.jsp");
                                rd.forward(request, response);
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(PacienteController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        //request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        
                           
                }    
                case "ExcluirPaciente":
                    try {
                        //PacienteDAO pacienteDAO = new PacienteDAO();
                        //paciente = pacienteDAO.getPaciente(id);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        throw new RuntimeException("Falha no cadastro de paciente");
                    }
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
