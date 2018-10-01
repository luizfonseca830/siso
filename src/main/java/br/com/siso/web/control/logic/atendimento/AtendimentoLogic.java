/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.logic.atendimento;

import br.com.siso.model.daos.TbatendimentoFacade;
import br.com.siso.model.entities.Tbagendamento;
import br.com.siso.model.entities.Tbatendimento;
import br.com.siso.model.entities.Tbcliente;
import br.com.siso.model.entities.Tbdente;
import br.com.siso.model.entities.Tbespecialidade;
import br.com.siso.model.entities.Tbfuncionario;
import br.com.siso.model.entities.Tborcamento;
import br.com.siso.model.entities.Tbprocedimento;
import br.com.siso.model.entities.Tbservico;
import br.com.siso.model.entities.Tbsubespecialidade;
import br.com.siso.model.entities.Tbtipostatusagendamento;
import br.com.siso.model.entities.Tbtipostatusorcamento;
import br.com.siso.model.entities.Tbtipostatusprocedimento;
import br.com.siso.web.control.logic.agendamento.AgendamentoLogic;
import br.com.siso.web.control.logic.cliente.ClienteLogic;
import br.com.siso.web.control.logic.dente.DenteLogic;
import br.com.siso.web.control.logic.funcionario.FuncionarioLogic;
import br.com.siso.web.control.logic.odontograma.OdontogramaLogic;
import br.com.siso.web.control.logic.orcamento.OrcamentoLogic;
import br.com.siso.web.control.logic.procedimento.ProcedimentoLogic;
import br.com.siso.web.control.logic.servico.ServicoLogic;
import br.com.siso.web.control.logic.subespecialidade.SubEspecialidadeLogic;
import br.com.siso.web.control.module.AbstractModuleCore;
import br.com.siso.web.faces.constants.StatusSiso;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

/**
 *
 * @author ioliveira
 */
@Stateless
public class AtendimentoLogic extends AbstractModuleCore {

    @EJB
    private AgendamentoLogic agendamentoLogic;
    @EJB
    private ClienteLogic clienteLogic;
    @EJB
    private FuncionarioLogic funcionarioLogic;
    @EJB
    private TbatendimentoFacade tbatendimentoFacade;
    @EJB
    private OdontogramaLogic odontogramaLogic;
    @EJB
    private SubEspecialidadeLogic subEspecialidadeLogic;
    @EJB
    private ServicoLogic servicoLogic;
    @EJB
    private ProcedimentoLogic procedimentoLogic;
    @EJB
    private DenteLogic denteLogic;
    @EJB
    private OrcamentoLogic orcamentoLogic;

    /**
     * metodo utilizado para editar informacoes do orcamento.
     *
     * @param tborcamento
     * @return true or false
     */
    public boolean editTborcamento(final Tborcamento tborcamento) {
        return orcamentoLogic.editTborcamento(tborcamento);
    }

    /**
     * metodo utilizado para fechar orcamento do cliente.
     *
     * @param tbatendimento
     * @param tborcamento
     * @param listTbprocedimentos
     * @return true or false
     */
    public boolean finalizarOrcamento(final Tbatendimento tbatendimento,
            final Tborcamento tborcamento, final List<Tbprocedimento> listTbprocedimentos) {

        boolean result = false;
        float valorTotalOrcamento = 0.0f;

        if (finalizarAtendimento(tbatendimento)) {
            tborcamento.setDtdatainclusao(new Date());

            if (orcamentoLogic.createTborcamento(tborcamento)) {
                for (Tbprocedimento procedimento : listTbprocedimentos) {
                    procedimento.setIdorcamento(tborcamento);
                    valorTotalOrcamento += (procedimento.getVlprocedimento() * procedimento.getQtprocedimento());
                    procedimentoLogic.editTbprocedimento(procedimento);
                }
                tborcamento.setVltotal(valorTotalOrcamento);
                orcamentoLogic.editTborcamento(tborcamento);
                result = true;
            }
        }

        return result;
    }

