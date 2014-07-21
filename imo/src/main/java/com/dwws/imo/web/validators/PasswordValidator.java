package com.dwws.imo.web.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * http://balusc.blogspot.com.br/2007/12/validator-for-multiple-fields.html
 * @author thiago.rodrigues
 *
 */
public class PasswordValidator implements Validator {

	public void validate(FacesContext ctx, UIComponent component, Object value)
			throws ValidatorException {
		
		String password = (String) value;
		
		// Obtain the component and submitted value of the confirm password component.
        UIInput confirmComponent = (UIInput) component.getAttributes().get("confirm");
        String confirm = (String) confirmComponent.getSubmittedValue();

        // Check if they both are filled in.
        if (password == null || password.isEmpty() || confirm == null || confirm.isEmpty()) {
            return; // Let required="true" do its job.
        }

        // Compare the password with the confirm password.
        if (!password.equals(confirm)) {
            confirmComponent.setValid(false); // So that it's marked invalid.
            FacesMessage msg = new FacesMessage("Senhas informadas não são iguais.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
		
	}

}
