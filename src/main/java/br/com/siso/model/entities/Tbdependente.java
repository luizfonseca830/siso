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
@Table(name = "tbdependente")
public class Tbdependente implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddependente")
    private Integer iddependente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "nrcodcliente")
    private String nrcodcliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nmdependente")
    private String nmdependente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "inparentesco")
    private int inparentesco;
    @Column(name = "insexo")
    private Integer insexo;
    @Size(max = 11)
    @Column(name = "nrcpf")
    private String nrcpf;
    @Column(name = "dtnascimento")
    @Temporal(TemporalType.DATE)
    private Date dtnascimento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nmlogradouro")
    private String nmlogradouro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "nrnumero")
    private String nrnumero;
    @Size(max = 50)
    @Column(name = "nmcomplemento")
    private String nmcomplemento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "nrcep")
    private String nrcep;
    @Size(max = 100)
    @Column(name = "nmpontoreferencia")
    private String nmpontoreferencia;
    @Size(max = 20)
    @Column(name = "nrtelefone")
    private String nrtelefone;
    @Size(max = 20)
    @Column(name = "nrcelular")
    private String nrcelular;
    @Size(max = 50)
    @Column(name = "nmemail")
    private String nmemail;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "vlpeso")
    private Float vlpeso;
    @Column(name = "vlaltura")
    private Float vlaltura;
    @Column(name = "txglicose")
    private Integer txglicose;
    @Column(name = "vltemperatura")
    private Integer vltemperatura;
    @Size(max = 10)
    @Column(name = "nmtiposanguineo")
    private String nmtiposanguineo;
    @Size(max = 10)
    @Column(name = "nmpressaoarterial")
    private String nmpressaoarterial;
    @Size(max = 10)
    @Column(name = "nmpressaoarterialexcelente")
    private String nmpressaoarterialexcelente;
    @Column(name = "dtexclusao")
    @Temporal(TemporalType.DATE)
    private Date dtexclusao;
    @Column(name = "dtatualizacaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtatualizacaolog;
    @Column(name = "dtinclusaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtinclusaolog;
    @Size(max = 500)
    @Column(name = "observacao")
    private String observacao;
    @OneToMany(mappedBy = "iddependente", fetch = FetchType.EAGER)
    private List<Tbcartaoimpresso> tbcartaoimpressoList;
    @OneToMany(mappedBy = "iddependente", fetch = FetchType.EAGER)
    private List<Tbagendamento> tbagendamentoList;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbcliente idcliente;
    @JoinColumn(name = "idcidade", referencedColumnName = "idcidade")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbcidade idcidade;
    @JoinColumn(name = "idbairro", referencedColumnName = "idbairro")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbbairro idbairro;
    @OneToMany(mappedBy = "iddependente", fetch = FetchType.EAGER)
    private List<Tborcamento> tborcamentoList;

    public Tbdependente() {
    }

    public Tbdependente(Integer iddependente) {
        this.iddependente = iddependente;
    }

    public Tbdependente(Integer iddependente, String nrcodcliente, String nmdependente, int inparentesco, String nmlogradouro, String nrnumero, String nrcep) {
        this.iddependente = iddependente;
        this.nrcodcliente = nrcodcliente;
        this.nmdependente = nmdependente;
        this.inparentesco = inparentesco;
        this.nmlogradouro = nmlogradouro;
        this.nrnumero = nrnumero;
        this.nrcep = nrcep;
    }

    public Integer getIddependente() {
        return iddependente;
    }

    public void setIddependente(Integer iddependente) {
        this.iddependente = iddependente;
    }

    public String getNrcodcliente() {
        return nrcodcliente;
    }

    public void setNrcodcliente(String nrcodcliente) {
        this.nrcodcliente = nrcodcliente;
    }

    public String getNmdependente() {
        return nmdependente;
    }

    public void setNmdependente(String nmdependente) {
        this.nmdependente = nmdependente;
    }

    public int getInparentesco() {
        return inparentesco;
    }

    public void setInparentesco(int inparentesco) {
        this.inparentesco = inparentesco;
    }

    public Integer getInsexo() {
        return insexo;
    }

    public void setInsexo(Integer insexo) {
        this.insexo = insexo;
    }

    public String getNrcpf() {
        return nrcpf;
    }

    public void setNrcpf(String nrcpf) {
        this.nrcpf = nrcpf;
    }

    public Date getDtnascimento() {
        return dtnascimento;
    }

    public void setDtnascimento(Date dtnascimento) {
        this.dtnascimento = dtnascimento;
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @XmlTransient
    public List<Tbcartaoimpresso> getTbcartaoimpressoList() {
        return tbcartaoimpressoList;
    }

    public void setTbcartaoimpressoList(List<Tbcartaoimpresso> tbcartaoimpressoList) {
        this.tbcartaoimpressoList = tbcartaoimpressoList;
    }

    @XmlTransient
    public List<Tbagendamento> getTbagendamentoList() {
        return tbagendamentoList;
    }

    public void setTbagendamentoList(List<Tbagendamento> tbagendamentoList) {
        this.tbagendamentoList = tbagendamentoList;
    }

    public Tbcliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Tbcliente idcliente) {
        this.idcliente = idcliente;
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

    @XmlTransient
    public List<Tborcamento> getTborcamentoList() {
        return tborcamentoList;
    }

    public void setTborcamentoList(List<Tborcamento> tborcamentoList) {
        this.tborcamentoList = tborcamentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddependente != null ? iddependente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbdependente)) {
            return false;
        }
        Tbdependente other = (Tbdependente) object;
        if ((this.iddependente == null && other.iddependente != null) || (this.iddependente != null && !this.iddependente.equals(other.iddependente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.siso.model.entities.Tbdependente[ iddependente=" + iddependente + " ]";
    }

    @Override
    public Integer getPK() {
        return getIddependente();
    }

}
