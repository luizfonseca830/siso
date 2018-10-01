/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.logic.acesso;

import br.com.siso.model.daos.TbacessoFacade;
import br.com.siso.model.entities.Tbacesso;
import br.com.siso.model.entities.Tbusuario;
import br.com.siso.web.control.module.AbstractModuleCore;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ioliveira
 */
@Stateless
public class AcessoLogic extends AbstractModuleCore {

    @EJB
    private TbacessoFacade tbacessoFacade;

    public List<Tbacesso> listUserByIdmenu(int idMenu) {
        return tbacessoFacade.listUserByIdmenu(idMenu, getEM());
    }

    public List<Tbacesso> listTbacessoByIdusuario(Tbacesso tbacesso) {
        return tbacessoFacade.listTbacessoByIdusuario(tbacesso, getEM());
    }

    public List<Tbacesso> listTbacessoByIdFuncionalidade(int idFuncionalidade) {
        return tbacessoFacade.listTbacessoByIdFuncionalidade(idFuncionalidade, getEM());
    }

    public List<Tbacesso> listTbfuncionalidadeByIdusuario(Tbusuario tbusuario) {
        Tbacesso tbacesso = new Tbacesso();
        tbacesso.setIdusuario(tbusuario);
        return tbacessoFacade.listTbacessoByIdusuario(tbacesso, getEM());
    }

    public int deleteAcessByIdUserAndIdMenu(int idUser, int idMenu) {
        return tbacessoFacade.deleteByIdUserAndIdMenu(idUser, idMenu, getEM());
    }

    public int deleteAcessByIdUser(int idUser) {
        return tbacessoFacade.deleteByIdUser(idUser, getEM());
    }

    public boolean createAcesso(Tbacesso tbacesso) {
        return tbacessoFacade.create(tbacesso, getEM());
    }

    public boolean editAcesso(Tbacesso tbacesso) {
        return tbacessoFacade.edit(tbacesso, getEM());
    }

    public int copiaAcesso(int userIdCopy, int userIdPaste) {
        return tbacessoFacade.copyAccessUser(userIdCopy, userIdPaste, getEM());
    }

    public Tbacesso findTbacessoByIsUserFuncionalidade(Tbacesso tbacesso) {
        return tbacessoFacade.getPermiteAcesso(tbacesso, getEM());
    }

    public int deleteAcessByIdFuncionalidade(int idFunc, int idUser) {
        return tbacessoFacade.deleteByIdUserAndIdFuncionalidade(idFunc, idUser, getEM());
    }

    public boolean remove(Tbacesso tbacesso) {
        return tbacessoFacade.remove(tbacesso, super.getEM());
    }

    public Tbacesso findTbacessoByIdusuarioAndIdMenu(Tbacesso tbacesso) {
        return tbacessoFacade.findTbacessoByIdusuarioAndIdMenu(tbacesso, super.getEM());
    }
}
