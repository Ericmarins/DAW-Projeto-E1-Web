package br.edu.ifsul.controle;

import br.edu.ifsul.dao.RecursoDAO;
import br.edu.ifsul.modelo.Recurso;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author eric_
 */
@ManagedBean(name = "controleRecurso")
@ApplicationScoped
public class ControleRecurso implements Serializable {
    
    private RecursoDAO dao;
    private Recurso objeto;
    
    public ControleRecurso(){
        dao = new RecursoDAO();
    }
    
    public String listar(){
        return "/privado/recurso/listar?faces-redirect=true";
    }
    
    public String inicio(){
        return "/index?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Recurso();
    }
    
    public String cancelar(){
        return "listar?faces-redirect=true";
    }
    
    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir: " + e.getMessage());
        }
    }

    public void editar(Integer id) {
        try {
            objeto = (Recurso) dao.localizar(id);
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar obejto: " + e.getMessage());
        }
    }

    public void remover(Integer id) {
        try {
            objeto = (Recurso) dao.localizar(id);
            dao.remover(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro a remover objeto: " + e.getMessage());
        }
    }
    
    public RecursoDAO getDao() {
        return dao;
    }

    public void setDao(RecursoDAO dao) {
        this.dao = dao;
    }

    public Recurso getObjeto() {
        return objeto;
    }

    public void setObjeto(Recurso objeto) {
        this.objeto = objeto;
    }

}
