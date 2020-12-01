/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PUSCH_TimeDomainResourceAllocationList;

public class RRC_PUSCH_ConfigCommon extends RRC_Sequence {

    public RRC_Integer groupHoppingEnabledTransformPrecoding;
    public RRC_PUSCH_TimeDomainResourceAllocationList pusch_TimeDomainAllocationList;
    public RRC_Integer msg3_DeltaPreamble;
    public RRC_Integer p0_NominalWithGrant;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "groupHoppingEnabledTransformPrecoding","pusch-TimeDomainAllocationList","msg3-DeltaPreamble","p0-NominalWithGrant" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "groupHoppingEnabledTransformPrecoding","pusch_TimeDomainAllocationList","msg3_DeltaPreamble","p0_NominalWithGrant" };
    }

    @Override
    public String getAsnName() {
        return "PUSCH-ConfigCommon";
    }

    @Override
    public String getXmlTagName() {
        return "PUSCH-ConfigCommon";
    }

}
