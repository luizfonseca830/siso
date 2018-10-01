/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.model.daos;

import br.com.siso.model.entities.Tbcidade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author JorgeFonseca
 */
@Stateless
public class TbcidadeFacade extends AbstractFacade<Tbcidade> {

    public TbcidadeFacade() {
        super(Tbcidade.class);
    }

    /**
     * metodo para recuperar todas as cidades cadastradas.
     *
     * @param entityManager
     * @return list of cities
     */
    public List<Tbcidade> findAllTbcidade(final EntityManager entityManager) {
        return entityManager.createQuery("SELECT t FROM Tbcidade t").getResultList();
    }

    /**
     * metodo utilizado na pesquisa de cidade por nome.
     *
     * @param nmCidade
     * @param entityManager
     * @return list of cidade
     */
    public List<Tbcidade> findAllTbcidadeByName(final String nmCidade, final EntityManager entityManager) {
        return entityManager.createQuery("SELECT t FROM Tbcidade t WHERE UPPER (t.nmcidade) LIKE (:nmCidade)", Tbcidade.class)
                .setParameter("nmCidade", nmCidade.toUpperCase() + "%")
                .getResultList();
    }
}
