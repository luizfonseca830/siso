/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.beans.perfil;

import br.com.siso.model.entities.Tbperfil;
import br.com.siso.web.control.logic.perfil.PerfilLogic;
import br.com.siso.web.faces.constants.PagesUrl;
import br.com.siso.web.faces.constants.Resources;
import br.com.siso.web.faces.utils.AbstractFacesContextUtils;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author ioliveira
 */
@ViewScoped
@ManagedBean
public class PerfilBean implements Serializable {

    @EJB
    private PerfilLogic perfilLogic;
    private List<Tbperfil> listTbperfil;
    private Tbperfil tbperfil;
    private Tbperfil select;
    private String pesquisar;

    @PostConstruct
    public void init() {
        listTbperfil = perfilLogic.getListTbperfil();
        tbperfil = new Tbperfil();
        int idperfil = AbstractFacesContextUtils.getParamInt("idperfil");
        if (idperfil > 0) {
            tbperfil = perfilLogic.find(idperfil);
        }
    }

    public void search() {
        if (!pesquisar.isEmpty() || getPesquisar() != null) {
            listTbperfil = perfilLogic.getListTbperfilByNome(getPesquisar().toLowerCase());
        }
    }

    public void create() {
        Tbperfil tbperfilTmp = perfilLogic.findTbperfilByNome(tbperfil.getNmperfil());
        if (tbperfilTmp != null) {
            AbstractFacesContextUtils.redirectPage(PagesUrl.URL_PERFIL_LIST);
            AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("falhaaoadicionarperfilperfiljaxiste"));
        } else {
            if (perfilLogic.create(tbperfil)) {
                AbstractFacesContextUtils.redirectPage(PagesUrl.URL_PERFIL_LIST);
                AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("perfiladicionadocomsucesso"));
            }
        }
    }

    public void edit() {
        Tbperfil tbperfilTmp = perfilLogic.findTbperfilByNome(tbperfil.getNmperfil());
        if (tbperfilTmp != null) {
            AbstractFacesContextUtils.redirectPage(PagesUrl.URL_PERFIL_LIST);
            AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("falhaaoeditarperfilperfiljaexiste"));
        } else {
            perfilLogic.edit(tbperfil);
            AbstractFacesContextUtils.redirectPage(PagesUrl.URL_PERFIL_LIST);
            AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("perfileditadocomsucesso"));
        }
    }

    public void remove(Tbperfil tbperfil) {
        try {
            if (perfilLogic.remove(tbperfil)) {
                AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("perfilremovidocomsucesso"));
                listTbperfil = perfilLogic.getListTbperfil();
            }
        } catch (Exception e) {
            AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("falhaaoremoverperfilperfilpodeestarsendousado"));
        }
    }

    public List<Tbperfil> getListTbperfil() {
        return listTbperfil;
    }

    public void setListTbperfil(List<Tbperfil> listTbperfil) {
        this.listTbperfil = listTbperfil;
    }

    public Tbperfil getTbperfil() {
        return tbperfil;
    }

    public void setTbperfil(Tbperfil tbperfil) {
        this.tbperfil = tbperfil;
    }

    public Tbperfil getSelect() {
        return select;
    }

    public void setSelect(Tbperfil select) {
        this.select = select;
    }

    public String getPesquisar() {
        return pesquisar;
    }

    public void setPesquisar(String pesquisar) {
        this.pesquisar = pesquisar;
    }

}
