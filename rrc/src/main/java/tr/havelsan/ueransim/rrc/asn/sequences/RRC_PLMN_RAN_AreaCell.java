/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.bit_strings.RRC_CellIdentity;

public class RRC_PLMN_RAN_AreaCell extends AsnSequence {
    public RRC_PLMN_Identity plmn_Identity; // optional
    public RRC_ran_AreaCells ran_AreaCells; // mandatory, SIZE(1..32)

    // SIZE(1..32)
    public static class RRC_ran_AreaCells extends AsnSequenceOf<RRC_CellIdentity> {
    }
}

