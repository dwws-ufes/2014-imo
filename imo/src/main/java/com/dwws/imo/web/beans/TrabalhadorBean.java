package com.dwws.imo.web.beans;

import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import com.dwws.imo.annotations.CDIBean;
import com.dwws.imo.exceptions.EntidadeNaoEncontradaException;
import com.dwws.imo.modelo.Estado;
import com.dwws.imo.modelo.Trabalhador;

@CDIBean
public class TrabalhadorBean extends IMOBean {
	
	@Inject
	private Trabalhador trabalhador;
	
	public void cadastrarTrabalhador() {
		this.imoService.cadastrarTrabalhador(trabalhador);
		showDialogInfoMessage("Resultado de Cadastro", "Cadastro de Trabalhador efetuado com sucesso!");
		this.trabalhador = new Trabalhador();
	}

	public void validarUsuarioSenha(FacesContext ctx, UIComponent component, Object value) {
		
		String usuario = (String) ((UIInput) component.findComponent("user_trab")).getLocalValue();
		String senha = (String) value;
		
		try {
			this.imoService.buscaTrabalhador(usuario, senha);
		} 
		catch (EntidadeNaoEncontradaException e) {
			FacesMessage msg = new FacesMessage("Trabalhador (usuário/senha) não encontrado no sistema.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
		}
	}
	
	public Trabalhador getTrabalhador() {
		return trabalhador;
	}

	public void setTrabalhador(Trabalhador trabalhador) {
		this.trabalhador = trabalhador;
	}

	public List<Estado> getListaEstados() {
		return Arrays.asList(Estado.values());
	}
	

}
