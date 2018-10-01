/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.beans.menu;

import br.com.siso.model.entities.Tbacesso;
import br.com.siso.model.entities.Tbmenu;
import br.com.siso.model.entities.Tbmodulo;
import br.com.siso.model.entities.Tbusuario;
import br.com.siso.model.entities.custom.MenuItem;
import br.com.siso.model.entities.custom.MenuModel;
import br.com.siso.web.control.locale.LocaleController;
import br.com.siso.web.control.logic.acesso.AcessoLogic;
import br.com.siso.web.control.logic.modulo.ModuloLogic;
import br.com.siso.web.faces.constants.PagesUrl;
import br.com.siso.web.faces.utils.AbstractFacesContextUtils;
import br.com.siso.web.faces.utils.Shareds;
import java.io.IOException;
import java.io.Serializable;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author ioliveira
 */
@ManagedBean
@ViewScoped
public class MenuBean implements Serializable {

    @EJB
    private AcessoLogic acessoLogic;
    @EJB
    private ModuloLogic moduloLogic;

    /**
     *
     */
    private List<MenuModel> model;
    /**
     * Lista de acessos que o usuario tem
     */
    private final List<String> listRules;
    /**
     * Cookie que define se o menu vai ficar visível ou oculto
     */
    private static final String COOKIE_MENU = "COOKIEMENU";
    private List<Tbacesso> listTbacessos;
    private List<Tbmodulo> listTbmodulos;
    private Tbusuario tbusuario;
    private String nmmenu;

    /**
     * Creates a new instance of MenuBean
     */
    public MenuBean() {
        model = new LinkedList<>();
        listRules = new LinkedList<>();
    }

    @PostConstruct
    public void init() {

        ArrayList<Tbmenu> listTbmenus = new ArrayList<>();
        LocaleController localeController = (LocaleController) AbstractFacesContextUtils.getSessionValue("localeController");
        final String locale = localeController.getCurrentLocale().getLanguage();
        
        if (model.isEmpty()) {

            tbusuario = Shareds.getUser();

            if (tbusuario != null) {

                listTbacessos = acessoLogic.listTbfuncionalidadeByIdusuario(tbusuario);
                listTbmodulos = moduloLogic.listTbmoduloByIntipo(0);
                HashMap<String, ArrayList<Tbmenu>> listMenu = new HashMap<>();

                for (final Tbmodulo tbmodulo : listTbmodulos) {
                    switch (locale) {
                        case "pt":
                            if (!listMenu.containsKey(tbmodulo.getNmmodulo())) {
                                listMenu.put(tbmodulo.getNmmodulo(), new ArrayList<Tbmenu>());
                            }
                            break;
                        case "en":
                            if (!listMenu.containsKey(tbmodulo.getNmmoduloEnUs())) {
                                listMenu.put(tbmodulo.getNmmoduloEnUs(), new ArrayList<Tbmenu>());
                            }
                            break;
                    }

                }
                for (Tbacesso tbacesso : listTbacessos) {
                    if (tbacesso.getIdmenu() != null) {

                        switch (locale) {
                            case "pt":
                                listTbmenus = listMenu.get(tbacesso.getIdmenu().getIdmodulo().getNmmodulo());
                                break;
                            case "en":
                                listTbmenus = listMenu.get(tbacesso.getIdmenu().getIdmodulo().getNmmoduloEnUs());
                                break;
                        }

                        if (listTbmenus != null) {
                            listTbmenus.add(tbacesso.getIdmenu());

                            switch (locale) {
                                case "pt":
                                    listMenu.put(tbacesso.getIdmenu().getIdmodulo().getNmmodulo(), listTbmenus);
                                    break;
                                case "en":
                                    listMenu.put(tbacesso.getIdmenu().getIdmodulo().getNmmoduloEnUs(), listTbmenus);
                                    break;
                            }

                        }
                        listRules.add(tbacesso.getIdmenu().getTxrole());
                    }
                }
                for (Map.Entry<String, ArrayList<Tbmenu>> entry : listMenu.entrySet()) {
                    String menu = entry.getKey();
                    ArrayList<Tbmenu> lstMenu = entry.getValue();
                    if (!lstMenu.isEmpty()) {
                        MenuModel submenu = new MenuModel();
                        submenu.setStrMenu(menu);
                        for (Tbmenu tbmenu : lstMenu) {
                            if (tbmenu.getTxurl() != null) {
                                MenuItem item = new MenuItem();

                                switch (locale) {
                                    case "pt":
                                        item.setStrDescription(tbmenu.getNmmenu());
                                        break;
                                    case "en":
                                        item.setStrDescription(tbmenu.getNmmenuEnUs());
                                        break;
                                }

                                item.setStrUrl(tbmenu.getTxurl());
                                submenu.getListMenuItems().add(item);
                            }
                        }
                        model.add(submenu);
                    }
                }
            }
        }
    }

