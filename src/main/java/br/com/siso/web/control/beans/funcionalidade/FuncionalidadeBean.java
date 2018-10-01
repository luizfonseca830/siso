/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.beans.funcionalidade;


import br.com.siso.model.entities.Tbacesso;
import br.com.siso.model.entities.Tbmenu;
import br.com.siso.model.entities.Tbmodulo;
import br.com.siso.web.control.logic.acesso.AcessoLogic;
import br.com.siso.web.control.logic.menu.MenuLogic;
import br.com.siso.web.control.logic.modulo.ModuloLogic;
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
@ManagedBean(name = "funcionalidadeBean")
@ViewScoped
public class FuncionalidadeBean implements Serializable {

    @EJB
    private MenuLogic menuLogic;
    @EJB
    private ModuloLogic moduleLogic;
    @EJB
    private AcessoLogic acessoLogic;
    
    private List<Tbmenu> allMenuList;
    private List<Tbmodulo> allModulos;
    private List<Tbmenu> filterMenu;
    private List<Tbacesso> listAcessoMenu;
    private List<Tbacesso> listRecebeMenu;
    private int idmodulo;
    private Tbmenu menuSelected;
    private String funcionalidade;
    private String funcionalidadeEnUs;
    private String role;
    private String url;
    private Integer sequencial;
    private Tbmenu tbmenu;
    private Tbacesso tbacesso;

    public FuncionalidadeBean() {
        idmodulo = -1;
    }

    @PostConstruct
    public void init() {
        allMenuList = menuLogic.allMenus();
        allModulos = moduleLogic.allModulos();

        int paraMenu = AbstractFacesContextUtils.getParamInt("menuId");

        if (paraMenu > 0) {
            tbmenu = menuLogic.findMenuById(paraMenu);
            listAcessoMenu = acessoLogic.listUserByIdmenu(tbmenu.getIdmenu());
        }

    }

    public void edit() {

        if (menuLogic.editMenu(tbmenu)) {
            AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("funcionalidadealteradacomsucesso"));
            AbstractFacesContextUtils.redirectPage(PagesUrl.URL_MENU_CONSULTA + "?menuId=" + tbmenu.getIdmenu());
        } else {
            AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("falhaaoalterarfuncionalidade"));
        }

    }

    public void delete(Tbmenu tbmenu) {

        try {
            if (menuLogic.deleteMenu(tbmenu)) {
                AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("funcionalidaderemovidacomsucesso"));
                setAllMenuList(menuLogic.allMenus());
            } else {
                AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("falhaaoremoverfuncionalidade"));
            }
        } catch (Exception e) {
            AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("falhaaoremoverfuncionalidade"));
        }

    }

    public void create() {
        Tbmodulo tbmodulo = new Tbmodulo();
        Tbmenu menu = new Tbmenu();

        menu.setNmmenu(funcionalidade);
        menu.setTxrole(role);
        menu.setInsequencia(getSequencial().shortValue());
        menu.setTxurl(url);
        menu.setNmmenuEnUs(funcionalidadeEnUs);
        tbmodulo.setIdmodulo(idmodulo);
        menu.setIdmodulo(tbmodulo);

        if (menuLogic.createMenu(menu)) {
            AbstractFacesContextUtils.redirectPage(PagesUrl.URL_MENU_LIST);
            AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("funcionalidadecriadacomsucesso"));
        } else {
            AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("falhaaocriarfuncionalidade"));
        }

    }

    public void remove() {
        Tbacesso temporario = acessoLogic.findTbacessoByIdusuarioAndIdMenu(tbacesso);

        if (temporario != null) {
            if (acessoLogic.remove(temporario)) {
                AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("removidocomsucesso"));
                listAcessoMenu = acessoLogic.listUserByIdmenu(tbmenu.getIdmenu());
            } else {
                AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("falhaaoremoverusuario"));
           }
        }
        else{
            AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("falhaaoremoverusuario"));
        }
    }

    /**
     * @return the AllMenuList
     */
    public List<Tbmenu> getAllMenuList() {
        return allMenuList;
    }

    /**
     * @param allMenuList   
     */
    public void setAllMenuList(List<Tbmenu> allMenuList) {
        this.allMenuList = allMenuList;
    }

    /**
     * @return the filterMenu
     */
    public List<Tbmenu> getFilterMenu() {
        return filterMenu;
    }

    /**
     * @param filterMenu the filterMenu to set
     */
    public void setFilterMenu(List<Tbmenu> filterMenu) {
        this.filterMenu = filterMenu;
    }

    /**
     * @return the allModulos
     */
    public List<Tbmodulo> getAllModulos() {
        return allModulos;
    }

    /**
     * @param allModulos the allModulos to set
     */
    public void setAllModulos(List<Tbmodulo> allModulos) {
        this.allModulos = allModulos;
    }

    /**
     * @return the idmodulo
     */
    public int getIdmodulo() {
        return idmodulo;
    }

    /**
     * @param idmodulo the idmodulo to set
     */
    public void setIdmodulo(int idmodulo) {
        this.idmodulo = idmodulo;
    }

    /**
     * @return the funcionalidade
     */
    public String getFuncionalidade() {
        return funcionalidade;
    }

    /**
     * @param funcionalidade the funcionalidade to set
     */
    public void setFuncionalidade(String funcionalidade) {
        this.funcionalidade = funcionalidade;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the sequencial
     */
    public Integer getSequencial() {
        return sequencial;
    }

    /**
     * @param sequencial the sequencial to set
     */
    public void setSequencial(Integer sequencial) {
        this.sequencial = sequencial;
    }

    /**
     * @return the menuSelected
     */
    public Tbmenu getMenuSelected() {
        return menuSelected;
    }

    /**
     * @param menuSelected the menuSelected to set
     */
    public void setMenuSelected(Tbmenu menuSelected) {
        this.menuSelected = menuSelected;
    }

    /**
     * @return the tbmenu
     */
    public Tbmenu getTbmenu() {
        return tbmenu;
    }

    /**
     * @param tbmenu the tbmenu to set
     */
    public void setTbmenu(Tbmenu tbmenu) {
        this.tbmenu = tbmenu;
    }

    /**
     * @return the listAcessoMenu
     */
    public List<Tbacesso> getListAcessoMenu() {
        return listAcessoMenu;
    }

    /**
     * @param listAcessoMenu the listAcessoMenu to set
     */
    public void setListAcessoMenu(List<Tbacesso> listAcessoMenu) {
        this.listAcessoMenu = listAcessoMenu;
    }

    /**
     * @return the listRecebeMenu
     */
    public List<Tbacesso> getListRecebeMenu() {
        return listRecebeMenu;
    }

    /**
     * @param listRecebeMenu the listRecebeMenu to set
     */
    public void setListRecebeMenu(List<Tbacesso> listRecebeMenu) {
        this.listRecebeMenu = listRecebeMenu;

    }

    /**
     * @return the funcionalidadeEnUs
     */
    public String getFuncionalidadeEnUs() {
        return funcionalidadeEnUs;
    }

    /**
     * @param funcionalidadeEnUs the funcionalidadeEnUs to set
     */
    public void setFuncionalidadeEnUs(String funcionalidadeEnUs) {
        this.funcionalidadeEnUs = funcionalidadeEnUs;
    }

    /**
     * @return the tbacesso
     */
    public Tbacesso getTbacesso() {
        return tbacesso;
    }

    /**
     * @param tbacesso the tbacesso to set
     */
    public void setTbacesso(Tbacesso tbacesso) {
        this.tbacesso = tbacesso;
    }
}
