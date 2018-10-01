/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.beans.agendamento;

import br.com.siso.model.entities.Tbagendamento;
import br.com.siso.model.entities.Tbcliente;
import br.com.siso.model.entities.Tbespecialidade;
import br.com.siso.model.entities.Tbfuncionario;
import br.com.siso.model.entities.Tbsubespecialidade;
import br.com.siso.model.entities.Tbtipostatusagendamento;
import br.com.siso.web.control.logic.agendamento.AgendamentoLogic;
import br.com.siso.web.faces.constants.PagesUrl;
import br.com.siso.web.faces.converter.TimeControl;
import br.com.siso.web.faces.utils.AbstractFacesContextUtils;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author ioliveira
 */
@ManagedBean(name = "agendamentoBean")
@ViewScoped
public class AgendamentoBean implements Serializable {

    @EJB
    private AgendamentoLogic agendamentoLogic;

    private List<Tbagendamento> listTbagendamentos;
    private List<Tbcliente> listTbclientes;
    private List<Tbfuncionario> listTbfuncionarios;
    private List<Tbtipostatusagendamento> listTbtipostatusagendamentos;
    private List<Tbespecialidade> listTbespecialidades;
    private List<Tbsubespecialidade> listTbsubespecialidades;

    private Date dataSearch;
    private Tbcliente tbcliente;
    private Tbfuncionario tbfuncionario;
    private Tbagendamento tbagendamentoSelected;
    private Tbtipostatusagendamento tbtipostatusagendamento;
    private Tbagendamento tbagendamento;
    private ScheduleModel scheduleAgendamento;
    private DefaultScheduleEvent event;
    private String horaAgendamento;

    @PostConstruct
    public void init() {
        listTbagendamentos = agendamentoLogic.findAllTbagendamentosOrderByNrAgendamento();
        listTbclientes = agendamentoLogic.findAllTbcliente();
        listTbtipostatusagendamentos = agendamentoLogic.findAllTbtipostatusagendamentos();
        listTbespecialidades = agendamentoLogic.findAllTbespecialidades();
        listTbsubespecialidades = agendamentoLogic.findAllTbsubespecialidades();
        scheduleAgendamento = new DefaultScheduleModel();

        dataSearch = new Date();
        tbagendamento = new Tbagendamento();
        tbagendamento.setIdespecialidade(new Tbespecialidade(6));

        Integer idAgendamento = AbstractFacesContextUtils.getParamInt("idAgendamento");
        if (idAgendamento > 0) {
            tbagendamento = agendamentoLogic.findTbagendamentoByIdAgendamento(idAgendamento);
        }

        loadScheduleList();
    }

    public void atualizaListaAgendamentos() {
        listTbagendamentos = agendamentoLogic.findAllTbagendamentoFromCurrentDay();
    }

    /**
     * metodo para executar a busca de agendamentos.
     */
    public void search() {
        listTbagendamentos
                = agendamentoLogic.findAllTbagendamentoByDataAndPacienteAndFuncionario(dataSearch, tbcliente, tbfuncionario);
    }

    /**
     * metodo utilizado para atualizar o tipo status do agendamento.
     */
    public void atualizarStatusAgendamento() {

        if (agendamentoLogic.editarStatusTbagendamento(tbagendamentoSelected, tbtipostatusagendamento)) {
            AbstractFacesContextUtils.addMessageInfo("Status atualizado com sucesso.");
        } else {
            AbstractFacesContextUtils.addMessageWarn("Falhar ao alterar status do agendamento.");
        }
    }

    /**
     * metodo para criar um novo agendamento.
     */
    public void createAgendamento() {

        if (tbagendamento.getTmdataagendamento().after(TimeControl.getDateIni())) {
            if (agendamentoLogic.createTbagendamento(tbagendamento)) {
                AbstractFacesContextUtils.redirectPage(PagesUrl.URL_AGENDAMENTO_LIST);
                AbstractFacesContextUtils.addMessageInfo("Agendamento cadastrado com sucesso.");
            } else {
                AbstractFacesContextUtils.addMessageWarn("Falhar ao realizado cadastro do agendamento.");
            }
        } else {
            AbstractFacesContextUtils.addMessageWarn("verifique se a data de agendamento esta correta.");
        }
    }

    /**
     * metodo utilizado para remover um determinado agendamento.
     */
    public void deleteTbagendamento() {

        if (agendamentoLogic.removeTbagendamento(tbagendamentoSelected)) {
            AbstractFacesContextUtils.addMessageInfo("Agendamento removido com sucesso.");
            listTbagendamentos = agendamentoLogic.findAllTbagendamentoFromCurrentDay();
        } else {
            AbstractFacesContextUtils.addMessageWarn("Falha ao remover agendamento.");
        }

    }

