/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_CG_UCI_OnPUSCH;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_P0_PUSCH_AlphaSetId;

public class RRC_ConfiguredGrantConfig extends RRC_Sequence {

    public RRC_Integer frequencyHopping;
    public RRC_DMRS_UplinkConfig cg_DMRS_Configuration;
    public RRC_Integer mcs_Table;
    public RRC_Integer mcs_TableTransformPrecoder;
    public RRC_SetupRelease_CG_UCI_OnPUSCH uci_OnPUSCH;
    public RRC_Integer resourceAllocation;
    public RRC_Integer rbg_Size;
    public RRC_Integer powerControlLoopToUse;
    public RRC_P0_PUSCH_AlphaSetId p0_PUSCH_Alpha;
    public RRC_Integer transformPrecoder;
    public RRC_Integer nrofHARQ_Processes;
    public RRC_Integer repK;
    public RRC_Integer repK_RV;
    public RRC_Integer periodicity;
    public RRC_Integer configuredGrantTimer;
    public RRC_ConfiguredGrantConfig__rrc_ConfiguredUplinkGrant rrc_ConfiguredUplinkGrant;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "frequencyHopping","cg-DMRS-Configuration","mcs-Table","mcs-TableTransformPrecoder","uci-OnPUSCH","resourceAllocation","rbg-Size","powerControlLoopToUse","p0-PUSCH-Alpha","transformPrecoder","nrofHARQ-Processes","repK","repK-RV","periodicity","configuredGrantTimer","rrc-ConfiguredUplinkGrant" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "frequencyHopping","cg_DMRS_Configuration","mcs_Table","mcs_TableTransformPrecoder","uci_OnPUSCH","resourceAllocation","rbg_Size","powerControlLoopToUse","p0_PUSCH_Alpha","transformPrecoder","nrofHARQ_Processes","repK","repK_RV","periodicity","configuredGrantTimer","rrc_ConfiguredUplinkGrant" };
    }

    @Override
    public String getAsnName() {
        return "ConfiguredGrantConfig";
    }

    @Override
    public String getXmlTagName() {
        return "ConfiguredGrantConfig";
    }

}
