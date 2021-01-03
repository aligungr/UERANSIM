/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.*;
import tr.havelsan.ueransim.rrc.asn.bit_strings.RRC_NG_5G_S_TMSI;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_S_NSSAI;
import tr.havelsan.ueransim.rrc.asn.octet_strings.RRC_DedicatedNAS_Message;

public class RRC_RRCSetupComplete_IEs extends AsnSequence {
    public AsnInteger selectedPLMN_Identity; // mandatory, VALUE(1..12)
    public RRC_RegisteredAMF registeredAMF; // optional
    public RRC_guami_Type guami_Type; // optional
    public RRC_s_NSSAI_List s_NSSAI_List; // optional, SIZE(1..8)
    public RRC_DedicatedNAS_Message dedicatedNAS_Message; // mandatory
    public RRC_ng_5G_S_TMSI_Value ng_5G_S_TMSI_Value; // optional
    public AsnOctetString lateNonCriticalExtension; // optional
    public RRC_nonCriticalExtension_10 nonCriticalExtension; // optional

    // SIZE(1..8)
    public static class RRC_s_NSSAI_List extends AsnSequenceOf<RRC_S_NSSAI> {
    }

    public static class RRC_nonCriticalExtension_10 extends AsnSequence {
    }

    public static class RRC_ng_5G_S_TMSI_Value extends AsnChoice {
        public RRC_NG_5G_S_TMSI ng_5G_S_TMSI;
        public AsnBitString ng_5G_S_TMSI_Part2; // SIZE(9)
    }

    public static class RRC_guami_Type extends AsnEnumerated {
        public static final RRC_guami_Type NATIVE = new RRC_guami_Type(0);
        public static final RRC_guami_Type MAPPED = new RRC_guami_Type(1);
    
        private RRC_guami_Type(long value) {
            super(value);
        }
    }
}

