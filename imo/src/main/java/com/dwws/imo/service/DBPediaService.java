package com.dwws.imo.service;

import javax.ejb.Stateless;

@Stateless
public class DBPediaService {

	private static final String PREFIXO = 
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " + 
			"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
			"PREFIX : <http://dbpedia.org/resource/> " +
			"PREFIX d: <http://dbpedia.org/ontology/> ";
	
	private static final String ENDPOINT = "http://dbpedia.org/sparql";
	
	/*
	PREFIX type: <http://dbpedia.org/class/yago/>
	PREFIX prop: <http://dbpedia.org/property/>
	SELECT str(?estados_nome)
	WHERE {
	?estado a type:StatesOfBrazil;
	rdfs:label ?estados_nome
	FILTER (langMatches(lang(?estados_nome),"pt"))
	}
	ORDER BY (?estados_nome)
	 */
	
	public String buscarDescricaoCargo(String nomeCargo) {
		return "Linked Data --- Descrição Cargo";
	}
}
