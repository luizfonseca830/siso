/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.beans.funcionario;

import br.com.siso.model.entities.Tbespecialidade;
import br.com.siso.model.entities.Tbespprofissional;
import br.com.siso.model.entities.Tbfuncionario;
import br.com.siso.model.entities.Tbsubespecialidade;
import br.com.siso.model.entities.Tbsubespprofissional;
import br.com.siso.web.control.logic.funcionario.FuncionarioLogic;
import br.com.siso.web.faces.constants.PagesUrl;
import br.com.siso.web.faces.constants.Resources;
import br.com.siso.web.faces.utils.AbstractFacesContextUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author JorgeFonseca
 */
@ManagedBean(name = "funcionarioBean")
@ViewScoped
public class FuncionarioBean implements Serializable {

    @EJB
    private FuncionarioLogic funcionariologic;

    private String nmFuncionarioSearch;
    private Tbfuncionario tbFuncionarioSelect;
    private Tbfuncionario tbfuncionario;
    private List<Tbfuncionario> listTbfuncionario;
    private boolean funcionario;
    private List<Tbespecialidade> listTbespecialidades;
    private List<Tbsubespecialidade> listTbsubespecialidades;
    private Tbespecialidade tbespecialidadeSelected;
    private Tbsubespecialidade tbsubespecialidadeSelected;

    @PostConstruct
    public void init() {
        listTbfuncionario = funcionariologic.findAllTbfuncionario();
        listTbespecialidades = funcionariologic.findAllTbespecialidades();
        listTbsubespecialidades = funcionariologic.findAllTbsubespecialidades();
        nmFuncionarioSearch = "";
        tbfuncionario = new Tbfuncionario();

        int idFuncionario = AbstractFacesContextUtils.getParamInt("idFuncionario");
        if (idFuncionario > 0) {
            tbfuncionario = funcionariologic.findTbfuncionarioById(idFuncionario);
        }
    }

    /**
     * Metodo para pesquisar o funcionario pelo nome.
     */
    public void pesquisarFuncionarioProNome() {
        if (nmFuncionarioSearch != null && !nmFuncionarioSearch.trim().isEmpty()) {
            listTbfuncionario = funcionariologic.findAllTbfuncionarioByName(nmFuncionarioSearch);
        } else {
            listTbfuncionario = funcionariologic.findAllTbfuncionario();
        }
    }

