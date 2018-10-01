/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.logic.orcamento;

import br.com.siso.model.daos.TborcamentoFacade;
import br.com.siso.model.entities.Tbagendamento;
import br.com.siso.model.entities.Tbcliente;
import br.com.siso.model.entities.Tborcamento;
import br.com.siso.web.control.module.AbstractModuleCore;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ioliveira
 */
@Stateless
public class OrcamentoLogic extends AbstractModuleCore {

    @EJB
    private TborcamentoFacade tborcamentoFacade;

    /**
     * metodo para verificar se existe um orcamento para esta data e este
     * agendamento.
     *
     * @param tbagendamento
     * @return tborcamento
     */
    public Tborcamento findTborcamentoByDtDataAndIdAgendamento(final Tbagendamento tbagendamento) {
        return tborcamentoFacade.findTborcamentoByDtDataAndIdAgendamento(tbagendamento, super.getEM());
    }

    /**
     * metodo utilizado para editar informacoes do orcamento.
     *
     * @param tborcamento
     * @return true or false
     */
    public boolean editTborcamento(final Tborcamento tborcamento) {
        return tborcamentoFacade.edit(tborcamento, super.getEM());

    }

    /**
     * metodo para carregar o orcamento do cliente para atendimento.
     *
     * @param tbcliente
     * @return tborcamento
     */
    public Tborcamento findTborcamentoByIdClienteAndStatusAberto(final Tbcliente tbcliente) {
        return tborcamentoFacade.findTborcamentoByIdClienteAndStatusAberto(tbcliente, super.getEM());
    }

    /**
     * metodo para carregar todos os orcamento do cliente para visualizacao.
     *
     * @param tbcliente
     * @return tborcamento
     */
    public List<Tborcamento> findAllTborcamentoByIdCliente(final Tbcliente tbcliente) {
        return tborcamentoFacade.findAllTborcamentoByIdCliente(tbcliente, super.getEM());
    }

    public boolean createTborcamento(final Tborcamento tborcamento) {
        return tborcamentoFacade.create(tborcamento, super.getEM());

    }
}
