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
@Table(name = "tbfuncionario")
public class Tbfuncionario implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfuncionario")
    private Integer idfuncionario;
    @Column(name = "bodebitainss")
    private Boolean bodebitainss;
    @Basic(optional = false)
    @Column(name = "insexo")
    private int insexo;
    @Basic(optional = false)
    @Column(name = "dtcontrato")
    @Temporal(TemporalType.DATE)
    private Date dtcontrato;
    @Basic(optional = false)
    @Column(name = "dtnascimento")
    @Temporal(TemporalType.DATE)
    private Date dtnascimento;
    @Basic(optional = false)
    @Column(name = "inestadocivil")
    private int inestadocivil;
    @Basic(optional = false)
    @Column(name = "ingrupofuncionario")
    private int ingrupofuncionario;
    @Column(name = "nmcargo")
    private String nmcargo;
    @Column(name = "nmcomplemento")
    private String nmcomplemento;
    @Basic(optional = false)
    @Column(name = "nmfuncionario")
    private String nmfuncionario;
    @Basic(optional = false)
    @Column(name = "nmlogradouro")
    private String nmlogradouro;
    @Column(name = "nmpontoreferencia")
    private String nmpontoreferencia;
    @Column(name = "nrcelular")
    private String nrcelular;
    @Column(name = "nrcep")
    private String nrcep;
    @Basic(optional = false)
    @Column(name = "nrcpf")
    private String nrcpf;
    @Basic(optional = false)
    @Column(name = "nrmatricula")
    private String nrmatricula;
    @Basic(optional = false)
    @Column(name = "nrnumero")
    private String nrnumero;
    @Basic(optional = false)
    @Column(name = "nrrg")
    private String nrrg;
    @Column(name = "nrtelefone")
    private String nrtelefone;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "vlacrescimo")
    private Float vlacrescimo;
    @Column(name = "vlajudacusto")
    private Float vlajudacusto;
    @Column(name = "vlbasecalcfalta")
    private Float vlbasecalcfalta;
    @Column(name = "vlcargahoraria")
    private Float vlcargahoraria;
    @Column(name = "vlinss")
    private Float vlinss;
    @Column(name = "vlrefeicao")
    private Float vlrefeicao;
    @Basic(optional = false)
    @Column(name = "vlsalario")
    private float vlsalario;
    @Basic(optional = false)
    @Column(name = "vltransporte")
    private float vltransporte;
    @Column(name = "dtatualizacaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtatualizacaolog;
    @Column(name = "dtinclusaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtinclusaolog;
    @Column(name = "dtexclusao")
    @Temporal(TemporalType.DATE)
    private Date dtexclusao;
    @Column(name = "limitedesconto")
    private Float limitedesconto;
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
    @JoinColumn(name = "idcidade", referencedColumnName = "idcidade")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbcidade idcidade;
    @JoinColumn(name = "idbairro", referencedColumnName = "idbairro")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbbairro idbairro;

    public Tbfuncionario() {
    }

    public Tbfuncionario(Integer idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    public Tbfuncionario(Integer idfuncionario, int insexo, Date dtcontrato, Date dtnascimento, int inestadocivil, int ingrupofuncionario, String nmfuncionario, String nmlogradouro, String nrcpf, String nrmatricula, String nrnumero, String nrrg, float vlsalario, float vltransporte) {
        this.idfuncionario = idfuncionario;
        this.insexo = insexo;
        this.dtcontrato = dtcontrato;
        this.dtnascimento = dtnascimento;
        this.inestadocivil = inestadocivil;
        this.ingrupofuncionario = ingrupofuncionario;
        this.nmfuncionario = nmfuncionario;
        this.nmlogradouro = nmlogradouro;
        this.nrcpf = nrcpf;
        this.nrmatricula = nrmatricula;
        this.nrnumero = nrnumero;
        this.nrrg = nrrg;
        this.vlsalario = vlsalario;
        this.vltransporte = vltransporte;
    }

    public Integer getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(Integer idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    public Boolean getBodebitainss() {
        return bodebitainss;
    }

    public void setBodebitainss(Boolean bodebitainss) {
        this.bodebitainss = bodebitainss;
    }

    public int getInsexo() {
        return insexo;
    }

    public void setInsexo(int insexo) {
        this.insexo = insexo;
    }

    public Date getDtcontrato() {
        return dtcontrato;
    }

    public void setDtcontrato(Date dtcontrato) {
        this.dtcontrato = dtcontrato;
    }

    public Date getDtnascimento() {
        return dtnascimento;
    }

    public void setDtnascimento(Date dtnascimento) {
        this.dtnascimento = dtnascimento;
    }

    public int getInestadocivil() {
        return inestadocivil;
    }

    public void setInestadocivil(int inestadocivil) {
        this.inestadocivil = inestadocivil;
    }

    public int getIngrupofuncionario() {
        return ingrupofuncionario;
    }

    public void setIngrupofuncionario(int ingrupofuncionario) {
        this.ingrupofuncionario = ingrupofuncionario;
    }

    public String getNmcargo() {
        return nmcargo;
    }

    public void setNmcargo(String nmcargo) {
        this.nmcargo = nmcargo;
    }

    public String getNmcomplemento() {
        return nmcomplemento;
    }

    public void setNmcomplemento(String nmcomplemento) {
        this.nmcomplemento = nmcomplemento;
    }

    public String getNmfuncionario() {
        return nmfuncionario;
    }

    public void setNmfuncionario(String nmfuncionario) {
        this.nmfuncionario = nmfuncionario;
    }

    public String getNmlogradouro() {
        return nmlogradouro;
    }

    public void setNmlogradouro(String nmlogradouro) {
        this.nmlogradouro = nmlogradouro;
    }

    public String getNmpontoreferencia() {
        return nmpontoreferencia;
    }

    public void setNmpontoreferencia(String nmpontoreferencia) {
        this.nmpontoreferencia = nmpontoreferencia;
    }

    public String getNrcelular() {
        return nrcelular;
    }

    public void setNrcelular(String nrcelular) {
        this.nrcelular = nrcelular;
    }

    public String getNrcep() {
        return nrcep;
    }

    public void setNrcep(String nrcep) {
        this.nrcep = nrcep;
    }

    public String getNrcpf() {
        return nrcpf;
    }

    public void setNrcpf(String nrcpf) {
        this.nrcpf = nrcpf;
    }

    public String getNrmatricula() {
        return nrmatricula;
    }

    public void setNrmatricula(String nrmatricula) {
        this.nrmatricula = nrmatricula;
    }

    public String getNrnumero() {
        return nrnumero;
    }

    public void setNrnumero(String nrnumero) {
        this.nrnumero = nrnumero;
    }

    public String getNrrg() {
        return nrrg;
    }

    public void setNrrg(String nrrg) {
        this.nrrg = nrrg;
    }

    public String getNrtelefone() {
        return nrtelefone;
    }

    public void setNrtelefone(String nrtelefone) {
        this.nrtelefone = nrtelefone;
    }

    public Float getVlacrescimo() {
        return vlacrescimo;
    }

    public void setVlacrescimo(Float vlacrescimo) {
        this.vlacrescimo = vlacrescimo;
    }

    public Float getVlajudacusto() {
        return vlajudacusto;
    }

    public void setVlajudacusto(Float vlajudacusto) {
        this.vlajudacusto = vlajudacusto;
    }

    public Float getVlbasecalcfalta() {
        return vlbasecalcfalta;
    }

    public void setVlbasecalcfalta(Float vlbasecalcfalta) {
        this.vlbasecalcfalta = vlbasecalcfalta;
    }

    public Float getVlcargahoraria() {
        return vlcargahoraria;
    }

    public void setVlcargahoraria(Float vlcargahoraria) {
        this.vlcargahoraria = vlcargahoraria;
    }

    public Float getVlinss() {
        return vlinss;
    }

    public void setVlinss(Float vlinss) {
        this.vlinss = vlinss;
    }

    public Float getVlrefeicao() {
        return vlrefeicao;
    }

    public void setVlrefeicao(Float vlrefeicao) {
        this.vlrefeicao = vlrefeicao;
    }

    public float getVlsalario() {
        return vlsalario;
    }

    public void setVlsalario(float vlsalario) {
        this.vlsalario = vlsalario;
    }

    public float getVltransporte() {
        return vltransporte;
    }

    public void setVltransporte(float vltransporte) {
        this.vltransporte = vltransporte;
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

    public Date getDtexclusao() {
        return dtexclusao;
    }

    public void setDtexclusao(Date dtexclusao) {
        this.dtexclusao = dtexclusao;
    }

    public Float getLimitedesconto() {
        return limitedesconto;
    }

    public void setLimitedesconto(Float limitedesconto) {
        this.limitedesconto = limitedesconto;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfuncionario != null ? idfuncionario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbfuncionario)) {
            return false;
        }
        Tbfuncionario other = (Tbfuncionario) object;
        if ((this.idfuncionario == null && other.idfuncionario != null) || (this.idfuncionario != null && !this.idfuncionario.equals(other.idfuncionario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.siso.model.Tbfuncionario[ idfuncionario=" + idfuncionario + " ]";
    }

    @Override
    public Integer getPK() {
        return idfuncionario;
    }

}
