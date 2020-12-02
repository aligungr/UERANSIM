/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_TDD_UL_DL_SlotConfig;

public class RRC_TDD_UL_DL_ConfigDedicated__slotSpecificConfigurationsToAddModList extends RRC_SequenceOf<RRC_TDD_UL_DL_SlotConfig> {

    @Override
    public Class<RRC_TDD_UL_DL_SlotConfig> getItemType() {
        return RRC_TDD_UL_DL_SlotConfig.class;
    }

}
