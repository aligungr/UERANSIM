/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_DummyH extends AsnSequence {
    public AsnInteger burstLength; // mandatory, VALUE(1..2)
    public AsnInteger maxSimultaneousResourceSetsPerCC; // mandatory, VALUE(1..8)
    public AsnInteger maxConfiguredResourceSetsPerCC; // mandatory, VALUE(1..64)
    public AsnInteger maxConfiguredResourceSetsAllCC; // mandatory, VALUE(1..128)
}

