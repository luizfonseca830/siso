/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.beans.cidade;

import br.com.siso.model.entities.Tbcidade;
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
 * @author JorgeFonseca
 */
@ManagedBean(name = "cidadeBean")
@ViewScoped
public class CidadeBean implements Serializable {

    @EJB
    CidadeLogic cidadeLogic;

    private String nmBairroSearch;
    private Tbcidade tbCidadeSelected;
    private Tbcidade tbcidade;
    private List<Tbcidade> listTbcidades;

    @PostConstruct
    public void init() {
        listTbcidades = cidadeLogic.findAllTbcidade();
        nmBairroSearch = "";
        tbcidade = new Tbcidade();

        int idCidade = AbstractFacesContextUtils.getParamInt("idCidade");
        if (idCidade > 0) {
            tbcidade = cidadeLogic.findTbcidadeById(idCidade);
        }
    }

    /**
     * metodo para fazer a pesquisa por nome.
     */
    public void pesquisarCidadePorNome() {

        if (nmBairroSearch != null && !nmBairroSearch.trim().isEmpty()) {
            listTbcidades = cidadeLogic.findAllTbcidadeByName(nmBairroSearch);
        } else {
            listTbcidades = cidadeLogic.findAllTbcidade();
        }
    }

    /**
     * metodo utilizado para deletar uma cidade.
     *
     */
    public void deletarCidade() {

        try {
            if (cidadeLogic.removerCidade(tbCidadeSelected)) {
                AbstractFacesContextUtils.redirectPage(PagesUrl.URL_CIDADE_LIST);
                AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("cidadedeletadacomsucesso"));
                listTbcidades = cidadeLogic.findAllTbcidade();
            }
        } catch (Exception e) {
            AbstractFacesContextUtils.redirectPage(PagesUrl.URL_CIDADE_LIST);
            AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("erroaoremoveracidade"));
        }

    }

    /**
     * metodo utilizado para cadastrar uma nova cidade.
     */
    public void createCidade() {
        tbcidade.setDtinclusaolog(new Date());
        if (tbcidade != null && tbcidade.getNmcidade() != null && !tbcidade.getNmcidade().trim().isEmpty()) {
            if (cidadeLogic.createCidade(tbcidade)) {
                AbstractFacesContextUtils.redirectPage(PagesUrl.URL_CIDADE_LIST);
                AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("cidadeadicionadacomsucesso"));
            } else {
                AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("erroaoadicionarcidade"));
            }
        } else {
            AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("porfavorverifiqueseasinformacoesestaopreenchidascorretamente"));
        }
    }

    /**
     * Metodo para editar cidade.
     */
    public void editarCidade() {
        tbcidade.setDtatualizacaolog(new Date());
        if (tbcidade != null && tbcidade.getNmcidade() != null && !tbcidade.getNmcidade().trim().isEmpty()) {
            if (cidadeLogic.editarCidade(tbcidade)) {
                AbstractFacesContextUtils.redirectPage(PagesUrl.URL_CIDADE_LIST);
                AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("cidadealteradacomsucesso"));
            } else {
                AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("erroaoalteraracidade"));
            }

        } else {
            AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("porfavorverifiqueseasinformacoesestaopreenchidascorretamente"));
        }
    }

    /**
     * @return the nmBairroSearch
     */
    public String getNmBairroSearch() {
        return nmBairroSearch;
    }

    /**
     * @param nmBairroSearch the nmBairroSearch to set
     */
    public void setNmBairroSearch(String nmBairroSearch) {
        this.nmBairroSearch = nmBairroSearch;
    }

    /**
     * @return the tbCidadeSelected
     */
    public Tbcidade getTbCidadeSelected() {
        return tbCidadeSelected;
    }

    /**
     * @param tbCidadeSelected the tbCidadeSelected to set
     */
    public void setTbCidadeSelected(Tbcidade tbCidadeSelected) {
        this.tbCidadeSelected = tbCidadeSelected;
    }

    /**
     * @return the tbcidade
     */
    public Tbcidade getTbcidade() {
        return tbcidade;
    }

    /**
     * @param tbcidade the tbcidade to set
     */
    public void setTbcidade(Tbcidade tbcidade) {
        this.tbcidade = tbcidade;
    }

    /**
     * @return the listTbcidades
     */
    public List<Tbcidade> getListTbcidades() {
        return listTbcidades;
    }

    /**
     * @param listTbcidades the listTbcidades to set
     */
    public void setListTbcidades(List<Tbcidade> listTbcidades) {
        this.listTbcidades = listTbcidades;
    }

}
