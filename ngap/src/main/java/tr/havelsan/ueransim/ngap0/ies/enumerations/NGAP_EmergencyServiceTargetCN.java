package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_EmergencyServiceTargetCN extends NGAP_Enumerated {

    public static final NGAP_EmergencyServiceTargetCN FIVEGC = new NGAP_EmergencyServiceTargetCN("fiveGC");
    public static final NGAP_EmergencyServiceTargetCN EPC = new NGAP_EmergencyServiceTargetCN("epc");

    protected NGAP_EmergencyServiceTargetCN(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "EmergencyServiceTargetCN";
    }

    @Override
    public String getXmlTagName() {
        return "EmergencyServiceTargetCN";
    }
}
