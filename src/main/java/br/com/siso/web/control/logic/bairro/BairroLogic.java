/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.logic.bairro;

import br.com.siso.model.daos.TbbairroFacade;
import br.com.siso.model.entities.Tbbairro;
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
public class BairroLogic extends AbstractModuleCore {

    @EJB
    private TbbairroFacade tbbairroFacade;

    /**
     * metodo para recuperar todos os bairros.
     *
     * @return list of bairros.
     */
    public List<Tbbairro> findAllTbbairro() {
        return tbbairroFacade.findAllTbbairro(super.getEM());
    }

    /**
     * metodo utilizado para filtrar os bairros por nome.
     *
     * @param nmBairro
     * @return list of tbbairro
     */
    public List<Tbbairro> findAllTbbairroByName(String nmBairro) {
        return tbbairroFacade.findAllTbbairroByName(nmBairro, super.getEM());
    }

    /**
     * metodo utilizado para editar um determinado bairro.
     *
     * @param tbbairro
     * @return true or false
     */
    public boolean editarBairro(final Tbbairro tbbairro) {
        return tbbairroFacade.edit(tbbairro, super.getEM());
    }

    /**
     * metodo utilizado para remover um determinado bairro.
     *
     * @param tbbairro
     * @return true or false
     */
    public boolean removerBairro(final Tbbairro tbbairro) {
        return tbbairroFacade.remove(tbbairro, super.getEM());
    }

    /**
     * metodo utilizado para criar um novo bairro.
     *
     * @param tbbairro
     * @return true or false
     */
    public boolean createBairro(final Tbbairro tbbairro) {
        return tbbairroFacade.create(tbbairro, super.getEM());
    }

    /**
     * metodo utilizado para recuperar bairro por id.
     *
     * @param idBairro
     * @return tbbairro
     */
    public Tbbairro findTbbairroById(final Integer idBairro) {
        return tbbairroFacade.find(idBairro, super.getEM());
    }

    /**
     * metodo que carrega todas os bairro por cidade.
     *
     * @param tbcidade
     * @return list of tbsubespecialidade
     */
    public List<Tbbairro> findAllTbbairroByTbcidade(final Tbcidade tbcidade) {
        return tbbairroFacade.findAllTbbairroByTbcidade(tbcidade, super.getEM());
    }
}
