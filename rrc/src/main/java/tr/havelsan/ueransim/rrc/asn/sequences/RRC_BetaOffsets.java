/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_BetaOffsets extends AsnSequence {
    public AsnInteger betaOffsetACK_Index1; // optional, VALUE(0..31)
    public AsnInteger betaOffsetACK_Index2; // optional, VALUE(0..31)
    public AsnInteger betaOffsetACK_Index3; // optional, VALUE(0..31)
    public AsnInteger betaOffsetCSI_Part1_Index1; // optional, VALUE(0..31)
    public AsnInteger betaOffsetCSI_Part1_Index2; // optional, VALUE(0..31)
    public AsnInteger betaOffsetCSI_Part2_Index1; // optional, VALUE(0..31)
    public AsnInteger betaOffsetCSI_Part2_Index2; // optional, VALUE(0..31)
}

