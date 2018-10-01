/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.siso.web.faces.converter;


import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author JorgeFonseca
 */
@FacesConverter("converterEntity")
public class ConverterEntity implements Converter{


    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Object objResult = null;
        if (value != null) {
            objResult = this.getAttributesFrom(component).get(value);
        }
        return objResult;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String strResult = null;
        if (value != null && !value.equals("") && value.toString()!=null && !value.toString().contains("null")) {            
            Identificador entity = (Identificador) value;

            this.addAttribute(component, entity);

            Integer codigo = (Integer) entity.getPK();
            if (codigo != null) {
                strResult = String.valueOf(codigo);
            }
        }
        return strResult;
    }

    protected void addAttribute(UIComponent component, Identificador o) {
        if (o.getPK() != null) {
            String key = o.getPK().toString();
            this.getAttributesFrom(component).put(key, o);
        }
    }

    protected Map<String, Object> getAttributesFrom(UIComponent component) {
        return component.getAttributes();
    }
}
