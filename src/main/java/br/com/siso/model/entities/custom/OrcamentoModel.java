/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.model.entities.custom;

import br.com.siso.model.entities.Tborcamento;
import br.com.siso.model.entities.Tbprocedimento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ioliveira
 */
public class OrcamentoModel implements Serializable {

    private Tborcamento tborcamento;
    private List<Tbprocedimento> listTbprocedimentos;

    public OrcamentoModel() {
        listTbprocedimentos = new ArrayList<>();
    }

    /**
     * @return the listTbprocedimentos
     */
    public List<Tbprocedimento> getListTbprocedimentos() {
        return listTbprocedimentos;
    }

    /**
     * @param listTbprocedimentos the listTbprocedimentos to set
     */
    public void setListTbprocedimentos(List<Tbprocedimento> listTbprocedimentos) {
        this.listTbprocedimentos = listTbprocedimentos;
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
}
