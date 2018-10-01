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
@Table(name = "tbtipostatusorcamento")
public class Tbtipostatusorcamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipostatusorcamento")
    private Integer idtipostatusorcamento;
    @Basic(optional = false)
    @Column(name = "nmtipostatusorcamento")
    private String nmtipostatusorcamento;
    @Basic(optional = false)
    @Column(name = "intipostatusorcamento")
    private int intipostatusorcamento;

    public Tbtipostatusorcamento() {
    }

    public Tbtipostatusorcamento(Integer idtipostatusorcamento) {
        this.idtipostatusorcamento = idtipostatusorcamento;
    }

    public Tbtipostatusorcamento(Integer idtipostatusorcamento, String nmtipostatusorcamento, int intipostatusorcamento) {
        this.idtipostatusorcamento = idtipostatusorcamento;
        this.nmtipostatusorcamento = nmtipostatusorcamento;
        this.intipostatusorcamento = intipostatusorcamento;
    }

    public Integer getIdtipostatusorcamento() {
        return idtipostatusorcamento;
    }

    public void setIdtipostatusorcamento(Integer idtipostatusorcamento) {
        this.idtipostatusorcamento = idtipostatusorcamento;
    }

    public String getNmtipostatusorcamento() {
        return nmtipostatusorcamento;
    }

    public void setNmtipostatusorcamento(String nmtipostatusorcamento) {
        this.nmtipostatusorcamento = nmtipostatusorcamento;
    }

    public int getIntipostatusorcamento() {
        return intipostatusorcamento;
    }

    public void setIntipostatusorcamento(int intipostatusorcamento) {
        this.intipostatusorcamento = intipostatusorcamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipostatusorcamento != null ? idtipostatusorcamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbtipostatusorcamento)) {
            return false;
        }
        Tbtipostatusorcamento other = (Tbtipostatusorcamento) object;
        if ((this.idtipostatusorcamento == null && other.idtipostatusorcamento != null) || (this.idtipostatusorcamento != null && !this.idtipostatusorcamento.equals(other.idtipostatusorcamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.entities.Tbtipostatusorcamento[ idtipostatusorcamento=" + idtipostatusorcamento + " ]";
    }

}
