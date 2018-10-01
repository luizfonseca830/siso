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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ioliveira
 */
@Entity
@Table(name = "tbbairro")
public class Tbbairro implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbairro")
    private Integer idbairro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nmbairro")
    private String nmbairro;
    @JoinColumn(name = "idcidade", referencedColumnName = "idcidade")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbcidade idcidade;
    @Column(name = "dtatualizacaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtatualizacaolog;
    @Column(name = "dtinclusaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtinclusaolog;
    @OneToMany(mappedBy = "idbairro", fetch = FetchType.EAGER)
    private List<Tbcliente> tbclienteList;
    @OneToMany(mappedBy = "idbairro", fetch = FetchType.EAGER)
    private List<Tbempresacliente> tbempresaclienteList;
    @OneToMany(mappedBy = "idbairro", fetch = FetchType.EAGER)
    private List<Tbfuncionario> tbfuncionarioList;
    @OneToMany(mappedBy = "idbairro", fetch = FetchType.EAGER)
    private List<Tbdependente> tbdependenteList;

    public Tbbairro() {
    }

    public Tbbairro(Integer idbairro) {
        this.idbairro = idbairro;
    }

    public Tbbairro(Integer idbairro, String nmbairro) {
        this.idbairro = idbairro;
        this.nmbairro = nmbairro;
    }

    public Integer getIdbairro() {
        return idbairro;
    }

    public void setIdbairro(Integer idbairro) {
        this.idbairro = idbairro;
    }

    public String getNmbairro() {
        return nmbairro;
    }

    public void setNmbairro(String nmbairro) {
        this.nmbairro = nmbairro;
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
    public List<Tbcliente> getTbclienteList() {
        return tbclienteList;
    }

    public void setTbclienteList(List<Tbcliente> tbclienteList) {
        this.tbclienteList = tbclienteList;
    }

    @XmlTransient
    public List<Tbempresacliente> getTbempresaclienteList() {
        return tbempresaclienteList;
    }

    public void setTbempresaclienteList(List<Tbempresacliente> tbempresaclienteList) {
        this.tbempresaclienteList = tbempresaclienteList;
    }

    @XmlTransient
    public List<Tbfuncionario> getTbfuncionarioList() {
        return tbfuncionarioList;
    }

    public void setTbfuncionarioList(List<Tbfuncionario> tbfuncionarioList) {
        this.tbfuncionarioList = tbfuncionarioList;
    }

    @XmlTransient
    public List<Tbdependente> getTbdependenteList() {
        return tbdependenteList;
    }

    public void setTbdependenteList(List<Tbdependente> tbdependenteList) {
        this.tbdependenteList = tbdependenteList;
    }
    
    public Tbcidade getIdcidade() {
        return idcidade;
    }

    public void setIdcidade(Tbcidade idcidade) {
        this.idcidade = idcidade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbairro != null ? idbairro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbbairro)) {
            return false;
        }
        Tbbairro other = (Tbbairro) object;
        if ((this.idbairro == null && other.idbairro != null) || (this.idbairro != null && !this.idbairro.equals(other.idbairro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.siso.model.entities.Tbbairro[ idbairro=" + idbairro + " ]";
    }

    @Override
    public Integer getPK() {
        return getIdbairro();
    }

}
