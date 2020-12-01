/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_CSI_ResourcePeriodicityAndOffset;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_NZP_CSI_RS_ResourceId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ScramblingId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_TCI_StateId;

public class RRC_NZP_CSI_RS_Resource extends RRC_Sequence {

    public RRC_NZP_CSI_RS_ResourceId nzp_CSI_RS_ResourceId;
    public RRC_CSI_RS_ResourceMapping resourceMapping;
    public RRC_Integer powerControlOffset;
    public RRC_Integer powerControlOffsetSS;
    public RRC_ScramblingId scramblingID;
    public RRC_CSI_ResourcePeriodicityAndOffset periodicityAndOffset;
    public RRC_TCI_StateId qcl_InfoPeriodicCSI_RS;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "nzp-CSI-RS-ResourceId","resourceMapping","powerControlOffset","powerControlOffsetSS","scramblingID","periodicityAndOffset","qcl-InfoPeriodicCSI-RS" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "nzp_CSI_RS_ResourceId","resourceMapping","powerControlOffset","powerControlOffsetSS","scramblingID","periodicityAndOffset","qcl_InfoPeriodicCSI_RS" };
    }

    @Override
    public String getAsnName() {
        return "NZP-CSI-RS-Resource";
    }

    @Override
    public String getXmlTagName() {
        return "NZP-CSI-RS-Resource";
    }

}
