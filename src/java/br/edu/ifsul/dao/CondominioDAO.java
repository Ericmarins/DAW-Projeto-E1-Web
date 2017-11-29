package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Condominio;
import java.io.Serializable;

/**
 *
 * @author eric_
 */
public class CondominioDAO<T> extends DAOGenerico<Condominio> implements Serializable {
 
    public CondominioDAO() {
        super();        
        classePersistente = Condominio.class;
    } 
}
