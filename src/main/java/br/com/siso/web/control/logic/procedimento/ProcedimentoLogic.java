/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.logic.procedimento;

import br.com.siso.model.daos.TbprocedimentoFacade;
import br.com.siso.model.entities.Tbagendamento;
import br.com.siso.model.entities.Tbcliente;
import br.com.siso.model.entities.Tbdente;
import br.com.siso.model.entities.Tborcamento;
import br.com.siso.model.entities.Tbprocedimento;
import br.com.siso.model.entities.Tbservico;
import br.com.siso.model.entities.Tbtipostatusprocedimento;
import br.com.siso.web.control.module.AbstractModuleCore;
import br.com.siso.web.faces.constants.StatusSiso;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ioliveira
 */
@Stateless
public class ProcedimentoLogic extends AbstractModuleCore {

    @EJB
    private TbprocedimentoFacade tbprocedimentoFacade;

    /**
     * metodo utilizado para recuperar um procedimento.
     *
     * @param idProcedimento
     * @return tbprocedimento
     */
    public Tbprocedimento findTbprocedimentoByIdProcedimento(final Integer idProcedimento) {
        return tbprocedimentoFacade.find(idProcedimento, super.getEM());
    }

    /**
     * metodo utilizado para criar um procedimento.
     *
     * @param tbprocedimento
     * @return true or false
     */
    public boolean createTbprocedimento(final Tbprocedimento tbprocedimento) {
        return tbprocedimentoFacade.create(tbprocedimento, super.getEM());
    }

    /**
     * metodo utilizado para remover um procedimento.
     *
     * @param tbprocedimento
     * @return true or false
     */
    public boolean removeTbprocedimento(final Tbprocedimento tbprocedimento) {
        return tbprocedimentoFacade.remove(tbprocedimento, super.getEM());
    }

    /**
     * metodo para atualizar dados do procedimento.
     *
     * @param tbprocedimento
     * @return true or false
     */
    public boolean editTbprocedimento(final Tbprocedimento tbprocedimento) {
        return tbprocedimentoFacade.edit(tbprocedimento, super.getEM());
    }

    /**
     * metodo para criar procedimento odontograma.
     *
     * @param tbdente
     * @param face
     * @param tbagendamento
     * @param tbservico
     * @return tbprocedimento
     */
    public Tbprocedimento adicionarProcedimentoOdontograma(final Tbdente tbdente, Integer face, final Tbagendamento tbagendamento, final Tbservico tbservico) {

        Tbprocedimento tbprocedimento = new Tbprocedimento();

        tbprocedimento.setIddente(tbdente);
        tbprocedimento.setInfacedente(face);
        tbprocedimento.setIdtipostatusprocedimento(new Tbtipostatusprocedimento()); // tipo de status nao realizado
        tbprocedimento.getIdtipostatusprocedimento().setIdtipostatusprocedimento(StatusSiso.ID_STATUS_PROCEDIMENTO_NAO_REALIZADOS);
        tbprocedimento.setInsituacao(0); // Incluido
        tbprocedimento.setQtprocedimento(1);
        tbprocedimento.setIdservico(tbservico);
        tbprocedimento.setIdorcamento(null);
        tbprocedimento.setBoautorizado(false);
        tbprocedimento.setIdsolicitante(null);
        tbprocedimento.setIdcliente(tbagendamento.getIdcliente());

        if (tbagendamento.getIdcliente().getBonaoassociado()) {
            tbprocedimento.setVlprocedimento(tbservico.getVlservico());//servicoSelecionado.getVlServico()
        } else {
            tbprocedimento.setVlprocedimento(tbservico.getVlservicoassociado());//servicoSelecionado.getVlServicoAssociado()
        }

        //tbprocedimentoFacade.create(tbprocedimento, super.getEM());
        return tbprocedimento;
    }

    /**
     * metodo utilizado para carregar a lista de procedimentos para um cliente.
     *
     * @param tbcliente
     * @return list of tbprocedimento
     */
    public List<Tbprocedimento> findAllTbprocedimentosByIdCliente(final Tbcliente tbcliente) {
        return tbprocedimentoFacade.findAllTbprocedimentosByIdCliente(tbcliente, super.getEM());
    }

