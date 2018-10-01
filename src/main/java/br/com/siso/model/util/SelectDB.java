/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.model.util;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ioliveira
 */
@Singleton
public class SelectDB {
    
    @PersistenceContext(unitName = "sisoPU")
    private EntityManager em;

    public EntityManager getEntityManager() {
        return em;
    }
}
