/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.dao;

import br.com.container.modelo.Gedod;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Pedrão Master
 */
public class GedodDaoImpl extends BaseDaoImpl<Gedod, Long> implements GedodDao, Serializable {

    @Override
    public Gedod pesquisaEntidadeId(Long id, Session session) throws HibernateException {
         return (Gedod) session.get(Gedod.class, id);
    }

    @Override
    public List<Gedod> listaTodos(Session session) throws HibernateException {
         return session.createQuery("from Gedod").list();
    }

    @Override
    public List<Gedod> pesquisaPorNome(String nome, Session session) throws HibernateException {
        Query consulta = session.createQuery("from Gedod g WHERE g.nome like :nome");
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.list();
    }
    
}
