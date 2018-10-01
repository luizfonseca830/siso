/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.model.daos;

import br.com.siso.model.entities.Tbmodulo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author JorgeFonseca
 */
@Stateless
public class TbmoduloFacade extends AbstractFacade<Tbmodulo> {

    public TbmoduloFacade() {
        super(Tbmodulo.class);
    }

    public Tbmodulo findModuloById(int idModulo, EntityManager em) {

        return em.createQuery("select t from Tbmodulo t where t.idmodulo = :idmodulo",Tbmodulo.class)
                .setParameter("idmodulo", idModulo)
                .getSingleResult();
    }

    public List<Tbmodulo> allModulos(EntityManager em) {
        return em.createQuery("SELECT t FROM Tbmodulo t").getResultList();
        
    }

    public List<Tbmodulo> listTbmoduloByIntipo(int intTipo, EntityManager em) {
        return em.createQuery("SELECT t FROM Tbmodulo t WHERE t.intipo = :intipo")
                .setParameter("intipo", intTipo)
                .getResultList();
    }
}
