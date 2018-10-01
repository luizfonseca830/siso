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
@Table(name = "tbbanco")
public class Tbbanco implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbanco")
    private Integer idbanco;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "cdbanco")
    private String cdbanco;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nmbanco")
    private String nmbanco;
    @Column(name = "dtatualizacaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtatualizacaolog;
    @Column(name = "dtinclusaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtinclusaolog;
    @OneToMany(mappedBy = "idbanco", fetch = FetchType.EAGER)
    private List<Tbcontrato> tbcontratoList;

    public Tbbanco() {
    }

    public Tbbanco(Integer idbanco) {
        this.idbanco = idbanco;
    }

    public Tbbanco(Integer idbanco, String cdbanco, String nmbanco) {
        this.idbanco = idbanco;
        this.cdbanco = cdbanco;
        this.nmbanco = nmbanco;
    }

    public Integer getIdbanco() {
        return idbanco;
    }

    public void setIdbanco(Integer idbanco) {
        this.idbanco = idbanco;
    }

    public String getCdbanco() {
        return cdbanco;
    }

    public void setCdbanco(String cdbanco) {
        this.cdbanco = cdbanco;
    }

    public String getNmbanco() {
        return nmbanco;
    }

    public void setNmbanco(String nmbanco) {
        this.nmbanco = nmbanco;
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
    public List<Tbcontrato> getTbcontratoList() {
        return tbcontratoList;
    }

    public void setTbcontratoList(List<Tbcontrato> tbcontratoList) {
        this.tbcontratoList = tbcontratoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbanco != null ? idbanco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbbanco)) {
            return false;
        }
        Tbbanco other = (Tbbanco) object;
        if ((this.idbanco == null && other.idbanco != null) || (this.idbanco != null && !this.idbanco.equals(other.idbanco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.siso.model.entities.Tbbanco[ idbanco=" + idbanco + " ]";
    }

    @Override
    public Integer getPK() {
        return getIdbanco();
    }

}
