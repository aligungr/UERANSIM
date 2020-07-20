package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.printable_strings.*;

public class NGAP_ServedGUAMIItem extends NGAP_Sequence {

    public NGAP_GUAMI gUAMI;
    public NGAP_AMFName backupAMFName;

    @Override
    public String getAsnName() {
        return "ServedGUAMIItem";
    }

    @Override
    public String getXmlTagName() {
        return "ServedGUAMIItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"gUAMI", "backupAMFName"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"gUAMI", "backupAMFName"};
    }
}
