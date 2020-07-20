package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_DLForwarding extends NGAP_Enumerated {

    public static final NGAP_DLForwarding DL_FORWARDING_PROPOSED = new NGAP_DLForwarding("dl-forwarding-proposed");

    protected NGAP_DLForwarding(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "DLForwarding";
    }

    @Override
    public String getXmlTagName() {
        return "DLForwarding";
    }
}
