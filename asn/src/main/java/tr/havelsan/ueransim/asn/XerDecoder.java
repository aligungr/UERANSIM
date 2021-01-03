/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.asn;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import tr.havelsan.ueransim.asn.core.*;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.OctetString;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.lang.reflect.Modifier;

public class XerDecoder {
    public final AsnMetaData metaData;

    public XerDecoder(AsnMetaData metaData) {
        this.metaData = metaData;
    }

    public <T extends AsnValue> T decode(String xer, Class<T> type) {
        try {
            var factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xer)));
            return decodeRoot(doc.getChildNodes().item(0), type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private <T extends AsnValue> T decodeRoot(Node node, Class<T> type) throws Exception {
        if (!(node instanceof Element))
            throw new RuntimeException("invalid XER source (invalid node type)");

        var element = (Element) node;

        var outTag = element.getTagName();
        var expectedTag = metaData.xmlTagName(type);
        if (!outTag.equals(expectedTag)) {
            throw new RuntimeException(String.format("Invalid XER source (expected outer tag: %s, found: %s)", expectedTag, outTag));
        }

        return decodeNode(node, type);
    }

    private <T extends AsnValue> T decodeNode(Node node, Class<T> type) throws Exception {
        if (!(node instanceof Element))
            throw new RuntimeException("invalid XER source (invalid node type)");

        var element = (Element) node;
        var children = element.getChildNodes();
        var childCount = children.getLength();

        if (AsnBitString.class.isAssignableFrom(type)) {
            if (childCount != 1) throw new RuntimeException("invalid XER source (single child expected for BitString)");
            var first = children.item(0);
            return type.getConstructor(BitString.class).newInstance(BitString.fromBits(first.getNodeValue()));
        }
        if (AsnBoolean.class.isAssignableFrom(type)) {
            if (childCount != 1) throw new RuntimeException("invalid XER source (single child expected for Boolean)");
            var first = children.item(0);
            return type.getConstructor(boolean.class).newInstance(Boolean.parseBoolean(first.getNodeValue()));
        }
        if (AsnEnumerated.class.isAssignableFrom(type)) {
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
                    var obj = (AsnEnumerated) field.get(null);
                    if (metaData.enumString(obj).equals(tagName))
                        return (T) obj;
                }
                throw new RuntimeException("invalid XER source (enum not found: " + tagName + ")");
            } else {
                throw new RuntimeException("invalid XER source (element child expected for Enumerated)");
            }
        }
        if (AsnInteger.class.isAssignableFrom(type)) {
            if (childCount != 1) throw new RuntimeException("invalid XER source (single child expected for Integer)");
            var first = children.item(0);
            return type.getConstructor(long.class).newInstance(Long.parseLong(first.getNodeValue())); // TODO: signed unsigned?
        }
        if (AsnNull.class.isAssignableFrom(type)) {
            if (childCount != 0)
                throw new RuntimeException("invalid XER source (no child expected for Null)");
            return type.getConstructor().newInstance();
        }
        if (AsnOctetString.class.isAssignableFrom(type)) {
            if (childCount != 1)
                throw new RuntimeException("invalid XER source (single child expected for OctetString)");
            var first = children.item(0);
            return type.getConstructor(OctetString.class).newInstance(new OctetString(first.getNodeValue()));
        }
        if (AsnChoice.class.isAssignableFrom(type)) {
            if (childCount != 1)
                throw new RuntimeException("invalid XER source (single child expected for Choice)");
            var first = children.item(0);
            if (!(first instanceof Element))
                throw new RuntimeException("invalid XER source (element child expected for Choice)");
            var firstElement = (Element) first;

            var choice = (AsnChoice) type.getConstructor().newInstance();
            var field = type.getField(tagNameToIdentifierName(choice, firstElement.getTagName()));

            var obj = decodeNode(firstElement, (Class<? extends AsnValue>) field.getType());
            field.set(choice, obj);
            return (T) choice;
        }
        if (AsnSequence.class.isAssignableFrom(type)) {
            var sequence = (AsnSequence) type.getConstructor().newInstance();
            for (int i = 0; i < childCount; i++) {
                var childNode = children.item(i);
                if (!(childNode instanceof Element)) {
                    throw new RuntimeException("invalid XER source (element children expected for Sequence)");
                }
                var childElement = (Element) childNode;

                var field = type.getField(tagNameToIdentifierName(sequence, childElement.getTagName()));
                var obj = decodeNode(childElement, (Class<? extends AsnValue>) field.getType());
                field.set(sequence, obj);
            }
            return (T) sequence;
        }
        if (AsnSequenceOf.class.isAssignableFrom(type)) {
            // TODO:  //  XMLDelimitedItemList vs XMLValueList

            var sequenceOf = (AsnSequenceOf) type.getConstructor().newInstance();
            for (int i = 0; i < childCount; i++) {
                var childNode = children.item(i);
                if (!(childNode instanceof Element)) {
                    throw new RuntimeException("invalid XER source (element children expected for SequenceOf)");
                }
                var childElement = (Element) childNode;
                if (!childElement.getTagName().equals(metaData.xmlTagName(metaData.elementType(type)))) {
                    throw new RuntimeException("invalid XER source (bad tag for SequenceOf item.)");
                }
                var obj = decodeNode(childElement, metaData.elementType(type));
                sequenceOf.list.add(obj);
            }
            return (T) sequenceOf;
        }

        throw new IllegalArgumentException("Type is not handled");
    }

    private String tagNameToIdentifierName(AsnSequence sequence, String tagName) {
        var names = metaData.memberAsnNames(sequence.getClass());
        var identifiers = metaData.memberIdentifiers(sequence.getClass());
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(tagName))
                return identifiers[i];
        }
        throw new RuntimeException("identifier not found for: " + tagName);
    }

    private String tagNameToIdentifierName(AsnChoice sequence, String tagName) {
        var names = metaData.memberAsnNames(sequence.getClass());
        var identifiers = metaData.memberIdentifiers(sequence.getClass());
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(tagName))
                return identifiers[i];
        }
        throw new RuntimeException("identifier not found for: " + tagName);
    }
}