    /**
     * metodo utilizado para editar os dados do agendamento.
     */
    public void editarTbagendamento() {

        if (tbagendamento.getTmdataagendamento().after(TimeControl.getDateIni())) {
            if (agendamentoLogic.editTbagendamento(tbagendamento)) {
                AbstractFacesContextUtils.redirectPage(PagesUrl.URL_AGENDAMENTO_LIST);
                AbstractFacesContextUtils.addMessageInfo("Agendamento editado com sucesso.");
            } else {
                AbstractFacesContextUtils.addMessageWarn("Falhar ao realizado ediçãos do agendamento.");
            }
        } else {
            AbstractFacesContextUtils.addMessageWarn("verifique se a data de agendamento esta correta.");
        }

    }

    /**
     * metodo para limpar os campos do agendamento list.
     */
    public void clearFields() {
        dataSearch = new Date();
        tbcliente = null;
        tbfuncionario = null;
        listTbagendamentos = agendamentoLogic.findAllTbagendamentoFromCurrentDay();
    }

    public void addEvent(ActionEvent actionEvent) {

        if (event.getId() == null) {

            if (tbagendamento.getTmdataagendamento().after(TimeControl.getDateIni())) {
                if (agendamentoLogic.createTbagendamento(tbagendamento)) {
                    event.setTitle(tbagendamento.getIdcliente().getNmcliente());

                    Calendar dtDataInicial = new GregorianCalendar();
                    dtDataInicial.setTime(tbagendamento.getTmdataagendamento());

                    event.setStartDate(dtDataInicial.getTime());

                    Calendar dtDataFinal = new GregorianCalendar();
                    dtDataFinal.setTime(tbagendamento.getTmdataagendamento());
                    dtDataFinal.set(Calendar.MINUTE, dtDataFinal.get(Calendar.MINUTE) + 30);

                    event.setEndDate(dtDataFinal.getTime());
                    event.setData(tbagendamento.getIdagendamento());
                    scheduleAgendamento.addEvent(event);
                    AbstractFacesContextUtils.addMessageInfo("Agendamento cadastrado com sucesso.");
                } else {
                    AbstractFacesContextUtils.addMessageWarn("Falhar ao realizado cadastro do agendamento.");
                }
            } else {
                AbstractFacesContextUtils.addMessageWarn("verifique se a data de agendamento esta correta.");
            }
        } else {

            if (agendamentoLogic.editarStatusTbagendamento(tbagendamentoSelected, tbtipostatusagendamento)) {
                event.setTitle(tbagendamentoSelected.getIdcliente().getNmcliente());

                Calendar dtDataInicial = new GregorianCalendar();
                dtDataInicial.setTime(tbagendamentoSelected.getTmdataagendamento());

                event.setStartDate(dtDataInicial.getTime());

                Calendar dtDataFinal = new GregorianCalendar();
                dtDataFinal.setTime(tbagendamentoSelected.getTmdataagendamento());
                dtDataFinal.set(Calendar.MINUTE, dtDataFinal.get(Calendar.MINUTE) + 30);

                event.setEndDate(dtDataFinal.getTime());
                event.setData(tbagendamentoSelected.getIdagendamento());
                scheduleAgendamento.updateEvent(event);
                tbagendamentoSelected = null;
                tbtipostatusagendamento = null;
                AbstractFacesContextUtils.addMessageInfo("Status atualizado com sucesso.");
            } else {
                tbagendamentoSelected = null;
                tbtipostatusagendamento = null;
                AbstractFacesContextUtils.addMessageWarn("Falhar ao alterar status do agendamento.");
            }
        }

        event = new DefaultScheduleEvent();
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (DefaultScheduleEvent) selectEvent.getObject();
        final Integer idAgendamento = (Integer) event.getData();

        tbagendamentoSelected = agendamentoLogic.findTbagendamentoByIdAgendamento(idAgendamento);
        tbtipostatusagendamento = tbagendamentoSelected.getIdtipostatusagendamento();
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent();
        tbagendamento = new Tbagendamento();
        tbagendamento.setTmdataagendamento((Date) selectEvent.getObject());
    }

    public void onEventMove(ScheduleEntryMoveEvent scheduleEntryMoveEvent) {

        final Integer schedule = (Integer) scheduleEntryMoveEvent.getScheduleEvent().getData();

        Tbagendamento tbschedule = agendamentoLogic.findTbagendamentoByIdAgendamento(schedule);

        Calendar tmDataInicial = new GregorianCalendar();
        tmDataInicial.setTime(scheduleEntryMoveEvent.getScheduleEvent().getStartDate());

        tbschedule.setTmdataagendamento(tmDataInicial.getTime());
        Calendar dtDataFinal = new GregorianCalendar();
        dtDataFinal.setTime(scheduleEntryMoveEvent.getScheduleEvent().getStartDate());

        tbschedule.setDtdataagendamento(dtDataFinal.getTime());

        if (agendamentoLogic.editTbagendamentoSchedule(tbschedule)) {
            AbstractFacesContextUtils.addMessageInfo("Agendamento atualizado com sucesso.");
        } else {
            AbstractFacesContextUtils.addMessageWarn("Falhar ao realizado atualização do agendamento.");
        }

    }

