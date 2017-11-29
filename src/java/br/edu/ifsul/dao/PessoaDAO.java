/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Pessoa;
import java.io.Serializable;

/**
 *
 * @author eric_
 */
public class PessoaDAO<T> extends DAOGenerico<Pessoa> implements Serializable {
 
    public PessoaDAO() {
        super();        
        classePersistente = Pessoa.class;
    }
}