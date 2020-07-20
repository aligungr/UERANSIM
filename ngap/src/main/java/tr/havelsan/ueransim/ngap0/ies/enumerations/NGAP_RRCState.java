package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_RRCState extends NGAP_Enumerated {

    public static final NGAP_RRCState INACTIVE = new NGAP_RRCState("inactive");
    public static final NGAP_RRCState CONNECTED = new NGAP_RRCState("connected");

    protected NGAP_RRCState(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "RRCState";
    }

    @Override
    public String getXmlTagName() {
        return "RRCState";
    }
}
