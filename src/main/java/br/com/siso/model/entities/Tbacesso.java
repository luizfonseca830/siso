/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.model.entities;

import br.com.siso.web.faces.converter.Identificador;
import java.io.Serializable;
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

/**
 *
 * @author ioliveira
 */
@Entity
@Table(name = "tbacesso")
public class Tbacesso implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idacesso")
    private Integer idacesso;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbusuario idusuario;
    @JoinColumn(name = "idmodulo", referencedColumnName = "idmodulo")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbmodulo idmodulo;
    @JoinColumn(name = "idmenu", referencedColumnName = "idmenu")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbmenu idmenu;
    @JoinColumn(name = "idfuncionalidade", referencedColumnName = "idfuncionalidade")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbfuncionalidade idfuncionalidade;

    public Tbacesso() {
    }

    public Tbacesso(Integer idacesso) {
        this.idacesso = idacesso;
    }

    public Integer getIdacesso() {
        return idacesso;
    }

    public void setIdacesso(Integer idacesso) {
        this.idacesso = idacesso;
    }

    public Tbusuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Tbusuario idusuario) {
        this.idusuario = idusuario;
    }

    public Tbmodulo getIdmodulo() {
        return idmodulo;
    }

    public void setIdmodulo(Tbmodulo idmodulo) {
        this.idmodulo = idmodulo;
    }

    public Tbmenu getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(Tbmenu idmenu) {
        this.idmenu = idmenu;
    }

    public Tbfuncionalidade getIdfuncionalidade() {
        return idfuncionalidade;
    }

    public void setIdfuncionalidade(Tbfuncionalidade idfuncionalidade) {
        this.idfuncionalidade = idfuncionalidade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idacesso != null ? idacesso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbacesso)) {
            return false;
        }
        Tbacesso other = (Tbacesso) object;
        if ((this.idacesso == null && other.idacesso != null) || (this.idacesso != null && !this.idacesso.equals(other.idacesso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.siso.model.entities.Tbacesso[ idacesso=" + idacesso + " ]";
    }

    @Override
    public Integer getPK() {
        return getIdacesso();
    }

}
