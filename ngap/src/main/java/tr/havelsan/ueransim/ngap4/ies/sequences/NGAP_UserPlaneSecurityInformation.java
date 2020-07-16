package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;

public class NGAP_UserPlaneSecurityInformation extends NgapSequence {

    public NGAP_SecurityResult securityResult;
    public NGAP_SecurityIndication securityIndication;

    @Override
    protected String getAsnName() {
        return "UserPlaneSecurityInformation";
    }

    @Override
    protected String getXmlTagName() {
        return "UserPlaneSecurityInformation";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"securityResult", "securityIndication"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"securityResult", "securityIndication"};
    }
}
