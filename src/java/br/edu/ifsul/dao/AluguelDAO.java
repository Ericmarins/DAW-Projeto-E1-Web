
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Aluguel;
import java.io.Serializable;


/**
 *
 * @author eric_
 */

public class AluguelDAO<T> extends DAOGenerico<Aluguel> implements Serializable {
 
    public AluguelDAO() {
        super();        
        classePersistente = Aluguel.class;
    }
    
    
}
