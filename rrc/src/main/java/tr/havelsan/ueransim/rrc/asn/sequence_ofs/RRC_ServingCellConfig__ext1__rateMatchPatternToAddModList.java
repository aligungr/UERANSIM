/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_RateMatchPattern;

public class RRC_ServingCellConfig__ext1__rateMatchPatternToAddModList extends RRC_SequenceOf<RRC_RateMatchPattern> {

    @Override
    public Class<RRC_RateMatchPattern> getItemType() {
        return RRC_RateMatchPattern.class;
    }

}
