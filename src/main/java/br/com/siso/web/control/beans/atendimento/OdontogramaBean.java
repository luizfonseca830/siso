/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.beans.atendimento;

import br.com.siso.model.entities.Tbagendamento;
import br.com.siso.model.entities.Tbatendimento;
import br.com.siso.model.entities.Tbcliente;
import br.com.siso.model.entities.Tbdente;
import br.com.siso.model.entities.Tbespecialidade;
import br.com.siso.model.entities.Tbfuncionario;
import br.com.siso.model.entities.Tborcamento;
import br.com.siso.model.entities.Tbprocedimento;
import br.com.siso.model.entities.Tbreceituario;
import br.com.siso.model.entities.Tbservico;
import br.com.siso.model.entities.Tbservicoface;
import br.com.siso.model.entities.Tbsubespecialidade;
import br.com.siso.model.entities.custom.DenteModel;
import br.com.siso.model.entities.custom.FaceModel;
import br.com.siso.model.entities.custom.OrcamentoModel;
import br.com.siso.web.control.logic.agendamento.AgendamentoLogic;
import br.com.siso.web.control.logic.atendimento.AtendimentoLogic;
import br.com.siso.web.control.logic.cliente.ClienteLogic;
import br.com.siso.web.control.logic.dente.DenteLogic;
import br.com.siso.web.control.logic.funcionario.FuncionarioLogic;
import br.com.siso.web.control.logic.orcamento.OrcamentoLogic;
import br.com.siso.web.control.logic.procedimento.ProcedimentoLogic;
import br.com.siso.web.control.logic.receituario.ReceituarioLogic;
import br.com.siso.web.control.logic.servicoface.ServicoFaceLogic;
import br.com.siso.web.faces.constants.ImagesUrl;
import br.com.siso.web.faces.constants.PagesUrl;
import br.com.siso.web.faces.constants.StatusSiso;
import br.com.siso.web.faces.utils.AbstractFacesContextUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author ioliveira
 */
@ManagedBean(name = "odontogramaBean")
@SessionScoped
public class OdontogramaBean implements Serializable {

    @EJB
    private AtendimentoLogic atendimentoLogic;
    @EJB
    private DenteLogic denteLogic;
    @EJB
    private ProcedimentoLogic procedimentoLogic;
    @EJB
    private AgendamentoLogic agendamentoLogic;
    @EJB
    private FuncionarioLogic funcionarioLogic;
    @EJB
    private ServicoFaceLogic servicoFaceLogic;
    @EJB
    private ClienteLogic clienteLogic;
    @EJB
    private OrcamentoLogic orcamentoLogic;
    @EJB
    private ReceituarioLogic receituarioLogic;

    private Date dataSearch;
    private Integer qtProcedimento;
    private Tbcliente tbcliente;
    private Tbfuncionario tbfuncionario;
    private Tbagendamento tbagendamento;
    private Tborcamento tborcamento;
    private Tbatendimento tbatendimento;
    private Tbdente tbdente;
    private Integer denteSelected;
    private Integer faceSelected;
    private String nmFaceSelected;
    private Integer posicao;
    private Integer inFace;
    private Tbsubespecialidade tbsubespecialidade;
    private Tbservico tbservico;
    private Tbservico tbservicoManual;
    private Tbprocedimento tbprocedimentoToRemove;
    private Tbprocedimento tbprocedimentoSelected;

    private List<DenteModel> listDentesSuperiores = new ArrayList<>();
    private List<DenteModel> listDentesInferiores = new ArrayList<>();
    private List<DenteModel> listDentesSuperioresInfantil = new ArrayList<>();
    private List<DenteModel> listDentesInferioresInfantil = new ArrayList<>();
    private List<DenteModel> listDentesSuperioresOrcamento = new ArrayList<>();
    private List<DenteModel> listDentesInferioresOrcamento = new ArrayList<>();
    private List<DenteModel> listDentesSuperioresInfantilOrcamento = new ArrayList<>();
    private List<DenteModel> listDentesInferioresInfantilOrcamento = new ArrayList<>();
    private List<Tbagendamento> listTbagendamentos;
    private List<Tbcliente> listTbclientes;
    private List<Tbfuncionario> listTbfuncionarios;
    private List<Tbsubespecialidade> listTbsubespecialidades;
    private List<Tbservico> listTbservicos;
    private List<Tbprocedimento> listTbprocedimentosGeral;
    private List<Tbprocedimento> listTbprocedimentosPorDente = new ArrayList<>();
    private List<Tbdente> listTbdentes;
    private List<SelectItem> listFacesDentes;
    private List<OrcamentoModel> listOrcamentoModels;
    private List<Tbatendimento> consultasOdontologicas;
    private List<Tbreceituario> listTbreceituarios;

    /**
     * metodo utilizado para carregar informacoes para a tela de atendimento de
     * um orcamento.
     */
    public void loadInfoAtendimentoOrcamento() {

        if (listDentesSuperioresOrcamento.isEmpty() && listDentesInferioresOrcamento.isEmpty()) {
            inicializarOdontogramaAdultoOrcamento();
        }

        if (listDentesInferioresInfantilOrcamento.isEmpty() && listDentesSuperioresInfantilOrcamento.isEmpty()) {
            inicializarOdontogramaInfantilOrcamento();
        }

        if (tbagendamento != null) {
            tborcamento = atendimentoLogic.iniciarOrcamento(tbagendamento);
            tbatendimento = atendimentoLogic.iniciarAtendimento(tbagendamento);
        }

        if (listTbprocedimentosGeral == null) {
            listTbprocedimentosGeral = new ArrayList<>();
        }
        listTbsubespecialidades = atendimentoLogic.findAllTbsubespecialidadesByTbespecialidade(new Tbespecialidade(6));
        listTbservicos = atendimentoLogic.findAllTbservicoByInTipoServicoAndDtExclusao();
        listTbdentes = atendimentoLogic.findAllTbdente();
        listFacesDentes = atendimentoLogic.carregarinFace();
    }

    /**
     * metodo utilizado para carregar informacoes para atendimento de um
     * procedimento.
     */
    public void loadInfoAtendimentoProcedimento() {

        listTbsubespecialidades = atendimentoLogic.findAllTbsubespecialidadesByTbespecialidade(new Tbespecialidade(6));
        listTbservicos = atendimentoLogic.findAllTbservicoByInTipoServicoAndDtExclusao();
        listTbdentes = atendimentoLogic.findAllTbdente();
        listFacesDentes = atendimentoLogic.carregarinFace();

        if (listDentesSuperiores.isEmpty() && listDentesInferiores.isEmpty()) {
            inicializarOdontogramaAdulto();
        }

        if (listDentesInferioresInfantil.isEmpty() && listDentesSuperioresInfantil.isEmpty()) {
            inicializarOdontogramaInfantil();
        }

        if (tbagendamento != null) {
            tbatendimento = atendimentoLogic.iniciarAtendimento(tbagendamento);
            loadInfoListProcedimentosClienteAtendimento();
        }
    }

    /**
     * metodo utilizado para carregar os agendamentos com status de presente.
     */
    public void loadSchedules() {

        clearListOdontograma();
        clearListOdontogramaOrcamento();
        listTbprocedimentosGeral = null;
        tborcamento = null;
        dataSearch = new Date();
        listTbagendamentos = atendimentoLogic.findAllTbagendamentoDiaAtualComStatusDePresente();
        listTbclientes = agendamentoLogic.findAllTbcliente();
        listTbfuncionarios = funcionarioLogic.findAllTbfuncionario();
    }

