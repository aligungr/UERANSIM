/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ZP_CSI_RS_ResourceSetId;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_ZP_CSI_RS_ResourceSet__zp_CSI_RS_ResourceIdList;

public class RRC_ZP_CSI_RS_ResourceSet extends RRC_Sequence {

    public RRC_ZP_CSI_RS_ResourceSetId zp_CSI_RS_ResourceSetId;
    public RRC_ZP_CSI_RS_ResourceSet__zp_CSI_RS_ResourceIdList zp_CSI_RS_ResourceIdList;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "zp-CSI-RS-ResourceSetId","zp-CSI-RS-ResourceIdList" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "zp_CSI_RS_ResourceSetId","zp_CSI_RS_ResourceIdList" };
    }

    @Override
    public String getAsnName() {
        return "ZP-CSI-RS-ResourceSet";
    }

    @Override
    public String getXmlTagName() {
        return "ZP-CSI-RS-ResourceSet";
    }

}