    /**
     * metodo utilizado para carregar a lista de agendamentos para a schedule.
     */
    private void loadScheduleList() {

        if (listTbagendamentos != null && !listTbagendamentos.isEmpty()) {
            scheduleAgendamento.clear();

            for (Tbagendamento agendamento : listTbagendamentos) {
                DefaultScheduleEvent defaultScheduleEvent = new DefaultScheduleEvent();
                defaultScheduleEvent.setTitle(agendamento.getIdcliente().getNmcliente());

                Calendar dtDataInicial = new GregorianCalendar();
                dtDataInicial.setTime(agendamento.getTmdataagendamento());

                defaultScheduleEvent.setStartDate(dtDataInicial.getTime());

                Calendar dtDataFinal = new GregorianCalendar();
                dtDataFinal.setTime(agendamento.getTmdataagendamento());
                dtDataFinal.set(Calendar.MINUTE, dtDataFinal.get(Calendar.MINUTE) + 30);

                defaultScheduleEvent.setEndDate(dtDataFinal.getTime());
                defaultScheduleEvent.setData(agendamento.getIdagendamento());
                scheduleAgendamento.addEvent(defaultScheduleEvent);
            }
        }
    }

    /**
     * metodo utilizado para carregar a lista de sub especialidade por
     * especialidade.
     */
    public void atualizaListSubEspecialidadeByEspecialidade() {
        if (tbagendamento != null) {
            if (tbagendamento.getIdespecialidade() != null) {
                listTbsubespecialidades = agendamentoLogic.findAllTbsubespecialidadesByTbespecialidade(tbagendamento.getIdespecialidade());
            } else {
                AbstractFacesContextUtils.addMessageWarn("Nenhuma especialidade não selecionada.");
            }
        }
    }

    /**
     * metodo para atualizar a lista de funcionarios pela subespecialidade.
     */
    public void updateListEmployeeBySubEspecialidade() {
        if (tbagendamento != null) {

            if (tbagendamento.getIdsubespecialidade() != null) {
                listTbfuncionarios = agendamentoLogic.findAllTbfuncionarioBySubEspecialidade(tbagendamento.getIdsubespecialidade());
            } else {
                AbstractFacesContextUtils.addMessageWarn("Nenhuma sub especialidade não selecionada.");
            }

        }
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
     * @return the tbagendamentoSelected
     */
    public Tbagendamento getTbagendamentoSelected() {
        return tbagendamentoSelected;
    }

    /**
     * @param tbagendamentoSelected the tbagendamentoSelected to set
     */
    public void setTbagendamentoSelected(Tbagendamento tbagendamentoSelected) {
        this.tbagendamentoSelected = tbagendamentoSelected;
    }

    /**
     * @return the listTbtipostatusagendamentos
     */
    public List<Tbtipostatusagendamento> getListTbtipostatusagendamentos() {
        return listTbtipostatusagendamentos;
    }

    /**
     * @param listTbtipostatusagendamentos the listTbtipostatusagendamentos to
     * set
     */
    public void setListTbtipostatusagendamentos(List<Tbtipostatusagendamento> listTbtipostatusagendamentos) {
        this.listTbtipostatusagendamentos = listTbtipostatusagendamentos;
    }

    /**
     * @return the tbtipostatusagendamento
     */
    public Tbtipostatusagendamento getTbtipostatusagendamento() {
        return tbtipostatusagendamento;
    }

    /**
     * @param tbtipostatusagendamento the tbtipostatusagendamento to set
     */
    public void setTbtipostatusagendamento(Tbtipostatusagendamento tbtipostatusagendamento) {
        this.tbtipostatusagendamento = tbtipostatusagendamento;
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
     * @return the scheduleAgendamento
     */
    public ScheduleModel getScheduleAgendamento() {
        return scheduleAgendamento;
    }

    /**
     * @param scheduleAgendamento the scheduleAgendamento to set
     */
    public void setScheduleAgendamento(ScheduleModel scheduleAgendamento) {
        this.scheduleAgendamento = scheduleAgendamento;
    }

    /**
     * @return the event
     */
    public DefaultScheduleEvent getEvent() {
        return event;
    }

    /**
     * @param event the event to set
     */
    public void setEvent(DefaultScheduleEvent event) {
        this.event = event;
    }

    /**
     * @return the horaAgendamento
     */
    public String getHoraAgendamento() {
        return horaAgendamento;
    }

    /**
     * @param horaAgendamento the horaAgendamento to set
     */
    public void setHoraAgendamento(String horaAgendamento) {
        this.horaAgendamento = horaAgendamento;
    }

}
