/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.logic.servicoface;

import br.com.siso.model.daos.TbservicofaceFacade;
import br.com.siso.model.entities.Tbservico;
import br.com.siso.model.entities.Tbservicoface;
import br.com.siso.web.control.module.AbstractModuleCore;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ioliveira
 */
@Stateless
public class ServicoFaceLogic extends AbstractModuleCore {

    @EJB
    private TbservicofaceFacade tbservicofaceFacade;

    /**
     * metodo que recuperar a face do dente pelo procedimento.
     *
     * @param tbservico
     * @return tbservicoface
     */
    public Tbservicoface findTbservicofaceByTbservico(final Tbservico tbservico) {
        return tbservicofaceFacade.findTbservicofaceByTbservico(tbservico, super.getEM());
    }
}
