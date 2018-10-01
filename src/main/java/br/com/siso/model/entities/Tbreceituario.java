/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.model.entities;

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
@Table(name = "tbreceituario")
public class Tbreceituario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idreceituario")
    private Integer idreceituario;
    @Basic(optional = false)
    @Column(name = "nmreceituario")
    private String nmreceituario;
    @Basic(optional = false)
    @Column(name = "dtdatareceituario")
    @Temporal(TemporalType.DATE)
    private Date dtdatareceituario;
    @Basic(optional = false)
    @Column(name = "tmdatainclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tmdatainclusao;
    @Basic(optional = false)
    @Column(name = "tmdataatualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tmdataatualizacao;
    @JoinColumn(name = "idusuariocadastro", referencedColumnName = "idusuario")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbusuario idusuariocadastro;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbcliente idcliente;
    @JoinColumn(name = "idatendimento", referencedColumnName = "idatendimento")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbatendimento idatendimento;

    public Tbreceituario() {
    }

    public Tbreceituario(Integer idreceituario) {
        this.idreceituario = idreceituario;
    }

    public Tbreceituario(Integer idreceituario, String nmreceituario, Date dtdatareceituario, Date tmdatainclusao, Date tmdataatualizacao) {
        this.idreceituario = idreceituario;
        this.nmreceituario = nmreceituario;
        this.dtdatareceituario = dtdatareceituario;
        this.tmdatainclusao = tmdatainclusao;
        this.tmdataatualizacao = tmdataatualizacao;
    }

    public Integer getIdreceituario() {
        return idreceituario;
    }

    public void setIdreceituario(Integer idreceituario) {
        this.idreceituario = idreceituario;
    }

    public String getNmreceituario() {
        return nmreceituario;
    }

    public void setNmreceituario(String nmreceituario) {
        this.nmreceituario = nmreceituario;
    }

    public Date getDtdatareceituario() {
        return dtdatareceituario;
    }

    public void setDtdatareceituario(Date dtdatareceituario) {
        this.dtdatareceituario = dtdatareceituario;
    }

    public Date getTmdatainclusao() {
        return tmdatainclusao;
    }

    public void setTmdatainclusao(Date tmdatainclusao) {
        this.tmdatainclusao = tmdatainclusao;
    }

    public Date getTmdataatualizacao() {
        return tmdataatualizacao;
    }

    public void setTmdataatualizacao(Date tmdataatualizacao) {
        this.tmdataatualizacao = tmdataatualizacao;
    }

    public Tbusuario getIdusuariocadastro() {
        return idusuariocadastro;
    }

    public void setIdusuariocadastro(Tbusuario idusuariocadastro) {
        this.idusuariocadastro = idusuariocadastro;
    }

    public Tbcliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Tbcliente idcliente) {
        this.idcliente = idcliente;
    }

    public Tbatendimento getIdatendimento() {
        return idatendimento;
    }

    public void setIdatendimento(Tbatendimento idatendimento) {
        this.idatendimento = idatendimento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idreceituario != null ? idreceituario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbreceituario)) {
            return false;
        }
        Tbreceituario other = (Tbreceituario) object;
        if ((this.idreceituario == null && other.idreceituario != null) || (this.idreceituario != null && !this.idreceituario.equals(other.idreceituario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.entities.Tbreceituario[ idreceituario=" + idreceituario + " ]";
    }

}
