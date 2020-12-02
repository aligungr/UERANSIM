/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.xer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import tr.havelsan.ueransim.rrc.asn.core.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.lang.reflect.Modifier;

public class RrcXerDecoder {

    public static <T extends RRC_Value> T decode(String xer, Class<T> type) {
        try {
            var factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xer)));
            return decodeRoot(doc.getChildNodes().item(0), type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static <T extends RRC_Value> T decodeRoot(Node node, Class<T> type) throws Exception {
        if (!(node instanceof Element))
            throw new RuntimeException("invalid XER source (invalid node type)");

        var element = (Element) node;

        var outTag = element.getTagName();
        var expectedTag = RrcXerEncoder.findItemTag(type);
        if (!outTag.equals(expectedTag)) {
            throw new RuntimeException(String.format("Invalid XER source (expected outer tag: %s, found: %s)", expectedTag, outTag));
        }

        return decodeNode(node, type);
    }

    private static <T extends RRC_Value> T decodeNode(Node node, Class<T> type) throws Exception {
        if (!(node instanceof Element))
            throw new RuntimeException("invalid XER source (invalid node type)");

        var element = (Element) node;
        var children = element.getChildNodes();
        var childCount = children.getLength();

        if (RRC_BitString.class.isAssignableFrom(type)) {
            // TODO: emin değilim

            if (childCount != 1) throw new RuntimeException("invalid XER source (single child expected for BitString)");
            var first = children.item(0);
            return type.getConstructor(String.class).newInstance(first.getNodeValue());
        }
        if (RRC_Boolean.class.isAssignableFrom(type)) {
            if (childCount != 1) throw new RuntimeException("invalid XER source (single child expected for Boolean)");
            var first = children.item(0);
            return type.getConstructor(boolean.class).newInstance(Boolean.parseBoolean(first.getNodeValue()));
        }
        if (RRC_Enumerated.class.isAssignableFrom(type)) {
            if (childCount != 1)
                throw new RuntimeException("invalid XER source (single child expected for Enumerated)");
            var first = children.item(0);
            if (first instanceof Element) {
                var tagName = ((Element) first).getTagName();
                for (var field : type.getFields()) {
                    if (field.getType() != type)
                        continue;
                    var modifiers = field.getModifiers();
                    if (!Modifier.isStatic(modifiers) || !Modifier.isFinal(modifiers))
                        continue;
                    var obj = (RRC_Enumerated) field.get(null);
                    if (obj.sValue.equals(tagName))
                        return (T) obj;
                }
                throw new RuntimeException("invalid XER source (enum not found: " + tagName + ")");
            } else {
                throw new RuntimeException("invalid XER source (element child expected for Enumerated)");
            }
        }
        if (RRC_Integer.class.isAssignableFrom(type)) {
            if (childCount != 1) throw new RuntimeException("invalid XER source (single child expected for Integer)");
            var first = children.item(0);
            return type.getConstructor(long.class).newInstance(Long.parseLong(first.getNodeValue())); // TODO: signed unsigned?
        }
        if (RRC_Null.class.isAssignableFrom(type)) {
            if (childCount != 0)
                throw new RuntimeException("invalid XER source (no child expected for Null)");
            // do nothing
        }
        if (RRC_OctetString.class.isAssignableFrom(type)) {
            // TODO: emin değilim

            if (childCount != 1)
                throw new RuntimeException("invalid XER source (single child expected for OctetString)");
            var first = children.item(0);
            return type.getConstructor(String.class).newInstance(first.getNodeValue());
        }
        if (RRC_Choice.class.isAssignableFrom(type)) {
            if (childCount != 1)
                throw new RuntimeException("invalid XER source (single child expected for Choice)");
            var first = children.item(0);
            if (!(first instanceof Element))
                throw new RuntimeException("invalid XER source (element child expected for Choice)");
            var firstElement = (Element) first;

            var choice = (RRC_Choice) type.getConstructor().newInstance();
            var field = type.getField(tagNameToIdentifierName(choice, firstElement.getTagName()));

            var obj = decodeNode(firstElement, (Class<? extends RRC_Value>) field.getType());
            field.set(choice, obj);
            return (T) choice;
        }
        if (RRC_Sequence.class.isAssignableFrom(type)) {
            var sequence = (RRC_Sequence) type.getConstructor().newInstance();
            for (int i = 0; i < childCount; i++) {
                var childNode = children.item(i);
                if (!(childNode instanceof Element)) {
                    throw new RuntimeException("invalid XER source (element children expected for Sequence)");
                }
                var childElement = (Element) childNode;

                var field = type.getField(tagNameToIdentifierName(sequence, childElement.getTagName()));
                var obj = decodeNode(childElement, (Class<? extends RRC_Value>) field.getType());
                field.set(sequence, obj);
            }
            return (T) sequence;
        }
        if (RRC_SequenceOf.class.isAssignableFrom(type)) {
            // TODO:  //  XMLDelimitedItemList vs XMLValueList

            var sequenceOf = (RRC_SequenceOf) type.getConstructor().newInstance();
            for (int i = 0; i < childCount; i++) {
                var childNode = children.item(i);
                if (!(childNode instanceof Element)) {
                    throw new RuntimeException("invalid XER source (element children expected for SequenceOf)");
                }
                var childElement = (Element) childNode;
                if (!childElement.getTagName().equals(RrcXerEncoder.findItemTag(sequenceOf.getItemType()))) {
                    throw new RuntimeException("invalid XER source (bad tag for SequenceOf item.)");
                }
                var obj = decodeNode(childElement, sequenceOf.getItemType());
                sequenceOf.list.add(obj);
            }
            return (T) sequenceOf;
        }

        throw new IllegalArgumentException("Type is not handled");
    }

    private static String tagNameToIdentifierName(RRC_Sequence sequence, String tagName) {
        var names = sequence.getMemberNames();
        var identifiers = sequence.getMemberIdentifiers();
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(tagName))
                return identifiers[i];
        }
        throw new RuntimeException("identifier not found for: " + tagName);
    }

    private static String tagNameToIdentifierName(RRC_Choice sequence, String tagName) {
        var names = sequence.getMemberNames();
        var identifiers = sequence.getMemberIdentifiers();
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(tagName))
                return identifiers[i];
        }
        throw new RuntimeException("identifier not found for: " + tagName);
    }
}
