/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.model.entities;

import br.com.siso.web.faces.converter.Identificador;
import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "tbservico")
public class Tbservico implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idservico")
    private Integer idservico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "nrcodservico")
    private String nrcodservico;
    @Basic(optional = false)
    @Size(min = 1, max = 500)
    @Column(name = "nmservico")
    private String nmservico;
    @Basic(optional = false)
    @Column(name = "intiposervico")
    private int intiposervico;
    @Basic(optional = false)
    @Column(name = "vlservico")
    private float vlservico;
    @Basic(optional = false)
    @Column(name = "vlservicoassociado")
    private float vlservicoassociado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "vlcusto")
    private Float vlcusto;
    @Column(name = "vlmargemassociado")
    private Float vlmargemassociado;
    @Column(name = "vlmargemnaoassociado")
    private Float vlmargemnaoassociado;
    @Basic(optional = false)
    @Column(name = "bovaloraberto")
    private boolean bovaloraberto;
    @Basic(optional = false)
    @Column(name = "bogratuito")
    private boolean bogratuito;
    @Basic(optional = false)
    @Column(name = "bobloqueio")
    private boolean bobloqueio;
    @Basic(optional = false)
    @Column(name = "dtinclusao")
    @Temporal(TemporalType.DATE)
    private Date dtinclusao;
    @Column(name = "dtexclusao")
    @Temporal(TemporalType.DATE)
    private Date dtexclusao;
    @Column(name = "dtatualizacaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtatualizacaolog;
    @Column(name = "dtinclusaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtinclusaolog;
    @Column(name = "dtcadastro")
    @Temporal(TemporalType.DATE)
    private Date dtcadastro;
    @Column(name = "dtalteracao")
    @Temporal(TemporalType.DATE)
    private Date dtalteracao;
    @Column(name = "idoperadorcadastro")
    private BigInteger idoperadorcadastro;
    @Column(name = "idoperadorexclusao")
    private BigInteger idoperadorexclusao;
    @Column(name = "idoperadoralteracao")
    private BigInteger idoperadoralteracao;
    @Column(name = "validade")
    private Integer validade;

    public Tbservico() {
    }

    public Tbservico(Integer idservico) {
        this.idservico = idservico;
    }

    public Tbservico(Integer idservico, String nrcodservico, String nmservico, int intiposervico, float vlservico, float vlservicoassociado, boolean bovaloraberto, boolean bogratuito, boolean bobloqueio, Date dtinclusao) {
        this.idservico = idservico;
        this.nrcodservico = nrcodservico;
        this.nmservico = nmservico;
        this.intiposervico = intiposervico;
        this.vlservico = vlservico;
        this.vlservicoassociado = vlservicoassociado;
        this.bovaloraberto = bovaloraberto;
        this.bogratuito = bogratuito;
        this.bobloqueio = bobloqueio;
        this.dtinclusao = dtinclusao;
    }

    public Integer getIdservico() {
        return idservico;
    }

    public void setIdservico(Integer idservico) {
        this.idservico = idservico;
    }

    public String getNrcodservico() {
        return nrcodservico;
    }

    public void setNrcodservico(String nrcodservico) {
        this.nrcodservico = nrcodservico;
    }

    public String getNmservico() {
        return nmservico;
    }

    public void setNmservico(String nmservico) {
        this.nmservico = nmservico;
    }

    public int getIntiposervico() {
        return intiposervico;
    }

    public void setIntiposervico(int intiposervico) {
        this.intiposervico = intiposervico;
    }

    public float getVlservico() {
        return vlservico;
    }

    public void setVlservico(float vlservico) {
        this.vlservico = vlservico;
    }

    public float getVlservicoassociado() {
        return vlservicoassociado;
    }

    public void setVlservicoassociado(float vlservicoassociado) {
        this.vlservicoassociado = vlservicoassociado;
    }

    public Float getVlcusto() {
        return vlcusto;
    }

    public void setVlcusto(Float vlcusto) {
        this.vlcusto = vlcusto;
    }

    public Float getVlmargemassociado() {
        return vlmargemassociado;
    }

    public void setVlmargemassociado(Float vlmargemassociado) {
        this.vlmargemassociado = vlmargemassociado;
    }

    public Float getVlmargemnaoassociado() {
        return vlmargemnaoassociado;
    }

    public void setVlmargemnaoassociado(Float vlmargemnaoassociado) {
        this.vlmargemnaoassociado = vlmargemnaoassociado;
    }

    public boolean getBovaloraberto() {
        return bovaloraberto;
    }

    public void setBovaloraberto(boolean bovaloraberto) {
        this.bovaloraberto = bovaloraberto;
    }

    public boolean getBogratuito() {
        return bogratuito;
    }

    public void setBogratuito(boolean bogratuito) {
        this.bogratuito = bogratuito;
    }

    public boolean getBobloqueio() {
        return bobloqueio;
    }

    public void setBobloqueio(boolean bobloqueio) {
        this.bobloqueio = bobloqueio;
    }

    public Date getDtinclusao() {
        return dtinclusao;
    }

    public void setDtinclusao(Date dtinclusao) {
        this.dtinclusao = dtinclusao;
    }

    public Date getDtexclusao() {
        return dtexclusao;
    }

    public void setDtexclusao(Date dtexclusao) {
        this.dtexclusao = dtexclusao;
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

    public Date getDtcadastro() {
        return dtcadastro;
    }

    public void setDtcadastro(Date dtcadastro) {
        this.dtcadastro = dtcadastro;
    }

    public Date getDtalteracao() {
        return dtalteracao;
    }

    public void setDtalteracao(Date dtalteracao) {
        this.dtalteracao = dtalteracao;
    }

    public BigInteger getIdoperadorcadastro() {
        return idoperadorcadastro;
    }

    public void setIdoperadorcadastro(BigInteger idoperadorcadastro) {
        this.idoperadorcadastro = idoperadorcadastro;
    }

    public BigInteger getIdoperadorexclusao() {
        return idoperadorexclusao;
    }

    public void setIdoperadorexclusao(BigInteger idoperadorexclusao) {
        this.idoperadorexclusao = idoperadorexclusao;
    }

    public BigInteger getIdoperadoralteracao() {
        return idoperadoralteracao;
    }

    public void setIdoperadoralteracao(BigInteger idoperadoralteracao) {
        this.idoperadoralteracao = idoperadoralteracao;
    }

    public Integer getValidade() {
        return validade;
    }

    public void setValidade(Integer validade) {
        this.validade = validade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idservico != null ? idservico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbservico)) {
            return false;
        }
        Tbservico other = (Tbservico) object;
        if ((this.idservico == null && other.idservico != null) || (this.idservico != null && !this.idservico.equals(other.idservico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.siso.model.entities.Tbservico[ idservico=" + idservico + " ]";
    }

    @Override
    public Integer getPK() {
        return getIdservico();
    }

}
