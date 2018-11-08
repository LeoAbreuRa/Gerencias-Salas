
package br.com.container.controle;

import br.com.container.dao.AlunoDao;
import br.com.container.dao.AlunoDaoImpl;
import br.com.container.dao.CarteirinhaBibliotecaDao;
import br.com.container.dao.CarteirinhaBibliotecaDaoImpl;
import br.com.container.dao.HibernateUtil;

import br.com.container.modelo.Aluno;
import br.com.container.modelo.CarteirinhaBiblioteca;

import br.com.container.modelo.Usuario;
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
 * @author Pedrão
 */

@ManagedBean(name = "carteirinhaC")
@ViewScoped
public class CarteirinhaBibliotecaControle implements Serializable {
     
    private CarteirinhaBiblioteca carteirinhaBiblioteca;
   
    private Session session;
    private List<CarteirinhaBiblioteca> carteirinhas;
    private boolean mostra_toolbar = false;
    private boolean pesquisaPorCpf = false;
    private String pesqNome = "";
    private String pesqCpf = "";     
    private Aluno aluno;
    private AlunoDao alunoDao;
    private List<Aluno> alunos;
    private DataModel<Aluno> modelAlunos;
    private DataModel<CarteirinhaBiblioteca> modelCarteirinhas;
    private CarteirinhaBibliotecaDao dao;

    public CarteirinhaBibliotecaControle() {
        dao = new CarteirinhaBibliotecaDaoImpl();
    }
   
    
    
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
        pesqCpf = "";
        mostra_toolbar = !mostra_toolbar;
    }
      
   public void pesquisar() {
        dao = new CarteirinhaBibliotecaDaoImpl();
        try {
            abreSessao();
            if (!pesqNome.equals("") && !pesqCpf.equals("")) {
                carteirinhas = dao.pesquisaPorNome(pesqNome, session);
            } else if (!pesqCpf.equals("")) {
                carteirinhas = dao.pesquisarPorCPF(pesqCpf, session);
            } else{
                carteirinhas = dao.listaTodos(session);
            }
            
            modelCarteirinhas = new ListDataModel(carteirinhas);
            pesqNome = null;
            pesqCpf = null;
           
        } catch (HibernateException ex) {
            System.err.println("Erro pesquisa carteirinha:\n" + ex.getMessage());
        } finally {
            session.close();
        }
    } 
   
 
   public void pesquisarAlunoPorCpf(){
        alunoDao = new AlunoDaoImpl();
       try{
           abreSessao();
           if(!pesqCpf.equals("")) {
            aluno = alunoDao.pesquisarCPF(pesqCpf, session);
            } else{
                alunos = alunoDao.listaTodos(session);
            }

            modelAlunos = new ListDataModel(alunos);
            pesqCpf = null;
           
       } catch (HibernateException ex) {
         System.err.println("Erro  ao pesquisar aluno por cpf :\n" + ex.getMessage());
       
       } finally{
         session.close();
        }
   }
   
    public void salvar() {
        dao = new CarteirinhaBibliotecaDaoImpl();
        try {
            abreSessao();
            
            carteirinhaBiblioteca.setAluno(aluno);
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
    
    public void alterarCarteirinha() {
        mostra_toolbar = !mostra_toolbar;
        carteirinhaBiblioteca = modelCarteirinhas.getRowData();
        
    }

    public void excluir() {
        carteirinhaBiblioteca = modelCarteirinhas.getRowData();
        dao = new CarteirinhaBibliotecaDaoImpl();
        try {
            abreSessao();
            dao.remover(carteirinhaBiblioteca, session);
            Mensagem.excluir("Carteirinha " + carteirinhaBiblioteca.getAluno());
            aluno = new Aluno();
        } catch (Exception ex) {
            System.err.println("Erro ao excluir carteirinha\n" + ex.getMessage());
        } finally {
            session.close();
        }
    }
     
    public void carregarParaAlterar() {
        mostra_toolbar = !mostra_toolbar;
        carteirinhaBiblioteca = modelCarteirinhas.getRowData();
        
    }
    
    
    
     public List<Aluno> getAlunos() {
        if (alunos == null) {
            alunos = new ArrayList();
        }
        return alunos;
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
    
    
     public void limpar() {
        aluno = new Aluno();
        carteirinhaBiblioteca = new CarteirinhaBiblioteca();
    } 
    
    
    public void limparTela() {
        limpar();
    }

    public CarteirinhaBiblioteca getCarteirinhaBiblioteca() {
        return carteirinhaBiblioteca;
    }

    public void setCarteirinhaBiblioteca(CarteirinhaBiblioteca carteirinhaBiblioteca) {
        this.carteirinhaBiblioteca = carteirinhaBiblioteca;
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

    public boolean isMostra_toolbar() {
        return mostra_toolbar;
    }

    public void setMostra_toolbar(boolean mostra_toolbar) {
        this.mostra_toolbar = mostra_toolbar;
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
          if (aluno == null) {
            aluno = new Aluno();
            
        }
        
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

    public DataModel<Aluno> getModelAlunos() {
        return modelAlunos;
    }

    public void setModelAlunos(DataModel<Aluno> modelAlunos) {
        this.modelAlunos = modelAlunos;
    }

     
     
  
     
     
     
}
