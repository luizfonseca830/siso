/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.logic.subespecialidade;

import br.com.siso.model.daos.TbsubespecialidadeFacade;
import br.com.siso.model.entities.Tbespecialidade;
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
public class SubEspecialidadeLogic extends AbstractModuleCore {

    @EJB
    private TbsubespecialidadeFacade tbsubespecialidadeFacade;

    /**
     * metodo para carregar todas as subespecialidades.
     *
     * @return list of tbsubespecialidade
     */
    public List<Tbsubespecialidade> findAllTbsubespecialidades() {
        return tbsubespecialidadeFacade.findAllTbsubespecialidades(super.getEM());
    }

    /**
     * metodo que carrega todas as sub especialidades por especialidade.
     *
     * @param tbespecialidade
     * @return list of tbsubespecialidade
     */
    public List<Tbsubespecialidade> findAllTbsubespecialidadesByTbespecialidade(final Tbespecialidade tbespecialidade) {
        return tbsubespecialidadeFacade.findAllTbsubespecialidadesByTbespecialidade(tbespecialidade, super.getEM());
    }
}
