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
@Table(name = "tbagendamento")
public class Tbagendamento implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idagendamento")
    private Integer idagendamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "nragendamento")
    private String nragendamento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "intipoconsulta")
    private int intipoconsulta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "intipoagendamento")
    private int intipoagendamento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "instatus")
    private int instatus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "intipo")
    private int intipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "inturno")
    private int inturno;
    @Column(name = "boretorno")
    private Boolean boretorno;
    @Column(name = "boremanejamento")
    private Boolean boremanejamento;
    @Column(name = "boreagendamento")
    private Boolean boreagendamento;
    @Column(name = "boretornogerado")
    private Boolean boretornogerado;
    @Column(name = "bodespesaexamegerada")
    private Boolean bodespesaexamegerada;
    @Column(name = "idoperadorcadastro")
    private Integer idoperadorcadastro;
    @Column(name = "idoperadoralteracao")
    private Integer idoperadoralteracao;
    @Column(name = "idoperadorexclusao")
    private Integer idoperadorexclusao;
    @Column(name = "bodespesagerada")
    private Boolean bodespesagerada;
    @Column(name = "dtatualizacaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtatualizacaolog;
    @Column(name = "dtinclusaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtinclusaolog;
    @Column(name = "dtatualizacao")
    @Temporal(TemporalType.DATE)
    private Date dtatualizacao;
    @Column(name = "dtexclusao")
    @Temporal(TemporalType.DATE)
    private Date dtexclusao;
    @Column(name = "bosolicitouretorno")
    private Boolean bosolicitouretorno;
    @Column(name = "boexames")
    private Boolean boexames;
    @Basic(optional = false)
    @NotNull
    @Column(name = "intipoatendimento")
    private int intipoatendimento;
    @Column(name = "boservicorealizado")
    private Boolean boservicorealizado;
    @JoinColumn(name = "idsubespecialidade", referencedColumnName = "idsubespecialidade")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbsubespecialidade idsubespecialidade;
    @JoinColumn(name = "idservico", referencedColumnName = "idservico")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbservico idservico;
    @JoinColumn(name = "idfuncionario", referencedColumnName = "idfuncionario")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbfuncionario idfuncionario;
    @JoinColumn(name = "idespecialidade", referencedColumnName = "idespecialidade")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbespecialidade idespecialidade;
    @JoinColumn(name = "iddependente", referencedColumnName = "iddependente")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbdependente iddependente;
    @JoinColumn(name = "idcontrato", referencedColumnName = "idcontrato")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbcontrato idcontrato;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbcliente idcliente;
    @JoinColumn(name = "idtipostatusagendamento", referencedColumnName = "idtipostatusagendamento")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbtipostatusagendamento idtipostatusagendamento;
    @NotNull
    @Column(name = "tmdataagendamento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tmdataagendamento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dtdataagendamento")
    @Temporal(TemporalType.DATE)
    private Date dtdataagendamento;

    public Tbagendamento() {
    }

    public Tbagendamento(Integer idagendamento) {
        this.idagendamento = idagendamento;
    }

    public Tbagendamento(Integer idagendamento, String nragendamento, int intipoconsulta, int intipoagendamento, int instatus, int intipo, int inturno, int intipoatendimento, Date dtdataagendamento,
            Date tmdataagendamento) {
        this.idagendamento = idagendamento;
        this.nragendamento = nragendamento;
        this.intipoconsulta = intipoconsulta;
        this.intipoagendamento = intipoagendamento;
        this.instatus = instatus;
        this.intipo = intipo;
        this.inturno = inturno;
        this.intipoatendimento = intipoatendimento;
        this.dtdataagendamento = dtdataagendamento;
        this.tmdataagendamento = tmdataagendamento;
    }

    public Integer getIdagendamento() {
        return idagendamento;
    }

    public void setIdagendamento(Integer idagendamento) {
        this.idagendamento = idagendamento;
    }

    public String getNragendamento() {
        return nragendamento;
    }

    public void setNragendamento(String nragendamento) {
        this.nragendamento = nragendamento;
    }

    public void setBoretorno(Boolean boretorno) {
        this.boretorno = boretorno;
    }

    public Boolean getBoremanejamento() {
        return boremanejamento;
    }

    public void setBoremanejamento(Boolean boremanejamento) {
        this.boremanejamento = boremanejamento;
    }

    public Boolean getBoreagendamento() {
        return boreagendamento;
    }

    public void setBoreagendamento(Boolean boreagendamento) {
        this.boreagendamento = boreagendamento;
    }

    public Boolean getBoretornogerado() {
        return boretornogerado;
    }

    public void setBoretornogerado(Boolean boretornogerado) {
        this.boretornogerado = boretornogerado;
    }

    public Boolean getBodespesaexamegerada() {
        return bodespesaexamegerada;
    }

    public void setBodespesaexamegerada(Boolean bodespesaexamegerada) {
        this.bodespesaexamegerada = bodespesaexamegerada;
    }

    public Integer getIdoperadorcadastro() {
        return idoperadorcadastro;
    }

    public void setIdoperadorcadastro(Integer idoperadorcadastro) {
        this.idoperadorcadastro = idoperadorcadastro;
    }

    public Integer getIdoperadoralteracao() {
        return idoperadoralteracao;
    }

    public void setIdoperadoralteracao(Integer idoperadoralteracao) {
        this.idoperadoralteracao = idoperadoralteracao;
    }

    public Integer getIdoperadorexclusao() {
        return idoperadorexclusao;
    }

    public void setIdoperadorexclusao(Integer idoperadorexclusao) {
        this.idoperadorexclusao = idoperadorexclusao;
    }

    public Boolean getBodespesagerada() {
        return bodespesagerada;
    }

    public void setBodespesagerada(Boolean bodespesagerada) {
        this.bodespesagerada = bodespesagerada;
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

    public Date getDtatualizacao() {
        return dtatualizacao;
    }

    public void setDtatualizacao(Date dtatualizacao) {
        this.dtatualizacao = dtatualizacao;
    }

    public Date getDtexclusao() {
        return dtexclusao;
    }

    public void setDtexclusao(Date dtexclusao) {
        this.dtexclusao = dtexclusao;
    }

    public Boolean getBosolicitouretorno() {
        return bosolicitouretorno;
    }

    public void setBosolicitouretorno(Boolean bosolicitouretorno) {
        this.bosolicitouretorno = bosolicitouretorno;
    }

    public Boolean getBoexames() {
        return boexames;
    }

    public void setBoexames(Boolean boexames) {
        this.boexames = boexames;
    }

    public Boolean getBoservicorealizado() {
        return boservicorealizado;
    }

    public void setBoservicorealizado(Boolean boservicorealizado) {
        this.boservicorealizado = boservicorealizado;
    }

    public Tbsubespecialidade getIdsubespecialidade() {
        return idsubespecialidade;
    }

    public void setIdsubespecialidade(Tbsubespecialidade idsubespecialidade) {
        this.idsubespecialidade = idsubespecialidade;
    }

    public Tbservico getIdservico() {
        return idservico;
    }

    public void setIdservico(Tbservico idservico) {
        this.idservico = idservico;
    }

    public Tbfuncionario getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(Tbfuncionario idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    public Tbespecialidade getIdespecialidade() {
        return idespecialidade;
    }

    public void setIdespecialidade(Tbespecialidade idespecialidade) {
        this.idespecialidade = idespecialidade;
    }

    public Tbdependente getIddependente() {
        return iddependente;
    }

    public void setIddependente(Tbdependente iddependente) {
        this.iddependente = iddependente;
    }

    public Tbcontrato getIdcontrato() {
        return idcontrato;
    }

    public void setIdcontrato(Tbcontrato idcontrato) {
        this.idcontrato = idcontrato;
    }

    public Tbcliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Tbcliente idcliente) {
        this.idcliente = idcliente;
    }

    /**
     * @return the intipoconsulta
     */
    public Integer getIntipoconsulta() {
        return intipoconsulta;
    }

    /**
     * @param intipoconsulta the intipoconsulta to set
     */
    public void setIntipoconsulta(Integer intipoconsulta) {
        this.intipoconsulta = intipoconsulta;
    }

    /**
     * @return the intipoagendamento
     */
    public Integer getIntipoagendamento() {
        return intipoagendamento;
    }

    /**
     * @param intipoagendamento the intipoagendamento to set
     */
    public void setIntipoagendamento(Integer intipoagendamento) {
        this.intipoagendamento = intipoagendamento;
    }

    /**
     * @return the instatus
     */
    public Integer getInstatus() {
        return instatus;
    }

    /**
     * @param instatus the instatus to set
     */
    public void setInstatus(Integer instatus) {
        this.instatus = instatus;
    }

    /**
     * @return the intipo
     */
    public Integer getIntipo() {
        return intipo;
    }

    /**
     * @param intipo the intipo to set
     */
    public void setIntipo(Integer intipo) {
        this.intipo = intipo;
    }

    /**
     * @return the inturno
     */
    public Integer getInturno() {
        return inturno;
    }

    /**
     * @param inturno the inturno to set
     */
    public void setInturno(Integer inturno) {
        this.inturno = inturno;
    }

    /**
     * @return the boretorno
     */
    public Boolean getBoretorno() {
        return boretorno;
    }

    /**
     * @return the intipoatendimento
     */
    public Integer getIntipoatendimento() {
        return intipoatendimento;
    }

    /**
     * @param intipoatendimento the intipoatendimento to set
     */
    public void setIntipoatendimento(Integer intipoatendimento) {
        this.intipoatendimento = intipoatendimento;
    }

    /**
     * @return the idtipostatusagendamento
     */
    public Tbtipostatusagendamento getIdtipostatusagendamento() {
        return idtipostatusagendamento;
    }

    /**
     * @param idtipostatusagendamento the idtipostatusagendamento to set
     */
    public void setIdtipostatusagendamento(Tbtipostatusagendamento idtipostatusagendamento) {
        this.idtipostatusagendamento = idtipostatusagendamento;
    }

    /**
     * @return the tmdataagendamento
     */
    public Date getTmdataagendamento() {
        return tmdataagendamento;
    }

    /**
     * @param tmdataagendamento the tmdataagendamento to set
     */
    public void setTmdataagendamento(Date tmdataagendamento) {
        this.tmdataagendamento = tmdataagendamento;
    }

    /**
     * @return the dtdataagendamento
     */
    public Date getDtdataagendamento() {
        return dtdataagendamento;
    }

    /**
     * @param dtdataagendamento the dtdataagendamento to set
     */
    public void setDtdataagendamento(Date dtdataagendamento) {
        this.dtdataagendamento = dtdataagendamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idagendamento != null ? idagendamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbagendamento)) {
            return false;
        }
        Tbagendamento other = (Tbagendamento) object;
        if ((this.idagendamento == null && other.idagendamento != null) || (this.idagendamento != null && !this.idagendamento.equals(other.idagendamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.siso.model.entities.Tbagendamento[ idagendamento=" + idagendamento + " ]";
    }

    @Override
    public Integer getPK() {
        return getIdagendamento();
    }

}
