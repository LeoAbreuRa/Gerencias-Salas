
package br.com.container.dao;

import br.com.container.modelo.Agenda;
import br.com.container.modelo.CarteirinhaBiblioteca;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Pedrão
 */
public class CarteirinhaBibliotecaDaoImpl  extends BaseDaoImpl<CarteirinhaBiblioteca, Long> implements CarteirinhaBibliotecaDao, Serializable  {

    @Override
    public CarteirinhaBiblioteca pesquisaEntidadeId(Long id, Session session) throws HibernateException {
         return (CarteirinhaBiblioteca) session.get(CarteirinhaBiblioteca.class, id);
    }

    @Override
    public List<CarteirinhaBiblioteca> listaTodos(Session session) throws HibernateException {
      return session.createQuery("from CarteirinhaBiblioteca").list();  
    }

    @Override
    public List<CarteirinhaBiblioteca> pesquisaPorNome(String nome, Session session) throws HibernateException {
       Query consulta = session.createQuery("from Aluno where nome like :nome");
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.list();
    }

    @Override
    public List<CarteirinhaBiblioteca> pesquisarPorCPF(String cpf, Session session) {
        Query consulta = session.createQuery("from Aluno where cpf like :cpf");
        consulta.setParameter("cpf", cpf);
        return consulta.list();
    }
    
    
}
