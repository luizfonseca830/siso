/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ioliveira
 */
@Entity
@Table(name = "tbtipostatusprocedimento")
public class Tbtipostatusprocedimento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipostatusprocedimento")
    private Integer idtipostatusprocedimento;
    @Basic(optional = false)
    @Column(name = "nmtipostatusprocedimento")
    private String nmtipostatusprocedimento;
    @Basic(optional = false)
    @Column(name = "intipostatusprocedimento")
    private Integer intipostatusprocedimento;

    public Tbtipostatusprocedimento() {
    }

    public Tbtipostatusprocedimento(Integer idtipostatusprocedimento) {
        this.idtipostatusprocedimento = idtipostatusprocedimento;
    }

    public Tbtipostatusprocedimento(Integer idtipostatusprocedimento, String nmtipostatusprocedimento, Integer intipostatusprocedimento) {
        this.idtipostatusprocedimento = idtipostatusprocedimento;
        this.nmtipostatusprocedimento = nmtipostatusprocedimento;
        this.intipostatusprocedimento = intipostatusprocedimento;
    }

    public Integer getIdtipostatusprocedimento() {
        return idtipostatusprocedimento;
    }

    public void setIdtipostatusprocedimento(Integer idtipostatusprocedimento) {
        this.idtipostatusprocedimento = idtipostatusprocedimento;
    }

    public String getNmtipostatusprocedimento() {
        return nmtipostatusprocedimento;
    }

    public void setNmtipostatusprocedimento(String nmtipostatusprocedimento) {
        this.nmtipostatusprocedimento = nmtipostatusprocedimento;
    }

    /**
     * @return the intipostatusprocedimento
     */
    public Integer getIntipostatusprocedimento() {
        return intipostatusprocedimento;
    }

    /**
     * @param intipostatusprocedimento the intipostatusprocedimento to set
     */
    public void setIntipostatusprocedimento(Integer intipostatusprocedimento) {
        this.intipostatusprocedimento = intipostatusprocedimento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipostatusprocedimento != null ? idtipostatusprocedimento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbtipostatusprocedimento)) {
            return false;
        }
        Tbtipostatusprocedimento other = (Tbtipostatusprocedimento) object;
        if ((this.idtipostatusprocedimento == null && other.idtipostatusprocedimento != null) || (this.idtipostatusprocedimento != null && !this.idtipostatusprocedimento.equals(other.idtipostatusprocedimento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.entities.Tbtipostatusprocedimento[ idtipostatusprocedimento=" + idtipostatusprocedimento + " ]";
    }
}
