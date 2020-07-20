package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_DataForwardingAccepted extends NGAP_Enumerated {

    public static final NGAP_DataForwardingAccepted DATA_FORWARDING_ACCEPTED = new NGAP_DataForwardingAccepted("data-forwarding-accepted");

    protected NGAP_DataForwardingAccepted(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "DataForwardingAccepted";
    }

    @Override
    public String getXmlTagName() {
        return "DataForwardingAccepted";
    }
}
