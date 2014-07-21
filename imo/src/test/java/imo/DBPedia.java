package imo;

import java.io.IOException;
import java.net.Authenticator;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.jena.atlas.web.auth.ApacheModAuthFormLogin;
import org.apache.jena.atlas.web.auth.FormLogin;
import org.apache.jena.atlas.web.auth.FormsAuthenticator;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.sparql.engine.http.QueryEngineHTTP;

public class DBPedia {

	public static void main(String[] args) throws IOException, URISyntaxException {
		
		final String userProxy = "thiago.rodrigues";
		final String passwordProxy = "$t1n8r97$";
		
		Authenticator.setDefault(new ProxyAuthenticator(userProxy, passwordProxy));
		System.setProperty("http.proxySet", "true");
		System.setProperty("http.proxyHost", "proxy.tre-es.gov.br");
		System.setProperty("http.proxyPort", "3128");
		System.setProperty("http.proxyUser", userProxy);
		System.setProperty("http.proxyPassword", passwordProxy);
		
		
/*		 final java.net.URL url = new java.net.URL( "http://www.google.com" );
	     final java.net.URLConnection uconn = url.openConnection( );
	     final java.net.HttpURLConnection conn = (java.net.HttpURLConnection)uconn;
	 
	        // Set up a request.
	        conn.setConnectTimeout( 10000 );    // 10 sec
	        conn.setReadTimeout( 10000 );       // 10 sec
	        conn.setInstanceFollowRedirects( true );
	        conn.setRequestProperty( "User-agent", "spider" );
	 
	        // Send the request.
	        conn.connect( );*/
		
		
		
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
	
		URI targetService = new URI("http://dbpedia.org/sparql");
		FormLogin formLogin = new ApacheModAuthFormLogin("proxy.tre-es.gov.br", userProxy, passwordProxy.toCharArray());
		FormsAuthenticator authenticator = new FormsAuthenticator(targetService, formLogin);
		
		Query query = QueryFactory.create(query1);
		QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query, authenticator);
		
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
