
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Aluguel;
import java.io.Serializable;


/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 */

public class AluguelDAO<T> extends DAOGenerico<Aluguel> implements Serializable {
 
    public AluguelDAO() {
        super();        
        classePersistente = Aluguel.class;
    }
    
    
}