    public List<String> autoComplete(String query) {
        List<String> menuItemsencontrados = new ArrayList<>();
        String srtItemAux;
        query = Normalizer.normalize(query, Normalizer.Form.NFD);
        query = query.replaceAll("[^\\p{ASCII}]", "");
        for (MenuModel submenu : model) {
            for (MenuItem item : submenu.getListMenuItems()) {
                srtItemAux = item.getStrDescription();
                srtItemAux = Normalizer.normalize(srtItemAux, Normalizer.Form.NFD);
                srtItemAux = srtItemAux.replaceAll("[^\\p{ASCII}]", "");
                if (srtItemAux.toUpperCase().contains(query.toUpperCase())) {
                    menuItemsencontrados.add(item.getStrDescription());
                }
            }
        }
        return menuItemsencontrados;
    }

    public void redirectToPage() {
        for (MenuModel submenu : model) {
            for (MenuItem item : submenu.getListMenuItems()) {
                if (item.getStrDescription().equals(nmmenu)) {
                    if (item.getStrUrl().startsWith("http")) {
                        redirectToPageHttp(item.getStrUrl());
                    } else {
                        AbstractFacesContextUtils.redirectPage(item.getStrUrl());
                    }
                }
            }
        }
        nmmenu = "";
    }

    public void redirectToPageHttp(String strUrl) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect(strUrl);
        } catch (IOException ex) {
            Logger.getLogger(MenuBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isRule(String requestRule) {
        boolean result = false;
        for (String rule : listRules) {
            if (rule.equals(requestRule)) {
                result = true;
            }
        }
        if (!result) {
            AbstractFacesContextUtils.redirectPage(PagesUrl.URL_USER_NOT_AUTHORIZED);
        }
        return result;
    }

    public boolean isRuleAccess(String requestRule) {
        boolean result = false;
        for (String rule : listRules) {
            if (rule.equals(requestRule)) {
                result = true;
            }
        }
        return result;
    }

    public void defineDisplayMenu() {
        String value = getDisplayMenu();
        if (value.isEmpty()) {
            AbstractFacesContextUtils.setCookie(COOKIE_MENU, "block");
        } else {
            if ("block".equals(value)) {
                AbstractFacesContextUtils.setCookie(COOKIE_MENU, "none");
            } else {
                AbstractFacesContextUtils.setCookie(COOKIE_MENU, "block");
            }
        }
    }

    public String getDisplayMenu() {
        String value = AbstractFacesContextUtils.getCookie(COOKIE_MENU);
        return value;
    }

    /**
     * @return the model
     */
    public List<MenuModel> getModel() {
        Collections.sort(model, new Comparator<MenuModel>() {
            @Override
            public int compare(MenuModel o1, MenuModel o2) {
                return o1.getStrMenu().compareTo(o2.getStrMenu());
            }
        });
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(List<MenuModel> model) {
        this.model = model;
    }

    /**
     * @return the nmmenu
     */
    public String getNmmenu() {
        return nmmenu;
    }

    /**
     * @param nmmenu the nmmenu to set
     */
    public void setNmmenu(String nmmenu) {
        this.nmmenu = nmmenu;
    }
}
