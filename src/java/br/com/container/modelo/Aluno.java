/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Alunos
 */
@Entity
@Table(name="aluno")
public class Aluno extends Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Column(unique = true)
    private String cpf;

    public Aluno() {
    }    

    public Aluno(String cpf, Long id, String nome, String email, String foneFixo, String foneMovel, Boolean whatsapp) {
        super(id, nome, email, foneFixo, foneMovel, whatsapp);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
 
    
    
}
    

    
