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
import javax.validation.constraints.Size;

/**
 *
 * @author ioliveira
 */
@Entity
@Table(name = "tbsubespecialidade")
public class Tbsubespecialidade implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsubespecialidade")
    private Integer idsubespecialidade;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nmsubespecialidade")
    private String nmsubespecialidade;
    @Column(name = "dtatualizacaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtatualizacaolog;
    @Column(name = "dtinclusaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtinclusaolog;
    @JoinColumn(name = "idespecialidade", referencedColumnName = "idespecialidade")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbespecialidade idespecialidade;

    public Tbsubespecialidade() {
    }

    public Tbsubespecialidade(Integer idsubespecialidade) {
        this.idsubespecialidade = idsubespecialidade;
    }

    public Tbsubespecialidade(Integer idsubespecialidade, String nmsubespecialidade) {
        this.idsubespecialidade = idsubespecialidade;
        this.nmsubespecialidade = nmsubespecialidade;
    }

    public Integer getIdsubespecialidade() {
        return idsubespecialidade;
    }

    public void setIdsubespecialidade(Integer idsubespecialidade) {
        this.idsubespecialidade = idsubespecialidade;
    }

    public String getNmsubespecialidade() {
        return nmsubespecialidade;
    }

    public void setNmsubespecialidade(String nmsubespecialidade) {
        this.nmsubespecialidade = nmsubespecialidade;
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

    public Tbespecialidade getIdespecialidade() {
        return idespecialidade;
    }

    public void setIdespecialidade(Tbespecialidade idespecialidade) {
        this.idespecialidade = idespecialidade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsubespecialidade != null ? idsubespecialidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbsubespecialidade)) {
            return false;
        }
        Tbsubespecialidade other = (Tbsubespecialidade) object;
        if ((this.idsubespecialidade == null && other.idsubespecialidade != null) || (this.idsubespecialidade != null && !this.idsubespecialidade.equals(other.idsubespecialidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.siso.model.entities.Tbsubespecialidade[ idsubespecialidade=" + idsubespecialidade + " ]";
    }

    @Override
    public Integer getPK() {
        return getIdsubespecialidade();
    }

}
