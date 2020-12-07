/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RRC_TransactionIdentifier;

public class RRC_RRCReconfigurationComplete extends AsnSequence {
    public RRC_RRC_TransactionIdentifier rrc_TransactionIdentifier; // mandatory
    public RRC_criticalExtensions_31 criticalExtensions; // mandatory

    public static class RRC_criticalExtensions_31 extends AsnChoice {
        public RRC_RRCReconfigurationComplete_IEs rrcReconfigurationComplete;
        public RRC_criticalExtensionsFuture_7 criticalExtensionsFuture;
    
        public static class RRC_criticalExtensionsFuture_7 extends AsnSequence {
        }
    }
}

