/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_ServingCellConfigCommon__ssb_PositionsInBurst;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_RateMatchPatternLTE_CRS;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SubcarrierSpacing;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PhysCellId;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_ServingCellConfigCommon__rateMatchPatternToAddModList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_ServingCellConfigCommon__rateMatchPatternToReleaseList;

public class RRC_ServingCellConfigCommon extends RRC_Sequence {

    public RRC_PhysCellId physCellId;
    public RRC_DownlinkConfigCommon downlinkConfigCommon;
    public RRC_UplinkConfigCommon uplinkConfigCommon;
    public RRC_UplinkConfigCommon supplementaryUplinkConfig;
    public RRC_Integer n_TimingAdvanceOffset;
    public RRC_ServingCellConfigCommon__ssb_PositionsInBurst ssb_PositionsInBurst;
    public RRC_Integer ssb_periodicityServingCell;
    public RRC_Integer dmrs_TypeA_Position;
    public RRC_SetupRelease_RateMatchPatternLTE_CRS lte_CRS_ToMatchAround;
    public RRC_ServingCellConfigCommon__rateMatchPatternToAddModList rateMatchPatternToAddModList;
    public RRC_ServingCellConfigCommon__rateMatchPatternToReleaseList rateMatchPatternToReleaseList;
    public RRC_SubcarrierSpacing ssbSubcarrierSpacing;
    public RRC_TDD_UL_DL_ConfigCommon tdd_UL_DL_ConfigurationCommon;
    public RRC_Integer ss_PBCH_BlockPower;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "physCellId","downlinkConfigCommon","uplinkConfigCommon","supplementaryUplinkConfig","n-TimingAdvanceOffset","ssb-PositionsInBurst","ssb-periodicityServingCell","dmrs-TypeA-Position","lte-CRS-ToMatchAround","rateMatchPatternToAddModList","rateMatchPatternToReleaseList","ssbSubcarrierSpacing","tdd-UL-DL-ConfigurationCommon","ss-PBCH-BlockPower" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "physCellId","downlinkConfigCommon","uplinkConfigCommon","supplementaryUplinkConfig","n_TimingAdvanceOffset","ssb_PositionsInBurst","ssb_periodicityServingCell","dmrs_TypeA_Position","lte_CRS_ToMatchAround","rateMatchPatternToAddModList","rateMatchPatternToReleaseList","ssbSubcarrierSpacing","tdd_UL_DL_ConfigurationCommon","ss_PBCH_BlockPower" };
    }

    @Override
    public String getAsnName() {
        return "ServingCellConfigCommon";
    }

    @Override
    public String getXmlTagName() {
        return "ServingCellConfigCommon";
    }

}
