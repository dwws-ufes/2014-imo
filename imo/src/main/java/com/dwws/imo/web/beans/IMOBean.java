package com.dwws.imo.web.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import com.dwws.imo.modelo.Escolaridade;
import com.dwws.imo.service.IMOService;
import com.dwws.imo.web.qualifiers.SelectMenuItem;
import com.dwws.imo.web.qualifiers.SelectMenuItemType;

public class IMOBean {
	
	@EJB
	protected IMOService imoService;
	
	@Inject @SelectMenuItem(SelectMenuItemType.ESCOLARIDADE)
	private List<SelectItem> listaSelectEscolaridade;
	
	@Produces
	private List<SelectItem> gerarListaSelectEscolaridade() {
		this.listaSelectEscolaridade = new ArrayList<SelectItem>();
		for (Escolaridade escolaridade : imoService.obterEscolaridadesDisponiveis()) 
			this.listaSelectEscolaridade.add(new SelectItem(escolaridade.getCodigo(), escolaridade.getDescricao()));
		return this.listaSelectEscolaridade;
	}

	public List<SelectItem> getListaSelectEscolaridade() {
		return listaSelectEscolaridade;
	}
	
	protected void showDialogInfoMessage(String header, String text) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, header, text);
        RequestContext.getCurrentInstance().showMessageInDialog(message);
	}

	
}
