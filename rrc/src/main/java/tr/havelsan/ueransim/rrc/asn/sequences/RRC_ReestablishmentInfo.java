/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.bit_strings.RRC_ShortMAC_I;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PhysCellId;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_ReestabNCellInfoList;

public class RRC_ReestablishmentInfo extends RRC_Sequence {

    public RRC_PhysCellId sourcePhysCellId;
    public RRC_ShortMAC_I targetCellShortMAC_I;
    public RRC_ReestabNCellInfoList additionalReestabInfoList;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "sourcePhysCellId","targetCellShortMAC-I","additionalReestabInfoList" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "sourcePhysCellId","targetCellShortMAC_I","additionalReestabInfoList" };
    }

    @Override
    public String getAsnName() {
        return "ReestablishmentInfo";
    }

    @Override
    public String getXmlTagName() {
        return "ReestablishmentInfo";
    }

}
