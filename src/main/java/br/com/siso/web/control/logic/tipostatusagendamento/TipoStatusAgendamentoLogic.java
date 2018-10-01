/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.logic.tipostatusagendamento;

import br.com.siso.model.daos.TbtipostatusagendamentoFacade;
import br.com.siso.model.entities.Tbtipostatusagendamento;
import br.com.siso.web.control.module.AbstractModuleCore;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ioliveira
 */
@Stateless
public class TipoStatusAgendamentoLogic extends AbstractModuleCore {

    @EJB
    private TbtipostatusagendamentoFacade tbtipostatusagendamentoFacade;

    /**
     * metodo para carregar todos os tipos de status para o agendamento.
     *
     * @return list of tbtipostatusagendamento
     */
    public List<Tbtipostatusagendamento> findAllTbtipostatusagendamentos() {
        return tbtipostatusagendamentoFacade.findAllTbtipostatusagendamentos(super.getEM());
    }
}
