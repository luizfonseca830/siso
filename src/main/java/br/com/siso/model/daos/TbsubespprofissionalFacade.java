/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.siso.model.daos;

import br.com.siso.model.entities.Tbsubespprofissional;
import javax.ejb.Stateless;

/**
 *
 * @author JorgeFonseca
 */
@Stateless
public class TbsubespprofissionalFacade extends AbstractFacade<Tbsubespprofissional> {    

    public TbsubespprofissionalFacade() {
        super(Tbsubespprofissional.class);
    }
    
}
