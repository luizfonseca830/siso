/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.logic.cidade;

import br.com.siso.model.daos.TbcidadeFacade;
import br.com.siso.model.entities.Tbcidade;
import br.com.siso.web.control.module.AbstractModuleCore;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ioliveira
 */
@Stateless
public class CidadeLogic extends AbstractModuleCore {

    @EJB
    private TbcidadeFacade tbcidadeFacade;

    /**
     * metodo para recuperar todas as cidades cadastradas.
     *
     * @return list of cities
     */
    public List<Tbcidade> findAllTbcidade() {
        return tbcidadeFacade.findAllTbcidade(super.getEM());
    }

    /**
     * metodo utilizado na pesquisa de cidade por nome.
     *
     * @param nmCidade
     * @return list of cidade
     */
    public List<Tbcidade> findAllTbcidadeByName(String nmCidade) {
        return tbcidadeFacade.findAllTbcidadeByName(nmCidade, super.getEM());
    }

    /**
     * metodo utilizado para editar as informacoes da cidade.
     *
     * @param tbcidade
     * @return true or false
     */
    public boolean editarCidade(Tbcidade tbcidade) {

        return tbcidadeFacade.edit(tbcidade, super.getEM());
    }

    /**
     * metodo utilizado para remover cidade.
     *
     * @param tbcidade
     * @return true or false
     */
    public boolean removerCidade(Tbcidade tbcidade) {
        return tbcidadeFacade.remove(tbcidade, super.getEM());
    }

    /**
     * metodo utilizado para criar uma nova Cidade.
     *
     * @param tbcidade
     * @return true or false
     */
    public boolean createCidade(final Tbcidade tbcidade) {

        return tbcidadeFacade.create(tbcidade, super.getEM());
    }

    /**
     * metodo utilizado para recuperar o cidade por id.
     *
     * @param idCidade
     * @return Tbcidade
     */
    public Tbcidade findTbcidadeById(final Integer idCidade) {
        return tbcidadeFacade.find(idCidade, super.getEM());
    }
}
