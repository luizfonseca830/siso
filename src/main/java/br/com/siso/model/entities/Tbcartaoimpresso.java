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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ioliveira
 */
@Entity
@Table(name = "tbcartaoimpresso")
public class Tbcartaoimpresso implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcartaoimpresso")
    private Integer idcartaoimpresso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "instatus")
    private int instatus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "boimpresso")
    private boolean boimpresso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "boliberadoimpressao")
    private boolean boliberadoimpressao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dtadesao")
    @Temporal(TemporalType.DATE)
    private Date dtadesao;
    @Size(max = 20)
    @Column(name = "nrmatricula")
    private String nrmatricula;
    @Column(name = "dtimpressao")
    @Temporal(TemporalType.DATE)
    private Date dtimpressao;
    @Column(name = "idcliente")
    private Integer idcliente;
    @Column(name = "idempresacliente")
    private Integer idempresacliente;
    @Column(name = "idcontratocliente")
    private Integer idcontratocliente;
    @Column(name = "dtatualizacaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtatualizacaolog;
    @Column(name = "dtinclusaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtinclusaolog;
    @Column(name = "idfuncionario")
    private Integer idfuncionario;
    @Size(max = 100)
    @Column(name = "nmgrupo")
    private String nmgrupo;
    @JoinColumn(name = "iddependente", referencedColumnName = "iddependente")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbdependente iddependente;

    public Tbcartaoimpresso() {
    }

    public Tbcartaoimpresso(Integer idcartaoimpresso) {
        this.idcartaoimpresso = idcartaoimpresso;
    }

    public Tbcartaoimpresso(Integer idcartaoimpresso, int instatus, boolean boimpresso, boolean boliberadoimpressao, Date dtadesao) {
        this.idcartaoimpresso = idcartaoimpresso;
        this.instatus = instatus;
        this.boimpresso = boimpresso;
        this.boliberadoimpressao = boliberadoimpressao;
        this.dtadesao = dtadesao;
    }

    public Integer getIdcartaoimpresso() {
        return idcartaoimpresso;
    }

    public void setIdcartaoimpresso(Integer idcartaoimpresso) {
        this.idcartaoimpresso = idcartaoimpresso;
    }

    public int getInstatus() {
        return instatus;
    }

    public void setInstatus(int instatus) {
        this.instatus = instatus;
    }

    public boolean getBoimpresso() {
        return boimpresso;
    }

    public void setBoimpresso(boolean boimpresso) {
        this.boimpresso = boimpresso;
    }

    public boolean getBoliberadoimpressao() {
        return boliberadoimpressao;
    }

    public void setBoliberadoimpressao(boolean boliberadoimpressao) {
        this.boliberadoimpressao = boliberadoimpressao;
    }

    public Date getDtadesao() {
        return dtadesao;
    }

    public void setDtadesao(Date dtadesao) {
        this.dtadesao = dtadesao;
    }

    public String getNrmatricula() {
        return nrmatricula;
    }

    public void setNrmatricula(String nrmatricula) {
        this.nrmatricula = nrmatricula;
    }

    public Date getDtimpressao() {
        return dtimpressao;
    }

    public void setDtimpressao(Date dtimpressao) {
        this.dtimpressao = dtimpressao;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Integer getIdempresacliente() {
        return idempresacliente;
    }

    public void setIdempresacliente(Integer idempresacliente) {
        this.idempresacliente = idempresacliente;
    }

    public Integer getIdcontratocliente() {
        return idcontratocliente;
    }

    public void setIdcontratocliente(Integer idcontratocliente) {
        this.idcontratocliente = idcontratocliente;
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

    public Integer getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(Integer idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    public String getNmgrupo() {
        return nmgrupo;
    }

    public void setNmgrupo(String nmgrupo) {
        this.nmgrupo = nmgrupo;
    }

    public Tbdependente getIddependente() {
        return iddependente;
    }

    public void setIddependente(Tbdependente iddependente) {
        this.iddependente = iddependente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcartaoimpresso != null ? idcartaoimpresso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbcartaoimpresso)) {
            return false;
        }
        Tbcartaoimpresso other = (Tbcartaoimpresso) object;
        if ((this.idcartaoimpresso == null && other.idcartaoimpresso != null) || (this.idcartaoimpresso != null && !this.idcartaoimpresso.equals(other.idcartaoimpresso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.siso.model.entities.Tbcartaoimpresso[ idcartaoimpresso=" + idcartaoimpresso + " ]";
    }

    @Override
    public Integer getPK() {
        return getIdcartaoimpresso();
    }

}
