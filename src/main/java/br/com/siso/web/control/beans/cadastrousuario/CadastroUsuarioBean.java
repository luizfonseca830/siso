/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.beans.cadastrousuario;

import br.com.siso.model.entities.Tbacesso;
import br.com.siso.model.entities.Tbfuncionalidade;
import br.com.siso.model.entities.Tbmenu;
import br.com.siso.model.entities.Tbperfil;
import br.com.siso.model.entities.Tbusuario;
import br.com.siso.web.control.logic.acesso.AcessoLogic;
import br.com.siso.web.control.logic.menu.MenuLogic;
import br.com.siso.web.control.logic.perfil.PerfilLogic;
import br.com.siso.web.control.logic.user.UserLogic;
import br.com.siso.web.faces.constants.PagesUrl;
import br.com.siso.web.faces.constants.Resources;
import br.com.siso.web.faces.converter.MD5;
import br.com.siso.web.faces.utils.AbstractFacesContextUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;

/**
 *
 * @author ioliveira
 */
@ManagedBean(name = "cadastroUsuarioBean")
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

    @EJB
    private UserLogic userLogic;
    @EJB
    private PerfilLogic perfilLogic;
    @EJB
    private AcessoLogic acessoLogic;
    @EJB
    private MenuLogic menuLogic;

    private List<Tbusuario> listUsers;
    private List<Tbacesso> listAcessos;
    private List<Tbfuncionalidade> listFuncionalidades;
    private List<Tbmenu> menus;
    private List<Tbmenu> menusDoUser;
    private DualListModel<Tbmenu> pickList;
    private Tbusuario tbusuario;
    private Tbperfil tbperfil;
    private Tbusuario tbusuariosearch;
    private Tbusuario usuarioSelected;
    private String paramPesquisa;
    private boolean ativo;
    private boolean alterarSenha;
    private String nome;
    private String login;
    private String cracha;
    private String senha;
    private Date tmdataexpiracao;
    private int userId;

    public CadastroUsuarioBean() {
        userId = -1;
    }

    @PostConstruct
    public void init() {
        setListUsers(userLogic.listAllUsers());
        int paramUser = AbstractFacesContextUtils.getParamInt("user");

        if (paramUser > 0) {
            tbusuario = userLogic.findUserById(paramUser);
            setListAcessos(acessoLogic.listTbfuncionalidadeByIdusuario(tbusuario));

            //PickList
            menusDoUser = new ArrayList<>();
            if (!listAcessos.isEmpty() && listAcessos != null) {

                for (Tbacesso aux : listAcessos) {
                    menusDoUser.add(aux.getIdmenu());
                }
                menus = menuLogic.listMenusNotUserAccess(tbusuario.getIdusuario());
            } else {
                setMenus(menuLogic.allMenus());
            }

            pickList = new DualListModel<>(menus, menusDoUser);
            // FIM do PickList
        }
    }

    public void copiarAcesso() {

        if (!listAcessos.isEmpty() && listAcessos != null) {
            for (Tbacesso aux : listAcessos) {
                acessoLogic.deleteAcessByIdUserAndIdMenu(tbusuario.getIdusuario(), aux.getIdmenu().getIdmenu());
            }
        }

        int registro = acessoLogic.copiaAcesso(tbusuariosearch.getIdusuario(), tbusuario.getIdusuario().intValue());

        if (registro > 0) {
            AbstractFacesContextUtils.redirectPage(PagesUrl.URL_CONSULTAR_USER + "?user=" + tbusuario.getIdusuario());
            AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("acessocopiadocomsucesso"));
        } else {
            AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("naofoipossivelcopiaracessoparaousuario") + tbusuario.getNmnomeusuario());
        }

    }

    public void createAccessUser() {
        boolean verifica = true;
        if (!listAcessos.isEmpty() && listAcessos != null) {
            for (Tbacesso aux : listAcessos) {
                acessoLogic.deleteAcessByIdUserAndIdMenu(tbusuario.getIdusuario(), aux.getIdmenu().getIdmenu());
            }

        }

        for (Tbmenu aux : pickList.getTarget()) {
            Tbacesso acesso = new Tbacesso();
            acesso.setIdusuario(tbusuario);
            acesso.setIdmenu(aux);
            acesso.setIdmodulo(aux.getIdmodulo());

            if (acessoLogic.createAcesso(acesso)) {
                verifica = true;
            } else {
                verifica = false;
            }
        }

        if (verifica) {
            Map<String, Object> params = new HashMap<>();
            params.put("user", tbusuario.getIdusuario());
            AbstractFacesContextUtils.redirectPageWithParam(PagesUrl.URL_CONSULTAR_USER, params);
            AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("usuariocriadocomsucesso"));
        } else {
            AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("naofoipossivelcriaracessoparaousuario") + tbusuario.getNmnomeusuario());
        }

    }

    public void editUser() {

        tbusuario.setNmsenhausuario(MD5.generateMD5Hashing(senha));

        if (!tbusuario.getNmnomeusuario().isEmpty() && tbusuario.getNmnomeusuario() != null && !tbusuario.getNmnomeusuario().trim().equals("")) {
            if (!tbusuario.getNmloginusuario().isEmpty() && tbusuario.getNmloginusuario() != null && !tbusuario.getNmloginusuario().trim().equals("")) {
                if (!tbusuario.getNmsenhausuario().isEmpty() && tbusuario.getNmsenhausuario() != null && !tbusuario.getNmsenhausuario().trim().equals("")) {
                    if (userLogic.findTbusuarioByLoginandIdusuario(tbusuario) == null) {
                        if (userLogic.editUser(tbusuario)) {
                            Map<String, Object> params = new HashMap<>();
                            params.put("user", tbusuario.getIdusuario());
                            AbstractFacesContextUtils.redirectPageWithParam(PagesUrl.URL_CONSULTAR_USER, params);
                            AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("usuariofoieditadocomsucesso"));
                        } else {
                            AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("naofoipossiveleditarusuariotentenovamente"));
                        }
                    } else {
                        AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("loginjaexiste"));
                    }
                } else {
                    AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("camposenhaeobrigatorio"));
                }
            } else {
                AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("campologineobrigatorio"));
            }
        } else {
            AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("campousuarioeobrigatorio"));
        }
    }

    public void editUserPadrao() {
        if (!tbusuario.getNmnomeusuario().isEmpty() && tbusuario.getNmnomeusuario() != null && !tbusuario.getNmnomeusuario().trim().equals("")) {
            if (!tbusuario.getNmloginusuario().isEmpty() && tbusuario.getNmloginusuario() != null && !tbusuario.getNmloginusuario().trim().equals("")) {
                if (!tbusuario.getNmsenhausuario().isEmpty() && tbusuario.getNmsenhausuario() != null && !tbusuario.getNmsenhausuario().trim().equals("")) {
                    if (userLogic.findTbusuarioByLoginandIdusuario(tbusuario) == null) {
                        if (userLogic.editUser(tbusuario)) {
                            Map<String, Object> params = new HashMap<>();
                            params.put("user", tbusuario.getIdusuario());
                            AbstractFacesContextUtils.redirectPageWithParam(PagesUrl.URL_USER_PADRAO, params);
                            AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("usuariofoieditadocomsucesso"));
                        } else {
                            AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("naofoipossiveleditarusuariotentenovamente"));
                        }
                    } else {
                        AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("loginjaexiste"));
                    }
                } else {
                    AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("camposenhaeobrigatorio"));
                }
            } else {
                AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("campologineobrigatorio"));
            }
        } else {
            AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("campousuarioeobrigatorio"));
        }
    }

    public void deleteUsuario() {

        acessoLogic.deleteAcessByIdUser(usuarioSelected.getIdusuario());
        try {
            if (userLogic.deleteUsuario(usuarioSelected)) {
                AbstractFacesContextUtils.redirectPage(PagesUrl.URL_USUARIO_LIST);
                AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("usuariodeletadocomsucesso"));
                setListUsers(userLogic.listAllUsers());
            }
        } catch (Exception e) {
            AbstractFacesContextUtils.redirectPage(PagesUrl.URL_USUARIO_LIST);
            AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("naofoipossiveldeletarusuariotentenovamente"));
        }

    }

    public void pesquisar() {

        if (!paramPesquisa.isEmpty() || getParamPesquisa() != null) {
            listUsers = userLogic.searchUser(getParamPesquisa().toLowerCase());
        } else {
            listUsers = userLogic.listAllUsers();
        }
    }

    public void criarUsuario() {

        tbusuario = new Tbusuario();
        tbusuario.setNmnomeusuario(nome);
        tbusuario.setNmloginusuario(login);
        tbusuario.setNmcracha(cracha);
        tbusuario.setNmsenhausuario(MD5.generateMD5Hashing(senha));
        tbusuario.setIdperfil(getTbperfil());
        tbusuario.setBolativo(true);
        tbusuario.setTmdatadeexpiracao(tmdataexpiracao);

        if (tbusuario.getNmnomeusuario() != null && !tbusuario.getNmnomeusuario().trim().isEmpty() && !tbusuario.getNmnomeusuario().trim().equals("null")) {
            if (tbusuario.getNmloginusuario() != null && !tbusuario.getNmloginusuario().trim().isEmpty() && !tbusuario.getNmloginusuario().trim().equals("null")) {
                if (tbusuario.getNmsenhausuario() != null && !tbusuario.getNmsenhausuario().trim().isEmpty() && !tbusuario.getNmsenhausuario().trim().equals("null")) {
                    if (userLogic.findtbusuarioByLogin(tbusuario) == null) {
                        if (userLogic.create(tbusuario)) {
                            AbstractFacesContextUtils.redirectPage(PagesUrl.URL_USUARIO_LIST);
                            AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("usuariogeradocomsucesso"));
                        } else {
                            AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("naofoipossivelcriarnovousuariotentenovamente"));
                        }
                    } else {
                        AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("loginjaexiste"));
                    }
                } else {
                    AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("camposenhaeobrigatorio"));
                }
            } else {
                AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("campologineobrigatorio"));
            }
        } else {
            AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("campousuarioeobrigatorio"));
        }
    }

    public List<Tbusuario> autoComplete(String query) {
        return userLogic.listTbusuarioByNome(query);
    }

    public List<Tbperfil> autoCompleteByPerfil(String query) {
        return perfilLogic.getListTbperfilByNome(query);
    }

    public Tbusuario getTbusuariosearch() {
        return tbusuariosearch;
    }

    public void setTbusuariosearch(Tbusuario tbusuariosearch) {
        this.tbusuariosearch = tbusuariosearch;
    }

    /**
     * @return the listUsers
     */
    public List<Tbusuario> getListUsers() {
        return listUsers;
    }

    /**
     * @param listUsers the listUsers to set
     */
    public void setListUsers(List<Tbusuario> listUsers) {
        this.listUsers = listUsers;
    }

    /**
     * @return the paramPesquisa
     */
    public String getParamPesquisa() {
        return paramPesquisa;
    }

    /**
     * @param paramPesquisa the paramPesquisa to set
     */
    public void setParamPesquisa(String paramPesquisa) {
        this.paramPesquisa = paramPesquisa;
    }

    /**
     * @return the tbusuario
     */
    public Tbusuario getTbusuario() {
        return tbusuario;
    }

    /**
     * @param tbusuario the tbusuario to set
     */
    public void setTbusuario(Tbusuario tbusuario) {
        this.tbusuario = tbusuario;
    }

    /**
     * @return the ativo
     */
    public boolean isAtivo() {
        return ativo;
    }

    /**
     * @param ativo the ativo to set
     */
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the cracha
     */
    public String getCracha() {
        return cracha;
    }

    /**
     * @param cracha the cracha to set
     */
    public void setCracha(String cracha) {
        this.cracha = cracha;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the listAcessos
     */
    public List<Tbacesso> getListAcessos() {
        return listAcessos;
    }

    /**
     * @param listAcessos the listAcessos to set
     */
    public void setListAcessos(List<Tbacesso> listAcessos) {
        this.listAcessos = listAcessos;
    }

    /**
     * @return the listFuncionalidades
     */
    public List<Tbfuncionalidade> getListFuncionalidades() {
        return listFuncionalidades;
    }

    /**
     * @param listFuncionalidades the listFuncionalidades to set
     */
    public void setListFuncionalidades(List<Tbfuncionalidade> listFuncionalidades) {
        this.listFuncionalidades = listFuncionalidades;
    }

    /**
     * @return the menus
     */
    public List<Tbmenu> getMenus() {
        return menus;
    }

    /**
     * @param menus the menus to set
     */
    public void setMenus(List<Tbmenu> menus) {
        this.menus = menus;
    }

    /**
     * @return the menusDoUser
     */
    public List<Tbmenu> getMenusDoUser() {
        return menusDoUser;
    }

    /**
     * @param menusDoUser the menusDoUser to set
     */
    public void setMenusDoUser(List<Tbmenu> menusDoUser) {
        this.menusDoUser = menusDoUser;
    }

    /**
     * @return the pickList
     */
    public DualListModel<Tbmenu> getPickList() {
        return pickList;
    }

    /**
     * @param pickList the pickList to set
     */
    public void setPickList(DualListModel<Tbmenu> pickList) {
        this.pickList = pickList;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the usuarioSelected
     */
    public Tbusuario getUsuarioSelected() {
        return usuarioSelected;
    }

    /**
     * @param usuarioSelected the usuarioSelected to set
     */
    public void setUsuarioSelected(Tbusuario usuarioSelected) {
        this.usuarioSelected = usuarioSelected;
    }

    public boolean isAlterarSenha() {
        return alterarSenha;
    }

    public void setAlterarSenha(boolean alterarSenha) {
        this.alterarSenha = alterarSenha;
    }

    /**
     * @return the tbperfil
     */
    public Tbperfil getTbperfil() {
        return tbperfil;
    }

    /**
     * @param tbperfil the tbperfil to set
     */
    public void setTbperfil(Tbperfil tbperfil) {
        this.tbperfil = tbperfil;
    }

    /**
     * @return the tmdataexpiracao
     */
    public Date getTmdataexpiracao() {
        return tmdataexpiracao;
    }

    /**
     * @param tmdataexpiracao the tmdataexpiracao to set
     */
    public void setTmdataexpiracao(Date tmdataexpiracao) {
        this.tmdataexpiracao = tmdataexpiracao;
    }
}
