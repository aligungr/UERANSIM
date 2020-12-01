/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_PDSCH_Config__prb_BundlingType;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_DMRS_DownlinkConfig;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_PDSCH_TimeDomainResourceAllocationList;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_ZP_CSI_RS_ResourceSet;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.*;

public class RRC_PDSCH_Config extends RRC_Sequence {

    public RRC_Integer dataScramblingIdentityPDSCH;
    public RRC_SetupRelease_DMRS_DownlinkConfig dmrs_DownlinkForPDSCH_MappingTypeA;
    public RRC_SetupRelease_DMRS_DownlinkConfig dmrs_DownlinkForPDSCH_MappingTypeB;
    public RRC_PDSCH_Config__tci_StatesToAddModList tci_StatesToAddModList;
    public RRC_PDSCH_Config__tci_StatesToReleaseList tci_StatesToReleaseList;
    public RRC_Integer vrb_ToPRB_Interleaver;
    public RRC_Integer resourceAllocation;
    public RRC_SetupRelease_PDSCH_TimeDomainResourceAllocationList pdsch_TimeDomainAllocationList;
    public RRC_Integer pdsch_AggregationFactor;
    public RRC_PDSCH_Config__rateMatchPatternToAddModList rateMatchPatternToAddModList;
    public RRC_PDSCH_Config__rateMatchPatternToReleaseList rateMatchPatternToReleaseList;
    public RRC_RateMatchPatternGroup rateMatchPatternGroup1;
    public RRC_RateMatchPatternGroup rateMatchPatternGroup2;
    public RRC_Integer rbg_Size;
    public RRC_Integer mcs_Table;
    public RRC_Integer maxNrofCodeWordsScheduledByDCI;
    public RRC_PDSCH_Config__prb_BundlingType prb_BundlingType;
    public RRC_PDSCH_Config__zp_CSI_RS_ResourceToAddModList zp_CSI_RS_ResourceToAddModList;
    public RRC_PDSCH_Config__zp_CSI_RS_ResourceToReleaseList zp_CSI_RS_ResourceToReleaseList;
    public RRC_PDSCH_Config__aperiodic_ZP_CSI_RS_ResourceSetsToAddModList aperiodic_ZP_CSI_RS_ResourceSetsToAddModList;
    public RRC_PDSCH_Config__aperiodic_ZP_CSI_RS_ResourceSetsToReleaseList aperiodic_ZP_CSI_RS_ResourceSetsToReleaseList;
    public RRC_PDSCH_Config__sp_ZP_CSI_RS_ResourceSetsToAddModList sp_ZP_CSI_RS_ResourceSetsToAddModList;
    public RRC_PDSCH_Config__sp_ZP_CSI_RS_ResourceSetsToReleaseList sp_ZP_CSI_RS_ResourceSetsToReleaseList;
    public RRC_SetupRelease_ZP_CSI_RS_ResourceSet p_ZP_CSI_RS_ResourceSet;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "dataScramblingIdentityPDSCH","dmrs-DownlinkForPDSCH-MappingTypeA","dmrs-DownlinkForPDSCH-MappingTypeB","tci-StatesToAddModList","tci-StatesToReleaseList","vrb-ToPRB-Interleaver","resourceAllocation","pdsch-TimeDomainAllocationList","pdsch-AggregationFactor","rateMatchPatternToAddModList","rateMatchPatternToReleaseList","rateMatchPatternGroup1","rateMatchPatternGroup2","rbg-Size","mcs-Table","maxNrofCodeWordsScheduledByDCI","prb-BundlingType","zp-CSI-RS-ResourceToAddModList","zp-CSI-RS-ResourceToReleaseList","aperiodic-ZP-CSI-RS-ResourceSetsToAddModList","aperiodic-ZP-CSI-RS-ResourceSetsToReleaseList","sp-ZP-CSI-RS-ResourceSetsToAddModList","sp-ZP-CSI-RS-ResourceSetsToReleaseList","p-ZP-CSI-RS-ResourceSet" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "dataScramblingIdentityPDSCH","dmrs_DownlinkForPDSCH_MappingTypeA","dmrs_DownlinkForPDSCH_MappingTypeB","tci_StatesToAddModList","tci_StatesToReleaseList","vrb_ToPRB_Interleaver","resourceAllocation","pdsch_TimeDomainAllocationList","pdsch_AggregationFactor","rateMatchPatternToAddModList","rateMatchPatternToReleaseList","rateMatchPatternGroup1","rateMatchPatternGroup2","rbg_Size","mcs_Table","maxNrofCodeWordsScheduledByDCI","prb_BundlingType","zp_CSI_RS_ResourceToAddModList","zp_CSI_RS_ResourceToReleaseList","aperiodic_ZP_CSI_RS_ResourceSetsToAddModList","aperiodic_ZP_CSI_RS_ResourceSetsToReleaseList","sp_ZP_CSI_RS_ResourceSetsToAddModList","sp_ZP_CSI_RS_ResourceSetsToReleaseList","p_ZP_CSI_RS_ResourceSet" };
    }

    @Override
    public String getAsnName() {
        return "PDSCH-Config";
    }

    @Override
    public String getXmlTagName() {
        return "PDSCH-Config";
    }

}
