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

public class RRC_UE_NR_Capability_v1550 extends AsnSequence {
    public RRC_reducedCP_Latency reducedCP_Latency; // optional
    public RRC_UE_NR_Capability_v1560 nonCriticalExtension; // optional

    public static class RRC_reducedCP_Latency extends AsnEnumerated {
        public static final RRC_reducedCP_Latency SUPPORTED = new RRC_reducedCP_Latency(0);
    
        private RRC_reducedCP_Latency(long value) {
            super(value);
        }
    }
}

