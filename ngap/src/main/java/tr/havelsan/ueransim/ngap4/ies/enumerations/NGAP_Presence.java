package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_Presence extends NgapEnumerated {

    public static final NGAP_Presence OPTIONAL = new NGAP_Presence("optional");
    public static final NGAP_Presence CONDITIONAL = new NGAP_Presence("conditional");
    public static final NGAP_Presence MANDATORY = new NGAP_Presence("mandatory");

    protected NGAP_Presence(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "Presence";
    }

    @Override
    protected String getXmlTagName() {
        return "Presence";
    }
}
