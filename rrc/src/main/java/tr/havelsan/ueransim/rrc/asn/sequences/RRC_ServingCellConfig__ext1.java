/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_RateMatchPatternLTE_CRS;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_ServingCellConfig__ext1__downlinkChannelBW_PerSCS_List;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_ServingCellConfig__ext1__rateMatchPatternToAddModList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_ServingCellConfig__ext1__rateMatchPatternToReleaseList;

public class RRC_ServingCellConfig__ext1 extends RRC_Sequence {

    public RRC_SetupRelease_RateMatchPatternLTE_CRS lte_CRS_ToMatchAround;
    public RRC_ServingCellConfig__ext1__rateMatchPatternToAddModList rateMatchPatternToAddModList;
    public RRC_ServingCellConfig__ext1__rateMatchPatternToReleaseList rateMatchPatternToReleaseList;
    public RRC_ServingCellConfig__ext1__downlinkChannelBW_PerSCS_List downlinkChannelBW_PerSCS_List;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "lte-CRS-ToMatchAround","rateMatchPatternToAddModList","rateMatchPatternToReleaseList","downlinkChannelBW-PerSCS-List" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "lte_CRS_ToMatchAround","rateMatchPatternToAddModList","rateMatchPatternToReleaseList","downlinkChannelBW_PerSCS_List" };
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
