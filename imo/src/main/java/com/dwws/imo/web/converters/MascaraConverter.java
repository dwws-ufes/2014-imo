package com.dwws.imo.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="maskConverter")
public class MascaraConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent comp, String valor) {
		
		if (valor != null && valor.length() > 0) {  
            valor = valor.toString().replaceAll("[- /.]", "");  
            valor = valor.toString().replaceAll("[-()]", "");  
        }  
		
        return valor; 
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent comp, Object valor) {
		return valor.toString();
	}

}
