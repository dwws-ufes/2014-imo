package com.dwws.imo.web.beans;

import javax.ejb.EJB;

import com.dwws.imo.annotations.CDIBean;
import com.dwws.imo.service.LinkedDataFacade;

@CDIBean
public class InformadorBean {

	@EJB
	private LinkedDataFacade linkedDataFacade;
	
	private String profissao;

	private String descricaoProfissao;
	
	
	public void buscarDescricaoProfissao() {
		this.descricaoProfissao = 
				this.linkedDataFacade.buscarDescricaoProfissao(this.profissao);
	}
	
	public void limparDescricao() {
		this.descricaoProfissao = null;
	}
	
	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getDescricaoProfissao() {
		return descricaoProfissao;
	}

	public void setDescricaoProfissao(String descricaoProfissao) {
		this.descricaoProfissao = descricaoProfissao;
	}
	
}
