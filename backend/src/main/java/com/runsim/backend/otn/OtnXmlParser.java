package com.runsim.backend.otn;

import com.runsim.backend.utils.Utils;
import kotlin.Pair;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

public final class OtnXmlParser {

    public static OtnElement parseXml(String xml) {
        try {
            var factory = DocumentBuilderFactory.newInstance();
            var builder = factory.newDocumentBuilder();

            Document document;
            try (var stringReader = new StringReader(xml)) {
                document = builder.parse(new InputSource(stringReader));
            }
            document.normalizeDocument();
            var parsed = parseXmlNode(document.getDocumentElement());
            if (parsed == null)
                throw new RuntimeException("invalid xml");
            return parsed.getSecond();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Pair<String, OtnElement> parseXmlNode(Node xmlNode) {
        var tagName = xmlNode.getNodeName();
        if (tagName.equals("#text"))
            return null;

        if (!(xmlNode instanceof Element))
            throw new RuntimeException("unrecognized xml node: " + xmlNode.getNodeName());

        var xmlElement = (Element) xmlNode;

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
            case "base16":
                return parseBase16(xmlElement);
            default:
                throw new RuntimeException("unrecognized xml tag: " + tagName);
        }
    }

    private static Pair<String, OtnElement> parseObject(Node xmlNode) {
        var xmlElement = (Element) xmlNode;
        var name = xmlElement.getAttribute("name");
        var obj = new OtnObject();

        var children = xmlElement.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            var child = children.item(i);

            var parsed = parseXmlNode(child);
            if (parsed == null)
                continue;
            if (parsed.getFirst() == null || parsed.getFirst().length() == 0)
                throw new RuntimeException("object elements must have a name.");
            obj.put(parsed.getFirst(), parsed.getSecond());
        }

        return new Pair<>(name, obj);
    }

    private static Pair<String, OtnElement> parseArray(Node xmlNode) {
        var xmlElement = (Element) xmlNode;
        var name = xmlElement.getAttribute("name");
        var arr = new OtnArray();

        var children = xmlElement.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            var child = children.item(i);

            var parsed = parseXmlNode(child);
            if (parsed == null)
                continue;
            arr.add(parsed.getSecond());
        }

        return new Pair<>(name, arr);
    }

    private static Pair<String, OtnElement> parseTuple(Node xmlNode) {
        var xmlElement = (Element) xmlNode;
        var name = xmlElement.getAttribute("name");
        var tuple = new OtnTuple();

        var children = xmlElement.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            var child = children.item(i);

            var parsed = parseXmlNode(child);
            if (parsed == null)
                continue;
            tuple.add(parsed.getSecond());
        }

        return new Pair<>(name, tuple);
    }

    private static Pair<String, OtnElement> parseNumber(Node xmlNode) {
        var xmlElement = (Element) xmlNode;
        var name = xmlElement.getAttribute("name");

        var children = xmlElement.getChildNodes();
        if (children.getLength() != 1)
            throw new RuntimeException("number elements must have one child.");
        var child = children.item(0);
        if (!child.getNodeName().equals("#text"))
            throw new RuntimeException("bad child for a number element.");
        var innerText = child.getNodeValue();
        var value = new OtnNumber(innerText);

        return new Pair<>(name, value);
    }

    private static Pair<String, OtnElement> parseString(Node xmlNode) {
        var xmlElement = (Element) xmlNode;
        var name = xmlElement.getAttribute("name");

        var children = xmlElement.getChildNodes();
        if (children.getLength() != 1)
            throw new RuntimeException("number elements must have one child.");
        var child = children.item(0);
        if (!child.getNodeName().equals("#text"))
            throw new RuntimeException("bad child for a number element.");
        var innerText = child.getNodeValue();
        var value = new OtnString(innerText);

        return new Pair<>(name, value);
    }

    private static Pair<String, OtnElement> parseBase16(Node xmlNode) {
        var xmlElement = (Element) xmlNode;
        var name = xmlElement.getAttribute("name");

        var children = xmlElement.getChildNodes();
        if (children.getLength() != 1)
            throw new RuntimeException("number elements must have one child.");
        var child = children.item(0);
        if (!child.getNodeName().equals("#text"))
            throw new RuntimeException("bad child for a number element.");
        var innerText = child.getNodeValue();
        if (!Utils.isValidHexString(innerText))
            throw new RuntimeException("base16 element has invalid string");
        var value = new OtnBase16(innerText);
        return new Pair<>(name, value);
    }
}
