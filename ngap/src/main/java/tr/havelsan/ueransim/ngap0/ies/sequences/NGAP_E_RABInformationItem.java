package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;
import tr.havelsan.ueransim.ngap0.ies.integers.*;
import tr.havelsan.ueransim.ngap0.ies.enumerations.*;

public class NGAP_E_RABInformationItem extends NGAP_Sequence {

    public NGAP_E_RAB_ID e_RAB_ID;
    public NGAP_DLForwarding dLForwarding;

    @Override
    public String getAsnName() {
        return "E-RABInformationItem";
    }

    @Override
    public String getXmlTagName() {
        return "E-RABInformationItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"e-RAB-ID", "dLForwarding"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"e_RAB_ID", "dLForwarding"};
    }
}
