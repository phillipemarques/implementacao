/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.wrappers;

import br.ufjf.myexperiment.model.File;
import br.ufjf.myexperiment.model.Pack;
import br.ufjf.myexperiment.model.Search;
import br.ufjf.myexperiment.model.Workflow;
import generated.Ontology;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author phillipe
 */
public class MyExperimentToOntology {

    public static void createOntology(Search search, Ontology ontology) throws JAXBException, IOException {
        if (search.getWorkflow() != null) {
            Ontology.Workflows workflowsOnto = new Ontology.Workflows();
            for (Workflow workflow : search.getWorkflow()) {
                Ontology.Workflows.Workflow workOnto = new Ontology.Workflows.Workflow();
                if (workflow.getId() != null) {
                    workOnto.setWorkflowIdMyExperiment(workflow.getId().toString());
                }
                if (workflow.getTitle() != null) {
                    workOnto.setWorkflowTitle(workflow.getTitle());
                }
                if (workflow.getDescription() != null) {
                    workOnto.setWorkflowDescription(workflow.getDescription());
                }
                if (workflow.getType() != null) {
                    workOnto.setWorkflowType(workflow.getType());
                }
                if (workflow.getUploader() != null) {
                    workOnto.setWorkflowUploaderMyExperiment(workflow.getUploader());
                }
                if (workflow.getCreatedAt() != null) {
                    workOnto.setWorkflowCreatedAt(workflow.getCreatedAt());
                }
                if (workflow.getUpdatedAt() != null) {
                    workOnto.setWorkflowUpdatedAt(workflow.getUpdatedAt());
                }
                if (workflow.getPreview() != null) {
                    workOnto.setWorkflowPreviewUrlMyExperiment(workflow.getPreview());
                }
                if (workflow.getThumbnail() != null) {
                    workOnto.setWorkflowThumbnailUrlMyExperiment(workflow.getThumbnail());
                }
                if (workflow.getThumbnailBig() != null) {
                    workOnto.setWorkflowThumbnailBigUrlMyExperiment(workflow.getThumbnailBig());
                }
                if (workflow.getSvg() != null) {
                    workOnto.setWorkflowSvgUrlMyExperiment(workflow.getSvg());
                }
                if (workflow.getLicenseType() != null) {
                    workOnto.setWorkflowLicenseType(workflow.getLicenseType());
                }
                if (workflow.getContentUri() != null) {
                    workOnto.setWorkflowContentUriUrlMyExperiment(workflow.getContentUri());
                }
                if (workflow.getContentType() != null) {
                    workOnto.setWorkflowContentType(workflow.getContentType());
                }
                workflowsOnto.addWorkflow(workOnto);
            }
            ontology.setWorkflows(workflowsOnto);

        }

        if (search.getFile() != null) {
            Ontology.Files filesOnto = new Ontology.Files();
            for (File file : search.getFile()) {
                Ontology.Files.File fileOnto = new Ontology.Files.File();
                if (file.getId() != null) {
                    fileOnto.setFileIdMyExperiment(file.getId().toString());
                }
                if (file.getName() != null) {
                    fileOnto.setFileName(file.getName());
                }
                if (file.getTitle() != null) {
                    fileOnto.setFileTitle(file.getTitle());
                }
                fileOnto.setFileUploaderMyExperiment(file.getUploader());
                if (file.getContentType() != null) {
                    fileOnto.setFileContentType(file.getContentType());
                }
                if (file.getCreatedAt() != null) {
                    fileOnto.setFileCreatedAtMyExperiment(file.getCreatedAt());
                }
                if (file.getUpdatedAt() != null) {
                    fileOnto.setFileUpdatedAtMyExperiment(file.getUpdatedAt());
                }
                filesOnto.addFile(fileOnto);
            }
            ontology.setFiles(filesOnto);

        }

        if (search.getPack() != null) {

            Ontology.Packs packsOnto = new Ontology.Packs();
            for (Pack pack : search.getPack()) {
                Ontology.Packs.Pack packOnto = new Ontology.Packs.Pack();
                if (pack.getId() != null) {
                    packOnto.setPackIdMyExperiment(pack.getId().toString());
                }
                if (pack.getTitle() != null) {
                    packOnto.setPackTitle(pack.getTitle());
                }
                packsOnto.addPack(packOnto);
            }
            ontology.setPacks(packsOnto);
        }

    }

    /**
     * Write the data of myExperiment repository in the ontology format
     *
     * @param ontology
     * @return
     * @throws JAXBException
     * @throws IOException
     */
    public static String writeOntology(Ontology ontology) throws JAXBException, IOException {
        Writer writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(Ontology.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(ontology, writer);
        String strTemp = writer.toString();

        //write to file
//        try (FileWriter arq = new FileWriter("/home/phillipe/workflow.xml")) {
//            PrintWriter gravarArq = new PrintWriter(arq);
//            gravarArq.printf(strTemp);
//        }
        return strTemp;
    }
}
