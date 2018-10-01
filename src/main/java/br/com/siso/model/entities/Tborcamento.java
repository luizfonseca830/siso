/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.model.entities;

import br.com.siso.web.faces.converter.Identificador;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ioliveira
 */
@Entity
@Table(name = "tborcamento")
public class Tborcamento implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idorcamento")
    private Integer idorcamento;
    @Basic(optional = false)
    @Column(name = "vltotal")
    private float vltotal;
    @Column(name = "dtdataatualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtdataatualizacao;
    @Column(name = "dtdatainclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtdatainclusao;
    @Column(name = "dtdataorcamento")
    @Temporal(TemporalType.DATE)
    private Date dtdataorcamento;
    @Column(name = "tmdataorcamento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tmdataorcamento;
    @JoinColumn(name = "idtipostatusorcamento", referencedColumnName = "idtipostatusorcamento")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbtipostatusorcamento idtipostatusorcamento;
    @JoinColumn(name = "iddependente", referencedColumnName = "iddependente")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbdependente iddependente;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbcliente idcliente;
    @JoinColumn(name = "idagendamento", referencedColumnName = "idagendamento")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbagendamento idagendamento;

    public Tborcamento() {
    }

    public Tborcamento(Integer idorcamento) {
        this.idorcamento = idorcamento;
    }

    public Tborcamento(Integer idorcamento, float vltotal) {
        this.idorcamento = idorcamento;
        this.vltotal = vltotal;
    }

    public Integer getIdorcamento() {
        return idorcamento;
    }

    public void setIdorcamento(Integer idorcamento) {
        this.idorcamento = idorcamento;
    }

    public float getVltotal() {
        return vltotal;
    }

    public void setVltotal(float vltotal) {
        this.vltotal = vltotal;
    }

    public Date getDtdataatualizacao() {
        return dtdataatualizacao;
    }

    public void setDtdataatualizacao(Date dtdataatualizacao) {
        this.dtdataatualizacao = dtdataatualizacao;
    }

    public Date getDtdatainclusao() {
        return dtdatainclusao;
    }

    public void setDtdatainclusao(Date dtdatainclusao) {
        this.dtdatainclusao = dtdatainclusao;
    }

    public Date getDtdataorcamento() {
        return dtdataorcamento;
    }

    public void setDtdataorcamento(Date dtdataorcamento) {
        this.dtdataorcamento = dtdataorcamento;
    }

    public Date getTmdataorcamento() {
        return tmdataorcamento;
    }

    public void setTmdataorcamento(Date tmdataorcamento) {
        this.tmdataorcamento = tmdataorcamento;
    }

    public Tbtipostatusorcamento getIdtipostatusorcamento() {
        return idtipostatusorcamento;
    }

    public void setIdtipostatusorcamento(Tbtipostatusorcamento idtipostatusorcamento) {
        this.idtipostatusorcamento = idtipostatusorcamento;
    }

    public Tbdependente getIddependente() {
        return iddependente;
    }

    public void setIddependente(Tbdependente iddependente) {
        this.iddependente = iddependente;
    }

    public Tbcliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Tbcliente idcliente) {
        this.idcliente = idcliente;
    }

    public Tbagendamento getIdagendamento() {
        return idagendamento;
    }

    public void setIdagendamento(Tbagendamento idagendamento) {
        this.idagendamento = idagendamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idorcamento != null ? idorcamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tborcamento)) {
            return false;
        }
        Tborcamento other = (Tborcamento) object;
        if ((this.idorcamento == null && other.idorcamento != null) || (this.idorcamento != null && !this.idorcamento.equals(other.idorcamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.entities.Tborcamento[ idorcamento=" + idorcamento + " ]";
    }

    @Override
    public Integer getPK() {
        return idorcamento;
    }

}
