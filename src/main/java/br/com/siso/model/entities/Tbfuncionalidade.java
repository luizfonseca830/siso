/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.model.entities;

import br.com.siso.web.faces.converter.Identificador;
import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ioliveira
 */
@Entity
@Table(name = "tbfuncionalidade")
public class Tbfuncionalidade implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfuncionalidade")
    private Integer idfuncionalidade;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nmfuncionalidade")
    private String nmfuncionalidade;
    @Size(max = 255)
    @Column(name = "txurl")
    private String txurl;
    @Size(max = 80)
    @Column(name = "txrole")
    private String txrole;
    @Column(name = "insequencia")
    private Short insequencia;
    @OneToMany(mappedBy = "idfuncionalidade", fetch = FetchType.EAGER)
    private List<Tbacesso> tbacessoList;
    @JoinColumn(name = "idmodulo", referencedColumnName = "idmodulo")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbmodulo idmodulo;

    public Tbfuncionalidade() {
    }

    public Tbfuncionalidade(Integer idfuncionalidade) {
        this.idfuncionalidade = idfuncionalidade;
    }

    public Tbfuncionalidade(Integer idfuncionalidade, String nmfuncionalidade) {
        this.idfuncionalidade = idfuncionalidade;
        this.nmfuncionalidade = nmfuncionalidade;
    }

    public Integer getIdfuncionalidade() {
        return idfuncionalidade;
    }

    public void setIdfuncionalidade(Integer idfuncionalidade) {
        this.idfuncionalidade = idfuncionalidade;
    }

    public String getNmfuncionalidade() {
        return nmfuncionalidade;
    }

    public void setNmfuncionalidade(String nmfuncionalidade) {
        this.nmfuncionalidade = nmfuncionalidade;
    }

    public String getTxurl() {
        return txurl;
    }

    public void setTxurl(String txurl) {
        this.txurl = txurl;
    }

    public String getTxrole() {
        return txrole;
    }

    public void setTxrole(String txrole) {
        this.txrole = txrole;
    }

    public Short getInsequencia() {
        return insequencia;
    }

    public void setInsequencia(Short insequencia) {
        this.insequencia = insequencia;
    }

    @XmlTransient
    public List<Tbacesso> getTbacessoList() {
        return tbacessoList;
    }

    public void setTbacessoList(List<Tbacesso> tbacessoList) {
        this.tbacessoList = tbacessoList;
    }

    public Tbmodulo getIdmodulo() {
        return idmodulo;
    }

    public void setIdmodulo(Tbmodulo idmodulo) {
        this.idmodulo = idmodulo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfuncionalidade != null ? idfuncionalidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbfuncionalidade)) {
            return false;
        }
        Tbfuncionalidade other = (Tbfuncionalidade) object;
        if ((this.idfuncionalidade == null && other.idfuncionalidade != null) || (this.idfuncionalidade != null && !this.idfuncionalidade.equals(other.idfuncionalidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.siso.model.entities.Tbfuncionalidade[ idfuncionalidade=" + idfuncionalidade + " ]";
    }

    @Override
    public Integer getPK() {
        return getIdfuncionalidade();
    }

}
