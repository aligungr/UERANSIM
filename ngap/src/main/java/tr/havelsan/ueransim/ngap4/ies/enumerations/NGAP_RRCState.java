package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_RRCState extends NgapEnumerated {

    public static final NGAP_RRCState INACTIVE = new NGAP_RRCState("inactive");
    public static final NGAP_RRCState CONNECTED = new NGAP_RRCState("connected");

    protected NGAP_RRCState(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "RRCState";
    }

    @Override
    protected String getXmlTagName() {
        return "RRCState";
    }
}
