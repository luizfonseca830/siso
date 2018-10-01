/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.siso.model.daos;

import br.com.siso.model.entities.Tbservicosubespecialidade;
import javax.ejb.Stateless;

/**
 *
 * @author JorgeFonseca
 */
@Stateless
public class TbservicosubespecialidadeFacade extends AbstractFacade<Tbservicosubespecialidade> {
    
    public TbservicosubespecialidadeFacade() {
        super(Tbservicosubespecialidade.class);
    }
    
}
