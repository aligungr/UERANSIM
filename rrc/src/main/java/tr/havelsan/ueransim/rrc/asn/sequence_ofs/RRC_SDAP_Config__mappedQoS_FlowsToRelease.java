/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_QFI;

public class RRC_SDAP_Config__mappedQoS_FlowsToRelease extends RRC_SequenceOf<RRC_QFI> {

    @Override
    public Class<RRC_QFI> getItemType() {
        return RRC_QFI.class;
    }

}
