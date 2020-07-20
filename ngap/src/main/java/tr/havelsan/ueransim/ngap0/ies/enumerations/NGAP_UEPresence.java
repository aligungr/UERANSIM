package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_UEPresence extends NGAP_Enumerated {

    public static final NGAP_UEPresence IN = new NGAP_UEPresence("in");
    public static final NGAP_UEPresence OUT = new NGAP_UEPresence("out");
    public static final NGAP_UEPresence UNKNOWN = new NGAP_UEPresence("unknown");

    protected NGAP_UEPresence(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "UEPresence";
    }

    @Override
    public String getXmlTagName() {
        return "UEPresence";
    }
}
