/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.beans.servico;

import br.com.siso.model.entities.Tbservico;
import br.com.siso.web.control.logic.servico.ServicoLogic;
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
 * @author Jorge Fonseca
 */
@ViewScoped
@ManagedBean
public class ServicoBean implements Serializable {

    @EJB
    private ServicoLogic servicoLogic;
    private List<Tbservico> listTbservico;
    private Tbservico tbservico;
    private Tbservico select;
    private String pesquisar;

    @PostConstruct
    public void init() {
        listTbservico = servicoLogic.getListTbservico();
        tbservico = new Tbservico();
        int idservico = AbstractFacesContextUtils.getParamInt("idservico");
        if (idservico > 0) {
            tbservico = servicoLogic.find(idservico);
        }
    }

    public void search() {
        if (!pesquisar.isEmpty() || getPesquisar() != null) {
            listTbservico = servicoLogic.getListTbservicoByNome(getPesquisar().toLowerCase());
        }
    }

    public void create() {

        if (tbservico.getNmservico().trim().equals("") && tbservico.getNmservico().isEmpty() 
                && tbservico.getNmservico() == null) {
            AbstractFacesContextUtils.redirectPage(PagesUrl.URL_SERVICO_LIST);
            AbstractFacesContextUtils.addMessageWarn((Resources.getMessage("naopodeseradicionadoservicoexistente")));

        } else {
            servicoLogic.create(tbservico);
            AbstractFacesContextUtils.redirectPage(PagesUrl.URL_SERVICO_LIST);
            AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("servicoadicionadocomsucesso"));

        }
    }

    public void edit() {

        if (tbservico != null) {
            AbstractFacesContextUtils.redirectPage(PagesUrl.URL_SERVICO_LIST);
            AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("falhaaoalteraroservicotalvezjaexista"));
        } else {
            servicoLogic.edit(tbservico);
            AbstractFacesContextUtils.redirectPage(PagesUrl.URL_SERVICO_LIST);
            AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("servicoalteradocomsucesso"));
        }

    }

    public void remove() {
        try {

            if (servicoLogic.remove(tbservico)) {
                AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("servicoremovicomsucesso"));
            }
        } catch (Exception e) {

            AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("falhaaoremoverservicotalvezestejaemuso"));
        }
    }

    /**
     * @return the servicoLogic
     */
    public ServicoLogic getServicoLogic() {
        return servicoLogic;
    }

    /**
     * @param servicoLogic the servicoLogic to set
     */
    public void setServicoLogic(ServicoLogic servicoLogic) {
        this.servicoLogic = servicoLogic;
    }

    /**
     * @return the listTbservico
     */
    public List<Tbservico> getListTbservico() {
        return listTbservico;
    }

    /**
     * @param listTbservico the listTbservico to set
     */
    public void setListTbservico(List<Tbservico> listTbservico) {
        this.listTbservico = listTbservico;
    }

    /**
     * @return the tbservico
     */
    public Tbservico getTbservico() {
        return tbservico;
    }

    /**
     * @param tbservico the tbservico to set
     */
    public void setTbservico(Tbservico tbservico) {
        this.tbservico = tbservico;
    }

    /**
     * @return the select
     */
    public Tbservico getSelect() {
        return select;
    }

    /**
     * @param select the select to set
     */
    public void setSelect(Tbservico select) {
        this.select = select;
    }

    /**
     * @return the pesquisar
     */
    public String getPesquisar() {
        return pesquisar;
    }

    /**
     * @param pesquisar the pesquisar to set
     */
    public void setPesquisar(String pesquisar) {
        this.pesquisar = pesquisar;
    }
}
