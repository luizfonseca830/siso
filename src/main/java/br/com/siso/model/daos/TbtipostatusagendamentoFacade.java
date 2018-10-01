/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.model.daos;

import br.com.siso.model.entities.Tbtipostatusagendamento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author ioliveira
 */
@Stateless
public class TbtipostatusagendamentoFacade extends AbstractFacade<Tbtipostatusagendamento> {

    public TbtipostatusagendamentoFacade() {
        super(Tbtipostatusagendamento.class);
    }

    /**
     * metodo para carregar todos os tipos de status para o agendamento.
     *
     * @param entityManager
     * @return list of tbtipostatusagendamento
     */
    public List<Tbtipostatusagendamento> findAllTbtipostatusagendamentos(final EntityManager entityManager) {
        return entityManager.createQuery("SELECT t FROM Tbtipostatusagendamento t", Tbtipostatusagendamento.class)
                .getResultList();
    }
}
