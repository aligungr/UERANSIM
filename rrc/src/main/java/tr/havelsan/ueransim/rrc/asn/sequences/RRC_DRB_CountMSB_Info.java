/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
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

