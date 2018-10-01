/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.model.entities.custom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ioliveira
 */
public class DenteModel implements Serializable {

    private Integer idDenteModel;
    private Integer nrDente;
    private List<FaceModel> listFacesDente;

    public DenteModel() {
        listFacesDente = new ArrayList<>();
    }

    /**
     * @return the nrDente
     */
    public Integer getNrDente() {
        return nrDente;
    }

    /**
     * @param nrDente the nrDente to set
     */
    public void setNrDente(Integer nrDente) {
        this.nrDente = nrDente;
    }

    /**
     * @return the listFacesDente
     */
    public List<FaceModel> getListFacesDente() {
        return listFacesDente;
    }

    /**
     * @param listFacesDente the listFacesDente to set
     */
    public void setListFacesDente(List<FaceModel> listFacesDente) {
        this.listFacesDente = listFacesDente;
    }

    /**
     * @return the idDenteModel
     */
    public Integer getIdDenteModel() {
        return idDenteModel;
    }

    /**
     * @param idDenteModel the idDenteModel to set
     */
    public void setIdDenteModel(Integer idDenteModel) {
        this.idDenteModel = idDenteModel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDenteModel != null ? idDenteModel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DenteModel)) {
            return false;
        }
        final DenteModel other = (DenteModel) obj;
        if ((this.idDenteModel == null && other.idDenteModel != null) || (this.idDenteModel != null && !this.idDenteModel.equals(other.idDenteModel))) {
            return false;
        }
        return true;
    }
}
