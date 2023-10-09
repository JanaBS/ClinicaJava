package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import aplicacao.Paciente;
import aplicacao.TipoPlano;
import aplicacao.Consulta;
import aplicacao.Especialidade;

/**
 *
 * @author janaina.borges
 */
public class PacienteDAO {
    
    public void Inserir(Paciente paciente) throws Exception {
        Conexao conexao = new Conexao();
        try {
            
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO paciente (nome, cpf, senha, autorizado, idtipoplano)"
                    + " VALUES (?,?,?,?,?)");
            sql.setString(1, paciente.getNome());
            sql.setString(2, paciente.getCpf());
            sql.setString(3, paciente.getSenha());
            sql.setString(4, paciente.getAutorizado());
            sql.setInt(5, paciente.getIdTipoPlano());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }
    
        public int validaConsulta(int id, String data) throws Exception {
        Conexao conexao = new Conexao();
        int contagem = 0;
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT COUNT(1) AS contagem FROM consulta WHERE idmedico = ? AND DATE(data) = ?");
            sql.setInt(1, id);
            sql.setString(2, data);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()){
                contagem = Integer.parseInt(resultado.getString("CONTAGEM"));
                }
            }
            
            return contagem;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
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

    public void Alterar(int id, String data, int idmedico) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE consulta SET data = ?, idmedico = ? WHERE ID = ? ");
            sql.setString(1, data);
            sql.setInt(2, idmedico);
            sql.setInt(3, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM consulta WHERE ID = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Paciente> ListaDePacientes() {
        ArrayList<Paciente> todosPacientes = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM paciente order by nome";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Paciente paciente = new Paciente(
                            resultado.getString("nome"),
                            resultado.getString("cpf"),
                            resultado.getString("senha"),
                            resultado.getString("autorizado"),
                            resultado.getInt("idtipoplano"));
                    paciente.setId(Integer.parseInt(resultado.getString("id")));
                    todosPacientes.add(paciente);
                }
            } 
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao consultar pacientes");
        } finally {
            conexao.closeConexao();
        }
        return todosPacientes;
    }
    
    public ArrayList<TipoPlano> ListaDePlanos() {
        ArrayList<TipoPlano> planos = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM tipoplano order by id";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    TipoPlano tipoPlano = new TipoPlano(
                            resultado.getInt("id"),
                            resultado.getString("descricao"));
                    tipoPlano.setId(Integer.parseInt(resultado.getString("id")));
                    planos.add(tipoPlano);
                }
            } 
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao consultar pacientes");
        } finally {
            conexao.closeConexao();
        }
        return planos;
    }
    
    public TipoPlano getPlano(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            TipoPlano plano = new TipoPlano();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM tipoplano WHERE id = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    plano.setId(Integer.parseInt(resultado.getString("ID")));
                    plano.setDescricao(resultado.getString("DESCRICAO"));
                }
            }
            return plano;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    public void cadastrarConsulta(Consulta consulta) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO consulta (data, descricao, realizada, idmedico, idpaciente)"
                    + " VALUES (?,?,?,?,?)");
            sql.setString(1, consulta.getData());
            sql.setString(2, "");
            sql.setString(3, "N");
            sql.setInt(4, consulta.getIdMedico());
            sql.setInt(5, consulta.getIdPaciente());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }
    
    public ArrayList<Consulta> ListaDeConsultas(int idpaciente) {
        ArrayList<Consulta> consultas = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT id, DATE(data) AS data, descricao, realizada, idmedico, idpaciente FROM consulta WHERE idpaciente=? order by data";
            PreparedStatement sql;
            sql = conexao.getConexao().prepareStatement(selectSQL);
            sql.setInt(1, idpaciente);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Consulta consulta = new Consulta(
                            resultado.getInt("id"),
                            resultado.getString("data"),
                            resultado.getString("descricao"),
                            resultado.getString("realizada"),
                            resultado.getInt("idmedico"),
                            resultado.getInt("idpaciente"));
                    consultas.add(consulta);
                }
            } 
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao consultar pacientes");
        } finally {
            conexao.closeConexao();
        }
        return consultas;
    }

    public Consulta getConsulta(int id) throws Exception {
    Conexao conexao = new Conexao();
        try {
            Consulta consulta = new Consulta();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM consulta WHERE id = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    consulta.setId(Integer.parseInt(resultado.getString("ID")));
                    consulta.setData(resultado.getString("DATA"));
                    consulta.setDescricao(resultado.getString("DESCRICAO"));
                    consulta.setRealizada(resultado.getString("REALIZADA"));
                    consulta.setIdMedico(Integer.parseInt(resultado.getString("IDMEDICO")));
                    consulta.setIdPaciente(Integer.parseInt(resultado.getString("IDPACIENTE")));
                }
            }
            return consulta;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    public ArrayList<Especialidade> ListaDeEspecialidades() {
        ArrayList<Especialidade> especialidades = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT DISTINCT * FROM especialidade order by descricao";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Especialidade especialidade = new Especialidade(
                            resultado.getInt("id"),
                            resultado.getString("descricao"));
                    especialidade.setId(Integer.parseInt(resultado.getString("id")));
                    especialidades.add(especialidade);
                }
            } 
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao consultar pacientes");
        } finally {
            conexao.closeConexao();
        }
        return especialidades;
    }
    public Especialidade getEspecialidade(int id) throws Exception {
    Conexao conexao = new Conexao();
        try {
            Especialidade especialidade = new Especialidade();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT DISTINCT * FROM especialidade WHERE id = ?");
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
