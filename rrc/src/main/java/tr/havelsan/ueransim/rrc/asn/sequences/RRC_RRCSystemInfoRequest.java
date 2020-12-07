/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_RRCSystemInfoRequest extends AsnSequence {
    public RRC_criticalExtensions_6 criticalExtensions; // mandatory

    public static class RRC_criticalExtensions_6 extends AsnChoice {
        public RRC_RRCSystemInfoRequest_r15_IEs rrcSystemInfoRequest_r15;
        public RRC_criticalExtensionsFuture_11 criticalExtensionsFuture;
    
        public static class RRC_criticalExtensionsFuture_11 extends AsnSequence {
        }
    }
}

