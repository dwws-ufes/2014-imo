package com.dwws.imo.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class LinkedDataFacade {
	
	@EJB
	private DBPediaService dbpediaService;
	
	public String buscarDescricaoProfissao(String profissao) {
		return this.dbpediaService.buscarDescricaoProfissao(profissao);
	}

}
