/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.model.daos;

import br.com.siso.model.entities.Tbusuario;
import java.util.Date;
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
public class TbusuarioFacade extends AbstractFacade<Tbusuario> {

    public TbusuarioFacade() {
        super(Tbusuario.class);
    }

    public Tbusuario findTbusuarioByLoginandIdusuario(Tbusuario tbusuario, EntityManager em) {
        try {
            return (Tbusuario) em.createQuery("SELECT t FROM Tbusuario t WHERE UPPER(t.nmloginusuario) = :nmloginusuario and t.idusuario != :idusuario")
                    .setParameter("nmloginusuario", tbusuario.getNmloginusuario().toUpperCase())
                    .setParameter("idusuario", tbusuario.getIdusuario())
                    .setMaxResults(1).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Tbusuario findtbusuarioByLogin(Tbusuario tbusuario, EntityManager em) {
        try {
            return (Tbusuario) em.createQuery("SELECT t FROM Tbusuario t WHERE UPPER(t.nmloginusuario) = :nmloginusuario ")
                    .setParameter("nmloginusuario", tbusuario.getNmloginusuario().toUpperCase())
                    .setMaxResults(1).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Tbusuario findTbusuarioByCrachar(Tbusuario tbusuario, EntityManager em) {
        try {
            return (Tbusuario) em.createQuery("SELECT t FROM Tbusuario t WHERE t.nmcracha = :nmcracha")
                    .setParameter("nmcracha", tbusuario.getNmcracha())
                    .getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {
            return null;
        }
    }

    public Tbusuario findTbusuarioByNome(String nome, EntityManager em) {
        Tbusuario result;
        try {
            result = em.createQuery("Select t from Tbusuario t Where t.nmnomeusuario = :nome", Tbusuario.class)
                    .setParameter("nome", nome)
                    .getSingleResult();
        } catch (NoResultException e) {
            result = null;
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    public Tbusuario findTbusuarioByLoginSenha(Tbusuario tbusuario, EntityManager em) {
        try {
            return em.createQuery("SELECT t FROM Tbusuario t WHERE t.nmloginusuario = :nmloginusuario and t.nmsenhausuario = :nmsenhausuario", Tbusuario.class)
                    .setParameter("nmloginusuario", tbusuario.getNmloginusuario())
                    .setParameter("nmsenhausuario", tbusuario.getNmsenhausuario())
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public Tbusuario findTbusuarioByID(Tbusuario tbusuario, EntityManager em) {
        try {
            return (Tbusuario) em.createQuery("SELECT t FROM Tbusuario t WHERE t.idusuario = :idusuario")
                    .setParameter("idusuario", tbusuario.getIdusuario())
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public boolean updateTbusuario(Tbusuario tbusuario, EntityManager em) {
        try {
            tbusuario.setTmdataultimoacesso(new Date());
            edit(tbusuario, em);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * metodo que veirifica se o usuario tem acesso a alterar setup
     *
     * @param tbusuario
     * @param em
     * @return
     */
    public Boolean isAcessaAlteraSetup(Tbusuario tbusuario, EntityManager em) {
        if (em.createQuery("select t from Tbacesso t where t.tbfuncionalidade.txrole = 'AlteraSetup' "
                + " and t.tbusuario.nmloginusuario = :nmloginusuario").setParameter("nmloginusuario", tbusuario.getNmloginusuario()).getResultList().size() > 0) {
            return true;
        }//fim do if
        return false;
    }//fim do metodo isAcessaAlteraSetup

    public List<Tbusuario> searchTbusuarioByCrachar(String cracha, EntityManager em) {
        try {
            return em.createQuery("SELECT t FROM Tbusuario t WHERE t.nmcracha like :nmcracha")
                    .setParameter("nmcracha", "%" + cracha.trim() + "%")
                    .getResultList();
        } catch (NoResultException | NonUniqueResultException ex) {
            return null;
        }
    }

    public List<Tbusuario> searchTbusuarioByNome(String nome, EntityManager em) {
        try {
            return em.createQuery("SELECT t FROM Tbusuario t WHERE lower(trim(t.nmnomeusuario)) like lower(trim(:nmnomeusuario))")
                    .setParameter("nmnomeusuario", "%" + nome.trim() + "%")
                    .getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public List<Tbusuario> searchTbusuarioByLogin(String login, EntityManager em) {
        try {
            return em.createQuery("SELECT t FROM Tbusuario t WHERE lower(trim(t.nmloginusuario)) like lower(trim(:nmloginusuario))")
                    .setParameter("nmloginusuario", "%" + login.trim() + "%")
                    .getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public List<Tbusuario> allUsuarios(EntityManager em) {
        return em.createQuery("SELECT t FROM Tbusuario t ORDER BY t.nmnomeusuario").getResultList();
    }

    public int deleteUser(int idUser, EntityManager em) {
        return em.createQuery("DELETE FROM Tbusuario t WHERE t.idusuario = :idusuario").
                setParameter("idusuario", idUser).executeUpdate();
    }

    public List<Tbusuario> listTbusuarioByNome(String nome, EntityManager em) {
        return em.createQuery("SELECT t FROM Tbusuario t WHERE UPPER (t.nmnomeusuario) LIKE (:nmnomeusuario)", Tbusuario.class)
                .setParameter("nmnomeusuario", nome.toUpperCase() + "%")
                .getResultList();
    }

    public Tbusuario getTbusuarioScrpAutomatico(EntityManager em) {
        try {
            return em.createQuery("SELECT t FROM Tbusuario t WHERE UPPER (t.nmnomeusuario) LIKE ('SCRP AUTOMÁTICO')", Tbusuario.class)
                    .getSingleResult();
        } catch (NoResultException nre) {
            nre.printStackTrace(System.err);
            return null;
        }
    }
}
