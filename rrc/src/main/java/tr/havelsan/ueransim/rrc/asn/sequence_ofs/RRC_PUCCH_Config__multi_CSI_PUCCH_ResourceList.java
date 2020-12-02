/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PUCCH_ResourceId;

public class RRC_PUCCH_Config__multi_CSI_PUCCH_ResourceList extends RRC_SequenceOf<RRC_PUCCH_ResourceId> {

    @Override
    public Class<RRC_PUCCH_ResourceId> getItemType() {
        return RRC_PUCCH_ResourceId.class;
    }

}
