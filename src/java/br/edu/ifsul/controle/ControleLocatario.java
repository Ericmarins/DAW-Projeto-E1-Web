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
    
    public void novo(){
        setObjeto(new Locatario());
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
            objeto = (Locatario) dao.localizar(id);
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar obejto: " + e.getMessage());
        }
    }

    public void remover(Integer id) {
        try {
            objeto = (Locatario) dao.localizar(id);
            dao.remover(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro a remover objeto: " + e.getMessage());
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
