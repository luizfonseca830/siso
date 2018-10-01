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
@Table(name = "tbmodulo")
public class Tbmodulo implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmodulo")
    private Integer idmodulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nmmodulo")
    private String nmmodulo;
    @Size(max = 255)
    @Column(name = "txdescmodulo")
    private String txdescmodulo;
    @Column(name = "intipo")
    private Integer intipo;
    @Size(max = 255)
    @Column(name = "nmmodulo_en_us")
    private String nmmoduloEnUs;
    @OneToMany(mappedBy = "idmodulo", fetch = FetchType.EAGER)
    private List<Tbmenu> tbmenuList;
    @OneToMany(mappedBy = "idmodulo", fetch = FetchType.EAGER)
    private List<Tbacesso> tbacessoList;
    @OneToMany(mappedBy = "idmodulo", fetch = FetchType.EAGER)
    private List<Tbfuncionalidade> tbfuncionalidadeList;

    public Tbmodulo() {
    }

    public Tbmodulo(Integer idmodulo) {
        this.idmodulo = idmodulo;
    }

    public Tbmodulo(Integer idmodulo, String nmmodulo) {
        this.idmodulo = idmodulo;
        this.nmmodulo = nmmodulo;
    }

    public Integer getIdmodulo() {
        return idmodulo;
    }

    public void setIdmodulo(Integer idmodulo) {
        this.idmodulo = idmodulo;
    }

    public String getNmmodulo() {
        return nmmodulo;
    }

    public void setNmmodulo(String nmmodulo) {
        this.nmmodulo = nmmodulo;
    }

    public String getTxdescmodulo() {
        return txdescmodulo;
    }

    public void setTxdescmodulo(String txdescmodulo) {
        this.txdescmodulo = txdescmodulo;
    }

    public Integer getIntipo() {
        return intipo;
    }

    public void setIntipo(Integer intipo) {
        this.intipo = intipo;
    }

    public String getNmmoduloEnUs() {
        return nmmoduloEnUs;
    }

    public void setNmmoduloEnUs(String nmmoduloEnUs) {
        this.nmmoduloEnUs = nmmoduloEnUs;
    }

    @XmlTransient
    public List<Tbmenu> getTbmenuList() {
        return tbmenuList;
    }

    public void setTbmenuList(List<Tbmenu> tbmenuList) {
        this.tbmenuList = tbmenuList;
    }

    @XmlTransient
    public List<Tbacesso> getTbacessoList() {
        return tbacessoList;
    }

    public void setTbacessoList(List<Tbacesso> tbacessoList) {
        this.tbacessoList = tbacessoList;
    }

    @XmlTransient
    public List<Tbfuncionalidade> getTbfuncionalidadeList() {
        return tbfuncionalidadeList;
    }

    public void setTbfuncionalidadeList(List<Tbfuncionalidade> tbfuncionalidadeList) {
        this.tbfuncionalidadeList = tbfuncionalidadeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmodulo != null ? idmodulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbmodulo)) {
            return false;
        }
        Tbmodulo other = (Tbmodulo) object;
        if ((this.idmodulo == null && other.idmodulo != null) || (this.idmodulo != null && !this.idmodulo.equals(other.idmodulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.siso.model.entities.Tbmodulo[ idmodulo=" + idmodulo + " ]";
    }

    @Override
    public Integer getPK() {
        return getIdmodulo();
    }

}
