/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.model.entities;

import br.com.siso.web.faces.converter.Identificador;
import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ioliveira
 */
@Entity
@Table(name = "tbmenu")
public class Tbmenu implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmenu")
    private Integer idmenu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nmmenu")
    private String nmmenu;
    @Size(max = 2147483647)
    @Column(name = "txurl")
    private String txurl;
    @Size(max = 80)
    @Column(name = "txrole")
    private String txrole;
    @Column(name = "insequencia")
    private Short insequencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nmmenu_en_us")
    private String nmmenuEnUs;
    @JoinColumn(name = "idmodulo", referencedColumnName = "idmodulo")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbmodulo idmodulo;
    @OneToMany(mappedBy = "idmenu", fetch = FetchType.EAGER)
    private List<Tbacesso> tbacessoList;

    public Tbmenu() {
    }

    public Tbmenu(Integer idmenu) {
        this.idmenu = idmenu;
    }

    public Tbmenu(Integer idmenu, String nmmenu, String nmmenuEnUs) {
        this.idmenu = idmenu;
        this.nmmenu = nmmenu;
        this.nmmenuEnUs = nmmenuEnUs;
    }

    public Integer getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(Integer idmenu) {
        this.idmenu = idmenu;
    }

    public String getNmmenu() {
        return nmmenu;
    }

    public void setNmmenu(String nmmenu) {
        this.nmmenu = nmmenu;
    }

    public String getTxurl() {
        return txurl;
    }

    public void setTxurl(String txurl) {
        this.txurl = txurl;
    }

    public String getTxrole() {
        return txrole;
    }

    public void setTxrole(String txrole) {
        this.txrole = txrole;
    }

    public Short getInsequencia() {
        return insequencia;
    }

    public void setInsequencia(Short insequencia) {
        this.insequencia = insequencia;
    }

    public String getNmmenuEnUs() {
        return nmmenuEnUs;
    }

    public void setNmmenuEnUs(String nmmenuEnUs) {
        this.nmmenuEnUs = nmmenuEnUs;
    }

    public Tbmodulo getIdmodulo() {
        return idmodulo;
    }

    public void setIdmodulo(Tbmodulo idmodulo) {
        this.idmodulo = idmodulo;
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
        hash += (idmenu != null ? idmenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbmenu)) {
            return false;
        }
        Tbmenu other = (Tbmenu) object;
        if ((this.idmenu == null && other.idmenu != null) || (this.idmenu != null && !this.idmenu.equals(other.idmenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.siso.model.entities.Tbmenu[ idmenu=" + idmenu + " ]";
    }

    @Override
    public Integer getPK() {
        return idmenu;
    }

}
