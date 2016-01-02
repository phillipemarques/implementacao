/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.wrappers;

import br.ufjf.myexperiment.core.MyExperimentClient;
import br.ufjf.myexperiment.exception.MyExperimentException;
import br.ufjf.myexperiment.model.Search;
import generated.ObjectFactory;
import generated.Ontology;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author phillipe
 */
public class OntologyToMyExperiment {

    /**
     * Does the ontology Mapping for the myExperiment format
     *
     * @throws JAXBException
     */
    public static String mapToMyExperimentQuery(String workflowCkeck, String fileCkeck, String packCkeck) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        File f = new File("/home/phillipe/implementacao/src/java/br/ufjf/resources/workflowMapMyExperiment.xml");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(f);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Can't open file");

        }

        InputStream stream = fis;
        Ontology ontology = (Ontology) unmarshaller.unmarshal(stream);

        //mapping workflow
        String idMyExperiment = ontology.getWorkflows().getWorkflow().get(0).getWorkflowIdMyExperiment();
        String titleMyExperiment = ontology.getWorkflows().getWorkflow().get(0).getWorkflowTitle();
        String descriptionMyExperiment = ontology.getWorkflows().getWorkflow().get(0).getWorkflowDescription();
        String typeMyExperiment = ontology.getWorkflows().getWorkflow().get(0).getWorkflowType();
        String uploaderMyExperiment = ontology.getWorkflows().getWorkflow().get(0).getWorkflowUploaderMyExperiment();
        String createdAtMyExperiment = ontology.getWorkflows().getWorkflow().get(0).getWorkflowCreatedAt();
        String updatedAtMyExperiment = ontology.getWorkflows().getWorkflow().get(0).getWorkflowUpdatedAt();
        String previewMyExperiment = ontology.getWorkflows().getWorkflow().get(0).getWorkflowPreviewUrlMyExperiment();
        String thumbnailMyExperiment = ontology.getWorkflows().getWorkflow().get(0).getWorkflowThumbnailUrlMyExperiment();
        String thumbnailBigMyExperiment = ontology.getWorkflows().getWorkflow().get(0).getWorkflowThumbnailBigUrlMyExperiment();
        String svgBigMyExperiment = ontology.getWorkflows().getWorkflow().get(0).getWorkflowSvgUrlMyExperiment();
        String licenceTypeMyExperiment = ontology.getWorkflows().getWorkflow().get(0).getWorkflowLicenseType();
        String contentUriMyExperiment = ontology.getWorkflows().getWorkflow().get(0).getWorkflowContentUriUrlMyExperiment();
        String contentTypeMyExperiment = ontology.getWorkflows().getWorkflow().get(0).getWorkflowContentType();

        //mapping file
        //String idFileMyExperiment = ontology.getFiles().getFile().get(0).getFileIdMyExperiment();
        String fileName = ontology.getFiles().getFile().get(0).getFileName();
        //String fileTitle = ontology.getFiles().getFile().get(0).getFileTitle();
        //String fileUploader = ontology.getFiles().getFile().get(0).getFileUploaderMyExperiment();
        //String fileCreatedAt = ontology.getFiles().getFile().get(0).getFileCreatedAtMyExperiment();
        //String fileUpdatedAt = ontology.getFiles().getFile().get(0).getFileUpdatedAtMyExperiment();

        //mapping pack
        //String idPackMyExperiment = ontology.getPacks().getPack().get(0).getPackIdMyExperiment();
        //String titlePackMyExperiment = ontology.getPacks().getPack().get(0).getPackTitle();

        String type = "&type=workflow";
        if (fileCkeck != null) {
            type += ",file";
        }
        if (packCkeck != null) {
            type += ",pack";
        }
        String elements = "&elements="+idMyExperiment + "," + titleMyExperiment + "," + descriptionMyExperiment + "," + typeMyExperiment + ","
                + uploaderMyExperiment + "," + createdAtMyExperiment + "," + updatedAtMyExperiment + "," + previewMyExperiment + ","
                + thumbnailMyExperiment + "," + thumbnailBigMyExperiment + "," + svgBigMyExperiment + "," + licenceTypeMyExperiment + ","
                + contentUriMyExperiment + "," + contentTypeMyExperiment+","+fileName;
        
        String queryTime = type+elements;
        return queryTime;
    }

    public static Search searchMyExperiment(String searchValue,String queryTime) throws MyExperimentException {
        MyExperimentClient client = new MyExperimentClient();
        client.setBaseUri("http://www.myexperiment.org/");
        Search search = client.search(searchValue,queryTime);
        return search;
    }
}
