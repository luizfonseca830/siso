/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.model.daos;

import br.com.siso.model.entities.Tbespecialidade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author JorgeFonseca
 */
@Stateless
public class TbespecialidadeFacade extends AbstractFacade<Tbespecialidade> {

    public TbespecialidadeFacade() {
        super(Tbespecialidade.class);
    }

    /**
     * metodo para carregar todas as especialidade.
     *
     * @param entityManager
     * @return list of tbespecialidade
     */
    public List<Tbespecialidade> findAllTbespecialidades(final EntityManager entityManager) {
        return entityManager.createQuery("SELECT t FROM Tbespecialidade t WHERE t.intipoespecialidade = :inTipoEspecialidade", Tbespecialidade.class)
                .setParameter("inTipoEspecialidade", 1)
                .getResultList();
    }
}
