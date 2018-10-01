/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.logic.espprofissional;

import br.com.siso.model.daos.TbespprofissionalFacade;
import br.com.siso.model.entities.Tbespprofissional;
import br.com.siso.web.control.module.AbstractModuleCore;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ioliveira
 */
@Stateless
public class EspProfissionalLogic extends AbstractModuleCore {

    @EJB
    private TbespprofissionalFacade facade;

    public boolean create(final Tbespprofissional tbespprofissional) {
        return facade.create(tbespprofissional, super.getEM());
    }
}
