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
@Table(name = "tbusuario")
public class Tbusuario implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusuario")
    private Integer idusuario;
    @Column(name = "inorigeminfo")
    private Short inorigeminfo;
    @Column(name = "inenviadaspo")
    private Short inenviadaspo;
    @Size(max = 20)
    @Column(name = "nmcracha")
    private String nmcracha;
    @Column(name = "tmdataultimoacesso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tmdataultimoacesso;
    @Column(name = "bolativo")
    private Boolean bolativo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "nmnomeusuario")
    private String nmnomeusuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "nmloginusuario")
    private String nmloginusuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "nmsenhausuario")
    private String nmsenhausuario;
    @Column(name = "tmdatadeexpiracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tmdatadeexpiracao;
    @JoinColumn(name = "idperfil", referencedColumnName = "idperfil")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbperfil idperfil;
    @OneToMany(mappedBy = "idusuario", fetch = FetchType.EAGER)
    private List<Tbacesso> tbacessoList;

    public Tbusuario() {
    }

    public Tbusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Tbusuario(Integer idusuario, String nmnomeusuario, String nmloginusuario, String nmsenhausuario) {
        this.idusuario = idusuario;
        this.nmnomeusuario = nmnomeusuario;
        this.nmloginusuario = nmloginusuario;
        this.nmsenhausuario = nmsenhausuario;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Short getInorigeminfo() {
        return inorigeminfo;
    }

    public void setInorigeminfo(Short inorigeminfo) {
        this.inorigeminfo = inorigeminfo;
    }

    public Short getInenviadaspo() {
        return inenviadaspo;
    }

    public void setInenviadaspo(Short inenviadaspo) {
        this.inenviadaspo = inenviadaspo;
    }

    public String getNmcracha() {
        return nmcracha;
    }

    public void setNmcracha(String nmcracha) {
        this.nmcracha = nmcracha;
    }

    public Date getTmdataultimoacesso() {
        return tmdataultimoacesso;
    }

    public void setTmdataultimoacesso(Date tmdataultimoacesso) {
        this.tmdataultimoacesso = tmdataultimoacesso;
    }

    public Boolean getBolativo() {
        return bolativo;
    }

    public void setBolativo(Boolean bolativo) {
        this.bolativo = bolativo;
    }

    public String getNmnomeusuario() {
        return nmnomeusuario;
    }

    public void setNmnomeusuario(String nmnomeusuario) {
        this.nmnomeusuario = nmnomeusuario;
    }

    public String getNmloginusuario() {
        return nmloginusuario;
    }

    public void setNmloginusuario(String nmloginusuario) {
        this.nmloginusuario = nmloginusuario;
    }

    public String getNmsenhausuario() {
        return nmsenhausuario;
    }

    public void setNmsenhausuario(String nmsenhausuario) {
        this.nmsenhausuario = nmsenhausuario;
    }

    public Tbperfil getIdperfil() {
        return idperfil;
    }

    public void setIdperfil(Tbperfil idperfil) {
        this.idperfil = idperfil;
    }

    @XmlTransient
    public List<Tbacesso> getTbacessoList() {
        return tbacessoList;
    }

    public void setTbacessoList(List<Tbacesso> tbacessoList) {
        this.tbacessoList = tbacessoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbusuario)) {
            return false;
        }
        Tbusuario other = (Tbusuario) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.siso.model.entities.Tbusuario[ idusuario=" + idusuario + " ]";
    }

    @Override
    public Integer getPK() {
        return idusuario;
    }

    /**
     * @return the tmdatadeexpiracao
     */
    public Date getTmdatadeexpiracao() {
        return tmdatadeexpiracao;
    }

    /**
     * @param tmdatadeexpiracao the tmdatadeexpiracao to set
     */
    public void setTmdatadeexpiracao(Date tmdatadeexpiracao) {
        this.tmdatadeexpiracao = tmdatadeexpiracao;
    }

}
