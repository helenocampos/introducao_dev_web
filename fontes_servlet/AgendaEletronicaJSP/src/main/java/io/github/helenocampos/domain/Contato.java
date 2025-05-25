/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.helenocampos.domain;

/**
 *
 * @author HelenoCampos
 */
public class Contato {

    private String nome;
    private String endereco;
    private String telefone;
    private Integer id;

    public Contato(String nome, String endereço, String telefone, Integer id) {
        this.nome = nome;
        this.endereco = endereço;
        this.telefone = telefone;
        this.id = id;
    }

    public Contato(String nome, String endereço, String telefone) {
        this.nome = nome;
        this.endereco = endereço;
        this.telefone = telefone;
    }
    
    
    
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the endereço
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereço the endereço to set
     */
    public void setEndereco(String endereço) {
        this.endereco = endereço;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

}
