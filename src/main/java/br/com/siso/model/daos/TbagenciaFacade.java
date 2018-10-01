/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.siso.model.daos;

import br.com.siso.model.entities.Tbagencia;
import javax.ejb.Stateless;

/**
 *
 * @author JorgeFonseca
 */
@Stateless
public class TbagenciaFacade extends AbstractFacade<Tbagencia> {    

    public TbagenciaFacade() {
        super(Tbagencia.class);
    }
    
}
