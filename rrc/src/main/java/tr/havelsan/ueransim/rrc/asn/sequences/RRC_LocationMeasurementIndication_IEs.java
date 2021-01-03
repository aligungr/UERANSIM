/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnNull;
import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_LocationMeasurementInfo;

public class RRC_LocationMeasurementIndication_IEs extends AsnSequence {
    public RRC_SetupRelease_LocationMeasurementInfo measurementIndication; // mandatory
    public AsnOctetString lateNonCriticalExtension; // optional
    public RRC_nonCriticalExtension_13 nonCriticalExtension; // optional

    public static class RRC_nonCriticalExtension_13 extends AsnSequence {
    }

    public static class RRC_SetupRelease_LocationMeasurementInfo extends AsnChoice {
        public AsnNull release;
        public RRC_LocationMeasurementInfo setup;
    }
}

