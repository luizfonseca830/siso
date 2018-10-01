/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.model.entities;

import br.com.siso.web.faces.converter.Identificador;
import java.io.Serializable;
import java.math.BigInteger;
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
import javax.validation.constraints.NotNull;

/**
 *
 * @author ioliveira
 */
@Entity
@Table(name = "tbatendimentoprofissional")
public class Tbatendimentoprofissional implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idatendimentoprofissional")
    private Integer idatendimentoprofissional;
    @Basic(optional = false)
    @NotNull
    @Column(name = "indiasemana")
    private int indiasemana;
    @Basic(optional = false)
    @NotNull
    @Column(name = "inturno")
    private int inturno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hrinicio")
    @Temporal(TemporalType.TIME)
    private Date hrinicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hrfim")
    @Temporal(TemporalType.TIME)
    private Date hrfim;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hrtempoconsulta")
    @Temporal(TemporalType.TIME)
    private Date hrtempoconsulta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "intipoconsulta")
    private int intipoconsulta;
    @Column(name = "idsubespprofissional")
    private BigInteger idsubespprofissional;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qtpacienteatender")
    private int qtpacienteatender;
    @Column(name = "dtatualizacaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtatualizacaolog;
    @Column(name = "dtinclusaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtinclusaolog;
    @Column(name = "intipoatendimento")
    private Integer intipoatendimento;
    @JoinColumn(name = "idfuncionario", referencedColumnName = "idfuncionario")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbfuncionario idfuncionario;
    @JoinColumn(name = "idespprofissional", referencedColumnName = "idespprofissional")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbespprofissional idespprofissional;

    public Tbatendimentoprofissional() {
    }

    public Tbatendimentoprofissional(Integer idatendimentoprofissional) {
        this.idatendimentoprofissional = idatendimentoprofissional;
    }

    public Tbatendimentoprofissional(Integer idatendimentoprofissional, int indiasemana, int inturno, Date hrinicio, Date hrfim, Date hrtempoconsulta, int intipoconsulta, int qtpacienteatender) {
        this.idatendimentoprofissional = idatendimentoprofissional;
        this.indiasemana = indiasemana;
        this.inturno = inturno;
        this.hrinicio = hrinicio;
        this.hrfim = hrfim;
        this.hrtempoconsulta = hrtempoconsulta;
        this.intipoconsulta = intipoconsulta;
        this.qtpacienteatender = qtpacienteatender;
    }

    public Integer getIdatendimentoprofissional() {
        return idatendimentoprofissional;
    }

    public void setIdatendimentoprofissional(Integer idatendimentoprofissional) {
        this.idatendimentoprofissional = idatendimentoprofissional;
    }

    public int getIndiasemana() {
        return indiasemana;
    }

    public void setIndiasemana(int indiasemana) {
        this.indiasemana = indiasemana;
    }

    public int getInturno() {
        return inturno;
    }

    public void setInturno(int inturno) {
        this.inturno = inturno;
    }

    public Date getHrinicio() {
        return hrinicio;
    }

    public void setHrinicio(Date hrinicio) {
        this.hrinicio = hrinicio;
    }

    public Date getHrfim() {
        return hrfim;
    }

    public void setHrfim(Date hrfim) {
        this.hrfim = hrfim;
    }

    public Date getHrtempoconsulta() {
        return hrtempoconsulta;
    }

    public void setHrtempoconsulta(Date hrtempoconsulta) {
        this.hrtempoconsulta = hrtempoconsulta;
    }

    public int getIntipoconsulta() {
        return intipoconsulta;
    }

    public void setIntipoconsulta(int intipoconsulta) {
        this.intipoconsulta = intipoconsulta;
    }

    public BigInteger getIdsubespprofissional() {
        return idsubespprofissional;
    }

    public void setIdsubespprofissional(BigInteger idsubespprofissional) {
        this.idsubespprofissional = idsubespprofissional;
    }

    public int getQtpacienteatender() {
        return qtpacienteatender;
    }

    public void setQtpacienteatender(int qtpacienteatender) {
        this.qtpacienteatender = qtpacienteatender;
    }

    public Date getDtatualizacaolog() {
        return dtatualizacaolog;
    }

    public void setDtatualizacaolog(Date dtatualizacaolog) {
        this.dtatualizacaolog = dtatualizacaolog;
    }

    public Date getDtinclusaolog() {
        return dtinclusaolog;
    }

    public void setDtinclusaolog(Date dtinclusaolog) {
        this.dtinclusaolog = dtinclusaolog;
    }

    public Integer getIntipoatendimento() {
        return intipoatendimento;
    }

    public void setIntipoatendimento(Integer intipoatendimento) {
        this.intipoatendimento = intipoatendimento;
    }

    public Tbfuncionario getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(Tbfuncionario idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    public Tbespprofissional getIdespprofissional() {
        return idespprofissional;
    }

    public void setIdespprofissional(Tbespprofissional idespprofissional) {
        this.idespprofissional = idespprofissional;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idatendimentoprofissional != null ? idatendimentoprofissional.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbatendimentoprofissional)) {
            return false;
        }
        Tbatendimentoprofissional other = (Tbatendimentoprofissional) object;
        if ((this.idatendimentoprofissional == null && other.idatendimentoprofissional != null) || (this.idatendimentoprofissional != null && !this.idatendimentoprofissional.equals(other.idatendimentoprofissional))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.siso.model.entities.Tbatendimentoprofissional[ idatendimentoprofissional=" + idatendimentoprofissional + " ]";
    }

    @Override
    public Integer getPK() {
        return getIdatendimentoprofissional();
    }

}
