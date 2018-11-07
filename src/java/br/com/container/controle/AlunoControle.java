/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.controle;

import br.com.container.dao.AlunoDao;
import br.com.container.dao.AlunoDaoImpl;
import br.com.container.dao.EnderecoDao;
import br.com.container.dao.HibernateUtil;
import br.com.container.modelo.Aluno;
import br.com.container.modelo.Endereco;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author cel05
 */
@ManagedBean(name = "alunoC")
@ViewScoped
public class AlunoControle implements Serializable {

    private boolean mostraToolbar = false;
    private boolean pesquisaPorDisciplina = false;
    private String pesqNome = "";
    private String pesqCpf = "";

    private Session session;
    private AlunoDao dao;

    private Aluno aluno;
    private List<Aluno> alunos;
    private DataModel<Aluno> modelAlunos;
    private Endereco endereco;

    
    private EnderecoDao enderecoDao;

    private void abreSessao() {
        if (session == null || !session.isOpen()) {
            session = HibernateUtil.abreSessao();
        }
    }

    public void mudaToolbar() {
        aluno = new Aluno();
        aluno.setWhatsapp(false);
        alunos = new ArrayList();
        pesqNome = "";
        mostraToolbar = !mostraToolbar;
    }

    public void pesquisar() {
        dao = new AlunoDaoImpl();
        try {
            abreSessao();

            if (!pesqNome.equals("") && !pesqCpf.equals("")) {
                alunos = dao.pesquisaPorNome(pesqNome, session);
            } else if (!pesqCpf.equals("")) {
                aluno = dao.pesquisarCPF(pesqCpf, session);
            } else{
                alunos = dao.listaTodos(session);
            }

            modelAlunos = new ListDataModel(alunos);
            pesqNome = null;
            pesqCpf = null;
        } catch (HibernateException ex) {
            System.err.println("Erro pesquisa aluno:\n" + ex.getMessage());
        } finally {
            session.close();
        }
    }

    public void salvar() {
        dao = new AlunoDaoImpl();
        try {
            abreSessao();
            aluno.setEndereco(endereco);
            endereco.setPessoa(aluno);
            dao.salvarOuAlterar(aluno, session);
            Mensagem.salvar("Aluno " + aluno.getNome());
        } catch (Exception ex) {
            Mensagem.mensagemError("Erro ao salvar\nTente novamente");
            System.err.println("Erro pesquisa aluno:\n" + ex.getMessage());
        } finally {
            aluno = new Aluno();
            aluno.setEndereco(endereco = new Endereco());
            aluno.setWhatsapp(false);
            session.close();
        }
    }

    public void alterarAluno() {
        mostraToolbar = !mostraToolbar;
        aluno = modelAlunos.getRowData();
        endereco = aluno.getEndereco();
    }

    public void excluir() {
        aluno = modelAlunos.getRowData();
        dao = new AlunoDaoImpl();
        try {
            abreSessao();
            dao.remover(aluno, session);
            aluno = null;
            modelAlunos = null;
            Mensagem.excluir("Aluno " + aluno.getNome());
            aluno = new Aluno();
        } catch (Exception ex) {
            System.err.println("Erro ao excluir aluno\n" + ex.getMessage());
        } finally {
            session.close();
        }
    }

    //Getters e Setters
    public boolean isMostraToolbar() {
        return mostraToolbar;
    }

    public void setMostraToolbar(boolean mostraToolbar) {
        this.mostraToolbar = mostraToolbar;
    }

    public boolean isPesquisaPorDisciplina() {
        return pesquisaPorDisciplina;
    }

    public void setPesquisaPorDisciplina(boolean pesquisaPorDisciplina) {
        this.pesquisaPorDisciplina = pesquisaPorDisciplina;
    }

    public String getPesqNome() {
        return pesqNome;
    }

    public void setPesqNome(String pesqNome) {
        this.pesqNome = pesqNome;
    }

    public String getPesqCpf() {
        return pesqCpf;
    }

    public void setPesqCpf(String pesqCpf) {
        this.pesqCpf = pesqCpf;
    }

    public Aluno getAluno() {
        if (aluno == null) {
            aluno = new Aluno();
            
        }
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public List<Aluno> getAlunos() {
        if (alunos == null) {
            alunos = new ArrayList();
        }
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public DataModel<Aluno> getModelAlunos() {
        return modelAlunos;
    }

    public void setModelAlunos(DataModel<Aluno> modelAlunos) {
        this.modelAlunos = modelAlunos;
    }
    
    public Endereco getEndereco() {
        if (endereco == null) {
            endereco = new Endereco();
        }
        return endereco;
        
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
