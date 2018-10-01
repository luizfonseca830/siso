/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.web.control.logic.funcionario;

import br.com.siso.model.daos.TbfuncionarioFacade;
import br.com.siso.model.entities.Tbespecialidade;
import br.com.siso.model.entities.Tbespprofissional;
import br.com.siso.model.entities.Tbfuncionario;
import br.com.siso.model.entities.Tbsubespecialidade;
import br.com.siso.model.entities.Tbsubespprofissional;
import br.com.siso.web.control.logic.especialidade.EspecialidadeLogic;
import br.com.siso.web.control.logic.espprofissional.EspProfissionalLogic;
import br.com.siso.web.control.logic.subespecialidade.SubEspecialidadeLogic;
import br.com.siso.web.control.logic.subespprofissional.SubEspProfissionalLogic;
import br.com.siso.web.control.module.AbstractModuleCore;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ioliveira
 */
@Stateless
public class FuncionarioLogic extends AbstractModuleCore {

    @EJB
    private TbfuncionarioFacade tbfuncionarioFacade;
    @EJB
    private EspecialidadeLogic especialidadeLogic;
    @EJB
    private SubEspecialidadeLogic subEspecialidadeLogic;
    @EJB
    private EspProfissionalLogic espProfissionalLogic;
    @EJB
    private SubEspProfissionalLogic subEspProfissionalLogic;

    /**
     * metodo que carrega todos os funcionario.
     *
     * @return
     */
    public List<Tbfuncionario> findAllTbfuncionario() {
        return tbfuncionarioFacade.findAllTbfuncionario(super.getEM());
    }

    /**
     * metodo utilizado para carregar a lista de todos os funcionarios por
     * subespecialidade.
     *
     * @param tbsubespecialidade
     * @return list of tbfuncionario
     */
    public List<Tbfuncionario> findAllTbfuncionarioBySubEspecialidade(final Tbsubespecialidade tbsubespecialidade) {
        return tbfuncionarioFacade.findAllTbfuncionarioBySubEspecialidade(tbsubespecialidade, super.getEM());
    }

    /**
     * Metodo que recupera a lista de todos os funcionarios por nome
     *
     * @param name
     * @return
     */
    public List<Tbfuncionario> findAllTbfuncionarioByName(String name) {
        return tbfuncionarioFacade.findAllTbfuncionarioByName(name, super.getEM());
    }

    /**
     * metodo utilizado para criar um novo funcionario.
     *
     * @param tbfuncionario
     * @return true or false
     */
    public boolean createFuncionario(final Tbfuncionario tbfuncionario) {
        tbfuncionario.setDtcadastro(new Date());
        tbfuncionario.setDtalteracao(new Date());
        tbfuncionario.setDtatualizacaolog(new Date());
        tbfuncionario.setIdoperadorcadastro(BigInteger.ZERO);
        return tbfuncionarioFacade.create(tbfuncionario, super.getEM());
    }

    /**
     * metodo utilizado para editar um determinado funcionario.
     *
     * @param tbfuncionario uncionario
     * @return
     */
    public boolean editFuncionario(final Tbfuncionario tbfuncionario) {
        tbfuncionario.setDtalteracao(new Date());
        tbfuncionario.setDtatualizacaolog(new Date());

        return tbfuncionarioFacade.edit(tbfuncionario, super.getEM());
    }

    /**
     * metodo utilizado para excluir um determinado funcionario.
     *
     * @param tbfuncionario
     * @return
     */
    public boolean removeFuncionario(final Tbfuncionario tbfuncionario) {
        tbfuncionario.setDtexclusao(new Date());
        return tbfuncionarioFacade.remove(tbfuncionario, super.getEM());

    }

    public Tbfuncionario findTbfuncionarioById(final Integer idFuncionario) {
        return tbfuncionarioFacade.find(idFuncionario, super.getEM());
    }

    public boolean ValidarCamposFuncionarios(final Tbfuncionario tbfuncionario) {
        boolean result = false;

        if (tbfuncionario != null 
                && tbfuncionario.getNmfuncionario() != null 
                && !tbfuncionario.getNmfuncionario().trim().isEmpty()) {
            result = true;
        }
        return result;
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

    public boolean create(final Tbespprofissional tbespprofissional) {
        return espProfissionalLogic.create(tbespprofissional);
    }

    public boolean create(final Tbsubespprofissional tbsubespprofissional) {
        return subEspProfissionalLogic.create(tbsubespprofissional);
    }
}
