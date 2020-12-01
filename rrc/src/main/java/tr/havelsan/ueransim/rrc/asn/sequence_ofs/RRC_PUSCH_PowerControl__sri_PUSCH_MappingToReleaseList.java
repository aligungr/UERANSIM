/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SRI_PUSCH_PowerControlId;

public class RRC_PUSCH_PowerControl__sri_PUSCH_MappingToReleaseList extends RRC_SequenceOf<RRC_SRI_PUSCH_PowerControlId> {

    @Override
    public String getAsnName() {
        throw new IllegalStateException("ASN.1 name is treated null for anonymous types.");
    }

    @Override
    public String getXmlTagName() {
        throw new IllegalStateException("XML tag name is treated null for anonymous types.");
    }

    @Override
    public Class<RRC_SRI_PUSCH_PowerControlId> getItemType() {
        return RRC_SRI_PUSCH_PowerControlId.class;
    }

}
