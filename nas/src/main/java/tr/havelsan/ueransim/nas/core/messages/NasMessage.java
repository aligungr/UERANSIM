/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.core.messages;

import tr.havelsan.ueransim.nas.NasDecoder;
import tr.havelsan.ueransim.nas.NasEncoder;
import tr.havelsan.ueransim.nas.core.IMessageBuilder;
import tr.havelsan.ueransim.nas.core.ProtocolValue;
import tr.havelsan.ueransim.nas.core.ies.InformationElement;
import tr.havelsan.ueransim.nas.core.ies.InformationElement1;
import tr.havelsan.ueransim.nas.impl.enums.EExtendedProtocolDiscriminator;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.bits.Bit4;
import tr.havelsan.ueransim.utils.exceptions.EncodingException;
import tr.havelsan.ueransim.utils.exceptions.IncorrectImplementationException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class NasMessage extends ProtocolValue {
    public EExtendedProtocolDiscriminator extendedProtocolDiscriminator;

    public void encodeMessage(OctetOutputStream stream) {
        stream.writeOctet(extendedProtocolDiscriminator.intValue());
    }

    public void build(IMessageBuilder builder) {

    }

    private Field findField(String name) {
        if (name == null)
            return null;

        Field field;
        try {
            field = getClass().getField(name);
        } catch (NoSuchFieldException e) {
            throw new IncorrectImplementationException("public field could not found: " + name);
        }
        return field;
    }

    private Object getFieldValue(Field field) {
        if (field == null)
            return null;

        Object value;
        try {
            value = field.get(this);
        } catch (IllegalAccessException e) {
            throw new IncorrectImplementationException("could not access to field: " + field.getName());
        }
        return value;
    }

    private void setFieldValue(Field field, Object instance, Object value) {
        try {
            field.set(instance, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    final NasMessage decodeViaBuilder(OctetInputStream stream) {
        var transcodeBuilder = new MessageBuilder();
        build(transcodeBuilder);

        var clazz = getClass();
        NasMessage instance;
        try {
            instance = clazz.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (var ie : transcodeBuilder.mandatory) {
            if (ie.isType1) {
                if (ie.field0 == null && ie.field1 == null)
                    throw new EncodingException("both fields cannot be null");
                if (ie.field0 != null && ie.field1 != null) {
                    var fieldInfo0 = findField(ie.field0);
                    var fieldInfo1 = findField(ie.field1);

                    int octet = stream.readOctetI();

                    var value0 = NasDecoder.ie1(octet & 0xF, (Class<? extends InformationElement1>) fieldInfo0.getType());
                    var value1 = NasDecoder.ie1((octet >> 4) & 0xF, (Class<? extends InformationElement1>) fieldInfo1.getType());

                    setFieldValue(fieldInfo0, instance, value0);
                    setFieldValue(fieldInfo1, instance, value1);
                } else {
                    var fieldName = ie.field0 != null ? ie.field0 : ie.field1;
                    var field = findField(fieldName);

                    int octet = stream.readOctetI();
                    int halfOctet = ie.field0 != null ? (octet & 0xF) : ((octet >> 4) & 0xF);

                    var value = NasDecoder.ie1(halfOctet, (Class<? extends InformationElement1>) field.getType());
                    setFieldValue(field, instance, value);
                }
            } else {
                var field = findField(ie.field0);

                if (!InformationElement.class.isAssignableFrom(field.getType()))
                    throw new IncorrectImplementationException("bad type for field: " + ie.field0);
                if (InformationElement1.class.isAssignableFrom(field.getType()))
                    throw new IncorrectImplementationException("bad type for field: " + ie.field0);

                var decoded = NasDecoder.ie2346(stream, (Class<? extends InformationElement>) field.getType());
                setFieldValue(field, instance, decoded);
            }
        }

        while (stream.hasNext()) {
            int iei = stream.readOctetI();
            int msb = iei >> 4 & 0xF;
            int lsb = iei & 0xF;

            if (transcodeBuilder.optionalIE1.containsKey(msb)) {
                var entry = transcodeBuilder.optionalIE1.get(msb);
                if (!entry.isType1)
                    throw new IncorrectImplementationException("bad type for field: " + entry.field0);

                var fieldInfo = findField(entry.field0);
                if (!InformationElement1.class.isAssignableFrom(fieldInfo.getType()))
                    throw new IncorrectImplementationException("bad type for field: " + entry.field0);

                var decoded = NasDecoder.ie1(lsb, (Class<? extends InformationElement1>) fieldInfo.getType());
                setFieldValue(fieldInfo, instance, decoded);
            } else {
                if (!transcodeBuilder.optionalIE.containsKey(iei)) {
                    throw new IncorrectImplementationException("iei is invalid: " + iei);
                }

                var entry = transcodeBuilder.optionalIE.get(iei);
                if (entry.isType1)
                    throw new IncorrectImplementationException("bad type for field: " + entry.field0);

                var fieldInfo = findField(entry.field0);
                if (InformationElement1.class.isAssignableFrom(fieldInfo.getType()))
                    throw new IncorrectImplementationException("bad type for field: " + entry.field0);
                if (!InformationElement.class.isAssignableFrom(fieldInfo.getType()))
                    throw new IncorrectImplementationException("bad type for field: " + entry.field0);

                var decoded = NasDecoder.ie2346(stream, (Class<? extends InformationElement>) fieldInfo.getType());
                setFieldValue(fieldInfo, instance, decoded);
            }
        }

        return instance;
    }

    final void encodeViaBuilder(OctetOutputStream stream) {
        var transcodeBuilder = new MessageBuilder();
        build(transcodeBuilder);

        for (var ie : transcodeBuilder.mandatory) {
            if (ie.isType1) {
                if (ie.field0 == null && ie.field1 == null)
                    throw new EncodingException("both fields cannot be null");

                var fieldInfo0 = findField(ie.field0);
                var fieldInfo1 = findField(ie.field1);

                if (fieldInfo0 != null && !InformationElement1.class.isAssignableFrom(fieldInfo0.getType())) {
                    throw new IncorrectImplementationException("bad type for field: " + ie.field0);
                }
                if (fieldInfo1 != null && !InformationElement1.class.isAssignableFrom(fieldInfo1.getType())) {
                    throw new IncorrectImplementationException("bad type for field: " + ie.field1);
                }

                var value0 = getFieldValue(fieldInfo0);
                var value1 = getFieldValue(fieldInfo1);

                if (ie.field0 != null && value0 == null)
                    throw new EncodingException("mandatory information element is null: " + ie.field0);
                if (ie.field1 != null && value1 == null)
                    throw new EncodingException("mandatory information element is null: " + ie.field1);

                if (value0 == null) {
                    if (value1 == null) {
                        stream.writeOctet(0);
                    } else {
                        NasEncoder.ie1(stream, (InformationElement1) value1, new Bit4(0));
                    }
                } else {
                    if (value1 == null) {
                        NasEncoder.ie1(stream, new Bit4(0), (InformationElement1) value0);
                    } else {
                        NasEncoder.ie1(stream, (InformationElement1) value1, (InformationElement1) value0);
                    }
                }
            } else {
                var field = findField(ie.field0);

                if (InformationElement1.class.isAssignableFrom(field.getType())) {
                    throw new IncorrectImplementationException("explicitly specify that this IE is type 1");
                }

                Object value = getFieldValue(field);

                if (value == null)
                    throw new EncodingException("mandatory information element is null: " + ie.field0);

                if (!(value instanceof InformationElement))
                    throw new IncorrectImplementationException("bad type for field: " + ie.field0);

                var ieValue = (InformationElement) value;
                NasEncoder.ie2346(stream, ieValue);
            }
        }

        for (var ie : transcodeBuilder.optional) {
            if (ie.isType1) {
                var field = findField(ie.field0);

                if (!InformationElement1.class.isAssignableFrom(field.getType())) {
                    throw new IncorrectImplementationException("bad type for field");
                }

                Object value = getFieldValue(field);
                if (value == null)
                    continue;
                NasEncoder.ie1(stream, new Bit4(ie.iei), (InformationElement1) value);
            } else {
                var field = findField(ie.field0);

                if (InformationElement1.class.isAssignableFrom(field.getType())) {
                    throw new IncorrectImplementationException("explicitly specify that this IE is type 1");
                }

                Object value = getFieldValue(field);

                if (value == null)
                    continue;

                if (!(value instanceof InformationElement))
                    throw new IncorrectImplementationException("bad type for field: " + ie.field0);

                var ieValue = (InformationElement) value;
                NasEncoder.ie2346(stream, ie.iei, ieValue);
            }
        }
    }

    static class MessageBuilder implements IMessageBuilder {
        List<InformationElementEntry> mandatory;
        List<InformationElementEntry> optional;
        Map<Integer, InformationElementEntry> optionalIE1;
        Map<Integer, InformationElementEntry> optionalIE;

        public MessageBuilder() {
            this.mandatory = new ArrayList<>();
            this.optional = new ArrayList<>();
            this.optionalIE1 = new HashMap<>();
            this.optionalIE = new HashMap<>();
        }

        @Override
        public void mandatoryIE(String field) {
            var entry = new InformationElementEntry();
            entry.iei = -1;
            entry.field0 = field;
            entry.field1 = null;
            entry.isType1 = false;
            this.mandatory.add(entry);
        }

        @Override
        public void mandatoryIE1(String field1, String field0) {
            var entry = new InformationElementEntry();
            entry.iei = -1;
            entry.field0 = field0;
            entry.field1 = field1;
            entry.isType1 = true;
            this.mandatory.add(entry);
        }

        @Override
        public void mandatoryIE1(String field0) {
            this.mandatoryIE1(null, field0);
        }

        @Override
        public void optionalIE(int iei, String field) {
            var entry = new InformationElementEntry();
            entry.iei = iei;
            entry.field0 = field;
            entry.field1 = null;
            entry.isType1 = false;
            this.optional.add(entry);

            // WARNING: No control for already used etc.
            this.optionalIE.put(iei, entry);
        }

        @Override
        public void optionalIE1(int iei, String field) {
            var entry = new InformationElementEntry();
            entry.iei = iei;
            entry.field0 = field;
            entry.field1 = null;
            entry.isType1 = true;
            this.optional.add(entry);

            // WARNING: No control for already used etc.
            this.optionalIE1.put(iei, entry);
        }
    }

    static class InformationElementEntry {
        boolean isType1;
        int iei;
        String field0;
        String field1;
    }
}