    /**
     * metodo utilizado para finalizar um atendimento.
     *
     * @param tbatendimento
     * @return true or false
     */
    public boolean finalizarAtendimento(final Tbatendimento tbatendimento) {

        boolean result = false;

        Tbagendamento tbagendamento = tbatendimento.getIdagendamento();
        tbagendamento.setIdtipostatusagendamento(new Tbtipostatusagendamento(StatusSiso.ID_STATUS_AGENDAMENTO_ATENDIDO));

        if (agendamentoLogic.editTbagendamento(tbagendamento)) {
            tbatendimento.setBoolfinalizado(true);
            tbatendimento.setTmdatafimatendimento(new Date());

            if (tbatendimentoFacade.edit(tbatendimento, super.getEM())) {
                result = true;
            }
        }

        return result;
    }

    /**
     * metodo para cancelar procedimento.
     *
     * @param tbprocedimento
     * @return true or false
     */
    public boolean cancelarProcedimento(final Tbprocedimento tbprocedimento) {

        boolean result = false;

        // precisa esta lancado e com status de nao realizado
        if (tbprocedimento.getIdtipostatusprocedimento().getIntipostatusprocedimento().equals(StatusSiso.INT_INTIPOSTATUS_PROCEDIMENTO_NAO_REALIZADOS)) {
            tbprocedimento.setIdtipostatusprocedimento(new Tbtipostatusprocedimento(StatusSiso.ID_STATUS_PROCEDIMENTO_CANCELADOS));
            tbprocedimento.setDtatualizacaolog(new Date());

            if (procedimentoLogic.editTbprocedimento(tbprocedimento)) {
                result = true;
            }
        }

        return result;
    }

    /**
     * metodo utilizado para finalizar um procedimento.
     *
     * @param tbprocedimento
     * @return true or false
     */
    public boolean finalizarProcedimento(final Tbprocedimento tbprocedimento) {

        boolean result = false;

        //2 em tratamento
        if (tbprocedimento.getIdtipostatusprocedimento().getIntipostatusprocedimento().equals(StatusSiso.INT_INTIPO_STATUS_PROCEDIMENTO_EM_TRATAMENTO)) {
            tbprocedimento.setIdtipostatusprocedimento(new Tbtipostatusprocedimento(StatusSiso.ID_STATUS_PROCEDIMENTO_REALIZADOS));
            tbprocedimento.setDtconclusao(new Date());
            tbprocedimento.setDtatualizacaolog(new Date());

            if (procedimentoLogic.editTbprocedimento(tbprocedimento)) {
                result = true;
            }
        }

        return result;
    }

    /**
     * metodo utilizado para iniciar um procedimento selecionado da lista de
     * procedimentos.
     *
     * @param tbprocedimento
     * @return true or false
     */
    public boolean iniciarProcedimento(final Tbprocedimento tbprocedimento) {

        boolean result = false;

        //0 nao realizado
        if (tbprocedimento.getIdtipostatusprocedimento().getIntipostatusprocedimento().equals(StatusSiso.INT_INTIPOSTATUS_PROCEDIMENTO_NAO_REALIZADOS)) {
            tbprocedimento.setIdtipostatusprocedimento(new Tbtipostatusprocedimento(StatusSiso.ID_STATUS_PROCEDIMENTO_EM_TRATAMENTO));
            tbprocedimento.setDtinclusaolog(new Date());
            tbprocedimento.setDtatualizacaolog(new Date());

            if (procedimentoLogic.editTbprocedimento(tbprocedimento)) {
                result = true;
            }

        }

        return result;
    }

    /**
     * metodo utilizado para ausentar um paciente.
     *
     * @param tbagendamento
     * @return true or false
     */
    public boolean ausentarPaciente(final Tbagendamento tbagendamento) {
        boolean result = false;

        tbagendamento.setIdtipostatusagendamento(new Tbtipostatusagendamento());
        tbagendamento.getIdtipostatusagendamento().setIdtipostatusagendamento(StatusSiso.ID_STATUS_AGENDAMENTO_AUSENTE);

        if (agendamentoLogic.editTbagendamento(tbagendamento)) {
            result = true;
        }

        return result;
    }

