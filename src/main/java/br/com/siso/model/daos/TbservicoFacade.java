/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.model.daos;

import br.com.siso.model.entities.Tbservico;
import br.com.siso.model.entities.Tbsubespecialidade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author JorgeFonseca
 */
@Stateless
public class TbservicoFacade extends AbstractFacade<Tbservico> {

    public TbservicoFacade() {
        super(Tbservico.class);
    }

    /**
     * metodo para recuperar todos os servico disponiveis para procedimento.
     *
     * @param tbsubespecialidade
     * @param entityManager
     * @return list of tbservico
     */
    public List<Tbservico> findAllTbservicosByIdSubEspecialidade(final Tbsubespecialidade tbsubespecialidade,
            final EntityManager entityManager) {
        return entityManager.createQuery("SELECT t.idservico FROM Tbservicosubespecialidade t WHERE t.idsubespecialidade = :idSubEspecialidade", Tbservico.class)
                .setParameter("idSubEspecialidade", tbsubespecialidade)
                .getResultList();
    }

    /**
     * metodo utilizado para carregar todos os servicos disponiveis.
     *
     * @param entityManager
     * @return list of tbservico
     */
    public List<Tbservico> findAllTbservicoByInTipoServicoAndDtExclusao(final EntityManager entityManager) {
        return entityManager.createQuery("SELECT t FROM Tbservico t WHERE t.intiposervico = :inTipoServico AND t.dtexclusao IS NULL", Tbservico.class)
                .setParameter("inTipoServico", 1)
                .getResultList();
    }

    /**
     * metodo que carrega todos os serviços
     *
     * @para entiyManager
     * @return list of tbservico
     */
    public List<Tbservico> findAll(final EntityManager entityManager) {
        return entityManager.createQuery("SELECT t FROM Tbservico t")
                .getResultList();
    }

    /**
     * metodo que carrega os serviços por nome
     *
     * @para nome
     * @para entityManager
     * @return list of tbservico
     */
    public List<Tbservico> listTbservicoByName(String nome, EntityManager entityManager) {
        return entityManager.createQuery("SELECT t FROM Tbservico t WHERE UPPER (t.nmservico) LIKE (:nome)")
                .setParameter("nome", nome.toUpperCase() + "%")
                .getResultList();
    }

    /**
     * metodo que pesquisar o nome
     *
     * @para nome
     * @para entityManager
     * @return result
     */
    public Tbservico findTbservicoByName(String nome, EntityManager entityManager) {
        Tbservico result;
        try {
            result = entityManager.createQuery("SELECT t FROM Tbservico t WHERE t.nmservico = nome", Tbservico.class)
                    .setParameter("nome", nome)
                    .getSingleResult();
        } catch (NoResultException e) {
            result = null;

        } catch (Exception e) {
            result = null;
        }
        return result;
    }
}
