/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.logic.agendamento;

import br.com.siso.model.daos.TbagendamentoFacade;
import br.com.siso.model.entities.Tbagendamento;
import br.com.siso.model.entities.Tbcliente;
import br.com.siso.model.entities.Tbespecialidade;
import br.com.siso.model.entities.Tbfuncionario;
import br.com.siso.model.entities.Tbsubespecialidade;
import br.com.siso.model.entities.Tbtipostatusagendamento;
import br.com.siso.web.control.logic.cliente.ClienteLogic;
import br.com.siso.web.control.logic.especialidade.EspecialidadeLogic;
import br.com.siso.web.control.logic.funcionario.FuncionarioLogic;
import br.com.siso.web.control.logic.orcamento.OrcamentoLogic;
import br.com.siso.web.control.logic.subespecialidade.SubEspecialidadeLogic;
import br.com.siso.web.control.logic.tipostatusagendamento.TipoStatusAgendamentoLogic;
import br.com.siso.web.control.module.AbstractModuleCore;
import br.com.siso.web.faces.utils.Shareds;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ioliveira
 */
@Stateless
public class AgendamentoLogic extends AbstractModuleCore {

    @EJB
    private TbagendamentoFacade tbagendamentoFacade;
    @EJB
    private ClienteLogic clienteLogic;
    @EJB
    private FuncionarioLogic funcionarioLogic;
    @EJB
    private TipoStatusAgendamentoLogic tipoStatusAgendamentoLogic;
    @EJB
    private EspecialidadeLogic especialidadeLogic;
    @EJB
    private SubEspecialidadeLogic subEspecialidadeLogic;
    @EJB
    private OrcamentoLogic orcamentoLogic;

    /**
     * metodo utilizado para carregar a lista de todos os funcionarios por
     * subespecialidade.
     *
     * @param tbsubespecialidade
     * @return list of tbfuncionario
     */
    public List<Tbfuncionario> findAllTbfuncionarioBySubEspecialidade(final Tbsubespecialidade tbsubespecialidade) {
        return funcionarioLogic.findAllTbfuncionarioBySubEspecialidade(tbsubespecialidade);
    }

    /**
     * metodo utilizado para recuperar um Agendamento por idAgendamento.
     *
     * @param idAgendamento
     * @return tbagendamento
     */
    public Tbagendamento findTbagendamentoByIdAgendamento(final Integer idAgendamento) {
        return tbagendamentoFacade.find(idAgendamento, super.getEM());
    }

    /**
     * metodo utilizado para remover um determinado agendamento.
     *
     * @param tbagendamento
     * @return true or false
     */
    public boolean removeTbagendamento(final Tbagendamento tbagendamento) {

        boolean result = false;

        if (tbagendamentoFacade.remove(tbagendamento, super.getEM())) {
            result = true;
        }

        return result;
    }

    /**
     * metodo para criar um novo agendamento.
     *
     * @param tbagendamento
     * @return true or false
     */
    public boolean createTbagendamento(final Tbagendamento tbagendamento) {

        boolean result = false;

        tbagendamento.setNragendamento(generateScheduleCode());
        tbagendamento.setDtdataagendamento(tbagendamento.getTmdataagendamento());
        tbagendamento.setDtinclusaolog(new Date());

        if (orcamentoLogic.findTborcamentoByIdClienteAndStatusAberto(tbagendamento.getIdcliente()) != null) {
            tbagendamento.setIntipo(0);
            tbagendamento.setIntipoagendamento(tbagendamento.getIntipo());
            tbagendamento.setInstatus(tbagendamento.getIntipo());
            tbagendamento.setInturno(tbagendamento.getIntipo());
            tbagendamento.setIntipoconsulta(tbagendamento.getIntipo());
        } else {
            tbagendamento.setIntipo(1);
            tbagendamento.setIntipoagendamento(tbagendamento.getIntipo());
            tbagendamento.setInstatus(tbagendamento.getIntipo());
            tbagendamento.setInturno(tbagendamento.getIntipo());
            tbagendamento.setIntipoconsulta(tbagendamento.getIntipo());
        }

        tbagendamento.setIdtipostatusagendamento(new Tbtipostatusagendamento(1));
        tbagendamento.setIdoperadorcadastro(Shareds.getUser().getIdusuario());

        if (tbagendamentoFacade.create(tbagendamento, super.getEM())) {
            result = true;
        }

        return result;
    }

    /**
     * metodo para editar status do agendamento.
     *
     * @param tbagendamento
     * @param tbtipostatusagendamento
     * @return true or false
     */
    public boolean editarStatusTbagendamento(final Tbagendamento tbagendamento, final Tbtipostatusagendamento tbtipostatusagendamento) {
        boolean result = false;

        tbagendamento.setIdtipostatusagendamento(tbtipostatusagendamento);
        tbagendamento.setIdoperadoralteracao(Shareds.getUser().getIdusuario());
        tbagendamento.setDtatualizacao(new Date());
        tbagendamento.setDtatualizacaolog(new Date());

        if (editTbagendamento(tbagendamento)) {
            result = true;
        }

        return result;
    }

    /**
     * metodo utilizado para atualizar tbagendamento.
     *
     * @param tbagendamento
     * @return true or false
     */
    public boolean editTbagendamento(final Tbagendamento tbagendamento) {
        
        tbagendamento.setDtdataagendamento(tbagendamento.getTmdataagendamento());
        tbagendamento.setIdoperadoralteracao(Shareds.getUser().getIdusuario());
        tbagendamento.setDtatualizacao(new Date());
        tbagendamento.setDtatualizacaolog(new Date());

        return tbagendamentoFacade.edit(tbagendamento, super.getEM());
    }
    
