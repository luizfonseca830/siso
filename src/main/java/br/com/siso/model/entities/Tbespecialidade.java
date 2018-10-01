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
@Table(name = "tbespecialidade")
public class Tbespecialidade implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idespecialidade")
    private Integer idespecialidade;
    @Basic(optional = false)
    @NotNull
    @Column(name = "intipoespecialidade")
    private int intipoespecialidade;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nmespecialidade")
    private String nmespecialidade;
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
    @Column(name = "dtexclusao")
    @Temporal(TemporalType.DATE)
    private Date dtexclusao;
    @Column(name = "idoperadorcadastro")
    private BigInteger idoperadorcadastro;
    @Column(name = "idoperadorexclusao")
    private BigInteger idoperadorexclusao;
    @Column(name = "idoperadoralteracao")
    private BigInteger idoperadoralteracao;
    @OneToMany(mappedBy = "idespecialidade", fetch = FetchType.EAGER)
    private List<Tbsubespecialidade> tbsubespecialidadeList;
    @OneToMany(mappedBy = "idespecialidade", fetch = FetchType.EAGER)
    private List<Tbagendamento> tbagendamentoList;
    @OneToMany(mappedBy = "idespecialidade", fetch = FetchType.EAGER)
    private List<Tbespprofissional> tbespprofissionalList;

    public Tbespecialidade() {
    }

    public Tbespecialidade(Integer idespecialidade) {
        this.idespecialidade = idespecialidade;
    }

    public Tbespecialidade(Integer idespecialidade, int intipoespecialidade, String nmespecialidade) {
        this.idespecialidade = idespecialidade;
        this.intipoespecialidade = intipoespecialidade;
        this.nmespecialidade = nmespecialidade;
    }

    public Integer getIdespecialidade() {
        return idespecialidade;
    }

    public void setIdespecialidade(Integer idespecialidade) {
        this.idespecialidade = idespecialidade;
    }

    public int getIntipoespecialidade() {
        return intipoespecialidade;
    }

    public void setIntipoespecialidade(int intipoespecialidade) {
        this.intipoespecialidade = intipoespecialidade;
    }

    public String getNmespecialidade() {
        return nmespecialidade;
    }

    public void setNmespecialidade(String nmespecialidade) {
        this.nmespecialidade = nmespecialidade;
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

    public Date getDtexclusao() {
        return dtexclusao;
    }

    public void setDtexclusao(Date dtexclusao) {
        this.dtexclusao = dtexclusao;
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

    @XmlTransient
    public List<Tbsubespecialidade> getTbsubespecialidadeList() {
        return tbsubespecialidadeList;
    }

    public void setTbsubespecialidadeList(List<Tbsubespecialidade> tbsubespecialidadeList) {
        this.tbsubespecialidadeList = tbsubespecialidadeList;
    }

    @XmlTransient
    public List<Tbagendamento> getTbagendamentoList() {
        return tbagendamentoList;
    }

    public void setTbagendamentoList(List<Tbagendamento> tbagendamentoList) {
        this.tbagendamentoList = tbagendamentoList;
    }

    @XmlTransient
    public List<Tbespprofissional> getTbespprofissionalList() {
        return tbespprofissionalList;
    }

    public void setTbespprofissionalList(List<Tbespprofissional> tbespprofissionalList) {
        this.tbespprofissionalList = tbespprofissionalList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idespecialidade != null ? idespecialidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbespecialidade)) {
            return false;
        }
        Tbespecialidade other = (Tbespecialidade) object;
        if ((this.idespecialidade == null && other.idespecialidade != null) || (this.idespecialidade != null && !this.idespecialidade.equals(other.idespecialidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.siso.model.entities.Tbespecialidade[ idespecialidade=" + idespecialidade + " ]";
    }

    @Override
    public Integer getPK() {
        return getIdespecialidade();
    }

}
