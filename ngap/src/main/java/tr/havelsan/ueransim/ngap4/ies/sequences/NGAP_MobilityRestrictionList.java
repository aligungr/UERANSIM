package tr.havelsan.ueransim.ngap4.ies.sequences;

import tr.havelsan.ueransim.ngap4.core.*;
import tr.havelsan.ueransim.utils.bits.*;
import tr.havelsan.ueransim.utils.octets.*;
import tr.havelsan.ueransim.ngap4.ies.bit_strings.*;
import tr.havelsan.ueransim.ngap4.ies.octet_strings.*;
import tr.havelsan.ueransim.ngap4.ies.printable_strings.*;
import tr.havelsan.ueransim.ngap4.ies.sequences.*;
import tr.havelsan.ueransim.ngap4.ies.sequence_ofs.*;
import tr.havelsan.ueransim.ngap4.ies.choices.*;
import tr.havelsan.ueransim.ngap4.ies.integers.*;
import tr.havelsan.ueransim.ngap4.ies.enumerations.*;

import java.util.List;

public class NGAP_MobilityRestrictionList extends NGAP_Sequence {

    public NGAP_PLMNIdentity servingPLMN;
    public NGAP_EquivalentPLMNs equivalentPLMNs;
    public NGAP_RATRestrictions rATRestrictions;
    public NGAP_ForbiddenAreaInformation forbiddenAreaInformation;
    public NGAP_ServiceAreaInformation serviceAreaInformation;

    @Override
    public String getAsnName() {
        return "MobilityRestrictionList";
    }

    @Override
    public String getXmlTagName() {
        return "MobilityRestrictionList";
    }

    @Override
    public String[] getMemberNames() {
        return new String[]{"servingPLMN", "equivalentPLMNs", "rATRestrictions", "forbiddenAreaInformation", "serviceAreaInformation"};
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{"servingPLMN", "equivalentPLMNs", "rATRestrictions", "forbiddenAreaInformation", "serviceAreaInformation"};
    }
}
