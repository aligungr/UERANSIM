/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_DummyI extends AsnSequence {
    public RRC_supportedSRS_TxPortSwitch_1 supportedSRS_TxPortSwitch; // mandatory
    public RRC_txSwitchImpactToRx txSwitchImpactToRx; // optional

    public static class RRC_txSwitchImpactToRx extends AsnEnumerated {
        public static final RRC_txSwitchImpactToRx TRUE = new RRC_txSwitchImpactToRx(0);
    
        private RRC_txSwitchImpactToRx(long value) {
            super(value);
        }
    }

    public static class RRC_supportedSRS_TxPortSwitch_1 extends AsnEnumerated {
        public static final RRC_supportedSRS_TxPortSwitch_1 T1R2 = new RRC_supportedSRS_TxPortSwitch_1(0);
        public static final RRC_supportedSRS_TxPortSwitch_1 T1R4 = new RRC_supportedSRS_TxPortSwitch_1(1);
        public static final RRC_supportedSRS_TxPortSwitch_1 T2R4 = new RRC_supportedSRS_TxPortSwitch_1(2);
        public static final RRC_supportedSRS_TxPortSwitch_1 T1R4_T2R4 = new RRC_supportedSRS_TxPortSwitch_1(3);
        public static final RRC_supportedSRS_TxPortSwitch_1 TR_EQUAL = new RRC_supportedSRS_TxPortSwitch_1(4);
    
        private RRC_supportedSRS_TxPortSwitch_1(long value) {
            super(value);
        }
    }
}

