package com.valkoshkin.model.strategy;

import com.valkoshkin.model.AverageMark;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class DOMStrategy implements Strategy {
    private DocumentBuilder documentBuilder;
    private Transformer transformer;
    private String inputFilePath;

    private NodeList studentsList;
    private Element averageElement;

    public DOMStrategy(String inputFilePath) {
        try {
            this.inputFilePath = inputFilePath;
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            transformer = TransformerFactory.newInstance().newTransformer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public AverageMark getAverageMark() throws Exception {
        if (documentBuilder == null || transformer == null) {
            throw new Exception("DocumentBuilder or Transformer is not initialized.");
        }
        var document = documentBuilder.parse(inputFilePath);
        transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, document.getDoctype().getSystemId());

        studentsList = document.getElementsByTagName("student");
        var studentElement = (Element) studentsList.item(0);
        var subjectsList = studentElement.getElementsByTagName("subject");
        averageElement = (Element) studentElement.getElementsByTagName("average").item(0);

        double averageFromDocument = Double.parseDouble(averageElement.getTextContent());
        double correctAverage = getCorrectAverage(subjectsList);

        return new AverageMark(averageFromDocument, correctAverage);
    }

    @Override
    public void fixAverageMark(double averageMark, String outputFilePath) throws Exception {
        if (documentBuilder == null || transformer == null) {
            throw new Exception("DocumentBuilder or Transformer is not initialized.");
        }
        averageElement.setTextContent(String.valueOf(averageMark));
        createDocument(studentsList, outputFilePath);
    }

    private double getCorrectAverage(NodeList nodeList) {
        double sum = 0;
        for (int i = 0; i < nodeList.getLength(); i++) {
            sum += Integer.parseInt(((Element) nodeList.item(i)).getAttribute("mark"));
        }
        return sum / nodeList.getLength();
    }

    private void createDocument(NodeList nodeList, String filePath) throws TransformerException {
        var newDocument = documentBuilder.newDocument();
        var source = new DOMSource(newDocument);
        var streamResult = new StreamResult(filePath);

        for (int i = 0; i < nodeList.getLength(); i++) {
            newDocument.appendChild(newDocument.importNode(nodeList.item(i), true));
        }

        transformer.transform(source, streamResult);
    }
}
