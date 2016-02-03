/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tenis;

import java.io.IOException;
import java.util.Iterator;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;

/**
 *
 * @author phillipe
 */
public class Golpe {
    
        public static String listaGolpesW(OntModel modelo, String baseURI) throws IOException {
        String listaGolpes = "";

            OntClass listaIndividuos = modelo.getOntClass(baseURI + "Golpes");

            //Listando as subclasses da classe Jogadores
            for (@SuppressWarnings("rawtypes") Iterator j = listaIndividuos.listInstances(); j.hasNext();) {
                //out.print("-->"+((Individual)j.next()).toString() + "<BR>");
                Individual ind = (Individual) j.next();
                String nomeGolpe = ind.getLocalName();
                listaGolpes += "--><a href='http://localhost:8084/OntologiaTenisFinal/webresources/generic/Golpes/" 
                + nomeGolpe + "'>"+nomeGolpe+"</a><BR>\n";
            }        
        return listaGolpes;
    }
        
        public static String criaQuery(String nomeGolpe, String objectPropertyName, OntModel model) {

        String ret = "";

        //Cria a consulta
        String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                + "PREFIX owl: <http://www.w3.org/2002/07/owl#> "
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> "
                + "PREFIX siop:<http://www.tenis.com/ontologies/tenis.owl#> "
                + "SELECT ?object WHERE { <http://www.tenis.com/ontologies/tenis.owl#" + nomeGolpe
                + "> siop:" + objectPropertyName + " ?object }";

        //Cria a query
        Query query = QueryFactory.create(queryString);

        //Executa a consulta e obtem os resultados
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results;
        results = qe.execSelect();

        ret += nomeGolpe + " " + objectPropertyName + " <BR>\n" + ResultSetFormatter.asText(results, query) + "<BR><BR>\n";

        return ret;
    }

    
}
