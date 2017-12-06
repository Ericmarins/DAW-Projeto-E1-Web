package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AluguelDAO;
import br.edu.ifsul.dao.UnidadeCondominialDAO;
import br.edu.ifsul.dao.LocatarioDAO;
import br.edu.ifsul.modelo.Aluguel;
import br.edu.ifsul.modelo.UnidadeCondominial;
import br.edu.ifsul.modelo.Locatario;
import br.edu.ifsul.modelo.Mensalidades;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author eric_
 */
@ManagedBean(name = "controleAluguel")
@ViewScoped
public class ControleAluguel implements Serializable {

    private AluguelDAO<Aluguel> dao;
    private Aluguel objeto;
    private LocatarioDAO<Locatario> daoLocatario;    
    private Locatario locatario;
    private Mensalidades mensalidades;
    private Boolean novaMensalidade;
    private UnidadeCondominialDAO<UnidadeCondominial> daoUnidadeCondominial;

    public ControleAluguel() {
        dao = new AluguelDAO<>();
        daoLocatario = new LocatarioDAO<>();
        daoUnidadeCondominial= new UnidadeCondominialDAO<>();
    }
    
    public void novaMensalidade() {
        setMensalidade(new Mensalidades());
        setNovaUnidade((Boolean) true);
    }
    
    public void alterarMensalidade(int index){
        setMensalidade(objeto.getMensalidades().get(index));
        setNovaMensalidade((Boolean) false);
    }
    
    public void salvarMensalidade(){
        if (getNovaMensalidade()){
            objeto.adicionarMensalidade(getMensalidade());
        }
        Util.mensagemInformacao("Mensalidade persistida com sucesso!");
    }
    
    public void removerMensalidade(int index){
        objeto.removerMensalidade(index);
        Util.mensagemInformacao("Mensalidade removida com sucesso!");
    }

    public String listar() {
        return "/privado/aluguel/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Aluguel();
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

    public AluguelDAO getDao() {
        return dao;
    }

    public void setDao(AluguelDAO dao) {
        this.dao = dao;
    }

    public Aluguel getObjeto() {
        return objeto;
    }

    public void setObjeto(Aluguel objeto) {
        this.objeto = objeto;
    }

    public LocatarioDAO<Locatario> getDaoLocatario() {
        return daoLocatario;
    }

    public void setDaoLocatario(LocatarioDAO<Locatario> daoLocatario) {
        this.daoLocatario = daoLocatario;
    }

    public Locatario getLocatario() {
        return locatario;
    }

    public void setLocatario(Locatario locatario) {
        this.locatario = locatario;
    }

    public Boolean getNovaUnidade() {
        return getNovaMensalidade();
    }

    public void setNovaUnidade(Boolean novaMensalidade) {
        this.setNovaMensalidade(novaMensalidade);
    }

    public Mensalidades getUnidade() {
        return getMensalidade();
    }

    public void setUnidade(Mensalidades mensalidades) {
        this.setMensalidade(mensalidades);
    }

    public UnidadeCondominialDAO<UnidadeCondominial> getDaoUnidadeCondominial() {
        return daoUnidadeCondominial;
    }

    public void setDaoUnidadeCondominial(UnidadeCondominialDAO<UnidadeCondominial> daoUnidadeCondominial) {
        this.daoUnidadeCondominial = daoUnidadeCondominial;
    }

    public Mensalidades getMensalidade() {
        return getMensalidades();
    }

    public void setMensalidade(Mensalidades mensalidades) {
        this.setMensalidades(mensalidades);
    }

    public Boolean getNovaMensalidade() {
        return novaMensalidade;
    }

    public void setNovaMensalidade(Boolean novaMensalidade) {
        this.novaMensalidade = novaMensalidade;
    }

    public Mensalidades getMensalidades() {
        return mensalidades;
    }

    public void setMensalidades(Mensalidades mensalidades) {
        this.mensalidades = mensalidades;
    }

}
