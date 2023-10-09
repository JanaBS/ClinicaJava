/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import aplicacao.Medico;
import aplicacao.Administrador;
import aplicacao.Especialidade;
import aplicacao.Paciente;
import aplicacao.TipoPlano;


/**
 *
 * @author janaina.borges
 */
public class AdministradorDAO {
    
    public ArrayList<Medico> ListaDeMedicos() {
        ArrayList<Medico> medicos = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM medico order by nome";
            PreparedStatement sql;
            sql = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Medico medico = new Medico(
                            resultado.getInt("id"),
                            resultado.getString("nome"),
                            resultado.getInt("crm"),
                            resultado.getString("estadocrm"),
                            resultado.getString("cpf"),
                            resultado.getString("senha"),
                            resultado.getString("autorizado"),
                            resultado.getInt("idespecialidade"));
                    medicos.add(medico);
                }
            } 
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao consultar pacientes");
        } finally {
            conexao.closeConexao();
        }
        return medicos;
    }    
    
    public void cadastrarMedico(Medico medico) throws Exception {
        Conexao conexao = new Conexao();
        try {
            
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO medico (nome, crm, estadocrm, cpf, senha, autorizado, idespecialidade)"
                    + " VALUES (?,?,?,?,?,?,?)");
            sql.setString(1, medico.getNome());
            sql.setInt(2, medico.getCrm());
            sql.setString(3, medico.getEstadoCrm());
            sql.setString(4, medico.getCpf());
            sql.setString(5, medico.getSenha());
            sql.setString(6, medico.getAutorizado());
            sql.setInt(7, medico.getIdEspecialidade());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }
    
    public void alterarMedico(Medico medico) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE medico SET nome = ?, crm = ?, estadocrm = ?, cpf = ?, senha = ?, autorizado = ?, idespecialidade = ? WHERE ID = ? ");
            sql.setString(1, medico.getNome());
            sql.setInt(2, medico.getCrm());
            sql.setString(3, medico.getEstadoCrm());
            sql.setString(4, medico.getCpf());
            sql.setString(5, medico.getSenha());
            sql.setString(6, medico.getAutorizado());
            sql.setInt(7, medico.getIdEspecialidade());
            sql.setInt(8, medico.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    public void excluirMedico(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM medico WHERE ID = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    public Medico getMedico(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Medico medico = new Medico();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM medico WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    medico.setId(Integer.parseInt(resultado.getString("ID")));
                    medico.setNome(resultado.getString("NOME"));
                    medico.setCrm(Integer.parseInt(resultado.getString("CRM")));
                    medico.setEstadoCrm(resultado.getString("ESTADOCRM"));
                    medico.setCpf(resultado.getString("CPF"));
                    medico.setSenha(resultado.getString("SENHA"));
                    medico.setAutorizado(resultado.getString("AUTORIZADO"));
                    medico.setIdEspecialidade(Integer.parseInt(resultado.getString("IDESPECIALIDADE")));
                    
                }
            }
            return medico;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    public ArrayList<Administrador> ListaDeAdministradores() {
        ArrayList<Administrador> administradores = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM administrador order by nome";
            PreparedStatement sql;
            sql = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Administrador administrador = new Administrador(
                            resultado.getInt("id"),
                            resultado.getString("nome"),
                            resultado.getString("cpf"),
                            resultado.getString("senha"));
                    administradores.add(administrador);
                }
            } 
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao consultar pacientes");
        } finally {
            conexao.closeConexao();
        }
        return administradores;
    }
    
    public Administrador getAdministrador(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Administrador administrador = new Administrador();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM administrador WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    administrador.setId(Integer.parseInt(resultado.getString("ID")));
                    administrador.setNome(resultado.getString("NOME"));
                    administrador.setCpf(resultado.getString("CPF"));
                    administrador.setSenha(resultado.getString("SENHA"));
                    
                }
            }
            return administrador;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    public void cadastrarAdministrador(Administrador administrador) throws Exception {
        Conexao conexao = new Conexao();
        try {
            
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO administrador (nome, cpf, senha)"
                    + " VALUES (?,?,?)");
            sql.setString(1, administrador.getNome());
            sql.setString(2, administrador.getCpf());
            sql.setString(3, administrador.getSenha());
            sql.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }
    
    public void alterarAdministrador(Administrador administrador) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE administrador SET nome = ?, cpf = ?, senha = ? WHERE ID = ? ");
            sql.setString(1, administrador.getNome());
            sql.setString(2, administrador.getCpf());
            sql.setString(3, administrador.getSenha());
            sql.setInt(4, administrador.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    public void excluirAdministrador(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM administrador WHERE ID = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
        public Paciente getPaciente(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Paciente paciente = new Paciente();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM paciente WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    paciente.setId(Integer.parseInt(resultado.getString("ID")));
                    paciente.setNome(resultado.getString("NOME"));
                    paciente.setCpf(resultado.getString("CPF"));
                    paciente.setSenha(resultado.getString("SENHA"));
                    paciente.setAutorizado(resultado.getString("AUTORIZADO"));
                    paciente.setIdTipoPlano(Integer.parseInt(resultado.getString("IDTIPOPLANO")));
                    
                }
            }
            return paciente;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Paciente> ListaDePacientes() {
        ArrayList<Paciente> pacientes = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM paciente order by nome";
            PreparedStatement sql;
            sql = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Paciente paciente = new Paciente(
                            resultado.getInt("id"),
                            resultado.getString("nome"),
                            resultado.getString("cpf"),
                            resultado.getString("senha"),
                            resultado.getString("autorizado"),
                            resultado.getInt("idtipoplano"));
                    pacientes.add(paciente);
                }
            } 
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao consultar pacientes");
        } finally {
            conexao.closeConexao();
        }
        return pacientes;
    }
    
    public void alterarPaciente(Paciente paciente) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE paciente SET nome = ?, cpf = ?, senha = ?, autorizado = ?, idtipoplano = ? WHERE ID = ? ");
            sql.setString(1, paciente.getNome());
            sql.setString(2, paciente.getCpf());
            sql.setString(3, paciente.getSenha());
            sql.setString(4, paciente.getAutorizado());
            sql.setInt(5, paciente.getIdTipoPlano());
            sql.setInt(6, paciente.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    public void excluirPaciente(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM paciente WHERE ID = ?");
            sql.setInt(1, id);
            sql.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void cadastrarPlano(TipoPlano tipoPlano) throws Exception {
        Conexao conexao = new Conexao();
        try {
            
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO tipoplano (descricao)"
                    + " VALUES (?)");
            sql.setString(1, tipoPlano.getDescricao());
            sql.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }
    
        public void alterarPlano(TipoPlano tipoPlano) throws Exception {
        Conexao conexao = new Conexao();
        
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE tipoplano SET descricao = ? WHERE ID = ? ");
            sql.setString(1, tipoPlano.getDescricao());
            sql.setInt(2, tipoPlano.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    public void excluirPlano(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM tipoplano WHERE ID = ?");
            sql.setInt(1, id);
            sql.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    public TipoPlano getPlano(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            TipoPlano tipoPlano = new TipoPlano();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Tipoplano WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    tipoPlano.setId(Integer.parseInt(resultado.getString("ID")));
                    tipoPlano.setDescricao(resultado.getString("DESCRICAO"));
                }
            }
            return tipoPlano;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
        public void cadastrarEspecialidade(Especialidade especialidade) throws Exception {
        Conexao conexao = new Conexao();
        try {
            
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO especialidade (descricao)"
                    + " VALUES (?)");
            sql.setString(1, especialidade.getDescricao());
            sql.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }
        
    public void alterarEspecialidade(Especialidade especialidade) throws Exception {
        Conexao conexao = new Conexao();
        
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE especialidade SET descricao = ? WHERE ID = ? ");
            sql.setString(1, especialidade.getDescricao());
            sql.setInt(2, especialidade.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    public void excluirEspecialidade(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM especialidade WHERE ID = ?");
            sql.setInt(1, id);
            sql.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    public Especialidade getEspecialidade(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Especialidade especialidade = new Especialidade();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM especialidade WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    especialidade.setId(Integer.parseInt(resultado.getString("ID")));
                    especialidade.setDescricao(resultado.getString("DESCRICAO"));
                }
            }
            return especialidade;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

        
}