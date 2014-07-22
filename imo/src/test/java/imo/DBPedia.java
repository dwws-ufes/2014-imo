package imo;

import java.io.IOException;
import java.net.Authenticator;
import java.net.URISyntaxException;
import java.util.Properties;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Literal;

public class DBPedia {

	public static void main(String[] args) throws IOException, URISyntaxException {
		
		final String userProxy = "thiago.rodrigues";
		final String passwordProxy = "$t1n8r97$";
		
		/*Authenticator.setDefault(new ProxyAuthenticator(userProxy, passwordProxy));
		System.setProperty("http.proxySet", "true");
		System.setProperty("http.proxyHost", "proxy.tre-es.gov.br");
		System.setProperty("http.proxyPort", "3128");
		System.setProperty("http.proxyUser", userProxy);
		System.setProperty("http.proxyPassword", passwordProxy);*/
		
		System.setProperty("java.net.useSystemProxies", "true");
		System.setProperty("socksProxyHost", "proxy.tre-es.gov.br");
		System.setProperty("socksProxyPort", "3128");
		/*System.setProperty("java.net.socks.username", userProxy);
		System.setProperty("java.net.socks.password", passwordProxy);*/
		
		Authenticator.setDefault(new ProxyAuthenticator(userProxy, passwordProxy));

		Properties prop = System.getProperties();
		
		for (Object key : prop.keySet())
			System.out.println(key + " : " + prop.getProperty((String) key));
		
		
		
		String query1 = " PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
		+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
		+ " PREFIX : <http://dbpedia.org/resource/>"
		+ " PREFIX d: <http://dbpedia.org/ontology/> "
		+ " SELECT distinct ?albumName ?artistName "
		
		+ " WHERE "
		+ " { "
		+ " ?album d:producer :Timbaland . "
		+ " ?album d:musicalArtist ?artist ."
		+ " ?album rdfs:label ?albumName . "
		+ " ?artist rdfs:label ?artistName ."
		+ " FILTER ( lang(?artistName) = \"en\")"
		+ " FILTER ( lang(?albumName) = \"en\" )" + " }";
	
		Query query = QueryFactory.create(query1);
		QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
		
		ResultSet results = queryExecution.execSelect();
		ResultSetFormatter.out(System.out, results);
		
		while (results.hasNext()) {
		
			QuerySolution soln = results.next();
			
			Literal albumName = soln.getLiteral("albumName");
			Literal artistName = soln.getLiteral("artistName");
			
			System.out.println(albumName+"--"+artistName);

		}

	}	
}
