/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.logic.receituario;

import br.com.siso.model.daos.TbreceituarioFacade;
import br.com.siso.model.entities.Tbcliente;
import br.com.siso.model.entities.Tbreceituario;
import br.com.siso.web.control.module.AbstractModuleCore;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ioliveira
 */
@Stateless
public class ReceituarioLogic extends AbstractModuleCore {

    @EJB
    private TbreceituarioFacade tbreceituarioFacade;

    /**
     * metodo utilizado para carregar todos os receituarios de um cliente.
     *
     * @param tbcliente
     * @return list of receituario
     */
    public List<Tbreceituario> findAllTbreceituarioByIdCliente(final Tbcliente tbcliente) {
        return tbreceituarioFacade.findAllTbreceituarioByIdCliente(tbcliente, super.getEM());
    }
}
