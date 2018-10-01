/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siso.model.entities.custom;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ioliveira
 */
public class MenuModel implements Serializable{
    
    private String strMenu;
    private List<MenuItem> listMenuItems;

    public MenuModel() {
        listMenuItems = new LinkedList<MenuItem>();
    }

    /**
     * @return the strMenu
     */
    public String getStrMenu() {
        return strMenu;
    }

    /**
     * @param strMenu the strMenu to set
     */
    public void setStrMenu(String strMenu) {
        this.strMenu = strMenu;
    }

    /**
     * @return the listMenuItems
     */
    public List<MenuItem> getListMenuItems() {      
        return listMenuItems;
    }

    /**
     * @param listMenuItems the listMenuItems to set
     */
    public void setListMenuItems(List<MenuItem> listMenuItems) {
        this.listMenuItems = listMenuItems;
    }
}
