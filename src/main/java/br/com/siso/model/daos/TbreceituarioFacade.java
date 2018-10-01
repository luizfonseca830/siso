/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.model.daos;

import br.com.siso.model.entities.Tbcliente;
import br.com.siso.model.entities.Tbreceituario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author ioliveira
 */
@Stateless
public class TbreceituarioFacade extends AbstractFacade<Tbreceituario> {

    public TbreceituarioFacade() {
        super(Tbreceituario.class);
    }

    /**
     * metodo utilizado para carregar todos os receituarios de um cliente.
     *
     * @param tbcliente
     * @param entityManager
     * @return list of receituario
     */
    public List<Tbreceituario> findAllTbreceituarioByIdCliente(final Tbcliente tbcliente, final EntityManager entityManager) {
        return entityManager.createQuery("SELECT t FROM Tbreceituario t WHERE t.idcliente = :idCliente", Tbreceituario.class)
                .setParameter("idCliente", tbcliente)
                .getResultList();
    }
}
