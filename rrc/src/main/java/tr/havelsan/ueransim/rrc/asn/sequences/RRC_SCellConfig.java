/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SCellIndex;

public class RRC_SCellConfig extends AsnSequence {
    public RRC_SCellIndex sCellIndex; // mandatory
    public RRC_ServingCellConfigCommon sCellConfigCommon; // optional
    public RRC_ServingCellConfig sCellConfigDedicated; // optional
    public RRC_ext1_25 ext1; // optional

    public static class RRC_ext1_25 extends AsnSequence {
        public RRC_SSB_MTC smtc; // optional
    }
}

