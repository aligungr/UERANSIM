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
import tr.havelsan.ueransim.rrc.asn.integers.RRC_TAG_Id;

public class RRC_TAG_Config extends AsnSequence {
    public RRC_tag_ToReleaseList tag_ToReleaseList; // optional, SIZE(1..4)
    public RRC_tag_ToAddModList tag_ToAddModList; // optional, SIZE(1..4)

    // SIZE(1..4)
    public static class RRC_tag_ToReleaseList extends AsnSequenceOf<RRC_TAG_Id> {
    }

    // SIZE(1..4)
    public static class RRC_tag_ToAddModList extends AsnSequenceOf<RRC_TAG> {
    }
}

