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
@Table(name = "tbcontrato")
public class Tbcontrato implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcontrato")
    private Integer idcontrato;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nrcontrato")
    private String nrcontrato;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dtcontrato")
    @Temporal(TemporalType.DATE)
    private Date dtcontrato;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vlcontrato")
    private float vlcontrato;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "vldesconto")
    private Float vldesconto;
    @Column(name = "vltotal")
    private Float vltotal;
    @Column(name = "informapagamento")
    private Integer informapagamento;
    @Column(name = "bonaofazusoplano")
    private Boolean bonaofazusoplano;
    @Size(max = 15)
    @Column(name = "nrconta")
    private String nrconta;
    @Size(max = 2)
    @Column(name = "nrdigitoverificador")
    private String nrdigitoverificador;
    @Column(name = "bocadastradobancobrasil")
    private Boolean bocadastradobancobrasil;
    @Column(name = "dtterminovinculo")
    @Temporal(TemporalType.DATE)
    private Date dtterminovinculo;
    @Column(name = "idplano")
    private BigInteger idplano;
    @Column(name = "idempresagrupo")
    private BigInteger idempresagrupo;
    @Column(name = "insituacao")
    private Integer insituacao;
    @Column(name = "instatussiape")
    private Integer instatussiape;
    @Column(name = "bobloqueado")
    private Boolean bobloqueado;
    @Size(max = 20)
    @Column(name = "nrtalao")
    private String nrtalao;
    @Column(name = "dtvencimentoinicial")
    @Temporal(TemporalType.DATE)
    private Date dtvencimentoinicial;
    @Column(name = "vlmultaatraso")
    private Float vlmultaatraso;
    @Column(name = "vltaxajuros")
    private Float vltaxajuros;
    @Column(name = "inrenovado")
    private Integer inrenovado;
    @Column(name = "dtterminocontrato")
    @Temporal(TemporalType.DATE)
    private Date dtterminocontrato;
    @Column(name = "inmensalidade")
    private Integer inmensalidade;
    @Column(name = "dtatualizacaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtatualizacaolog;
    @Column(name = "dtinclusaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtinclusaolog;
    @Column(name = "inexcluidoporalteracao")
    private Boolean inexcluidoporalteracao;
    @Column(name = "dtalteracao")
    @Temporal(TemporalType.DATE)
    private Date dtalteracao;
    @Column(name = "boempresacliente")
    private Boolean boempresacliente;
    @Column(name = "boisento")
    private Boolean boisento;
    @Column(name = "dtinativacao")
    @Temporal(TemporalType.DATE)
    private Date dtinativacao;
    @Column(name = "nrparcelaconvenio")
    private Short nrparcelaconvenio;
    @Column(name = "idoperadorcadastro")
    private BigInteger idoperadorcadastro;
    @Column(name = "idoperadoralteracao")
    private BigInteger idoperadoralteracao;
    @Column(name = "idoperadorinativacao")
    private BigInteger idoperadorinativacao;
    @Column(name = "vlcontratoantigo")
    private Float vlcontratoantigo;
    @Column(name = "intipoinativacao")
    private Integer intipoinativacao;
    @Size(max = 500)
    @Column(name = "motivo")
    private String motivo;
    @OneToMany(mappedBy = "idcontrato", fetch = FetchType.EAGER)
    private List<Tbagendamento> tbagendamentoList;
    @JoinColumn(name = "idrazao", referencedColumnName = "idrazao")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbrazao idrazao;
    @JoinColumn(name = "idbanco", referencedColumnName = "idbanco")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbbanco idbanco;
    @JoinColumn(name = "idagencia", referencedColumnName = "idagencia")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbagencia idagencia;

    public Tbcontrato() {
    }

    public Tbcontrato(Integer idcontrato) {
        this.idcontrato = idcontrato;
    }

    public Tbcontrato(Integer idcontrato, String nrcontrato, Date dtcontrato, float vlcontrato) {
        this.idcontrato = idcontrato;
        this.nrcontrato = nrcontrato;
        this.dtcontrato = dtcontrato;
        this.vlcontrato = vlcontrato;
    }

    public Integer getIdcontrato() {
        return idcontrato;
    }

    public void setIdcontrato(Integer idcontrato) {
        this.idcontrato = idcontrato;
    }

    public String getNrcontrato() {
        return nrcontrato;
    }

    public void setNrcontrato(String nrcontrato) {
        this.nrcontrato = nrcontrato;
    }

    public Date getDtcontrato() {
        return dtcontrato;
    }

    public void setDtcontrato(Date dtcontrato) {
        this.dtcontrato = dtcontrato;
    }

    public float getVlcontrato() {
        return vlcontrato;
    }

    public void setVlcontrato(float vlcontrato) {
        this.vlcontrato = vlcontrato;
    }

    public Float getVldesconto() {
        return vldesconto;
    }

    public void setVldesconto(Float vldesconto) {
        this.vldesconto = vldesconto;
    }

    public Float getVltotal() {
        return vltotal;
    }

    public void setVltotal(Float vltotal) {
        this.vltotal = vltotal;
    }

    public Integer getInformapagamento() {
        return informapagamento;
    }

    public void setInformapagamento(Integer informapagamento) {
        this.informapagamento = informapagamento;
    }

    public Boolean getBonaofazusoplano() {
        return bonaofazusoplano;
    }

    public void setBonaofazusoplano(Boolean bonaofazusoplano) {
        this.bonaofazusoplano = bonaofazusoplano;
    }

    public String getNrconta() {
        return nrconta;
    }

    public void setNrconta(String nrconta) {
        this.nrconta = nrconta;
    }

    public String getNrdigitoverificador() {
        return nrdigitoverificador;
    }

    public void setNrdigitoverificador(String nrdigitoverificador) {
        this.nrdigitoverificador = nrdigitoverificador;
    }

    public Boolean getBocadastradobancobrasil() {
        return bocadastradobancobrasil;
    }

    public void setBocadastradobancobrasil(Boolean bocadastradobancobrasil) {
        this.bocadastradobancobrasil = bocadastradobancobrasil;
    }

    public Date getDtterminovinculo() {
        return dtterminovinculo;
    }

    public void setDtterminovinculo(Date dtterminovinculo) {
        this.dtterminovinculo = dtterminovinculo;
    }

    public BigInteger getIdplano() {
        return idplano;
    }

    public void setIdplano(BigInteger idplano) {
        this.idplano = idplano;
    }

    public BigInteger getIdempresagrupo() {
        return idempresagrupo;
    }

    public void setIdempresagrupo(BigInteger idempresagrupo) {
        this.idempresagrupo = idempresagrupo;
    }

    public Integer getInsituacao() {
        return insituacao;
    }

    public void setInsituacao(Integer insituacao) {
        this.insituacao = insituacao;
    }

    public Integer getInstatussiape() {
        return instatussiape;
    }

    public void setInstatussiape(Integer instatussiape) {
        this.instatussiape = instatussiape;
    }

    public Boolean getBobloqueado() {
        return bobloqueado;
    }

    public void setBobloqueado(Boolean bobloqueado) {
        this.bobloqueado = bobloqueado;
    }

    public String getNrtalao() {
        return nrtalao;
    }

    public void setNrtalao(String nrtalao) {
        this.nrtalao = nrtalao;
    }

    public Date getDtvencimentoinicial() {
        return dtvencimentoinicial;
    }

    public void setDtvencimentoinicial(Date dtvencimentoinicial) {
        this.dtvencimentoinicial = dtvencimentoinicial;
    }

    public Float getVlmultaatraso() {
        return vlmultaatraso;
    }

    public void setVlmultaatraso(Float vlmultaatraso) {
        this.vlmultaatraso = vlmultaatraso;
    }

    public Float getVltaxajuros() {
        return vltaxajuros;
    }

    public void setVltaxajuros(Float vltaxajuros) {
        this.vltaxajuros = vltaxajuros;
    }

    public Integer getInrenovado() {
        return inrenovado;
    }

    public void setInrenovado(Integer inrenovado) {
        this.inrenovado = inrenovado;
    }

    public Date getDtterminocontrato() {
        return dtterminocontrato;
    }

    public void setDtterminocontrato(Date dtterminocontrato) {
        this.dtterminocontrato = dtterminocontrato;
    }

    public Integer getInmensalidade() {
        return inmensalidade;
    }

    public void setInmensalidade(Integer inmensalidade) {
        this.inmensalidade = inmensalidade;
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

    public Boolean getInexcluidoporalteracao() {
        return inexcluidoporalteracao;
    }

    public void setInexcluidoporalteracao(Boolean inexcluidoporalteracao) {
        this.inexcluidoporalteracao = inexcluidoporalteracao;
    }

    public Date getDtalteracao() {
        return dtalteracao;
    }

    public void setDtalteracao(Date dtalteracao) {
        this.dtalteracao = dtalteracao;
    }

    public Boolean getBoempresacliente() {
        return boempresacliente;
    }

    public void setBoempresacliente(Boolean boempresacliente) {
        this.boempresacliente = boempresacliente;
    }

    public Boolean getBoisento() {
        return boisento;
    }

    public void setBoisento(Boolean boisento) {
        this.boisento = boisento;
    }

    public Date getDtinativacao() {
        return dtinativacao;
    }

    public void setDtinativacao(Date dtinativacao) {
        this.dtinativacao = dtinativacao;
    }

    public Short getNrparcelaconvenio() {
        return nrparcelaconvenio;
    }

    public void setNrparcelaconvenio(Short nrparcelaconvenio) {
        this.nrparcelaconvenio = nrparcelaconvenio;
    }

    public BigInteger getIdoperadorcadastro() {
        return idoperadorcadastro;
    }

    public void setIdoperadorcadastro(BigInteger idoperadorcadastro) {
        this.idoperadorcadastro = idoperadorcadastro;
    }

    public BigInteger getIdoperadoralteracao() {
        return idoperadoralteracao;
    }

    public void setIdoperadoralteracao(BigInteger idoperadoralteracao) {
        this.idoperadoralteracao = idoperadoralteracao;
    }

    public BigInteger getIdoperadorinativacao() {
        return idoperadorinativacao;
    }

    public void setIdoperadorinativacao(BigInteger idoperadorinativacao) {
        this.idoperadorinativacao = idoperadorinativacao;
    }

    public Float getVlcontratoantigo() {
        return vlcontratoantigo;
    }

    public void setVlcontratoantigo(Float vlcontratoantigo) {
        this.vlcontratoantigo = vlcontratoantigo;
    }

    public Integer getIntipoinativacao() {
        return intipoinativacao;
    }

    public void setIntipoinativacao(Integer intipoinativacao) {
        this.intipoinativacao = intipoinativacao;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @XmlTransient
    public List<Tbagendamento> getTbagendamentoList() {
        return tbagendamentoList;
    }

    public void setTbagendamentoList(List<Tbagendamento> tbagendamentoList) {
        this.tbagendamentoList = tbagendamentoList;
    }

    public Tbrazao getIdrazao() {
        return idrazao;
    }

    public void setIdrazao(Tbrazao idrazao) {
        this.idrazao = idrazao;
    }

    public Tbbanco getIdbanco() {
        return idbanco;
    }

    public void setIdbanco(Tbbanco idbanco) {
        this.idbanco = idbanco;
    }

    public Tbagencia getIdagencia() {
        return idagencia;
    }

    public void setIdagencia(Tbagencia idagencia) {
        this.idagencia = idagencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontrato != null ? idcontrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbcontrato)) {
            return false;
        }
        Tbcontrato other = (Tbcontrato) object;
        if ((this.idcontrato == null && other.idcontrato != null) || (this.idcontrato != null && !this.idcontrato.equals(other.idcontrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.siso.model.entities.Tbcontrato[ idcontrato=" + idcontrato + " ]";
    }

    @Override
    public Integer getPK() {
        return getIdcontrato();
    }

}
