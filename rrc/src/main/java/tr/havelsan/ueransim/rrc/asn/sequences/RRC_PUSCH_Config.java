/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_DMRS_UplinkConfig;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_PUSCH_TimeDomainResourceAllocationList;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_UCI_OnPUSCH;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PUSCH_Config__frequencyHoppingOffsetLists;

public class RRC_PUSCH_Config extends RRC_Sequence {

    public RRC_Integer dataScramblingIdentityPUSCH;
    public RRC_Integer txConfig;
    public RRC_SetupRelease_DMRS_UplinkConfig dmrs_UplinkForPUSCH_MappingTypeA;
    public RRC_SetupRelease_DMRS_UplinkConfig dmrs_UplinkForPUSCH_MappingTypeB;
    public RRC_PUSCH_PowerControl pusch_PowerControl;
    public RRC_Integer frequencyHopping;
    public RRC_PUSCH_Config__frequencyHoppingOffsetLists frequencyHoppingOffsetLists;
    public RRC_Integer resourceAllocation;
    public RRC_SetupRelease_PUSCH_TimeDomainResourceAllocationList pusch_TimeDomainAllocationList;
    public RRC_Integer pusch_AggregationFactor;
    public RRC_Integer mcs_Table;
    public RRC_Integer mcs_TableTransformPrecoder;
    public RRC_Integer transformPrecoder;
    public RRC_Integer codebookSubset;
    public RRC_Integer maxRank;
    public RRC_Integer rbg_Size;
    public RRC_SetupRelease_UCI_OnPUSCH uci_OnPUSCH;
    public RRC_Integer tp_pi2BPSK;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "dataScramblingIdentityPUSCH","txConfig","dmrs-UplinkForPUSCH-MappingTypeA","dmrs-UplinkForPUSCH-MappingTypeB","pusch-PowerControl","frequencyHopping","frequencyHoppingOffsetLists","resourceAllocation","pusch-TimeDomainAllocationList","pusch-AggregationFactor","mcs-Table","mcs-TableTransformPrecoder","transformPrecoder","codebookSubset","maxRank","rbg-Size","uci-OnPUSCH","tp-pi2BPSK" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "dataScramblingIdentityPUSCH","txConfig","dmrs_UplinkForPUSCH_MappingTypeA","dmrs_UplinkForPUSCH_MappingTypeB","pusch_PowerControl","frequencyHopping","frequencyHoppingOffsetLists","resourceAllocation","pusch_TimeDomainAllocationList","pusch_AggregationFactor","mcs_Table","mcs_TableTransformPrecoder","transformPrecoder","codebookSubset","maxRank","rbg_Size","uci_OnPUSCH","tp_pi2BPSK" };
    }

    @Override
    public String getAsnName() {
        return "PUSCH-Config";
    }

    @Override
    public String getXmlTagName() {
        return "PUSCH-Config";
    }

}
