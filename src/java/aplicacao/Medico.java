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
public class Medico {
    
    private int id;
    private String nome;
    private int crm;
    private String estadocrm;
    private String cpf;
    private String senha;
    private String autorizado;
    private int idespecialidade;
    
    
    public Medico(int id, String nome, int crm, String estadocrm, String cpf, String senha, String autorizado, int idespecialidade) {
        this.id = id;
        this.nome = nome;
        this.crm = crm;
        this.estadocrm = estadocrm;
        this.cpf = cpf;
        this.senha = senha;
        this.autorizado = autorizado;
        this.idespecialidade = idespecialidade;
       
    }
    
    public Medico(String nome, int crm, String estadocrm, String cpf, String senha, String autorizado, int idespecialidade) {
        this.nome = nome;
        this.crm = crm;
        this.estadocrm = estadocrm;
        this.cpf = cpf;
        this.senha = senha;
        this.autorizado = autorizado;
        this.idespecialidade = idespecialidade;
       
    }
    public Medico(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }

    public Medico() {
        this.id = 0;
        this.nome = "";
        this.crm = 0;
        this.estadocrm = "";
        this.cpf = "";
        this.senha = "";
        this.autorizado = "";
        this.idespecialidade = 0;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public int getCrm() {
        return crm;
    }

    public void setCrm(int crm) {
        this.crm = crm;
    }
    
    public String getEstadoCrm() {
        return estadocrm;
    }

    public void setEstadoCrm(String estadocrm) {
        this.estadocrm = estadocrm;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String getAutorizado() {
        return autorizado;
    }

    public void setAutorizado(String autorizado) {
        this.autorizado = autorizado;
    }
    
    public int getIdEspecialidade() {
        return idespecialidade;
    }

    public void setIdEspecialidade(int idespecialidade) {
        this.idespecialidade = idespecialidade;
    }
    
}
