/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_Phy_ParametersXDD_Diff extends RRC_Sequence {

    public RRC_Integer dynamicSFI;
    public RRC_Integer twoPUCCH_F0_2_ConsecSymbols;
    public RRC_Integer twoDifferentTPC_Loop_PUSCH;
    public RRC_Integer twoDifferentTPC_Loop_PUCCH;
    public RRC_Phy_ParametersXDD_Diff__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "dynamicSFI","twoPUCCH-F0-2-ConsecSymbols","twoDifferentTPC-Loop-PUSCH","twoDifferentTPC-Loop-PUCCH","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "dynamicSFI","twoPUCCH_F0_2_ConsecSymbols","twoDifferentTPC_Loop_PUSCH","twoDifferentTPC_Loop_PUCCH","ext1" };
    }

    @Override
    public String getAsnName() {
        return "Phy-ParametersXDD-Diff";
    }

    @Override
    public String getXmlTagName() {
        return "Phy-ParametersXDD-Diff";
    }

}
