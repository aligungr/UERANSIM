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
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_SIB1;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_SystemInformation;

public class RRC_BCCH_DL_SCH_MessageType extends AsnChoice {
    public RRC_c1_12 c1;
    public RRC_messageClassExtension_6 messageClassExtension;

    public static class RRC_c1_12 extends AsnChoice {
        public RRC_SystemInformation systemInformation;
        public RRC_SIB1 systemInformationBlockType1;
    }

    public static class RRC_messageClassExtension_6 extends AsnSequence {
    }
}

