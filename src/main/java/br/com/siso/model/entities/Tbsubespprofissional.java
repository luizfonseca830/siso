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
@Table(name = "tbsubespprofissional")
public class Tbsubespprofissional implements Serializable, Identificador<Integer>{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsubespprofissional")
    private Integer idsubespprofissional;
    @Column(name = "dtatualizacaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtatualizacaolog;
    @Column(name = "dtinclusaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtinclusaolog;
    @JoinColumn(name = "idsubespecialidade", referencedColumnName = "idsubespecialidade")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbsubespecialidade idsubespecialidade;
    @JoinColumn(name = "idfuncionario", referencedColumnName = "idfuncionario")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbfuncionario idfuncionario;

    public Tbsubespprofissional() {
    }

    public Tbsubespprofissional(Integer idsubespprofissional) {
        this.idsubespprofissional = idsubespprofissional;
    }

    public Integer getIdsubespprofissional() {
        return idsubespprofissional;
    }

    public void setIdsubespprofissional(Integer idsubespprofissional) {
        this.idsubespprofissional = idsubespprofissional;
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

    public Tbfuncionario getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(Tbfuncionario idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsubespprofissional != null ? idsubespprofissional.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbsubespprofissional)) {
            return false;
        }
        Tbsubespprofissional other = (Tbsubespprofissional) object;
        if ((this.idsubespprofissional == null && other.idsubespprofissional != null) || (this.idsubespprofissional != null && !this.idsubespprofissional.equals(other.idsubespprofissional))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.siso.model.entities.Tbsubespprofissional[ idsubespprofissional=" + idsubespprofissional + " ]";
    }

    @Override
    public Integer getPK() {
       return getIdsubespprofissional();    }
    
}