    /**
     * metodo para remover procedimento.
     *
     * @param tbprocedimento
     * @return true or false.
     */
    public boolean removeTbprocedimento(final Tbprocedimento tbprocedimento) {
        tbprocedimento.setIdtipostatusprocedimento(new Tbtipostatusprocedimento(StatusSiso.ID_STATUS_PROCEDIMENTO_REMOVIDOS));
        tbprocedimento.setInsituacao(4);//excluido        
        tbprocedimento.setDtatualizacaolog(new Date());
        return procedimentoLogic.editTbprocedimento(tbprocedimento);
    }

    /**
     * metodo utilizado para criar procedimento manual sem odontograma.
     *
     * @param tbservico
     * @param tbdente
     * @param face
     * @param quantidade
     * @param tbagendamento
     * @return true or false
     */
    public boolean createProcedimentoManual(final Tbservico tbservico, final Tbdente tbdente, final Integer face,
            final Integer quantidade, final Tbagendamento tbagendamento) {

        boolean result = false;

        if (odontogramaLogic.validarDadosAdicionarProcedimentoOdontograma(face, tbservico)) {
            Tbprocedimento tbprocedimento = procedimentoLogic.adicionarProcedimentoManual(tbservico, tbdente, face, quantidade, tbagendamento);

            if (tbprocedimento != null) {
                result = true;
            }
        }

        return result;
    }

    /**
     * metodo utilizado para criar procedimento manual sem odontograma.
     *
     * @param tbservico
     * @param tbdente
     * @param face
     * @param quantidade
     * @param tbagendamento
     * @return Tbprocedimento
     */
    public Tbprocedimento createProcedimentoOrcamentoManual(final Tbservico tbservico, final Tbdente tbdente, final Integer face,
            final Integer quantidade, final Tbagendamento tbagendamento) {

        Tbprocedimento tbprocedimento = null;

        if (tbservico != null && tbdente != null && face != null && quantidade != null) {
            if (odontogramaLogic.validarDadosAdicionarProcedimentoOdontograma(face, tbservico)) {
                tbprocedimento = procedimentoLogic.adicionarProcedimentoOrcamento(tbservico, tbdente, face, quantidade, tbagendamento);
            }
        }

        return tbprocedimento;
    }

    /**
     * metodo para recuperar um tbatendimento por id.
     *
     * @param idAtendimento
     * @return tbatendimento
     */
    public Tbatendimento findTbatendimentoByIdAtendimento(final Integer idAtendimento) {
        return tbatendimentoFacade.find(idAtendimento, super.getEM());
    }

    /**
     * metodo utilizado para iniciar um orcamento para um agendamento.
     *
     * @param tbagendamento
     * @return tborcamento
     */
    public Tborcamento iniciarOrcamento(final Tbagendamento tbagendamento) {

        Tborcamento tborcamento = null;

        if (tbagendamento != null) {

            //tborcamento = orcamentoLogic.findTborcamentoByDtDataAndIdAgendamento(tbagendamento);
            if (tborcamento == null) {
                tborcamento = new Tborcamento();
                tborcamento.setIdagendamento(tbagendamento);
                tborcamento.setIdcliente(tbagendamento.getIdcliente());
                tborcamento.setIddependente(null);
                tborcamento.setDtdataorcamento(new Date());
                tborcamento.setTmdataorcamento(new Date());
                tborcamento.setIdtipostatusorcamento(new Tbtipostatusorcamento());
                tborcamento.getIdtipostatusorcamento().setIdtipostatusorcamento(StatusSiso.ID_STATUS_ORCAMENTO_ABERTO);
//                orcamentoLogic.editTborcamento(tborcamento);
            }
        }

        return tborcamento;
    }

    /**
     * metodo utilizado para iniciar um atendimento.
     *
     * @param tbagendamento
     * @return Tbatendimento
     */
    public Tbatendimento iniciarAtendimento(final Tbagendamento tbagendamento) {

        Tbatendimento tbatendimento = null;

        if (tbagendamento != null) {

            tbatendimento = tbatendimentoFacade.findTbatendimentoByDtDataAtendimentoAndIdAgendamento(tbagendamento, super.getEM());

            if (tbatendimento == null) {
                tbatendimento = new Tbatendimento();
                tbatendimento.setIdagendamento(tbagendamento);
                tbatendimento.setIdfuncionario(tbagendamento.getIdfuncionario());
                tbatendimento.setDtdataatendimento(tbagendamento.getDtdataagendamento());
                tbatendimento.setTmdatainicioatendimento(new Date());
                //tbatendimentoFacade.create(tbatendimento, super.getEM());
            }
        }

        return tbatendimento;
    }

