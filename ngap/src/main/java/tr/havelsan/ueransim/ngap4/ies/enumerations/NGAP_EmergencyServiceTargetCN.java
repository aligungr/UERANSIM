package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_EmergencyServiceTargetCN extends NgapEnumerated {

    public static final NGAP_EmergencyServiceTargetCN FIVEGC = new NGAP_EmergencyServiceTargetCN("fiveGC");
    public static final NGAP_EmergencyServiceTargetCN EPC = new NGAP_EmergencyServiceTargetCN("epc");

    protected NGAP_EmergencyServiceTargetCN(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "EmergencyServiceTargetCN";
    }

    @Override
    protected String getXmlTagName() {
        return "EmergencyServiceTargetCN";
    }
}
