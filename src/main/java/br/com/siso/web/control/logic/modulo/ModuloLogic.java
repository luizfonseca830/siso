/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.logic.modulo;

import br.com.siso.model.daos.TbmoduloFacade;
import br.com.siso.model.entities.Tbmodulo;
import br.com.siso.web.control.module.AbstractModuleCore;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ioliveira
 */
@Stateless
public class ModuloLogic extends AbstractModuleCore{

    @EJB
    private TbmoduloFacade tbmoduloFacade;
    
    public Tbmodulo findModuloById(int idMOdulo) {
        return tbmoduloFacade.findModuloById(idMOdulo, super.getEM());
    }

    public List<Tbmodulo> allModulos() {
        return tbmoduloFacade.allModulos(super.getEM());
    }

    public List<Tbmodulo> listTbmoduloByIntipo(int intipo) {
        return tbmoduloFacade.listTbmoduloByIntipo(intipo, super.getEM());
    }
    
    public Tbmodulo find(int idmodulo){
        return tbmoduloFacade.find(idmodulo, super.getEM());
    }
    
    public boolean create(Tbmodulo tbmodulo){
        return tbmoduloFacade.create(tbmodulo, super.getEM());
    }
    
    public boolean edit(Tbmodulo tbmodulo){
        return tbmoduloFacade.edit(tbmodulo, super.getEM());
    }
    
    public boolean remove(Tbmodulo tbmodulo){
        return tbmoduloFacade.remove(tbmodulo, super.getEM());
    }
}
