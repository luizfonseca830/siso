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
@Table(name = "tbprocedimento")
public class Tbprocedimento implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprocedimento")
    private Integer idprocedimento;
    @Basic(optional = false)
    @Column(name = "qtprocedimento")
    private int qtprocedimento;
    @Basic(optional = false)
    @Column(name = "infacedente")
    private int infacedente;
    @Basic(optional = false)
    @Column(name = "vlprocedimento")
    private float vlprocedimento;
    @Column(name = "insituacao")
    private Integer insituacao;
    @Column(name = "dtconclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtconclusao;
    @Column(name = "boautorizado")
    private Boolean boautorizado;
    @Column(name = "bocobertura")
    private Boolean bocobertura;
    @Column(name = "idsolicitante")
    private Integer idsolicitante;
    @Column(name = "idexecutor")
    private Integer idexecutor;
    @Column(name = "idprocedimentoaux")
    private Integer idprocedimentoaux;
    @Column(name = "idprocedimentoodonto")
    private Integer idprocedimentoodonto;
    @Column(name = "dtatualizacaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtatualizacaolog;
    @Column(name = "dtinclusaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtinclusaolog;
    @Column(name = "nmfuncionariosolicitante")
    private String nmfuncionariosolicitante;
    @Column(name = "nmfuncionarioexecutor")
    private String nmfuncionarioexecutor;
    @JoinColumn(name = "idtipostatusprocedimento", referencedColumnName = "idtipostatusprocedimento")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbtipostatusprocedimento idtipostatusprocedimento;
    @JoinColumn(name = "idservico", referencedColumnName = "idservico")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbservico idservico;
    @JoinColumn(name = "idorcamento", referencedColumnName = "idorcamento")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tborcamento idorcamento;
    @JoinColumn(name = "iddente", referencedColumnName = "iddente")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbdente iddente;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbcliente idcliente;

    public Tbprocedimento() {
    }

    public Tbprocedimento(Integer idprocedimento) {
        this.idprocedimento = idprocedimento;
    }

    public Tbprocedimento(Integer idprocedimento, int qtprocedimento, int infacedente, float vlprocedimento) {
        this.idprocedimento = idprocedimento;
        this.qtprocedimento = qtprocedimento;
        this.infacedente = infacedente;
        this.vlprocedimento = vlprocedimento;
    }

    public Integer getIdprocedimento() {
        return idprocedimento;
    }

    public void setIdprocedimento(Integer idprocedimento) {
        this.idprocedimento = idprocedimento;
    }

    public int getQtprocedimento() {
        return qtprocedimento;
    }

    public void setQtprocedimento(int qtprocedimento) {
        this.qtprocedimento = qtprocedimento;
    }

    public int getInfacedente() {
        return infacedente;
    }

    public void setInfacedente(int infacedente) {
        this.infacedente = infacedente;
    }

    public float getVlprocedimento() {
        return vlprocedimento;
    }

    public void setVlprocedimento(float vlprocedimento) {
        this.vlprocedimento = vlprocedimento;
    }

    public Integer getInsituacao() {
        return insituacao;
    }

    public void setInsituacao(Integer insituacao) {
        this.insituacao = insituacao;
    }

    public Date getDtconclusao() {
        return dtconclusao;
    }

    public void setDtconclusao(Date dtconclusao) {
        this.dtconclusao = dtconclusao;
    }

    public Boolean getBoautorizado() {
        return boautorizado;
    }

    public void setBoautorizado(Boolean boautorizado) {
        this.boautorizado = boautorizado;
    }

    public Boolean getBocobertura() {
        return bocobertura;
    }

    public void setBocobertura(Boolean bocobertura) {
        this.bocobertura = bocobertura;
    }

    public Integer getIdsolicitante() {
        return idsolicitante;
    }

    public void setIdsolicitante(Integer idsolicitante) {
        this.idsolicitante = idsolicitante;
    }

    public Integer getIdexecutor() {
        return idexecutor;
    }

    public void setIdexecutor(Integer idexecutor) {
        this.idexecutor = idexecutor;
    }

    public Integer getIdprocedimentoaux() {
        return idprocedimentoaux;
    }

    public void setIdprocedimentoaux(Integer idprocedimentoaux) {
        this.idprocedimentoaux = idprocedimentoaux;
    }

    public Integer getIdprocedimentoodonto() {
        return idprocedimentoodonto;
    }

    public void setIdprocedimentoodonto(Integer idprocedimentoodonto) {
        this.idprocedimentoodonto = idprocedimentoodonto;
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

    public String getNmfuncionariosolicitante() {
        return nmfuncionariosolicitante;
    }

    public void setNmfuncionariosolicitante(String nmfuncionariosolicitante) {
        this.nmfuncionariosolicitante = nmfuncionariosolicitante;
    }

    public String getNmfuncionarioexecutor() {
        return nmfuncionarioexecutor;
    }

    public void setNmfuncionarioexecutor(String nmfuncionarioexecutor) {
        this.nmfuncionarioexecutor = nmfuncionarioexecutor;
    }

    public Tbtipostatusprocedimento getIdtipostatusprocedimento() {
        return idtipostatusprocedimento;
    }

    public void setIdtipostatusprocedimento(Tbtipostatusprocedimento idtipostatusprocedimento) {
        this.idtipostatusprocedimento = idtipostatusprocedimento;
    }

    public Tbservico getIdservico() {
        return idservico;
    }

    public void setIdservico(Tbservico idservico) {
        this.idservico = idservico;
    }

    public Tborcamento getIdorcamento() {
        return idorcamento;
    }

    public void setIdorcamento(Tborcamento idorcamento) {
        this.idorcamento = idorcamento;
    }

    public Tbdente getIddente() {
        return iddente;
    }

    public void setIddente(Tbdente iddente) {
        this.iddente = iddente;
    }

    public Tbcliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Tbcliente idcliente) {
        this.idcliente = idcliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprocedimento != null ? idprocedimento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbprocedimento)) {
            return false;
        }
        Tbprocedimento other = (Tbprocedimento) object;
        if ((this.idprocedimento == null && other.idprocedimento != null) || (this.idprocedimento != null && !this.idprocedimento.equals(other.idprocedimento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.entities.Tbprocedimento[ idprocedimento=" + idprocedimento + " ]";
    }

    @Override
    public Integer getPK() {
        return idprocedimento;
    }

}
