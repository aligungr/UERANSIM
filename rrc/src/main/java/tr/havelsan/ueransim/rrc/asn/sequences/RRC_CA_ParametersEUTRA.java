/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_CA_ParametersEUTRA extends RRC_Sequence {

    public RRC_Integer multipleTimingAdvance;
    public RRC_Integer simultaneousRx_Tx;
    public RRC_BitString supportedNAICS_2CRS_AP;
    public RRC_Integer additionalRx_Tx_PerformanceReq;
    public RRC_Integer ue_CA_PowerClass_N;
    public RRC_BitString supportedBandwidthCombinationSetEUTRA_v1530;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "multipleTimingAdvance","simultaneousRx-Tx","supportedNAICS-2CRS-AP","additionalRx-Tx-PerformanceReq","ue-CA-PowerClass-N","supportedBandwidthCombinationSetEUTRA-v1530" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "multipleTimingAdvance","simultaneousRx_Tx","supportedNAICS_2CRS_AP","additionalRx_Tx_PerformanceReq","ue_CA_PowerClass_N","supportedBandwidthCombinationSetEUTRA_v1530" };
    }

    @Override
    public String getAsnName() {
        return "CA-ParametersEUTRA";
    }

    @Override
    public String getXmlTagName() {
        return "CA-ParametersEUTRA";
    }

}