    /**
     * metodo para criar procedimento informado manualmente pelo profissional.
     *
     * @param tbdente
     * @param face
     * @param tbagendamento
     * @param quantidade
     * @param tbservico
     * @return tbprocedimento
     */
    public Tbprocedimento adicionarProcedimentoManual(final Tbservico tbservico, final Tbdente tbdente, final Integer face,
            final Integer quantidade, final Tbagendamento tbagendamento) {

        Tbprocedimento tbprocedimento = new Tbprocedimento();

        tbprocedimento.setIddente(tbdente);
        tbprocedimento.setInfacedente(face);
        tbprocedimento.setIdtipostatusprocedimento(new Tbtipostatusprocedimento(StatusSiso.ID_STATUS_PROCEDIMENTO_NAO_LANCADOS)); // tipo de status nao realizado
        tbprocedimento.setInsituacao(0); // Incluido
        tbprocedimento.setQtprocedimento(quantidade);
        tbprocedimento.setIdservico(tbservico);
        tbprocedimento.setIdorcamento(null);
        tbprocedimento.setBoautorizado(false);
        tbprocedimento.setIdsolicitante(null);
        tbprocedimento.setIdcliente(tbagendamento.getIdcliente());

        if (tbagendamento.getIdcliente().getBonaoassociado()) {
            tbprocedimento.setVlprocedimento(tbservico.getVlservico());//servicoSelecionado.getVlServico()
        } else {
            tbprocedimento.setVlprocedimento(tbservico.getVlservicoassociado());//servicoSelecionado.getVlServicoAssociado()
        }

        tbprocedimentoFacade.create(tbprocedimento, super.getEM());

        return tbprocedimento;
    }

    /**
     * metodo para criar procedimento orcamento informado manualmente pelo
     * profissional.
     *
     * @param tbdente
     * @param face
     * @param tbagendamento
     * @param quantidade
     * @param tbservico
     * @return tbprocedimento
     */
    public Tbprocedimento adicionarProcedimentoOrcamento(final Tbservico tbservico, final Tbdente tbdente, final Integer face,
            final Integer quantidade, final Tbagendamento tbagendamento) {

        Tbprocedimento tbprocedimento = new Tbprocedimento();

        tbprocedimento.setIddente(tbdente);
        tbprocedimento.setInfacedente(face);
        tbprocedimento.setIdtipostatusprocedimento(new Tbtipostatusprocedimento(StatusSiso.ID_STATUS_PROCEDIMENTO_NAO_LANCADOS)); // tipo de status nao lancados
        tbprocedimento.setInsituacao(0); // Incluido
        tbprocedimento.setQtprocedimento(quantidade);
        tbprocedimento.setIdservico(tbservico);
        tbprocedimento.setIdorcamento(null);
        tbprocedimento.setBoautorizado(false);
        tbprocedimento.setIdsolicitante(null);
        tbprocedimento.setIdcliente(tbagendamento.getIdcliente());

        if (tbagendamento.getIdcliente().getBonaoassociado()) {
            tbprocedimento.setVlprocedimento(tbservico.getVlservico());//servicoSelecionado.getVlServico()
        } else {
            tbprocedimento.setVlprocedimento(tbservico.getVlservicoassociado());//servicoSelecionado.getVlServicoAssociado()
        }

        return tbprocedimento;
    }

    /**
     * metodo utilizado para carregar a lista de procedimentos para um cliente e
     * idtipostatusprocedimentos (nao realizado, em tratamento e realizados).
     *
     * @param tbcliente
     * @return list of tbprocedimento
     */
    public List<Tbprocedimento> findAllTbprocedimentosByIdClienteAndIdTipoStatusProcedimentos(final Tbcliente tbcliente) {
        return tbprocedimentoFacade.findAllTbprocedimentosByIdClienteAndIdTipoStatusProcedimentos(tbcliente, super.getEM());
    }

    /**
     * metodo utilizado para carregar a lista de procedimentos de um orcamento.
     *
     * @param tborcamento
     * @return list of tbprocedimento
     */
    public List<Tbprocedimento> findAllTbprocedimentoByIdOrcamento(final Tborcamento tborcamento) {
        return tbprocedimentoFacade.findAllTbprocedimentoByIdOrcamento(tborcamento, super.getEM());
    }
}