    /**
     * metodo utilizado para carregar a lista de procedimentos para o
     * odontograma parte de atendimento.
     */
    private void loadInfoListProcedimentosClienteAtendimento() {

        tborcamento = orcamentoLogic.findTborcamentoByIdClienteAndStatusAberto(tbatendimento.getIdagendamento().getIdcliente());

        if (tborcamento != null) {
            listTbprocedimentosGeral = procedimentoLogic.findAllTbprocedimentoByIdOrcamento(tborcamento);

            for (final Tbprocedimento tbprocedimento : listTbprocedimentosGeral) {

                if (tbprocedimento.getIddente().getNrposicao() >= 11 && tbprocedimento.getIddente().getNrposicao() <= 28) {

                    boolean achouFace = false;

                    for (DenteModel denteModel : listDentesSuperiores) {
                        if (denteModel.getNrDente().equals(tbprocedimento.getIddente().getNrposicao())) {

                            for (FaceModel faceModel : denteModel.getListFacesDente()) {

                                if (faceModel.getNrFace().equals(tbprocedimento.getInfacedente())) {

                                    if (faceModel.getSetProcedimentosFace().isEmpty()) {
                                        faceModel.setUrlImageFace(verificaImageProcedimentoPorFaceOrcamento(tbprocedimento.getInfacedente(), true));
                                        faceModel.getSetProcedimentosFace().add(tbprocedimento);
                                        achouFace = true;
                                        break;
                                    } else {
                                        faceModel.getSetProcedimentosFace().add(tbprocedimento);
                                        achouFace = true;
                                        break;
                                    }
                                }
                            }
                        }
                        if (achouFace) {
                            break;
                        }
                    }

                } else if (tbprocedimento.getIddente().getNrposicao() >= 51 && tbprocedimento.getIddente().getNrposicao() <= 65) {
                    boolean achouFace = false;

                    for (DenteModel denteModel : listDentesSuperioresInfantil) {
                        if (denteModel.getNrDente().equals(tbprocedimento.getIddente().getNrposicao())) {

                            for (FaceModel faceModel : denteModel.getListFacesDente()) {

                                if (faceModel.getNrFace().equals(tbprocedimento.getInfacedente())) {

                                    if (faceModel.getSetProcedimentosFace().isEmpty()) {
                                        faceModel.setUrlImageFace(verificaImageProcedimentoPorFaceOrcamento(tbprocedimento.getInfacedente(), true));
                                        faceModel.getSetProcedimentosFace().add(tbprocedimento);
                                        achouFace = true;
                                        break;
                                    } else {
                                        faceModel.getSetProcedimentosFace().add(tbprocedimento);
                                        achouFace = true;
                                        break;
                                    }
                                }
                            }
                        }
                        if (achouFace) {
                            break;
                        }
                    }
                } else if (tbprocedimento.getIddente().getNrposicao() >= 71 && tbprocedimento.getIddente().getNrposicao() <= 85) {
                    boolean achouFace = false;

                    for (DenteModel denteModel : listDentesInferiores) {
                        if (denteModel.getNrDente().equals(tbprocedimento.getIddente().getNrposicao())) {

                            for (FaceModel faceModel : denteModel.getListFacesDente()) {

                                if (faceModel.getNrFace().equals(tbprocedimento.getInfacedente())) {

                                    if (faceModel.getSetProcedimentosFace().isEmpty()) {
                                        faceModel.setUrlImageFace(verificaImageProcedimentoPorFaceOrcamento(tbprocedimento.getInfacedente(), false));
                                        faceModel.getSetProcedimentosFace().add(tbprocedimento);
                                        achouFace = true;
                                        break;
                                    } else {
                                        faceModel.getSetProcedimentosFace().add(tbprocedimento);
                                        achouFace = true;
                                        break;
                                    }
                                }
                            }
                        }
                        if (achouFace) {
                            break;
                        }
                    }
                } else {
                    boolean achouFace = false;

                    for (DenteModel denteModel : listDentesInferioresInfantil) {
                        if (denteModel.getNrDente().equals(tbprocedimento.getIddente().getNrposicao())) {

                            for (FaceModel faceModel : denteModel.getListFacesDente()) {

                                if (faceModel.getNrFace().equals(tbprocedimento.getInfacedente())) {

                                    if (faceModel.getSetProcedimentosFace().isEmpty()) {
                                        faceModel.setUrlImageFace(verificaImageProcedimentoPorFaceOrcamento(tbprocedimento.getInfacedente(), false));
                                        faceModel.getSetProcedimentosFace().add(tbprocedimento);
                                        achouFace = true;
                                        break;
                                    } else {
                                        faceModel.getSetProcedimentosFace().add(tbprocedimento);
                                        achouFace = true;
                                        break;
                                    }
                                }
                            }
                        }
                        if (achouFace) {
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * metodo para executar a busca de agendamentos para atendimentos.
     */
    public void search() {
        listTbagendamentos
                = atendimentoLogic.findAllTbagendamentoByDataAndPacienteAndFuncionarioAndStatusPresente(dataSearch, tbcliente, tbfuncionario);
    }

    /**
     * metodo utilizado para carregar a lista de servicos disponivel para
     * procedimento.
     */
    public void atualizaListaTbServicos() {
        listTbservicos = atendimentoLogic.findAllTbservicosByIdSubEspecialidade(tbsubespecialidade);
    }

    /**
     * metodo utilizado para finalizar atendimento para um determinado paciente.
     *
     * @param option
     */
    public void finalizarAtendimento(boolean option) {
        if (option) {
            if (tbatendimento.getIdagendamento().getIdcliente().getObservacao() != null
                    && !tbatendimento.getIdagendamento().getIdcliente().getObservacao().isEmpty()) {
                if (atendimentoLogic.finalizarAtendimento(tbatendimento)) {
                    clienteLogic.editarCliente(tbatendimento.getIdagendamento().getIdcliente());
                    fecharOrcamento();
                    AbstractFacesContextUtils.redirectPage(PagesUrl.URL_ATENDIMENTO_LIST);
                    AbstractFacesContextUtils.addMessageInfo("Atendimento finalizado com sucesso!");
                } else {
                    AbstractFacesContextUtils.addMessageWarn("Falha ao finalizar atendimento.");
                }

            } else {
                AbstractFacesContextUtils.addMessageWarn("Campo observação deve ser preenchido.");
            }
        } else {

            List<Tbprocedimento> procedimentosToCreate = criaListaProcedimento();

            if (atendimentoLogic.finalizarOrcamento(tbatendimento, tborcamento, procedimentosToCreate)) {
                AbstractFacesContextUtils.redirectPage(PagesUrl.URL_ATENDIMENTO_LIST);
                AbstractFacesContextUtils.addMessageInfo("Atendimento finalizado com sucesso!");
            } else {
                AbstractFacesContextUtils.addMessageWarn("Falha ao finalizar atendimento.");
            }
        }
    }

    /**
     * metodo utilizado para remover procedimento de um determinado dente.
     */
    public void removeTbprocedimentoOrcamento() {
        if (tbprocedimentoToRemove != null) {
            listTbprocedimentosGeral = atendimentoLogic.removeTbprocedimentoSelected(tbprocedimentoToRemove, listTbprocedimentosGeral);

            if (tbprocedimentoToRemove.getIddente().getNrposicao() >= 11 && tbprocedimentoToRemove.getIddente().getNrposicao() <= 28) {
                boolean achouFace = false;

                for (DenteModel denteModel : listDentesSuperioresOrcamento) {
                    if (denteModel.getNrDente().equals(tbprocedimentoToRemove.getIddente().getNrposicao())) {

                        for (FaceModel faceModel : denteModel.getListFacesDente()) {

                            if (faceModel.getNrFace().equals(tbprocedimentoToRemove.getInfacedente())) {
                                if (!faceModel.getListProcedimentosFace().isEmpty()) {
                                    if (faceModel.getListProcedimentosFace().remove(tbprocedimentoToRemove)) {

                                        if (faceModel.getListProcedimentosFace().isEmpty()) {
                                            faceModel.setUrlImageFace(verificaImageInicialPorFaceOrcamento(tbprocedimentoToRemove.getInfacedente(), true));
                                        }

                                    } else {
                                        AbstractFacesContextUtils.addMessageInfo("Erro ao remover procedimento.");
                                    }

                                    achouFace = true;
                                    break;
                                } else {
                                    AbstractFacesContextUtils.addMessageInfo("Nenhum procedimento para remover.");
                                }
                            }
                        }
                    }

                    if (achouFace) {
                        AbstractFacesContextUtils.addMessageInfo("Procedimento removido com sucesso.");
                        break;
                    }
                }

            } else if (tbprocedimentoToRemove.getIddente().getNrposicao() >= 51 && tbprocedimentoToRemove.getIddente().getNrposicao() <= 65) {
                boolean achouFace = false;

                for (DenteModel denteModel : listDentesSuperioresInfantilOrcamento) {
                    if (denteModel.getNrDente().equals(tbprocedimentoToRemove.getIddente().getNrposicao())) {

                        for (FaceModel faceModel : denteModel.getListFacesDente()) {

                            if (faceModel.getNrFace().equals(tbprocedimentoToRemove.getInfacedente())) {
                                if (!faceModel.getListProcedimentosFace().isEmpty()) {
                                    if (faceModel.getListProcedimentosFace().remove(tbprocedimentoToRemove)) {

                                        if (faceModel.getListProcedimentosFace().isEmpty()) {
                                            faceModel.setUrlImageFace(verificaImageInicialPorFaceOrcamento(tbprocedimentoToRemove.getInfacedente(), true));
                                        }

                                    } else {
                                        AbstractFacesContextUtils.addMessageInfo("Erro ao remover procedimento.");
                                    }

                                    achouFace = true;
                                    break;
                                } else {
                                    AbstractFacesContextUtils.addMessageInfo("Nenhum procedimento para remover.");
                                }
                            }
                        }
                    }

                    if (achouFace) {
                        AbstractFacesContextUtils.addMessageInfo("Procedimento removido com sucesso.");
                        break;
                    }
                }
            } else if (tbprocedimentoToRemove.getIddente().getNrposicao() >= 71 && tbprocedimentoToRemove.getIddente().getNrposicao() <= 85) {
                boolean achouFace = false;

                for (DenteModel denteModel : listDentesInferioresInfantilOrcamento) {
                    if (denteModel.getNrDente().equals(tbprocedimentoToRemove.getIddente().getNrposicao())) {

                        for (FaceModel faceModel : denteModel.getListFacesDente()) {

                            if (faceModel.getNrFace().equals(tbprocedimentoToRemove.getInfacedente())) {

                                if (!faceModel.getListProcedimentosFace().isEmpty()) {

                                    if (faceModel.getListProcedimentosFace().remove(tbprocedimentoToRemove)) {
                                        if (faceModel.getListProcedimentosFace().isEmpty()) {
                                            faceModel.setUrlImageFace(verificaImageInicialPorFaceOrcamento(tbprocedimentoToRemove.getInfacedente(), false));
                                        }
                                    } else {
                                        AbstractFacesContextUtils.addMessageInfo("Erro ao remover procedimento.");
                                    }

                                    achouFace = true;
                                    break;

                                } else {
                                    AbstractFacesContextUtils.addMessageInfo("Nenhum procedimento para remover.");
                                }
                            }
                        }
                    }

                    if (achouFace) {
                        AbstractFacesContextUtils.addMessageInfo("Procedimento removido com sucesso.");
                        break;
                    }
                }
            } else {
                boolean achouFace = false;

                for (DenteModel denteModel : listDentesInferioresOrcamento) {
                    if (denteModel.getNrDente().equals(tbprocedimentoToRemove.getIddente().getNrposicao())) {

                        for (FaceModel faceModel : denteModel.getListFacesDente()) {

                            if (faceModel.getNrFace().equals(tbprocedimentoToRemove.getInfacedente())) {
                                if (!faceModel.getListProcedimentosFace().isEmpty()) {
                                    if (faceModel.getListProcedimentosFace().remove(tbprocedimentoToRemove)) {

                                        if (faceModel.getListProcedimentosFace().isEmpty()) {
                                            faceModel.setUrlImageFace(verificaImageInicialPorFaceOrcamento(tbprocedimentoToRemove.getInfacedente(), false));
                                        }

                                    } else {
                                        AbstractFacesContextUtils.addMessageInfo("Erro ao remover procedimento.");
                                    }

                                    achouFace = true;
                                    break;
                                } else {
                                    AbstractFacesContextUtils.addMessageInfo("Nenhum procedimento para remover.");
                                }
                            }
                        }
                    }

                    if (achouFace) {
                        AbstractFacesContextUtils.addMessageInfo("Procedimento removido com sucesso.");
                        break;
                    }
                }
            }
        } else {
            AbstractFacesContextUtils.addMessageInfo("Procedimento não selecionado para remoção.");
        }
    }

    /**
     * metodo utilizado para remover procedimento de um determinado dente
     * atendimento.
     */
    public void removeProcedimentoAtendimento() {

        if (tbprocedimentoToRemove != null) {

            if (tbprocedimentoToRemove.getIddente().getNrposicao() >= 11 && tbprocedimentoToRemove.getIddente().getNrposicao() <= 28) {
                boolean achouFace = false;

                for (DenteModel denteModel : listDentesSuperiores) {
                    if (denteModel.getNrDente().equals(tbprocedimentoToRemove.getIddente().getNrposicao())) {

                        for (FaceModel faceModel : denteModel.getListFacesDente()) {

                            if (faceModel.getNrFace().equals(tbprocedimentoToRemove.getInfacedente())) {
                                if (!faceModel.getSetProcedimentosFace().isEmpty()) {

                                    if (faceModel.getSetProcedimentosFace().remove(tbprocedimentoToRemove)
                                            && procedimentoLogic.removeTbprocedimento(tbprocedimentoToRemove)) {

                                        if (faceModel.getSetProcedimentosFace().isEmpty()) {
                                            faceModel.setUrlImageFace(verificaImageInicialPorFaceOrcamento(tbprocedimentoToRemove.getInfacedente(), true));
                                        }

                                    } else {
                                        AbstractFacesContextUtils.addMessageWarn("Erro ao remover procedimento.");
                                    }

                                    achouFace = true;
                                    break;
                                } else {
                                    AbstractFacesContextUtils.addMessageWarn("Nenhum procedimento para remover.");
                                }
                            }
                        }
                    }

                    if (achouFace) {
                        AbstractFacesContextUtils.addMessageInfo("Procedimento removido com sucesso.");
                        break;
                    }
                }

            } else if (tbprocedimentoToRemove.getIddente().getNrposicao() >= 51 && tbprocedimentoToRemove.getIddente().getNrposicao() <= 65) {
                boolean achouFace = false;

                for (DenteModel denteModel : listDentesSuperioresInfantil) {
                    if (denteModel.getNrDente().equals(tbprocedimentoToRemove.getIddente().getNrposicao())) {

                        for (FaceModel faceModel : denteModel.getListFacesDente()) {

                            if (faceModel.getNrFace().equals(tbprocedimentoToRemove.getInfacedente())) {
                                if (!faceModel.getSetProcedimentosFace().isEmpty()) {
                                    if (faceModel.getSetProcedimentosFace().remove(tbprocedimentoToRemove)
                                            && procedimentoLogic.removeTbprocedimento(tbprocedimentoToRemove)) {

                                        if (faceModel.getSetProcedimentosFace().isEmpty()) {
                                            faceModel.setUrlImageFace(verificaImageInicialPorFaceOrcamento(tbprocedimentoToRemove.getInfacedente(), true));
                                        }

                                    } else {
                                        AbstractFacesContextUtils.addMessageWarn("Erro ao remover procedimento.");
                                    }

                                    achouFace = true;
                                    break;
                                } else {
                                    AbstractFacesContextUtils.addMessageWarn("Nenhum procedimento para remover.");
                                }
                            }
                        }
                    }

                    if (achouFace) {
                        AbstractFacesContextUtils.addMessageInfo("Procedimento removido com sucesso.");
                        break;
                    }
                }
            } else if (tbprocedimentoToRemove.getIddente().getNrposicao() >= 71 && tbprocedimentoToRemove.getIddente().getNrposicao() <= 85) {
                boolean achouFace = false;

                for (DenteModel denteModel : listDentesInferioresInfantil) {
                    if (denteModel.getNrDente().equals(tbprocedimentoToRemove.getIddente().getNrposicao())) {

                        for (FaceModel faceModel : denteModel.getListFacesDente()) {

                            if (faceModel.getNrFace().equals(tbprocedimentoToRemove.getInfacedente())) {

                                if (!faceModel.getSetProcedimentosFace().isEmpty()) {
                                    if (faceModel.getSetProcedimentosFace().remove(tbprocedimentoToRemove)
                                            && procedimentoLogic.removeTbprocedimento(tbprocedimentoToRemove)) {
                                        if (faceModel.getSetProcedimentosFace().isEmpty()) {
                                            faceModel.setUrlImageFace(verificaImageInicialPorFaceOrcamento(tbprocedimentoToRemove.getInfacedente(), false));
                                        }
                                    } else {
                                        AbstractFacesContextUtils.addMessageWarn("Erro ao remover procedimento.");
                                    }

                                    achouFace = true;
                                    break;

                                } else {
                                    AbstractFacesContextUtils.addMessageWarn("Nenhum procedimento para remover.");
                                }
                            }
                        }
                    }

                    if (achouFace) {
                        AbstractFacesContextUtils.addMessageInfo("Procedimento removido com sucesso.");
                        break;
                    }
                }
            } else {
                boolean achouFace = false;

                for (DenteModel denteModel : listDentesInferiores) {
                    if (denteModel.getNrDente().equals(tbprocedimentoToRemove.getIddente().getNrposicao())) {

                        for (FaceModel faceModel : denteModel.getListFacesDente()) {

                            if (faceModel.getNrFace().equals(tbprocedimentoToRemove.getInfacedente())) {
                                if (!faceModel.getSetProcedimentosFace().isEmpty()) {
                                    if (faceModel.getSetProcedimentosFace().remove(tbprocedimentoToRemove)
                                            && procedimentoLogic.removeTbprocedimento(tbprocedimentoToRemove)) {

                                        if (faceModel.getSetProcedimentosFace().isEmpty()) {
                                            faceModel.setUrlImageFace(verificaImageInicialPorFaceOrcamento(tbprocedimentoToRemove.getInfacedente(), false));
                                        }

                                    } else {
                                        AbstractFacesContextUtils.addMessageWarn("Erro ao remover procedimento.");
                                    }

                                    achouFace = true;
                                    break;
                                } else {
                                    AbstractFacesContextUtils.addMessageWarn("Nenhum procedimento para remover.");
                                }
                            }
                        }
                    }

                    if (achouFace) {
                        AbstractFacesContextUtils.addMessageInfo("Procedimento removido com sucesso.");
                        break;
                    }
                }
            }
        } else {
            AbstractFacesContextUtils.addMessageWarn("Procedimento não selecionado para remoção.");
        }
    }

    /**
     * metodo utilizado para adicionar um procedimento manualmente informado no
     * formulario para o orcamento.
     */
    public void criarProcedimentoManual() {

        if (tbservicoManual != null) {

            Tbprocedimento procedimento;

            if (tbdente == null) {
                AbstractFacesContextUtils.addMessageWarn("Nenhum dente selecionado.");
            } else if (inFace == null) {
                AbstractFacesContextUtils.addMessageWarn("Nenhum face selecionada.");
            } else {
                procedimento = procedimentoLogic.adicionarProcedimentoOrcamento(tbservicoManual, tbdente, inFace, qtProcedimento, tbagendamento);
                listTbprocedimentosGeral.add(procedimento);

                if (tbdente.getNrposicao() >= 11 && tbdente.getNrposicao() <= 28) {

                    boolean achouFace = false;

                    for (DenteModel denteModel : listDentesSuperioresOrcamento) {
                        if (denteModel.getNrDente().equals(tbdente.getNrposicao())) {

                            for (FaceModel faceModel : denteModel.getListFacesDente()) {
                                if (faceModel.getNrFace().equals(inFace)) {
                                    achouFace = true;
                                    faceModel.setUrlImageFace(verificaImageProcedimentoPorFaceOrcamento(inFace, true));
                                    faceModel.getListProcedimentosFace().add(procedimento);
                                    break;
                                }
                            }
                        }

                        if (achouFace) {
                            break;
                        }
                    }

                } else if (tbdente.getNrposicao() >= 51 && tbdente.getNrposicao() <= 65) {
                    boolean achouFace = false;

                    for (DenteModel denteModel : listDentesSuperioresInfantilOrcamento) {
                        if (denteModel.getNrDente().equals(tbdente.getNrposicao())) {

                            for (FaceModel faceModel : denteModel.getListFacesDente()) {
                                if (faceModel.getNrFace().equals(inFace)) {
                                    achouFace = true;
                                    faceModel.setUrlImageFace(verificaImageProcedimentoPorFaceOrcamento(inFace, true));
                                    faceModel.getListProcedimentosFace().add(procedimento);
                                    break;
                                }
                            }
                        }

                        if (achouFace) {
                            break;
                        }
                    }

                } else if (tbdente.getNrposicao() >= 71 && tbdente.getNrposicao() <= 85) {
                    boolean achouFace = false;

                    for (DenteModel denteModel : listDentesInferioresInfantilOrcamento) {
                        if (denteModel.getNrDente().equals(tbdente.getNrposicao())) {

                            for (FaceModel faceModel : denteModel.getListFacesDente()) {
                                if (faceModel.getNrFace().equals(inFace)) {
                                    achouFace = true;
                                    faceModel.setUrlImageFace(verificaImageProcedimentoPorFaceOrcamento(inFace, false));
                                    faceModel.getListProcedimentosFace().add(procedimento);
                                    break;
                                }
                            }
                        }

                        if (achouFace) {
                            break;
                        }
                    }
                } else {

                    boolean achouFace = false;

                    for (DenteModel denteModel : listDentesInferioresOrcamento) {
                        if (denteModel.getNrDente().equals(tbdente.getNrposicao())) {

                            for (FaceModel faceModel : denteModel.getListFacesDente()) {
                                if (faceModel.getNrFace().equals(inFace)) {
                                    achouFace = true;
                                    faceModel.setUrlImageFace(verificaImageProcedimentoPorFaceOrcamento(inFace, false));
                                    faceModel.getListProcedimentosFace().add(procedimento);
                                    break;
                                }
                            }
                        }

                        if (achouFace) {
                            break;
                        }
                    }
                }
            }

        } else {
            AbstractFacesContextUtils.addMessageWarn("Nenhum procedimento selecionado.");
        }

        tbservicoManual = null;
        tbdente = null;
        inFace = null;
        qtProcedimento = null;
    }

    /**
     * metodo utilizado para adicionar um procedimento manualmente informado no
     * formulario para o atendimento.
     */
    public void criarProcedimentoManualAtendimento() {

        if (tbservicoManual != null) {

            Tbprocedimento procedimento;

            if (tbdente == null) {
                AbstractFacesContextUtils.addMessageWarn("Nenhum dente selecionado.");
            } else if (inFace == null) {
                AbstractFacesContextUtils.addMessageWarn("Nenhum face selecionada.");
            } else {
                procedimento = procedimentoLogic.adicionarProcedimentoManual(tbservicoManual, tbdente, inFace, qtProcedimento, tbagendamento);
                procedimento.setIdorcamento(tborcamento);
                procedimentoLogic.editTbprocedimento(procedimento);

                listTbprocedimentosGeral = procedimentoLogic.findAllTbprocedimentoByIdOrcamento(tborcamento);

                if (tbdente.getNrposicao() >= 11 && tbdente.getNrposicao() <= 28) {

                    boolean achouFace = false;

                    for (DenteModel denteModel : listDentesSuperiores) {
                        if (denteModel.getNrDente().equals(tbdente.getNrposicao())) {

                            for (FaceModel faceModel : denteModel.getListFacesDente()) {
                                if (faceModel.getNrFace().equals(inFace)) {
                                    achouFace = true;
                                    faceModel.setUrlImageFace(verificaImageProcedimentoPorFaceOrcamento(inFace, true));
                                    faceModel.getSetProcedimentosFace().add(procedimento);
                                    break;
                                }
                            }
                        }

                        if (achouFace) {
                            break;
                        }
                    }

                } else if (tbdente.getNrposicao() >= 51 && tbdente.getNrposicao() <= 65) {
                    boolean achouFace = false;

                    for (DenteModel denteModel : listDentesSuperioresInfantil) {
                        if (denteModel.getNrDente().equals(tbdente.getNrposicao())) {

                            for (FaceModel faceModel : denteModel.getListFacesDente()) {
                                if (faceModel.getNrFace().equals(inFace)) {
                                    achouFace = true;
                                    faceModel.setUrlImageFace(verificaImageProcedimentoPorFaceOrcamento(inFace, true));
                                    faceModel.getSetProcedimentosFace().add(procedimento);
                                    break;
                                }
                            }
                        }

                        if (achouFace) {
                            break;
                        }
                    }

                } else if (tbdente.getNrposicao() >= 71 && tbdente.getNrposicao() <= 85) {
                    boolean achouFace = false;

                    for (DenteModel denteModel : listDentesInferioresInfantil) {
                        if (denteModel.getNrDente().equals(tbdente.getNrposicao())) {

                            for (FaceModel faceModel : denteModel.getListFacesDente()) {
                                if (faceModel.getNrFace().equals(inFace)) {
                                    achouFace = true;
                                    faceModel.setUrlImageFace(verificaImageProcedimentoPorFaceOrcamento(inFace, false));
                                    faceModel.getSetProcedimentosFace().add(procedimento);
                                    break;
                                }
                            }
                        }

                        if (achouFace) {
                            break;
                        }
                    }
                } else {

                    boolean achouFace = false;

                    for (DenteModel denteModel : listDentesInferiores) {
                        if (denteModel.getNrDente().equals(tbdente.getNrposicao())) {

                            for (FaceModel faceModel : denteModel.getListFacesDente()) {
                                if (faceModel.getNrFace().equals(inFace)) {
                                    achouFace = true;
                                    faceModel.setUrlImageFace(verificaImageProcedimentoPorFaceOrcamento(inFace, false));
                                    faceModel.getSetProcedimentosFace().add(procedimento);
                                    break;
                                }
                            }
                        }

                        if (achouFace) {
                            break;
                        }
                    }
                }
            }

        } else {
            AbstractFacesContextUtils.addMessageWarn("Nenhum procedimento selecionado.");
        }

        tbservicoManual = null;
        tbdente = null;
        inFace = null;
        qtProcedimento = null;
    }

    /**
     * metodo utilizado para adicionar procedimento em um orcamento pelo
     * odontograma.
     */
    public void adicionarOrcamentoOdontograma() {

        if (tbservico != null) {

            Tbdente dente = denteLogic.findTbdenteByNrPosicao(denteSelected);
            Tbprocedimento procedimento;

            if (validarDadosAdicionarProcedimentoOdontograma(faceSelected, tbservico)) {
                if (dente != null) {
                    procedimento = procedimentoLogic.adicionarProcedimentoOdontograma(dente, faceSelected, tbagendamento, tbservico);
                    listTbprocedimentosGeral.add(procedimento);

                    if (denteSelected >= 11 && denteSelected <= 28) {

                        for (DenteModel denteModel : listDentesSuperioresOrcamento) {
                            if (denteModel.getNrDente().equals(denteSelected)) {

                                denteModel.getListFacesDente().get(posicao).setUrlImageFace(verificaImageProcedimentoPorFaceOrcamento(faceSelected, true));
                                denteModel.getListFacesDente().get(posicao).getListProcedimentosFace().add(procedimento);
                                AbstractFacesContextUtils.addMessageInfo("Procedimento adicionado com sucesso.");
                                break;
                            }
                        }

                    } else if (denteSelected >= 51 && denteSelected <= 65) {

                        for (DenteModel denteModel : listDentesSuperioresInfantilOrcamento) {
                            if (denteModel.getNrDente().equals(denteSelected)) {

                                denteModel.getListFacesDente().get(posicao).setUrlImageFace(verificaImageProcedimentoPorFaceOrcamento(faceSelected, true));
                                denteModel.getListFacesDente().get(posicao).getListProcedimentosFace().add(procedimento);
                                AbstractFacesContextUtils.addMessageInfo("Procedimento adicionado com sucesso.");
                                break;
                            }
                        }
                    } else if (denteSelected >= 71 && denteSelected <= 85) {
                        for (DenteModel denteModel : listDentesInferioresInfantilOrcamento) {
                            if (denteModel.getNrDente().equals(denteSelected)) {

                                denteModel.getListFacesDente().get(posicao).setUrlImageFace(verificaImageProcedimentoPorFaceOrcamento(faceSelected, false));
                                denteModel.getListFacesDente().get(posicao).getListProcedimentosFace().add(procedimento);
                                AbstractFacesContextUtils.addMessageInfo("Procedimento adicionado com sucesso.");
                                break;
                            }
                        }
                    } else {
                        for (DenteModel denteModel : listDentesInferioresOrcamento) {
                            if (denteModel.getNrDente().equals(denteSelected)) {

                                denteModel.getListFacesDente().get(posicao).setUrlImageFace(verificaImageProcedimentoPorFaceOrcamento(faceSelected, false));
                                denteModel.getListFacesDente().get(posicao).getListProcedimentosFace().add(procedimento);
                                AbstractFacesContextUtils.addMessageInfo("Procedimento adicionado com sucesso.");
                                break;
                            }
                        }
                    }
                }
            }

        } else {
            AbstractFacesContextUtils.addMessageWarn("Nenhum procedimento selecionado.");
        }

    }

    /**
     * metodo utilizado para adicionar procedimento em um atendimento pelo
     * odontograma.
     */
    public void adicionarProcedimentoOdontogramaAtendimento() {
        if (tbservico != null) {

            Tbdente dente = denteLogic.findTbdenteByNrPosicao(denteSelected);
            Tbprocedimento procedimento;

            if (validarDadosAdicionarProcedimentoOdontograma(faceSelected, tbservico)) {
                if (dente != null) {
                    procedimento = procedimentoLogic.adicionarProcedimentoOdontograma(dente, faceSelected, tbatendimento.getIdagendamento(), tbservico);
                    procedimento.setIdorcamento(tborcamento);
                    procedimentoLogic.createTbprocedimento(procedimento);
                    procedimento = procedimentoLogic.findTbprocedimentoByIdProcedimento(procedimento.getIdprocedimento());

                    listTbprocedimentosGeral = atendimentoLogic.findAllTbprocedimentoByIdOrcamento(tborcamento);

                    if (denteSelected >= 11 && denteSelected <= 28) {

                        for (DenteModel denteModel : listDentesSuperiores) {
                            if (denteModel.getNrDente().equals(denteSelected)) {

                                denteModel.getListFacesDente().get(posicao).setUrlImageFace(verificaImageProcedimentoPorFaceOrcamento(faceSelected, true));
                                denteModel.getListFacesDente().get(posicao).getSetProcedimentosFace().add(procedimento);
                                AbstractFacesContextUtils.addMessageInfo("Procedimento adicionado com sucesso.");
                                break;
                            }
                        }

                    } else if (denteSelected >= 51 && denteSelected <= 65) {

                        for (DenteModel denteModel : listDentesSuperioresInfantil) {
                            if (denteModel.getNrDente().equals(denteSelected)) {

                                denteModel.getListFacesDente().get(posicao).setUrlImageFace(verificaImageProcedimentoPorFaceOrcamento(faceSelected, true));
                                denteModel.getListFacesDente().get(posicao).getListProcedimentosFace().add(procedimento);
                                AbstractFacesContextUtils.addMessageInfo("Procedimento adicionado com sucesso.");
                                break;
                            }
                        }
                    } else if (denteSelected >= 71 && denteSelected <= 85) {
                        for (DenteModel denteModel : listDentesInferioresInfantil) {
                            if (denteModel.getNrDente().equals(denteSelected)) {

                                denteModel.getListFacesDente().get(posicao).setUrlImageFace(verificaImageProcedimentoPorFaceOrcamento(faceSelected, false));
                                denteModel.getListFacesDente().get(posicao).getListProcedimentosFace().add(procedimento);
                                AbstractFacesContextUtils.addMessageInfo("Procedimento adicionado com sucesso.");
                                break;
                            }
                        }
                    } else {
                        for (DenteModel denteModel : listDentesInferiores) {
                            if (denteModel.getNrDente().equals(denteSelected)) {

                                denteModel.getListFacesDente().get(posicao).setUrlImageFace(verificaImageProcedimentoPorFaceOrcamento(faceSelected, false));
                                denteModel.getListFacesDente().get(posicao).getListProcedimentosFace().add(procedimento);
                                AbstractFacesContextUtils.addMessageInfo("Procedimento adicionado com sucesso.");
                                break;
                            }
                        }
                    }
                }
            }

        } else {
            AbstractFacesContextUtils.addMessageWarn("Nenhum procedimento selecionado.");
        }
    }

    /**
     * metodo utilizado para carregar a lista de procedimentos para um
     * determinado dente.
     */
    public void loadProcedimentosPorDente() {

        if (denteSelected >= 11 && denteSelected <= 28) {

            listTbprocedimentosPorDente.clear();
            nmFaceSelected = retornaNmFaceSelected(faceSelected);

            for (final Tbprocedimento tbprocedimento : listTbprocedimentosGeral) {
                if (denteSelected.equals(tbprocedimento.getIddente().getNrposicao())
                        && faceSelected.equals(tbprocedimento.getInfacedente())) {
                    listTbprocedimentosPorDente.add(tbprocedimento);
                }
            }

        } else if (denteSelected >= 51 && denteSelected <= 65) {

            listTbprocedimentosPorDente.clear();
            nmFaceSelected = retornaNmFaceSelected(faceSelected);

            for (final Tbprocedimento tbprocedimento : listTbprocedimentosGeral) {
                if (denteSelected.equals(tbprocedimento.getIddente().getNrposicao())
                        && faceSelected.equals(tbprocedimento.getInfacedente())) {
                    nmFaceSelected = retornaNmFaceSelected(faceSelected);
                    listTbprocedimentosPorDente.add(tbprocedimento);
                }
            }

        } else if (denteSelected >= 71 && denteSelected <= 85) {

            listTbprocedimentosPorDente.clear();
            nmFaceSelected = retornaNmFaceSelected(faceSelected);

            for (final Tbprocedimento tbprocedimento : listTbprocedimentosGeral) {
                if (denteSelected.equals(tbprocedimento.getIddente().getNrposicao())
                        && faceSelected.equals(tbprocedimento.getInfacedente())) {
                    nmFaceSelected = retornaNmFaceSelected(faceSelected);
                    listTbprocedimentosPorDente.add(tbprocedimento);
                }
            }

        } else {
            listTbprocedimentosPorDente.clear();
            nmFaceSelected = retornaNmFaceSelected(faceSelected);

            for (final Tbprocedimento tbprocedimento : listTbprocedimentosGeral) {
                if (denteSelected.equals(tbprocedimento.getIddente().getNrposicao())
                        && faceSelected.equals(tbprocedimento.getInfacedente())) {
                    nmFaceSelected = retornaNmFaceSelected(faceSelected);
                    listTbprocedimentosPorDente.add(tbprocedimento);
                }
            }
        }
    }

    /**
     * metodo utilizado para carregar a lista de procedimentos para um
     * determinado dente orcamento.
     */
    public void loadProcedimentosPorDenteOrcamento() {

        if (denteSelected >= 11 && denteSelected <= 28) {

            boolean achou = false;
            listTbprocedimentosPorDente.clear();
            nmFaceSelected = retornaNmFaceSelected(faceSelected);

            for (DenteModel denteModel : listDentesSuperioresOrcamento) {
                if (denteModel.getNrDente().equals(denteSelected)) {
                    for (FaceModel faceModel : denteModel.getListFacesDente()) {
                        if (faceModel.getNrFace().equals(faceSelected)) {
                            listTbprocedimentosPorDente.addAll(faceModel.getListProcedimentosFace());
                            achou = true;
                            break;
                        }
                    }
                }

                if (achou) {
                    break;
                }
            }

        } else if (denteSelected >= 51 && denteSelected <= 65) {

            listTbprocedimentosPorDente.clear();
            nmFaceSelected = retornaNmFaceSelected(faceSelected);

            for (final Tbprocedimento tbprocedimento : listTbprocedimentosGeral) {
                if (denteSelected.equals(tbprocedimento.getIddente().getNrposicao())
                        && faceSelected.equals(tbprocedimento.getInfacedente())) {
                    nmFaceSelected = retornaNmFaceSelected(faceSelected);
                    listTbprocedimentosPorDente.add(tbprocedimento);
                }
            }

        } else if (denteSelected >= 71 && denteSelected <= 85) {

            listTbprocedimentosPorDente.clear();
            nmFaceSelected = retornaNmFaceSelected(faceSelected);

            for (final Tbprocedimento tbprocedimento : listTbprocedimentosGeral) {
                if (denteSelected.equals(tbprocedimento.getIddente().getNrposicao())
                        && faceSelected.equals(tbprocedimento.getInfacedente())) {
                    nmFaceSelected = retornaNmFaceSelected(faceSelected);
                    listTbprocedimentosPorDente.add(tbprocedimento);
                }
            }

        } else {
            listTbprocedimentosPorDente.clear();
            nmFaceSelected = retornaNmFaceSelected(faceSelected);

            for (final Tbprocedimento tbprocedimento : listTbprocedimentosGeral) {
                if (denteSelected.equals(tbprocedimento.getIddente().getNrposicao())
                        && faceSelected.equals(tbprocedimento.getInfacedente())) {
                    nmFaceSelected = retornaNmFaceSelected(faceSelected);
                    listTbprocedimentosPorDente.add(tbprocedimento);
                }
            }
        }
    }

    /**
     * metodo utilizado para iniciar um procedimento.
     */
    public void iniciarProcedimentoAtendimento() {
        if (tbprocedimentoSelected != null) {
            if (atendimentoLogic.iniciarProcedimento(tbprocedimentoSelected)) {
                tbprocedimentoSelected = null;
                AbstractFacesContextUtils.addMessageInfo("Procedimento iniciado com sucesso!");
            } else {
                AbstractFacesContextUtils.addMessageWarn("Não é possível iniciar procedimento!");
            }
        } else {
            AbstractFacesContextUtils.addMessageWarn("Não há item selecionado para iniciar procedimento!");
        }
    }

    /**
     * metodo utilizado para finalizar um procedimento.
     */
    public void finalizarProcedimentoAtendimento() {
        if (tbprocedimentoSelected != null) {
            if (atendimentoLogic.finalizarProcedimento(tbprocedimentoSelected)) {
                //fecharOrcamento();
                tbprocedimentoSelected = null;
                AbstractFacesContextUtils.addMessageInfo("Procedimento finalizado com sucesso!");
            } else {
                AbstractFacesContextUtils.addMessageWarn("Não é possível finalizar procedimento!");
            }
        } else {
            AbstractFacesContextUtils.addMessageWarn("Não há item selecionado para finalizar procedimento!");
        }
    }

    /**
     * metodo utilizado para cancelar um procedimento.
     */
    public void cancelarProcedimentoAtendimento() {
        if (tbprocedimentoSelected != null) {
            if (atendimentoLogic.cancelarProcedimento(tbprocedimentoSelected)) {
                tbprocedimentoSelected = null;
                AbstractFacesContextUtils.addMessageInfo("Procedimento cancelado com sucesso!");
            } else {
                AbstractFacesContextUtils.addMessageWarn("É necessário que o procedimento esteja lançado e com status não Realizado!");
            }
        } else {
            AbstractFacesContextUtils.addMessageWarn("Não há item selecionado para cancelar procedimento!");
        }
    }

    /**
     * metodo utilizado para ausentar paciente.
     */
    public void ausentarPaciente() {
        if (tbagendamento != null) {

            if (atendimentoLogic.ausentarPaciente(tbagendamento)) {
                AbstractFacesContextUtils.addMessageInfo("Paciente foi definido como ausente!");
            } else {
                AbstractFacesContextUtils.addMessageWarn("Não é possível ausentar paciente!");
            }
        } else {
            AbstractFacesContextUtils.addMessageWarn("Não há item selecionado para ausentar!");
        }
    }

    /**
     * metodo utilizado para carregar as informações de todos os orcamentos do
     * cliente.
     */
    public void loadConsultasOrcamentos() {

        listOrcamentoModels = new ArrayList<>();

        for (final Tborcamento orcamento : orcamentoLogic.findAllTborcamentoByIdCliente(tbatendimento.getIdagendamento().getIdcliente())) {
            OrcamentoModel orcamentoModel = new OrcamentoModel();
            orcamentoModel.setTborcamento(orcamento);
            orcamentoModel.setListTbprocedimentos(procedimentoLogic.findAllTbprocedimentoByIdOrcamento(orcamento));
            listOrcamentoModels.add(orcamentoModel);
        }
    }

    /**
     * metodo utilizado para carregar as informações de todos as consultas do
     * cliente.
     */
    public void loadConsultasOdontologicas() {
        consultasOdontologicas = atendimentoLogic.findAllTbatendimentosByIdClienteAndStatusAtendido(tbatendimento.getIdagendamento().getIdcliente());
    }

    /**
     * metodo utilizado para carregar as informações de todos os receituarios do
     * cliente.
     */
    public void loadConsultasReceituariosOdontologicos() {
        listTbreceituarios = receituarioLogic.findAllTbreceituarioByIdCliente(tbatendimento.getIdagendamento().getIdcliente());
    }

    /**
     * metodo utilizado para limpar os dados das lista odontograma.
     */
    private void clearListOdontograma() {
        listDentesInferiores.clear();
        listDentesInferioresInfantil.clear();
        listDentesSuperiores.clear();
        listDentesSuperioresInfantil.clear();
    }

    /**
     * metodo utilizado para limpar os dados das lista odontograma.
     */
    private void clearListOdontogramaOrcamento() {
        listDentesInferioresOrcamento.clear();
        listDentesInferioresInfantilOrcamento.clear();
        listDentesSuperioresOrcamento.clear();
        listDentesSuperioresInfantilOrcamento.clear();
    }

    /**
     * metodo utilizado para agrupar os procedimentos.
     *
     * @return list of procedimentos
     */
    private List<Tbprocedimento> criaListaProcedimento() {

        List<Tbprocedimento> procedimentosToCreate = new ArrayList<>();

        if (!listDentesSuperioresOrcamento.isEmpty()) {
            for (DenteModel denteModel : listDentesSuperioresOrcamento) {
                for (FaceModel faceModel : denteModel.getListFacesDente()) {
                    if (!faceModel.getListProcedimentosFace().isEmpty()) {
                        for (Tbprocedimento procedimento : faceModel.getListProcedimentosFace()) {
                            procedimentosToCreate.add(procedimento);
                        }
                    }
                }
            }
        }

        if (!listDentesSuperioresInfantilOrcamento.isEmpty()) {
            for (DenteModel denteModel : listDentesSuperioresInfantilOrcamento) {
                for (FaceModel faceModel : denteModel.getListFacesDente()) {
                    if (!faceModel.getListProcedimentosFace().isEmpty()) {
                        for (Tbprocedimento procedimento : faceModel.getListProcedimentosFace()) {
                            procedimentosToCreate.add(procedimento);
                        }
                    }
                }
            }
        }

        if (!listDentesInferioresOrcamento.isEmpty()) {
            for (DenteModel denteModel : listDentesInferioresOrcamento) {
                for (FaceModel faceModel : denteModel.getListFacesDente()) {
                    if (!faceModel.getListProcedimentosFace().isEmpty()) {
                        for (Tbprocedimento procedimento : faceModel.getListProcedimentosFace()) {
                            procedimentosToCreate.add(procedimento);
                        }
                    }
                }
            }
        }

        if (!listDentesInferioresInfantilOrcamento.isEmpty()) {
            for (DenteModel denteModel : listDentesInferioresInfantilOrcamento) {
                for (FaceModel faceModel : denteModel.getListFacesDente()) {
                    if (!faceModel.getListProcedimentosFace().isEmpty()) {
                        for (Tbprocedimento procedimento : faceModel.getListProcedimentosFace()) {
                            procedimentosToCreate.add(procedimento);
                        }
                    }
                }
            }
        }

        return procedimentosToCreate;
    }

    /**
     * metodo utilizado para iniciar o odontograma adulto.
     */
    private void inicializarOdontogramaAdulto() {

        System.out.println("iniciando odontograma adulto.");

        for (int i = 18; i >= 11; i--) {
            DenteModel denteModel = new DenteModel();
            denteModel.setIdDenteModel(i);
            denteModel.setNrDente(i);

            List<FaceModel> listStrings = new ArrayList<>();
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_RAIZ, StatusSiso.IN_FACE_DENTE_RAIZ, ImagesUrl.FACE_RAIZ_SUPERIOR, "Raiz"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_CERVICAL, StatusSiso.IN_FACE_DENTE_CERVICAL, ImagesUrl.FACE_CERVICAL, "Cervical"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_VESTIBULAR, StatusSiso.IN_FACE_DENTE_VESTIBULAR, ImagesUrl.FACE_VESTIBULAR, "Vestibular"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_DISTAL, StatusSiso.IN_FACE_DENTE_DISTAL, ImagesUrl.FACE_DISTAL, "Distal"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_OCLUSAL, StatusSiso.IN_FACE_DENTE_OCLUSAL, ImagesUrl.FACE_OCLUSAL, "Oclusal"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_MESIAL, StatusSiso.IN_FACE_DENTE_MESIAL, ImagesUrl.FACE_MESIAL, "Mesial"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_PALATINA, StatusSiso.IN_FACE_DENTE_PALATINA, ImagesUrl.FACE_PALATINA, "Palatina"));

            denteModel.setListFacesDente(listStrings);

            listDentesSuperiores.add(denteModel);
        }
        for (int i = 21; i <= 28; i++) {
            DenteModel denteModel = new DenteModel();
            denteModel.setIdDenteModel(i);
            denteModel.setNrDente(i);

            List<FaceModel> listStrings = new ArrayList<>();
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_RAIZ, StatusSiso.IN_FACE_DENTE_RAIZ, ImagesUrl.FACE_RAIZ_SUPERIOR, "Raiz"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_CERVICAL, StatusSiso.IN_FACE_DENTE_CERVICAL, ImagesUrl.FACE_CERVICAL, "Cervical"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_VESTIBULAR, StatusSiso.IN_FACE_DENTE_VESTIBULAR, ImagesUrl.FACE_VESTIBULAR, "Vestibular"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_MESIAL, StatusSiso.IN_FACE_DENTE_MESIAL, ImagesUrl.FACE_DISTAL, "Mesial"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_OCLUSAL, StatusSiso.IN_FACE_DENTE_OCLUSAL, ImagesUrl.FACE_OCLUSAL, "Oclusal"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_DISTAL, StatusSiso.IN_FACE_DENTE_DISTAL, ImagesUrl.FACE_MESIAL, "Distal"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_PALATINA, StatusSiso.IN_FACE_DENTE_PALATINA, ImagesUrl.FACE_PALATINA, "Palatina"));

            denteModel.setListFacesDente(listStrings);

            listDentesSuperiores.add(denteModel);
        }

        for (int i = 48; i >= 41; i--) {
            DenteModel denteModel = new DenteModel();
            denteModel.setIdDenteModel(i);
            denteModel.setNrDente(i);

            List<FaceModel> listStrings = new ArrayList<>();
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_VESTIBULAR, StatusSiso.IN_FACE_DENTE_VESTIBULAR, ImagesUrl.FACE_VESTIBULAR, "Vestibular"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_DISTAL, StatusSiso.IN_FACE_DENTE_DISTAL, ImagesUrl.FACE_DISTAL, "Distal"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_OCLUSAL, StatusSiso.IN_FACE_DENTE_OCLUSAL, ImagesUrl.FACE_OCLUSAL, "Oclusal"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_MESIAL, StatusSiso.IN_FACE_DENTE_MESIAL, ImagesUrl.FACE_MESIAL, "Mesial"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_PALATINA, StatusSiso.IN_FACE_DENTE_PALATINA, ImagesUrl.FACE_PALATINA, "Palatina"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_CERVICAL, StatusSiso.IN_FACE_DENTE_CERVICAL, ImagesUrl.FACE_CERVICAL, "Cervical"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_RAIZ, StatusSiso.IN_FACE_DENTE_RAIZ, ImagesUrl.FACE_RAIZ_INFERIOR, "Raiz"));
            denteModel.setListFacesDente(listStrings);

            listDentesInferiores.add(denteModel);
        }

        for (int i = 31; i <= 38; i++) {
            DenteModel denteModel = new DenteModel();
            denteModel.setIdDenteModel(i);
            denteModel.setNrDente(i);

            List<FaceModel> listStrings = new ArrayList<>();
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_VESTIBULAR, StatusSiso.IN_FACE_DENTE_VESTIBULAR, ImagesUrl.FACE_VESTIBULAR, "Vestibular"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_MESIAL, StatusSiso.IN_FACE_DENTE_MESIAL, ImagesUrl.FACE_DISTAL, "Mesial"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_OCLUSAL, StatusSiso.IN_FACE_DENTE_OCLUSAL, ImagesUrl.FACE_OCLUSAL, "Oclusal"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_DISTAL, StatusSiso.IN_FACE_DENTE_DISTAL, ImagesUrl.FACE_MESIAL, "Distal"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_PALATINA, StatusSiso.IN_FACE_DENTE_PALATINA, ImagesUrl.FACE_PALATINA, "Palatina"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_CERVICAL, StatusSiso.IN_FACE_DENTE_CERVICAL, ImagesUrl.FACE_CERVICAL, "Cervical"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_RAIZ, StatusSiso.IN_FACE_DENTE_RAIZ, ImagesUrl.FACE_RAIZ_INFERIOR, "Raiz"));
            denteModel.setListFacesDente(listStrings);

