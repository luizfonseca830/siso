/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.model.daos;

import br.com.siso.model.entities.Tbagendamento;
import br.com.siso.web.faces.converter.TimeControl;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author JorgeFonseca
 */
@Stateless
public class TbagendamentoFacade extends AbstractFacade<Tbagendamento> {

    public TbagendamentoFacade() {
        super(Tbagendamento.class);
    }

    /**
     * metodo para carregar a lista de todos os agendamento do dia.
     *
     * @param entityManager
     * @return list of tbagendamento
     */
    public List<Tbagendamento> findAllTbagendamentoFromCurrentDay(final EntityManager entityManager) {

        return entityManager.createQuery("SELECT t FROM Tbagendamento t WHERE t.dtdataagendamento = :dtDataAgendamento ORDER BY t.tmdataagendamento", Tbagendamento.class)
                .setParameter("dtDataAgendamento", TimeControl.getDateIni())
                .getResultList();
    }

    /**
     * metodo utilizado para filtrar os agendamentos por data, cliente e
     * funcionario.
     *
     * @param data
     * @param filtro
     * @param entityManager
     * @return list of agendamento
     */
    public List<Tbagendamento> findAllTbagendamentoByDataAndPacienteAndFuncionario(final Date data, String filtro, final EntityManager entityManager) {

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT t from Tbagendamento t WHERE t.dtdataagendamento = :dtDataAgendamento");
        builder.append(filtro).append(" ORDER BY t.tmdataagendamento ");

        return entityManager.createQuery(builder.toString(), Tbagendamento.class)
                .setParameter("dtDataAgendamento", data)
                .getResultList();
    }

    /**
     * metodo que carrega a lista de todos os agendamentos ordenado por
     * nragendamento.
     *
     * @param entityManager
     * @return list of tbagendamento
     */
    public List<Tbagendamento> findAllTbagendamentosOrderByNrAgendamento(final EntityManager entityManager) {
        return entityManager.createQuery("SELECT t FROM Tbagendamento t ORDER BY t.nragendamento", Tbagendamento.class)
                .getResultList();
    }

    /**
     * metodo para carregar a lista de todos os agendamento do dia com o status
     * de presente para atendimento.
     *
     * @param entityManager
     * @return list of tbagendamento
     */
    public List<Tbagendamento> findAllTbagendamentoDiaAtualComStatusDePresente(final EntityManager entityManager) {

        return entityManager.createQuery("SELECT t FROM Tbagendamento t WHERE t.dtdataagendamento = :dtDataAgendamento AND t.idtipostatusagendamento.intipostatusagendamento = :idTipoStatusAgendamento ORDER BY t.tmdataagendamento", Tbagendamento.class)
                .setParameter("dtDataAgendamento", TimeControl.getDateIni())
                .setParameter("idTipoStatusAgendamento", 2)
                .getResultList();
    }

    /**
     * metodo utilizado para filtrar os agendamentos por data, cliente e
     * funcionario e tipo status presente para atendimento.
     *
     * @param data
     * @param filtro
     * @param entityManager
     * @return list of agendamento
     */
    public List<Tbagendamento> findAllTbagendamentoByDataAndPacienteAndFuncionarioAndStatusPresente(final Date data, String filtro, final EntityManager entityManager) {

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT t from Tbagendamento t WHERE t.dtdataagendamento = :dtDataAgendamento AND t.idtipostatusagendamento.intipostatusagendamento = :idTipoStatusAgendamento");
        builder.append(filtro).append(" ORDER BY t.tmdataagendamento ");

        return entityManager.createQuery(builder.toString(), Tbagendamento.class)
                .setParameter("dtDataAgendamento", data)
                .setParameter("idTipoStatusAgendamento", 2)
                .getResultList();
    }
}