    /**
     * Metodo utilizado para deletar funcionario.
     */
    public void deletarFuncionario() {
        try {
            if (funcionariologic.removeFuncionario(tbFuncionarioSelect)) {
                AbstractFacesContextUtils.redirectPage(PagesUrl.URL_FUNCIONARIO_LIST);
                AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("funcionarioremovidocomsucesso"));
                listTbfuncionario = funcionariologic.findAllTbfuncionario();
            }
        } catch (Exception e) {
            AbstractFacesContextUtils.redirectPage(PagesUrl.URL_FUNCIONARIO_LIST);
            AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("falhaaoremoverfuncionario"));
        }
    }

    /**
     * Metodo para cadastrar funcionario.
     */
    public void createFuncionario() {

        if (funcionariologic.ValidarCamposFuncionarios(tbfuncionario)) {
            tbfuncionario.setNmlogradouro("");
            tbfuncionario.setNrmatricula("");
            tbfuncionario.setNrnumero("");
            tbfuncionario.setNrrg("");
            if (funcionariologic.createFuncionario(tbfuncionario)) {
                try {
                    if (funcionario) {
                        insertProfessionalSpecialities();
                        insertProfessionalSubSpeciality();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                AbstractFacesContextUtils.redirectPage(PagesUrl.URL_FUNCIONARIO_LIST);
                AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("funcionarioadicionadocomsucesso"));
            } else {
                AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("falhaaoadicionarfuncionario"));
            }
        } else {
            AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("porfavorverifiqueseasinformacoesestaopreenchidascorretamente"));
        }
    }

    private void insertProfessionalSpecialities() {
        Tbespprofissional tbespprofissional = new Tbespprofissional();
        tbespprofissional.setDtatualizacaolog(new Date());
        tbespprofissional.setDtinclusaolog(new Date());
        tbespprofissional.setIdespecialidade(tbespecialidadeSelected);
        tbespprofissional.setIdfuncionario(tbfuncionario);
        funcionariologic.create(tbespprofissional);
    }

    private void insertProfessionalSubSpeciality() {
        Tbsubespprofissional tbsubespprofissional = new Tbsubespprofissional();
        tbsubespprofissional.setDtatualizacaolog(new Date());
        tbsubespprofissional.setDtinclusaolog(new Date());
        tbsubespprofissional.setIdfuncionario(tbfuncionario);
        tbsubespprofissional.setIdsubespecialidade(tbsubespecialidadeSelected);
        funcionariologic.create(tbsubespprofissional);
    }

    /**
     * Metodo para editar funcionario.
     */
    public void editarFuncionario() {
        if (funcionariologic.ValidarCamposFuncionarios(tbfuncionario)) {
            if (funcionariologic.editFuncionario(tbfuncionario)) {
                AbstractFacesContextUtils.redirectPage(PagesUrl.URL_FUNCIONARIO_LIST);
                AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("funcionarioeditadocomsucesso"));
            } else {
                AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("falhaaoremoverfuncionario"));
            }
        } else {
            AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("porfavorverifiqueseasinformacoesestaopreenchidascorretamente"));
        }
    }

    /**
     * methos is used to test checkbox ajax response.
     */
    public void addMessage() {
        String summary = funcionario ? "Checked" : "Unchecked";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));

        /**
         * se valor for true entra aqui
         */
        if (funcionario) {

        } /**
         * quando for false entra aqui
         */
        else {

        }
    }

    /**
     * metodo utilizado para carregar a lista de sub especialidade por
     * especialidade.
     */
    public void atualizaListSubEspecialidadeByEspecialidade() {
        if (tbespecialidadeSelected != null
                && tbespecialidadeSelected.getIdespecialidade() != null) {
            listTbsubespecialidades = funcionariologic.findAllTbsubespecialidadesByTbespecialidade(tbespecialidadeSelected);
        } else {
            AbstractFacesContextUtils.addMessageWarn("Nenhuma especialidade não selecionada.");
        }
    }

    /**
     * @return the nmFuncionarioSearch
     */
    public String getNmFuncionarioSearch() {
        return nmFuncionarioSearch;
    }

    /**
     * @param nmFuncionarioSearch the nmFuncionarioSearch to set
     */
    public void setNmFuncionarioSearch(String nmFuncionarioSearch) {
        this.nmFuncionarioSearch = nmFuncionarioSearch;
    }

    /**
     * @return the tbFuncionarioSelect
     */
    public Tbfuncionario getTbFuncionarioSelect() {
        return tbFuncionarioSelect;
    }

    /**
     * @param tbFuncionarioSelect the tbFuncionarioSelect to set
     */
    public void setTbFuncionarioSelect(Tbfuncionario tbFuncionarioSelect) {
        this.tbFuncionarioSelect = tbFuncionarioSelect;
    }

    /**
     * @return the tbfuncionario
     */
    public Tbfuncionario getTbfuncionario() {
        return tbfuncionario;
    }

    /**
     * @param tbfuncionario the tbfuncionario to set
     */
    public void setTbfuncionario(Tbfuncionario tbfuncionario) {
        this.tbfuncionario = tbfuncionario;
    }

    /**
     * @return the listTbfuncionario
     */
    public List<Tbfuncionario> getListTbfuncionario() {
        return listTbfuncionario;
    }

    /**
     * @param listTbfuncionario the listTbfuncionario to set
     */
    public void setListTbfuncionario(List<Tbfuncionario> listTbfuncionario) {
        this.listTbfuncionario = listTbfuncionario;
    }

    /**
     * @return the funcionario
     */
    public boolean isFuncionario() {
        return funcionario;
    }

    /**
     * @param funcionario the funcionario to set
     */
    public void setFuncionario(boolean funcionario) {
        this.funcionario = funcionario;
    }

    /**
     * @return the listTbespecialidades
     */
    public List<Tbespecialidade> getListTbespecialidades() {
        return listTbespecialidades;
    }

    /**
     * @param listTbespecialidades the listTbespecialidades to set
     */
    public void setListTbespecialidades(List<Tbespecialidade> listTbespecialidades) {
        this.listTbespecialidades = listTbespecialidades;
    }

    /**
     * @return the listTbsubespecialidades
     */
    public List<Tbsubespecialidade> getListTbsubespecialidades() {
        return listTbsubespecialidades;
    }

    /**
     * @param listTbsubespecialidades the listTbsubespecialidades to set
     */
    public void setListTbsubespecialidades(List<Tbsubespecialidade> listTbsubespecialidades) {
        this.listTbsubespecialidades = listTbsubespecialidades;
    }

    /**
     * @return the tbespecialidadeSelected
     */
    public Tbespecialidade getTbespecialidadeSelected() {
        return tbespecialidadeSelected;
    }

    /**
     * @param tbespecialidadeSelected the tbespecialidadeSelected to set
     */
    public void setTbespecialidadeSelected(Tbespecialidade tbespecialidadeSelected) {
        this.tbespecialidadeSelected = tbespecialidadeSelected;
    }

    /**
     * @return the tbsubespecialidadeSelected
     */
    public Tbsubespecialidade getTbsubespecialidadeSelected() {
        return tbsubespecialidadeSelected;
    }

    /**
     * @param tbsubespecialidadeSelected the tbsubespecialidadeSelected to set
     */
    public void setTbsubespecialidadeSelected(Tbsubespecialidade tbsubespecialidadeSelected) {
        this.tbsubespecialidadeSelected = tbsubespecialidadeSelected;
    }

}
