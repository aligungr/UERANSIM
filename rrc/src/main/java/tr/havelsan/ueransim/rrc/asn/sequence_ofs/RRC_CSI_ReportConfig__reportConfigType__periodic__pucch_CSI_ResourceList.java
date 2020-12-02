/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_PUCCH_CSI_Resource;

public class RRC_CSI_ReportConfig__reportConfigType__periodic__pucch_CSI_ResourceList extends RRC_SequenceOf<RRC_PUCCH_CSI_Resource> {

    @Override
    public Class<RRC_PUCCH_CSI_Resource> getItemType() {
        return RRC_PUCCH_CSI_Resource.class;
    }

}
