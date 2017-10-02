/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.LocatarioDAO;
import br.edu.ifsul.modelo.Locatario;
import br.edu.ifsul.util.Util;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author eric_
 */
@ManagedBean
@ApplicationScoped
public class ControleLocatario {
    private LocatarioDAO dao;
    private Locatario objeto;
    
    public ControleLocatario(){
        dao= new LocatarioDAO();
    }
    
    public String listar(){
        return "/privado/locatario/listar?faces-redirect=true";
    }
    
    public String inicio(){
        return "/index?faces-redirect=true";
    }
    
    public String novo(){
        setObjeto(new Locatario());
        return "formulario?faces-redirect=true";
    }
    
    public String salvar(){
        if (getDao().salvar(getObjeto())){
            Util.mensagemInformacao(getDao().getMensagem());
            return "listar?faces-redirect=true";
        } else {
            Util.mensagemErro(getDao().getMensagem());
            return "formulario?faces-redirect=true";
        }
    }
    
    public String cancelar(){
        return "listar?faces-redirect=true";
    }
    
    public String editar(Integer id){
        setObjeto(getDao().localizar(id));
        return "formulario?faces-redirect=true";
    }
    
    public void remover(Integer id){
        setObjeto(getDao().localizar(id));
        if (getDao().remover(getObjeto())){
            Util.mensagemInformacao(getDao().getMensagem());
        } else {
            Util.mensagemErro(getDao().getMensagem());
        }
    }

    public LocatarioDAO getDao() {
        return dao;
    }

    public void setDao(LocatarioDAO dao) {
        this.dao = dao;
    }

    public Locatario getObjeto() {
        return objeto;
    }

    public void setObjeto(Locatario objeto) {
        this.objeto = objeto;
    }
}
