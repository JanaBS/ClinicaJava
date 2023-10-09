/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao;

/**
 *
 * @author janaina.borges
 */
public class TipoPlano {
    
    private int id;
    private String descricao;
    
   
    public TipoPlano() {
        this.id = 0;
        this.descricao = "";
        
    }
    
    public TipoPlano(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
    
    public TipoPlano(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
