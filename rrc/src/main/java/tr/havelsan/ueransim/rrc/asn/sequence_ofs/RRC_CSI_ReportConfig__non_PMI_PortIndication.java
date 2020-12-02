/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_PortIndexFor8Ranks;
import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;

public class RRC_CSI_ReportConfig__non_PMI_PortIndication extends RRC_SequenceOf<RRC_PortIndexFor8Ranks> {

    @Override
    public Class<RRC_PortIndexFor8Ranks> getItemType() {
        return RRC_PortIndexFor8Ranks.class;
    }

}
