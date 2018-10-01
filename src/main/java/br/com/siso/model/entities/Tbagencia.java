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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "tbagencia")
public class Tbagencia implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idagencia")
    private Integer idagencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nragencia")
    private String nragencia;
    @Size(max = 255)
    @Column(name = "nrdigitoverificador")
    private String nrdigitoverificador;
    @Size(max = 255)
    @Column(name = "nmagencia")
    private String nmagencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idbanco")
    private int idbanco;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcidade")
    private int idcidade;
    @Column(name = "idagenciaaux")
    private Integer idagenciaaux;
    @Column(name = "idagenciaodonto")
    private Integer idagenciaodonto;
    @Column(name = "dtatualizacaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtatualizacaolog;
    @Column(name = "dtinclusaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtinclusaolog;

    public Tbagencia() {
    }

    public Tbagencia(Integer idagencia) {
        this.idagencia = idagencia;
    }

    public Tbagencia(Integer idagencia, String nragencia, int idbanco, int idcidade) {
        this.idagencia = idagencia;
        this.nragencia = nragencia;
        this.idbanco = idbanco;
        this.idcidade = idcidade;
    }

    public Integer getIdagencia() {
        return idagencia;
    }

    public void setIdagencia(Integer idagencia) {
        this.idagencia = idagencia;
    }

    public String getNragencia() {
        return nragencia;
    }

    public void setNragencia(String nragencia) {
        this.nragencia = nragencia;
    }

    public String getNrdigitoverificador() {
        return nrdigitoverificador;
    }

    public void setNrdigitoverificador(String nrdigitoverificador) {
        this.nrdigitoverificador = nrdigitoverificador;
    }

    public String getNmagencia() {
        return nmagencia;
    }

    public void setNmagencia(String nmagencia) {
        this.nmagencia = nmagencia;
    }

    public int getIdbanco() {
        return idbanco;
    }

    public void setIdbanco(int idbanco) {
        this.idbanco = idbanco;
    }

    public int getIdcidade() {
        return idcidade;
    }

    public void setIdcidade(int idcidade) {
        this.idcidade = idcidade;
    }

    public Integer getIdagenciaaux() {
        return idagenciaaux;
    }

    public void setIdagenciaaux(Integer idagenciaaux) {
        this.idagenciaaux = idagenciaaux;
    }

    public Integer getIdagenciaodonto() {
        return idagenciaodonto;
    }

    public void setIdagenciaodonto(Integer idagenciaodonto) {
        this.idagenciaodonto = idagenciaodonto;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idagencia != null ? idagencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbagencia)) {
            return false;
        }
        Tbagencia other = (Tbagencia) object;
        if ((this.idagencia == null && other.idagencia != null) || (this.idagencia != null && !this.idagencia.equals(other.idagencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.siso.model.entities.Tbagencia[ idagencia=" + idagencia + " ]";
    }

    @Override
    public Integer getPK() {
        return getIdagencia();
    }

}
