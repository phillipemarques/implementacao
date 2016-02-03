/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tenis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.util.iterator.ExtendedIterator;

/**
 *
 * @author phillipe
 */
public class Tenis {

    /**
     * Retorna verdadeiro se a classe passada como parametro for uma das classes
     * principais da ontologia (nao sendo uma subclasse)
     * @param classesName nome da classe
     * @return
     */
    public static boolean ehEntidadePrincipal(String classesName) {
        return classesName.equals("Jogadores") || classesName.equals("Equipamentos")
                || classesName.equals("Golpes") || classesName.equals("Pais")
                || classesName.equals("Quadras") || classesName.equals("Torneios");
    }

    /**
     * Lista todas as entidades existentes na ontologia
     *
     * @param modelo
     * @return
     * @throws IOException
     */
    public static String listaEntidades(OntModel modelo) throws IOException {

        List<String> classesNames = new ArrayList<>();
        //Objeto para ler as Classes 
        ExtendedIterator<OntClass> classes = modelo.listNamedClasses();

        while (classes.hasNext()) {
            //Acessa a primeira posição do objeto
            OntClass ont = classes.next();
            if (ont.isURIResource()) {
                classesNames.add(ont.getLocalName());
            }
        }

        //ordenando classes por ordem alfabetica
        Collections.sort(classesNames);

        String listaEntidades = "";

        //Adicionar nomes das classes na String
        for (String classesName : classesNames) {
            if (ehEntidadePrincipal(classesName)) {
                listaEntidades += "<a href='http://localhost:8084/OntologiaTenisFinal/webresources/generic/";
            }

            listaEntidades += classesName;
            if (ehEntidadePrincipal(classesName)){
                listaEntidades += "'>" + classesName + "</a>";
            }

            listaEntidades += "\n<BR>";
        }

        return listaEntidades;
    }

}
