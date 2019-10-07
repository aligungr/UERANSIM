package com.runsim.backend.otn;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

public final class OtnXmlParser {

    public static OtnElement parseXml(String xml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document;
        try (StringReader stringReader = new StringReader(xml)) {
            document = builder.parse(new InputSource(stringReader));
        }

        return parseXmlElement(document.getDocumentElement());
    }

    private static OtnElement parseXmlElement(Element xmlElement) {
        String tagName = xmlElement.getTagName();
        switch (tagName) {
            case "object":
                return parseObject(xmlElement);
            case "array":
                return parseArray(xmlElement);
            case "tuple":
                return parseTuple(xmlElement);
            case "number":
                return parseNumber(xmlElement);
            case "string":
                return parseString(xmlElement);
            default:
                throw new RuntimeException("unrecognized xml tag: " + tagName);
        }
    }

    private static OtnElement parseObject(Element xmlElement) {
        return new OtnObject();
    }

    private static OtnElement parseArray(Element xmlElement) {
        return new OtnObject();
    }

    private static OtnElement parseTuple(Element xmlElement) {
        return new OtnObject();
    }

    private static OtnElement parseNumber(Element xmlElement) {
        return new OtnObject();
    }

    private static OtnElement parseString(Element xmlElement) {
        return new OtnObject();
    }
}
