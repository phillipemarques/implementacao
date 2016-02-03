/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semantico;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;
import tenis.Tenis;
import static tenis.Tenis.ehEntidadePrincipal;

/**
 * REST Web Service
 *
 * @author phillipe
 */
@Path("semantic")
public class SemanticResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SemanticResource
     */
    public SemanticResource() {
    }

    /**
     * Retrieves representation of an instance of semantico.SemanticResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    
        /**
     * Retrieves representation of an instance of helloWorld.HelloWorld
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/html")
    public String getHtml() throws IOException {
        String baseURI = "http://www.tenis.com/ontologies/service.owl";
        String arquivo = "/home/phillipe/Documentos/OntologiaTenis/service.owl";

        OntModel modelo = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);

        //usar FileManager para abrir o arquivo
        InputStream in = FileManager.get().open(arquivo);

        //ler o documento, propriamente dito
        modelo.read(new InputStreamReader(in), "");
        
        String html = "<html><body><h1>Ontologia de Tenis Individual - Assinatura Semantica</h1><BR>";
        
        List<String> classesNames = new ArrayList<>();
        List<String> propertiesNames = new ArrayList<>();
        //Objeto para ler as Classes 
        ExtendedIterator<OntClass> classes = modelo.listNamedClasses();

        while (classes.hasNext()) {
            //Acessa a primeira posição do objeto
            OntClass ont = classes.next();
            if (ont.isURIResource()) {
                classesNames.add(ont.getLocalName());
            }
        }
        
//        Individual ind = modelo.getIndividual(baseURI);
//        StmtIterator propriedades = ind.listProperties();
        
//        while(propriedades.hasNext())
//            propertiesNames.add(ind.listProperties().toString());
        
                //ordenando classes por ordem alfabetica
        Collections.sort(classesNames);

        String listaEntidades = "";

        //Adicionar nomes das classes na String
        for (String classesName : classesNames) {
            //listaEntidades += "<a href='http://localhost:8084/OntologiaTenisFinal/webresources/semantic/";
            listaEntidades += classesName;
            //listaEntidades += "'>" + classesName + "</a>";
            listaEntidades += "\n<BR>";
        }
        
//        for (String propertyName : propertiesNames){
//            listaEntidades += propertyName+"\n<BR>";
//        }

        html += listaEntidades+"</body></html>";
        return html;
        //return "<html><body><h1>Ontologia de Tenis Individual</h1></body></html>";
    }


    /**
     * PUT method for updating or creating an instance of SemanticResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
