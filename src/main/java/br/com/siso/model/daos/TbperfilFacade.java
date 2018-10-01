/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.siso.model.daos;

import br.com.siso.model.entities.Tbperfil;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author JorgeFonseca
 */
@Stateless
public class TbperfilFacade extends AbstractFacade<Tbperfil> {
    
    public TbperfilFacade() {
        super(Tbperfil.class);
    }
    
    public List<Tbperfil> findAll(EntityManager em) {
        return em.createQuery("SELECT t FROM Tbperfil t").getResultList();
    }

    public List<Tbperfil> listTbperfilByNome(String nome, EntityManager em) {
        return em.createQuery("SELECT t FROM Tbperfil t WHERE UPPER(t.nmperfil) LIKE (:nome)")
                .setParameter("nome", nome.toUpperCase() + "%")
                .getResultList();
    }

    public Tbperfil FindTbperfilByNome(String nome, EntityManager em) {
        Tbperfil result;
        try {
            result = em.createQuery("SELECT t FROM Tbperfil t WHERE t.nmperfil = :nome", Tbperfil.class)
                    .setParameter("nome", nome)
                    .getSingleResult();
        } catch (NoResultException e) {
            result = null;
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    public List<Tbperfil> listTbperfilByIdperfilNmperfil(String idperfilnmperfil, EntityManager em) {
        return em.createQuery("SELECT t FROM Tbperfil t WHERE (t.nmperfil) LIKE (:idperfilnmperfil)", Tbperfil.class)
                .setParameter("idperfilnmperfil", idperfilnmperfil + "%").getResultList();
    }
}
