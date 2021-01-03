/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_P_Max;

public class RRC_ConfigRestrictModReqSCG extends AsnSequence {
    public RRC_BandCombinationInfoSN requestedBC_MRDC; // optional
    public RRC_P_Max requestedP_MaxFR1; // optional
    public RRC_ext1_23 ext1; // optional

    public static class RRC_ext1_23 extends AsnSequence {
        public AsnInteger requestedPDCCH_BlindDetectionSCG; // optional, VALUE(1..15)
        public RRC_P_Max requestedP_MaxEUTRA; // optional
    }
}

