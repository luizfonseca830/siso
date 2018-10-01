/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.model.daos;

import br.com.siso.model.entities.Tbservico;
import br.com.siso.model.entities.Tbservicoface;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author ioliveira
 */
@Stateless
public class TbservicofaceFacade extends AbstractFacade<Tbservicoface> {

    public TbservicofaceFacade() {
        super(Tbservicoface.class);
    }

    /**
     * metodo que recuperar a face do dente pelo procedimento.
     *
     * @param tbservico
     * @param entityManager
     * @return tbservicoface
     */
    public Tbservicoface findTbservicofaceByTbservico(final Tbservico tbservico, final EntityManager entityManager) {

        Tbservicoface tbservicoface = null;

        try {
            tbservicoface = entityManager.createQuery("SELECT t FROM Tbservicoface t WHERE t.idservico = :idServico", Tbservicoface.class)
                    .setParameter("idServico", tbservico)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception e) {
        }

        return tbservicoface;
    }
}
