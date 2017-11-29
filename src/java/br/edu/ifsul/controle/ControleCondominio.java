package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CondominioDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.dao.RecursoDAO;
import br.edu.ifsul.modelo.Condominio;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.modelo.Recurso;
import br.edu.ifsul.modelo.UnidadeCondominial;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author eric_
 */
@ManagedBean(name = "controleCondominio")
@ViewScoped
public class ControleCondominio implements Serializable {

    private CondominioDAO<Condominio> dao;
    private Condominio objeto;
    private RecursoDAO<Recurso> daoRecurso;    
    private Recurso recurso;
    private UnidadeCondominial unidade;
    private Boolean novaUnidade;
    private PessoaDAO<Pessoa> daoPessoa;

    public ControleCondominio() {
        dao = new CondominioDAO<>();
        daoRecurso = new RecursoDAO<>();
        daoPessoa= new PessoaDAO<>();
    }
    
    public void imprimir(Integer id){
        objeto= dao.localizar(id);
        List<Condominio> lista= new ArrayList<>();
        lista.add(objeto);
        HashMap parametros= new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioCondominio", parametros, lista);
    }
    public void adicionarRecurso(){
        if (recurso != null){
            if (!objeto.getRecursos().contains(recurso)){
                objeto.getRecursos().add(recurso);
                Util.mensagemInformacao("Recurso adicionado com sucesso!");
            } else {
                Util.mensagemErro("Recurso já existente na lista de recursos!");
            }
        }
    }
    
    public void removerRecurso(int index){
        objeto.getRecursos().remove(index);
        Util.mensagemInformacao("Recurso removido com sucesso!");
    }

    public void novaUnidade() {
        unidade = new UnidadeCondominial();
        setNovaUnidade((Boolean) true);
    }
    
    public void alterarUnidade(int index){
        unidade = objeto.getUnidades().get(index);
        novaUnidade = false;
    }
    
    public void salvarUnidade(){
        if (novaUnidade){
            objeto.adicionarUnidade(unidade);
        }
        Util.mensagemInformacao("Unidade persistida com sucesso!");
    }
    
    public void removerUnidade(int index){
        objeto.removerUnidade(index);
        Util.mensagemInformacao("Unidade removida com sucesso!");
    }

    public String listar() {
        return "/privado/condominio/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Condominio();
    }

    public void salvar() {
        boolean persistiu;
        if (objeto.getId() == null) {
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public void editar(Integer id) {
        objeto = dao.localizar(id);
    }

    public void remover(Integer id) {
        objeto = dao.localizar(id);
        if (dao.remover(objeto)) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public CondominioDAO getDao() {
        return dao;
    }

    public void setDao(CondominioDAO dao) {
        this.dao = dao;
    }

    public Condominio getObjeto() {
        return objeto;
    }

    public void setObjeto(Condominio objeto) {
        this.objeto = objeto;
    }

    public RecursoDAO<Recurso> getDaoRecurso() {
        return daoRecurso;
    }

    public void setDaoRecurso(RecursoDAO<Recurso> daoRecurso) {
        this.daoRecurso = daoRecurso;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public Boolean getNovaUnidade() {
        return novaUnidade;
    }

    public void setNovaUnidade(Boolean novaUnidade) {
        this.novaUnidade = novaUnidade;
    }

    public UnidadeCondominial getUnidade() {
        return unidade;
    }

    public void setUnidade(UnidadeCondominial unidade) {
        this.unidade = unidade;
    }

    public PessoaDAO<Pessoa> getDaoPessoa() {
        return daoPessoa;
    }

    public void setDaoPessoa(PessoaDAO<Pessoa> daoPessoa) {
        this.daoPessoa = daoPessoa;
    }

}