    /**
     * metodo utilizado para recuperar um Agendamento por idAgendamento.
     *
     * @param idAgendamento
     * @return tbagendamento
     */
    public Tbagendamento findTbagendamentoByIdAgendamento(final Integer idAgendamento) {
        return agendamentoLogic.findTbagendamentoByIdAgendamento(idAgendamento);
    }

    /**
     * metodo para carregar todos os clientes cadastrados.
     *
     * @return list of tbcliente
     */
    public List<Tbcliente> findAllTbcliente() {
        return clienteLogic.findAllTbcliente();
    }

    /**
     * metodo que carrega todos os funcionario.
     *
     * @return
     */
    public List<Tbfuncionario> findAllTbfuncionario() {
        return funcionarioLogic.findAllTbfuncionario();
    }

    /**
     * metodo para carregar a lista de todos os agendamento do dia com o status
     * de presente para atendimento.
     *
     * @return list of tbagendamento
     */
    public List<Tbagendamento> findAllTbagendamentoDiaAtualComStatusDePresente() {
        return agendamentoLogic.findAllTbagendamentoDiaAtualComStatusDePresente();
    }

    /**
     * metodo utilizado para filtrar os agendamentos por data, cliente e
     * funcionario e tipo status presente para atendimento.
     *
     * @param data
     * @param tbcliente
     * @param tbfuncionario
     * @return list of agendamento
     */
    public List<Tbagendamento> findAllTbagendamentoByDataAndPacienteAndFuncionarioAndStatusPresente(final Date data, final Tbcliente tbcliente,
            final Tbfuncionario tbfuncionario) {
        return agendamentoLogic.findAllTbagendamentoByDataAndPacienteAndFuncionarioAndStatusPresente(data, tbcliente, tbfuncionario);
    }

    /**
     * metodo que carrega todas as sub especialidades por especialidade.
     *
     * @param tbespecialidade
     * @return list of tbsubespecialidade
     */
    public List<Tbsubespecialidade> findAllTbsubespecialidadesByTbespecialidade(final Tbespecialidade tbespecialidade) {
        return subEspecialidadeLogic.findAllTbsubespecialidadesByTbespecialidade(tbespecialidade);
    }

    /**
     * metodo para recuperar todos os servico disponiveis para procedimento.
     *
     * @param tbsubespecialidade
     * @return list of tbservico
     */
    public List<Tbservico> findAllTbservicosByIdSubEspecialidade(final Tbsubespecialidade tbsubespecialidade) {
        return servicoLogic.findAllTbservicosByIdSubEspecialidade(tbsubespecialidade);
    }

    /**
     * metodo utilizado para carregar a lista de procedimentos para um cliente.
     *
     * @param tbcliente
     * @return list of tbprocedimento
     */
    public List<Tbprocedimento> findAllTbprocedimentosByIdCliente(final Tbcliente tbcliente) {
        return procedimentoLogic.findAllTbprocedimentosByIdCliente(tbcliente);
    }

    /**
     * metodo utilizado para carregar todos os servicos disponiveis.
     *
     * @return list of tbservico
     */
    public List<Tbservico> findAllTbservicoByInTipoServicoAndDtExclusao() {
        return servicoLogic.findAllTbservicoByInTipoServicoAndDtExclusao();
    }

    /**
     * metodo que carrega a lista de todos os dentes.
     *
     * @return list of Tbdente
     */
    public List<Tbdente> findAllTbdente() {
        return denteLogic.findAllTbdente();
    }

    /**
     * metodo utilizado para carregar a lista de procedimentos de um orcamento.
     *
     * @param tborcamento
     * @return list of tbprocedimento
     */
    public List<Tbprocedimento> findAllTbprocedimentoByIdOrcamento(final Tborcamento tborcamento) {
        return procedimentoLogic.findAllTbprocedimentoByIdOrcamento(tborcamento);
    }

