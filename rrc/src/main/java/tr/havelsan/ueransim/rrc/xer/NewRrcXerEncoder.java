/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.xer;

import tr.havelsan.ueransim.rrc.asn.core.*;

public class NewRrcXerEncoder {

    public static String encode(RRC_Value value) {
        var sb = new StringBuilder();
        try {
            String rootTag = findItemTag(value.getClass());

            sb.append(String.format("<%s>", rootTag));
            encode(sb, value);
            sb.append(String.format("</%s>", rootTag));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    private static void encode(StringBuilder sb, Object value) throws Exception {
        if (value instanceof RRC_BitString) {
            // TODO: emin değilim
            sb.append(((RRC_BitString) value).value.toBinaryString(false));
        } else if (value instanceof RRC_Boolean) {
            sb.append(((RRC_Boolean) value).value ? "<true/>" : "<false/>");
        } else if (value instanceof RRC_Enumerated) {
            sb.append(String.format("<%s/>", ((RRC_Enumerated) value).sValue));
        } else if (value instanceof RRC_Integer) {
            // TODO: Signed/unsigned
            sb.append(((RRC_Integer) value).value);
        } else if (value instanceof RRC_Null) {
            // do nothing
        } else if (value instanceof RRC_OctetString) {
            // TODO: emin değilim
            sb.append(((RRC_OctetString) value).value.toHexString(false));
        } else if (value instanceof RRC_Choice) {
            var choice = (RRC_Choice) value;
            var type = choice.getClass();

            var identifiers = choice.getMemberIdentifiers();

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
                sb.append(String.format("<%s>", choice.getMemberNames()[j]));
                encode(sb, chosen);
                sb.append(String.format("</%s>", choice.getMemberNames()[j]));
            } else {
                throw new RuntimeException("No non-null fields in choice value");
            }
        } else if (value instanceof RRC_Sequence) {
            var sequence = (RRC_Sequence) value;
            var type = sequence.getClass();

            var identifiers = sequence.getMemberIdentifiers();
            var names = sequence.getMemberNames();

            for (int i = 0; i < identifiers.length; i++) {
                var obj = type.getField(identifiers[i]).get(sequence);
                if (obj != null) {
                    sb.append(String.format("<%s>", names[i]));
                    encode(sb, obj);
                    sb.append(String.format("</%s>", names[i]));
                }
            }
        } else if (value instanceof RRC_SequenceOf) {
            // TODO:
            //  XMLDelimitedItemList vs XMLValueList

            var sequenceOf = ((RRC_SequenceOf<?>) value);
            var itemType = sequenceOf.getItemType();
            var itemTag = findItemTag(itemType);

            for (var item : sequenceOf.list) {
                sb.append(String.format("<%s>", itemTag));
                encode(sb, item);
                sb.append(String.format("</%s>", itemTag));
            }
        } else {
            throw new IllegalArgumentException("Type is not handled");
        }
    }

    private static String findItemTag(Class<?> type) throws Exception {
        if (RRC_BitString.class.isAssignableFrom(type)) {
            return ((RRC_BitString) type.getConstructor(String.class).newInstance("")).getXmlTagName();
        }
        if (RRC_Boolean.class.isAssignableFrom(type)) {
            return ((RRC_Boolean) type.getConstructor(boolean.class).newInstance(false)).getXmlTagName();
        }
        if (RRC_Enumerated.class.isAssignableFrom(type)) {
            var ctor = type.getConstructor(String.class);
            ctor.setAccessible(true);
            return ((RRC_Enumerated) ctor.newInstance("")).getXmlTagName();
        }
        if (RRC_Integer.class.isAssignableFrom(type)) {
            return ((RRC_Integer) type.getConstructor(long.class).newInstance(0)).getXmlTagName();
        }
        if (RRC_Null.class.isAssignableFrom(type)) {
            return ((RRC_Null) type.getConstructor().newInstance()).getXmlTagName();
        }
        if (RRC_OctetString.class.isAssignableFrom(type)) {
            return ((RRC_OctetString) type.getConstructor(String.class).newInstance("")).getXmlTagName();
        }
        if (RRC_Sequence.class.isAssignableFrom(type)) {
            return ((RRC_Sequence) type.getConstructor().newInstance()).getXmlTagName();
        }
        if (RRC_SequenceOf.class.isAssignableFrom(type)) {
            return ((RRC_SequenceOf<?>) type.getConstructor().newInstance()).getXmlTagName();
        }
        if (RRC_Choice.class.isAssignableFrom(type)) {
            return ((RRC_Choice) type.getConstructor().newInstance()).getXmlTagName();
        }
        throw new IllegalArgumentException("Type is not handled");
    }
}
