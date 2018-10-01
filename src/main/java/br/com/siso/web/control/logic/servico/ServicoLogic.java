/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.logic.servico;

import br.com.siso.model.daos.TbservicoFacade;
import br.com.siso.model.entities.Tbservico;
import br.com.siso.model.entities.Tbsubespecialidade;
import br.com.siso.web.control.module.AbstractModuleCore;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ioliveira
 */
@Stateless
public class ServicoLogic extends AbstractModuleCore {

    @EJB
    private TbservicoFacade tbservicoFacade;

    public Tbservico find(int id) {

        return tbservicoFacade.find(id, super.getEM());
    }

    /**
     * metodo para recuperar todos os servico disponiveis para procedimento.
     *
     * @param tbsubespecialidade
     * @return list of tbservico
     */
    public List<Tbservico> findAllTbservicosByIdSubEspecialidade(final Tbsubespecialidade tbsubespecialidade) {
        return tbservicoFacade.findAllTbservicosByIdSubEspecialidade(tbsubespecialidade, super.getEM());
    }

    /**
     * metodo utilizado para carregar todos os servicos disponiveis.
     *
     * @return list of tbservico
     */
    public List<Tbservico> findAllTbservicoByInTipoServicoAndDtExclusao() {
        return tbservicoFacade.findAllTbservicoByInTipoServicoAndDtExclusao(super.getEM());
    }

    /**
     * metodo utilizado para carrega todos os serviços
     * @return
     */
    public List<Tbservico> getListTbservico() {

        return tbservicoFacade.findAll(super.getEM());
    }

    /**
     * metodo utilizado para carrega os serviços por nome
     *
     * @param nome
     * @return 
     */
    public List<Tbservico> getListTbservicoByNome(String nome) {
        return tbservicoFacade.listTbservicoByName(nome, super.getEM());
    }

    /**
     * metodo para pesquisar por nome
     *
     * @param nome
     * @return
     */
    public Tbservico findTbservicoByNome(String nome) {
        return tbservicoFacade.findTbservicoByName(nome, super.getEM());
    }

    public boolean create(Tbservico tbservico) {
        return tbservicoFacade.create(tbservico, super.getEM());
    }

    public boolean edit(Tbservico tbservico) {
        return tbservicoFacade.edit(tbservico, super.getEM());
    }

    public boolean remove(Tbservico tbservico) {
        return tbservicoFacade.remove(tbservico, super.getEM());
    }

}
