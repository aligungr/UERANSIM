package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_PLMNIdentity;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_AllowedTACs;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_NotAllowedTACs;

public class NGAP_ServiceAreaInformation_Item extends NgapSequence {

    public NGAP_PLMNIdentity pLMNIdentity;
    public NGAP_AllowedTACs allowedTACs;
    public NGAP_NotAllowedTACs notAllowedTACs;

    @Override
    protected String getAsnName() {
        return "ServiceAreaInformation-Item";
    }

    @Override
    protected String getXmlTagName() {
        return "ServiceAreaInformation-Item";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"pLMNIdentity", "allowedTACs", "notAllowedTACs"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"pLMNIdentity", "allowedTACs", "notAllowedTACs"};
    }
}
