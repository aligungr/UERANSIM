/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_ServingCellConfigCommonSIB extends RRC_Sequence {

    public RRC_DownlinkConfigCommonSIB downlinkConfigCommon;
    public RRC_UplinkConfigCommonSIB uplinkConfigCommon;
    public RRC_UplinkConfigCommonSIB supplementaryUplink;
    public RRC_Integer n_TimingAdvanceOffset;
    public RRC_ServingCellConfigCommonSIB__ssb_PositionsInBurst ssb_PositionsInBurst;
    public RRC_Integer ssb_PeriodicityServingCell;
    public RRC_TDD_UL_DL_ConfigCommon tdd_UL_DL_ConfigurationCommon;
    public RRC_Integer ss_PBCH_BlockPower;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "downlinkConfigCommon","uplinkConfigCommon","supplementaryUplink","n-TimingAdvanceOffset","ssb-PositionsInBurst","ssb-PeriodicityServingCell","tdd-UL-DL-ConfigurationCommon","ss-PBCH-BlockPower" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "downlinkConfigCommon","uplinkConfigCommon","supplementaryUplink","n_TimingAdvanceOffset","ssb_PositionsInBurst","ssb_PeriodicityServingCell","tdd_UL_DL_ConfigurationCommon","ss_PBCH_BlockPower" };
    }

    @Override
    public String getAsnName() {
        return "ServingCellConfigCommonSIB";
    }

    @Override
    public String getXmlTagName() {
        return "ServingCellConfigCommonSIB";
    }

}
