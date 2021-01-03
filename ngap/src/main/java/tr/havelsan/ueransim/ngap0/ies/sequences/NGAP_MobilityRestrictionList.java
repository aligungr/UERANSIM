/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.ngap0.ies.sequences;

import tr.havelsan.ueransim.ngap0.core.NGAP_Sequence;
import tr.havelsan.ueransim.ngap0.ies.octet_strings.NGAP_PLMNIdentity;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_EquivalentPLMNs;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_ForbiddenAreaInformation;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_RATRestrictions;
import tr.havelsan.ueransim.ngap0.ies.sequence_ofs.NGAP_ServiceAreaInformation;

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
