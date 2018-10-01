/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.beans.bairro;

import br.com.siso.model.entities.Tbbairro;
import br.com.siso.model.entities.Tbcidade;
import br.com.siso.web.control.logic.bairro.BairroLogic;
import br.com.siso.web.control.logic.cidade.CidadeLogic;
import br.com.siso.web.faces.constants.PagesUrl;
import br.com.siso.web.faces.constants.Resources;
import br.com.siso.web.faces.utils.AbstractFacesContextUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author ioliveira
 */
@ManagedBean
@ViewScoped
public class BairroBean implements Serializable {

    @EJB
    private BairroLogic bairroLogic;
    @EJB
    private CidadeLogic cidadeLogic;

    private List<Tbbairro> listTbbairros;
    private List<Tbcidade> listTbcidade;

    private Tbbairro tbbairro;
    private String nmBairro;
    private Tbbairro tbbairroSelected;

    @PostConstruct
    public void init() {
        listTbbairros = bairroLogic.findAllTbbairro();
        listTbcidade = cidadeLogic.findAllTbcidade();
        nmBairro = "";
        tbbairro = new Tbbairro();

        int idBairro = AbstractFacesContextUtils.getParamInt("idBairro");
        if (idBairro > 0) {
            tbbairro = bairroLogic.findTbbairroById(idBairro);
        }
    }

    /**
     * metodo utilizado para pesquisar bairro por nome.
     */
    public void pesquisaBairroPorNome() {

        if (nmBairro != null && !nmBairro.trim().isEmpty()) {
            listTbbairros = bairroLogic.findAllTbbairroByName(nmBairro);
        } else {
            listTbbairros = bairroLogic.findAllTbbairro();
        }

    }

    /**
     * metodo para criar um novo bairro.
     */
    public void createTbbairro() {

        tbbairro.setDtinclusaolog(new Date());

        if (tbbairro != null && tbbairro.getNmbairro() != null && !tbbairro.getNmbairro().trim().isEmpty()) {
            if (bairroLogic.createBairro(tbbairro)) {
                AbstractFacesContextUtils.redirectPage(PagesUrl.URL_BAIRRO_LIST);
                AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("bairroadicionadocomsucesso"));
            } else {
                AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("erroaorealizarcadastrodobairro"));
            }
        } else {
            AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("porfavorverifiqueseasinformacoesestaopreenchidascorretamente"));
        }

    }

    /**
     * metodo para editar um bairro.
     */
    public void editTbbairro() {

        tbbairro.setDtatualizacaolog(new Date());

        if (tbbairro != null && tbbairro.getNmbairro() != null && !tbbairro.getNmbairro().trim().isEmpty()) {
            if (bairroLogic.editarBairro(tbbairro)) {
                AbstractFacesContextUtils.redirectPage(PagesUrl.URL_BAIRRO_LIST);
                AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("bairrofoialteradocomsucesso"));
            } else {
                AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("erroaorealizaralteracaodobairro"));
            }
        } else {
            AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("porfavorverifiqueseasinformacoesestaopreenchidascorretamente"));
        }
    }

    /**
     * metodo para deletar um bairro.
     */
    public void deleteTbbairro() {

        try {
            if (bairroLogic.removerBairro(tbbairroSelected)) {
                AbstractFacesContextUtils.redirectPage(PagesUrl.URL_BAIRRO_LIST);
                AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("bairrodeletadocomsucesso"));
                listTbbairros = bairroLogic.findAllTbbairro();
            }
        } catch (Exception e) {
            AbstractFacesContextUtils.redirectPage(PagesUrl.URL_BAIRRO_LIST);
            AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("erroaoremoverbairro"));
        }
    }

    /**
     * @return the listTbbairros
     */
    public List<Tbbairro> getListTbbairros() {
        return listTbbairros;
    }

    /**
     * @param listTbbairros the listTbbairros to set
     */
    public void setListTbbairros(List<Tbbairro> listTbbairros) {
        this.listTbbairros = listTbbairros;
    }

    /**
     * @return the tbbairro
     */
    public Tbbairro getTbbairro() {
        return tbbairro;
    }

    /**
     * @param tbbairro the tbbairro to set
     */
    public void setTbbairro(Tbbairro tbbairro) {
        this.tbbairro = tbbairro;
    }

    /**
     * @return the nmBairro
     */
    public String getNmBairro() {
        return nmBairro;
    }

    /**
     * @param nmBairro the nmBairro to set
     */
    public void setNmBairro(String nmBairro) {
        this.nmBairro = nmBairro;
    }

    /**
     * @return the tbbairroSelected
     */
    public Tbbairro getTbbairroSelected() {
        return tbbairroSelected;
    }

    /**
     * @param tbbairroSelected the tbbairroSelected to set
     */
    public void setTbbairroSelected(Tbbairro tbbairroSelected) {
        this.tbbairroSelected = tbbairroSelected;
    }

    /**
     * @return the listTbcidade
     */
    public List<Tbcidade> getListTbcidade() {
        return listTbcidade;
    }

    /**
     * @param listTbcidade the listTbcidade to set
     */
    public void setListTbcidade(List<Tbcidade> listTbcidade) {
        this.listTbcidade = listTbcidade;
    }
}
