/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_CSI_ReportPeriodicityAndOffset;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_CSI_ReportConfig__reportConfigType__semiPersistentOnPUCCH__pucch_CSI_ResourceList;

public class RRC_CSI_ReportConfig__reportConfigType__semiPersistentOnPUCCH extends RRC_Sequence {

    public RRC_CSI_ReportPeriodicityAndOffset reportSlotConfig;
    public RRC_CSI_ReportConfig__reportConfigType__semiPersistentOnPUCCH__pucch_CSI_ResourceList pucch_CSI_ResourceList;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "reportSlotConfig","pucch-CSI-ResourceList" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "reportSlotConfig","pucch_CSI_ResourceList" };
    }

    @Override
    public String getAsnName() {
        throw new IllegalStateException("ASN.1 name is treated null for anonymous types.");
    }

    @Override
    public String getXmlTagName() {
        throw new IllegalStateException("XML tag name is treated null for anonymous types.");
    }

}
