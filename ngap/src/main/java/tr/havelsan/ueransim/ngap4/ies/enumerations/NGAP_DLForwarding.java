package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_DLForwarding extends NgapEnumerated {

    public static final NGAP_DLForwarding DL_FORWARDING_PROPOSED = new NGAP_DLForwarding("dl-forwarding-proposed");

    protected NGAP_DLForwarding(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "DLForwarding";
    }

    @Override
    protected String getXmlTagName() {
        return "DLForwarding";
    }
}
