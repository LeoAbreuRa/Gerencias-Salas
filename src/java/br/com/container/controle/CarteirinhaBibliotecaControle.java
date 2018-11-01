
package br.com.container.controle;

import br.com.container.dao.CarteirinhaBibliotecaDao;
import br.com.container.dao.CarteirinhaBibliotecaDaoImpl;
import br.com.container.dao.HibernateUtil;
import br.com.container.dao.ProfessorDaoImpl;
import br.com.container.modelo.Aluno;
import br.com.container.modelo.CarteirinhaBiblioteca;
import br.com.container.modelo.Professor;
import br.com.container.modelo.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Pedrão
 */
public class CarteirinhaBibliotecaControle implements Serializable {
     
    private CarteirinhaBiblioteca carteirinhaBiblioteca;
    private Usuario usuario;
    private Session session;
    private List<CarteirinhaBiblioteca> carteirinhas;
    private boolean mostraToolbar = false;
    private boolean pesquisaPorCpf = false;
    private String pesqNome = "";
    private String pesqCpf = "";     
    private Aluno aluno;
    private DataModel<CarteirinhaBiblioteca> modelCarteirinhas;
    private CarteirinhaBibliotecaDao dao;
   
    
    
     private void abreSessao() {
        if (session == null) {
            session = HibernateUtil.abreSessao();
        } else if (!session.isOpen()) {
            session = HibernateUtil.abreSessao();
        }
    }
      public void mudaToolbar() {
        carteirinhaBiblioteca = new CarteirinhaBiblioteca();
        carteirinhas = new ArrayList();
        pesqNome = "";
        mostraToolbar = !mostraToolbar;
    }
      
   public void pesquisar() {
        dao = new CarteirinhaBibliotecaDaoImpl();
        try {
            abreSessao();

           
           
        } catch (HibernateException ex) {
            System.err.println("Erro pesquisa carteirinha:\n" + ex.getMessage());
        } finally {
            session.close();
        }
    }  
   
    public void salvar() {
        dao = new CarteirinhaBibliotecaDaoImpl();
        try {
            abreSessao();
            
            
           
            dao.salvarOuAlterar(carteirinhaBiblioteca, session);
            Mensagem.salvar("Carteirinha salva");
        } catch (Exception ex) {
            Mensagem.mensagemError("Erro ao salvar\nTente novamente");
            System.err.println("Erro pesquisa carteirinha:\n" + ex.getMessage());
        } finally {
            carteirinhaBiblioteca = new CarteirinhaBiblioteca();
            
            
            session.close();
        }
    }

    public CarteirinhaBiblioteca getCarteirinhaBiblioteca() {
        return carteirinhaBiblioteca;
    }

    public void setCarteirinhaBiblioteca(CarteirinhaBiblioteca carteirinhaBiblioteca) {
        this.carteirinhaBiblioteca = carteirinhaBiblioteca;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public List<CarteirinhaBiblioteca> getCarteirinhas() {
        return carteirinhas;
    }

    public void setCarteirinhas(List<CarteirinhaBiblioteca> carteirinhas) {
        this.carteirinhas = carteirinhas;
    }

    public boolean isMostraToolbar() {
        return mostraToolbar;
    }

    public void setMostraToolbar(boolean mostraToolbar) {
        this.mostraToolbar = mostraToolbar;
    }

    public boolean isPesquisaPorCpf() {
        return pesquisaPorCpf;
    }

    public void setPesquisaPorCpf(boolean pesquisaPorCpf) {
        this.pesquisaPorCpf = pesquisaPorCpf;
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
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public DataModel<CarteirinhaBiblioteca> getModelCarteirinhas() {
        return modelCarteirinhas;
    }

    public void setModelCarteirinhas(DataModel<CarteirinhaBiblioteca> modelCarteirinhas) {
        this.modelCarteirinhas = modelCarteirinhas;
    }

    public CarteirinhaBibliotecaDao getDao() {
        return dao;
    }

    public void setDao(CarteirinhaBibliotecaDao dao) {
        this.dao = dao;
    }

     
     
  
     
     
     
}
