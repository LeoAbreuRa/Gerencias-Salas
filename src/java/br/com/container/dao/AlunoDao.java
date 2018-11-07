/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.dao;

import br.com.container.modelo.Aluno;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Alunos
 */
public interface AlunoDao extends BaseDao<Aluno, Long>{
    
    
    public Aluno pesquisarCPF(String cpf, Session session) throws HibernateException ;
    
}
