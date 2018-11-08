/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.dao;

import br.com.container.modelo.Empresa;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author junior182b
 */
public class EmpresaDaoImpl extends BaseDaoImpl<Empresa, Long> implements EmpresaDao {

    @Override
    public Empresa pesquisaEntidadeId(Long id, Session session) throws HibernateException {
        return (Empresa) session.get(Empresa.class, id);
    }

    @Override
    public List<Empresa> listaTodos(Session session) throws HibernateException {
        return session.createQuery("from Empresa").list();
    }

    @Override
   public List<Empresa> pesquisaPorNome(String nome, Session session) throws HibernateException {
        Query consulta = session.createQuery("from Empresa where nome like :nomeEmpresa");
        consulta.setParameter("nomeEmpresa", "%" + nome + "%");
        return consulta.list();
    }
    
    
    
}
