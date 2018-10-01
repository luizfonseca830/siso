/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.model.daos;

import br.com.siso.model.entities.Tbbairro;
import br.com.siso.model.entities.Tbcidade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author JorgeFonseca
 */
@Stateless
public class TbbairroFacade extends AbstractFacade<Tbbairro> {

    public TbbairroFacade() {
        super(Tbbairro.class);
    }

    /**
     * metodo para recuperar todos os bairros.
     *
     * @param entityManager
     * @return list of bairros.
     */
    public List<Tbbairro> findAllTbbairro(final EntityManager entityManager) {
        return entityManager.createQuery("SELECT t FROM Tbbairro t").getResultList();
    }

    /**
     * metodo utilizado para filtrar os bairros por nome.
     *
     * @param nmBairro
     * @param entityManager
     * @return list of tbbairro
     */
    public List<Tbbairro> findAllTbbairroByName(String nmBairro, final EntityManager entityManager) {
        return entityManager.createQuery("SELECT t FROM Tbbairro t WHERE UPPER (t.nmbairro) LIKE (:nmBairro)", Tbbairro.class)
                .setParameter("nmBairro", nmBairro.toUpperCase() + "%")
                .getResultList();
    }

    public List<Tbbairro> findAllTbbairroByTbcidade(final Tbcidade tbcidade, final EntityManager entityManager) {
        return entityManager.createQuery("SELECT t FROM Tbbairro t WHERE t.idcidade = :idcidade", Tbbairro.class)
                .setParameter("idcidade", tbcidade)
                .getResultList();
    }
}
