/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SubcarrierSpacing;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ARFCN_ValueNR;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.*;

public class RRC_MeasObjectNR extends RRC_Sequence {

    public RRC_ARFCN_ValueNR ssbFrequency;
    public RRC_SubcarrierSpacing ssbSubcarrierSpacing;
    public RRC_SSB_MTC smtc1;
    public RRC_SSB_MTC2 smtc2;
    public RRC_ARFCN_ValueNR refFreqCSI_RS;
    public RRC_ReferenceSignalConfig referenceSignalConfig;
    public RRC_ThresholdNR absThreshSS_BlocksConsolidation;
    public RRC_ThresholdNR absThreshCSI_RS_Consolidation;
    public RRC_Integer nrofSS_BlocksToAverage;
    public RRC_Integer nrofCSI_RS_ResourcesToAverage;
    public RRC_Integer quantityConfigIndex;
    public RRC_Q_OffsetRangeList offsetMO;
    public RRC_PCI_List cellsToRemoveList;
    public RRC_CellsToAddModList cellsToAddModList;
    public RRC_PCI_RangeIndexList blackCellsToRemoveList;
    public RRC_MeasObjectNR__blackCellsToAddModList blackCellsToAddModList;
    public RRC_PCI_RangeIndexList whiteCellsToRemoveList;
    public RRC_MeasObjectNR__whiteCellsToAddModList whiteCellsToAddModList;
    public RRC_MeasObjectNR__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ssbFrequency","ssbSubcarrierSpacing","smtc1","smtc2","refFreqCSI-RS","referenceSignalConfig","absThreshSS-BlocksConsolidation","absThreshCSI-RS-Consolidation","nrofSS-BlocksToAverage","nrofCSI-RS-ResourcesToAverage","quantityConfigIndex","offsetMO","cellsToRemoveList","cellsToAddModList","blackCellsToRemoveList","blackCellsToAddModList","whiteCellsToRemoveList","whiteCellsToAddModList","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ssbFrequency","ssbSubcarrierSpacing","smtc1","smtc2","refFreqCSI_RS","referenceSignalConfig","absThreshSS_BlocksConsolidation","absThreshCSI_RS_Consolidation","nrofSS_BlocksToAverage","nrofCSI_RS_ResourcesToAverage","quantityConfigIndex","offsetMO","cellsToRemoveList","cellsToAddModList","blackCellsToRemoveList","blackCellsToAddModList","whiteCellsToRemoveList","whiteCellsToAddModList","ext1" };
    }

    @Override
    public String getAsnName() {
        return "MeasObjectNR";
    }

    @Override
    public String getXmlTagName() {
        return "MeasObjectNR";
    }

}
