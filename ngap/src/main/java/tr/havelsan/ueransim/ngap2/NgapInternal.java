/*
 * MIT License
 *
 * Copyright (c) 2020 ALİ GÜNGÖR
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * @author Ali Güngör (aligng1620@gmail.com)
 */

package tr.havelsan.ueransim.ngap2;

import tr.havelsan.ueransim.nas.NasDecoder;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.utils.Logging;
import tr.havelsan.ueransim.utils.Tag;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static tr.havelsan.ueransim.core.Constants.NGAP_PDU_CONTENTS;

// TODO: This utility is invalid for PrivateMessage, privateIE and related.
/*public class NgapInternal {

    public static void sortProtocolIEs(NgapMessageType messageType, List<Value> protocolIEs) {
        var sortingList = NgapData.findIeListOfMessage(messageType);
        protocolIEs.sort(Comparator.comparingInt(ie -> {
            int index = sortingList.indexOf(findIeAsnType(ie).typeName);
            if (index == -1) index = Integer.MAX_VALUE;
            return index;
        }));
    }

    public static NgapIeType findIeAsnType(Value protocolIe) {
        return NgapIeType.valueOf(protocolIe.getClass().getSimpleName());
    }

    public static NgapIeType findIeAsnType(Class<? extends Value> protocolIeType) {
        return NgapIeType.valueOf(protocolIeType.getSimpleName());
    }

    public static String getMessageTypeClassName(NgapMessageType messageType) {
        return NGAP_PDU_CONTENTS + "." + messageType.name();
    }

    public static Class<?> getMessageClass(NgapMessageType messageType) {
        try {
            return Class.forName(getMessageTypeClassName(messageType));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Value createMessageValue(NgapMessageType messageType) {
        Class<?> clazz = getMessageClass(messageType);
        try {
            return (Value) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> void setField(Object sequence, String name, T value) {
        try {
            sequence.getClass().getField(name).set(sequence, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void appendProtocolIe(NgapMessageType messageType, Value messageValue, NgapCriticality criticality, Value value, int ieId) {
        try {
            var messageClassName = getMessageTypeClassName(messageType);
            var protocolIEsClassName = messageClassName + "$ProtocolIEs";
            var sequenceClassName = protocolIEsClassName + "$SEQUENCE";

            Class<?> classProtocolIEs = Class.forName(protocolIEsClassName);
            Class<?> classSequence = Class.forName(sequenceClassName);

            var fieldProtocolIEs = messageValue.getClass().getField("protocolIEs");
            var protocolIEs = fieldProtocolIEs.get(messageValue);
            var valueList = new ArrayList<>();
            var fieldValueList = classProtocolIEs.getField("valueList");
            if (protocolIEs == null) {
                protocolIEs = classProtocolIEs.getConstructor().newInstance();
                fieldValueList.set(protocolIEs, valueList);
            } else {
                valueList = (ArrayList) fieldValueList.get(protocolIEs);
            }
            fieldProtocolIEs.set(messageValue, protocolIEs);

            var sequence = classSequence.getConstructor().newInstance();
            setField(sequence, "id", new ProtocolIE_ID(ieId));
            setField(sequence, "criticality", new Criticality(criticality.intValue()));
            setField(sequence, "value", new OpenTypeValue(value));

            valueList.add(sequence);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends Value> List<T> extractProtocolIe(Value messageContent, Class<T> ieType) {
        try {
            var list = new ArrayList<T>();
            if (messageContent != null) {
                var messageClassName = messageContent.getClass().getName();
                var protocolIEsClassName = messageClassName + "$ProtocolIEs";
                var sequenceClassName = protocolIEsClassName + "$SEQUENCE";

                Class<?> classProtocolIEs = Class.forName(protocolIEsClassName);
                Class<?> classSequence = Class.forName(sequenceClassName);

                var fieldProtocolIEs = messageContent.getClass().getField("protocolIEs");
                var protocolIEs = fieldProtocolIEs.get(messageContent);
                var fieldValueList = classProtocolIEs.getField("valueList");
                if (protocolIEs != null) {
                    var valueList = (ArrayList) fieldValueList.get(protocolIEs);

                    for (var protocolIe : valueList) {
                        if (protocolIe != null) {
                            var openTypeValue = (OpenTypeValue) classSequence.getField("value").get(protocolIe);
                            var ie = openTypeValue.getDecodedValue();

                            if (ie != null) {
                                if (ieType.isAssignableFrom(ie.getClass())) {
                                    list.add((T) ie);
                                }
                            }
                        }
                    }
                }
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends Value> T extractLastProtocolIe(Value messageContent, Class<T> ieType) {
        var list = extractProtocolIe(messageContent, ieType);
        if (list.isEmpty()) return null;
        return list.get(list.size() - 1);
    }

    public static SequenceValue extractNgapMessage(NGAP_PDU ngapPdu) {
        if (ngapPdu == null) {
            return null;
        }

        OpenTypeValue otv = null;

        if (ngapPdu.getFieldNumber() == NGAP_PDU.ASN_initiatingMessage) {
            var initiatingMessage = (InitiatingMessage) ngapPdu.getValue();
            otv = initiatingMessage.value;
        } else if (ngapPdu.getFieldNumber() == NGAP_PDU.ASN_successfulOutcome) {
            var successfulOutcome = (SuccessfulOutcome) ngapPdu.getValue();
            otv = successfulOutcome.value;
        } else if (ngapPdu.getFieldNumber() == NGAP_PDU.ASN_unsuccessfulOutcome) {
            var unsuccessfulOutcome = (UnsuccessfulOutcome) ngapPdu.getValue();
            otv = unsuccessfulOutcome.value;
        }

        if (otv == null) {
            return null;
        }

        return (SequenceValue) otv.getDecodedValue();
    }

    public static NasMessage extractNasMessage(SequenceValue ngapMessage) {
        if (ngapMessage == null) return null;

        var protocolIes = NgapInternal.extractProtocolIe(ngapMessage, NAS_PDU.class);

        if (protocolIes.size() == 0) {
            return null;
        }

        if (protocolIes.size() > 1) {
            Logging.error(Tag.NGAP_INTERNAL, "Multiple NAS_PDU found in NGAP_PDU. All NAS_PDUs are being ignored.");
            return null;
        }

        return NasDecoder.nasPdu(protocolIes.get(0).getValue());
    }

    public static boolean isUeAssociated(SequenceValue ngapMessage) {
        var ies = extractProtocolIe(ngapMessage, RAN_UE_NGAP_ID.class);
        return ies.size() > 0;
    }

    public static boolean isProtocolIeUsable(NgapMessageType messageType, Class<? extends Value> protocolIeType) {
        var x = NgapData.findIeListOfMessage(messageType);
        return x.contains(findIeAsnType(protocolIeType).typeName);
    }
}
*/