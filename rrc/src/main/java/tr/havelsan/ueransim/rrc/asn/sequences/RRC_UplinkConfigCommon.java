/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_TimeAlignmentTimer;

public class RRC_UplinkConfigCommon extends AsnSequence {
    public RRC_FrequencyInfoUL frequencyInfoUL; // optional
    public RRC_BWP_UplinkCommon initialUplinkBWP; // optional
    public RRC_TimeAlignmentTimer dummy; // mandatory
}

