package br.com.container.modelo;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 50, nullable = false)
    private String nome;
    @Column(unique = true)
    private String email;
    
    private String foneFixo;
    private String foneMovel;
    private Boolean whatsapp;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private Endereco endereco;

    public Pessoa(Long id, String nome, String email, String foneFixo, String foneMovel, Boolean whatsapp) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.foneFixo = foneFixo;
        this.foneMovel = foneMovel;
        this.whatsapp = whatsapp;
    }

    

    public Pessoa() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getFoneFixo() {
        return foneFixo;
    }

    public void setFoneFixo(String foneFixo) {
        this.foneFixo = foneFixo;
    }

    public String getFoneMovel() {
        return foneMovel;
    }

    public void setFoneMovel(String foneMovel) {
        this.foneMovel = foneMovel;
    }

    public Boolean getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(Boolean whatsapp) {
        this.whatsapp = whatsapp;
    }

     @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }


    @Override
    public String toString() {
        return "br.com.container.modelo.Atividade[ id=" + id + " ]";
    }
    
}
