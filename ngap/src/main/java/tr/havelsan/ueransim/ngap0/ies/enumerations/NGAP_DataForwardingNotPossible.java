package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_DataForwardingNotPossible extends NGAP_Enumerated {

    public static final NGAP_DataForwardingNotPossible DATA_FORWARDING_NOT_POSSIBLE = new NGAP_DataForwardingNotPossible("data-forwarding-not-possible");

    protected NGAP_DataForwardingNotPossible(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "DataForwardingNotPossible";
    }

    @Override
    public String getXmlTagName() {
        return "DataForwardingNotPossible";
    }
}
