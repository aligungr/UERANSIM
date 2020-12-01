/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ReportConfigId;

public class RRC_ReportConfigToRemoveList extends RRC_SequenceOf<RRC_ReportConfigId> {

    @Override
    public String getAsnName() {
        return "ReportConfigToRemoveList";
    }

    @Override
    public String getXmlTagName() {
        return "ReportConfigToRemoveList";
    }

    @Override
    public Class<RRC_ReportConfigId> getItemType() {
        return RRC_ReportConfigId.class;
    }

}
