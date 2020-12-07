/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_BWP_Id;

public class RRC_BWP_Downlink extends AsnSequence {
    public RRC_BWP_Id bwp_Id; // mandatory
    public RRC_BWP_DownlinkCommon bwp_Common; // optional
    public RRC_BWP_DownlinkDedicated bwp_Dedicated; // optional
}

