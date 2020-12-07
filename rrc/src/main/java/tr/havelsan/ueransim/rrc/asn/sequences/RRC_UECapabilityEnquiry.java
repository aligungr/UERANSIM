/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RRC_TransactionIdentifier;

public class RRC_UECapabilityEnquiry extends AsnSequence {
    public RRC_RRC_TransactionIdentifier rrc_TransactionIdentifier; // mandatory
    public RRC_criticalExtensions_9 criticalExtensions; // mandatory

    public static class RRC_criticalExtensions_9 extends AsnChoice {
        public RRC_UECapabilityEnquiry_IEs ueCapabilityEnquiry;
        public RRC_criticalExtensionsFuture_13 criticalExtensionsFuture;
    
        public static class RRC_criticalExtensionsFuture_13 extends AsnSequence {
        }
    }
}

