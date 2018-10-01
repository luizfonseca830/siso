/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.model.entities;

import br.com.siso.web.faces.converter.Identificador;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ioliveira
 */
@Entity
@Table(name = "tbespprofissional")
public class Tbespprofissional implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idespprofissional")
    private Integer idespprofissional;
    @Column(name = "dtatualizacaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtatualizacaolog;
    @Column(name = "dtinclusaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtinclusaolog;
    @OneToMany(mappedBy = "idespprofissional", fetch = FetchType.EAGER)
    private List<Tbatendimentoprofissional> tbatendimentoprofissionalList;
    @JoinColumn(name = "idfuncionario", referencedColumnName = "idfuncionario")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbfuncionario idfuncionario;
    @JoinColumn(name = "idespecialidade", referencedColumnName = "idespecialidade")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbespecialidade idespecialidade;

    public Tbespprofissional() {
    }

    public Tbespprofissional(Integer idespprofissional) {
        this.idespprofissional = idespprofissional;
    }

    public Integer getIdespprofissional() {
        return idespprofissional;
    }

    public void setIdespprofissional(Integer idespprofissional) {
        this.idespprofissional = idespprofissional;
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

    @XmlTransient
    public List<Tbatendimentoprofissional> getTbatendimentoprofissionalList() {
        return tbatendimentoprofissionalList;
    }

    public void setTbatendimentoprofissionalList(List<Tbatendimentoprofissional> tbatendimentoprofissionalList) {
        this.tbatendimentoprofissionalList = tbatendimentoprofissionalList;
    }

    public Tbfuncionario getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(Tbfuncionario idfuncionario) {
        this.idfuncionario = idfuncionario;
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
        hash += (idespprofissional != null ? idespprofissional.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbespprofissional)) {
            return false;
        }
        Tbespprofissional other = (Tbespprofissional) object;
        if ((this.idespprofissional == null && other.idespprofissional != null) || (this.idespprofissional != null && !this.idespprofissional.equals(other.idespprofissional))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.siso.model.entities.Tbespprofissional[ idespprofissional=" + idespprofissional + " ]";
    }

    @Override
    public Integer getPK() {
        return getIdespprofissional();
    }

}
