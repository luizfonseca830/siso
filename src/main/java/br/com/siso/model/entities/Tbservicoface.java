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
@Table(name = "tbservicoface")
public class Tbservicoface implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idservicoface")
    private Integer idservicoface;
    @Basic(optional = false)
    @Column(name = "bovestibular")
    private boolean bovestibular;
    @Basic(optional = false)
    @Column(name = "booclusal")
    private boolean booclusal;
    @Basic(optional = false)
    @Column(name = "bopalatina")
    private boolean bopalatina;
    @Basic(optional = false)
    @Column(name = "bomesial")
    private boolean bomesial;
    @Basic(optional = false)
    @Column(name = "bodistal")
    private boolean bodistal;
    @Basic(optional = false)
    @Column(name = "bocervical")
    private boolean bocervical;
    @Basic(optional = false)
    @Column(name = "boraiz")
    private boolean boraiz;
    @Basic(optional = false)
    @Column(name = "bodente")
    private boolean bodente;
    @Basic(optional = false)
    @Column(name = "boarcada00")
    private boolean boarcada00;
    @Basic(optional = false)
    @Column(name = "boinferior00")
    private boolean boinferior00;
    @Basic(optional = false)
    @Column(name = "bosuperior00")
    private boolean bosuperior00;
    @Column(name = "dtatualizacaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtatualizacaolog;
    @Column(name = "dtinclusaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtinclusaolog;
    @Column(name = "boinferiordireito")
    private Boolean boinferiordireito;
    @Column(name = "boinferioresquerdo")
    private Boolean boinferioresquerdo;
    @Column(name = "bosuperiordireito")
    private Boolean bosuperiordireito;
    @Column(name = "bosuperioresquerdo")
    private Boolean bosuperioresquerdo;
    @JoinColumn(name = "idservico", referencedColumnName = "idservico")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbservico idservico;

    public Tbservicoface() {
    }

    public Tbservicoface(Integer idservicoface) {
        this.idservicoface = idservicoface;
    }

    public Tbservicoface(Integer idservicoface, boolean bovestibular, boolean booclusal, boolean bopalatina, boolean bomesial, boolean bodistal, boolean bocervical, boolean boraiz, boolean bodente, boolean boarcada00, boolean boinferior00, boolean bosuperior00) {
        this.idservicoface = idservicoface;
        this.bovestibular = bovestibular;
        this.booclusal = booclusal;
        this.bopalatina = bopalatina;
        this.bomesial = bomesial;
        this.bodistal = bodistal;
        this.bocervical = bocervical;
        this.boraiz = boraiz;
        this.bodente = bodente;
        this.boarcada00 = boarcada00;
        this.boinferior00 = boinferior00;
        this.bosuperior00 = bosuperior00;
    }

    public Integer getIdservicoface() {
        return idservicoface;
    }

    public void setIdservicoface(Integer idservicoface) {
        this.idservicoface = idservicoface;
    }

    public boolean getBovestibular() {
        return bovestibular;
    }

    public void setBovestibular(boolean bovestibular) {
        this.bovestibular = bovestibular;
    }

    public boolean getBooclusal() {
        return booclusal;
    }

    public void setBooclusal(boolean booclusal) {
        this.booclusal = booclusal;
    }

    public boolean getBopalatina() {
        return bopalatina;
    }

    public void setBopalatina(boolean bopalatina) {
        this.bopalatina = bopalatina;
    }

    public boolean getBomesial() {
        return bomesial;
    }

    public void setBomesial(boolean bomesial) {
        this.bomesial = bomesial;
    }

    public boolean getBodistal() {
        return bodistal;
    }

    public void setBodistal(boolean bodistal) {
        this.bodistal = bodistal;
    }

    public boolean getBocervical() {
        return bocervical;
    }

    public void setBocervical(boolean bocervical) {
        this.bocervical = bocervical;
    }

    public boolean getBoraiz() {
        return boraiz;
    }

    public void setBoraiz(boolean boraiz) {
        this.boraiz = boraiz;
    }

    public boolean getBodente() {
        return bodente;
    }

    public void setBodente(boolean bodente) {
        this.bodente = bodente;
    }

    public boolean getBoarcada00() {
        return boarcada00;
    }

    public void setBoarcada00(boolean boarcada00) {
        this.boarcada00 = boarcada00;
    }

    public boolean getBoinferior00() {
        return boinferior00;
    }

    public void setBoinferior00(boolean boinferior00) {
        this.boinferior00 = boinferior00;
    }

    public boolean getBosuperior00() {
        return bosuperior00;
    }

    public void setBosuperior00(boolean bosuperior00) {
        this.bosuperior00 = bosuperior00;
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

    public Boolean getBoinferiordireito() {
        return boinferiordireito;
    }

    public void setBoinferiordireito(Boolean boinferiordireito) {
        this.boinferiordireito = boinferiordireito;
    }

    public Boolean getBoinferioresquerdo() {
        return boinferioresquerdo;
    }

    public void setBoinferioresquerdo(Boolean boinferioresquerdo) {
        this.boinferioresquerdo = boinferioresquerdo;
    }

    public Boolean getBosuperiordireito() {
        return bosuperiordireito;
    }

    public void setBosuperiordireito(Boolean bosuperiordireito) {
        this.bosuperiordireito = bosuperiordireito;
    }

    public Boolean getBosuperioresquerdo() {
        return bosuperioresquerdo;
    }

    public void setBosuperioresquerdo(Boolean bosuperioresquerdo) {
        this.bosuperioresquerdo = bosuperioresquerdo;
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
        hash += (idservicoface != null ? idservicoface.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbservicoface)) {
            return false;
        }
        Tbservicoface other = (Tbservicoface) object;
        if ((this.idservicoface == null && other.idservicoface != null) || (this.idservicoface != null && !this.idservicoface.equals(other.idservicoface))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.entities.Tbservicoface[ idservicoface=" + idservicoface + " ]";
    }

    @Override
    public Integer getPK() {
        return idservicoface;
    }

}
