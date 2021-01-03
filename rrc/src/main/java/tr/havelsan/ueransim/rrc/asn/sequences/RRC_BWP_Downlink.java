/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_BWP_Id;

public class RRC_BWP_Downlink extends AsnSequence {
    public RRC_BWP_Id bwp_Id; // mandatory
    public RRC_BWP_DownlinkCommon bwp_Common; // optional
    public RRC_BWP_DownlinkDedicated bwp_Dedicated; // optional
}

