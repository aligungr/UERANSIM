package tr.havelsan.ueransim.ngap4.ies.enumerations;

import tr.havelsan.ueransim.ngap4.core.NgapEnumerated;

public class NGAP_TimerApproachForGUAMIRemoval extends NgapEnumerated {

    public static final NGAP_TimerApproachForGUAMIRemoval APPLY_TIMER = new NGAP_TimerApproachForGUAMIRemoval("apply-timer");

    protected NGAP_TimerApproachForGUAMIRemoval(String sValue) {
        super(sValue);
    }

    @Override
    protected String getAsnName() {
        return "TimerApproachForGUAMIRemoval";
    }

    @Override
    protected String getXmlTagName() {
        return "TimerApproachForGUAMIRemoval";
    }
}
