/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_DRB_Identity;

public class RRC_DRB_CountMSB_Info extends AsnSequence {
    public RRC_DRB_Identity drb_Identity; // mandatory
    public AsnInteger countMSB_Uplink; // mandatory, VALUE(0..33554431)
    public AsnInteger countMSB_Downlink; // mandatory, VALUE(0..33554431)
}

