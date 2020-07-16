package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_TimerApproachForGUAMIRemoval;
import tr.havelsan.ueransim.ngap4.ies.printable_strings.NGAP_AMFName;

public class NGAP_UnavailableGUAMIItem extends NgapSequence {

    public NGAP_GUAMI gUAMI;
    public NGAP_TimerApproachForGUAMIRemoval timerApproachForGUAMIRemoval;
    public NGAP_AMFName backupAMFName;

    @Override
    protected String getAsnName() {
        return "UnavailableGUAMIItem";
    }

    @Override
    protected String getXmlTagName() {
        return "UnavailableGUAMIItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"gUAMI", "timerApproachForGUAMIRemoval", "backupAMFName"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"gUAMI", "timerApproachForGUAMIRemoval", "backupAMFName"};
    }
}
