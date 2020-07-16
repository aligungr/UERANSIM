package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_PLMNIdentity;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_ForbiddenTACs;

public class NGAP_ForbiddenAreaInformation_Item extends NgapSequence {

    public NGAP_PLMNIdentity pLMNIdentity;
    public NGAP_ForbiddenTACs forbiddenTACs;

    @Override
    protected String getAsnName() {
        return "ForbiddenAreaInformation-Item";
    }

    @Override
    protected String getXmlTagName() {
        return "ForbiddenAreaInformation-Item";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pLMNIdentity", "forbiddenTACs"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pLMNIdentity", "forbiddenTACs"};
    }
}
