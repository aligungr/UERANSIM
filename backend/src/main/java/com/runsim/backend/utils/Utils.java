package com.runsim.backend.utils;

import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

public final class Utils {

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static boolean isValidHexString(String s) {
        if (s == null || s.length() % 2 != 0)
            return false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') continue;
            if (c >= 'a' && c <= 'f') continue;
            if (c >= 'A' && c <= 'F') continue;
            return false;
        }
        return true;
    }

    public static String byteArrayToHexString(byte[] bytes) {
        final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte[] getResourceFile(String name) {
        try (var stream = Utils.class.getClassLoader().getResourceAsStream(name)) {
            if (stream == null)
                throw new RuntimeException("resource not found: " + name);
            return stream.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getResourceString(String name) {
        return new String(getResourceFile(name), StandardCharsets.UTF_8);
    }

    public static InputStream getResourceStream(String name) {
        return Utils.class.getClassLoader().getResourceAsStream(name);
    }

    public static String normalizeXml(String xml) {
        try {
            var factory = DocumentBuilderFactory.newInstance();
            var builder = factory.newDocumentBuilder();
            var is = new InputSource(new StringReader(xml));
            var document = builder.parse(is);
            document.normalizeDocument();
            trimWhitespace(document.getDocumentElement());
            return nodeToString(document.getDocumentElement(), true, false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String indentXml(String xml) {
        try {
            var factory = DocumentBuilderFactory.newInstance();
            var builder = factory.newDocumentBuilder();
            var is = new InputSource(new StringReader(xml));
            var document = builder.parse(is);
            return nodeToString(document.getDocumentElement(), true, true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void trimWhitespace(Node node) {
        var children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); ++i) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.TEXT_NODE)
                child.setTextContent(child.getTextContent().trim());
            trimWhitespace(child);
        }
    }

    private static String nodeToString(Node node, boolean omitXmlDeclaration, boolean indent) {
        var sw = new StringWriter();
        try {
            var t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, omitXmlDeclaration ? "yes" : "no");
            t.setOutputProperty(OutputKeys.INDENT, indent ? "yes" : "no");
            t.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            t.transform(new DOMSource(node), new StreamResult(sw));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sw.toString();
    }
}
