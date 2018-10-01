/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.logic.dente;

import br.com.siso.model.daos.TbdenteFacade;
import br.com.siso.model.entities.Tbdente;
import br.com.siso.web.control.module.AbstractModuleCore;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ioliveira
 */
@Stateless
public class DenteLogic extends AbstractModuleCore {

    @EJB
    private TbdenteFacade tbdenteFacade;

    /**
     * metodo utilizado para achar o dente pela posicao.
     *
     * @param nrPosicao
     * @return Tbdente
     */
    public Tbdente findTbdenteByNrPosicao(Integer nrPosicao) {
        return tbdenteFacade.findTbdenteByNrPosicao(nrPosicao, super.getEM());
    }

    /**
     * metodo que carrega a lista de todos os dentes.
     *
     * @return list of Tbdente
     */
    public List<Tbdente> findAllTbdente() {
        return tbdenteFacade.findAllTbdente(super.getEM());
    }
}
