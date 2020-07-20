package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_SliceSupportItem extends NGAP_Sequence {

    public NGAP_S_NSSAI s_NSSAI;

    @Override
    public String getAsnName() {
        return "SliceSupportItem";
    }

    @Override
    public String getXmlTagName() {
        return "SliceSupportItem";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"s-NSSAI"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"s_NSSAI"};
    }
}
