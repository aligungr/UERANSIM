/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnBoolean;
import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_NextHopChainingCount;

public class RRC_MasterKeyUpdate extends AsnSequence {
    public AsnBoolean keySetChangeIndicator; // mandatory
    public RRC_NextHopChainingCount nextHopChainingCount; // mandatory
    public AsnOctetString nas_Container; // optional
}

