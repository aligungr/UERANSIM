package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.NgapSequence;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.NGAP_PLMNIdentity;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_EquivalentPLMNs;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_ForbiddenAreaInformation;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_RATRestrictions;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.NGAP_ServiceAreaInformation;

public class NGAP_MobilityRestrictionList extends NgapSequence {

    public NGAP_PLMNIdentity servingPLMN;
    public NGAP_EquivalentPLMNs equivalentPLMNs;
    public NGAP_RATRestrictions rATRestrictions;
    public NGAP_ForbiddenAreaInformation forbiddenAreaInformation;
    public NGAP_ServiceAreaInformation serviceAreaInformation;

    @Override
    protected String getAsnName() {
        return "MobilityRestrictionList";
    }

    @Override
    protected String getXmlTagName() {
        return "MobilityRestrictionList";
    }

    @Override
    protected String[] getMemberNames() {
        return new String[]{"servingPLMN", "equivalentPLMNs", "rATRestrictions", "forbiddenAreaInformation", "serviceAreaInformation"};
    }

    @Override
    protected String[] getMemberIdentifiers() {
        return new String[]{"servingPLMN", "equivalentPLMNs", "rATRestrictions", "forbiddenAreaInformation", "serviceAreaInformation"};
    }
}
