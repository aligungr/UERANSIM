/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_ReportConfigToAddMod;

public class RRC_ReportConfigToAddModList extends RRC_SequenceOf<RRC_ReportConfigToAddMod> {

    @Override
    public String getAsnName() {
        return "ReportConfigToAddModList";
    }

    @Override
    public String getXmlTagName() {
        return "ReportConfigToAddModList";
    }

    @Override
    public Class<RRC_ReportConfigToAddMod> getItemType() {
        return RRC_ReportConfigToAddMod.class;
    }

}
