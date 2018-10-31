/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.dao;

import br.com.container.modelo.CarteirinhaBiblioteca;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Alunos
 */
public interface CarteirinhaBibliotecaDao extends BaseDao<CarteirinhaBiblioteca, Long>{
    
    public List<CarteirinhaBiblioteca> pesquisarPorCPF(String cpf, Session session);
    
}
