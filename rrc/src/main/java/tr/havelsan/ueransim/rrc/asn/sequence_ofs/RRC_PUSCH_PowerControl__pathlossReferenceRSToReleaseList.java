/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PUSCH_PathlossReferenceRS_Id;

public class RRC_PUSCH_PowerControl__pathlossReferenceRSToReleaseList extends RRC_SequenceOf<RRC_PUSCH_PathlossReferenceRS_Id> {

    @Override
    public Class<RRC_PUSCH_PathlossReferenceRS_Id> getItemType() {
        return RRC_PUSCH_PathlossReferenceRS_Id.class;
    }

}
