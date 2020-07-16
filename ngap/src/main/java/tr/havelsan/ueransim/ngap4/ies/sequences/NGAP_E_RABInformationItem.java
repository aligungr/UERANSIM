package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.enumerations.NGAP_DLForwarding;
import tr.havelsan.ueransim.ngap4.ies.integers.NGAP_E_RAB_ID;

public class NGAP_E_RABInformationItem extends NgapSequence {

    public NGAP_E_RAB_ID e_RAB_ID;
    public NGAP_DLForwarding dLForwarding;

    @Override
    protected String getAsnName() {
        return "E-RABInformationItem";
    }

    @Override
    protected String getXmlTagName() {
        return "E-RABInformationItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"e-RAB-ID", "dLForwarding"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"e_RAB_ID", "dLForwarding"};
    }
}
