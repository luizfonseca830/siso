/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.logic.cliente;

import br.com.siso.model.daos.TbclienteFacade;
import br.com.siso.model.entities.Tbbairro;
import br.com.siso.model.entities.Tbcidade;
import br.com.siso.model.entities.Tbcliente;
import br.com.siso.web.control.logic.bairro.BairroLogic;
import br.com.siso.web.control.module.AbstractModuleCore;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ioliveira
 */
@Stateless
public class ClienteLogic extends AbstractModuleCore {

    @EJB
    private TbclienteFacade tbclienteFacade;
    @EJB
    private BairroLogic bairroLogic;

    /**
     * metodo para carregar todos os clientes cadastrados.
     *
     * @return list of tbcliente
     */
    public List<Tbcliente> findAllTbcliente() {
        return tbclienteFacade.findAllTbcliente(super.getEM());
    }

    /**
     *
     * @param name
     * @return list of tbcliente by name
     */
    public List<Tbcliente> findAllTbclienteByName(String name) {
        return tbclienteFacade.findAllTbclienteByName(name, super.getEM());
    }

    /**
     * metodo para deletar um cliente.
     *
     * @param tbcliente
     * @return true or false
     */
    public boolean deletarCliente(final Tbcliente tbcliente) {
        return tbclienteFacade.remove(tbcliente, super.getEM());
    }

    /**
     * metodo utilizado para criar um novo cliente.
     *
     * @param tbcliente
     * @return true or false
     */
    public boolean createCliente(final Tbcliente tbcliente) {

        tbcliente.setBobloqueado(false);
        tbcliente.setBonaoassociado(false);
        tbcliente.setBoestrangeiro(false);
        tbcliente.setDtinclusao(new Date());
        tbcliente.setDtinclusaolog(new Date());
        tbcliente.setNrcodcliente(genarateCodeClient());

        return tbclienteFacade.create(tbcliente, super.getEM());
    }

    /**
     * metodo utilizado para editar um determinado cliente.
     *
     * @param tbcliente
     * @return
     */
    public boolean editarCliente(final Tbcliente tbcliente) {

        tbcliente.setDtalteracao(new Date());
        tbcliente.setDtatualizacaolog(new Date());

        return tbclienteFacade.edit(tbcliente, super.getEM());
    }

    /**
     * metodo utilizado para recuperar o cliente por id.
     *
     * @param idCliente
     * @return tbcliente
     */
    public Tbcliente findTbclienteById(final Integer idCliente) {
        return tbclienteFacade.find(idCliente, super.getEM());
    }

    /**
     * metodo para carregar todos os clientes cadastrados ordenados por
     * nrcodcliente.
     *
     * @return list of tbcliente
     */
    public List<Tbcliente> findAllTbclienteOrderByNrCodCliente() {
        return tbclienteFacade.findAllTbclienteOrderByNrCodCliente(super.getEM());
    }

    /**
     * metodo para validar as informações se estão preenchidas.
     *
     * @param tbcliente
     * @return true or false
     */
    public boolean validaCamposCliente(final Tbcliente tbcliente) {

        boolean result = false;

        if (tbcliente != null) {

            if ((tbcliente.getNmcliente() != null && !tbcliente.getNmcliente().trim().isEmpty())
                    && (tbcliente.getNrcpf() != null && !tbcliente.getNrcpf().trim().isEmpty())) {
                result = true;
            }

        }

        return result;
    }

    /**
     * metodo que gera o codigo do cliente.
     *
     * @return cod cliente
     */
    private String genarateCodeClient() {

        List<Tbcliente> listTbclientes = tbclienteFacade.findAllTbclienteOrderByNrCodCliente(super.getEM());
        Tbcliente tbcliente = null;
        String result = null;
        Integer codigo;

        if (listTbclientes != null && !listTbclientes.isEmpty()) {
            tbcliente = listTbclientes.get(listTbclientes.size() - 1);
        }

        if (tbcliente != null) {
            codigo = Integer.parseInt(tbcliente.getNrcodcliente());

            result = String.format("%06d", codigo == 0 ? 1 : codigo + 1);

        } else {
            codigo = 0;
            result = String.format("%06d", codigo == 0 ? 1 : codigo + 1);

        }

        return result;
    }

    /**
     * metodo que carrega todos os bairro por cidade.
     *
     * @param tbcidade
     * @return list of tbsubespecialidade
     */
    public List<Tbbairro> findAllTbairrobytTbcidade(final Tbcidade tbcidade) {
        return bairroLogic.findAllTbbairroByTbcidade(tbcidade);
    }
}
