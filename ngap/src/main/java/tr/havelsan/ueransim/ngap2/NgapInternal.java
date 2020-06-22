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

import fr.marben.asnsdk.japi.spe.OpenTypeValue;
import fr.marben.asnsdk.japi.spe.SequenceValue;
import fr.marben.asnsdk.japi.spe.Value;
import tr.havelsan.ueransim.nas.NasDecoder;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.ngap.Values;
import tr.havelsan.ueransim.ngap.ngap_commondatatypes.Criticality;
import tr.havelsan.ueransim.ngap.ngap_commondatatypes.ProtocolIE_ID;
import tr.havelsan.ueransim.ngap.ngap_ies.NAS_PDU;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.InitiatingMessage;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.NGAP_PDU;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.SuccessfulOutcome;
import tr.havelsan.ueransim.ngap.ngap_pdu_descriptions.UnsuccessfulOutcome;
import tr.havelsan.ueransim.utils.Logging;
import tr.havelsan.ueransim.utils.Tag;

import java.util.ArrayList;
import java.util.List;

import static tr.havelsan.ueransim.core.Constants.NGAP_PDU_CONTENTS;

public class NgapInternal {

    public static int findProcedureCode(NgapProcedure procedure) {
        String procedureName = procedure.name();

        String fieldName = "NGAP_Constants__id_" + procedureName;
        try {
            return (int) Values.class.getField(fieldName).get(null);
        } catch (Exception ignored) {

        }

        if (procedureName.endsWith("Request"))
            procedureName = procedureName.substring(0, procedureName.length() - "Request".length());
        else if (procedureName.endsWith("Response"))
            procedureName = procedureName.substring(0, procedureName.length() - "Response".length());
        else if (procedureName.endsWith("Complete"))
            procedureName = procedureName.substring(0, procedureName.length() - "Complete".length());
        fieldName = "NGAP_Constants__id_" + procedureName;

        try {
            return (int) Values.class.getField(fieldName).get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProcedureClassName(NgapProcedure procedure) {
        return NGAP_PDU_CONTENTS + "." + procedure.name();
    }

    public static Class<?> getProcedureClass(NgapProcedure procedure) {
        try {
            return Class.forName(getProcedureClassName(procedure));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Value createProcedureValue(NgapProcedure procedure) {
        Class<?> clazz = getProcedureClass(procedure);
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

    public static int findConstantId(Value value) {
        String fieldName = "NGAP_Constants__id_" + value.getClass().getSimpleName();
        try {
            return (int) Values.class.getField(fieldName).get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void appendProtocolIe(NgapProcedure procedure, Value procedureContent, NgapCriticality criticality, Value value, Integer ieId) {
        try {
            var procedureClassName = getProcedureClassName(procedure);
            var protocolIEsClassName = procedureClassName + "$ProtocolIEs";
            var sequenceClassName = protocolIEsClassName + "$SEQUENCE";

            Class<?> classProtocolIEs = Class.forName(protocolIEsClassName);
            Class<?> classSequence = Class.forName(sequenceClassName);

            var fieldProtocolIEs = procedureContent.getClass().getField("protocolIEs");
            var protocolIEs = fieldProtocolIEs.get(procedureContent);
            var valueList = new ArrayList<>();
            var fieldValueList = classProtocolIEs.getField("valueList");
            if (protocolIEs == null) {
                protocolIEs = classProtocolIEs.getConstructor().newInstance();
                fieldValueList.set(protocolIEs, valueList);
            } else {
                valueList = (ArrayList) fieldValueList.get(protocolIEs);
            }
            fieldProtocolIEs.set(procedureContent, protocolIEs);

            if (ieId == null) {
                ieId = findConstantId(value);
            }

            var sequence = classSequence.getConstructor().newInstance();
            setField(sequence, "id", new ProtocolIE_ID(ieId));
            setField(sequence, "criticality", new Criticality(criticality.getAsnValue()));
            setField(sequence, "value", new OpenTypeValue(value));

            valueList.add(sequence);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends Value> List<T> extractProtocolIe(Value procedureContent, Class<T> ieType) {
        try {
            var list = new ArrayList<T>();
            if (procedureContent != null) {
                var procedureClassName = procedureContent.getClass().getName();
                var protocolIEsClassName = procedureClassName + "$ProtocolIEs";
                var sequenceClassName = protocolIEsClassName + "$SEQUENCE";

                Class<?> classProtocolIEs = Class.forName(protocolIEsClassName);
                Class<?> classSequence = Class.forName(sequenceClassName);

                var fieldProtocolIEs = procedureContent.getClass().getField("protocolIEs");
                var protocolIEs = fieldProtocolIEs.get(procedureContent);
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

    public static NasMessage extractNasMessage(NGAP_PDU ngapPdu) {
        if (ngapPdu == null) return null;

        var ngapMessage = NgapInternal.extractNgapMessage(ngapPdu);
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
}
