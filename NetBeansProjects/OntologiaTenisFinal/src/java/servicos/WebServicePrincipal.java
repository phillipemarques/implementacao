/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.util.FileManager;
import tenis.Equipamento;
import tenis.Golpe;
import tenis.Jogador;
import tenis.Pais;
import tenis.Quadra;
import tenis.Tenis;
import tenis.Torneio;

/**
 * REST Web Service
 *
 * @author phillipe
 */
@Path("generic")
public class WebServicePrincipal {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public WebServicePrincipal() {
    }

    /**
     * Retrieves representation of an instance of servicos.WebServicePrincipal
     *
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
        String baseURI = "http://www.tenis.com/ontologies/tenis.owl#";
        String arquivo = "/home/phillipe/Documentos/OntologiaTenis/tenis_inferencias.owl";

        OntModel modelo = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);

        //usar FileManager para abrir o arquivo
        InputStream in = FileManager.get().open(arquivo);

        //ler o documento, propriamente dito
        modelo.read(new InputStreamReader(in), "");
        
        String html = "<html><body><h1>Ontologia de Tenis Individual</h1><BR>";
        html += Tenis.listaEntidades(modelo);
        html += "</body></html>";
        return html;
        //return "<html><body><h1>Ontologia de Tenis Individual</h1></body></html>";
    }

    @GET
    @Path("/{parametro}")
    public String listaIndividuos(@PathParam("parametro") String parametro) throws IOException {

        String baseURI = "http://www.tenis.com/ontologies/tenis.owl#";
        String arquivo = "/home/phillipe/Documentos/OntologiaTenis/tenis_inferencias.owl";

        OntModel modelo = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
        
        //usar FileManager para abrir o arquivo
        InputStream in = FileManager.get().open(arquivo);

        //ler o documento, propriamente dito
        modelo.read(new InputStreamReader(in), "");

        String html = "<html>\n<body>\n";
        switch (parametro) {
            case "Jogadores":
                html += Jogador.listaJogadoresW(modelo, baseURI);
                break;
            case "Equipamentos":
                html += Equipamento.listaEquipamentosW(modelo, baseURI);
                break;
            case "Golpes":
                html += Golpe.listaGolpesW(modelo, baseURI);
                break;
            case "Pais":
                html += Pais.listaPaisesW(modelo, baseURI);
                break;
            case "Quadras":
                html += Quadra.listaQuadrasW(modelo, baseURI);
                break;
            case "Torneios":
                html += Torneio.listaTorneiosW(modelo, baseURI);
                break;
            default:
                html += parametro;
                break;
        }
        html += "</body>\n</html>";
        return html;
    }

    @GET
    @Path("Jogadores/{parametro}")
    public String sparqlJogador(@PathParam("parametro") String parametro) throws IOException {


        String baseURI = "http://www.tenis.com/ontologies/tenis.owl#";
        String arquivo = "/home/phillipe/Documentos/OntologiaTenis/tenis_inferencias.owl";
        //String arquivo = "/home/phillipe/Documentos/OntologiaTenis/tenis.owl";

        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        ontModel.read(arquivo);
        OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;

        OntModel modelo = ModelFactory.createOntologyModel(ontModelSpec, ontModel);
        modelo.read(arquivo, "RDF/XML");

//        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
//        reasoner = reasoner.bindSchema(modelo);
//        ontModelSpec = OntModelSpec.OWL_DL_MEM;
//        ontModelSpec.setReasoner(reasoner);
//        OntModel modeloInferido = ModelFactory.createOntologyModel(ontModelSpec, modelo);
//        modeloInferido.read(arquivo, "RDF/XML");

        String ret = "";
        String objectProperty = "competiuCom";
        ret += Jogador.criaQuery(parametro, objectProperty, modelo);
        objectProperty = "conhece";
        ret += Jogador.criaQuery(parametro, objectProperty, modelo);
        objectProperty = "foiCampeaoEm";
        ret += Jogador.criaQuery(parametro, objectProperty, modelo);
        objectProperty = "jogamEm";
        ret += Jogador.criaQuery(parametro, objectProperty, modelo);
        objectProperty = "possuiMesmaNacionalidadeQue";
        ret += Jogador.criaQuery(parametro, objectProperty, modelo);
        objectProperty = "realiza";
        ret += Jogador.criaQuery(parametro, objectProperty, modelo);
        objectProperty = "utiliza";
        ret += Jogador.criaQuery(parametro, objectProperty, modelo);
        objectProperty = "venceu";
        ret += Jogador.criaQuery(parametro, objectProperty, modelo);
        String html = "<html>\n<body>\n" + ret + "\n</body>\n</html>";
        return html;
    }

    @GET
    @Path("Equipamentos/{parametro}")
    public String sparqlEquipamento(@PathParam("parametro") String parametro) throws IOException {

        String baseURI = "http://www.tenis.com/ontologies/tenis.owl#";
        String arquivo = "/home/phillipe/Documentos/OntologiaTenis/tenis_inferencias.owl";

        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        ontModel.read(arquivo);
        OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;

        OntModel model = ModelFactory.createOntologyModel(ontModelSpec, ontModel);
        model.read(arquivo, "RDF/XML");
        
//        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
//        reasoner = reasoner.bindSchema(model);
//        ontModelSpec = OntModelSpec.OWL_DL_MEM;
//        ontModelSpec.setReasoner(reasoner);
//        OntModel modeloInferido = ModelFactory.createOntologyModel(ontModelSpec, model);
//        modeloInferido.read(arquivo, "RDF/XML");
        
        String ret = "";
        String objectProperty = "necessarioEm";
        ret += Equipamento.criaQuery(parametro, objectProperty, model);
        objectProperty = "usadoPor";
        ret += Equipamento.criaQuery(parametro, objectProperty, model);
        String html = "<html>\n<body>\n" + ret + "\n</body>\n</html>";
        return html;
    }

    @GET
    @Path("Torneios/{parametro}")
    public String sparqlTorneio(@PathParam("parametro") String parametro) throws IOException {

        String baseURI = "http://www.tenis.com/ontologies/tenis.owl#";
        String arquivo = "/home/phillipe/Documentos/OntologiaTenis/tenis_inferencias.owl";

        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        ontModel.read(arquivo);
        OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;

        OntModel model = ModelFactory.createOntologyModel(ontModelSpec, ontModel);
        model.read(arquivo, "RDF/XML");
        String ret = "";
        String objectProperty = "disputadoEm";
        ret += Torneio.criaQuery(parametro, objectProperty, model);
        objectProperty = "realizadoEm";
        ret += Torneio.criaQuery(parametro, objectProperty, model);
        objectProperty = "vencidoPor";
        ret += Torneio.criaQuery(parametro, objectProperty, model);
        String html = "<html>\n<body>\n" + ret + "\n</body>\n</html>";
        return html;
    }

    @GET
    @Path("Golpes/{parametro}")
    public String sparqlGolpe(@PathParam("parametro") String parametro) throws IOException {

        String baseURI = "http://www.tenis.com/ontologies/tenis.owl#";
        String arquivo = "/home/phillipe/Documentos/OntologiaTenis/tenis_inferencias.owl";

        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        ontModel.read(arquivo);
        OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;

        OntModel model = ModelFactory.createOntologyModel(ontModelSpec, ontModel);
        model.read(arquivo, "RDF/XML");
        String ret = "";
        String objectProperty = "realizadosPor";
        ret += Golpe.criaQuery(parametro, objectProperty, model);
        String html = "<html>\n<body>\n" + ret + "\n</body>\n</html>";
        return html;
    }

    @GET
    @Path("Pais/{parametro}")
    public String sparqlPais(@PathParam("parametro") String parametro) throws IOException {

        String baseURI = "http://www.tenis.com/ontologies/tenis.owl#";
        String arquivo = "/home/phillipe/Documentos/OntologiaTenis/tenis_inferencias.owl";

        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        ontModel.read(arquivo);
        OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;

        OntModel model = ModelFactory.createOntologyModel(ontModelSpec, ontModel);
        model.read(arquivo, "RDF/XML");
        String ret = "";
        String objectProperty = "ehSedeDe";
        ret += Pais.criaQuery(parametro, objectProperty, model);
        objectProperty = "possui";
        ret += Pais.criaQuery(parametro, objectProperty, model);
        String html = "<html>\n<body>\n" + ret + "\n</body>\n</html>";
        return html;
    }

    @GET
    @Path("Quadras/{parametro}")
    public String sparqlQuadras(@PathParam("parametro") String parametro) throws IOException {

        String baseURI = "http://www.tenis.com/ontologies/tenis.owl#";
        String arquivo = "/home/phillipe/Documentos/OntologiaTenis/tenis_inferencias.owl";

        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        ontModel.read(arquivo);
        OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;

        OntModel model = ModelFactory.createOntologyModel(ontModelSpec, ontModel);
        model.read(arquivo, "RDF/XML");
        String ret = "";
        String objectProperty = "localizadaEm";
        ret += Pais.criaQuery(parametro, objectProperty, model);
        objectProperty = "necessitaDe";
        ret += Pais.criaQuery(parametro, objectProperty, model);
        objectProperty = "utilizadaEm";
        ret += Pais.criaQuery(parametro, objectProperty, model);
        String html = "<html>\n<body>\n" + ret + "\n</body>\n</html>";
        return html;
    }

    
    /**
     * PUT method for updating or creating an instance of WebServicePrincipal
     *
     * @param content representation for the resource
     *
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}