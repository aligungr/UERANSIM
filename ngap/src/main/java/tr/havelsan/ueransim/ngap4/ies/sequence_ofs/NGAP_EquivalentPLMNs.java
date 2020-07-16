package tr.havelsan.ueransim.ngap4.ies.sequence_ofs;

import tr.havelsan.ueransim.ngap4.core.NgapSequenceOf;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_PLMNIdentity;

public class NGAP_EquivalentPLMNs extends NgapSequenceOf<NGAP_PLMNIdentity> {

    @Override
    protected String getAsnName() {
        return "EquivalentPLMNs";
    }

    @Override
    protected String getXmlTagName() {
        return "EquivalentPLMNs";
    }

    @Override
    public Class<NGAP_PLMNIdentity> getItemType() {
        return NGAP_PLMNIdentity.class;
    }
}