    /**
     * metodo para carregar o orcamento do cliente para atendimento.
     *
     * @param tbcliente
     * @return tborcamento
     */
    public Tborcamento findTborcamentoByIdClienteAndStatusAberto(final Tbcliente tbcliente) {
        return orcamentoLogic.findTborcamentoByIdClienteAndStatusAberto(tbcliente);
    }

    /**
     * metodo utilizado para remover manualmente um procedimento da lista de
     * procedimentos
     *
     * @param procedimentoSelected
     * @param listTbprocedimentos
     * @return list of tbprocedimento
     */
    public List<Tbprocedimento> removeTbprocedimentoSelected(final Tbprocedimento procedimentoSelected,
            final List<Tbprocedimento> listTbprocedimentos) {

        List<Tbprocedimento> listResult = new ArrayList<>();

        for (Tbprocedimento procedimento : listTbprocedimentos) {

            if ((procedimentoSelected.getIddente().getIddente().intValue() != (procedimento.getIddente().getIddente()))
                    || (procedimentoSelected.getInfacedente() != procedimento.getInfacedente())
                    || (procedimentoSelected.getQtprocedimento() != procedimento.getQtprocedimento())
                    || (procedimentoSelected.getIdservico().getIdservico().intValue() != (procedimento.getIdservico().getIdservico()))) {
                listResult.add(procedimento);
            }
        }

        return listResult;
    }

    /**
     * metodo utilizado para iniciar procedimento pelo odontograma.
     *
     * @param event
     * @param listTbprocedimentos
     * @return true or false
     */
    public boolean iniciarProcedimentoOdontograma(ActionEvent event, List<Tbprocedimento> listTbprocedimentos) {
        return odontogramaLogic.iniciarProcedimentoOdontograma(event, listTbprocedimentos);
    }

    /**
     * metodo utilizado para finalizar procedimento odontograma.
     *
     * @param event
     * @param listTbprocedimentos
     * @return true or false
     */
    public boolean finalizarProcedimentoOdontograma(ActionEvent event, List<Tbprocedimento> listTbprocedimentos) {
        return odontogramaLogic.finalizarProcedimentoOdontograma(event, listTbprocedimentos);
    }

    /**
     * metodo que carrega todas as faces do dente.
     *
     * @return list of selectItem
     */
    public List<SelectItem> carregarinFace() {
        List<SelectItem> cmbinFace = new ArrayList<>();

        cmbinFace.add(new SelectItem(0, "Arcada"));
        cmbinFace.add(new SelectItem(1, "Cervical"));
        cmbinFace.add(new SelectItem(2, "Dente"));
        cmbinFace.add(new SelectItem(3, "Distal"));
        cmbinFace.add(new SelectItem(4, "Inferior"));
        cmbinFace.add(new SelectItem(5, "Inferior Direito"));
        cmbinFace.add(new SelectItem(6, "Inferior Esquerdo"));
        cmbinFace.add(new SelectItem(7, "Mesial"));
        cmbinFace.add(new SelectItem(8, "Oclusal"));
        cmbinFace.add(new SelectItem(9, "Palatina"));
        cmbinFace.add(new SelectItem(10, "Raiz"));
        cmbinFace.add(new SelectItem(11, "Superior"));
        cmbinFace.add(new SelectItem(12, "Superior Direito"));
        cmbinFace.add(new SelectItem(13, "Superior Esquerdo"));
        cmbinFace.add(new SelectItem(14, "Vestibular"));

        return cmbinFace;
    }

    /**
     * metodo utilizado para gerar odontograma adulto.
     *
     * @return htmlPanelGrid
     */
    public HtmlPanelGrid montarOdontogramaAdulto() {
        return odontogramaLogic.montarOdontogramaAdulto();
    }

    /**
     * metodo utilizado para gerar odontograma infantil.
     *
     * @return htmlPanelGrid
     */
    public HtmlPanelGrid montarOdontogramaInfantil() {
        return odontogramaLogic.montarOdontogramaInfantil();
    }

