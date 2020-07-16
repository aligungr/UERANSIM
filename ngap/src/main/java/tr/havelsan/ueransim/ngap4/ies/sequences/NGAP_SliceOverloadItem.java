package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;

public class NGAP_SliceOverloadItem extends NgapSequence {

    public NGAP_S_NSSAI s_NSSAI;

    @Override
    protected String getAsnName() {
        return "SliceOverloadItem";
    }

    @Override
    protected String getXmlTagName() {
        return "SliceOverloadItem";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"s-NSSAI"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"s_NSSAI"};
    }
}
