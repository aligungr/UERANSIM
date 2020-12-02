/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SubcarrierSpacing;

public class RRC_LogicalChannelConfig__ul_SpecificParameters__allowedSCS_List extends RRC_SequenceOf<RRC_SubcarrierSpacing> {

    @Override
    public Class<RRC_SubcarrierSpacing> getItemType() {
        return RRC_SubcarrierSpacing.class;
    }

}