    /**
     * metodo utilizado para atualizar tbagendamento.
     *
     * @param tbagendamento
     * @return true or false
     */
    public boolean editTbagendamentoSchedule(Tbagendamento tbagendamento) {
                
        tbagendamento.setIdoperadoralteracao(Shareds.getUser().getIdusuario());
        tbagendamento.setDtatualizacao(new Date());
        tbagendamento.setDtatualizacaolog(new Date());

        return tbagendamentoFacade.edit(tbagendamento, super.getEM());
    }

    /**
     * metodo para carregar a lista de todos os agendamento do dia.
     *
     * @return list of tbagendamento
     */
    public List<Tbagendamento> findAllTbagendamentoFromCurrentDay() {
        return tbagendamentoFacade.findAllTbagendamentoFromCurrentDay(super.getEM());
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
     * metodo que carrega todos os funcionario cadastrados.
     *
     * @return
     */
    public List<Tbfuncionario> findAllTbfuncionario() {
        return funcionarioLogic.findAllTbfuncionario();
    }

    /**
     * metodo utilizado para filtrar os agendamentos por data, cliente e
     * funcionario.
     *
     * @param data
     * @param tbcliente
     * @param tbfuncionario
     * @return list of agendamento
     */
    public List<Tbagendamento> findAllTbagendamentoByDataAndPacienteAndFuncionario(final Date data,
            final Tbcliente tbcliente, final Tbfuncionario tbfuncionario) {

        StringBuilder filtro = new StringBuilder();

        if (tbcliente != null) {
            filtro.append(" AND t.idcliente.idcliente = ").append(tbcliente.getIdcliente());
        }

        if (tbfuncionario != null) {
            filtro.append(" AND t.idfuncionario.idfuncionario = ").append(tbfuncionario.getIdfuncionario());
        }

        return tbagendamentoFacade.findAllTbagendamentoByDataAndPacienteAndFuncionario(data, filtro.toString(), super.getEM());
    }

    /**
     * metodo para carregar todos os tipos de status para o agendamento.
     *
     * @return list of tbtipostatusagendamento
     */
    public List<Tbtipostatusagendamento> findAllTbtipostatusagendamentos() {
        return tipoStatusAgendamentoLogic.findAllTbtipostatusagendamentos();
    }

    /**
     * metodo para carregar todas as especialidade.
     *
     * @return list of tbespecialidade
     */
    public List<Tbespecialidade> findAllTbespecialidades() {
        return especialidadeLogic.findAllTbespecialidades();
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
     * metodo para carregar todas as subespecialidades.
     *
     * @return list of tbsubespecialidade
     */
    public List<Tbsubespecialidade> findAllTbsubespecialidades() {
        return subEspecialidadeLogic.findAllTbsubespecialidades();
    }

    /**
     * metodo para carregar a lista de todos os agendamento do dia com o status
     * de presente para atendimento.
     *
     * @return list of tbagendamento
     */
    public List<Tbagendamento> findAllTbagendamentoDiaAtualComStatusDePresente() {
        return tbagendamentoFacade.findAllTbagendamentoDiaAtualComStatusDePresente(super.getEM());
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

        StringBuilder filtro = new StringBuilder();

        if (tbcliente != null) {
            filtro.append(" AND t.idcliente.idcliente = ").append(tbcliente.getIdcliente());
        }

        if (tbfuncionario != null) {
            filtro.append(" AND t.idfuncionario.idfuncionario = ").append(tbfuncionario.getIdfuncionario());
        }

        return tbagendamentoFacade.findAllTbagendamentoByDataAndPacienteAndFuncionarioAndStatusPresente(data, filtro.toString(), super.getEM());
    }

    /**
     * metodo que carrega a lista de todos os agendamentos ordenado por
     * nragendamento.
     *
     * @return list of tbagendamento
     */
    public List<Tbagendamento> findAllTbagendamentosOrderByNrAgendamento() {
        return tbagendamentoFacade.findAllTbagendamentosOrderByNrAgendamento(super.getEM());
    }

    /**
     * metodo para gerar codigo do agendamento.
     *
     * @return codigo do agendamento formatado
     */
    private String generateScheduleCode() {

        List<Tbagendamento> listTbagendamentos = tbagendamentoFacade.findAllTbagendamentosOrderByNrAgendamento(super.getEM());
        String ultimoNumeroGerado = null;
        String result;

        Calendar calendar = Calendar.getInstance();
        final Integer anoAtual = calendar.get(Calendar.YEAR);

        if (listTbagendamentos != null && !listTbagendamentos.isEmpty()) {
            final Tbagendamento tbagendamento = listTbagendamentos.get(listTbagendamentos.size() - 1);
            ultimoNumeroGerado = tbagendamento.getNragendamento();
        }

        if (ultimoNumeroGerado == null) {
            result = String.format("%06d/%d", 1, anoAtual);
        } else {

            final Integer sequencial = Integer.parseInt(ultimoNumeroGerado.split("/")[0]);
            final Integer ano = Integer.parseInt(ultimoNumeroGerado.split("/")[1]);
            result = String.format("%06d/%d", ano.equals(anoAtual) ? sequencial + 1 : 1, anoAtual);
        }

        return result;
    }

}
