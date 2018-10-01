/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.model.daos;

import javax.persistence.EntityManager;

/**
 *
 * @author JorgeFonseca
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public boolean create(T entity, EntityManager em) {
        boolean result;
        try {
            em.persist(entity);
            result = true;
        } catch (Exception e) {
            e.printStackTrace(System.err);
            result = false;
        }
        return result;
    }

    public boolean edit(T entity, EntityManager em) {
        boolean result;
        try {
            em.merge(entity);
            result = true;
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean remove(T entity, EntityManager em) {
        boolean result;
        try {
            em.remove(em.merge(entity));
            result = true;
        } catch (Exception e) {
            e.printStackTrace(System.err);
            result = false;
        }
        return result;
    }

    public T find(Object id, EntityManager em) {
        return em.find(entityClass, id);
    }

//    public List<T> findAll(EntityManager em) {
//        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//        cq.select(cq.from(entityClass));
//        return em.createQuery(cq).getResultList();
//    }
}
