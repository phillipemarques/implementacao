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
public class Quadra {

    /**
     * Lista todas as subclasses e individuos da entidade Quadras
     *
     * @param modelo
     * @param baseURI
     * @return
     * @throws IOException
     */
    public static String listaQuadrasW(OntModel modelo, String baseURI) throws IOException {
        OntClass individuo;
        individuo = modelo.getOntClass(baseURI + "Quadras");
        String listaQuadras = "";

        for (@SuppressWarnings("rawtypes") Iterator i = individuo.listSubClasses(); i.hasNext();) {
            OntClass Class = (OntClass) i.next();
            OntClass listaIndividuos;

            listaQuadras += "<BR>" + Class.getLocalName() + "<BR>\n";
            listaIndividuos = modelo.getOntClass(Class.getURI());

            //Listando as subclasses da classe Jogadores
            for (@SuppressWarnings("rawtypes") Iterator j = listaIndividuos.listInstances(); j.hasNext();) {
                Individual ind = (Individual) j.next();
                String nomeQuadra = ind.getLocalName();
                listaQuadras += "--><a href='http://localhost:8084/OntologiaTenisFinal/webresources/generic/Quadras/" + nomeQuadra +
               "'>"+nomeQuadra+"</a><BR>\n";
            }
        }
        return listaQuadras;
    }

    /**
     * Cria query para quadras
     *
     * @param nomeQuadra
     * @param objectPropertyName
     * @param model
     * @return
     */
    public static String criaQuery(String nomeQuadra, String objectPropertyName, OntModel model) {

        String ret = "";

        //Cria a consulta
        String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                + "PREFIX owl: <http://www.w3.org/2002/07/owl#> "
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> "
                + "PREFIX siop:<http://www.tenis.com/ontologies/tenis.owl#> "
                + "SELECT ?object WHERE { <http://www.tenis.com/ontologies/tenis.owl#" + nomeQuadra
                + "> siop:" + objectPropertyName + " ?object }";

        //Cria a query
        Query query = QueryFactory.create(queryString);

        //Executa a consulta e obtem os resultados
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results;
        results = qe.execSelect();

        ret += nomeQuadra + " " + objectPropertyName + " <BR>\n" + ResultSetFormatter.asText(results, query) + "<BR><BR>\n";

        return ret;
    }

}
