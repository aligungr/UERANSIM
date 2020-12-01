/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ServCellIndex;

public class RRC_PH_InfoSCG extends RRC_Sequence {

    public RRC_ServCellIndex servCellIndex;
    public RRC_PH_UplinkCarrierSCG ph_Uplink;
    public RRC_PH_UplinkCarrierSCG ph_SupplementaryUplink;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "servCellIndex","ph-Uplink","ph-SupplementaryUplink" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "servCellIndex","ph_Uplink","ph_SupplementaryUplink" };
    }

    @Override
    public String getAsnName() {
        return "PH-InfoSCG";
    }

    @Override
    public String getXmlTagName() {
        return "PH-InfoSCG";
    }

}
