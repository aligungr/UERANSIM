package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.*;

public class NGAP_UserPlaneSecurityInformation extends NGAP_Sequence {

    public NGAP_SecurityResult securityResult;
    public NGAP_SecurityIndication securityIndication;

    @Override
    public String getAsnName() {
        return "UserPlaneSecurityInformation";
    }

    @Override
    public String getXmlTagName() {
        return "UserPlaneSecurityInformation";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"securityResult", "securityIndication"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"securityResult", "securityIndication"};
    }
}
