package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import aplicacao.Exames;
import aplicacao.TipoPlano;
import aplicacao.Consulta;
import aplicacao.Especialidade;
import aplicacao.TipoExame;

/**
 *
 * @author janaina.borges
 */
public class ExameDAO {

    
    public void Inserir(Exames exame) throws Exception {
        Conexao conexao = new Conexao();
        try {
            
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO exames (idtipoexame, idconsulta)"
                    + " VALUES (?,?)");
            sql.setInt(1, exame.getIdTipoExame());
            sql.setInt(2, exame.getIdConsulta());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    public TipoExame getTipoExame(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            TipoExame exame = new TipoExame();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM tipoexame WHERE id = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    exame.setDescricao(resultado.getString("descricao"));
                    exame.setId(Integer.parseInt(resultado.getString("ID")));
                    
                }
            }
            return exame;

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
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM exames WHERE ID = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<TipoExame> ListaTipoExame() {
        ArrayList<TipoExame> todosExames = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM tipoexame order by id";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    TipoExame exame = new TipoExame(
                            resultado.getInt("id"),
                            resultado.getString("descricao"));
                            exame.setId(Integer.parseInt(resultado.getString("id")));
                    todosExames.add(exame);
                }
            } 
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao consultar pacientes");
        } finally {
            conexao.closeConexao();
        }
        return todosExames;
    }
    
        public ArrayList<Exames> ListaExames(int idconsulta) {
        ArrayList<Exames> todosExames = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM exames WHERE idconsulta = ?  order by id");
            sql.setInt(1, idconsulta);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Exames exame = new Exames(
                            resultado.getInt("id"),
                            resultado.getInt("idtipoexame"),
                            resultado.getInt("idconsulta"));
                    todosExames.add(exame);
                }
            } 
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao consultar pacientes");
        } finally {
            conexao.closeConexao();
        }
        return todosExames;
    }
    
  
        
}
