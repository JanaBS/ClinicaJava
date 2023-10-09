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
public class Consulta {
    
    private int id;
    private String data;
    private String descricao;
    private String realizada;
    private int idmedico;
    private int idpaciente;

    public Consulta() {
        this.id = 0;
        this.data = "";
        this.descricao = "";
        this.realizada = "";
        this.idmedico = 0;
        this.idpaciente = 0;
    }
    
    public Consulta(int id, String data, String descricao, String realizada, int idmedico, int idpaciente) {
        this.id = id;
        this.data = data;
        this.descricao = descricao;
        this.realizada = realizada;
        this.idmedico = idmedico;
        this.idpaciente = idpaciente;
    }
    
    public Consulta(String data, int idmedico, int idpaciente) {
        this.data = data;
        this.idmedico = idmedico;
        this.idpaciente = idpaciente;
    }
    

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String descricao) {
        this.data = data;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getRealizada() {
        return realizada;
    }

    public void setRealizada(String realizada) {
        this.realizada = realizada;
    }
    
    public int getIdMedico() {
        return idmedico;
    }

    public void setIdMedico(int idmedico) {
        this.idmedico = idmedico;
    }
    
    public int getIdPaciente() {
        return idpaciente;
    }

    public void setIdPaciente(int idpaciente) {
        this.idpaciente = idpaciente;
    }
    
}
