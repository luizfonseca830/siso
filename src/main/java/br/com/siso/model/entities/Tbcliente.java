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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ioliveira
 */
@Entity
@Table(name = "tbcliente")
public class Tbcliente implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcliente")
    private Integer idcliente;
    @Basic(optional = false)
    @Column(name = "nrcodcliente")
    private String nrcodcliente;
    @Basic(optional = false)
    @Column(name = "nmcliente")
    private String nmcliente;
    @Basic(optional = false)
    @Column(name = "nrcpf")
    private String nrcpf;
    @Basic(optional = false)
    @Column(name = "nrrg")
    private String nrrg;
    @Column(name = "inestadocivil")
    private Integer inestadocivil;
    @Column(name = "insexo")
    private Integer insexo;
    @Column(name = "bobloqueado")
    private Boolean bobloqueado;
    @Column(name = "dtnascimento")
    @Temporal(TemporalType.DATE)
    private Date dtnascimento;
    @Column(name = "nmocupacao")
    private String nmocupacao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "vlcreditopessoaltotal")
    private Float vlcreditopessoaltotal;
    @Column(name = "vlsalario")
    private Float vlsalario;
    @Column(name = "nrpercentual")
    private Integer nrpercentual;
    @Column(name = "vllimite")
    private Float vllimite;
    @Basic(optional = false)
    @Column(name = "bonaoassociado")
    private boolean bonaoassociado;
    @Basic(optional = false)
    @Column(name = "nmlogradouro")
    private String nmlogradouro;
    @Basic(optional = false)
    @Column(name = "nrnumero")
    private String nrnumero;
    @Column(name = "nmcomplemento")
    private String nmcomplemento;
    @Basic(optional = false)
    @Column(name = "nrcep")
    private String nrcep;
    @Column(name = "nmpontoreferencia")
    private String nmpontoreferencia;
    @Column(name = "nrtelefone")
    private String nrtelefone;
    @Column(name = "nrcelular")
    private String nrcelular;
    @Column(name = "nmemail")
    private String nmemail;
    @Column(name = "dtinclusao")
    @Temporal(TemporalType.DATE)
    private Date dtinclusao;
    @Column(name = "dtalteracao")
    @Temporal(TemporalType.DATE)
    private Date dtalteracao;
    @Column(name = "dtexclusao")
    @Temporal(TemporalType.DATE)
    private Date dtexclusao;
    @Column(name = "boestrangeiro")
    private Boolean boestrangeiro;
    @Column(name = "vlpeso")
    private Float vlpeso;
    @Column(name = "vlaltura")
    private Float vlaltura;
    @Column(name = "txglicose")
    private Integer txglicose;
    @Column(name = "vltemperatura")
    private Integer vltemperatura;
    @Column(name = "nmtiposanguineo")
    private String nmtiposanguineo;
    @Column(name = "nmpressaoarterial")
    private String nmpressaoarterial;
    @Column(name = "nmpressaoarterialexcelente")
    private String nmpressaoarterialexcelente;
    @Column(name = "instatussiape")
    private Integer instatussiape;
    @Column(name = "instatusretorno")
    private Integer instatusretorno;
    @Column(name = "nmobservacaoaux")
    private String nmobservacaoaux;
    @Column(name = "instatusretornoodonto")
    private Integer instatusretornoodonto;
    @Column(name = "instatussiapeodonto")
    private Integer instatussiapeodonto;
    @Column(name = "idclienteodonto")
    private BigInteger idclienteodonto;
    @Column(name = "dtatualizacaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtatualizacaolog;
    @Column(name = "dtinclusaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtinclusaolog;
    @Column(name = "inarquivosiapegerado")
    private Short inarquivosiapegerado;
    @Column(name = "idoperadorcadastro")
    private BigInteger idoperadorcadastro;
    @Column(name = "idoperadoralteracao")
    private BigInteger idoperadoralteracao;
    @Column(name = "idoperadorexclusao")
    private BigInteger idoperadorexclusao;
    @Column(name = "observacao")
    private String observacao;
    @Lob
    @Column(name = "btfotocliente")
    private byte[] btfotocliente;
    @JoinColumn(name = "idcidade", referencedColumnName = "idcidade")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbcidade idcidade;
    @JoinColumn(name = "idbairro", referencedColumnName = "idbairro")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbbairro idbairro;
    @OneToMany(mappedBy = "idcliente", fetch = FetchType.EAGER)
    private List<Tbprocedimento> tbprocedimentoList;
    @OneToMany(mappedBy = "idcliente", fetch = FetchType.EAGER)
    private List<Tbdependente> tbdependenteList;
    @OneToMany(mappedBy = "idcliente", fetch = FetchType.EAGER)
    private List<Tbagendamento> tbagendamentoList;
    @OneToMany(mappedBy = "idcliente", fetch = FetchType.EAGER)
    private List<Tborcamento> tborcamentoList;

    public Tbcliente() {
    }

    public Tbcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Tbcliente(Integer idcliente, String nrcodcliente, String nmcliente, String nrcpf, String nrrg, boolean bonaoassociado, String nmlogradouro, String nrnumero, String nrcep) {
        this.idcliente = idcliente;
        this.nrcodcliente = nrcodcliente;
        this.nmcliente = nmcliente;
        this.nrcpf = nrcpf;
        this.nrrg = nrrg;
        this.bonaoassociado = bonaoassociado;
        this.nmlogradouro = nmlogradouro;
        this.nrnumero = nrnumero;
        this.nrcep = nrcep;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public String getNrcodcliente() {
        return nrcodcliente;
    }

    public void setNrcodcliente(String nrcodcliente) {
        this.nrcodcliente = nrcodcliente;
    }

    public String getNmcliente() {
        return nmcliente;
    }

    public void setNmcliente(String nmcliente) {
        this.nmcliente = nmcliente;
    }

    public String getNrcpf() {
        return nrcpf;
    }

    public void setNrcpf(String nrcpf) {
        this.nrcpf = nrcpf;
    }

    public String getNrrg() {
        return nrrg;
    }

    public void setNrrg(String nrrg) {
        this.nrrg = nrrg;
    }

    public Integer getInestadocivil() {
        return inestadocivil;
    }

    public void setInestadocivil(Integer inestadocivil) {
        this.inestadocivil = inestadocivil;
    }

    public Integer getInsexo() {
        return insexo;
    }

    public void setInsexo(Integer insexo) {
        this.insexo = insexo;
    }

    public Boolean getBobloqueado() {
        return bobloqueado;
    }

    public void setBobloqueado(Boolean bobloqueado) {
        this.bobloqueado = bobloqueado;
    }

    public Date getDtnascimento() {
        return dtnascimento;
    }

    public void setDtnascimento(Date dtnascimento) {
        this.dtnascimento = dtnascimento;
    }

    public String getNmocupacao() {
        return nmocupacao;
    }

    public void setNmocupacao(String nmocupacao) {
        this.nmocupacao = nmocupacao;
    }

    public Float getVlcreditopessoaltotal() {
        return vlcreditopessoaltotal;
    }

    public void setVlcreditopessoaltotal(Float vlcreditopessoaltotal) {
        this.vlcreditopessoaltotal = vlcreditopessoaltotal;
    }

    public Float getVlsalario() {
        return vlsalario;
    }

    public void setVlsalario(Float vlsalario) {
        this.vlsalario = vlsalario;
    }

    public Integer getNrpercentual() {
        return nrpercentual;
    }

    public void setNrpercentual(Integer nrpercentual) {
        this.nrpercentual = nrpercentual;
    }

    public Float getVllimite() {
        return vllimite;
    }

    public void setVllimite(Float vllimite) {
        this.vllimite = vllimite;
    }

    public boolean getBonaoassociado() {
        return bonaoassociado;
    }

    public void setBonaoassociado(boolean bonaoassociado) {
        this.bonaoassociado = bonaoassociado;
    }

    public String getNmlogradouro() {
        return nmlogradouro;
    }

    public void setNmlogradouro(String nmlogradouro) {
        this.nmlogradouro = nmlogradouro;
    }

    public String getNrnumero() {
        return nrnumero;
    }

    public void setNrnumero(String nrnumero) {
        this.nrnumero = nrnumero;
    }

    public String getNmcomplemento() {
        return nmcomplemento;
    }

    public void setNmcomplemento(String nmcomplemento) {
        this.nmcomplemento = nmcomplemento;
    }

    public String getNrcep() {
        return nrcep;
    }

    public void setNrcep(String nrcep) {
        this.nrcep = nrcep;
    }

    public String getNmpontoreferencia() {
        return nmpontoreferencia;
    }

    public void setNmpontoreferencia(String nmpontoreferencia) {
        this.nmpontoreferencia = nmpontoreferencia;
    }

    public String getNrtelefone() {
        return nrtelefone;
    }

    public void setNrtelefone(String nrtelefone) {
        this.nrtelefone = nrtelefone;
    }

    public String getNrcelular() {
        return nrcelular;
    }

    public void setNrcelular(String nrcelular) {
        this.nrcelular = nrcelular;
    }

    public String getNmemail() {
        return nmemail;
    }

    public void setNmemail(String nmemail) {
        this.nmemail = nmemail;
    }

    public Date getDtinclusao() {
        return dtinclusao;
    }

    public void setDtinclusao(Date dtinclusao) {
        this.dtinclusao = dtinclusao;
    }

    public Date getDtalteracao() {
        return dtalteracao;
    }

    public void setDtalteracao(Date dtalteracao) {
        this.dtalteracao = dtalteracao;
    }

    public Date getDtexclusao() {
        return dtexclusao;
    }

    public void setDtexclusao(Date dtexclusao) {
        this.dtexclusao = dtexclusao;
    }

    public Boolean getBoestrangeiro() {
        return boestrangeiro;
    }

    public void setBoestrangeiro(Boolean boestrangeiro) {
        this.boestrangeiro = boestrangeiro;
    }

    public Float getVlpeso() {
        return vlpeso;
    }

    public void setVlpeso(Float vlpeso) {
        this.vlpeso = vlpeso;
    }

    public Float getVlaltura() {
        return vlaltura;
    }

    public void setVlaltura(Float vlaltura) {
        this.vlaltura = vlaltura;
    }

    public Integer getTxglicose() {
        return txglicose;
    }

    public void setTxglicose(Integer txglicose) {
        this.txglicose = txglicose;
    }

    public Integer getVltemperatura() {
        return vltemperatura;
    }

    public void setVltemperatura(Integer vltemperatura) {
        this.vltemperatura = vltemperatura;
    }

    public String getNmtiposanguineo() {
        return nmtiposanguineo;
    }

    public void setNmtiposanguineo(String nmtiposanguineo) {
        this.nmtiposanguineo = nmtiposanguineo;
    }

    public String getNmpressaoarterial() {
        return nmpressaoarterial;
    }

    public void setNmpressaoarterial(String nmpressaoarterial) {
        this.nmpressaoarterial = nmpressaoarterial;
    }

    public String getNmpressaoarterialexcelente() {
        return nmpressaoarterialexcelente;
    }

    public void setNmpressaoarterialexcelente(String nmpressaoarterialexcelente) {
        this.nmpressaoarterialexcelente = nmpressaoarterialexcelente;
    }

    public Integer getInstatussiape() {
        return instatussiape;
    }

    public void setInstatussiape(Integer instatussiape) {
        this.instatussiape = instatussiape;
    }

    public Integer getInstatusretorno() {
        return instatusretorno;
    }

    public void setInstatusretorno(Integer instatusretorno) {
        this.instatusretorno = instatusretorno;
    }

    public String getNmobservacaoaux() {
        return nmobservacaoaux;
    }

    public void setNmobservacaoaux(String nmobservacaoaux) {
        this.nmobservacaoaux = nmobservacaoaux;
    }

    public Integer getInstatusretornoodonto() {
        return instatusretornoodonto;
    }

    public void setInstatusretornoodonto(Integer instatusretornoodonto) {
        this.instatusretornoodonto = instatusretornoodonto;
    }

    public Integer getInstatussiapeodonto() {
        return instatussiapeodonto;
    }

    public void setInstatussiapeodonto(Integer instatussiapeodonto) {
        this.instatussiapeodonto = instatussiapeodonto;
    }

    public BigInteger getIdclienteodonto() {
        return idclienteodonto;
    }

    public void setIdclienteodonto(BigInteger idclienteodonto) {
        this.idclienteodonto = idclienteodonto;
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

    public Short getInarquivosiapegerado() {
        return inarquivosiapegerado;
    }

    public void setInarquivosiapegerado(Short inarquivosiapegerado) {
        this.inarquivosiapegerado = inarquivosiapegerado;
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

    public BigInteger getIdoperadorexclusao() {
        return idoperadorexclusao;
    }

    public void setIdoperadorexclusao(BigInteger idoperadorexclusao) {
        this.idoperadorexclusao = idoperadorexclusao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Tbcidade getIdcidade() {
        return idcidade;
    }

    public void setIdcidade(Tbcidade idcidade) {
        this.idcidade = idcidade;
    }

    public Tbbairro getIdbairro() {
        return idbairro;
    }

    public void setIdbairro(Tbbairro idbairro) {
        this.idbairro = idbairro;
    }

    public byte[] getBtfotocliente() {
        return btfotocliente;
    }

    public void setBtfotocliente(byte[] btfotocliente) {
        this.btfotocliente = btfotocliente;
    }

    public List<Tbprocedimento> getTbprocedimentoList() {
        return tbprocedimentoList;
    }

    public void setTbprocedimentoList(List<Tbprocedimento> tbprocedimentoList) {
        this.tbprocedimentoList = tbprocedimentoList;
    }

    public List<Tbdependente> getTbdependenteList() {
        return tbdependenteList;
    }

    public void setTbdependenteList(List<Tbdependente> tbdependenteList) {
        this.tbdependenteList = tbdependenteList;
    }

    public List<Tbagendamento> getTbagendamentoList() {
        return tbagendamentoList;
    }

    public void setTbagendamentoList(List<Tbagendamento> tbagendamentoList) {
        this.tbagendamentoList = tbagendamentoList;
    }

    public List<Tborcamento> getTborcamentoList() {
        return tborcamentoList;
    }

    public void setTborcamentoList(List<Tborcamento> tborcamentoList) {
        this.tborcamentoList = tborcamentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcliente != null ? idcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbcliente)) {
            return false;
        }
        Tbcliente other = (Tbcliente) object;
        if ((this.idcliente == null && other.idcliente != null) || (this.idcliente != null && !this.idcliente.equals(other.idcliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.entities.Tbcliente[ idcliente=" + idcliente + " ]";
    }

    @Override
    public Integer getPK() {
        return idcliente;
    }

}
