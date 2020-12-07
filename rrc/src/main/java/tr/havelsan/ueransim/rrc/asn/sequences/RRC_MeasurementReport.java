/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_MeasurementReport extends AsnSequence {
    public RRC_criticalExtensions_21 criticalExtensions; // mandatory

    public static class RRC_criticalExtensions_21 extends AsnChoice {
        public RRC_MeasurementReport_IEs measurementReport;
        public RRC_criticalExtensionsFuture_36 criticalExtensionsFuture;
    
        public static class RRC_criticalExtensionsFuture_36 extends AsnSequence {
        }
    }
}

