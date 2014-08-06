package com.dwws.imo.web.validators;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.dwws.imo.annotations.CDIBean;
import com.dwws.imo.service.ValidatorService;

@CDIBean
public class UsuarioTrabalhadorValidator implements Validator {
	
	@EJB
	private ValidatorService validatorService;
	
	@Override
	public void validate(FacesContext ctx, UIComponent component, Object value)
			throws ValidatorException {
		
		String usuario = (String) value;
		
		if (this.validatorService.buscarTrabalhadorPorUsuario(usuario) != null) {
			((UIInput) component).setValid(false);
			FacesMessage msg = new FacesMessage("Usuário já utilizado no sistema.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
		}
	}
}
