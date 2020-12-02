/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ControlResourceSetId;

public class RRC_PDCCH_Config__controlResourceSetToReleaseList extends RRC_SequenceOf<RRC_ControlResourceSetId> {

    @Override
    public Class<RRC_ControlResourceSetId> getItemType() {
        return RRC_ControlResourceSetId.class;
    }

}
