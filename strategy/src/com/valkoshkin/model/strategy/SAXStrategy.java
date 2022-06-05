package com.valkoshkin.model.strategy;

import com.valkoshkin.model.AverageMark;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Result;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.util.ArrayList;

public class SAXStrategy implements Strategy {
    private SAXParser saxParser;
    private String inputFilePath;

    public SAXStrategy(String inputFilePath) {
        try {
            this.inputFilePath = inputFilePath;
            saxParser = SAXParserFactory.newInstance().newSAXParser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public AverageMark getAverageMark() throws Exception {
        if (saxParser == null) {
            throw new Exception("SAXParser is not initialized.");
        }
        var handler = new SAXReadHandler();
        saxParser.parse(inputFilePath, handler);
        return new AverageMark(handler.getCurrentAverageMark(), handler.getCorrectAverageMark());
    }

    @Override
    public void fixAverageMark(double averageMark, String outputFilePath) throws Exception {
        if (saxParser == null) {
            throw new Exception("SAXParser is not initialized.");
        }
        var streamResult = new StreamResult(outputFilePath);
        saxParser.parse(inputFilePath, new SAXWriteHandler(averageMark, streamResult));
    }

    private static class SAXReadHandler extends DefaultHandler {
        private final ArrayList<Double> marks = new ArrayList<>();
        private boolean isAverageElement = false;
        private double currentAverageMark;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if (qName.equalsIgnoreCase("student")) {
                isAverageElement = false;
            } else if (qName.equalsIgnoreCase("subject")) {
                marks.add(Double.parseDouble(attributes.getValue("mark")));
            } else if (qName.equalsIgnoreCase("average")) {
                isAverageElement = true;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            if (isAverageElement) {
                currentAverageMark = Double.parseDouble(new String(ch, start, length));
            }
        }

        public double getCurrentAverageMark() {
            return currentAverageMark;
        }

        public double getCorrectAverageMark() {
            if (marks.size() == 0) {
                return 0;
            }
            double sum = 0;
            for (var mark: marks) {
                sum += mark;
            }
            return sum / marks.size();
        }
    }

    private static class SAXWriteHandler extends DefaultHandler {
        TransformerFactory transformerFactory;
        SAXTransformerFactory saxTransformerFactory;
        TransformerHandler transformerHandler;

        private boolean isAverageElement = false;
        private final double averageMark;

        public SAXWriteHandler(double averageMark, Result result) throws Exception {
            transformerFactory = TransformerFactory.newInstance();
            if (!transformerFactory.getFeature(SAXTransformerFactory.FEATURE)) {
                throw new Exception(
                        "Cannot find a SAX-compatible TransformerFactory.");
            }

            this.averageMark = averageMark;
            saxTransformerFactory = (SAXTransformerFactory) transformerFactory;
            transformerHandler = saxTransformerFactory.newTransformerHandler();
            transformerHandler.setResult(result);
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            this.transformerHandler.startElement(uri, localName, qName, attributes);
            isAverageElement = qName.equalsIgnoreCase("average");
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            transformerHandler.endElement(uri, localName, qName);
        }

        @Override
        public void startDocument() throws SAXException {
            transformerHandler.startDocument();
        }

        @Override
        public void endDocument() throws SAXException {
            transformerHandler.endDocument();
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if (isAverageElement) {
                var preparedAverageMark = String.valueOf(averageMark).toCharArray();
                transformerHandler.characters(preparedAverageMark, 0, preparedAverageMark.length);
            } else {
                transformerHandler.characters(ch, start, length);
            }
        }
    }
}
