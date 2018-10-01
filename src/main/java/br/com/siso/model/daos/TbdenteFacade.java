/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.model.daos;

import br.com.siso.model.entities.Tbdente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author ioliveira
 */
@Stateless
public class TbdenteFacade extends AbstractFacade<Tbdente> {

    public TbdenteFacade() {
        super(Tbdente.class);
    }

    /**
     * metodo utilizado para achar o dente pela posicao.
     *
     * @param nrPosicao
     * @param entityManager
     * @return Tbdente
     */
    public Tbdente findTbdenteByNrPosicao(Integer nrPosicao, final EntityManager entityManager) {

        Tbdente tbdente = null;

        try {
            tbdente = entityManager.createQuery("SELECT t FROM Tbdente t WHERE t.nrposicao = :nrPosicao", Tbdente.class)
                    .setParameter("nrPosicao", nrPosicao)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception e) {
        }

        return tbdente;
    }

    /**
     * metodo que carrega a lista de todos os dentes.
     *
     * @param entityManager
     * @return list of Tbdente
     */
    public List<Tbdente> findAllTbdente(final EntityManager entityManager) {
        return entityManager.createQuery("SELECT t FROM Tbdente t ", Tbdente.class)
                .getResultList();
    }
}
