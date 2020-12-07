/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_MIB;

public class RRC_BCCH_BCH_MessageType extends AsnChoice {
    public RRC_MIB mib;
    public RRC_messageClassExtension_7 messageClassExtension;

    public static class RRC_messageClassExtension_7 extends AsnSequence {
    }
}

