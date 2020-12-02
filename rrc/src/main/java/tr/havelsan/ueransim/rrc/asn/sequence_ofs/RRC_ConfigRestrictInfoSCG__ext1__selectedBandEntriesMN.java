/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_BandEntryIndex;

public class RRC_ConfigRestrictInfoSCG__ext1__selectedBandEntriesMN extends RRC_SequenceOf<RRC_BandEntryIndex> {

    @Override
    public Class<RRC_BandEntryIndex> getItemType() {
        return RRC_BandEntryIndex.class;
    }

}
