package tr.havelsan.ueransim.ngap2;

import fr.marben.asnsdk.japi.spe.OpenTypeValue;
import fr.marben.asnsdk.japi.spe.Value;
import tr.havelsan.ueransim.ngap.Values;
import tr.havelsan.ueransim.ngap.ngap_commondatatypes.Criticality;
import tr.havelsan.ueransim.ngap.ngap_commondatatypes.ProtocolIE_ID;

import static tr.havelsan.ueransim.core.Constants.NGAP_PDU_CONTENTS;

public class NgapInternal {

    public static int findProcedureCode(NgapProcedure procedure) {
        String fieldName = "NGAP_Constants__id_" + procedure.name();
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

    public static void appendProtocolIe(NgapProcedure procedure, Value procedureContent, NgapCriticality criticality, Value value) {
        try {
            var procedureClassName = getProcedureClassName(procedure);
            var protocolIEsClassName = procedureClassName + "$ProtocolIEs";
            var sequenceClassName = protocolIEsClassName + "$SEQUENCE";

            Class<?> classProtocolIEs = Class.forName(protocolIEsClassName);
            Class<?> classSequence = Class.forName(sequenceClassName);

            // protocolIEs field
            var fieldProtocolIEs = procedureContent.getClass().getField("protocolIEs");
            var protocolIEs = fieldProtocolIEs.get(procedureContent);
            if (protocolIEs == null) {
                protocolIEs = classProtocolIEs.getConstructor().newInstance();
            }
            fieldProtocolIEs.set(procedureContent, protocolIEs);

            var sequence = classSequence.getConstructor().newInstance();
            setField(sequence, "id", new ProtocolIE_ID(findConstantId(value)));
            setField(sequence, "criticality", new Criticality(criticality.getAsnValue()));
            setField(sequence, "value", new OpenTypeValue(value));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
