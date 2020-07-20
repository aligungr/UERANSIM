package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_ReflectiveQosAttribute extends NGAP_Enumerated {

    public static final NGAP_ReflectiveQosAttribute SUBJECT_TO = new NGAP_ReflectiveQosAttribute("subject-to");

    protected NGAP_ReflectiveQosAttribute(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "ReflectiveQosAttribute";
    }

    @Override
    public String getXmlTagName() {
        return "ReflectiveQosAttribute";
    }
}
