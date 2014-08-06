package com.dwws.imo.service;

import java.text.Normalizer;

import javax.ejb.Stateless;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

@Stateless
public class DBPediaService {

	private static final String ENDPOINT = "http://dbpedia.org/sparql";
	
	public String buscarDescricaoProfissao(String profissao) {
		
		profissao = profissao.replaceAll(" ", "_");
		profissao = Normalizer.normalize(profissao, Normalizer.Form.NFD);
		
		StringBuilder sparqlQueryBuilder = new StringBuilder();
		sparqlQueryBuilder.append("SELECT ?desc ")
			.append("WHERE { <http://dbpedia.org/resource/").append(profissao).append("> ")
			.append("<http://dbpedia.org/ontology/abstract> ?desc ")
			.append("FILTER (langMatches(lang(?desc), 'pt') || langMatches(lang(?desc), 'en')) ")
			.append("}");

		System.out.println("Query Sparql: " + sparqlQueryBuilder.toString());
		Query query = QueryFactory.create(sparqlQueryBuilder.toString());
		QueryExecution queryExecution = QueryExecutionFactory.sparqlService(ENDPOINT, query);
		
		ResultSet results = queryExecution.execSelect();
		String descricaoProfissao = "Nenhuma informação recuperada.";
		
		while (results.hasNext()) {
			QuerySolution soln = results.next();
			descricaoProfissao = soln.getLiteral("desc").toString();
		}
		
		return descricaoProfissao;
	}
}
