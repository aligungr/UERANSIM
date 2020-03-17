package tr.havelsan.ueransim.ngap2;

import fr.marben.asnsdk.japi.spe.OpenTypeValue;
import fr.marben.asnsdk.japi.spe.Value;
import tr.havelsan.ueransim.ngap.Values;
import tr.havelsan.ueransim.ngap.ngap_commondatatypes.Criticality;
import tr.havelsan.ueransim.ngap.ngap_commondatatypes.ProtocolIE_ID;

import java.util.ArrayList;

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
}
