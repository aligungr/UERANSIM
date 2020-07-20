package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

public class NGAP_UnavailableGUAMIItem extends NGAP_Sequence {

    public NGAP_GUAMI gUAMI;
    public NGAP_TimerApproachForGUAMIRemoval timerApproachForGUAMIRemoval;
    public NGAP_AMFName backupAMFName;

    @Override
    public String getAsnName() {
        return "UnavailableGUAMIItem";
    }

    @Override
    public String getXmlTagName() {
        return "UnavailableGUAMIItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"gUAMI", "timerApproachForGUAMIRemoval", "backupAMFName"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"gUAMI", "timerApproachForGUAMIRemoval", "backupAMFName"};
    }
}
