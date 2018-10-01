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
@Table(name = "tbatendimento")
public class Tbatendimento implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idatendimento")
    private Integer idatendimento;
    @Column(name = "nmqueixapaciente")
    private String nmqueixapaciente;
    @Column(name = "nmdiagnostico")
    private String nmdiagnostico;
    @Column(name = "nmcondutamedica")
    private String nmcondutamedica;
    @Basic(optional = false)
    @Column(name = "boolfinalizado")
    private boolean boolfinalizado;
    @Column(name = "boolsolicitouretorno")
    private Boolean boolsolicitouretorno;
    @Column(name = "dtdataatendimento")
    @Temporal(TemporalType.DATE)
    private Date dtdataatendimento;
    @Column(name = "tmdatainicioatendimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tmdatainicioatendimento;
    @Column(name = "tmdatafimatendimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tmdatafimatendimento;
    @JoinColumn(name = "idfuncionario", referencedColumnName = "idfuncionario")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbfuncionario idfuncionario;
    @JoinColumn(name = "idagendamento", referencedColumnName = "idagendamento")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbagendamento idagendamento;

    public Tbatendimento() {
    }

    public Tbatendimento(Integer idatendimento) {
        this.idatendimento = idatendimento;
    }

    public Tbatendimento(Integer idatendimento, boolean boolfinalizado) {
        this.idatendimento = idatendimento;
        this.boolfinalizado = boolfinalizado;
    }

    public Integer getIdatendimento() {
        return idatendimento;
    }

    public void setIdatendimento(Integer idatendimento) {
        this.idatendimento = idatendimento;
    }

    public String getNmqueixapaciente() {
        return nmqueixapaciente;
    }

    public void setNmqueixapaciente(String nmqueixapaciente) {
        this.nmqueixapaciente = nmqueixapaciente;
    }

    public String getNmdiagnostico() {
        return nmdiagnostico;
    }

    public void setNmdiagnostico(String nmdiagnostico) {
        this.nmdiagnostico = nmdiagnostico;
    }

    public String getNmcondutamedica() {
        return nmcondutamedica;
    }

    public void setNmcondutamedica(String nmcondutamedica) {
        this.nmcondutamedica = nmcondutamedica;
    }

    public boolean getBoolfinalizado() {
        return boolfinalizado;
    }

    public void setBoolfinalizado(boolean boolfinalizado) {
        this.boolfinalizado = boolfinalizado;
    }

    public Boolean getBoolsolicitouretorno() {
        return boolsolicitouretorno;
    }

    public void setBoolsolicitouretorno(Boolean boolsolicitouretorno) {
        this.boolsolicitouretorno = boolsolicitouretorno;
    }

    public Date getDtdataatendimento() {
        return dtdataatendimento;
    }

    public void setDtdataatendimento(Date dtdataatendimento) {
        this.dtdataatendimento = dtdataatendimento;
    }

    public Date getTmdatainicioatendimento() {
        return tmdatainicioatendimento;
    }

    public void setTmdatainicioatendimento(Date tmdatainicioatendimento) {
        this.tmdatainicioatendimento = tmdatainicioatendimento;
    }

    public Date getTmdatafimatendimento() {
        return tmdatafimatendimento;
    }

    public void setTmdatafimatendimento(Date tmdatafimatendimento) {
        this.tmdatafimatendimento = tmdatafimatendimento;
    }

    public Tbfuncionario getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(Tbfuncionario idfuncionario) {
        this.idfuncionario = idfuncionario;
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
        hash += (idatendimento != null ? idatendimento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbatendimento)) {
            return false;
        }
        Tbatendimento other = (Tbatendimento) object;
        if ((this.idatendimento == null && other.idatendimento != null) || (this.idatendimento != null && !this.idatendimento.equals(other.idatendimento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.entities.Tbatendimento[ idatendimento=" + idatendimento + " ]";
    }

    @Override
    public Integer getPK() {
        return idatendimento;
    }

}
