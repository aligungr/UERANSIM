package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_DataForwardingAccepted extends NgapEnumerated {

    public static final NGAP_DataForwardingAccepted DATA_FORWARDING_ACCEPTED = new NGAP_DataForwardingAccepted("data-forwarding-accepted");

    protected NGAP_DataForwardingAccepted(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "DataForwardingAccepted";
    }

    @Override
    protected String getXmlTagName() {
        return "DataForwardingAccepted";
    }
}
