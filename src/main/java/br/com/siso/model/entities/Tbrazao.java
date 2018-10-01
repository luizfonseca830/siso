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
@Table(name = "tbrazao")
public class Tbrazao implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrazao")
    private Integer idrazao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "nrrazao")
    private String nrrazao;
    @Column(name = "idbanco")
    private Integer idbanco;
    @Column(name = "dtatualizacaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtatualizacaolog;
    @Column(name = "dtinclusaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtinclusaolog;
    @OneToMany(mappedBy = "idrazao", fetch = FetchType.EAGER)
    private List<Tbcontrato> tbcontratoList;

    public Tbrazao() {
    }

    public Tbrazao(Integer idrazao) {
        this.idrazao = idrazao;
    }

    public Tbrazao(Integer idrazao, String nrrazao) {
        this.idrazao = idrazao;
        this.nrrazao = nrrazao;
    }

    public Integer getIdrazao() {
        return idrazao;
    }

    public void setIdrazao(Integer idrazao) {
        this.idrazao = idrazao;
    }

    public String getNrrazao() {
        return nrrazao;
    }

    public void setNrrazao(String nrrazao) {
        this.nrrazao = nrrazao;
    }

    public Integer getIdbanco() {
        return idbanco;
    }

    public void setIdbanco(Integer idbanco) {
        this.idbanco = idbanco;
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
        hash += (idrazao != null ? idrazao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbrazao)) {
            return false;
        }
        Tbrazao other = (Tbrazao) object;
        if ((this.idrazao == null && other.idrazao != null) || (this.idrazao != null && !this.idrazao.equals(other.idrazao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.siso.model.entities.Tbrazao[ idrazao=" + idrazao + " ]";
    }

    @Override
    public Integer getPK() {
        return getIdrazao();
    }

}
