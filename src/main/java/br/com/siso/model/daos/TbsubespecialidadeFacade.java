/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.model.daos;

import br.com.siso.model.entities.Tbespecialidade;
import br.com.siso.model.entities.Tbsubespecialidade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author JorgeFonseca
 */
@Stateless
public class TbsubespecialidadeFacade extends AbstractFacade<Tbsubespecialidade> {

    public TbsubespecialidadeFacade() {
        super(Tbsubespecialidade.class);
    }

    /**
     * metodo para carregar todas as subespecialidades.
     *
     * @param entityManager
     * @return list of tbsubespecialidade
     */
    public List<Tbsubespecialidade> findAllTbsubespecialidades(final EntityManager entityManager) {
        return entityManager.createQuery("SELECT t FROM Tbsubespecialidade t", Tbsubespecialidade.class)
                .getResultList();
    }

    /**
     * metodo que carrega todas as sub especialidades por especialidade.
     *
     * @param tbespecialidade
     * @param entityManager
     * @return list of tbsubespecialidade
     */
    public List<Tbsubespecialidade> findAllTbsubespecialidadesByTbespecialidade(final Tbespecialidade tbespecialidade,
            final EntityManager entityManager) {
        return entityManager.createQuery("SELECT t FROM Tbsubespecialidade t WHERE t.idespecialidade = :idEspecialidade", Tbsubespecialidade.class)
                .setParameter("idEspecialidade", tbespecialidade)
                .getResultList();
    }
}
