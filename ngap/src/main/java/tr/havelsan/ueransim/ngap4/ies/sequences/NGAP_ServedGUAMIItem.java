package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.printable_strings.NGAP_AMFName;

public class NGAP_ServedGUAMIItem extends NgapSequence {

    public NGAP_GUAMI gUAMI;
    public NGAP_AMFName backupAMFName;

    @Override
    protected String getAsnName() {
        return "ServedGUAMIItem";
    }

    @Override
    protected String getXmlTagName() {
        return "ServedGUAMIItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"gUAMI", "backupAMFName"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"gUAMI", "backupAMFName"};
    }
}
