/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnBitString;
import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_ServingCellConfigCommonSIB extends AsnSequence {
    public RRC_DownlinkConfigCommonSIB downlinkConfigCommon; // mandatory
    public RRC_UplinkConfigCommonSIB uplinkConfigCommon; // optional
    public RRC_UplinkConfigCommonSIB supplementaryUplink; // optional
    public RRC_n_TimingAdvanceOffset_1 n_TimingAdvanceOffset; // optional
    public RRC_ssb_PositionsInBurst_1 ssb_PositionsInBurst; // mandatory
    public RRC_ssb_PeriodicityServingCell ssb_PeriodicityServingCell; // mandatory
    public RRC_TDD_UL_DL_ConfigCommon tdd_UL_DL_ConfigurationCommon; // optional
    public AsnInteger ss_PBCH_BlockPower; // mandatory, VALUE(-60..50)

    public static class RRC_n_TimingAdvanceOffset_1 extends AsnEnumerated {
        public static final RRC_n_TimingAdvanceOffset_1 N0 = new RRC_n_TimingAdvanceOffset_1(0);
        public static final RRC_n_TimingAdvanceOffset_1 N25600 = new RRC_n_TimingAdvanceOffset_1(1);
        public static final RRC_n_TimingAdvanceOffset_1 N39936 = new RRC_n_TimingAdvanceOffset_1(2);
    
        private RRC_n_TimingAdvanceOffset_1(long value) {
            super(value);
        }
    }

    public static class RRC_ssb_PositionsInBurst_1 extends AsnSequence {
        public AsnBitString inOneGroup; // mandatory, SIZE(8)
        public AsnBitString groupPresence; // optional, SIZE(8)
    }

    public static class RRC_ssb_PeriodicityServingCell extends AsnEnumerated {
        public static final RRC_ssb_PeriodicityServingCell MS5 = new RRC_ssb_PeriodicityServingCell(0);
        public static final RRC_ssb_PeriodicityServingCell MS10 = new RRC_ssb_PeriodicityServingCell(1);
        public static final RRC_ssb_PeriodicityServingCell MS20 = new RRC_ssb_PeriodicityServingCell(2);
        public static final RRC_ssb_PeriodicityServingCell MS40 = new RRC_ssb_PeriodicityServingCell(3);
        public static final RRC_ssb_PeriodicityServingCell MS80 = new RRC_ssb_PeriodicityServingCell(4);
        public static final RRC_ssb_PeriodicityServingCell MS160 = new RRC_ssb_PeriodicityServingCell(5);
    
        private RRC_ssb_PeriodicityServingCell(long value) {
            super(value);
        }
    }
}

