/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.logic.especialidade;

import br.com.siso.model.daos.TbespecialidadeFacade;
import br.com.siso.model.entities.Tbespecialidade;
import br.com.siso.web.control.module.AbstractModuleCore;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ioliveira
 */
@Stateless
public class EspecialidadeLogic extends AbstractModuleCore {

    @EJB
    private TbespecialidadeFacade tbespecialidadeFacade;

    /**
     * metodo para carregar todas as especialidade.
     *
     * @return list of tbespecialidade
     */
    public List<Tbespecialidade> findAllTbespecialidades() {
        return tbespecialidadeFacade.findAllTbespecialidades(super.getEM());
    }
}