    /**
     * metodo para remontar Odontograma adulto com os procedimentos do cliente.
     *
     * @param panelOdontogramaAdulto
     * @param listTbprocedimentos
     * @return htmlPanelGrid
     */
    public HtmlPanelGrid remontarOdontogramaAdultoAtendimento(final HtmlPanelGrid panelOdontogramaAdulto,
            final List<Tbprocedimento> listTbprocedimentos) {
        return odontogramaLogic.remontarOdontogramaAdultoAtendimento(panelOdontogramaAdulto, listTbprocedimentos);
    }

    /**
     * metodo para remontar Odontograma infantil com os procedimentos do
     * cliente.
     *
     * @param panelOdontogramaInfantil
     * @param listTbprocedimentos
     * @return htmlPanelGrid
     */
    public HtmlPanelGrid remontarOdontogramaInfantilAtendimento(final HtmlPanelGrid panelOdontogramaInfantil,
            final List<Tbprocedimento> listTbprocedimentos) {
        return odontogramaLogic.remontarOdontogramaInfantilAtendimento(panelOdontogramaInfantil, listTbprocedimentos);
    }

    /**
     * metodo utilizado para remontar odontograma para orcamento.
     *
     * @param panelOdontogramaAdulto
     * @param boPopup
     * @param listTbprocedimentos
     * @return htmlPanelGrid
     */
    public HtmlPanelGrid remontarOdontogramaAdulto(HtmlPanelGrid panelOdontogramaAdulto, boolean boPopup, final List<Tbprocedimento> listTbprocedimentos) {
        return odontogramaLogic.remontarOdontogramaAdulto(panelOdontogramaAdulto, boPopup, listTbprocedimentos);
    }

    /**
     * metodo utilizado para remontar odontograma infantil.
     *
     * @param panelDenteInfantil
     * @param listTbprocedimentos
     * @return htmlPanelGrid
     */
    public HtmlPanelGrid remontarOdontogramaInfantil(HtmlPanelGrid panelDenteInfantil, final List<Tbprocedimento> listTbprocedimentos) {
        return odontogramaLogic.remontarOdontogramaInfantil(panelDenteInfantil, listTbprocedimentos);
    }

    /**
     * metodo para adicionar procedimento na raiz.
     *
     * @param event
     * @param tbagendamento
     * @param tbservico
     * @param listTbprocedimentos
     * @return true or false
     */
    public boolean adicionarRaiz(ActionEvent event, final Tbagendamento tbagendamento, final Tbservico tbservico, final List<Tbprocedimento> listTbprocedimentos) {
        return odontogramaLogic.adicionarRaiz(event, tbagendamento, tbservico, listTbprocedimentos);
    }

    /**
     * metodo para remover procedimento do dente raiz.
     *
     * @param event
     * @param listTbprocedimentos
     * @return true or false
     */
    public boolean removePopupRaiz(ActionEvent event, List<Tbprocedimento> listTbprocedimentos) {
        return odontogramaLogic.removePopupRaiz(event, listTbprocedimentos);
    }

    /**
     * metodo para adicionar procedimento na Cervical.
     *
     * @param event
     * @param tbagendamento
     * @param tbservico
     * @param listTbprocedimentos
     * @return true or false
     */
    public boolean adicionarCervical(ActionEvent event, final Tbagendamento tbagendamento, final Tbservico tbservico, final List<Tbprocedimento> listTbprocedimentos) {
        return odontogramaLogic.adicionarCervical(event, tbagendamento, tbservico, listTbprocedimentos);
    }

    /**
     * metodo para remover procedimento da faces cervical do dente.
     *
     * @param event
     * @param listTbprocedimentos
     * @return true or false
     */
    public boolean removePopupCervical(ActionEvent event, List<Tbprocedimento> listTbprocedimentos) {
        return odontogramaLogic.removePopupCervical(event, listTbprocedimentos);
    }

    /**
     * metodo para adicionar procedimento na face vestibular.
     *
     * @param event
     * @param tbagendamento
     * @param tbservico
     * @param listTbprocedimentos
     * @return true or false
     */
    public boolean adicionarVestibular(ActionEvent event, final Tbagendamento tbagendamento, final Tbservico tbservico, final List<Tbprocedimento> listTbprocedimentos) {
        return odontogramaLogic.adicionarVestibular(event, tbagendamento, tbservico, listTbprocedimentos);
    }

