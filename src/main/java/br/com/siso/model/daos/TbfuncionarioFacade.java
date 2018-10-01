/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.model.daos;

import br.com.siso.model.entities.Tbfuncionario;
import br.com.siso.model.entities.Tbsubespecialidade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author JorgeFonseca
 */
@Stateless
public class TbfuncionarioFacade extends AbstractFacade<Tbfuncionario> {

    public TbfuncionarioFacade() {
        super(Tbfuncionario.class);
    }

    /**
     * metodo que carrega todos os funcionario.
     *
     * @param entityManager
     * @return
     */
    public List<Tbfuncionario> findAllTbfuncionario(final EntityManager entityManager) {
        return entityManager.createQuery("SELECT t FROM Tbfuncionario t", Tbfuncionario.class)
                .getResultList();
    }

    /**
     * metodo utilizado para carregar a lista de todos os funcionarios por
     * subespecialidade.
     *
     * @param tbsubespecialidade
     * @param entityManager
     * @return list of tbfuncionario
     */
    public List<Tbfuncionario> findAllTbfuncionarioBySubEspecialidade(final Tbsubespecialidade tbsubespecialidade,
            final EntityManager entityManager) {
        return entityManager.createQuery("SELECT t.idfuncionario FROM Tbsubespprofissional t WHERE t.idsubespecialidade = :idSubEspecialidade", Tbfuncionario.class)
                .setParameter("idSubEspecialidade", tbsubespecialidade)
                .getResultList();
    }
    /**
     * Metodo que recupera a lista de todos os funcionarios por nome 
     * @param name
     * @param entityManager   
     * @return    
     */
    public List<Tbfuncionario> findAllTbfuncionarioByName(String name, final EntityManager entityManager) {
        return entityManager.createQuery("SELECT t FROM Tbfuncionario t WHERE UPPER (t.nmfuncionario) LIKE (:nmfuncionario) ", Tbfuncionario.class)
                .setParameter("nmfuncionario", name.toUpperCase() + "%")
                .getResultList();
    }
}
