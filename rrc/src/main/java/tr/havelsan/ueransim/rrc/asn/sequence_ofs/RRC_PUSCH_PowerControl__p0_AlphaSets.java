/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_P0_PUSCH_AlphaSet;

public class RRC_PUSCH_PowerControl__p0_AlphaSets extends RRC_SequenceOf<RRC_P0_PUSCH_AlphaSet> {

    @Override
    public Class<RRC_P0_PUSCH_AlphaSet> getItemType() {
        return RRC_P0_PUSCH_AlphaSet.class;
    }

}
