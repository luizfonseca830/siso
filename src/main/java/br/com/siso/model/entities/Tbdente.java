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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ioliveira
 */
@Entity
@Table(name = "tbdente")
public class Tbdente implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddente")
    private Integer iddente;
    @Basic(optional = false)
    @Column(name = "nmdente")
    private String nmdente;
    @Basic(optional = false)
    @Column(name = "nrarco")
    private int nrarco;
    @Basic(optional = false)
    @Column(name = "nrposicao")
    private int nrposicao;
    @Basic(optional = false)
    @Column(name = "intipo")
    private int intipo;
    @Column(name = "dtdataatualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtdataatualizacao;
    @Column(name = "dtdatainclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtdatainclusao;

    public Tbdente() {
    }

    public Tbdente(Integer iddente) {
        this.iddente = iddente;
    }

    public Tbdente(Integer iddente, String nmdente, int nrarco, int nrposicao, int intipo) {
        this.iddente = iddente;
        this.nmdente = nmdente;
        this.nrarco = nrarco;
        this.nrposicao = nrposicao;
        this.intipo = intipo;
    }

    public Integer getIddente() {
        return iddente;
    }

    public void setIddente(Integer iddente) {
        this.iddente = iddente;
    }

    public String getNmdente() {
        return nmdente;
    }

    public void setNmdente(String nmdente) {
        this.nmdente = nmdente;
    }

    public int getNrarco() {
        return nrarco;
    }

    public void setNrarco(int nrarco) {
        this.nrarco = nrarco;
    }

    public int getNrposicao() {
        return nrposicao;
    }

    public void setNrposicao(int nrposicao) {
        this.nrposicao = nrposicao;
    }

    public int getIntipo() {
        return intipo;
    }

    public void setIntipo(int intipo) {
        this.intipo = intipo;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddente != null ? iddente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbdente)) {
            return false;
        }
        Tbdente other = (Tbdente) object;
        if ((this.iddente == null && other.iddente != null) || (this.iddente != null && !this.iddente.equals(other.iddente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.entities.Tbdente[ iddente=" + iddente + " ]";
    }

    @Override
    public Integer getPK() {
        return iddente;
    }

}
