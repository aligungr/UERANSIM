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
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_MIB;

public class RRC_BCCH_BCH_MessageType extends AsnChoice {
    public RRC_MIB mib;
    public RRC_messageClassExtension_7 messageClassExtension;

    public static class RRC_messageClassExtension_7 extends AsnSequence {
    }
}

