package tr.havelsan.ueransim.ngap0.ies.enumerations;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_TimerApproachForGUAMIRemoval extends NGAP_Enumerated {

    public static final NGAP_TimerApproachForGUAMIRemoval APPLY_TIMER = new NGAP_TimerApproachForGUAMIRemoval("apply-timer");

    protected NGAP_TimerApproachForGUAMIRemoval(String sValue) {
        super(sValue);
    }

    @Override
    public String getAsnName() {
        return "TimerApproachForGUAMIRemoval";
    }

    @Override
    public String getXmlTagName() {
        return "TimerApproachForGUAMIRemoval";
    }
}
