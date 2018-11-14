/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Pedrão Master
 */

@Entity
@Table(name="gedod")
public class Gedod implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nome;
    
    @Column(unique = true)
    private String numeroEquipamento;
    
    private String lugar;
    
    private String descricaoDefeito;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCadastro;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Gedod() {
    
    }

    public Gedod(String nome, String numeroEquipamento, String lugar, String descricaoDefeito, Date dataCadastro) {
        this.nome = nome;
        this.numeroEquipamento = numeroEquipamento;
        this.lugar = lugar;
        this.descricaoDefeito = descricaoDefeito;
        this.dataCadastro = dataCadastro;
    }

    public String getNumeroEquipamento() {
        return numeroEquipamento;
    }

    public void setNumeroEquipamento(String numeroEquipamento) {
        this.numeroEquipamento = numeroEquipamento;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getDescricaoDefeito() {
        return descricaoDefeito;
    }

    public void setDescricaoDefeito(String descricaoDefeito) {
        this.descricaoDefeito = descricaoDefeito;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.nome);
        hash = 41 * hash + Objects.hashCode(this.numeroEquipamento);
        hash = 41 * hash + Objects.hashCode(this.lugar);
        hash = 41 * hash + Objects.hashCode(this.descricaoDefeito);
        hash = 41 * hash + Objects.hashCode(this.dataCadastro);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Gedod other = (Gedod) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.numeroEquipamento, other.numeroEquipamento)) {
            return false;
        }
        if (!Objects.equals(this.lugar, other.lugar)) {
            return false;
        }
        if (!Objects.equals(this.descricaoDefeito, other.descricaoDefeito)) {
            return false;
        }
        if (!Objects.equals(this.dataCadastro, other.dataCadastro)) {
            return false;
        }
        return true;
    }
    
}
