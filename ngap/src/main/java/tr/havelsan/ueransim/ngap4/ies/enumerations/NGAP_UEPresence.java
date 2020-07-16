package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_UEPresence extends NgapEnumerated {

    public static final NGAP_UEPresence IN = new NGAP_UEPresence("in");
    public static final NGAP_UEPresence OUT = new NGAP_UEPresence("out");
    public static final NGAP_UEPresence UNKNOWN = new NGAP_UEPresence("unknown");

    protected NGAP_UEPresence(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "UEPresence";
    }

    @Override
    protected String getXmlTagName() {
        return "UEPresence";
    }
}
