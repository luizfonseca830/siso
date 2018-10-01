/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.beans.modulo;

import br.com.siso.model.entities.Tbmodulo;
import br.com.siso.web.control.logic.modulo.ModuloLogic;
import br.com.siso.web.faces.constants.PagesUrl;
import br.com.siso.web.faces.constants.Resources;
import br.com.siso.web.faces.utils.AbstractFacesContextUtils;
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
public class ModuloBean {

    @EJB
    private ModuloLogic moduloLogic;
    private List <Tbmodulo> listTbmodulo;
    private Tbmodulo tbmodulo;
    private List <Tbmodulo> filteredmodulo;

    @PostConstruct
    public void init() {
        setTbmodulo(new Tbmodulo());
        setListTbmodulo(moduloLogic.allModulos());
        int idmodulo = AbstractFacesContextUtils.getParamInt("idmodulo");
        if (idmodulo > 0) {
            setTbmodulo(moduloLogic.find(idmodulo));
        }
    }

    public void create() {
        if (moduloLogic.create(getTbmodulo())) {
            AbstractFacesContextUtils.redirectPage(PagesUrl.URL_MODULO_LIST);
            AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("modulocriadocomsucesso"));
        } else {
            AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("falhaaoadicionaromodulo"));
        }
    }

    public void edit() {
        if (getTbmodulo() != null) {
            if (moduloLogic.edit(getTbmodulo())) {
                AbstractFacesContextUtils.redirectPage(PagesUrl.URL_MODULO_LIST);
                AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("moduloeditadocomsucesso"));
            } else {
                AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("falhaaoeditaromodulo"));
            }
        }
    }

    public void remove() {
        if (moduloLogic.remove(tbmodulo)) {
            AbstractFacesContextUtils.redirectPage(PagesUrl.URL_MODULO_LIST);
            AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("moduloremovidocomsucesso"));
        } else {
            AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("falhaaoremoveomodulo"));
        }

    }

    /**
     * @return the listTbmodulo
     */
    public List<Tbmodulo> getListTbmodulo() {
        return listTbmodulo;
    }

    /**
     * @param listTbmodulo the listTbmodulo to set
     */
    public void setListTbmodulo(List<Tbmodulo> listTbmodulo) {
        this.listTbmodulo = listTbmodulo;
    }

    /**
     * @return the tbmodulo
     */
    public Tbmodulo getTbmodulo() {
        return tbmodulo;
    }

    /**
     * @param tbmodulo the tbmodulo to set
     */
    public void setTbmodulo(Tbmodulo tbmodulo) {
        this.tbmodulo = tbmodulo;
    }

    /**
     * @return the filteredmodulo
     */
    public List <Tbmodulo> getFilteredmodulo() {
        return filteredmodulo;
    }

    /**
     * @param filteredmodulo the filteredmodulo to set
     */
    public void setFilteredmodulo(List <Tbmodulo> filteredmodulo) {
        this.filteredmodulo = filteredmodulo;
    }
}
