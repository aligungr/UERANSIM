/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_DownlinkConfigCommonSIB extends AsnSequence {
    public RRC_FrequencyInfoDL_SIB frequencyInfoDL; // mandatory
    public RRC_BWP_DownlinkCommon initialDownlinkBWP; // mandatory
    public RRC_BCCH_Config bcch_Config; // mandatory
    public RRC_PCCH_Config pcch_Config; // mandatory
}

