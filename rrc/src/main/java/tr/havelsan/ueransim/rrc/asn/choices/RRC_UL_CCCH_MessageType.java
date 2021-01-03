/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_RRCReestablishmentRequest;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_RRCResumeRequest;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_RRCSetupRequest;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_RRCSystemInfoRequest;

public class RRC_UL_CCCH_MessageType extends AsnChoice {
    public RRC_c1_1 c1;
    public RRC_messageClassExtension_2 messageClassExtension;

    public static class RRC_c1_1 extends AsnChoice {
        public RRC_RRCSetupRequest rrcSetupRequest;
        public RRC_RRCResumeRequest rrcResumeRequest;
        public RRC_RRCReestablishmentRequest rrcReestablishmentRequest;
        public RRC_RRCSystemInfoRequest rrcSystemInfoRequest;
    }

    public static class RRC_messageClassExtension_2 extends AsnSequence {
    }
}

