/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.dao;

import br.com.container.modelo.Endereco;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Alunos
 */
public class EnderecoDaoImpl extends BaseDaoImpl<Endereco, Long> implements EnderecoDao{

    @Override
    public Endereco pesquisaEntidadeId(Long id, Session session) throws HibernateException {
        return (Endereco) session.get(Endereco.class, id);
    }

    @Override
    public List<Endereco> listaTodos(Session session) throws HibernateException {
        return session.createQuery("from Endereco").list();
    }

    @Override
    public List<Endereco> pesquisaPorNome(String nome, Session session) throws HibernateException {
        Query consulta = session.createQuery("from Endereco e where e.nome like :nome");
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.list();
    }
    
}
