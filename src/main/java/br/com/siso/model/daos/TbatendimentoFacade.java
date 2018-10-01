/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.model.daos;

import br.com.siso.model.entities.Tbagendamento;
import br.com.siso.model.entities.Tbatendimento;
import br.com.siso.model.entities.Tbcliente;
import br.com.siso.web.faces.constants.StatusSiso;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author ioliveira
 */
@Stateless
public class TbatendimentoFacade extends AbstractFacade<Tbatendimento> {

    public TbatendimentoFacade() {
        super(Tbatendimento.class);
    }

    /**
     * metodo utilizado para verificar se ja existe um atendimento para o
     * agendamento.
     *
     * @param tbagendamento
     * @param entityManager
     * @return tbatendimento
     */
    public Tbatendimento findTbatendimentoByDtDataAtendimentoAndIdAgendamento(final Tbagendamento tbagendamento,
            final EntityManager entityManager) {

        Tbatendimento tbatendimento = null;

        try {
            tbatendimento = entityManager.createQuery("SELECT t FROM Tbatendimento t WHERE t.idagendamento = :idAgendamento AND t.dtdataatendimento = :dtDataAgendamento", Tbatendimento.class)
                    .setParameter("idAgendamento", tbagendamento)
                    .setParameter("dtDataAgendamento", tbagendamento.getDtdataagendamento())
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception e) {
        }

        return tbatendimento;
    }

    /**
     * metodo utilizado para carregar todas as consultas com status de atendido
     * para um determinado cliente.
     *
     * @param tbcliente
     * @param entityManager
     * @return list of tbatendimento
     */
    public List<Tbatendimento> findAllTbatendimentosByIdClienteAndStatusAtendido(final Tbcliente tbcliente,
            final EntityManager entityManager) {
        return entityManager.createQuery("SELECT t FROM Tbatendimento t WHERE t.idagendamento.idcliente = :idCliente AND t.idagendamento.idtipostatusagendamento.intipostatusagendamento = :inTipoStatus", Tbatendimento.class)
                .setParameter("idCliente", tbcliente)
                .setParameter("inTipoStatus", StatusSiso.INT_INTIPO_STATUS_AGENDAMENTO_ATENDIDO)
                .getResultList();
    }

}
