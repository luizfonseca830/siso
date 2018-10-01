/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.logic.user;

import br.com.siso.model.daos.TbusuarioFacade;
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
public class UserLogic extends AbstractModuleCore {

    @EJB
    private TbusuarioFacade tbusuarioFacade;

    public Tbusuario findUserById(int idusuario) {
        Tbusuario tbusuario = new Tbusuario();
        tbusuario.setIdusuario(idusuario);
        return tbusuarioFacade.findTbusuarioByID(tbusuario, getEM());
    }

    public Tbusuario findTbusuarioByLoginSenha(Tbusuario tbusuario) {
        Tbusuario tbusuarioTmp = null;
        try {
            if (tbusuario != null && tbusuario.getNmloginusuario() != null && tbusuario.getNmsenhausuario() != null) {
                tbusuarioTmp = tbusuarioFacade.findTbusuarioByLoginSenha(tbusuario, getEM());
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return tbusuarioTmp;
    }

    public boolean editUsuario(Tbusuario tbusuario) {
        return tbusuarioFacade.edit(tbusuario, getEM());
    }

    public List<Tbusuario> listAllUsers() {
        return tbusuarioFacade.allUsuarios(getEM());
    }

    public List<Tbusuario> listTbusuarioByNome(String nome) {
        return tbusuarioFacade.listTbusuarioByNome(nome, super.getEM());
    }

    public List<Tbusuario> searchUser(String valor) {

        List<Tbusuario> list = tbusuarioFacade.searchTbusuarioByNome(valor, getEM());

        if (list == null || list.isEmpty()) {
            list = tbusuarioFacade.searchTbusuarioByLogin(valor, getEM());

            if (list == null || list.isEmpty()) {
                list = tbusuarioFacade.searchTbusuarioByCrachar(valor, getEM());
            }
        }

        return list;
    }

    public boolean create(Tbusuario tbusuario) {
        return tbusuarioFacade.create(tbusuario, getEM());
    }

    public boolean deleteUsuario(Tbusuario tbusuario) {
        return tbusuarioFacade.remove(tbusuario, getEM());
    }

    public boolean editUser(Tbusuario tbusuario) {
        return tbusuarioFacade.edit(tbusuario, getEM());
    }

    public Tbusuario findtbusuarioByLogin(Tbusuario tbusuario) {
        return tbusuarioFacade.findtbusuarioByLogin(tbusuario, getEM());
    }

    public Tbusuario findTbusuarioByLoginandIdusuario(Tbusuario tbusuario) {
        return tbusuarioFacade.findTbusuarioByLoginandIdusuario(tbusuario, getEM());
    }

}
