/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnBoolean;
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_BWP_Id;

public class RRC_UplinkTxDirectCurrentBWP extends AsnSequence {
    public RRC_BWP_Id bwp_Id; // mandatory
    public AsnBoolean shift7dot5kHz; // mandatory
    public AsnInteger txDirectCurrentLocation; // mandatory, VALUE(0..3301)
}

