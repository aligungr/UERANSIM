/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SubcarrierSpacing;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ARFCN_ValueNR;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_FreqBandIndicatorNR;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_CellsToAddModList;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PCI_List;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PCI_RangeIndexList;

public class RRC_MeasObjectNR extends AsnSequence {
    public RRC_ARFCN_ValueNR ssbFrequency; // optional
    public RRC_SubcarrierSpacing ssbSubcarrierSpacing; // optional
    public RRC_SSB_MTC smtc1; // optional
    public RRC_SSB_MTC2 smtc2; // optional
    public RRC_ARFCN_ValueNR refFreqCSI_RS; // optional
    public RRC_ReferenceSignalConfig referenceSignalConfig; // mandatory
    public RRC_ThresholdNR absThreshSS_BlocksConsolidation; // optional
    public RRC_ThresholdNR absThreshCSI_RS_Consolidation; // optional
    public AsnInteger nrofSS_BlocksToAverage; // optional, VALUE(2..16)
    public AsnInteger nrofCSI_RS_ResourcesToAverage; // optional, VALUE(2..16)
    public AsnInteger quantityConfigIndex; // mandatory, VALUE(1..2)
    public RRC_Q_OffsetRangeList offsetMO; // mandatory
    public RRC_PCI_List cellsToRemoveList; // optional
    public RRC_CellsToAddModList cellsToAddModList; // optional
    public RRC_PCI_RangeIndexList blackCellsToRemoveList; // optional
    public RRC_blackCellsToAddModList blackCellsToAddModList; // optional, SIZE(1..8)
    public RRC_PCI_RangeIndexList whiteCellsToRemoveList; // optional
    public RRC_whiteCellsToAddModList whiteCellsToAddModList; // optional, SIZE(1..8)
    public RRC_ext1_19 ext1; // optional

    public static class RRC_ext1_19 extends AsnSequence {
        public RRC_FreqBandIndicatorNR freqBandIndicatorNR_v1530; // optional
        public RRC_measCycleSCell_v1530 measCycleSCell_v1530; // optional
    
        public static class RRC_measCycleSCell_v1530 extends AsnEnumerated {
            public static final RRC_measCycleSCell_v1530 SF160 = new RRC_measCycleSCell_v1530(0);
            public static final RRC_measCycleSCell_v1530 SF256 = new RRC_measCycleSCell_v1530(1);
            public static final RRC_measCycleSCell_v1530 SF320 = new RRC_measCycleSCell_v1530(2);
            public static final RRC_measCycleSCell_v1530 SF512 = new RRC_measCycleSCell_v1530(3);
            public static final RRC_measCycleSCell_v1530 SF640 = new RRC_measCycleSCell_v1530(4);
            public static final RRC_measCycleSCell_v1530 SF1024 = new RRC_measCycleSCell_v1530(5);
            public static final RRC_measCycleSCell_v1530 SF1280 = new RRC_measCycleSCell_v1530(6);
        
            private RRC_measCycleSCell_v1530(long value) {
                super(value);
            }
        }
    }

    // SIZE(1..8)
    public static class RRC_whiteCellsToAddModList extends AsnSequenceOf<RRC_PCI_RangeElement> {
    }

    // SIZE(1..8)
    public static class RRC_blackCellsToAddModList extends AsnSequenceOf<RRC_PCI_RangeElement> {
    }
}

