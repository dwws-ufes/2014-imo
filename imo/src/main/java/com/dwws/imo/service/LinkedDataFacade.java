package com.dwws.imo.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class LinkedDataFacade {
	
	@EJB
	private DBPediaService dbpediaService;
	
	public String buscarDescricaoCargo(String nomeCargo) {
		return this.dbpediaService.buscarDescricaoCargo(nomeCargo);
	}

}
