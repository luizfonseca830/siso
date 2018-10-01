/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.beans.cliente;

import br.com.siso.model.entities.Tbbairro;
import br.com.siso.model.entities.Tbcidade;
import br.com.siso.model.entities.Tbcliente;
import br.com.siso.web.control.logic.bairro.BairroLogic;
import br.com.siso.web.control.logic.cidade.CidadeLogic;
import br.com.siso.web.control.logic.cliente.ClienteLogic;
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
@ManagedBean(name = "clienteBean")
@ViewScoped
public class ClienteBean implements Serializable {

    @EJB
    private ClienteLogic clienteLogic;
    @EJB
    private CidadeLogic cidadeLogic;
    @EJB
    private BairroLogic bairroLogic;

    private String nmClienteSearch;
    private Tbcliente tbClienteSelected;
    private Tbcliente tbcliente;

    private List<Tbcliente> listTbclientes;
    private List<Tbbairro> listTbbairros;
    private List<Tbcidade> listTbcidades;

    @PostConstruct
    public void init() {
        listTbclientes = clienteLogic.findAllTbcliente();
        listTbbairros = bairroLogic.findAllTbbairro();
        listTbcidades = cidadeLogic.findAllTbcidade();

        nmClienteSearch = "";
        tbcliente = new Tbcliente();

        int idCliente = AbstractFacesContextUtils.getParamInt("idCliente");
        if (idCliente > 0) {
            tbcliente = clienteLogic.findTbclienteById(idCliente);
        }
    }

    /**
     * metodo para fazer a pesquisa por nome.
     */
    public void pesquisarClientePorNome() {

        if (nmClienteSearch != null && !nmClienteSearch.trim().isEmpty()) {
            listTbclientes = clienteLogic.findAllTbclienteByName(nmClienteSearch);
        } else {
            listTbclientes = clienteLogic.findAllTbcliente();
        }
    }

    /**
     * metodo utilizado para deletar um usuario.
     *
     */
    public void deletarCliente() {

        try {
            if (clienteLogic.deletarCliente(tbClienteSelected)) {
                AbstractFacesContextUtils.redirectPage(PagesUrl.URL_CLIENTE_LIST);
                AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("clientedeletadocomsucesso"));
                listTbclientes = clienteLogic.findAllTbcliente();
            }
        } catch (Exception e) {
            AbstractFacesContextUtils.redirectPage(PagesUrl.URL_CLIENTE_LIST);
            AbstractFacesContextUtils.addMessageWarn(Resources.getField("erroaoremovercliente"));
        }

    }

    /**
     * metodo utilizado para cadastrar um novo cliente.
     */
    public void createClienteMethod() {

        if (clienteLogic.validaCamposCliente(tbcliente)) {
            if (clienteLogic.createCliente(tbcliente)) {
                AbstractFacesContextUtils.redirectPage(PagesUrl.URL_CLIENTE_LIST);
                AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("clienteadicionadocomsucesso"));
            } else {
                AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("erroaorealizarcadastrodocliente"));
            }
        } else {
            AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("porfavorverifiqueseasinformacoesestaopreenchidascorretamente"));
        }
    }

    /**
     * metodo utilizado para editar informações do cliente.
     */
    public void editarCliente() {

        if (clienteLogic.validaCamposCliente(tbcliente)) {
            if (clienteLogic.editarCliente(tbcliente)) {
                AbstractFacesContextUtils.redirectPage(PagesUrl.URL_CLIENTE_LIST);
                AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("clientefoialteradocomsucesso"));
            } else {
                AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("erroaorealizaralteracaodocliente"));
            }
        } else {
            AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("porfavorverifiqueseasinformacoesestaopreenchidascorretamente"));
        }
    }

    /**
     * metodo utilizado para carregar a lista de bairro por cidade.
     */
    public void atualizaListBairroByCidade() {
        if (tbcliente != null) {
            if (tbcliente.getIdcidade() != null) {
                listTbbairros = clienteLogic.findAllTbairrobytTbcidade(tbcliente.getIdcidade());
            } else {
                AbstractFacesContextUtils.addMessageWarn("Nenhuma cidade foi selecionada.");
            }
        }
    }

    /**
     * @return the nmClienteSearch
     */
    public String getNmClienteSearch() {
        return nmClienteSearch;
    }

    /**
     * @param nmClienteSearch the nmClienteSearch to set
     */
    public void setNmClienteSearch(String nmClienteSearch) {
        this.nmClienteSearch = nmClienteSearch;
    }

    /**
     * @return the listTbclientes
     */
    public List<Tbcliente> getListTbclientes() {
        return listTbclientes;
    }

    /**
     * @param listTbclientes the listTbclientes to set
     */
    public void setListTbclientes(List<Tbcliente> listTbclientes) {
        this.listTbclientes = listTbclientes;
    }

    /**
     * @return the tbClienteSelected
     */
    public Tbcliente getTbClienteSelected() {
        return tbClienteSelected;
    }

    /**
     * @param tbClienteSelected the tbClienteSelected to set
     */
    public void setTbClienteSelected(Tbcliente tbClienteSelected) {
        this.tbClienteSelected = tbClienteSelected;
    }

    /**
     * @return the tbcliente
     */
    public Tbcliente getTbcliente() {
        return tbcliente;
    }

    /**
     * @param tbcliente the tbcliente to set
     */
    public void setTbcliente(Tbcliente tbcliente) {
        this.tbcliente = tbcliente;
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
