/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.controle;

import br.com.container.dao.GedodDao;
import br.com.container.dao.GedodDaoImpl;
import br.com.container.dao.HibernateUtil;
import br.com.container.modelo.Gedod;
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
 * @author Pedrão Master
 */
@ManagedBean(name = "GedodC")
@ViewScoped
public class GedodControle implements Serializable {

    private Gedod gedod;
    private Session sessao;
    private List<Gedod> listaGedod;
    private boolean mostra_toolbar = false;
    private GedodDao dao;
    private String pesqNome = "";
    private DataModel<Gedod> modelGedod;

    public GedodControle() {
        dao = new GedodDaoImpl();
    }

    private void abreSessao() {
        if (sessao == null) {
            sessao = HibernateUtil.abreSessao();
        } else if (!sessao.isOpen()) {
            sessao = HibernateUtil.abreSessao();
        }
    }

    public void mudaToolbar() {
        gedod = new Gedod();
        pesqNome = "";
        listaGedod = new ArrayList();
        mostra_toolbar = !mostra_toolbar;
    }

    public void novo() {
        mostra_toolbar = !mostra_toolbar;

    }

    public void novaPesquisa() {
        mostra_toolbar = !mostra_toolbar;
    }

    public void preparaAlterar() {
        mostra_toolbar = !mostra_toolbar;
    }

    public void pesquisar() {
        dao = new GedodDaoImpl();
        try {
            abreSessao();
            if (!pesqNome.equals("")) {
                listaGedod = dao.pesquisaPorNome(pesqNome, sessao);

            } else {
                listaGedod = dao.listaTodos(sessao);
            }
            modelGedod = new ListDataModel(listaGedod);
            pesqNome = null;
        } catch (HibernateException ex) {
            System.err.println("Erro pesquisa equipamento:\n" + ex.getMessage());
        } finally {
            sessao.close();
        }
    }

    public void salvar() {
        dao = new GedodDaoImpl();
        try {
            abreSessao();
            dao.salvarOuAlterar(gedod, sessao);

            Mensagem.salvar("Equipamento salvo");
        } catch (Exception ex) {
            Mensagem.mensagemError("Erro ao salvar\nTente novamente");
            System.err.println("Erro pesquisa equipamento:\n" + ex.getMessage());
        } finally {
            gedod = new Gedod();

            sessao.close();
        }
    }

    public void alterarCarteirinha() {
        mostra_toolbar = !mostra_toolbar;
        gedod = modelGedod.getRowData();

    }
    public void excluir() {
        gedod = modelGedod.getRowData();
        dao = new GedodDaoImpl();
        try {
            abreSessao();
            dao.remover(gedod, sessao);
            Mensagem.excluir("Equipamento excluído");
        } catch (Exception ex) {
            System.err.println("Erro ao excluir equipamento\n" + ex.getMessage());
        } finally {
            sessao.close();
        }
    }

}
