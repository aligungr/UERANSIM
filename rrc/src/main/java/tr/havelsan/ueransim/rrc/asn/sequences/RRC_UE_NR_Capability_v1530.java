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

public class RRC_UE_NR_Capability_v1530 extends AsnSequence {
    public RRC_UE_NR_CapabilityAddXDD_Mode_v1530 fdd_Add_UE_NR_Capabilities_v1530; // optional
    public RRC_UE_NR_CapabilityAddXDD_Mode_v1530 tdd_Add_UE_NR_Capabilities_v1530; // optional
    public RRC_dummy_1 dummy; // optional
    public RRC_InterRAT_Parameters interRAT_Parameters; // optional
    public RRC_inactiveState inactiveState; // optional
    public RRC_delayBudgetReporting delayBudgetReporting; // optional
    public RRC_UE_NR_Capability_v1540 nonCriticalExtension; // optional

    public static class RRC_inactiveState extends AsnEnumerated {
        public static final RRC_inactiveState SUPPORTED = new RRC_inactiveState(0);
    
        private RRC_inactiveState(long value) {
            super(value);
        }
    }

    public static class RRC_dummy_1 extends AsnEnumerated {
        public static final RRC_dummy_1 SUPPORTED = new RRC_dummy_1(0);
    
        private RRC_dummy_1(long value) {
            super(value);
        }
    }

    public static class RRC_delayBudgetReporting extends AsnEnumerated {
        public static final RRC_delayBudgetReporting SUPPORTED = new RRC_delayBudgetReporting(0);
    
        private RRC_delayBudgetReporting(long value) {
            super(value);
        }
    }
}

