package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import aplicacao.Medico;
import aplicacao.Consulta;



/**
 *
 * @author janaina.borges
 */
public class MedicoDAO {
    
    
    public void cadastrarConsulta(Consulta consulta) throws Exception {
        Conexao conexao = new Conexao();
        try {
            
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE consulta SET descricao = ?, realizada = ? WHERE ID = ?");
            sql.setString(1, consulta.getDescricao());
            sql.setString(2, consulta.getRealizada());
            sql.setInt(3, consulta.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    public  ArrayList<Medico> listaMedicos() throws Exception {
        ArrayList<Medico> medicos = new ArrayList();
        Conexao conexao = new Conexao();
            try {
                PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM medico");
                ResultSet resultado = sql.executeQuery();
                if (resultado != null) {
                    while (resultado.next()) {
                        Medico medico = new Medico(
                        resultado.getInt("ID"),
                        resultado.getString("NOME"),
                        resultado.getInt("CRM"),
                        resultado.getString("ESTADOCRM"),
                        resultado.getString("CPF"),
                        resultado.getString("SENHA"),
                        resultado.getString("AUTORIZADO"),
                        resultado.getInt("IDESPECIALIDADE"));
                        medicos.add(medico);
                    }
                }
                return medicos;

            } catch (SQLException e) {
                throw new RuntimeException("Query de select (get) incorreta");
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
                    medico.setId(resultado.getInt("ID"));
                    medico.setNome(resultado.getString("NOME"));
                    medico.setCrm(resultado.getInt("CRM"));
                    medico.setEstadoCrm(resultado.getString("ESTADOCRM"));
                    medico.setCpf(resultado.getString("CPF"));
                    medico.setSenha(resultado.getString("SENHA"));
                    medico.setAutorizado(resultado.getString("AUTORIZADO"));
                    medico.setIdEspecialidade(resultado.getInt("IDESPECIALIDADE"));
                }
            }
            return medico;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
        
    public Consulta getConsulta(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Consulta consulta = new Consulta();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM consulta WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    consulta.setId(resultado.getInt("ID"));
                    consulta.setData(resultado.getString("DATA"));
                    consulta.setDescricao(resultado.getString("DESCRICAO"));
                    consulta.setRealizada(resultado.getString("REALIZADA"));
                    consulta.setIdMedico(resultado.getInt("IDMEDICO"));
                    consulta.setIdPaciente(resultado.getInt("IDPACIENTE"));
                }
            }
            return consulta;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

        public ArrayList<Consulta> ListaDeConsultas(int idmedico) {
        ArrayList<Consulta> consultas = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT id, DATE(data) AS data, descricao, realizada, idmedico, idpaciente FROM consulta WHERE idmedico=? order by data";
            PreparedStatement sql;
            sql = conexao.getConexao().prepareStatement(selectSQL);
            sql.setInt(1, idmedico);
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

        
}