            listDentesInferiores.add(denteModel);
        }
    }

    /**
     * metodo utilizado para iniciar o odontograma adulto orcamento.
     */
    private void inicializarOdontogramaAdultoOrcamento() {

        System.out.println("iniciando odontograma adulto.");

        for (int i = 18; i >= 11; i--) {
            DenteModel denteModel = new DenteModel();
            denteModel.setIdDenteModel(i);
            denteModel.setNrDente(i);

            List<FaceModel> listStrings = new ArrayList<>();
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_RAIZ, StatusSiso.IN_FACE_DENTE_RAIZ, ImagesUrl.FACE_RAIZ_SUPERIOR, "Raiz"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_CERVICAL, StatusSiso.IN_FACE_DENTE_CERVICAL, ImagesUrl.FACE_CERVICAL, "Cervical"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_VESTIBULAR, StatusSiso.IN_FACE_DENTE_VESTIBULAR, ImagesUrl.FACE_VESTIBULAR, "Vestibular"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_DISTAL, StatusSiso.IN_FACE_DENTE_DISTAL, ImagesUrl.FACE_DISTAL, "Distal"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_OCLUSAL, StatusSiso.IN_FACE_DENTE_OCLUSAL, ImagesUrl.FACE_OCLUSAL, "Oclusal"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_MESIAL, StatusSiso.IN_FACE_DENTE_MESIAL, ImagesUrl.FACE_MESIAL, "Mesial"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_PALATINA, StatusSiso.IN_FACE_DENTE_PALATINA, ImagesUrl.FACE_PALATINA, "Palatina"));

            denteModel.setListFacesDente(listStrings);

            listDentesSuperioresOrcamento.add(denteModel);
        }
        for (int i = 21; i <= 28; i++) {
            DenteModel denteModel = new DenteModel();
            denteModel.setIdDenteModel(i);
            denteModel.setNrDente(i);

            List<FaceModel> listStrings = new ArrayList<>();
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_RAIZ, StatusSiso.IN_FACE_DENTE_RAIZ, ImagesUrl.FACE_RAIZ_SUPERIOR, "Raiz"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_CERVICAL, StatusSiso.IN_FACE_DENTE_CERVICAL, ImagesUrl.FACE_CERVICAL, "Cervical"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_VESTIBULAR, StatusSiso.IN_FACE_DENTE_VESTIBULAR, ImagesUrl.FACE_VESTIBULAR, "Vestibular"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_MESIAL, StatusSiso.IN_FACE_DENTE_MESIAL, ImagesUrl.FACE_DISTAL, "Mesial"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_OCLUSAL, StatusSiso.IN_FACE_DENTE_OCLUSAL, ImagesUrl.FACE_OCLUSAL, "Oclusal"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_DISTAL, StatusSiso.IN_FACE_DENTE_DISTAL, ImagesUrl.FACE_MESIAL, "Distal"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_PALATINA, StatusSiso.IN_FACE_DENTE_PALATINA, ImagesUrl.FACE_PALATINA, "Palatina"));

            denteModel.setListFacesDente(listStrings);

            listDentesSuperioresOrcamento.add(denteModel);
        }

        for (int i = 48; i >= 41; i--) {
            DenteModel denteModel = new DenteModel();
            denteModel.setIdDenteModel(i);
            denteModel.setNrDente(i);

            List<FaceModel> listStrings = new ArrayList<>();
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_VESTIBULAR, StatusSiso.IN_FACE_DENTE_VESTIBULAR, ImagesUrl.FACE_VESTIBULAR, "Vestibular"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_DISTAL, StatusSiso.IN_FACE_DENTE_DISTAL, ImagesUrl.FACE_DISTAL, "Distal"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_OCLUSAL, StatusSiso.IN_FACE_DENTE_OCLUSAL, ImagesUrl.FACE_OCLUSAL, "Oclusal"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_MESIAL, StatusSiso.IN_FACE_DENTE_MESIAL, ImagesUrl.FACE_MESIAL, "Mesial"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_PALATINA, StatusSiso.IN_FACE_DENTE_PALATINA, ImagesUrl.FACE_PALATINA, "Palatina"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_CERVICAL, StatusSiso.IN_FACE_DENTE_CERVICAL, ImagesUrl.FACE_CERVICAL, "Cervical"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_RAIZ, StatusSiso.IN_FACE_DENTE_RAIZ, ImagesUrl.FACE_RAIZ_INFERIOR, "Raiz"));
            denteModel.setListFacesDente(listStrings);

            listDentesInferioresOrcamento.add(denteModel);
        }

        for (int i = 31; i <= 38; i++) {
            DenteModel denteModel = new DenteModel();
            denteModel.setIdDenteModel(i);
            denteModel.setNrDente(i);

            List<FaceModel> listStrings = new ArrayList<>();
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_VESTIBULAR, StatusSiso.IN_FACE_DENTE_VESTIBULAR, ImagesUrl.FACE_VESTIBULAR, "Vestibular"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_MESIAL, StatusSiso.IN_FACE_DENTE_MESIAL, ImagesUrl.FACE_DISTAL, "Mesial"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_OCLUSAL, StatusSiso.IN_FACE_DENTE_OCLUSAL, ImagesUrl.FACE_OCLUSAL, "Oclusal"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_DISTAL, StatusSiso.IN_FACE_DENTE_DISTAL, ImagesUrl.FACE_MESIAL, "Distal"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_PALATINA, StatusSiso.IN_FACE_DENTE_PALATINA, ImagesUrl.FACE_PALATINA, "Palatina"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_CERVICAL, StatusSiso.IN_FACE_DENTE_CERVICAL, ImagesUrl.FACE_CERVICAL, "Cervical"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_RAIZ, StatusSiso.IN_FACE_DENTE_RAIZ, ImagesUrl.FACE_RAIZ_INFERIOR, "Raiz"));
            denteModel.setListFacesDente(listStrings);

            listDentesInferioresOrcamento.add(denteModel);
        }
    }

    /**
     * metodo utilizado para iniciar o odontograma adulto.
     */
    private void inicializarOdontogramaInfantil() {

        System.out.println("iniciando odontograma infantil.");

        for (int i = 55; i >= 51; i--) {
            DenteModel denteModel = new DenteModel();
            denteModel.setIdDenteModel(i);
            denteModel.setNrDente(i);

            List<FaceModel> listStrings = new ArrayList<>();
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_RAIZ, StatusSiso.IN_FACE_DENTE_RAIZ, ImagesUrl.FACE_RAIZ_SUPERIOR, "Raiz"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_CERVICAL, StatusSiso.IN_FACE_DENTE_CERVICAL, ImagesUrl.FACE_CERVICAL, "Cervical"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_VESTIBULAR, StatusSiso.IN_FACE_DENTE_VESTIBULAR, ImagesUrl.FACE_VESTIBULAR, "Vestibular"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_DISTAL, StatusSiso.IN_FACE_DENTE_DISTAL, ImagesUrl.FACE_DISTAL, "Distal"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_OCLUSAL, StatusSiso.IN_FACE_DENTE_OCLUSAL, ImagesUrl.FACE_OCLUSAL, "Oclusal"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_MESIAL, StatusSiso.IN_FACE_DENTE_MESIAL, ImagesUrl.FACE_MESIAL, "Mesial"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_PALATINA, StatusSiso.IN_FACE_DENTE_PALATINA, ImagesUrl.FACE_PALATINA, "Palatina"));

            denteModel.setListFacesDente(listStrings);

            listDentesSuperioresInfantil.add(denteModel);
        }
        for (int i = 61; i <= 65; i++) {
            DenteModel denteModel = new DenteModel();
            denteModel.setIdDenteModel(i);
            denteModel.setNrDente(i);

            List<FaceModel> listStrings = new ArrayList<>();
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_RAIZ, StatusSiso.IN_FACE_DENTE_RAIZ, ImagesUrl.FACE_RAIZ_SUPERIOR, "Raiz"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_CERVICAL, StatusSiso.IN_FACE_DENTE_CERVICAL, ImagesUrl.FACE_CERVICAL, "Cervical"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_VESTIBULAR, StatusSiso.IN_FACE_DENTE_VESTIBULAR, ImagesUrl.FACE_VESTIBULAR, "Vestibular"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_MESIAL, StatusSiso.IN_FACE_DENTE_MESIAL, ImagesUrl.FACE_DISTAL, "Mesial"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_OCLUSAL, StatusSiso.IN_FACE_DENTE_OCLUSAL, ImagesUrl.FACE_OCLUSAL, "Oclusal"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_DISTAL, StatusSiso.IN_FACE_DENTE_DISTAL, ImagesUrl.FACE_MESIAL, "Distal"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_PALATINA, StatusSiso.IN_FACE_DENTE_PALATINA, ImagesUrl.FACE_PALATINA, "Palatina"));

            denteModel.setListFacesDente(listStrings);

            listDentesSuperioresInfantil.add(denteModel);
        }

        for (int i = 85; i >= 81; i--) {
            DenteModel denteModel = new DenteModel();
            denteModel.setIdDenteModel(i);
            denteModel.setNrDente(i);

            List<FaceModel> listStrings = new ArrayList<>();
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_VESTIBULAR, StatusSiso.IN_FACE_DENTE_VESTIBULAR, ImagesUrl.FACE_VESTIBULAR, "Vestibular"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_DISTAL, StatusSiso.IN_FACE_DENTE_DISTAL, ImagesUrl.FACE_DISTAL, "Distal"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_OCLUSAL, StatusSiso.IN_FACE_DENTE_OCLUSAL, ImagesUrl.FACE_OCLUSAL, "Oclusal"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_MESIAL, StatusSiso.IN_FACE_DENTE_MESIAL, ImagesUrl.FACE_MESIAL, "Mesial"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_PALATINA, StatusSiso.IN_FACE_DENTE_PALATINA, ImagesUrl.FACE_PALATINA, "Palatina"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_CERVICAL, StatusSiso.IN_FACE_DENTE_CERVICAL, ImagesUrl.FACE_CERVICAL, "Cervical"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_RAIZ, StatusSiso.IN_FACE_DENTE_RAIZ, ImagesUrl.FACE_RAIZ_INFERIOR, "Raiz"));
            denteModel.setListFacesDente(listStrings);

            listDentesInferioresInfantil.add(denteModel);
        }

        for (int i = 71; i <= 75; i++) {
            DenteModel denteModel = new DenteModel();
            denteModel.setIdDenteModel(i);
            denteModel.setNrDente(i);

            List<FaceModel> listStrings = new ArrayList<>();
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_VESTIBULAR, StatusSiso.IN_FACE_DENTE_VESTIBULAR, ImagesUrl.FACE_VESTIBULAR, "Vestibular"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_MESIAL, StatusSiso.IN_FACE_DENTE_MESIAL, ImagesUrl.FACE_DISTAL, "Mesial"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_OCLUSAL, StatusSiso.IN_FACE_DENTE_OCLUSAL, ImagesUrl.FACE_OCLUSAL, "Oclusal"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_DISTAL, StatusSiso.IN_FACE_DENTE_DISTAL, ImagesUrl.FACE_MESIAL, "Distal"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_PALATINA, StatusSiso.IN_FACE_DENTE_PALATINA, ImagesUrl.FACE_PALATINA, "Palatina"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_CERVICAL, StatusSiso.IN_FACE_DENTE_CERVICAL, ImagesUrl.FACE_CERVICAL, "Cervical"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_RAIZ, StatusSiso.IN_FACE_DENTE_RAIZ, ImagesUrl.FACE_RAIZ_INFERIOR, "Raiz"));
            denteModel.setListFacesDente(listStrings);

            listDentesInferioresInfantil.add(denteModel);
        }
    }

    /**
     * metodo utilizado para iniciar o odontograma adulto orcamento.
     */
    private void inicializarOdontogramaInfantilOrcamento() {

        System.out.println("iniciando odontograma infantil.");

        for (int i = 55; i >= 51; i--) {
            DenteModel denteModel = new DenteModel();
            denteModel.setIdDenteModel(i);
            denteModel.setNrDente(i);

            List<FaceModel> listStrings = new ArrayList<>();
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_RAIZ, StatusSiso.IN_FACE_DENTE_RAIZ, ImagesUrl.FACE_RAIZ_SUPERIOR, "Raiz"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_CERVICAL, StatusSiso.IN_FACE_DENTE_CERVICAL, ImagesUrl.FACE_CERVICAL, "Cervical"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_VESTIBULAR, StatusSiso.IN_FACE_DENTE_VESTIBULAR, ImagesUrl.FACE_VESTIBULAR, "Vestibular"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_DISTAL, StatusSiso.IN_FACE_DENTE_DISTAL, ImagesUrl.FACE_DISTAL, "Distal"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_OCLUSAL, StatusSiso.IN_FACE_DENTE_OCLUSAL, ImagesUrl.FACE_OCLUSAL, "Oclusal"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_MESIAL, StatusSiso.IN_FACE_DENTE_MESIAL, ImagesUrl.FACE_MESIAL, "Mesial"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_PALATINA, StatusSiso.IN_FACE_DENTE_PALATINA, ImagesUrl.FACE_PALATINA, "Palatina"));

            denteModel.setListFacesDente(listStrings);

            listDentesSuperioresInfantilOrcamento.add(denteModel);
        }
        for (int i = 61; i <= 65; i++) {
            DenteModel denteModel = new DenteModel();
            denteModel.setIdDenteModel(i);
            denteModel.setNrDente(i);

            List<FaceModel> listStrings = new ArrayList<>();
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_RAIZ, StatusSiso.IN_FACE_DENTE_RAIZ, ImagesUrl.FACE_RAIZ_SUPERIOR, "Raiz"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_CERVICAL, StatusSiso.IN_FACE_DENTE_CERVICAL, ImagesUrl.FACE_CERVICAL, "Cervical"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_VESTIBULAR, StatusSiso.IN_FACE_DENTE_VESTIBULAR, ImagesUrl.FACE_VESTIBULAR, "Vestibular"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_MESIAL, StatusSiso.IN_FACE_DENTE_MESIAL, ImagesUrl.FACE_DISTAL, "Mesial"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_OCLUSAL, StatusSiso.IN_FACE_DENTE_OCLUSAL, ImagesUrl.FACE_OCLUSAL, "Oclusal"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_DISTAL, StatusSiso.IN_FACE_DENTE_DISTAL, ImagesUrl.FACE_MESIAL, "Distal"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_PALATINA, StatusSiso.IN_FACE_DENTE_PALATINA, ImagesUrl.FACE_PALATINA, "Palatina"));

            denteModel.setListFacesDente(listStrings);

            listDentesSuperioresInfantilOrcamento.add(denteModel);
        }

        for (int i = 85; i >= 81; i--) {
            DenteModel denteModel = new DenteModel();
            denteModel.setIdDenteModel(i);
            denteModel.setNrDente(i);

            List<FaceModel> listStrings = new ArrayList<>();
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_VESTIBULAR, StatusSiso.IN_FACE_DENTE_VESTIBULAR, ImagesUrl.FACE_VESTIBULAR, "Vestibular"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_DISTAL, StatusSiso.IN_FACE_DENTE_DISTAL, ImagesUrl.FACE_DISTAL, "Distal"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_OCLUSAL, StatusSiso.IN_FACE_DENTE_OCLUSAL, ImagesUrl.FACE_OCLUSAL, "Oclusal"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_MESIAL, StatusSiso.IN_FACE_DENTE_MESIAL, ImagesUrl.FACE_MESIAL, "Mesial"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_PALATINA, StatusSiso.IN_FACE_DENTE_PALATINA, ImagesUrl.FACE_PALATINA, "Palatina"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_CERVICAL, StatusSiso.IN_FACE_DENTE_CERVICAL, ImagesUrl.FACE_CERVICAL, "Cervical"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_RAIZ, StatusSiso.IN_FACE_DENTE_RAIZ, ImagesUrl.FACE_RAIZ_INFERIOR, "Raiz"));
            denteModel.setListFacesDente(listStrings);

            listDentesInferioresInfantilOrcamento.add(denteModel);
        }

        for (int i = 71; i <= 75; i++) {
            DenteModel denteModel = new DenteModel();
            denteModel.setIdDenteModel(i);
            denteModel.setNrDente(i);

            List<FaceModel> listStrings = new ArrayList<>();
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_VESTIBULAR, StatusSiso.IN_FACE_DENTE_VESTIBULAR, ImagesUrl.FACE_VESTIBULAR, "Vestibular"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_MESIAL, StatusSiso.IN_FACE_DENTE_MESIAL, ImagesUrl.FACE_DISTAL, "Mesial"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_OCLUSAL, StatusSiso.IN_FACE_DENTE_OCLUSAL, ImagesUrl.FACE_OCLUSAL, "Oclusal"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_DISTAL, StatusSiso.IN_FACE_DENTE_DISTAL, ImagesUrl.FACE_MESIAL, "Distal"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_PALATINA, StatusSiso.IN_FACE_DENTE_PALATINA, ImagesUrl.FACE_PALATINA, "Palatina"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_CERVICAL, StatusSiso.IN_FACE_DENTE_CERVICAL, ImagesUrl.FACE_CERVICAL, "Cervical"));
            listStrings.add(new FaceModel(StatusSiso.IN_FACE_DENTE_RAIZ, StatusSiso.IN_FACE_DENTE_RAIZ, ImagesUrl.FACE_RAIZ_INFERIOR, "Raiz"));
            denteModel.setListFacesDente(listStrings);

            listDentesInferioresInfantilOrcamento.add(denteModel);
        }
    }

    /**
     * metodo utilizado para retornar a url da imagem por face.
     *
     * @param inFace
     * @param raizUpDown
     * @return url
     */
    private String verificaImageProcedimentoPorFaceOrcamento(final Integer inFace, boolean raizUpDown) {
        String result = "";

        switch (inFace) {
            case StatusSiso.IN_FACE_DENTE_CERVICAL:
                result = ImagesUrl.FACE_CERVICAL_PROCEDIMENTO;
                break;
            case StatusSiso.IN_FACE_DENTE_DISTAL:
                result = ImagesUrl.FACE_DISTAL_PROCEDIMENTO;
                break;
            case StatusSiso.IN_FACE_DENTE_MESIAL:
                result = ImagesUrl.FACE_MESIAL_PROCEDIMENTO;
                break;
            case StatusSiso.IN_FACE_DENTE_OCLUSAL:
                result = ImagesUrl.FACE_OCLUSAL_PROCEDIMENTO;
                break;
            case StatusSiso.IN_FACE_DENTE_PALATINA:
                result = ImagesUrl.FACE_PALATINA_PROCEDIMENTO;
                break;
            case StatusSiso.IN_FACE_DENTE_RAIZ:

                if (raizUpDown) {
                    result = ImagesUrl.FACE_RAIZ_SUPERIOR_PROCEDIMENTO;
                } else {
                    result = ImagesUrl.FACE_RAIZ_INFERIOR_PROCEDIMENTO;
                }

                break;
            case StatusSiso.IN_FACE_DENTE_VESTIBULAR:
                result = ImagesUrl.FACE_VESTIBULAR_PROCEDIMENTO;
                break;
        }
        return result;
    }

    /**
     * metodo utilizado para retornar a url da imagem inicial por face.
     *
     * @param inFace
     * @param raizUpDown
     * @return url
     */
    private String verificaImageInicialPorFaceOrcamento(final Integer inFace, boolean raizUpDown) {
        String result = "";

        switch (inFace) {
            case StatusSiso.IN_FACE_DENTE_CERVICAL:
                result = ImagesUrl.FACE_CERVICAL;
                break;
            case StatusSiso.IN_FACE_DENTE_DISTAL:
                result = ImagesUrl.FACE_DISTAL;
                break;
            case StatusSiso.IN_FACE_DENTE_MESIAL:
                result = ImagesUrl.FACE_MESIAL;
                break;
            case StatusSiso.IN_FACE_DENTE_OCLUSAL:
                result = ImagesUrl.FACE_OCLUSAL;
                break;
            case StatusSiso.IN_FACE_DENTE_PALATINA:
                result = ImagesUrl.FACE_PALATINA;
                break;
            case StatusSiso.IN_FACE_DENTE_RAIZ:

                if (raizUpDown) {
                    result = ImagesUrl.FACE_RAIZ_SUPERIOR;
                } else {
                    result = ImagesUrl.FACE_RAIZ_INFERIOR;
                }

                break;
            case StatusSiso.IN_FACE_DENTE_VESTIBULAR:
                result = ImagesUrl.FACE_VESTIBULAR;
                break;
        }
        return result;
    }

    /**
     * metodo utilizado para retornar a url da imagem inicial por face.
     *
     * @param inFace
     * @param raizUpDown
     * @return url
     */
    private String retornaNmFaceSelected(final Integer inFace) {
        String result = "";

        switch (inFace) {
            case StatusSiso.IN_FACE_DENTE_CERVICAL:
                result = "Cervical";
                break;
            case StatusSiso.IN_FACE_DENTE_DISTAL:
                result = "Distal";
                break;
            case StatusSiso.IN_FACE_DENTE_MESIAL:
                result = "Mesial";
                break;
            case StatusSiso.IN_FACE_DENTE_OCLUSAL:
                result = "Oclusal";
                break;
            case StatusSiso.IN_FACE_DENTE_PALATINA:
                result = "Palatina";
                break;
            case StatusSiso.IN_FACE_DENTE_RAIZ:
                result = "Raiz";
                break;
            case StatusSiso.IN_FACE_DENTE_VESTIBULAR:
                result = "Vestibular";
                break;
        }
        return result;
    }

    /**
     * metodo utilizado para verificar se pode adicionar procedimento a face
     * selecionada.
     *
     * @param inFace
     * @param tbservico
     * @return true or false
     */
    private boolean validarDadosAdicionarProcedimentoOdontograma(Integer inFace, final Tbservico tbservico) {

        boolean dadosValidos = true;

        Tbservicoface tbservicoface = servicoFaceLogic.findTbservicofaceByTbservico(tbservico);

        if (tbservicoface != null) {
            switch (inFace) {
                case 1: // Cervical
                    if (!tbservicoface.getBocervical()) {
                        dadosValidos = false;
                        AbstractFacesContextUtils.addMessageWarn("Procedimento não pode ser adicionado a esta Face!");
                    }
                    break;
                case 3://distal
                    if (!tbservicoface.getBodistal()) {
                        dadosValidos = false;
                        AbstractFacesContextUtils.addMessageWarn("Procedimento não pode ser adicionado a esta Face!");
                    }
                    break;
                case 7://mesial
                    if (!tbservicoface.getBomesial()) {
                        dadosValidos = false;
                        AbstractFacesContextUtils.addMessageWarn("Procedimento não pode ser adicionado a esta Face!");
                    }
                    break;
                case 8://oclusal
                    if (!tbservicoface.getBooclusal()) {
                        dadosValidos = false;
                        AbstractFacesContextUtils.addMessageWarn("Procedimento não pode ser adicionado a esta Face!");
                    }
                    break;
                case 9://palatina
                    if (!tbservicoface.getBopalatina()) {
                        dadosValidos = false;
                        AbstractFacesContextUtils.addMessageWarn("Procedimento não pode ser adicionado a esta Face!");
                    }
                    break;
                case 10://raiz
                    if (!tbservicoface.getBoraiz()) {
                        dadosValidos = false;
                        AbstractFacesContextUtils.addMessageWarn("Procedimento não pode ser adicionado a esta Face!");
                    }
                    break;
                case 14://vestibular
                    if (!tbservicoface.getBovestibular()) {
                        dadosValidos = false;
                        AbstractFacesContextUtils.addMessageWarn("Procedimento não pode ser adicionado a esta Face!");
                    }
                    break;
                default:
                    break;
            }
        } else {
            dadosValidos = false;
            AbstractFacesContextUtils.addMessageWarn("Procedimento não pode ser adicionado a esta Face pois não possui configuração!");
        }

        return dadosValidos;

    }

    /**
     * metodo utilizado para fechar um determinado orcamento.
     */
    private void fecharOrcamento() {
        boolean boNaoRealizado = false;

        for (Tbprocedimento p : listTbprocedimentosGeral) {
            if (!p.getIdtipostatusprocedimento().getIntipostatusprocedimento().equals(StatusSiso.INT_INTIPO_STATUS_PROCEDIMENTO_REALIZADOS)) {
                boNaoRealizado = true;
                break;
            }
        }

        if (!boNaoRealizado) {
            tborcamento.getIdtipostatusorcamento().setIdtipostatusorcamento(StatusSiso.ID_STATUS_ORCAMENTO_FECHADO);
            try {
                atendimentoLogic.editTborcamento(tborcamento);
            } catch (Exception e) {
                System.out.println("ERRO FECHAR ORCAMENTO: " + e.getMessage());
            }
        }
    }

    /**
     * @return the denteSelected
     */
    public Integer getDenteSelected() {
        return denteSelected;
    }

    /**
     * @param denteSelected the denteSelected to set
     */
    public void setDenteSelected(Integer denteSelected) {
        this.denteSelected = denteSelected;
    }

    /**
     * @return the listDentesInferiores
     */
    public List<DenteModel> getListDentesInferiores() {
        return listDentesInferiores;
    }

    /**
     * @param listDentesInferiores the listDentesInferiores to set
     */
    public void setListDentesInferiores(List<DenteModel> listDentesInferiores) {
        this.listDentesInferiores = listDentesInferiores;
    }

    /**
     * @return the listDentesSuperiores
     */
    public List<DenteModel> getListDentesSuperiores() {
        return listDentesSuperiores;
    }

    /**
     * @param listDentesSuperiores the listDentesSuperiores to set
     */
    public void setListDentesSuperiores(List<DenteModel> listDentesSuperiores) {
        this.listDentesSuperiores = listDentesSuperiores;
    }

    /**
     * @return the faceSelected
     */
    public Integer getFaceSelected() {
        return faceSelected;
    }

    /**
     * @param faceSelected the faceSelected to set
     */
    public void setFaceSelected(Integer faceSelected) {
        this.faceSelected = faceSelected;
    }

    /**
     * @return the posicao
     */
    public Integer getPosicao() {
        return posicao;
    }

    /**
     * @param posicao the posicao to set
     */
    public void setPosicao(Integer posicao) {
        this.posicao = posicao;
    }

    /**
     * @return the listTbagendamentos
     */
    public List<Tbagendamento> getListTbagendamentos() {
        return listTbagendamentos;
    }

    /**
     * @param listTbagendamentos the listTbagendamentos to set
     */
    public void setListTbagendamentos(List<Tbagendamento> listTbagendamentos) {
        this.listTbagendamentos = listTbagendamentos;
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
     * @return the listTbfuncionarios
     */
    public List<Tbfuncionario> getListTbfuncionarios() {
        return listTbfuncionarios;
    }

    /**
     * @param listTbfuncionarios the listTbfuncionarios to set
     */
    public void setListTbfuncionarios(List<Tbfuncionario> listTbfuncionarios) {
        this.listTbfuncionarios = listTbfuncionarios;
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
     * @return the listTbservicos
     */
    public List<Tbservico> getListTbservicos() {
        return listTbservicos;
    }

    /**
     * @param listTbservicos the listTbservicos to set
     */
    public void setListTbservicos(List<Tbservico> listTbservicos) {
        this.listTbservicos = listTbservicos;
    }

    /**
     * @return the listTbdentes
     */
    public List<Tbdente> getListTbdentes() {
        return listTbdentes;
    }

    /**
     * @param listTbdentes the listTbdentes to set
     */
    public void setListTbdentes(List<Tbdente> listTbdentes) {
        this.listTbdentes = listTbdentes;
    }

    /**
     * @return the listFacesDentes
     */
    public List<SelectItem> getListFacesDentes() {
        return listFacesDentes;
    }

    /**
     * @param listFacesDentes the listFacesDentes to set
     */
    public void setListFacesDentes(List<SelectItem> listFacesDentes) {
        this.listFacesDentes = listFacesDentes;
    }

    /**
     * @return the tbsubespecialidade
     */
    public Tbsubespecialidade getTbsubespecialidade() {
        return tbsubespecialidade;
    }

    /**
     * @param tbsubespecialidade the tbsubespecialidade to set
     */
    public void setTbsubespecialidade(Tbsubespecialidade tbsubespecialidade) {
        this.tbsubespecialidade = tbsubespecialidade;
    }

    /**
     * @return the tbservico
     */
    public Tbservico getTbservico() {
        return tbservico;
    }

    /**
     * @param tbservico the tbservico to set
     */
    public void setTbservico(Tbservico tbservico) {
        this.tbservico = tbservico;
    }

    /**
     * @return the listTbprocedimentosGeral
     */
    public List<Tbprocedimento> getListTbprocedimentosGeral() {
        return listTbprocedimentosGeral;
    }

    /**
     * @param listTbprocedimentosGeral the listTbprocedimentosGeral to set
     */
    public void setListTbprocedimentosGeral(List<Tbprocedimento> listTbprocedimentosGeral) {
        this.listTbprocedimentosGeral = listTbprocedimentosGeral;
    }

    /**
     * @return the listTbprocedimentosPorDente
     */
    public List<Tbprocedimento> getListTbprocedimentosPorDente() {
        return listTbprocedimentosPorDente;
    }

    /**
     * @param listTbprocedimentosPorDente the listTbprocedimentosPorDente to set
     */
    public void setListTbprocedimentosPorDente(List<Tbprocedimento> listTbprocedimentosPorDente) {
        this.listTbprocedimentosPorDente = listTbprocedimentosPorDente;
    }

    /**
     * @return the dataSearch
     */
    public Date getDataSearch() {
        return dataSearch;
    }

    /**
     * @param dataSearch the dataSearch to set
     */
    public void setDataSearch(Date dataSearch) {
        this.dataSearch = dataSearch;
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
     * @return the tbagendamento
     */
    public Tbagendamento getTbagendamento() {
        return tbagendamento;
    }

    /**
     * @param tbagendamento the tbagendamento to set
     */
    public void setTbagendamento(Tbagendamento tbagendamento) {
        this.tbagendamento = tbagendamento;
    }

    /**
     * @return the tbatendimento
     */
    public Tbatendimento getTbatendimento() {
        return tbatendimento;
    }

    /**
     * @param tbatendimento the tbatendimento to set
     */
    public void setTbatendimento(Tbatendimento tbatendimento) {
        this.tbatendimento = tbatendimento;
    }

    /**
     * @return the tborcamento
     */
    public Tborcamento getTborcamento() {
        return tborcamento;
    }

    /**
     * @param tborcamento the tborcamento to set
     */
    public void setTborcamento(Tborcamento tborcamento) {
        this.tborcamento = tborcamento;
    }

    /**
     * @return the qtProcedimento
     */
    public Integer getQtProcedimento() {
        return qtProcedimento;
    }

    /**
     * @param qtProcedimento the qtProcedimento to set
     */
    public void setQtProcedimento(Integer qtProcedimento) {
        this.qtProcedimento = qtProcedimento;
    }

    /**
     * @return the inFace
     */
    public Integer getInFace() {
        return inFace;
    }

    /**
     * @param inFace the inFace to set
     */
    public void setInFace(Integer inFace) {
        this.inFace = inFace;
    }

    /**
     * @return the tbdente
     */
    public Tbdente getTbdente() {
        return tbdente;
    }

    /**
     * @param tbdente the tbdente to set
     */
    public void setTbdente(Tbdente tbdente) {
        this.tbdente = tbdente;
    }

    /**
     * @return the tbservicoManual
     */
    public Tbservico getTbservicoManual() {
        return tbservicoManual;
    }

    /**
     * @param tbservicoManual the tbservicoManual to set
     */
    public void setTbservicoManual(Tbservico tbservicoManual) {
        this.tbservicoManual = tbservicoManual;
    }

    /**
     * @return the tbprocedimentoToRemove
     */
    public Tbprocedimento getTbprocedimentoToRemove() {
        return tbprocedimentoToRemove;
    }

    /**
     * @param tbprocedimentoToRemove the tbprocedimentoToRemove to set
     */
    public void setTbprocedimentoToRemove(Tbprocedimento tbprocedimentoToRemove) {
        this.tbprocedimentoToRemove = tbprocedimentoToRemove;
    }

    /**
     * @return the nmFaceSelected
     */
    public String getNmFaceSelected() {
        return nmFaceSelected;
    }

    /**
     * @param nmFaceSelected the nmFaceSelected to set
     */
    public void setNmFaceSelected(String nmFaceSelected) {
        this.nmFaceSelected = nmFaceSelected;
    }

    /**
     * @return the listDentesSuperioresInfantil
     */
    public List<DenteModel> getListDentesSuperioresInfantil() {
        return listDentesSuperioresInfantil;
    }

    /**
     * @param listDentesSuperioresInfantil the listDentesSuperioresInfantil to
     * set
     */
    public void setListDentesSuperioresInfantil(List<DenteModel> listDentesSuperioresInfantil) {
        this.listDentesSuperioresInfantil = listDentesSuperioresInfantil;
    }

    /**
     * @return the listDentesInferioresInfantil
     */
    public List<DenteModel> getListDentesInferioresInfantil() {
        return listDentesInferioresInfantil;
    }

    /**
     * @param listDentesInferioresInfantil the listDentesInferioresInfantil to
     * set
     */
    public void setListDentesInferioresInfantil(List<DenteModel> listDentesInferioresInfantil) {
        this.listDentesInferioresInfantil = listDentesInferioresInfantil;
    }

    /**
     * @return the tbprocedimentoSelected
     */
    public Tbprocedimento getTbprocedimentoSelected() {
        return tbprocedimentoSelected;
    }

    /**
     * @param tbprocedimentoSelected the tbprocedimentoSelected to set
     */
    public void setTbprocedimentoSelected(Tbprocedimento tbprocedimentoSelected) {
        this.tbprocedimentoSelected = tbprocedimentoSelected;
    }

    /**
     * @return the listDentesSuperioresOrcamento
     */
    public List<DenteModel> getListDentesSuperioresOrcamento() {
        return listDentesSuperioresOrcamento;
    }

    /**
     * @param listDentesSuperioresOrcamento the listDentesSuperioresOrcamento to
     * set
     */
    public void setListDentesSuperioresOrcamento(List<DenteModel> listDentesSuperioresOrcamento) {
        this.listDentesSuperioresOrcamento = listDentesSuperioresOrcamento;
    }

    /**
     * @return the listDentesInferioresOrcamento
     */
    public List<DenteModel> getListDentesInferioresOrcamento() {
        return listDentesInferioresOrcamento;
    }

    /**
     * @param listDentesInferioresOrcamento the listDentesInferioresOrcamento to
     * set
     */
    public void setListDentesInferioresOrcamento(List<DenteModel> listDentesInferioresOrcamento) {
        this.listDentesInferioresOrcamento = listDentesInferioresOrcamento;
    }

    /**
     * @return the listDentesSuperioresInfantilOrcamento
     */
    public List<DenteModel> getListDentesSuperioresInfantilOrcamento() {
        return listDentesSuperioresInfantilOrcamento;
    }

    /**
     * @param listDentesSuperioresInfantilOrcamento the
     * listDentesSuperioresInfantilOrcamento to set
     */
    public void setListDentesSuperioresInfantilOrcamento(List<DenteModel> listDentesSuperioresInfantilOrcamento) {
        this.listDentesSuperioresInfantilOrcamento = listDentesSuperioresInfantilOrcamento;
    }

    /**
     * @return the listDentesInferioresInfantilOrcamento
     */
    public List<DenteModel> getListDentesInferioresInfantilOrcamento() {
        return listDentesInferioresInfantilOrcamento;
    }

    /**
     * @param listDentesInferioresInfantilOrcamento the
     * listDentesInferioresInfantilOrcamento to set
     */
    public void setListDentesInferioresInfantilOrcamento(List<DenteModel> listDentesInferioresInfantilOrcamento) {
        this.listDentesInferioresInfantilOrcamento = listDentesInferioresInfantilOrcamento;
    }

    /**
     * @return the listOrcamentoModels
     */
    public List<OrcamentoModel> getListOrcamentoModels() {
        return listOrcamentoModels;
    }

    /**
     * @param listOrcamentoModels the listOrcamentoModels to set
     */
    public void setListOrcamentoModels(List<OrcamentoModel> listOrcamentoModels) {
        this.listOrcamentoModels = listOrcamentoModels;
    }

    /**
     * @return the consultasOdontologicas
     */
    public List<Tbatendimento> getConsultasOdontologicas() {
        return consultasOdontologicas;
    }

    /**
     * @param consultasOdontologicas the consultasOdontologicas to set
     */
    public void setConsultasOdontologicas(List<Tbatendimento> consultasOdontologicas) {
        this.consultasOdontologicas = consultasOdontologicas;
    }

    /**
     * @return the listTbreceituarios
     */
    public List<Tbreceituario> getListTbreceituarios() {
        return listTbreceituarios;
    }

    /**
     * @param listTbreceituarios the listTbreceituarios to set
     */
    public void setListTbreceituarios(List<Tbreceituario> listTbreceituarios) {
        this.listTbreceituarios = listTbreceituarios;
    }
}
