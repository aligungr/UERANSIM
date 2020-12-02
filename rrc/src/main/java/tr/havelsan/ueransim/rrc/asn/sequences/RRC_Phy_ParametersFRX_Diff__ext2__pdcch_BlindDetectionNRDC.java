/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_Phy_ParametersFRX_Diff__ext2__pdcch_BlindDetectionNRDC extends RRC_Sequence {

    public RRC_Integer pdcch_BlindDetectionMCG_UE;
    public RRC_Integer pdcch_BlindDetectionSCG_UE;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "pdcch-BlindDetectionMCG-UE","pdcch-BlindDetectionSCG-UE" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "pdcch_BlindDetectionMCG_UE","pdcch_BlindDetectionSCG_UE" };
    }

}
