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
public class Exames {
    
    private int id;
    private int idtipoexame;
    private int idconsulta;
    
   
    public Exames() {
        this.id = 0;
        this.idtipoexame = 0;
        this.idconsulta = 0;
    }
    
    public Exames(int id, int idtipoexame, int idconsulta) {
        this.id = id;
        this.idtipoexame = idtipoexame;
        this.idconsulta = idconsulta;
    }
    
        public Exames(int idtipoexame, int idconsulta) {
        this.idtipoexame = idtipoexame;
        this.idconsulta = idconsulta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getIdTipoExame() {
        return idtipoexame;
    }

    public void setIdTipoExame(int idtipoexame) {
        this.idtipoexame = idtipoexame;
    }
    
    public int getIdConsulta() {
        return idconsulta;
    }

    public void setIdConsulta(int idconsulta) {
        this.idconsulta = idconsulta;
    }
    
}
