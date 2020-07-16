package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_DataForwardingNotPossible extends NgapEnumerated {

    public static final NGAP_DataForwardingNotPossible DATA_FORWARDING_NOT_POSSIBLE = new NGAP_DataForwardingNotPossible("data-forwarding-not-possible");

    protected NGAP_DataForwardingNotPossible(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "DataForwardingNotPossible";
    }

    @Override
    protected String getXmlTagName() {
        return "DataForwardingNotPossible";
    }
}