    /**
     * metodo para remover procedimento da faces do dente vestibular.
     *
     * @param event
     * @param listTbprocedimentos
     * @return true or false
     */
    public boolean removePopupVestibular(ActionEvent event, List<Tbprocedimento> listTbprocedimentos) {
        return odontogramaLogic.removePopupVestibular(event, listTbprocedimentos);

    }

    /**
     * metodo para adicionar procedimento no Distal.
     *
     * @param event
     * @param tbagendamento
     * @param tbservico
     * @param listTbprocedimentos
     * @return true or false
     */
    public boolean adicionarDistal(ActionEvent event, final Tbagendamento tbagendamento, final Tbservico tbservico, final List<Tbprocedimento> listTbprocedimentos) {
        return odontogramaLogic.adicionarDistal(event, tbagendamento, tbservico, listTbprocedimentos);
    }

    /**
     * metodo para remover procedimento da faces do dente distal.
     *
     * @param event
     * @param listTbprocedimentos
     * @return true or false
     */
    public boolean removePopupDistal(ActionEvent event, List<Tbprocedimento> listTbprocedimentos) {
        return odontogramaLogic.removePopupDistal(event, listTbprocedimentos);
    }

    /**
     * metodo para adicionar procedimento na Oclusal.
     *
     * @param event
     * @param tbagendamento
     * @param tbservico
     * @param listTbprocedimentos
     * @return true or false
     */
    public boolean adicionarOclusal(ActionEvent event, final Tbagendamento tbagendamento, final Tbservico tbservico, final List<Tbprocedimento> listTbprocedimentos) {
        return odontogramaLogic.adicionarOclusal(event, tbagendamento, tbservico, listTbprocedimentos);
    }

    /**
     * metodo para remover procedimento da faces do dente Oclusal.
     *
     * @param event
     * @param listTbprocedimentos
     * @return true or false
     */
    public boolean removePopupOclusal(ActionEvent event, List<Tbprocedimento> listTbprocedimentos) {
        return odontogramaLogic.removePopupOclusal(event, listTbprocedimentos);
    }

    /**
     * metodo para adicionar procedimento na Mesial.
     *
     * @param event
     * @param tbagendamento
     * @param tbservico
     * @param listTbprocedimentos
     * @return true or false
     */
    public boolean adicionarMesial(ActionEvent event, final Tbagendamento tbagendamento, final Tbservico tbservico, final List<Tbprocedimento> listTbprocedimentos) {
        return odontogramaLogic.adicionarMesial(event, tbagendamento, tbservico, listTbprocedimentos);
    }

    /**
     * metodo para remover procedimento da faces do dente mesial.
     *
     * @param event
     * @param listTbprocedimentos
     * @return true or false
     */
    public boolean removePopupMesial(ActionEvent event, List<Tbprocedimento> listTbprocedimentos) {
        return odontogramaLogic.removePopupMesial(event, listTbprocedimentos);
    }

    /**
     * metodo para adicionar procedimento na palatina.
     *
     * @param event
     * @param tbagendamento
     * @param tbservico
     * @param listTbprocedimentos
     * @return true or false
     */
    public boolean adicionarPalatina(ActionEvent event, final Tbagendamento tbagendamento, final Tbservico tbservico, final List<Tbprocedimento> listTbprocedimentos) {
        return odontogramaLogic.adicionarPalatina(event, tbagendamento, tbservico, listTbprocedimentos);
    }

    /**
     * metodo para remover procedimento da faces do dente distal.
     *
     * @param event
     * @param listTbprocedimentos
     * @return true or false
     */
    public boolean removePopupPalatina(ActionEvent event, List<Tbprocedimento> listTbprocedimentos) {
        return odontogramaLogic.removePopupPalatina(event, listTbprocedimentos);
    }

    /**
     * metodo utilizado para carregar todas as consultas com status de atendido
     * para um determinado cliente.
     *
     * @param tbcliente
     * @return list of tbatendimento
     */
    public List<Tbatendimento> findAllTbatendimentosByIdClienteAndStatusAtendido(final Tbcliente tbcliente) {
        return tbatendimentoFacade.findAllTbatendimentosByIdClienteAndStatusAtendido(tbcliente, super.getEM());
    }
}
