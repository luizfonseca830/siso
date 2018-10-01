/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.model.daos;

import br.com.siso.model.entities.Tbcliente;
import br.com.siso.model.entities.Tborcamento;
import br.com.siso.model.entities.Tbprocedimento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author ioliveira
 */
@Stateless
public class TbprocedimentoFacade extends AbstractFacade<Tbprocedimento> {

    public TbprocedimentoFacade() {
        super(Tbprocedimento.class);
    }

    /**
     * metodo utilizado para carregar a lista de procedimentos para um cliente.
     *
     * @param tbcliente
     * @param entityManager
     * @return list of tbprocedimento
     */
    public List<Tbprocedimento> findAllTbprocedimentosByIdCliente(final Tbcliente tbcliente, final EntityManager entityManager) {
        return entityManager.createQuery("SELECT t FROM Tbprocedimento t WHERE t.idcliente = :idCliente", Tbprocedimento.class)
                .setParameter("idCliente", tbcliente)
                .getResultList();
    }

    /**
     * metodo utilizado para carregar a lista de procedimentos para um cliente e
     * idtipostatusprocedimentos (nao realizado, em tratamento e realizados).
     *
     * @param tbcliente
     * @param entityManager
     * @return list of tbprocedimento
     */
    public List<Tbprocedimento> findAllTbprocedimentosByIdClienteAndIdTipoStatusProcedimentos(final Tbcliente tbcliente, final EntityManager entityManager) {
        return entityManager.createQuery("SELECT t FROM Tbprocedimento t WHERE t.idcliente = :idCliente AND t.idtipostatusprocedimento.intipostatusprocedimento IN (0,1,2)", Tbprocedimento.class)
                .setParameter("idCliente", tbcliente)
                .getResultList();
    }

    /**
     * metodo utilizado para carregar a lista de procedimentos de um orcamento.
     *
     * @param tborcamento
     * @param entityManager
     * @return list of tbprocedimento
     */
    public List<Tbprocedimento> findAllTbprocedimentoByIdOrcamento(final Tborcamento tborcamento, final EntityManager entityManager) {
        return entityManager.createQuery("SELECT t FROM Tbprocedimento t WHERE t.idorcamento = :idOrcamento", Tbprocedimento.class)
                .setParameter("idOrcamento", tborcamento)
                .getResultList();
    }
}
