/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.logic.perfil;




import br.com.siso.model.daos.TbperfilFacade;
import br.com.siso.model.entities.Tbperfil;
import br.com.siso.web.control.module.AbstractModuleCore;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ioliveira
 */
@Stateless
public class PerfilLogic extends AbstractModuleCore {

    @EJB
    private TbperfilFacade tbperfilFacade;

    public List<Tbperfil> getListTbperfil() {
        return tbperfilFacade.findAll(super.getEM());
    }

    public List<Tbperfil> getListTbperfilByNome(String nome) {
        return tbperfilFacade.listTbperfilByNome(nome, super.getEM());
    }

    public Tbperfil findTbperfilByNome(String nome) {
        return tbperfilFacade.FindTbperfilByNome(nome, super.getEM());
    }

    public boolean create(Tbperfil tbperfil) {
        return tbperfilFacade.create(tbperfil, super.getEM());
    }

    public boolean edit(Tbperfil tbperfil) {
        return tbperfilFacade.edit(tbperfil, super.getEM());
    }

    public Tbperfil find(int id) {
        return tbperfilFacade.find(id, super.getEM());
    }

    public boolean remove(Tbperfil tbperfil) {
        return tbperfilFacade.remove(tbperfil, super.getEM());
    }

    public List<Tbperfil> getListTbperfilByNmperfil(String idperfilnmperfil) {
        return tbperfilFacade.listTbperfilByIdperfilNmperfil(idperfilnmperfil, super.getEM());
    }
}
