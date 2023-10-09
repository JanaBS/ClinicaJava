package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import aplicacao.Usuario;


public class UsuarioDAO {

    public Usuario Logar(Usuario usuario, String perfil) throws Exception {
        Conexao conexao = new Conexao();
        try {
            if (perfil.equals("administrador")) {
                PreparedStatement sql = conexao.getConexao().prepareStatement(String.format("SELECT * FROM %s WHERE cpf=? and senha =? LIMIT 1;", perfil));
                sql.setString(1, usuario.getCpf());
                sql.setString(2, usuario.getSenha());
                ResultSet resultado = sql.executeQuery();
                Usuario usuarioObtido = new Usuario();
            
                if (resultado != null) {
                    while (resultado.next()) {
                        usuarioObtido.setId(Integer.parseInt(resultado.getString("ID")));
                        usuarioObtido.setCpf(resultado.getString("CPF"));
                        usuarioObtido.setSenha(resultado.getString("SENHA"));
                    }
                }
                return usuarioObtido;
                
            } else {
                PreparedStatement sql = conexao.getConexao().prepareStatement(String.format("SELECT * FROM %s WHERE cpf=? and senha =? AND autorizado = 'S' LIMIT 1;", perfil));
                sql.setString(1, usuario.getCpf());
                sql.setString(2, usuario.getSenha());
                ResultSet resultado = sql.executeQuery();
                
                Usuario usuarioObtido = new Usuario();
            
                if (resultado != null) {
                    while (resultado.next()) {
                        usuarioObtido.setId(Integer.parseInt(resultado.getString("ID")));
                        usuarioObtido.setCpf(resultado.getString("CPF"));
                        usuarioObtido.setSenha(resultado.getString("SENHA"));
                    }
                }
                return usuarioObtido;
            }
            
            //sql.setString(1, perfil);
            //System.out.print(perfil + usuario.getSenha() + usuario.getCpf());
            
            
            

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Query incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

}
