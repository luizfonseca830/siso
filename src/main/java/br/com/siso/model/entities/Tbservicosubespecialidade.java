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
import javax.validation.constraints.NotNull;

/**
 *
 * @author ioliveira
 */
@Entity
@Table(name = "tbservicosubespecialidade")
public class Tbservicosubespecialidade implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idservicosubespecialidade")
    private Integer idservicosubespecialidade;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bopadrao")
    private boolean bopadrao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "boconsulta")
    private boolean boconsulta;
    @Column(name = "dtatualizacaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtatualizacaolog;
    @Column(name = "dtinclusaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtinclusaolog;
    @JoinColumn(name = "idsubespecialidade", referencedColumnName = "idsubespecialidade")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbsubespecialidade idsubespecialidade;
    @JoinColumn(name = "idservico", referencedColumnName = "idservico")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbservico idservico;

    public Tbservicosubespecialidade() {
    }

    public Tbservicosubespecialidade(Integer idservicosubespecialidade) {
        this.idservicosubespecialidade = idservicosubespecialidade;
    }

    public Tbservicosubespecialidade(Integer idservicosubespecialidade, boolean bopadrao, boolean boconsulta) {
        this.idservicosubespecialidade = idservicosubespecialidade;
        this.bopadrao = bopadrao;
        this.boconsulta = boconsulta;
    }

    public Integer getIdservicosubespecialidade() {
        return idservicosubespecialidade;
    }

    public void setIdservicosubespecialidade(Integer idservicosubespecialidade) {
        this.idservicosubespecialidade = idservicosubespecialidade;
    }

    public boolean getBopadrao() {
        return bopadrao;
    }

    public void setBopadrao(boolean bopadrao) {
        this.bopadrao = bopadrao;
    }

    public boolean getBoconsulta() {
        return boconsulta;
    }

    public void setBoconsulta(boolean boconsulta) {
        this.boconsulta = boconsulta;
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

    public Tbsubespecialidade getIdsubespecialidade() {
        return idsubespecialidade;
    }

    public void setIdsubespecialidade(Tbsubespecialidade idsubespecialidade) {
        this.idsubespecialidade = idsubespecialidade;
    }

    public Tbservico getIdservico() {
        return idservico;
    }

    public void setIdservico(Tbservico idservico) {
        this.idservico = idservico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idservicosubespecialidade != null ? idservicosubespecialidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbservicosubespecialidade)) {
            return false;
        }
        Tbservicosubespecialidade other = (Tbservicosubespecialidade) object;
        if ((this.idservicosubespecialidade == null && other.idservicosubespecialidade != null) || (this.idservicosubespecialidade != null && !this.idservicosubespecialidade.equals(other.idservicosubespecialidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.siso.model.entities.Tbservicosubespecialidade[ idservicosubespecialidade=" + idservicosubespecialidade + " ]";
    }

    @Override
    public Integer getPK() {
        return getIdservicosubespecialidade();
    }

}
