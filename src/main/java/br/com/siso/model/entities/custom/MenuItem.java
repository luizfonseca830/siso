/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.model.entities.custom;

import java.io.Serializable;

/**
 *
 * @author ioliveira
 */
public class MenuItem implements Serializable{

    private String strUrl;
    private String strDescription;

    /**
     * @return the strUrl
     */
    public String getStrUrl() {
        return strUrl;
    }

    /**
     * @param strUrl the strUrl to set
     */
    public void setStrUrl(String strUrl) {
        this.strUrl = strUrl;
    }

    /**
     * @return the strDescription
     */
    public String getStrDescription() {
        return strDescription;
    }

    /**
     * @param strDescription the strDescription to set
     */
    public void setStrDescription(String strDescription) {
        this.strDescription = strDescription;
    }
}
