/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.asn;

import tr.havelsan.ueransim.asn.core.*;

public class XerEncoder {
    private final AsnMetaData metaData;

    public XerEncoder(AsnMetaData metaData) {
        this.metaData = metaData;
    }

    public String encode(AsnValue value) {
        var sb = new StringBuilder();
        try {
            String rootTag = metaData.xmlTagName(value.getClass());

            sb.append(String.format("<%s>", rootTag));
            encode(sb, value);
            sb.append(String.format("</%s>", rootTag));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    private void encode(StringBuilder sb, Object value) throws Exception {
        var desc = metaData.typeDescs[metaData.typeToId.get(value.getClass())];

        if (value instanceof AsnBitString) {
            sb.append(((AsnBitString) value).value.toBinaryString(false));
        } else if (value instanceof AsnBoolean) {
            sb.append(((AsnBoolean) value).value ? "<true/>" : "<false/>");
        } else if (value instanceof AsnEnumerated) {
            sb.append(String.format("<%s/>", metaData.enumString(value.getClass(), ((AsnEnumerated) value).intValue)));
        } else if (value instanceof AsnInteger) {
            long longVal = ((AsnInteger) value).value;

            if (desc.int_specs != null && desc.int_specs.is_unsigned) {
                sb.append(Long.toUnsignedString(longVal));
            } else {
                sb.append(longVal);
            }
        } else if (value instanceof AsnNull) {
            // do nothing
        } else if (value instanceof AsnOctetString) {
            sb.append(((AsnOctetString) value).value.toHexString(false));
        } else if (value instanceof AsnChoice) {
            var choice = (AsnChoice) value;
            var type = choice.getClass();

            var identifiers = metaData.memberIdentifiers(type);
            var names = metaData.memberAsnNames(type);

            int j = -1;
            Object chosen = null;

            for (int i = 0; i < identifiers.length; i++) {
                var field = type.getField(identifiers[i]);
                var obj = field.get(choice);
                if (obj != null) {
                    if (j == -1) {
                        j = i;
                        chosen = obj;
                    } else {
                        throw new RuntimeException("multiple non-null fields in choice value");
                    }
                }
            }

            if (j != -1) {
                sb.append(String.format("<%s>", names[j]));
                encode(sb, chosen);
                sb.append(String.format("</%s>", names[j]));
            } else {
                throw new RuntimeException("No non-null fields in choice value");
            }
        } else if (value instanceof AsnSequence) {
            var sequence = (AsnSequence) value;
            var type = sequence.getClass();

            var identifiers = metaData.memberIdentifiers(type);
            var names = metaData.memberAsnNames(type);

            for (int i = 0; i < identifiers.length; i++) {
                var obj = type.getField(identifiers[i]).get(sequence);
                if (obj != null) {
                    sb.append(String.format("<%s>", names[i]));
                    encode(sb, obj);
                    sb.append(String.format("</%s>", names[i]));
                }
            }
        } else if (value instanceof AsnSequenceOf) {
            // TODO:
            //  XMLDelimitedItemList vs XMLValueList

            var sequenceOf = ((AsnSequenceOf<?>) value);
            var itemType = metaData.elementType(value.getClass());
            var itemTag = metaData.xmlTagName(itemType);

            for (var item : sequenceOf.list) {
                sb.append(String.format("<%s>", itemTag));
                encode(sb, item);
                sb.append(String.format("</%s>", itemTag));
            }
        } else {
            throw new IllegalArgumentException("Type is not handled");
        }
    }
}
