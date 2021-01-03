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
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SchedulingRequestId;

public class RRC_SchedulingRequestConfig extends AsnSequence {
    public RRC_schedulingRequestToAddModList schedulingRequestToAddModList; // optional, SIZE(1..8)
    public RRC_schedulingRequestToReleaseList schedulingRequestToReleaseList; // optional, SIZE(1..8)

    // SIZE(1..8)
    public static class RRC_schedulingRequestToAddModList extends AsnSequenceOf<RRC_SchedulingRequestToAddMod> {
    }

    // SIZE(1..8)
    public static class RRC_schedulingRequestToReleaseList extends AsnSequenceOf<RRC_SchedulingRequestId> {
    }
}

