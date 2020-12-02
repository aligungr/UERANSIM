/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RateMatchPatternId;

public class RRC_ServingCellConfigCommon__rateMatchPatternToReleaseList extends RRC_SequenceOf<RRC_RateMatchPatternId> {

    @Override
    public Class<RRC_RateMatchPatternId> getItemType() {
        return RRC_RateMatchPatternId.class;
    }

}
