package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.UnidadeCondominial;
import java.io.Serializable;

/**
 *
 * @author eric_
 */
public class UnidadeCondominialDAO<T> extends DAOGenerico<UnidadeCondominial> implements Serializable {

    public UnidadeCondominialDAO() {
        super();
        classePersistente = UnidadeCondominial.class;
        ordem = "descricao";
    }
    

}
