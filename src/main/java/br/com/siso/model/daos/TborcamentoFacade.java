/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.model.daos;

import br.com.siso.model.entities.Tbagendamento;
import br.com.siso.model.entities.Tbcliente;
import br.com.siso.model.entities.Tborcamento;
import br.com.siso.web.faces.constants.StatusSiso;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author JorgeFonseca
 */
@Stateless
public class TborcamentoFacade extends AbstractFacade<Tborcamento> {

    public TborcamentoFacade() {
        super(Tborcamento.class);
    }

    /**
     * metodo para verificar se existe um orcamento para esta data e este
     * agendamento.
     *
     * @param tbagendamento
     * @param entityManager
     * @return tborcamento
     */
    public Tborcamento findTborcamentoByDtDataAndIdAgendamento(final Tbagendamento tbagendamento, final EntityManager entityManager) {
        Tborcamento tborcamento = null;

        try {
            tborcamento = entityManager.createQuery("SELECT t FROM Tborcamento t WHERE t.idagendamento = :idAgendamento AND t.dtdataorcamento = :dtDataAgendamento", Tborcamento.class)
                    .setParameter("idAgendamento", tbagendamento)
                    .setParameter("dtDataAgendamento", tbagendamento.getDtdataagendamento())
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception e) {
        }

        return tborcamento;
    }

    /**
     * metodo para carregar o orcamento do cliente para atendimento.
     *
     * @param tbcliente
     * @param entityManager
     * @return tborcamento
     */
    public Tborcamento findTborcamentoByIdClienteAndStatusAberto(final Tbcliente tbcliente, final EntityManager entityManager) {
        Tborcamento tborcamento = null;

        try {
            tborcamento = entityManager.createQuery("SELECT t FROM Tborcamento t WHERE t.idcliente = :idCliente AND t.idtipostatusorcamento.intipostatusorcamento = :inTipoSTatus ORDER BY t.tmdataorcamento DESC", Tborcamento.class)
                    .setParameter("idCliente", tbcliente)
                    .setParameter("inTipoSTatus", StatusSiso.INT_INTIPO_STATUS_ORCAMENTO_ABERTO)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception e) {
        }

        return tborcamento;
    }

    /**
     * metodo para carregar todos os orcamento do cliente para visualizacao.
     *
     * @param tbcliente
     * @param entityManager
     * @return tborcamento
     */
    public List<Tborcamento> findAllTborcamentoByIdCliente(final Tbcliente tbcliente, final EntityManager entityManager) {
        return entityManager.createQuery("SELECT t FROM Tborcamento t WHERE t.idcliente = :idCliente ORDER BY t.tmdataorcamento DESC", Tborcamento.class)
                .setParameter("idCliente", tbcliente)
                .getResultList();
    }
}
