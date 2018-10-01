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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ioliveira
 */
@Entity
@Table(name = "tbtipostatusagendamento")
public class Tbtipostatusagendamento implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipostatusagendamento")
    private Integer idtipostatusagendamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nmdesctipostatusagendamento")
    private String nmdesctipostatusagendamento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "intipostatusagendamento")
    private int intipostatusagendamento;

    public Tbtipostatusagendamento() {
    }

    public Tbtipostatusagendamento(Integer idtipostatusagendamento) {
        this.idtipostatusagendamento = idtipostatusagendamento;
    }

    public Tbtipostatusagendamento(Integer idtipostatusagendamento, String nmdesctipostatusagendamento, int intipostatusagendamento) {
        this.idtipostatusagendamento = idtipostatusagendamento;
        this.nmdesctipostatusagendamento = nmdesctipostatusagendamento;
        this.intipostatusagendamento = intipostatusagendamento;
    }

    public Integer getIdtipostatusagendamento() {
        return idtipostatusagendamento;
    }

    public void setIdtipostatusagendamento(Integer idtipostatusagendamento) {
        this.idtipostatusagendamento = idtipostatusagendamento;
    }

    public String getNmdesctipostatusagendamento() {
        return nmdesctipostatusagendamento;
    }

    public void setNmdesctipostatusagendamento(String nmdesctipostatusagendamento) {
        this.nmdesctipostatusagendamento = nmdesctipostatusagendamento;
    }

    public int getIntipostatusagendamento() {
        return intipostatusagendamento;
    }

    public void setIntipostatusagendamento(int intipostatusagendamento) {
        this.intipostatusagendamento = intipostatusagendamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipostatusagendamento != null ? idtipostatusagendamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbtipostatusagendamento)) {
            return false;
        }
        Tbtipostatusagendamento other = (Tbtipostatusagendamento) object;
        if ((this.idtipostatusagendamento == null && other.idtipostatusagendamento != null) || (this.idtipostatusagendamento != null && !this.idtipostatusagendamento.equals(other.idtipostatusagendamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.siso.model.entities.Tbtipostatusagendamento[ idtipostatusagendamento=" + idtipostatusagendamento + " ]";
    }

    @Override
    public Integer getPK() {
        return idtipostatusagendamento;
    }

}
