/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import tr.havelsan.ueransim.rrc.asn.core.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RrcXerEncoder {

    public static String encode(RRC_Value value) {
        var factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        var document = builder.newDocument();

        List<Node> nodes;
        try {
            nodes = encode(document, value, true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (var item : nodes) {
            document.appendChild(item);
        }

        var tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        var writer = new StringWriter();
        try {
            transformer.transform(new DOMSource(document), new StreamResult(writer));
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
        return writer.getBuffer().toString().replaceAll("[\n\r]", "");
    }

    private static List<Node> encode(Document document, Object value, boolean explicitTag) throws Exception {
        List<Node> list = new ArrayList<>();

        if (value instanceof RRC_BitString) {
            if (explicitTag) {
                var root = document.createElement(((RRC_BitString) value).getXmlTagName());
                root.appendChild(document.createTextNode(((RRC_BitString) value).value.toBinaryString(false)));
                list.add(root);
            } else {
                list.add(document.createTextNode(((RRC_BitString) value).value.toBinaryString(false)));
            }
            return list;
        }

        if (value instanceof RRC_OctetString) {
            if (explicitTag) {
                var root = document.createElement(((RRC_OctetString) value).getXmlTagName());
                root.appendChild(document.createTextNode(((RRC_OctetString) value).value.toHexString(false)));
                list.add(root);
            } else {
                list.add(document.createTextNode(((RRC_OctetString) value).value.toHexString(false)));
            }
            return list;
        }

        if (value instanceof RRC_Integer) {
            if (explicitTag) {
                var root = document.createElement(((RRC_Integer) value).getXmlTagName());
                root.appendChild(document.createTextNode(Long.toString(((RRC_Integer) value).value)));
                list.add(root);
            } else {
                list.add(document.createTextNode(Long.toString(((RRC_Integer) value).value)));
            }
            return list;
        }

        if (value instanceof RRC_Enumerated) {
            if (explicitTag) {
                var root = document.createElement(((RRC_Enumerated) value).getXmlTagName());
                root.appendChild(document.createElement(((RRC_Enumerated) value).sValue));
                list.add(root);
            } else {
                list.add(document.createElement(((RRC_Enumerated) value).sValue));
            }
            return list;
        }

        if (value instanceof RRC_Choice) {
            var choice = (RRC_Choice) value;
            var type = choice.getClass();

            var identifiers = choice.getMemberIdentifiers();

            int j = -1;
            Object object = null;

            for (int i = 0; i < identifiers.length; i++) {
                var field = type.getField(identifiers[i]);
                var obj = field.get(choice);
                if (obj != null) {
                    if (j == -1) {
                        j = i;
                        object = obj;
                    } else {
                        throw new RuntimeException("multiple non-null fields in choice value");
                    }
                }
            }

            Element element = null;

            if (j != -1) {
                element = document.createElement(choice.getMemberNames()[j]);
                var node = encode(document, object, false);

                for (var item : node) {
                    element.appendChild(item);
                }
            }

            if (explicitTag) {
                var root = document.createElement(choice.getXmlTagName());
                if (element != null) {
                    root.appendChild(element);
                }
                list.add(root);
            } else {
                if (element != null) {
                    list.add(element);
                }
            }

            return list;
        }

        if (value instanceof RRC_Sequence) {
            var sequence = (RRC_Sequence) value;
            var type = sequence.getClass();

            var identifiers = sequence.getMemberIdentifiers();
            var names = sequence.getMemberNames();

            var root = document.createElement(sequence.getXmlTagName());

            for (int i = 0; i < identifiers.length; i++) {
                var field = type.getField(identifiers[i]);
                var obj = field.get(sequence);
                if (obj != null) {
                    var element = document.createElement(names[i]);
                    for (var item : encode(document, obj, false)) {
                        element.appendChild(item);
                    }

                    if (explicitTag) {
                        root.appendChild(element);
                    } else {
                        list.add(element);
                    }
                }
            }

            if (explicitTag) {
                list.add(root);
            }

            return list;
        }

        if (value instanceof RRC_Boolean) {
            if (explicitTag) {
                var root = document.createElement(((RRC_Boolean) value).getXmlTagName());
                root.appendChild(document.createTextNode(Boolean.toString(((RRC_Boolean) value).value)));
                list.add(root);
            } else {
                list.add(document.createTextNode(Boolean.toString(((RRC_Boolean) value).value)));
            }
            return list;
        }

        if (value instanceof RRC_SequenceOf<?>) {
            var sequenceOf = (RRC_SequenceOf<?>) value;
            var root = document.createElement(sequenceOf.getXmlTagName());

            for (var item : sequenceOf.list) {
                var element = document.createElement(((RRC_Value) item).getXmlTagName());
                for (var inner : encode(document, item, false)) {
                    element.appendChild(inner);
                }

                if (explicitTag) {
                    root.appendChild(element);
                } else {
                    list.add(element);
                }
            }

            if (explicitTag) {
                list.add(root);
            }
            return list;
        }

        throw new RuntimeException("unrecognized type in NgapXerEncoder.encodeIe");
    }

    public static <T extends RRC_Value> RRC_Value decode(String xer, Class<T> type) {
        try {
            var factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            var doc = builder.parse(new InputSource(new StringReader(xer)));
            return decodeRoot(doc.getChildNodes().item(0), type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getXmlTagNameOfClass(Class<? extends RRC_Value> type) {
        try {
            if (RRC_BitString.class.isAssignableFrom(type)) {
                return type.getDeclaredConstructor(String.class).newInstance("").getXmlTagName();
            }
            if (RRC_OctetString.class.isAssignableFrom(type)) {
                return type.getDeclaredConstructor(String.class).newInstance("").getXmlTagName();
            }
            if (RRC_Integer.class.isAssignableFrom(type)) {
                return type.getDeclaredConstructor(long.class).newInstance(0).getXmlTagName();
            }
            if (RRC_Boolean.class.isAssignableFrom(type)) {
                return type.getDeclaredConstructor(boolean.class).newInstance(false).getXmlTagName();
            }
            if (RRC_Enumerated.class.isAssignableFrom(type)) {
                var ctor = type.getDeclaredConstructor(String.class);
                ctor.setAccessible(true);
                return ctor.newInstance("").getXmlTagName();
            }
            return type.getDeclaredConstructor().newInstance().getXmlTagName();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Class<? extends RRC_Value> findTypeFromTagName(String tag) throws Exception {
        Class<? extends RRC_Value>[] arr = new Class[]{
                RRC_BitString.class,
                RRC_Integer.class,
                RRC_OctetString.class,
                RRC_Boolean.class
        };

        for (var item : arr) {
            if (getXmlTagNameOfClass(item).equals(tag)) {
                return item;
            }
        }

        throw new RuntimeException("type not found for tag: " + tag);
    }

    private static <T extends RRC_Value> RRC_Value decodeRoot(Node node, Class<T> type) throws Exception {
        String tagName = node instanceof Element ? ((Element) node).getTagName() : "";
        var neededType = findTypeFromTagName(tagName);

        return decode(node.getChildNodes(), neededType, tagName);
    }

    private static int findMemberIndex(RRC_Choice choice, String memberName) {
        var names = choice.getMemberNames();
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(memberName)) {
                return i;
            }
        }
        return -1;
    }

    private static int findMemberIndex(RRC_Sequence choice, String memberName) {
        var names = choice.getMemberNames();
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(memberName)) {
                return i;
            }
        }
        return -1;
    }

    private static <T extends RRC_Value> RRC_Value decode(NodeList nodes, Class<T> type, String rootTag) throws Exception {
        if (RRC_Choice.class.isAssignableFrom(type)) {
            var choice = (RRC_Choice) type.getDeclaredConstructor().newInstance();

            if (nodes.getLength() > 1) {
                throw new RuntimeException("multiple children in choice value");
            }

            if (nodes.getLength() > 0) {
                var child = (Element) nodes.item(0);
                var memberIndex = findMemberIndex(choice, child.getTagName());
                if (memberIndex == -1) {
                    throw new RuntimeException("invalid member name in choice value");
                }

                var field = type.getField(choice.getMemberIdentifiers()[memberIndex]);
                var decoded = decode(child.getChildNodes(), (Class<RRC_Value>) field.getType(), child.getTagName());
                field.set(choice, decoded);
            }

            return choice;
        }

        if (RRC_Sequence.class.isAssignableFrom(type)) {
            RRC_Sequence sequence = (RRC_Sequence) type.getDeclaredConstructor().newInstance();

            for (int i = 0; i < nodes.getLength(); i++) {
                var child = (Element) nodes.item(i);

                var memberIndex = findMemberIndex(sequence, child.getTagName());
                if (memberIndex == -1) {
                    throw new RuntimeException("invalid member name in sequence value");
                }
                var field = type.getField(sequence.getMemberIdentifiers()[memberIndex]);
                var decoded = decode(child.getChildNodes(), (Class<RRC_Value>) field.getType(), child.getTagName());
                field.set(sequence, decoded);
            }

            return sequence;
        }

        if (RRC_SequenceOf.class.isAssignableFrom(type)) {
            var sequenceOf = (RRC_SequenceOf) type.getDeclaredConstructor().newInstance();

            for (int i = 0; i < nodes.getLength(); i++) {
                var child = (Element) nodes.item(i);
                sequenceOf.list.add(decodeRoot(child, sequenceOf.getItemType()));
            }

            return sequenceOf;
        }

        if (RRC_Integer.class.isAssignableFrom(type)) {
            if (nodes.getLength() > 1) {
                throw new RuntimeException("multiple children in integer value");
            }

            var child = nodes.item(0);
            return type.getConstructor(long.class).newInstance(Long.parseLong(child.getNodeValue()));
        }

        if (RRC_BitString.class.isAssignableFrom(type)) {
            if (nodes.getLength() > 1) {
                throw new RuntimeException("multiple children in bit string value");
            }

            var child = nodes.item(0);
            return type.getConstructor(String.class).newInstance(child.getNodeValue());
        }

        if (RRC_OctetString.class.isAssignableFrom(type)) {
            if (nodes.getLength() > 1) {
                throw new RuntimeException("multiple children in octet string value");
            }

            var child = nodes.item(0);
            return type.getConstructor(String.class).newInstance(child.getNodeValue());
        }

        if (RRC_Enumerated.class.isAssignableFrom(type)) {
            if (nodes.getLength() > 1) {
                throw new RuntimeException("multiple children in integer value");
            }

            var child = (Element) nodes.item(0);
            var fieldName = child.getTagName().toUpperCase(Locale.ENGLISH).replace("-", "_");
            var field = type.getField(fieldName);
            return (T) field.get(null);
        }

        return null;
    }
}
