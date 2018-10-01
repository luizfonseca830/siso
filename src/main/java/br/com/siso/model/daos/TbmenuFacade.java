/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.siso.model.daos;

import br.com.siso.model.entities.Tbmenu;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author JorgeFonseca
 */
@Stateless
public class TbmenuFacade extends AbstractFacade<Tbmenu> {
    
    public TbmenuFacade() {
        super(Tbmenu.class);
    }
 
    public List<Tbmenu> listAllMenu(EntityManager em) {
        List<Tbmenu> list = new ArrayList<Tbmenu>();
        list = em.createQuery("select t from Tbmenu t order by t.idmodulo.nmmodulo ").getResultList();
        return list;
    }

    public Tbmenu findMenuByIdMenu(int idMenu, EntityManager em) {

        return (Tbmenu) em.createQuery("select t from Tbmenu t where t.idmenu = :idmenu")
                .setParameter("idmenu", idMenu).
                getSingleResult();
    }

    public List<Tbmenu> listTbmenuUserNotAccess(int idUser, EntityManager em) {
        StringBuilder query = new StringBuilder();
        query.append(" select * from");
        query.append(" tbmenu inner join tbmodulo on tbmenu.idmodulo = tbmodulo.idmodulo where not exists (select * from tbacesso where tbacesso.idmenu = tbmenu.idmenu and tbacesso.idusuario = ");
        query.append(idUser);
        query.append(") order by tbmodulo.nmmodulo");
        return em.createNativeQuery(query.toString(), Tbmenu.class).getResultList();
    }

    public Tbmenu findTbmenuByTxurl(String txurl, EntityManager em) {
        try {
            return (Tbmenu) em.createQuery("SELECT t FROM Tbmenu t WHERE t.txurl = :txurl")
                    .setParameter("txurl", txurl)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
