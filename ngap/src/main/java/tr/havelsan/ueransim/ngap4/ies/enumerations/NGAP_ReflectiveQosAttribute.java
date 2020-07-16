package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_ReflectiveQosAttribute extends NgapEnumerated {

    public static final NGAP_ReflectiveQosAttribute SUBJECT_TO = new NGAP_ReflectiveQosAttribute("subject-to");

    protected NGAP_ReflectiveQosAttribute(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "ReflectiveQosAttribute";
    }

    @Override
    protected String getXmlTagName() {
        return "ReflectiveQosAttribute";
    }
}
