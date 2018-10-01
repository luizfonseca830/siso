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
@Table(name = "tbcidade")
public class Tbcidade implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcidade")
    private Integer idcidade;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nmcidade")
    private String nmcidade;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "nmuf")
    private String nmuf;
    @Column(name = "dtatualizacaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtatualizacaolog;
    @Column(name = "dtinclusaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtinclusaolog;
    @OneToMany(mappedBy = "idcidade", fetch = FetchType.EAGER)
    private List<Tbcliente> tbclienteList;
    @OneToMany(mappedBy = "idcidade", fetch = FetchType.EAGER)
    private List<Tbempresacliente> tbempresaclienteList;
    @OneToMany(mappedBy = "idcidade", fetch = FetchType.EAGER)
    private List<Tbfuncionario> tbfuncionarioList;
    @OneToMany(mappedBy = "idcidade", fetch = FetchType.EAGER)
    private List<Tbdependente> tbdependenteList;

    public Tbcidade() {
    }

    public Tbcidade(Integer idcidade) {
        this.idcidade = idcidade;
    }

    public Tbcidade(Integer idcidade, String nmcidade, String nmuf) {
        this.idcidade = idcidade;
        this.nmcidade = nmcidade;
        this.nmuf = nmuf;
    }

    public Integer getIdcidade() {
        return idcidade;
    }

    public void setIdcidade(Integer idcidade) {
        this.idcidade = idcidade;
    }

    public String getNmcidade() {
        return nmcidade;
    }

    public void setNmcidade(String nmcidade) {
        this.nmcidade = nmcidade;
    }

    public String getNmuf() {
        return nmuf;
    }

    public void setNmuf(String nmuf) {
        this.nmuf = nmuf;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcidade != null ? idcidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbcidade)) {
            return false;
        }
        Tbcidade other = (Tbcidade) object;
        if ((this.idcidade == null && other.idcidade != null) || (this.idcidade != null && !this.idcidade.equals(other.idcidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.siso.model.entities.Tbcidade[ idcidade=" + idcidade + " ]";
    }

    @Override
    public Integer getPK() {
        return getIdcidade();
    }

}
