/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.model.entities.custom;

import br.com.siso.model.entities.Tbprocedimento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author ioliveira
 */
public class FaceModel implements Serializable {

    private Integer idFaceModel;
    private Integer nrFace;
    private String urlImageFace;
    private String nmFace;
    private List<Tbprocedimento> listProcedimentosFace;
    private Set<Tbprocedimento> setProcedimentosFace;

    public FaceModel() {
        listProcedimentosFace = new ArrayList<>();
    }

    public FaceModel(Integer idFaceModel, Integer nrFace, String urlImageFace, String nmFace) {
        this.idFaceModel = idFaceModel;
        this.nrFace = nrFace;
        this.urlImageFace = urlImageFace;
        this.nmFace = nmFace;
        listProcedimentosFace = new LinkedList<>();
        setProcedimentosFace = new HashSet<>();
    }

    /**
     * @return the urlImageFace
     */
    public String getUrlImageFace() {
        return urlImageFace;
    }

    /**
     * @param urlImageFace the urlImageFace to set
     */
    public void setUrlImageFace(String urlImageFace) {
        this.urlImageFace = urlImageFace;
    }

    /**
     * @return the nrFace
     */
    public Integer getNrFace() {
        return nrFace;
    }

    /**
     * @param nrFace the nrFace to set
     */
    public void setNrFace(Integer nrFace) {
        this.nrFace = nrFace;
    }

    /**
     * @return the nmFace
     */
    public String getNmFace() {
        return nmFace;
    }

    /**
     * @param nmFace the nmFace to set
     */
    public void setNmFace(String nmFace) {
        this.nmFace = nmFace;
    }

    /**
     * @return the idFaceModel
     */
    public Integer getIdFaceModel() {
        return idFaceModel;
    }

    /**
     * @param idFaceModel the idFaceModel to set
     */
    public void setIdFaceModel(Integer idFaceModel) {
        this.idFaceModel = idFaceModel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFaceModel != null ? idFaceModel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FaceModel)) {
            return false;
        }
        final FaceModel other = (FaceModel) obj;
        if ((this.idFaceModel == null && other.idFaceModel != null) || (this.idFaceModel != null && !this.idFaceModel.equals(other.idFaceModel))) {
            return false;
        }
        return true;
    }

    /**
     * @return the listProcedimentosFace
     */
    public List<Tbprocedimento> getListProcedimentosFace() {
        return listProcedimentosFace;
    }

    /**
     * @param listProcedimentosFace the listProcedimentosFace to set
     */
    public void setListProcedimentosFace(List<Tbprocedimento> listProcedimentosFace) {
        this.listProcedimentosFace = listProcedimentosFace;
    }

    /**
     * @return the setProcedimentosFace
     */
    public Set<Tbprocedimento> getSetProcedimentosFace() {
        return setProcedimentosFace;
    }

    /**
     * @param setProcedimentosFace the setProcedimentosFace to set
     */
    public void setSetProcedimentosFace(Set<Tbprocedimento> setProcedimentosFace) {
        this.setProcedimentosFace = setProcedimentosFace;
    }
}
