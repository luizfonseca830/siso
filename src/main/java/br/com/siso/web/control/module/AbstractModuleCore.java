/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.module;

import br.com.siso.model.util.SelectDB;
import javax.ejb.EJB;
import javax.persistence.EntityManager;

/**
 *
 * @author ioliveira
 */
public abstract class AbstractModuleCore {

    @EJB
    private SelectDB selectDB;

    public EntityManager getEM() {
        return selectDB.getEntityManager();
    }

}
