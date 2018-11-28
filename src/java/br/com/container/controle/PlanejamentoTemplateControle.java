/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.controle;

import br.com.container.dao.HibernateUtil;
import br.com.container.dao.PlanejamentoTemplateDao;
import br.com.container.dao.PlanejamentoTemplateDaoImpl;
import br.com.container.modelo.PlanejamentoTemplate;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author silvio
 */
@ManagedBean(name = "planejaTemplateC")
@ViewScoped
public class PlanejamentoTemplateControle implements Serializable {

    private PlanejamentoTemplate planejamentoTemplate;
    private PlanejamentoTemplateDao planejamentoTemplateDao;
    private Session sessao;
    private DataModel<PlanejamentoTemplate> modelPlanejamentoTemplates;
    private List<PlanejamentoTemplate> planejamentoTemplates;
    private boolean mostra_toolbar;

    private void abreSessao() {
        if (sessao == null) {
            sessao = HibernateUtil.abreSessao();
        } else if (!sessao.isOpen()) {
            sessao = HibernateUtil.abreSessao();
        }
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
        planejamentoTemplateDao = new PlanejamentoTemplateDaoImpl();
        try {
            abreSessao();
            planejamentoTemplates = planejamentoTemplateDao.pesquisaPorNome(planejamentoTemplate.getNome(), sessao);
            modelPlanejamentoTemplates = new ListDataModel(planejamentoTemplates);
            planejamentoTemplate.setNome(null);
        } catch (Exception e) {
            System.out.println("erro ao pesquisar Planejamento por nome: " + e.getMessage());
        } finally {
            sessao.close();
        }
    }

    public void limpar() {
        planejamentoTemplate = new PlanejamentoTemplate();
    }

    public void carregarParaAlterar() {
        mostra_toolbar = !mostra_toolbar;
        planejamentoTemplate = modelPlanejamentoTemplates.getRowData();
    }

    public void excluir() {
        planejamentoTemplate = modelPlanejamentoTemplates.getRowData();
        planejamentoTemplateDao = new PlanejamentoTemplateDaoImpl();
        abreSessao();
        try {
            planejamentoTemplateDao.remover(planejamentoTemplate, sessao);
            planejamentoTemplates.remove(planejamentoTemplate);
            modelPlanejamentoTemplates = new ListDataModel(planejamentoTemplates);
            Mensagem.excluir("Planejamento");
            limpar();
        } catch (Exception e) {
            System.out.println("erro ao excluir Planejamento: " + e.getMessage());
        } finally {
            sessao.close();
        }
    }

    public void salvar() {
        planejamentoTemplateDao = new PlanejamentoTemplateDaoImpl();
        abreSessao();
        try {
            planejamentoTemplateDao.salvarOuAlterar(planejamentoTemplate, sessao);
            Mensagem.salvar("Planejamento " + planejamentoTemplate.getNome());
            planejamentoTemplate = null;
        } catch (HibernateException e) {
            System.out.println("Erro ao Planejamento Sala " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro no salvar Planejamento Controle " + e.getMessage());
        } finally {
            sessao.close();
        }
    }

    public void limparTela() {
        limpar();
    }

    //getters e setters
    public PlanejamentoTemplate getPlanejamentoTemplate() {
        if (planejamentoTemplate == null) {
            planejamentoTemplate = new PlanejamentoTemplate();
        }
        return planejamentoTemplate;
    }

    public void setPlanejamentoTemplate(PlanejamentoTemplate planejamentoTemplate) {
        this.planejamentoTemplate = planejamentoTemplate;
    }

    public DataModel<PlanejamentoTemplate> getModelPlanejamentoTemplates() {
        return modelPlanejamentoTemplates;
    }

    public void setModelPlanejamentoTemplates(DataModel<PlanejamentoTemplate> modelPlanejamentoTemplates) {
        this.modelPlanejamentoTemplates = modelPlanejamentoTemplates;
    }

    public boolean isMostra_toolbar() {
        return mostra_toolbar;
    }

    public void setMostra_toolbar(boolean mostra_toolbar) {
        this.mostra_toolbar = mostra_toolbar;
    }

    public List<PlanejamentoTemplate> getPlanejamentoTemplates() {
        return planejamentoTemplates;
    }


}
