/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.model.daos;

import br.com.siso.model.entities.Tbcliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author JorgeFonseca
 */
@Stateless
public class TbclienteFacade extends AbstractFacade<Tbcliente> {

    public TbclienteFacade() {
        super(Tbcliente.class);
    }

    /**
     * metodo para carregar todos os clientes cadastrados.
     *
     * @param entityManager
     * @return list of tbcliente
     */
    public List<Tbcliente> findAllTbcliente(final EntityManager entityManager) {
        return entityManager.createQuery("SELECT t FROM Tbcliente t").getResultList();
    }

    /**
     * metodo que recupera a lista de todos os clientes por nome.
     *
     * @param name
     * @param entityManager
     * @return list of tbcliente by name
     */
    public List<Tbcliente> findAllTbclienteByName(String name, final EntityManager entityManager) {
        return entityManager.createQuery("SELECT t FROM Tbcliente t WHERE UPPER (t.nmcliente) LIKE (:nmCliente)", Tbcliente.class)
                .setParameter("nmCliente", name.toUpperCase() + "%")
                .getResultList();
    }

    /**
     * metodo para carregar todos os clientes cadastrados ordenados por
     * nrcodcliente.
     *
     * @param entityManager
     * @return list of tbcliente
     */
    public List<Tbcliente> findAllTbclienteOrderByNrCodCliente(final EntityManager entityManager) {
        return entityManager.createQuery("SELECT t FROM Tbcliente t ORDER BY t.nrcodcliente").getResultList();
    }
}
