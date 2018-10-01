/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.siso.model.daos;

import br.com.siso.model.entities.Tbacesso;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

/**
 *
 * @author JorgeFonseca
 */
@Stateless
public class TbacessoFacade extends AbstractFacade<Tbacesso> {    

    public TbacessoFacade() {
        super(Tbacesso.class);
    }
 
    public Tbacesso findByUsuarioFuncionalidade(Tbacesso tbacesso, EntityManager em) {
        try {
            return (Tbacesso) em.createQuery("SELECT t FROM Tbacesso t WHERE  t.tbmodulo.idmodulo = :idmodulo and "
                    + "t.tbusuario.nmloginusuario = :nmloginusuario and t.tbusuario.nmsenhausuario = :nmsenhausuario and "
                    + "t.tbfuncionalidade.nmfuncionalidade = :nmfuncionalidade")
                    .setParameter("nmloginusuario", tbacesso.getIdusuario().getNmloginusuario())
                    .setParameter("nmsenhausuario", tbacesso.getIdusuario().getNmsenhausuario())
                    .setParameter("idmodulo", tbacesso.getIdmodulo().getIdmodulo())
                    .setParameter("nmfuncionalidade", tbacesso.getIdfuncionalidade().getNmfuncionalidade())
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public Tbacesso getPermiteAcesso(Tbacesso tbacesso, EntityManager em) {
        try {
            return (Tbacesso) em.createQuery("select t from Tbacesso t where t.idusuario.idusuario =:idusuario and "
                    + "t.idfuncionalidade.idfuncionalidade = :idfuncionalidade")
                    .setParameter("idusuario", tbacesso.getIdusuario().getIdusuario())
                    .setParameter("idfuncionalidade", tbacesso.getIdfuncionalidade().getIdfuncionalidade())
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public List<Tbacesso> listTbacessoByIdusuario(Tbacesso tbacesso, EntityManager em) {
        try {
            return em.createQuery("SELECT t FROM Tbacesso t WHERE t.idusuario.idusuario = :idusuario order by t.idmenu.insequencia")
                    .setParameter("idusuario", tbacesso.getIdusuario().getIdusuario())
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.err);
            return new ArrayList<Tbacesso>();
        }
    }

    public List<Tbacesso> listTbacessoByIdFuncionalidade(int idFuncionalidade, EntityManager em) {
        try {
            return em.createQuery("SELECT t FROM Tbacesso t WHERE t.idFuncionalidade.idFuncionalidade = :idFuncionalidade")
                    .setParameter("idFuncionalidade", idFuncionalidade)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.err);
            return new ArrayList<Tbacesso>();
        }
    }

    public List<Tbacesso> listUserByIdmenu(int idMenu, EntityManager em) {
        try {
            return em.createQuery("SELECT t FROM Tbacesso t WHERE t.idmenu.idmenu = :idmenu order by t.idmenu.insequencia")
                    .setParameter("idmenu", idMenu)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace(System.err);
            return new ArrayList<Tbacesso>();
        }
    }

    public int deleteByIdUser(int idUser, EntityManager em) {

        return em.createQuery("DELETE FROM Tbacesso t WHERE t.idusuario.idusuario = :idusuario").
                setParameter("idusuario", idUser).executeUpdate();

    }

    public int deleteByIdUserAndIdMenu(int idUser, int idMenu, EntityManager em) {

        return em.createQuery("DELETE FROM Tbacesso t WHERE t.idusuario.idusuario = :idusuario and t.idmenu.idmenu = :idmenu").
                setParameter("idusuario", idUser)
                .setParameter("idmenu", idMenu)
                .executeUpdate();

    }

    public int copyAccessUser(int userIdCopy, int userIdPaste, EntityManager em) {

        StringBuilder querry = new StringBuilder();

        querry.append("insert into tbacesso (idusuario,idmodulo,idmenu)");
        querry.append("select ");
        querry.append(userIdPaste);
        querry.append(",idmodulo,idmenu from tbacesso where idusuario = ");
        querry.append(userIdCopy);
        querry.append(" and idmenu is not null");

        return em.createNativeQuery(querry.toString()).executeUpdate();

    }

    public int deleteByIdUserAndIdFuncionalidade(int idUser, int idfuncinalidade, EntityManager em) {

        return em.createQuery("DELETE FROM Tbacesso t WHERE t.idusuario.idusuario = :idusuario and t.idfuncionalidade.idfuncionalidade = :idfuncionalidade").
                setParameter("idusuario", idUser)
                .setParameter("idfuncionalidade", idfuncinalidade)
                .executeUpdate();

    }
    
    public Tbacesso findTbacessoByIdusuarioAndIdMenu (Tbacesso tbacesso, EntityManager em){
        Tbacesso temp;
        
        try {
            temp = em.createQuery("SELECT t from Tbacesso t WHERE t.idusuario.idusuario=:idusuario AND t.idmenu.idmenu=:idmenu", Tbacesso.class)
                    .setParameter("idusuario", tbacesso.getIdusuario().getIdusuario())
                    .setParameter("idmenu", tbacesso.getIdmenu().getIdmenu())
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception e) {
            temp = null;
        }
        
        return temp;
    }
}
