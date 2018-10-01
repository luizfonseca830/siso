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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ioliveira
 */
@Entity
@Table(name = "tbempresacliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tbempresacliente.findAll", query = "SELECT t FROM Tbempresacliente t")})
public class Tbempresacliente implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idempresacliente")
    private Integer idempresacliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nmrazaosocial")
    private String nmrazaosocial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nmfantasia")
    private String nmfantasia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "nrcnpj")
    private String nrcnpj;
    @Size(max = 20)
    @Column(name = "nrinscestadual")
    private String nrinscestadual;
    @Size(max = 11)
    @Column(name = "nrinscmunicipal")
    private String nrinscmunicipal;
    @Size(max = 11)
    @Column(name = "nrinscsuframa")
    private String nrinscsuframa;
    @Size(max = 10)
    @Column(name = "nrtelefone1")
    private String nrtelefone1;
    @Size(max = 10)
    @Column(name = "nrtelefone2")
    private String nrtelefone2;
    @Size(max = 10)
    @Column(name = "nrtelefone3")
    private String nrtelefone3;
    @Size(max = 10)
    @Column(name = "nrfax")
    private String nrfax;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
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
    @Size(min = 1, max = 8)
    @Column(name = "nrcep")
    private String nrcep;
    @Size(max = 100)
    @Column(name = "nmpontoreferencia")
    private String nmpontoreferencia;
    @Size(max = 50)
    @Column(name = "nmsite")
    private String nmsite;
    @Size(max = 50)
    @Column(name = "nmemail")
    private String nmemail;
    @Column(name = "dtatualizacaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtatualizacaolog;
    @Column(name = "dtinclusaolog")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtinclusaolog;
    @Column(name = "diavencimento")
    private Short diavencimento;
    @Column(name = "dtexclusao")
    @Temporal(TemporalType.DATE)
    private Date dtexclusao;
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
    @Column(name = "diafechamento")
    private Integer diafechamento;
    @Column(name = "inacrescimovencimento")
    private Integer inacrescimovencimento;
    @Column(name = "intipopagamento")
    private Integer intipopagamento;
    @JoinColumn(name = "idcidade", referencedColumnName = "idcidade")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbcidade idcidade;
    @JoinColumn(name = "idbairro", referencedColumnName = "idbairro")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbbairro idbairro;

    public Tbempresacliente() {
    }

    public Tbempresacliente(Integer idempresacliente) {
        this.idempresacliente = idempresacliente;
    }

    public Tbempresacliente(Integer idempresacliente, String nmrazaosocial, String nmfantasia, String nrcnpj, String nmlogradouro, String nrnumero, String nrcep) {
        this.idempresacliente = idempresacliente;
        this.nmrazaosocial = nmrazaosocial;
        this.nmfantasia = nmfantasia;
        this.nrcnpj = nrcnpj;
        this.nmlogradouro = nmlogradouro;
        this.nrnumero = nrnumero;
        this.nrcep = nrcep;
    }

    public Integer getIdempresacliente() {
        return idempresacliente;
    }

    public void setIdempresacliente(Integer idempresacliente) {
        this.idempresacliente = idempresacliente;
    }

    public String getNmrazaosocial() {
        return nmrazaosocial;
    }

    public void setNmrazaosocial(String nmrazaosocial) {
        this.nmrazaosocial = nmrazaosocial;
    }

    public String getNmfantasia() {
        return nmfantasia;
    }

    public void setNmfantasia(String nmfantasia) {
        this.nmfantasia = nmfantasia;
    }

    public String getNrcnpj() {
        return nrcnpj;
    }

    public void setNrcnpj(String nrcnpj) {
        this.nrcnpj = nrcnpj;
    }

    public String getNrinscestadual() {
        return nrinscestadual;
    }

    public void setNrinscestadual(String nrinscestadual) {
        this.nrinscestadual = nrinscestadual;
    }

    public String getNrinscmunicipal() {
        return nrinscmunicipal;
    }

    public void setNrinscmunicipal(String nrinscmunicipal) {
        this.nrinscmunicipal = nrinscmunicipal;
    }

    public String getNrinscsuframa() {
        return nrinscsuframa;
    }

    public void setNrinscsuframa(String nrinscsuframa) {
        this.nrinscsuframa = nrinscsuframa;
    }

    public String getNrtelefone1() {
        return nrtelefone1;
    }

    public void setNrtelefone1(String nrtelefone1) {
        this.nrtelefone1 = nrtelefone1;
    }

    public String getNrtelefone2() {
        return nrtelefone2;
    }

    public void setNrtelefone2(String nrtelefone2) {
        this.nrtelefone2 = nrtelefone2;
    }

    public String getNrtelefone3() {
        return nrtelefone3;
    }

    public void setNrtelefone3(String nrtelefone3) {
        this.nrtelefone3 = nrtelefone3;
    }

    public String getNrfax() {
        return nrfax;
    }

    public void setNrfax(String nrfax) {
        this.nrfax = nrfax;
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

    public String getNmsite() {
        return nmsite;
    }

    public void setNmsite(String nmsite) {
        this.nmsite = nmsite;
    }

    public String getNmemail() {
        return nmemail;
    }

    public void setNmemail(String nmemail) {
        this.nmemail = nmemail;
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

    public Short getDiavencimento() {
        return diavencimento;
    }

    public void setDiavencimento(Short diavencimento) {
        this.diavencimento = diavencimento;
    }

    public Date getDtexclusao() {
        return dtexclusao;
    }

    public void setDtexclusao(Date dtexclusao) {
        this.dtexclusao = dtexclusao;
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

    public Integer getDiafechamento() {
        return diafechamento;
    }

    public void setDiafechamento(Integer diafechamento) {
        this.diafechamento = diafechamento;
    }

    public Integer getInacrescimovencimento() {
        return inacrescimovencimento;
    }

    public void setInacrescimovencimento(Integer inacrescimovencimento) {
        this.inacrescimovencimento = inacrescimovencimento;
    }

    public Integer getIntipopagamento() {
        return intipopagamento;
    }

    public void setIntipopagamento(Integer intipopagamento) {
        this.intipopagamento = intipopagamento;
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
        hash += (idempresacliente != null ? idempresacliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbempresacliente)) {
            return false;
        }
        Tbempresacliente other = (Tbempresacliente) object;
        if ((this.idempresacliente == null && other.idempresacliente != null) || (this.idempresacliente != null && !this.idempresacliente.equals(other.idempresacliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.siso.model.entities.Tbempresacliente[ idempresacliente=" + idempresacliente + " ]";
    }

    @Override
    public Integer getPK() {
        return getIdempresacliente();
    }

}
