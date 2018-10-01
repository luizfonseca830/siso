/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.logic.subespprofissional;

import br.com.siso.model.daos.TbsubespprofissionalFacade;
import br.com.siso.model.entities.Tbsubespprofissional;
import br.com.siso.web.control.module.AbstractModuleCore;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ioliveira
 */
@Stateless
public class SubEspProfissionalLogic extends AbstractModuleCore {

    @EJB
    private TbsubespprofissionalFacade facade;

    public boolean create(final Tbsubespprofissional tbsubespprofissional) {
        return facade.create(tbsubespprofissional, super.getEM());
    }
}
